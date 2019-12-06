package se.nrm.dina.dnakey.logic.vo;

import org.junit.After; 
import org.junit.Before; 
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author idali
 */
public class MorphBankImageTest {
  
  private MorphBankImage instance;
  
  public MorphBankImageTest() {
  } 
  
  @Before
  public void setUp() {
    instance = new MorphBankImage(18, "testLink", "cat1234", "taxon", "body", "2378");
  }
  
  @After
  public void tearDown() { 
    instance = null;
  }

  @Test
  public void testDefaultConstructor() {
    instance = new MorphBankImage();
    assertNotNull(instance);
    assertTrue(instance.getMorphybankImageId() == 0);
  }
  
  /**
   * Test of getMorphybankImageId method, of class MorphBankImage.
   */
  @Test
  public void testGetMorphybankImageId() {
    System.out.println("getMorphybankImageId"); 
  
    int result = instance.getMorphybankImageId();
    assertEquals(18, result); 
  }

  /**
   * Test of getMorphybankImageLink method, of class MorphBankImage.
   */
  @Test
  public void testGetMorphybankImageLink() {
    System.out.println("getMorphybankImageLink"); 
    String expResult = "testLink";
    String result = instance.getMorphybankImageLink();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getCatalogNumber method, of class MorphBankImage.
   */
  @Test
  public void testGetCatalogNumber() {
    System.out.println("getCatalogNumber"); 
    String expResult = "cat1234";
    String result = instance.getCatalogNumber();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getName method, of class MorphBankImage.
   */
  @Test
  public void testGetName() {
    System.out.println("getName"); 
    String expResult = "taxon";
    String result = instance.getName();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getThumb method, of class MorphBankImage.
   */
  @Test
  public void testGetThumb() {
    System.out.println("getThumb"); 
    String expResult = "2378";
    String result = instance.getThumb();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getMbview method, of class MorphBankImage.
   */
  @Test
  public void testGetMbview() {
    System.out.println("getMbview"); 
    String expResult = "body";
    String result = instance.getMbview();
    assertEquals(expResult, result); 
  }

  /**
   * Test of toString method, of class MorphBankImage.
   */
  @Test
  public void testToString() {
    System.out.println("toString"); 
    String expResult = "MorphBankImage : [testLink - 2378]";
    String result = instance.toString();
    assertEquals(expResult, result); 
  }
  
}
