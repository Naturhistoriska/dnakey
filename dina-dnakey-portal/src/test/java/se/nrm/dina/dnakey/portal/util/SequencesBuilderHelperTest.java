package se.nrm.dina.dnakey.portal.util;

import java.util.List;
import org.junit.After; 
import org.junit.Before; 
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author idali
 */
public class SequencesBuilderHelperTest {
  
  private SequencesBuilderHelper instance;
  private FastaFiles fasta; 
  private String testSequences;
  
  
   String dnaSequence = ">gnl|alu|HSU14574 ***ALU WARNING: Human Alu-Sx subfamily consensus sequence.\n"
            + "GGCCGGGCGCGGTGGCTCACGCCTGTAATCCCAGCACTTTGGGAGGCCGAGGCGGGCGGA\n"
            + "TCACCTGAGGTCAGGAGTTCGAGACCAGCCTGGCCAACATGGTGAAACCCCGTCTCTACT\n"
            + "AAAAATACAAAAATTAGCCGGGCGTGGTGGCGCGCGCCTGTAATCCCAGCTACTCGGGAG\n"
            + "GCTGAGGCAGGAGAATCGCTTGAACCCGGGAGGCGGAGGTTGCAGTGAGCCGAGATCGCG\n"
            + "CCACTGCACTCCAGCCTGGGCGACAGAGCGAGACTCCGTCTCAAAAAAAA\n"
            + "\n\n             "
            + ">gnl|alu|HSU14573 ***ALU WARNING: Human Alu-Sq subfamily consensus sequence.\n"
            + "GGCCGGGCGCGGTGGCTCACGCCTGTAATCCCAGCACTTTGGGAGGCCGAGGCGGGTGGA\n"
            + "TCACCTGAGGTCAGGAGTTCGAGACCAGCCTGGCCAACATGGTGAAACCCCGTCTCTACT\n"
            + "AAAAATACAAAAATTAGCCGGGCGTGGTGGCGGGCGCCTGTAATCCCAGCTACTCGGGAG\n"
            + "GCTGAGGCAGGAGAATCGCTTGAACCCGGGAGGCGGAGGTTGCAGTGAGCCGAGATCGCG\n"
            + "CCACTGCACTCCAGCCTGGGCAACAAGAGCGAAACTCCGTCTCAAAAAAAA\n"
            + ">gnl|alu|HSU14572 ***ALU WARNING: Human Alu-Sp subfamily consensus sequence.\n"
            + "GGCCGGGCGCGGTGGCTCACGCCTGTAATCCCAGCACTTTGGGAGGCCGAGGCGGGCGGA\n"
            + "TCACCTGAGGTCGGGAGTTCGAGACCAGCCTGACCAACATGGAGAAACCCCGTCTCTACT\n"
            + "AAAAATACAAAAATTAGCCGGGCGTGGTGGCGCATGCCTGTAATCCCAGCTACTCGGGAG\n"
            + "GCTGAGGCAGGAGAATCGxCTTGAACCCGGGAGGCGGAGGTTGCGGTGAGCCGAGATCGCG\n"
            + "CCATTGCACTCCAGCCTGGGCAACAAGAGCGAAACTCCGTCTCAAAAAAAA";
  
  public SequencesBuilderHelperTest() {
  }
   
  @Before
  public void setUp() {
    instance = SequencesBuilderHelper.getInstance();
    fasta = FastaFiles.getInstance();
    testSequences = fasta.getSequences(3);
  }
  
  @After
  public void tearDown() {
    instance = null;
    fasta = null;
  }

  /**
   * Test of getInstance method, of class SequencesBuilderHelper.
   */
  @Test
  public void testGetInstance() {
    System.out.println("getInstance");
    
    instance = null;
    instance = SequencesBuilderHelper.getInstance();
    assertNotNull(instance);
  }

  /**
   * Test of buildSequenceList method, of class SequencesBuilderHelper.
   */
  @Test
  public void testBuildSequenceList() {
    System.out.println("buildSequenceList");
  
    List<String> result = instance.buildSequenceList(dnaSequence); 
    assertEquals(result.size(), 18);
  }

  /**
   * Test of addSequenceHeader method, of class SequencesBuilderHelper.
   */
  @Test
  public void testAddSequenceHeader() {
    System.out.println("addSequenceHeader");
    String string = "safsafdsadf"; 
     
    String result = instance.addSequenceHeader(string); 
    assertTrue(result.contains(">")); 
  }

  /**
   * Test of buildStringList method, of class SequencesBuilderHelper.
   */
 @Test
  public void testBuildStringList() {
    System.out.println("buildStringList"); 
    List<String> result = instance.buildStringList(dnaSequence); 
    assertNotNull(result); 
  }
  
}
