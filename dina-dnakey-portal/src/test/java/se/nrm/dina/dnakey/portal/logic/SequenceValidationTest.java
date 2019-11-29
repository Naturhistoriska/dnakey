package se.nrm.dina.dnakey.portal.logic;

import java.util.List;
import org.junit.After; 
import org.junit.Before; 
import org.junit.Test;
import static org.junit.Assert.*;
import se.nrm.dina.dnakey.portal.util.FastaFiles;

/**
 *
 * @author idali
 */
public class SequenceValidationTest {
  
  private SequenceValidation instance;
  private FastaFiles fasta;
  
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
//  @Test
  public void testValidate() {
    System.out.println("validate");
    
    fasta = FastaFiles.getInstance(); 
    List<String> seqs = null; 
    boolean expResult = false;
    boolean result = instance.validate(seqs);
    assertEquals(expResult, result); 
  }

  /**
   * Test of getErrorMsgs method, of class SequenceValidation.
   */
//  @Test
  public void testGetErrorMsgs() {
    System.out.println("getErrorMsgs"); 
    
    List<String> expResult = null;
    List<String> result = instance.getErrorMsgs();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getErrorMsg method, of class SequenceValidation.
   */
//  @Test
  public void testGetErrorMsg() {
    System.out.println("getErrorMsg");
    SequenceValidation instance = new SequenceValidation();
    String expResult = "";
    String result = instance.getErrorMsg();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of main method, of class SequenceValidation.
   */
//  @Test
  public void testMain() {
    System.out.println("main");
    String[] args = null;
    SequenceValidation.main(args);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }
  
}
