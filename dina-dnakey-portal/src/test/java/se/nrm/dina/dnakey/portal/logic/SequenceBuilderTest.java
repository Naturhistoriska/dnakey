package se.nrm.dina.dnakey.portal.logic;

import java.util.List;
import java.util.Map;
import org.junit.After; 
import org.junit.Before; 
import org.junit.Test;
import static org.junit.Assert.*;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author idali
 */
public class SequenceBuilderTest {
  
  public SequenceBuilderTest() {
  }
    
  @Before
  public void setUp() {
  }
  
  @After
  public void tearDown() {
  }

  /**
   * Test of isEmptySequences method, of class SequenceBuilder.
   */
//  @Test
  public void testIsEmptySequences() {
    System.out.println("isEmptySequences");
    String sequence = "";
    SequenceBuilder instance = new SequenceBuilder();
    boolean expResult = false;
    boolean result = instance.isEmptySequences(sequence);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of prepareSequenceList method, of class SequenceBuilder.
   */
//  @Test
  public void testPrepareSequenceList() {
    System.out.println("prepareSequenceList");
    String sequence = "";
    SequenceBuilder instance = new SequenceBuilder();
    List<String> expResult = null;
    List<String> result = instance.prepareSequenceList(sequence);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of convertSequencesMapToList method, of class SequenceBuilder.
   */
//  @Test
  public void testConvertSequencesMapToList() {
    System.out.println("convertSequencesMapToList");
    Map<String, List<String>> sequencesMap = null;
    SequenceBuilder instance = new SequenceBuilder();
    List<String> expResult = null;
    List<String> result = instance.convertSequencesMapToList(sequencesMap);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of buildSequencesFromUploadFile method, of class SequenceBuilder.
   */
//  @Test
  public void testBuildSequencesFromUploadFile() {
    System.out.println("buildSequencesFromUploadFile");
    UploadedFile uploadedFile = null;
    SequenceBuilder instance = new SequenceBuilder();
    List<String> expResult = null;
    List<String> result = instance.buildSequencesFromUploadFile(uploadedFile);
    assertEquals(expResult, result); 
  }
  
}
