package se.nrm.dina.dnakey.logic.metadata;

import java.util.ArrayList;
import java.util.List;
import org.junit.After; 
import org.junit.Before; 
import org.junit.Test;
import static org.junit.Assert.*;
import se.nrm.dina.dnakey.logic.vo.NrmData;

/**
 *
 * @author idali
 */
public class BlastSubjectMetadataTest {
  
  private BlastSubjectMetadata instance;
  private List<BlastSubjectHsp> list;
  
  public BlastSubjectMetadataTest() {
  }
   
  @Before
  public void setUp() {
    BlastSubjectHsp hsp = new BlastSubjectHsp(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 20, 80, "hspQseq", "hspHseq", "hspMidline", 0, 0);
    list = new ArrayList<>();
     
    list.add(hsp);
    instance = new BlastSubjectMetadata(2, "genbankId","genbankAccession", "boldId",
          "targetMarker", "58_N_12_E", "catalogNumber", "scientificName", 0, list, true);
  }
  
  @After
  public void tearDown() {
    instance = null;
  }

  /**
   * Test of getBoldId method, of class BlastSubjectMetadata.
   */
  @Test
  public void testGetBoldId() {
    System.out.println("getBoldId"); 
    String expResult = "boldId";
    String result = instance.getBoldId();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getBoldIdWithType method, of class BlastSubjectMetadata.
   */
  @Test
  public void testGetBoldIdWithType() {
    System.out.println("getBoldIdWithType"); 
    
    instance = new BlastSubjectMetadata(2, "genbankId","genbankAccession", "test.boldId",
          "targetMarker", "58_N_12_E", "catalogNumber", "scientificName", 0, null, true);
    String expResult = "test";
    String result = instance.getBoldIdWithType();
    assertEquals(expResult, result); 
  }
  
  /**
   * Test of getBoldIdWithType method, of class BlastSubjectMetadata.
   */
  @Test
  public void testGetBoldIdWithType2() {
    System.out.println("getBoldIdWithType"); 
    
    instance = new BlastSubjectMetadata(2, "genbankId","genbankAccession", "boldId",
          "targetMarker", "58_N_12_E", "catalogNumber", "scientificName", 0, null, true);
    String expResult = "boldId";
    String result = instance.getBoldIdWithType();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getCoordinates method, of class BlastSubjectMetadata.
   */
  @Test
  public void testGetCoordinates() {
    System.out.println("getCoordinates"); 
    String expResult = "58_N_12_E";
    String result = instance.getCoordinates();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getFormattedCoordinates method, of class BlastSubjectMetadata.
   */
  @Test
  public void testGetFormattedCoordinates() {
    System.out.println("getFormattedCoordinates"); 
    String expResult = "58 N 12 E";
    String result = instance.getFormattedCoordinates();
    assertEquals(expResult, result); 
  } 
  
  /**
   * Test of getFormattedCoordinates method, of class BlastSubjectMetadata.
   */
  @Test
  public void testGetFormattedCoordinatesWithNullValue() {
    System.out.println("getFormattedCoordinates"); 
    
    instance = new BlastSubjectMetadata(2, "genbankId","genbankAccession", "test.boldId",
          "targetMarker", "", "catalogNumber", "scientificName", 0, null, true); 
    String result = instance.getFormattedCoordinates();
    assertEquals(null, result); 
  }
  
  /**
   * Test of isHasCoordinates method, of class BlastSubjectMetadata.
   */
  @Test
  public void testIsHasCoordinates() {
    System.out.println("isHasCoordinates"); 
    boolean expResult = true;
    boolean result = instance.isHasCoordinates();
    assertEquals(expResult, result); 
  }
  
  /**
   * Test of isHasCoordinates method, of class BlastSubjectMetadata.
   */
  @Test
  public void testIsHasCoordinatesFalse() {
    System.out.println("isHasCoordinates"); 
    instance = new BlastSubjectMetadata(2, "genbankId","genbankAccession", "boldId",
          "targetMarker", " ", "catalogNumber", "scientificName", 0, null, true);
    boolean expResult = false;
    boolean result = instance.isHasCoordinates();
    assertEquals(expResult, result); 
  }

  /**
   * Test of isHasImages method, of class BlastSubjectMetadata.
   */
  @Test
  public void testIsHasImages() {
    System.out.println("isHasImages"); 
    boolean expResult = false;
    boolean result = instance.isHasImages();
    assertEquals(expResult, result); 
  }

  
  /**
   * Test of isHasImages method, of class BlastSubjectMetadata.
   */
  @Test
  public void testIsHasImagesTrue() {
    System.out.println("isHasImages"); 
    
    NrmData data = new NrmData(null, null, null, null, null, null, null, null, true, null, null, null, null);
    instance.setNrmData(data);
    boolean expResult = true;
    boolean result = instance.isHasImages();
    assertEquals(expResult, result); 
  }
  
    /**
   * Test of isHasImages method, of class BlastSubjectMetadata.
   */
  @Test
  public void testIsHasImagesFalse() {
    System.out.println("isHasImages"); 
    
    NrmData data = new NrmData(null, null, null, null, null, null, null, null, false, null, null, null, null);
    instance.setNrmData(data);
    boolean expResult = false;
    boolean result = instance.isHasImages();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getGenbankAccession method, of class BlastSubjectMetadata.
   */
  @Test
  public void testGetGenbankAccession() {
    System.out.println("getGenbankAccession"); 
    String expResult = "genbankAccession";
    String result = instance.getGenbankAccession();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getGenbankId method, of class BlastSubjectMetadata.
   */
  @Test
  public void testGetGenbankId() {
    System.out.println("getGenbankId"); 
    String expResult = "genbankId";
    String result = instance.getGenbankId();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getHitNumber method, of class BlastSubjectMetadata.
   */
  @Test
  public void testGetHitNumber() {
    System.out.println("getHitNumber"); 
    int expResult = 2;
    int result = instance.getHitNumber();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getScientificName method, of class BlastSubjectMetadata.
   */
  @Test
  public void testGetScientificName() {
    System.out.println("getScientificName"); 
    String expResult = "scientificName";
    String result = instance.getScientificName();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getSubjectHspList method, of class BlastSubjectMetadata.
   */
  @Test
  public void testGetSubjectHspList() {
    System.out.println("getSubjectHspList");  
    List<BlastSubjectHsp> result = instance.getSubjectHspList();
    assertNotNull(result);
    assertEquals(1, result.size()); 
  }

  /**
   * Test of getTargetMarker method, of class BlastSubjectMetadata.
   */
  @Test
  public void testGetTargetMarker() {
    System.out.println("getTargetMarker"); 
    String expResult = "targetMarker";
    String result = instance.getTargetMarker();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getHitLen method, of class BlastSubjectMetadata.
   */
  @Test
  public void testGetHitLen() {
    System.out.println("getHitLen"); 
    int expResult = 0;
    int result = instance.getHitLen();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getCatalogNumber method, of class BlastSubjectMetadata.
   */
  @Test
  public void testGetCatalogNumber() {
    System.out.println("getCatalogNumber"); 
    String expResult = "catalogNumber";
    String result = instance.getCatalogNumber();
    assertEquals(expResult, result); 
  }

  /**
   * Test of isNrm method, of class BlastSubjectMetadata.
   */
  @Test
  public void testIsNrm() {
    System.out.println("isNrm"); 
    boolean expResult = true;
    boolean result = instance.isNrm();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getNrmData method, of class BlastSubjectMetadata.
   */
  @Test
  public void testGetNrmData() {
    System.out.println("getNrmData"); 
    NrmData expResult = null;
    NrmData result = instance.getNrmData();
    assertEquals(expResult, result); 
  }

  /**
   * Test of setNrmData method, of class BlastSubjectMetadata.
   */
  @Test
  public void testSetNrmData() {
    System.out.println("setNrmData"); 
    NrmData data = new NrmData(null, null, null, null, null, null, null, null, true, null, null, null, null);
    instance.setNrmData(data); 
    assertNotNull(instance.getNrmData());
  }

  /**
   * Test of getTextColor method, of class BlastSubjectMetadata.
   */
  @Test
  public void testGetTextColor() {
    System.out.println("getTextColor"); 
    String expResult = "graytext";
    String result = instance.getTextColor();
    assertEquals(expResult, result); 
  }
  
  
  /**
   * Test of getTextColor method, of class BlastSubjectMetadata.
   */
  @Test
  public void testGetTextColorBlack() {
    System.out.println("getTextColor"); 
    
    BlastSubjectHsp hsp = new BlastSubjectHsp(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 20, 99, "hspQseq", "hspHseq", "hspMidline", 0, 0);
    list.add(hsp);
    String expResult = "blacktext";
    String result = instance.getTextColor();
    assertEquals(expResult, result); 
  }


  /**
   * Test of getStyle method, of class BlastSubjectMetadata.
   */
  @Test
  public void testGetStyle() {
    System.out.println("getStyle");  
    String result = instance.getStyle();
    assertEquals(null, result); 
  }
   
  @Test
  public void testGetStyleNotNull() {
    System.out.println("getStyle");  
    
    BlastSubjectHsp hsp = new BlastSubjectHsp(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 20, 99, "hspQseq", "hspHseq", "hspMidline", 0, 0);
    list.add(hsp);
    String result = instance.getStyle();
    assertEquals("blacktext", result); 
  }
  
}
