package se.nrm.dina.dnakey.portal.controller;
 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.IntStream;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent; 
import org.junit.After; 
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before; 
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import org.mockito.Mock; 
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.event.ToggleEvent; 
import org.primefaces.model.UploadedFile;
import se.nrm.dina.dnakey.logic.BlastQueue;
import se.nrm.dina.dnakey.logic.GenbankBlaster;
import se.nrm.dina.dnakey.logic.metadata.BlastMetadata;
import se.nrm.dina.dnakey.logic.metadata.BlastSubjectHsp;
import se.nrm.dina.dnakey.logic.metadata.BlastSubjectMetadata;
import se.nrm.dina.dnakey.logic.vo.NrmData;
import se.nrm.dina.dnakey.portal.ContextMocker;
import se.nrm.dina.dnakey.portal.beans.MessageBean;
import se.nrm.dina.dnakey.portal.logic.SequenceBuilder;
import se.nrm.dina.dnakey.portal.logic.SequenceValidation;
import se.nrm.dina.dnakey.portal.solr.SolrClient;
import se.nrm.dina.dnakey.portal.vo.SolrRecord;

/**
 *
 * @author idali
 */
@RunWith(MockitoJUnitRunner.class)  
public class BlastBeanTest {
  
  private BlastBean instance;
  
  @Mock
  private BlastQueue serviceQueue;
  @Mock
  private FileUploadEvent fileUploadEvent;
  
  @Mock
  private static UploadedFile uploadFile;
  
  @Mock  
  private SequenceBuilder sequenceBuilder;
  
  @Mock
  private MessageBean msg;
  @Mock
  private Languages languages;
  @Mock
  private SequenceValidation validation;
  @Mock
  private Navigator navigator;
  @Mock
  private FileHandler fileHandler;
  @Mock
  private GenbankBlaster blaster;
  @Mock
  private SolrClient solr;
   
  private static List<BlastMetadata> listMetadata;
  private static BlastMetadata metadata;
  private static List<UploadedFile> uploadedFiles;   
  
  private static List<String> sequences;
  private static List<String> fastaFilesPath;


  public BlastBeanTest() {
  }
  
  
  @BeforeClass
  public static void setUpClass() throws Exception {
    listMetadata = new ArrayList<>();
    metadata  = new BlastMetadata();
    listMetadata.add(metadata);
    listMetadata.add(metadata);
    
    uploadedFiles = new ArrayList<>(); 
    IntStream.range(0, 6)
            .forEach(i -> {
              uploadedFiles.add(uploadFile);
            });
    
    sequences = new ArrayList<>();
    sequences.add("asfsadfasf");
    sequences.add("asdfetsiaosgaf");
    
    fastaFilesPath  = new ArrayList<>();
    fastaFilesPath.add("iojojfa");
    fastaFilesPath.add("goasgapjf");
  }

  @AfterClass
  public static void tearDownClass() throws Exception {
    listMetadata = null;
    metadata = null; 
    sequences = null;
    fastaFilesPath = null; 
    uploadedFiles = null;
    uploadFile = null;
  }
  
 
  @Before
  public void setUp() {  
  }
  
  @After
  public void tearDown() {
  }

  /**
   * Test of init method, of class BlastBean.
   */
  @Test
  public void testInit() {
    System.out.println("init");  
     
    instance = new BlastBean();
    FacesContext context = ContextMocker.mockFacesContext();  
    try {
      instance.init(); 
      verify(context, times(1)).isPostback(); 
      assertEquals("nrm", instance.getDatabase());
    } finally {
      context.release();
    }  
  }
  
  /**
   * Test of init method, of class BlastBean.
   */
  @Test
  public void testInitWithPostBack() {
    System.out.println("init");  
     
    instance = new BlastBean();
    FacesContext context = ContextMocker.mockFacesContext();  
    when(context.isPostback()).thenReturn(true); 
    try {
      instance.init(); 
      verify(context, times(1)).isPostback();  
    } finally {
      context.release();
    }  
  }

  /**
   * Test of isIsMax method, of class BlastBean.
   */
  @Test
  public void testIsIsMax() {
    System.out.println("isIsMax"); 
       
    instance = new BlastBean(); 
    boolean result = instance.isIsMax();
    assertFalse(result); 
   
    instance.setUploadedFiles(uploadedFiles);
    result = instance.isIsMax();
    assertTrue(result);
  }

  /**
   * Test of onTabChange method, of class BlastBean.
   */
  @Test
  public void testOnTabChange() {
    System.out.println("onTabChange");
    TabChangeEvent event = null; 
    
    instance = new BlastBean();
    instance.setNumOfTestSeqs(2);
    instance.setTestSequences(null);
    instance.onTabChange(event); 
    assertEquals(0, instance.getNumOfTestSeqs());
    
    instance.setTestSequences("");
    instance.setNumOfTestSeqs(2);
    instance.onTabChange(event); 
    assertEquals(0, instance.getNumOfTestSeqs());
    
    instance.setTestSequences("ADADFIN");
    instance.setNumOfTestSeqs(3);
    instance.onTabChange(event); 
    assertEquals(3, instance.getNumOfTestSeqs());  
  }

  /**
   * Test of handleFileUpload method, of class BlastBean.
   */
  @Test
  public void testHandleFileUpload() {
    System.out.println("handleFileUpload");
 
    instance = getInstance();
    List<String> strings = new ArrayList<>();
    when(uploadFile.getFileName()).thenReturn("testFile"); 
    when(sequenceBuilder.buildSequencesFromUploadFile(uploadFile)).thenReturn(strings);
    when(fileUploadEvent.getFile()).thenReturn(uploadFile);   
    when(languages.getLocale()).thenReturn("sv");  
     
    instance.handleFileUpload(fileUploadEvent);  
    verify(fileUploadEvent, times(1)).getFile(); 
    verify(sequenceBuilder, times(1)).buildSequencesFromUploadFile(uploadFile);  
    
    instance.handleFileUpload(fileUploadEvent); 
    verify(msg, times(1)).addWarning(any(String.class), any(String.class));  
  } 
   
  @Test
  public void testHandleFileUploadNull() {
    
    instance = getInstance();
    uploadFile = null;
    when(fileUploadEvent.getFile()).thenReturn(uploadFile);  
    instance.handleFileUpload(fileUploadEvent);   
    verify(fileUploadEvent, times(1)).getFile(); 
    verify(sequenceBuilder, times(0)).buildSequencesFromUploadFile(uploadFile);  
  }

  /**
   * Test of changeTestNumber method, of class BlastBean.
   */
  @Test
  public void testChangeTestNumber() {
    System.out.println("changeTestNumber"); 
     
    instance = new BlastBean();
    instance.changeTestNumber();
    String result = instance.getTestSequences();
    assertEquals(null, result);
    
    instance.setNumOfTestSeqs(2);
    instance.changeTestNumber();
    result = instance.getTestSequences();
    assertNotNull(result); 
    assertTrue(result.contains(">Seq 2")); 
  }

  /**
   * Test of submit method, of class BlastBean.
   */
  @Test
  public void testSubmitWithEmptySequencesAndTab1() {
    System.out.println("submit");
    
    instance = getInstance();
    instance.setActiveIndex(0);
     
    when(sequenceBuilder.prepareSequenceList(any(String.class))).thenReturn(null); 
    instance.submit(); 
    verify(msg, times(1)).addError(Matchers.anyString(), Matchers.anyString());   
    verify(validation, never()).validate(Matchers.anyList()); 
  }
  
  /**
   * Test of submit method, of class BlastBean.
   */
  @Test
  public void testSubmitWithEmptySequencesAndTab2() {
    System.out.println("submit");
    
    instance = getInstance();
    instance.setActiveIndex(1);
     
    when(sequenceBuilder.convertSequencesMapToList(any(HashMap.class))).thenReturn(null); 
    instance.submit(); 
    verify(msg, times(1)).addError(Matchers.anyString(), Matchers.anyString());   
    verify(validation, never()).validate(Matchers.anyList()); 
  }
  
  /**
   * Test of submit method, of class BlastBean.
   */
  @Test
  public void testSubmitWithEmptySequencesAndTab3() {
    System.out.println("submit");
    
    instance = getInstance();
    instance.setActiveIndex(2);
     
    when(sequenceBuilder.prepareSequenceList(any(String.class))).thenReturn(null); 
    instance.submit(); 
    verify(msg, times(1)).addError(Matchers.anyString(), Matchers.anyString()); 
    verify(validation, never()).validate(Matchers.anyList()); 
  }
  
  /**
   * Test of submit method, of class BlastBean.
   */
  @Test
  public void testSubmitWithSwitchDefault() {
    System.out.println("submit");
    
    instance = getInstance();
    instance.setActiveIndex(6);
      
    instance.submit(); 
    verify(msg, times(1)).addError(Matchers.anyString(), Matchers.anyString());   
    verify(validation, never()).validate(Matchers.anyList());  
  }
  
  @Test
  public void testSubmitWithTab1() {
    System.out.println("submit");
    
    instance = getInstance();
    instance.setActiveIndex(0); 
      
    when(sequenceBuilder.prepareSequenceList(any(String.class))).thenReturn(sequences); 
    when(validation.validate(sequences)).thenReturn(true);
    when(fileHandler.createFastaFiles(sequences)).thenReturn(fastaFilesPath); 
    when(serviceQueue.run(eq(fastaFilesPath), Matchers.anyString())).thenReturn(listMetadata);
    
     
    instance.submit(); 
    verify(validation, times(1)).validate(Matchers.anyList());  
    verify(fileHandler, times(1)).createFastaFiles(Matchers.anyList());
    verify(fileHandler, times(1)).deleteTempFiles(Matchers.anyList());
    verify(navigator, times(1)).result();
  }
  
  
  @Test
  public void testSubmitWithTab1AndInvalidSequence() {
    System.out.println("submit");
    
    instance = getInstance();
    instance.setActiveIndex(0); 
      
    when(sequenceBuilder.prepareSequenceList(any(String.class))).thenReturn(sequences); 
    when(validation.validate(sequences)).thenReturn(false);
    when(fileHandler.createFastaFiles(sequences)).thenReturn(fastaFilesPath); 
    when(serviceQueue.run(eq(fastaFilesPath), Matchers.anyString())).thenReturn(listMetadata);
    
     
    instance.submit();  
    verify(validation, times(1)).validate(Matchers.anyList());   
    verify(msg, times(1)).createErrorMsgs(Matchers.anyString(), Matchers.anyList());
  }

  /**
   * Test of removefile method, of class BlastBean.
   */
   @Test
  public void testRemovefile() {
    System.out.println("removefile"); 
    
    when(uploadFile.getFileName()).thenReturn("testFile"); 
    
    List<UploadedFile> testFiles = new ArrayList<>(); 
    IntStream.range(0, 6)
            .forEach(i -> {
              testFiles.add(uploadFile);
            });
     
    instance = new BlastBean();
    instance.setUploadedFiles(testFiles);
    
    assertEquals(instance.getUploadedFiles().size(), 6);  
    instance.removefile(uploadFile);  
    assertEquals(instance.getUploadedFiles().size(), 5); 
  }

  /**
   * Test of remotBlast method, of class BlastBean.
   */
  @Test
  public void testRemotBlast() {
    System.out.println("remotBlast");
    
    String rid = "sfsafdlsadf";
    String sequence = "abcdefg";
    BlastMetadata mock = mock(BlastMetadata.class);
    when(mock.getSequence()).thenReturn(sequence);
    when(blaster.remoteGenbankBlast(sequence)).thenReturn(rid);
  
    instance = getInstance();
    instance.remotBlast(mock);  
    assertEquals(instance.ridByMetadata(mock), rid); 
  }

  /**
   * Test of onRowToggleSingle method, of class BlastBean.
   */
  @Test
  public void testOnRowToggleSingleNotNrm() {
    System.out.println("onRowToggleSingle"); 
    
    BlastSubjectMetadata testMetadata = new BlastSubjectMetadata(1, null, null, 
            null, null, null, "1234", null, 0, null, false);
    ToggleEvent event = mock(ToggleEvent.class);
    
    when(event.getData()).thenReturn(testMetadata);
    instance = getInstance();
    instance.onRowToggleSingle(event); 
    Assert.assertNull(testMetadata.getNrmData()); 
  }
  

  
  /**
   * Test of onRowToggleSingle method, of class BlastBean.
   */
  @Test
  public void testOnRowToggleSingleNrmWithData() {
    System.out.println("onRowToggleSingle"); 
    
    BlastSubjectMetadata testMetadata = new BlastSubjectMetadata(1, null, null, 
            null, null, null, "1234", null, 0, null, true);
    
    NrmData data = new NrmData("1234", null, null, null, null, null, null, null, true, null, null , null, null);
    testMetadata.setNrmData(data);
     
    String catalogNumber = testMetadata.getCatalogNumber();
    ToggleEvent event = mock(ToggleEvent.class);
    
    when(event.getData()).thenReturn(testMetadata);
    
    SolrRecord record = new SolrRecord();
    when(solr.getRecordByCollectionObjectCatalognumber(eq(catalogNumber), any(String.class))).thenReturn(record);
    
    record.setCatalogNumber(catalogNumber);
    instance = getInstance();
    instance.onRowToggleSingle(event); 
    assertNotNull(testMetadata.getNrmData()); 
    assertEquals(testMetadata.getNrmData().getCatalogNumber(), catalogNumber);
    verify(solr, times(0)).getRecordByCollectionObjectCatalognumber(eq(catalogNumber), any(String.class));
  }
  
    
  /**
   * Test of onRowToggleSingle method, of class BlastBean.
   */
  @Test
  public void testOnRowToggleSingleNrmWithoutData() {
    System.out.println("onRowToggleSingle"); 
    
    BlastSubjectMetadata testMetadata = new BlastSubjectMetadata(1, null, null, 
            null, null, null, "1234", null, 0, null, true);
    ToggleEvent event = mock(ToggleEvent.class);
    String catalogNumber = testMetadata.getCatalogNumber();
    
    SolrRecord record = new SolrRecord();
    when(solr.getRecordByCollectionObjectCatalognumber(eq(catalogNumber), any(String.class))).thenReturn(record);
    
    when(event.getData()).thenReturn(testMetadata);
    instance = getInstance();
    instance.onRowToggleSingle(event); 
    assertNotNull(testMetadata.getNrmData()); 
    verify(solr, times(1)).getRecordByCollectionObjectCatalognumber(eq(catalogNumber), any(String.class));
  }

  /**
   * Test of clear method, of class BlastBean.
   */
  @Test
  public void testClear() {
    System.out.println("clear");
    instance = new BlastBean();
    instance.setDatabase("test");
    instance.clear(); 
    assertEquals(instance.getDatabase(), "nrm");
  }

  /**
   * Test of newblast method, of class BlastBean.
   */
  @Test
  public void testNewblast() {
    System.out.println("newblast");
    instance = getInstance();
    instance.newblast();
    
    verify(navigator, times(1)).home();
  }

  /**
   * Test of getActiveIndex method, of class BlastBean.
   */
  @Test
  public void testGetActiveIndex() {
    System.out.println("getActiveIndex");
    instance = new BlastBean();
    int expResult = 0;
    int result = instance.getActiveIndex();
    assertEquals(expResult, result);  
  }

  /**
   * Test of setActiveIndex method, of class BlastBean.
   */
  @Test
  public void testSetActiveIndex() {
    System.out.println("setActiveIndex");
    int activeIndex = 2;
    instance = new BlastBean();
    instance.setActiveIndex(activeIndex); 
    
    assertEquals(instance.getActiveIndex(), 2);
  }

  /**
   * Test of getSequenceList method, of class BlastBean.
   */
  @Test
  public void testGetSequenceList() {
    System.out.println("getSequenceList");
    instance = new BlastBean();
    String expResult = null;
    String result = instance.getSequenceList();
    assertEquals(expResult, result); 
  }

  /**
   * Test of setSequenceList method, of class BlastBean.
   */
  @Test
  public void testSetSequenceList() {
    System.out.println("setSequenceList");
    String sequenceList = "asdfsadff";
    instance = new BlastBean();
    instance.setSequenceList(sequenceList); 
    assertEquals(instance.getSequenceList(), sequenceList);
  }

  /**
   * Test of getMetadata method, of class BlastBean.
   */
  @Test
  public void testGetMetadata() {
    System.out.println("getMetadata");
    instance = new BlastBean();
    BlastMetadata expResult = null;
    BlastMetadata result = instance.getMetadata();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getDbFullName method, of class BlastBean.
   */
  @Test
  public void testGetDbFullNameEn() {
    System.out.println("getDbFullName");
     
    when(languages.isIsSwedish()).thenReturn(Boolean.FALSE);
    instance = getInstance();
    
    instance.setDatabase("nrm");
    String expResult = "Swedish vertebrate animals (COI, 16S)";
    String result = instance.getDbFullName();
    assertEquals(expResult, result); 
    
    instance.setDatabase("bold");
    expResult = "Barcode sequences for Swedish organisms (COI, matK, rbcL, 16S*)";
    result = instance.getDbFullName();
    assertEquals(expResult, result); 
     
    instance.setDatabase("genbank");
    expResult = "Barcode sequences from Genbank (COI, matK, rbcL, 16S*)";
    result = instance.getDbFullName();
    assertEquals(expResult, result);  
  }
  
  
  /**
   * Test of getDbFullName method, of class BlastBean.
   */
  @Test
  public void testGetDbFullNameSv() {
    System.out.println("getDbFullName");
     
    when(languages.isIsSwedish()).thenReturn(Boolean.TRUE);
    instance = getInstance();
    
    instance.setDatabase("nrm");
    String expResult = "Svenska ryggradsdjur (COI, 16S)";
    String result = instance.getDbFullName();
    assertEquals(expResult, result); 
    
    instance.setDatabase("bold");
    expResult = "Barkodsekvenser för svenska organismer (COI, matK, rbcL, 16S*)";
    result = instance.getDbFullName();
    assertEquals(expResult, result); 
     
    instance.setDatabase("genbank");
    expResult = "Barkodsekvenser från Genbank (COI, matK, rbcL, 16S*)";
    result = instance.getDbFullName();
    assertEquals(expResult, result);  
  }

  /**
   * Test of openLowMatchList method, of class BlastBean.
   */
  @Test
  public void testOpenLowMatchList() {
    System.out.println("openLowMatchList"); 
    instance = new BlastBean();
    instance.openLowMatchList(metadata); 
    assertTrue(metadata.isOpenLowMatch());
  }

  /**
   * Test of getAlignment method, of class BlastBean.
   */
  @Test
  public void testGetAlignment() {
    System.out.println("getAlignment");
    
    List<BlastSubjectHsp> list = new ArrayList();
    BlastSubjectMetadata subMetada = new BlastSubjectMetadata(0, "sdf", "asf", 
            null, null, null, null, null, 0, list, true);  
     
    instance = new BlastBean();
    instance.getAlignment(subMetada);  
    assertNotNull(instance.getSelectedHsps());
  }

  /**
   * Test of getSelectedHsps method, of class BlastBean.
   */
 @Test
  public void testGetSelectedHsps() {
    System.out.println("getSelectedHsps");
    instance = new BlastBean();
    List<BlastSubjectHsp> expResult = null;
    List<BlastSubjectHsp> result = instance.getSelectedHsps();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getSelectedMetadata method, of class BlastBean.
   */
  @Test
  public void testGetSelectedMetadata() {
    System.out.println("getSelectedMetadata");
    instance = new BlastBean();
    BlastSubjectMetadata expResult = null;
    BlastSubjectMetadata result = instance.getSelectedMetadata();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getSelectedSubMetadata method, of class BlastBean.
   */
  @Test
  public void testGetSelectedSubMetadata() {
    System.out.println("getSelectedSubMetadata");
    instance = new BlastBean();
    BlastSubjectMetadata expResult = null;
    BlastSubjectMetadata result = instance.getSelectedSubMetadata();
    assertEquals(expResult, result); 
  }

  /**
   * Test of databaseChanged method, of class BlastBean.
   */
  @Test
  public void testDatabaseChanged() {
    System.out.println("databaseChanged");
    AjaxBehaviorEvent event = null;
    instance = new BlastBean();
    instance.databaseChanged(event); 
  }

  /**
   * Test of getListMetadata method, of class BlastBean.
   */
  @Test
  public void testGetListMetadata() {
    System.out.println("getListMetadata");
    instance = new BlastBean();
    List<BlastMetadata> expResult = new ArrayList();
    List<BlastMetadata> result = instance.getListMetadata();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getUrlEncode method, of class BlastBean.
   */
  @Test
  public void testGetUrlEncode() {
    System.out.println("getUrlEncode");
    instance = new BlastBean();
    String expResult = "param=dnakey&catalogNumber=";
    String result = instance.getUrlEncode();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getBlastMetadata method, of class BlastBean.
   */
  @Test
  public void testGetBlastMetadata() {
    System.out.println("getBlastMetadata");
    instance = new BlastBean();
    BlastMetadata expResult = null;
    BlastMetadata result = instance.getBlastMetadata();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getBlastportalUrl method, of class BlastBean.
   */
  @Test
  public void testGetBlastportalUrl() {
    System.out.println("getBlastportalUrl");
    instance = new BlastBean();
    String expResult = "https://blast.ncbi.nlm.nih.gov/Blast.cgi?PROGRAM=blastn&PAGE_TYPE=BlastSearch&LINK_LOC=blasthome";
    String result = instance.getBlastportalUrl();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getNumOfTestSeqs method, of class BlastBean.
   */
  @Test
  public void testGetNumOfTestSeqs() {
    System.out.println("getNumOfTestSeqs");
    int numOfTestSeqs = 3;
    instance = new BlastBean();
    instance.setNumOfTestSeqs(numOfTestSeqs); 
    int result = instance.getNumOfTestSeqs();
    assertEquals(3, result);
  }

  /**
   * Test of setNumOfTestSeqs method, of class BlastBean.
   */
  @Test
  public void testSetNumOfTestSeqs() {
    System.out.println("setNumOfTestSeqs");
    int numOfTestSeqs = 3;
    instance = new BlastBean();
    instance.setNumOfTestSeqs(numOfTestSeqs); 
    assertEquals(instance.getNumOfTestSeqs(), 3);
  }

  /**
   * Test of getUploadedFiles method, of class BlastBean.
   */
  @Test
  public void testGetUploadedFiles() {
    System.out.println("getUploadedFiles");
     
    instance = new BlastBean();
    instance.setUploadedFiles(uploadedFiles);  
    List<UploadedFile> result = instance.getUploadedFiles();
    assertNotNull(result); 
  }

  /**
   * Test of setUploadedFiles method, of class BlastBean.
   */
  @Test
  public void testSetUploadedFiles() {
    System.out.println("setUploadedFiles");
 
    instance = new BlastBean();
    instance.setUploadedFiles(uploadedFiles); 
    
    assertNotNull(instance.getUploadedFiles());
  }

  /**
   * Test of getDatabase method, of class BlastBean.
   */
  @Test
  public void testGetDatabase() {
    System.out.println("getDatabase"); 
    
    String database = "nrm";
    instance = new BlastBean();
    instance.setDatabase(database); 
    String result = instance.getDatabase();
    assertEquals(database, result); 
  }

  /**
   * Test of setDatabase method, of class BlastBean.
   */
  @Test
  public void testSetDatabase() {
    System.out.println("setDatabase");
    String database = "nrm";
    instance = new BlastBean();
    instance.setDatabase(database); 
    assertEquals(instance.getDatabase(), database); 
  }

  /**
   * Test of getTestSequences method, of class BlastBean.
   */
  @Test
  public void testGetTestSequences() {
    System.out.println("getTestSequences");
    instance = new BlastBean();
    String testSequences = "sfsadf";
    instance.setTestSequences(testSequences); 
    String result = instance.getTestSequences();
    assertEquals(testSequences, result); 
  }

  /**
   * Test of setTestSequences method, of class BlastBean.
   */
  @Test
  public void testSetTestSequences() {
    System.out.println("setTestSequences");
    String testSequences = "adfdase";
    instance = new BlastBean();
    instance.setTestSequences(testSequences); 
    assertEquals(instance.getTestSequences(), testSequences);
  }

  /**
   * Test of getBlastdocument method, of class BlastBean.
   */
  @Test
  public void testGetBlastdocument() {
    System.out.println("getBlastdocument");
    instance = new BlastBean();
    String expResult = "http://blast.ncbi.nlm.nih.gov/Blast.cgi?CMD=Web&PAGE_TYPE=BlastDocs";
    String result = instance.getBlastdocument();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getBlastPlus method, of class BlastBean.
   */
  @Test
  public void testGetBlastPlus() {
    System.out.println("getBlastPlus");
    instance = new BlastBean();
    String expResult = "http://blast.ncbi.nlm.nih.gov/Blast.cgi?CMD=Web&PAGE_TYPE=BlastDocs&DOC_TYPE=Download";
    String result = instance.getBlastPlus();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getGenbankRidUrl method, of class BlastBean.
   */
  @Test
  public void testGetGenbankRidUrl() {
    System.out.println("getGenbankRidUrl");
    instance = new BlastBean();
    String expResult = "http://www.ncbi.nlm.nih.gov/blast/Blast.cgi?CMD=Get&RID=";
    String result = instance.getGenbankRidUrl();
    assertEquals(expResult, result); 
  }

  /**
   * Test of ridByMetadata method, of class BlastBean.
   */
  @Test
  public void testRidByMetadata() {
    System.out.println("ridByMetadata"); 
    java.util.Map<BlastMetadata, String> ridMap = new HashMap<>();
    ridMap.put(metadata, "12345");
   
    instance = new BlastBean();
    String expResult = "12345";
 
    instance.setRidMap(ridMap);
    String result = instance.ridByMetadata(metadata);
    assertEquals(expResult, result); 
  }

  /**
   * Test of getTotalSequences method, of class BlastBean.
   */
  @Test
  public void testGetTotalSequences() {
    System.out.println("getTotalSequences");
    instance = new BlastBean();
    int expResult = 0; 
    int result = instance.getTotalSequences();
    assertEquals(expResult, result);  
  } 
  
  private BlastBean getInstance() {
    return new BlastBean(sequenceBuilder, msg, languages, serviceQueue, 
            validation, navigator, fileHandler, blaster, solr);
  }
}
