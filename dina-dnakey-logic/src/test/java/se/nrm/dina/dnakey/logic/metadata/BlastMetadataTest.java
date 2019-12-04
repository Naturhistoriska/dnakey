package se.nrm.dina.dnakey.logic.metadata;

import java.util.ArrayList;
import java.util.List;
import org.junit.After; 
import org.junit.Before; 
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author idali
 */
public class BlastMetadataTest {
  
  private BlastMetadata instance;
  private String program;
  private String version;
  private String reference;
  private String database;
  private String query;
  private int queryLen;
  private int statisticDbNumber;
  private int statisticDbLength;
  private List<BlastSubjectMetadata> subjectMetadataList;
  private List<BlastSubjectMetadata> lowMatchSubjectMetadataList; 
  
  public BlastMetadataTest() {
  }
   
  @Before
  public void setUp() {
    program = "BLASTN";
    version = "2.2.30+";
    reference = "reference";
    database = "nrm";
    query = "Seq 1";
    queryLen = 5;
    statisticDbNumber = 1000;
    statisticDbLength = 1000;
    subjectMetadataList = new ArrayList();
    lowMatchSubjectMetadataList = new ArrayList<>();
    
    instance = new BlastMetadata(program, version, reference, database, query, queryLen, 
            statisticDbNumber, statisticDbLength, subjectMetadataList, lowMatchSubjectMetadataList, true, true);
  }
  
  @After
  public void tearDown() {
  }
  
  public void testDefaultConstructor() {
    instance = new BlastMetadata();
    assertNotNull(instance);
  }

  /**
   * Test of getProgram method, of class BlastMetadata.
   */
  @Test
  public void testGetProgram() {
    System.out.println("getProgram");
  
    String result = instance.getProgram();
    assertEquals(program, result); 
  }

  /**
   * Test of getDatabase method, of class BlastMetadata.
   */
  @Test
  public void testGetDatabase() {
    System.out.println("getDatabase"); 
    String result = instance.getDatabase();
    assertEquals(database, result); 
  }

  /**
   * Test of getQuery method, of class BlastMetadata.
   */
  @Test
  public void testGetQuery() {
    System.out.println("getQuery"); 
    String result = instance.getQuery();
    assertEquals(query, result); 
  }

  /**
   * Test of getQueryLen method, of class BlastMetadata.
   */
  @Test
  public void testGetQueryLen() {
    System.out.println("getQueryLen"); 
    int result = instance.getQueryLen();
    assertEquals(queryLen, result); 
  }

  /**
   * Test of getReference method, of class BlastMetadata.
   */
  @Test
  public void testGetReference() {
    System.out.println("getReference"); 
    String result = instance.getReference();
    assertEquals(reference, result); 
  }

  /**
   * Test of getStatisticDbLength method, of class BlastMetadata.
   */
  @Test
  public void testGetStatisticDbLength() {
    System.out.println("getStatisticDbLength"); 
    int result = instance.getStatisticDbLength();
    assertEquals(statisticDbLength, result); 
  }

  /**
   * Test of getStatisticDbNumber method, of class BlastMetadata.
   */
  @Test
  public void testGetStatisticDbNumber() {
    System.out.println("getStatisticDbNumber"); 
    int result = instance.getStatisticDbNumber();
    assertEquals(statisticDbNumber, result); 
  }

  /**
   * Test of getSubjectMetadataList method, of class BlastMetadata.
   */
  @Test
  public void testGetSubjectMetadataList() {
    System.out.println("getSubjectMetadataList"); 
    List<BlastSubjectMetadata> result = instance.getSubjectMetadataList();
    assertNotNull(result); 
  }

  /**
   * Test of getVersion method, of class BlastMetadata.
   */
  @Test
  public void testGetVersion() {
    System.out.println("getVersion");  
    String result = instance.getVersion();
    assertEquals(version, result); 
  }

  /**
   * Test of setSequence method, of class BlastMetadata.
   */
  @Test
  public void testSetSequence() {
    System.out.println("setSequence");  
    String sequence = "asfasdf";
    instance.setSequence(sequence); 
  }

  /**
   * Test of getSequence method, of class BlastMetadata.
   */
  @Test
  public void testGetSequence() {
    System.out.println("getSequence"); 
    
    String sequence = "asfasdf";
    instance.setSequence(sequence); 
    String result = instance.getSequence();
    assertEquals(sequence, result); 
  }

  /**
   * Test of getLowMatchsubjectMetadataList method, of class BlastMetadata.
   */
  @Test
  public void testGetLowMatchsubjectMetadataList() {
    System.out.println("getLowMatchsubjectMetadataList"); 
    List<BlastSubjectMetadata> result = instance.getLowMatchsubjectMetadataList();
    assertNotNull(result); 
  }

  /**
   * Test of isOpenLowMatch method, of class BlastMetadata.
   */
  @Test
  public void testIsOpenLowMatch() {
    System.out.println("isOpenLowMatch"); 
    boolean result = instance.isOpenLowMatch();
    assertTrue(result); 
  }

  /**
   * Test of setOpenLowMatch method, of class BlastMetadata.
   */
  @Test
  public void testSetOpenLowMatch() {
    System.out.println("setOpenLowMatch"); 
    instance.setOpenLowMatch(false);  
    assertFalse(instance.isOpenLowMatch()); 
  }

  /**
   * Test of isHasHighMatach method, of class BlastMetadata.
   */
  @Test
  public void testIsHasHighMatach() {
    System.out.println("isHasHighMatach"); 
    boolean expResult = false;
    boolean result = instance.isHasHighMatach();
    assertEquals(expResult, result); 
  }

  /**
   * Test of isHasLowMatch method, of class BlastMetadata.
   */
  @Test
  public void testIsHasLowMatch() {
    System.out.println("isHasLowMatch"); 
    boolean expResult = false;
    boolean result = instance.isHasLowMatch();
    assertEquals(expResult, result); 
  }

  /**
   * Test of isHasHit method, of class BlastMetadata.
   */
  @Test
  public void testIsHasHit() {
    System.out.println("isHasHit"); 
    boolean expResult = true;
    boolean result = instance.isHasHit();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getGenbankUrl method, of class BlastMetadata.
   */
  @Test
  public void testGetGenbankUrl() {
    System.out.println("getGenbankUrl");  
    String result = instance.getGenbankUrl();
    assertNotNull(result); 
  } 
}
