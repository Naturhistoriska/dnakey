package se.nrm.dina.dnakey.logic.metadata;

import java.util.List;
import org.junit.After; 
import org.junit.Before; 
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author idali
 */
public class BlastSubjectHspTest {
  
  private BlastSubjectHsp instance;
  
  public BlastSubjectHspTest() {
  }
   
  @Before
  public void setUp() {
    instance = new BlastSubjectHsp(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 20, 80, "hspQseq", "hspHseq", "hspMidline", 0, 0);
  }
  
  @After
  public void tearDown() {
    instance = null;
  }

//  /**
//   * Test of getNUMBER_FORMAT_ERROR method, of class BlastSubjectHsp.
//   */
//  @Test
//  public void testGetNUMBER_FORMAT_ERROR() {
//    System.out.println("getNUMBER_FORMAT_ERROR");
//    String expResult = "";
//    String result = BlastSubjectHsp.getNUMBER_FORMAT_ERROR();
//    assertEquals(expResult, result); 
//  }

  /**
   * Test of getHspAlignLen method, of class BlastSubjectHsp.
   */
  @Test
  public void testGetHspAlignLen() {
    System.out.println("getHspAlignLen"); 
    int expResult = 20;
    int result = instance.getHspAlignLen();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getHspBitScore method, of class BlastSubjectHsp.
   */
  @Test
  public void testGetHspBitScore() {
    System.out.println("getHspBitScore"); 
    double expResult = 0.0;
    double result = instance.getHspBitScore();
    assertEquals(expResult, result, 0.0); 
  }

  /**
   * Test of getHspEvalue method, of class BlastSubjectHsp.
   */
  @Test
  public void testGetHspEvalue() {
    System.out.println("getHspEvalue"); 
    double expResult = 0.0;
    double result = instance.getHspEvalue();
    assertEquals(expResult, result, 0.0); 
  }

  /**
   * Test of getHspGaps method, of class BlastSubjectHsp.
   */
  @Test
  public void testGetHspGaps() {
    System.out.println("getHspGaps"); 
    int expResult = 0;
    int result = instance.getHspGaps();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getHspHitFrame method, of class BlastSubjectHsp.
   */
  @Test
  public void testGetHspHitFrame() {
    System.out.println("getHspHitFrame"); 
    int expResult = 0;
    int result = instance.getHspHitFrame();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getHspHitFrom method, of class BlastSubjectHsp.
   */
  @Test
  public void testGetHspHitFrom() {
    System.out.println("getHspHitFrom"); 
    int expResult = 0;
    int result = instance.getHspHitFrom();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getHspHitTo method, of class BlastSubjectHsp.
   */
  @Test
  public void testGetHspHitTo() {
    System.out.println("getHspHitTo"); 
    int expResult = 0;
    int result = instance.getHspHitTo();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getHspHseq method, of class BlastSubjectHsp.
   */
  @Test
  public void testGetHspHseq() {
    System.out.println("getHspHseq"); 
    String expResult = "hspHseq";
    String result = instance.getHspHseq();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getHspIdentity method, of class BlastSubjectHsp.
   */
  @Test
  public void testGetHspIdentity() {
    System.out.println("getHspIdentity"); 
    int expResult = 0;
    int result = instance.getHspIdentity();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getHspMidline method, of class BlastSubjectHsp.
   */
  @Test
  public void testGetHspMidline() {
    System.out.println("getHspMidline"); 
    String expResult = "hspMidline";
    String result = instance.getHspMidline();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getHspPositive method, of class BlastSubjectHsp.
   */
  @Test
  public void testGetHspPositive() {
    System.out.println("getHspPositive"); 
    int expResult = 0;
    int result = instance.getHspPositive();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getHspQseq method, of class BlastSubjectHsp.
   */
  @Test
  public void testGetHspQseq() {
    System.out.println("getHspQseq"); 
    String expResult = "hspQseq";
    String result = instance.getHspQseq();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getHspQueryFrame method, of class BlastSubjectHsp.
   */
  @Test
  public void testGetHspQueryFrame() {
    System.out.println("getHspQueryFrame"); 
    int expResult = 0;
    int result = instance.getHspQueryFrame();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getHspQueryFrom method, of class BlastSubjectHsp.
   */
  @Test
  public void testGetHspQueryFrom() {
    System.out.println("getHspQueryFrom"); 
    int expResult = 0;
    int result = instance.getHspQueryFrom();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getHspQueryTo method, of class BlastSubjectHsp.
   */
  @Test
  public void testGetHspQueryTo() {
    System.out.println("getHspQueryTo"); 
    int expResult = 0;
    int result = instance.getHspQueryTo();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getHspScore method, of class BlastSubjectHsp.
   */
  @Test
  public void testGetHspScore() {
    System.out.println("getHspScore"); 
    double expResult = 0.0;
    double result = instance.getHspScore();
    assertEquals(expResult, result, 0.0); 
  }

  
  /**
   * Test of getTextColor method, of class BlastSubjectHsp.
   */
  @Test
  public void testGetTextColorGray() {
    System.out.println("getTextColor");  
    String expResult = "graytext";
    String result = instance.getTextColor();
    assertEquals(expResult, result); 
  }
  
  /**
   * Test of getTextColor method, of class BlastSubjectHsp.
   */
  @Test
  public void testGetTextColorBlack() {
    System.out.println("getTextColor"); 
    instance = new BlastSubjectHsp(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 99, "hspQseq", "hspHseq", "hspMidline", 0, 0);
    String expResult = "blacktext";
    String result = instance.getTextColor();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getPercentage method, of class BlastSubjectHsp.
   */
  @Test
  public void testGetPercentage() {
    System.out.println("getPercentage"); 
    Long expResult = 80l;
    Long result = instance.getPercentage();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getIdentitiesPercentage method, of class BlastSubjectHsp.
   */
  @Test
  public void testGetIdentitiesPercentage() {
    System.out.println("getIdentitiesPercentage"); 
  
    String result = instance.getIdentitiesPercentage(); 
    assertEquals("80%", result); 
  }

  /**
   * Test of getGapsPercentage method, of class BlastSubjectHsp.
   */
  @Test
  public void testGetGapsPercentage() {
    System.out.println("getGapsPercentage");
    instance = new BlastSubjectHsp(0, 0, 0, 0, 0, 0, 0, 0, 0, 20, 310, 90, "hspQseq", "hspHseq", "hspMidline", 0, 0);
 
    String result = instance.getGapsPercentage(); 
    assertEquals("6%", result); 
  }

  /**
   * Test of getHspStrand method, of class BlastSubjectHsp.
   */
  @Test
  public void testGetHspStrandPlusPlus() {
    System.out.println("getHspStrand");  
    String result = instance.getHspStrand(); 
    assertEquals("Strand = Plus/Plus", result); 
  }

  /**
   * Test of getHspStrand method, of class BlastSubjectHsp.
   */
  @Test
  public void testGetHspStrandMinusMinus() {
    System.out.println("getHspStrand");  
    
    instance = new BlastSubjectHsp(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 99, "hspQseq", "hspHseq", "hspMidline", -2, -3);
    String result = instance.getHspStrand();
    System.out.println("result..." +result);
    assertEquals("Strand = Minus/Minus", result); 
  }

  
  /**
   * Test of getSequencesAlignment method, of class BlastSubjectHsp.
   */
  @Test
  public void testGetSequencesAlignmentWithCount() {
    System.out.println("getSequencesAlignment");  
    instance = new BlastSubjectHsp(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 310, 90, "hspQseq", "hspHseq", "hspMidline", 0, 0);
    List<AlignSequence> result = instance.getSequencesAlignment();
   
    assertNotNull(result); 
    assertEquals(23, result.size()); 
  }
  
  /**
   * Test of getSequencesAlignment method, of class BlastSubjectHsp.
   */
  @Test
  public void testGetSequencesAlignment() {
    System.out.println("getSequencesAlignment");  
    instance = new BlastSubjectHsp(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 20, 99, "hspQseq", "hspHseq", "hspMidline", 0, 0);
    List<AlignSequence> result = instance.getSequencesAlignment();
   
    assertNotNull(result); 
    assertEquals(3, result.size());
  }

  /**
   * Test of compareTo method, of class BlastSubjectHsp.
   */
  @Test
  public void testCompareTo() {
    System.out.println("compareTo");
    BlastSubjectHsp o1 = new BlastSubjectHsp(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 70, "hspQseq", "hspHseq", "hspMidline", 0, 0);
    int expResult = 1;
    int result = instance.compareTo(o1); 
    assertEquals(expResult, result); 
  }
  
}
