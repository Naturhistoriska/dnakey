package se.nrm.dina.dnakey.logic.metadata;

import org.junit.After; 
import org.junit.Before; 
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author idali
 */
public class AlignSequenceTest {
  
  private AlignSequence instance;
  private String start;
  private String end;
  private String type;
  private String sequence;
   
  public AlignSequenceTest() {
  }
   
  @Before
  public void setUp() {
    start = "afasifasf";
    end = "asfsafd";
    sequence = "AAADDDBAAD";
  }
  
  @After
  public void tearDown() {
  }
  
  @Test
  public void testDefaultConstructor() {
    instance = new AlignSequence();
    assertNotNull(instance);
  }

  /**
   * Test of getEnd method, of class AlignSequence.
   */
  @Test
  public void testGetEnd() {
    System.out.println("getEnd");
     
    type = "q";
    instance = new AlignSequence(start, end, sequence, type);
     
    String result = instance.getEnd();
    assertEquals(end, result);
  }

  /**
   * Test of getSeq method, of class AlignSequence.
   */
  @Test
  public void testGetSeq() {
    System.out.println("getSeq");  
    
    type = "q";
    instance = new AlignSequence(start, end, sequence, type);
    String result = instance.getSeq();
    assertEquals(sequence, result); 
  }

  /**
   * Test of getStart method, of class AlignSequence.
   */
  @Test
  public void testGetStartWithQtype() {
    System.out.println("getStart");
    
    type = "q";
    instance = new AlignSequence(start, end, sequence, type);
    String expResult = "Query  " + start;
    String result = instance.getStart();
    assertEquals(expResult, result); 
  }
  
  /**
   * Test of getStart method, of class AlignSequence.
   */
  @Test
  public void testGetStartWithStype() {
    System.out.println("getStart");
    
    type = "s";
    instance = new AlignSequence(start, end, sequence, type);
    String expResult = "Subjc  " + start;
    String result = instance.getStart();
    assertEquals(expResult, result); 
  }
  
    
  /**
   * Test of getStart method, of class AlignSequence.
   */
  @Test
  public void testGetStartWithNoType() {
    System.out.println("getStart");
    
    type = "";
    instance = new AlignSequence(start, end, sequence, type);
    String expResult = "";
    String result = instance.getStart();
    assertEquals(expResult, result); 
  }
}
