package se.nrm.dina.dnakey.portal.logic;

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
public class SequenceValidationTest {
  
  private final String dnaSequence = ">gnl|alu|HSU14574 ***ALU WARNING: Human Alu-Sx subfamily consensus sequence.\n"
            + "GGCCGGGCGCGGTGGCTCACGCCTGTAATCCCAGCACTTTGGGAGGCCGAGGCGGGCGGA\n"
            + "TCACCTGAGGTCAGGAGTTCGAGACCAGCCTGGCCAACATGGTGAAACCCCGTCTCTACT\n"
            + "AAAAATACAAAAATTAGCCGGGCGTGGTGGCGCGCGCCTGTAATCCCAGCTACTCGGGAG\n"
            + "GCTGAGGCAGGAGAATCGCTTGAACCCGGGAGGCGGAGGTTGCAGTGAGCCGAGATCGCG\n"
            + "CCACTGCACTCCAGCCTGGGCGACAGAGCGAGACTCCGTCTCAAAAAAAA\n";
  
  private final String invalidDnaSequence = ">gnl|alu|HSU14574 ***ALU WARNING: Human Alu-Sx subfamily consensus sequence.\n"
            + "GGCCGGGCGCGGTGGCTCACGCCTGTAATCCCAGCACTTTGGGAGGCCGAGGCGGGCGGA\n"
            + "TCACCTGAGGTCAGGAGTTCGAGACCAGCCTGGCCAACATGGTGAAACCCCGTCTCTACT\n"
            + "AAAAATACAAAAATTAGCCGGGCGTGGTGGCGxGCGCCTGTAATCCCAGCTACTCGGGAG\n"
            + "GCTGAGGCAGGAGAATCGCTTGAACCCGGGAGGCGGAGGTTGCAGTGAGCCGAGATCGCG\n"
            + "CCACTGCACTCCAGCCTGGGCGACAGAGCGAGACTCCGTCTCAAAAAAAA\n";
  
  private SequenceValidation instance; 
  
  public SequenceValidationTest() {
  }
   
  @Before
  public void setUp() {
    instance =  new SequenceValidation(); 
  }
  
  @After
  public void tearDown() {
    instance = null;
  }

  /**
   * Test of validate method, of class SequenceValidation.
   */
  @Test
  public void testValidateTrue() {
    System.out.println("validate");
    
    List<String> list = new ArrayList();
    list.add(dnaSequence);
      
    boolean expResult = true;
    boolean result = instance.validate(list);
    assertEquals(expResult, result); 
  }
  
  
  /**
   * Test of validate method, of class SequenceValidation.
   */
  @Test
  public void testValidateFalse() {
    System.out.println("validate");
    
    List<String> list = new ArrayList();
    list.add(invalidDnaSequence);
      
    boolean expResult = false;
    boolean result = instance.validate(list);
    assertEquals(expResult, result); 
  }

  /**
   * Test of getErrorMsgs method, of class SequenceValidation.
   */
  @Test
  public void testGetErrorMsgsWithInvalidSequences() {
    System.out.println("getErrorMsgs"); 
    
    List<String> list = new ArrayList();
    list.add(invalidDnaSequence);
       
    instance.validate(list);
    List<String> result = instance.getErrorMsgs(); 
    assertFalse(result.isEmpty());
  }
  
  
  /**
   * Test of getErrorMsgs method, of class SequenceValidation.
   */
  @Test
  public void testGetErrorMsgsWithValidSequences() {
    System.out.println("getErrorMsgs"); 
    
    List<String> list = new ArrayList();
    list.add(dnaSequence);
       
    instance.validate(list);
    List<String> result = instance.getErrorMsgs(); 
    assertTrue(result.isEmpty());
  }

  /**
   * Test of getErrorMsg method, of class SequenceValidation.
   */
  @Test
  public void testGetErrorMsg() {
    System.out.println("getErrorMsg"); 
    String expResult = null;
    String result = instance.getErrorMsg();
    assertEquals(expResult, result); 
  }  
}
