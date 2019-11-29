package se.nrm.dina.dnakey.portal.controller;
 
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent; 
import org.junit.After; 
import org.junit.Before; 
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import static org.mockito.Matchers.any;
import org.mockito.Mock; 
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.event.ToggleEvent; 
import org.primefaces.model.UploadedFile;
import se.nrm.dina.dnakey.logic.metadata.BlastMetadata;
import se.nrm.dina.dnakey.logic.metadata.BlastSubjectHsp;
import se.nrm.dina.dnakey.logic.metadata.BlastSubjectMetadata;
import se.nrm.dina.dnakey.portal.ContextMocker;
import se.nrm.dina.dnakey.portal.beans.MessageBean;
import se.nrm.dina.dnakey.portal.logic.SequenceBuilder;

/**
 *
 * @author idali
 */
@RunWith(MockitoJUnitRunner.class)  
public class BlastBeanTest {
  
  private BlastBean instance;
  
  @Mock
  private FileUploadEvent fileUploadEvent;
  
  @Mock
  private UploadedFile uploadFile;
  
  @Mock  
  private SequenceBuilder sequenceBuilder;
  
  @Mock
  private MessageBean msg;
  @Mock
  private Languages languages;
  
  public BlastBeanTest() {
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
   * Test of isIsMax method, of class BlastBean.
   */
  @Test
  public void testIsIsMax() {
    System.out.println("isIsMax"); 
       
    instance = new BlastBean();
    boolean expResult = false;
    boolean result = instance.isIsMax();
    assertEquals(expResult, result); 
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
    instance.onTabChange(event); 
    assertEquals(0, instance.getNumOfTestSeqs());
  }

  /**
   * Test of handleFileUpload method, of class BlastBean.
   */
  @Test
  public void testHandleFileUpload() {
    System.out.println("handleFileUpload");
 
    instance = new BlastBean(sequenceBuilder, msg, languages);
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
//  @Test
  public void testSubmit() {
    System.out.println("submit");
    
    instance = new BlastBean(sequenceBuilder, msg, languages);
    instance.submit(); 
    
  }

  /**
   * Test of removefile method, of class BlastBean.
   */
//  @Test
  public void testRemovefile() {
    System.out.println("removefile");
    UploadedFile file = null;
    BlastBean instance = new BlastBean();
    instance.removefile(file);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of remotBlast method, of class BlastBean.
   */
//  @Test
  public void testRemotBlast() {
    System.out.println("remotBlast");
    BlastMetadata metadata = null;
    BlastBean instance = new BlastBean();
    instance.remotBlast(metadata);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of onRowToggleSingle method, of class BlastBean.
   */
//  @Test
  public void testOnRowToggleSingle() {
    System.out.println("onRowToggleSingle");
    ToggleEvent event = null;
    BlastBean instance = new BlastBean();
    instance.onRowToggleSingle(event);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of clear method, of class BlastBean.
   */
//  @Test
  public void testClear() {
    System.out.println("clear");
    BlastBean instance = new BlastBean();
    instance.clear();
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of newblast method, of class BlastBean.
   */
//  @Test
  public void testNewblast() {
    System.out.println("newblast");
    BlastBean instance = new BlastBean();
    instance.newblast();
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getActiveIndex method, of class BlastBean.
   */
//  @Test
  public void testGetActiveIndex() {
    System.out.println("getActiveIndex");
    BlastBean instance = new BlastBean();
    int expResult = 0;
    int result = instance.getActiveIndex();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setActiveIndex method, of class BlastBean.
   */
//  @Test
  public void testSetActiveIndex() {
    System.out.println("setActiveIndex");
    int activeIndex = 0;
    BlastBean instance = new BlastBean();
    instance.setActiveIndex(activeIndex);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getSequenceList method, of class BlastBean.
   */
//  @Test
  public void testGetSequenceList() {
    System.out.println("getSequenceList");
    BlastBean instance = new BlastBean();
    String expResult = "";
    String result = instance.getSequenceList();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setSequenceList method, of class BlastBean.
   */
//  @Test
  public void testSetSequenceList() {
    System.out.println("setSequenceList");
    String sequenceList = "";
    BlastBean instance = new BlastBean();
    instance.setSequenceList(sequenceList);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getMetadata method, of class BlastBean.
   */
//  @Test
  public void testGetMetadata() {
    System.out.println("getMetadata");
    BlastBean instance = new BlastBean();
    BlastMetadata expResult = null;
    BlastMetadata result = instance.getMetadata();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getDbFullName method, of class BlastBean.
   */
//  @Test
  public void testGetDbFullName() {
    System.out.println("getDbFullName");
    BlastBean instance = new BlastBean();
    String expResult = "";
    String result = instance.getDbFullName();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of openLowMatchList method, of class BlastBean.
   */
//  @Test
  public void testOpenLowMatchList() {
    System.out.println("openLowMatchList");
    BlastMetadata metadata = null;
    BlastBean instance = new BlastBean();
    instance.openLowMatchList(metadata);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getAlignment method, of class BlastBean.
   */
//  @Test
  public void testGetAlignment() {
    System.out.println("getAlignment");
    BlastSubjectMetadata subjectMetadata = null;
    BlastBean instance = new BlastBean();
    instance.getAlignment(subjectMetadata);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getSelectedHsps method, of class BlastBean.
   */
//  @Test
  public void testGetSelectedHsps() {
    System.out.println("getSelectedHsps");
    BlastBean instance = new BlastBean();
    List<BlastSubjectHsp> expResult = null;
    List<BlastSubjectHsp> result = instance.getSelectedHsps();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getSelectedMetadata method, of class BlastBean.
   */
//  @Test
  public void testGetSelectedMetadata() {
    System.out.println("getSelectedMetadata");
    BlastBean instance = new BlastBean();
    BlastSubjectMetadata expResult = null;
    BlastSubjectMetadata result = instance.getSelectedMetadata();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getSelectedSubMetadata method, of class BlastBean.
   */
//  @Test
  public void testGetSelectedSubMetadata() {
    System.out.println("getSelectedSubMetadata");
    BlastBean instance = new BlastBean();
    BlastSubjectMetadata expResult = null;
    BlastSubjectMetadata result = instance.getSelectedSubMetadata();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of databaseChanged method, of class BlastBean.
   */
//  @Test
  public void testDatabaseChanged() {
    System.out.println("databaseChanged");
    AjaxBehaviorEvent event = null;
    BlastBean instance = new BlastBean();
    instance.databaseChanged(event);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getListMetadata method, of class BlastBean.
   */
//  @Test
  public void testGetListMetadata() {
    System.out.println("getListMetadata");
    BlastBean instance = new BlastBean();
    List<BlastMetadata> expResult = null;
    List<BlastMetadata> result = instance.getListMetadata();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getUrlEncode method, of class BlastBean.
   */
//  @Test
  public void testGetUrlEncode() {
    System.out.println("getUrlEncode");
    BlastBean instance = new BlastBean();
    String expResult = "";
    String result = instance.getUrlEncode();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getBlastMetadata method, of class BlastBean.
   */
//  @Test
  public void testGetBlastMetadata() {
    System.out.println("getBlastMetadata");
    BlastBean instance = new BlastBean();
    BlastMetadata expResult = null;
    BlastMetadata result = instance.getBlastMetadata();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getBlastportalUrl method, of class BlastBean.
   */
//  @Test
  public void testGetBlastportalUrl() {
    System.out.println("getBlastportalUrl");
    BlastBean instance = new BlastBean();
    String expResult = "";
    String result = instance.getBlastportalUrl();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getNumOfTestSeqs method, of class BlastBean.
   */
//  @Test
  public void testGetNumOfTestSeqs() {
    System.out.println("getNumOfTestSeqs");
    BlastBean instance = new BlastBean();
    int expResult = 0;
    int result = instance.getNumOfTestSeqs();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setNumOfTestSeqs method, of class BlastBean.
   */
//  @Test
  public void testSetNumOfTestSeqs() {
    System.out.println("setNumOfTestSeqs");
    int numOfTestSeqs = 0;
    BlastBean instance = new BlastBean();
    instance.setNumOfTestSeqs(numOfTestSeqs);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getUploadedFiles method, of class BlastBean.
   */
//  @Test
  public void testGetUploadedFiles() {
    System.out.println("getUploadedFiles");
    BlastBean instance = new BlastBean();
    List<UploadedFile> expResult = null;
    List<UploadedFile> result = instance.getUploadedFiles();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setUploadedFiles method, of class BlastBean.
   */
//  @Test
  public void testSetUploadedFiles() {
    System.out.println("setUploadedFiles");
    List<UploadedFile> uploadedFiles = null;
    BlastBean instance = new BlastBean();
    instance.setUploadedFiles(uploadedFiles);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getDatabase method, of class BlastBean.
   */
//  @Test
  public void testGetDatabase() {
    System.out.println("getDatabase");
    BlastBean instance = new BlastBean();
    String expResult = "";
    String result = instance.getDatabase();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setDatabase method, of class BlastBean.
   */
//  @Test
  public void testSetDatabase() {
    System.out.println("setDatabase");
    String database = "";
    BlastBean instance = new BlastBean();
    instance.setDatabase(database);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getTestSequences method, of class BlastBean.
   */
//  @Test
  public void testGetTestSequences() {
    System.out.println("getTestSequences");
    BlastBean instance = new BlastBean();
    String expResult = "";
    String result = instance.getTestSequences();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setTestSequences method, of class BlastBean.
   */
//  @Test
  public void testSetTestSequences() {
    System.out.println("setTestSequences");
    String testSequences = "";
    BlastBean instance = new BlastBean();
    instance.setTestSequences(testSequences);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getBlastdocument method, of class BlastBean.
   */
//  @Test
  public void testGetBlastdocument() {
    System.out.println("getBlastdocument");
    BlastBean instance = new BlastBean();
    String expResult = "";
    String result = instance.getBlastdocument();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getBlastPlus method, of class BlastBean.
   */
//  @Test
  public void testGetBlastPlus() {
    System.out.println("getBlastPlus");
    BlastBean instance = new BlastBean();
    String expResult = "";
    String result = instance.getBlastPlus();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getGenbankRidUrl method, of class BlastBean.
   */
//  @Test
  public void testGetGenbankRidUrl() {
    System.out.println("getGenbankRidUrl");
    BlastBean instance = new BlastBean();
    String expResult = "";
    String result = instance.getGenbankRidUrl();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of ridByMetadata method, of class BlastBean.
   */
//  @Test
  public void testRidByMetadata() {
    System.out.println("ridByMetadata");
    BlastMetadata metadata = null;
    BlastBean instance = new BlastBean();
    String expResult = "";
    String result = instance.ridByMetadata(metadata);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getTotalSequences method, of class BlastBean.
   */
//  @Test
  public void testGetTotalSequences() {
    System.out.println("getTotalSequences");
    BlastBean instance = new BlastBean();
    int expResult = 0;
    int result = instance.getTotalSequences();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }
  
}
