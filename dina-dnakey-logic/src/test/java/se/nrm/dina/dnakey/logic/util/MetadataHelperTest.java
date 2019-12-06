package se.nrm.dina.dnakey.logic.util;

import java.util.ArrayList;
import java.util.List;
import org.junit.After; 
import org.junit.Before; 
import org.junit.Test;
import static org.junit.Assert.*;
import se.nrm.dina.dnakey.logic.metadata.AlignSequence;
import se.nrm.dina.dnakey.logic.metadata.BlastSubjectHsp;

/**
 *
 * @author idali
 */
public class MetadataHelperTest {
  
  private MetadataHelper instance;
  
  public MetadataHelperTest() {
  }
   
  @Before
  public void setUp() {
    instance = MetadataHelper.getInstance();
  }
  
  @After
  public void tearDown() {
    instance = null;
  }

  /**
   * Test of getInstance method, of class MetadataHelper.
   */
  @Test
  public void testGetInstance() {
    System.out.println("getInstance"); 
    assertNotNull(instance); 
  }

  /**
   * Test of buildNoCountAlignment method, of class MetadataHelper.
   */
  @Test
  public void testBuildNoCountAlignment() {
    System.out.println("buildNoCountAlignment");
    List<AlignSequence> alignSequences = new ArrayList<>();
    BlastSubjectHsp subject = new BlastSubjectHsp(1.0, 2.0, 0.1, 18, 68, 78, 128, 89, 12,
          1, 3, 99l, "hspQseq",   "hspHseq",  "hspMidline", 8, 6); 
    List<AlignSequence> result = instance.buildNoCountAlignment(alignSequences, subject);
    assertEquals(3, result.size()); 
  }

  /**
   * Test of buildAlignment method, of class MetadataHelper.
   */
  @Test
  public void testBuildAlignmentCountIsZero() {
    System.out.println("buildAlignment");
    
    BlastSubjectHsp subject = new BlastSubjectHsp(1.0, 2.0, 0.1, 18, 68, 78, 128, 89, 12,
          1, 3, 99l, "hspQseq",   "hspHseq",  "hspMidline", 8, 6); 
    int count = 0;
    List<AlignSequence> alignSequences = new ArrayList<>();
    int remainder = 0;   
    List<AlignSequence> result = instance.buildAlignment(count, alignSequences, remainder, subject);
    assertTrue(result.isEmpty()); 
  }
  
  @Test
  public void testBuildAlignmentReminderIsZero() {
    System.out.println("buildAlignment");
    
    BlastSubjectHsp subject = new BlastSubjectHsp(1.0, 2.0, 0.1, 18, 68, 78, 128, 89, 12,
          1, 3, 99l, "hspQseq",   "hspHseq",  "hspMidline", 8, 6); 
    int count = 1;
    List<AlignSequence> alignSequences = new ArrayList<>();
    int remainder = 0;   
    List<AlignSequence> result = instance.buildAlignment(count, alignSequences, remainder, subject); 
    assertFalse(result.isEmpty()); 
    assertEquals(4, result.size());
  }
  
  @Test
  public void testBuildAlignment() {
    System.out.println("buildAlignment");
    
    BlastSubjectHsp subject = new BlastSubjectHsp(1.0, 2.0, 0.1, 18, 68, 78, 128, 89, 12,
          1, 3, 99l, "hspQs-eq",   "hspHseq",  "hspMidline", 8, 6); 
    int count = 1;
    List<AlignSequence> alignSequences = new ArrayList<>();
    int remainder = 2;   
    List<AlignSequence> result = instance.buildAlignment(count, alignSequences, remainder, subject); 
    assertFalse(result.isEmpty()); 
    assertEquals(7, result.size());
  }
  
}
