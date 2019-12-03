package se.nrm.dina.dnakey.portal.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map; 
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import org.primefaces.event.FileUploadEvent;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.extern.slf4j.Slf4j; 
import org.primefaces.event.TabChangeEvent;
import org.primefaces.event.ToggleEvent;
import org.primefaces.model.UploadedFile;
import se.nrm.dina.dnakey.logic.BlastQueue;
import se.nrm.dina.dnakey.logic.GenbankBlaster;
import se.nrm.dina.dnakey.logic.metadata.BlastMetadata;
import se.nrm.dina.dnakey.logic.metadata.BlastSubjectHsp;
import se.nrm.dina.dnakey.logic.metadata.BlastSubjectMetadata;
import se.nrm.dina.dnakey.logic.vo.NrmData;
import se.nrm.dina.dnakey.portal.beans.MessageBean; 
import se.nrm.dina.dnakey.portal.logic.SequenceBuilder;
import se.nrm.dina.dnakey.portal.util.ConstantString;
import se.nrm.dina.dnakey.portal.logic.SequenceValidation;
import se.nrm.dina.dnakey.portal.solr.SolrClient;
import se.nrm.dina.dnakey.portal.vo.SolrRecord;
import se.nrm.dina.dnakey.portal.util.FastaFiles;

/**
 *
 * @author idali
 */
@SessionScoped
@Named("blast")
@Slf4j
public class BlastBean implements Serializable {

  private String sequenceList;                                                    // sequence or sequences are listed in tab1
  private String testSequences;                                                   // list of sequences in tab3 

  private List<UploadedFile> uploadedFiles = new ArrayList<>();                   // upload fasta files in tab2
  private Map<String, List<String>> sequencesMap = new HashMap();

  private List<String> sequences;

  private int numOfTestSeqs = 0;
  private final int MAX_UPLOADED_FILES = 5;
  private int activeIndex = 0;                                                    // active tab in sequence page

  private Map<BlastMetadata, String> ridMap = new HashMap<>();
  private String database;

  // Result
  private BlastMetadata metadata; 
  private int totalSequences = 0;
  // End of result

  private String errorTitle;
  private String message;

  @Inject
  private Languages languages;

  @Inject
  private Navigator navigator;

  @Inject
  private MessageBean msg;

  @Inject
  private SequenceBuilder sequenceBuilder;

  @Inject
  private FileHandler fileHandler;

  @Inject
  private SequenceValidation validation;

  @Inject
  private SolrClient solr;

  @Inject
  private BlastQueue serviceQueue;

  @Inject
  private GenbankBlaster blaster;

  private BlastMetadata blastMetadata;
  private BlastSubjectMetadata selectedMetadata;
  private BlastSubjectMetadata selectedSubMetadata;
  private List<BlastSubjectHsp> selectedHsps;

  private List<BlastMetadata> listMetadata = new ArrayList<>();

  private String urlEncode;

  private SolrRecord selectedRecord;
  private NrmData data;
 
  private FacesContext context;
//  RequestContext requestContext;

  public BlastBean() {
    log.info("BlastBean");

    ridMap = new HashMap<>();
    sequences = new ArrayList<>();
    sequencesMap = new HashMap();
    database = ConstantString.getInstance().getDefaultBlastDb();
    urlEncode = ConstantString.getInstance().getNaturarvUrlEncode();

    totalSequences = 0;
  }
  
  public BlastBean(SequenceBuilder sequenceBuilder, MessageBean msg,
          Languages languages, BlastQueue serviceQueue, SequenceValidation validation, 
          Navigator navigator, FileHandler fileHander, GenbankBlaster blaster, SolrClient solr) {
    this.sequenceBuilder = sequenceBuilder; 
    this.msg = msg;
    this.languages = languages;
    this.serviceQueue = serviceQueue;
    this.validation = validation;
    this.navigator = navigator;
    this.fileHandler = fileHander;
    this.blaster = blaster; 
    this.solr = solr;
  }

  @PostConstruct
  public void init() {
    log.info("init");

    context = FacesContext.getCurrentInstance();
    boolean isPostBack = context.isPostback();        // false if page is start up or reloaded by browser  

//    requestContext = RequestContext.getCurrentInstance();
    log.info("isPostBack : {}", isPostBack);

    if (!isPostBack) {
      sequences = new ArrayList<>();
      sequencesMap = new HashMap();
      sequenceList = "";
      testSequences = "";
      database = ConstantString.getInstance().getDefaultBlastDb(); 
      totalSequences = 0;

      clear();
    }
  }

  /**
   * Disable upload file if uploaded file size is big than max uploaded file
   * size. Max uploaded files is 5
   *
   * @return boolean
   */
  public boolean isIsMax() {
    return uploadedFiles.size() >= MAX_UPLOADED_FILES;
  }

  /**
   * onTabChange event fires when user click tab.
   *
   * @param event
   */
  public void onTabChange(TabChangeEvent event) {
    log.info("onTabChange : {} ", activeIndex);

    if (testSequences == null || testSequences.trim().isEmpty()) {
      numOfTestSeqs = 0;
    }
  }

  /**
   * Upload file
   *
   * @param event
   */
  public void handleFileUpload(FileUploadEvent event) {
    log.info("handleFileUpload");
    UploadedFile uploadedFile = event.getFile(); 
    if (uploadedFile != null) {
      if (!sequencesMap.containsKey(uploadedFile.getFileName())) {
        uploadedFiles.add(uploadedFile);
        List<String> strings = sequenceBuilder.buildSequencesFromUploadFile(uploadedFile);
        sequencesMap.put(uploadedFile.getFileName(), strings);
      } else {
        message = ConstantString.getInstance().getText("file_uploaded_" + languages.getLocale());
        msg.addWarning(message, message);
      }
    }
  }

  /**
   * changeTestNumber -- event when number of test sequences changed
   */
  public void changeTestNumber() {
    log.info("changeTestNumber : {}", numOfTestSeqs); 
    testSequences = FastaFiles.getInstance().getSequences(numOfTestSeqs); 
  }

  /**
   * submit sequences to blast
   */
  public void submit() {
    log.info("submit : {}", activeIndex); 
     
    switch (activeIndex) {
      case 0:
        sequences = sequenceBuilder.prepareSequenceList(sequenceList); 
        break;
      case 1:
        sequences = sequenceBuilder.convertSequencesMapToList(sequencesMap);
        break;
      case 2:
        sequences = sequenceBuilder.prepareSequenceList(testSequences);
        break;
      default:
        break;
    } 
    if (sequences == null) {
      errorTitle = ConstantString.getInstance().getText("blastfailed_" + languages.getLocale());
      message = ConstantString.getInstance().getText("inputdata_" + languages.getLocale());
      msg.addError(errorTitle, message);
    } else {
      run();
    }
  }

  /**
   * remove uploaded file
   *
   * @param file
   */
  public void removefile(UploadedFile file) {
    log.info("removefile : {}", file.getFileName()); 
    uploadedFiles.remove(file);
    sequencesMap.remove(file.getFileName()); 
  }

  /**
   * run blast
   */
  private void run() {
    if (validation.validate(sequences)) {
      blast();
      navigator.result();
    } else {
      errorTitle = ConstantString.getInstance().getText("validationfailed_" + languages.getLocale());
      msg.createErrorMsgs(errorTitle, validation.getErrorMsgs());
    }
  }

  /**
   * Blasts a single sequence
   */
  private void blast() {
    log.info("blast : {}", sequences.size());

    ridMap = new HashMap<>();
    totalSequences = sequences.size();                                      // display in result page

    List<String> fastaFilesPath = fileHandler.createFastaFiles(sequences);
    listMetadata = serviceQueue.run(fastaFilesPath, database);
    fileHandler.deleteTempFiles(fastaFilesPath);                            // remove temp fasta files
 
    metadata = listMetadata.get(0); 
    int count = 0; 
    for (BlastMetadata bm : listMetadata) {
      bm.setSequence(sequences.get(count)); 
      count++;
    }
  }

  /**
   * Blast sequence from remote genbank service
   *
   *
   * @param metadata
   */
  public void remotBlast(BlastMetadata metadata) {
    log.info("remotBlast : {}", metadata.getSequence());

    String seq = metadata.getSequence();
    String rid = blaster.remoteGenbankBlast(seq); 
    ridMap.remove(metadata);
    ridMap.put(metadata, rid);
  }

  /**
   * Toggle result table row to open expand content
   *
   * @param event - ToggleEvent
   */
  public void onRowToggleSingle(ToggleEvent event) {
    log.info("onRowToggleSingle : {}", event);

    selectedRecord = null;
    selectedMetadata = (BlastSubjectMetadata) event.getData();
    String catalognumber = selectedMetadata.getCatalogNumber();
 
    if (selectedMetadata.isNrm() && selectedMetadata.getNrmData() == null) {
      selectedRecord = solr.getRecordByCollectionObjectCatalognumber(catalognumber, database); 
      data = new NrmData(selectedRecord.getCatalogNumber(), selectedRecord.getTxFullName(),
              selectedRecord.getCollectionName(), selectedRecord.getCommonNames(),
              selectedRecord.getLocalityWithCountryAndContinent(), selectedRecord.getCoordinateString(),
              selectedRecord.getStartDate(), selectedRecord.getCollectors(), selectedRecord.isImage(),
              selectedRecord.getMorphbankImageId(), selectedRecord.getMorphbankId(),
              selectedRecord.getCountry(), selectedRecord.getContinent());
      selectedMetadata.setNrmData(data);
    }
  }

  /**
   * Clear all the sequences in tabs
   */
  public void clear() {
    sequenceList = null;
    uploadedFiles = new ArrayList<>();
    testSequences = null;

    sequencesMap.clear();

    numOfTestSeqs = 0;
    database = ConstantString.getInstance().getDefaultBlastDb();
    ridMap.clear();
  }

  /**
   * newblast
   */
  public void newblast() {
    log.info("newblast : {}", activeIndex);

    totalSequences = 0;
    ridMap.clear();
    sequencesMap.clear();
    uploadedFiles.clear();

    navigator.home();
  }

  public int getActiveIndex() {
    return activeIndex;
  }

  public void setActiveIndex(int activeIndex) {
    this.activeIndex = activeIndex;
  }

  public String getSequenceList() {
    return sequenceList;
  }

  public void setSequenceList(String sequenceList) {
    this.sequenceList = sequenceList;
  }

  public BlastMetadata getMetadata() {
    return metadata;
  }

  /**
   * Description of blast database
   *
   * @return
   */
  public String getDbFullName() {
    boolean isSwedish = languages.isIsSwedish();
    switch (database) {
      case "nrm":
        return ConstantString.getInstance().getNrmDbName(isSwedish);
      case "bold":
        return ConstantString.getInstance().getBoldDbName(isSwedish);
      default:
        return ConstantString.getInstance().getGenbankDbName(isSwedish);
    }
  }

  public void openLowMatchList(BlastMetadata metadata) {
    log.info("openLowMatchList : {}", metadata);
    metadata.setOpenLowMatch(true);
  }

  public void getAlignment(BlastSubjectMetadata subjectMetadata) {
    log.info("getAlignment {}", subjectMetadata);

    selectedSubMetadata = subjectMetadata;
    selectedHsps = selectedSubMetadata.getSubjectHspList();
  }

  public List<BlastSubjectHsp> getSelectedHsps() {
    return selectedHsps;
  }

  public BlastSubjectMetadata getSelectedMetadata() {
    return selectedMetadata;
  }

  public BlastSubjectMetadata getSelectedSubMetadata() {
    return selectedSubMetadata;
  }
 
  public void databaseChanged(final AjaxBehaviorEvent event) {
    log.info("databaseChanged : {}", database);
  }
 
  public List<BlastMetadata> getListMetadata() {
    return listMetadata;
  }

  public String getUrlEncode() {
    return urlEncode;
  }

  public BlastMetadata getBlastMetadata() {
    return blastMetadata;
  }

  public String getBlastportalUrl() {
    return "https://blast.ncbi.nlm.nih.gov/Blast.cgi?PROGRAM=blastn&PAGE_TYPE=BlastSearch&LINK_LOC=blasthome";
  }

  public int getNumOfTestSeqs() {
    return numOfTestSeqs;
  }

  public void setNumOfTestSeqs(int numOfTestSeqs) {
    this.numOfTestSeqs = numOfTestSeqs;
  }

  public List<UploadedFile> getUploadedFiles() {
    return uploadedFiles;
  }

  public void setUploadedFiles(List<UploadedFile> uploadedFiles) {
    this.uploadedFiles = uploadedFiles;
  }

  public String getDatabase() {
    return database;
  }

  public void setDatabase(String database) {
    this.database = database;
  }

  public String getTestSequences() {
    return testSequences;
  }

  public void setTestSequences(String testSequences) {
    this.testSequences = testSequences;
  }

  public String getBlastdocument() {
    return "http://blast.ncbi.nlm.nih.gov/Blast.cgi?CMD=Web&PAGE_TYPE=BlastDocs";
  }

  public String getBlastPlus() {
    return "http://blast.ncbi.nlm.nih.gov/Blast.cgi?CMD=Web&PAGE_TYPE=BlastDocs&DOC_TYPE=Download";
  }

  public String getGenbankRidUrl() {
    return "http://www.ncbi.nlm.nih.gov/blast/Blast.cgi?CMD=Get&RID=";
  }

  public String ridByMetadata(BlastMetadata metadata) {
    log.info("ridByMetadata" );  
    return ridMap.get(metadata);
  }

  public void setRidMap(Map<BlastMetadata, String> ridMap) {
    this.ridMap = ridMap;
  }
   
  public int getTotalSequences() {
    return totalSequences;
  } 
}
