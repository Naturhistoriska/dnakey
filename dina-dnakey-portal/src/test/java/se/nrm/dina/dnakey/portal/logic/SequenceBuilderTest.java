package se.nrm.dina.dnakey.portal.logic;

import java.io.IOException;
import java.io.InputStream; 
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream; 
import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when; 
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.primefaces.model.UploadedFile; 

/**
 *
 * @author idali
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({IOUtils.class})
@PowerMockIgnore({"javax.management.*", "org.apache.http.conn.ssl.*", "com.amazonaws.http.conn.ssl.*", "javax.net.ssl.*"})
public class SequenceBuilderTest {

  private SequenceBuilder instance;

  String dnaSequence = ">gnl|alu|HSU14574 ***ALU WARNING: Human Alu-Sx subfamily consensus sequence.\n"
          + "GGCCGGGCGCGGTGGCTCACGCCTGTAATCCCAGCACTTTGGGAGGCCGAGGCGGGCGGA\n"
          + "TCACCTGAGGTCAGGAGTTCGAGACCAGCCTGGCCAACATGGTGAAACCCCGTCTCTACT\n"
          + "AAAAATACAAAAATTAGCCGGGCGTGGTGGCGCGCGCCTGTAATCCCAGCTACTCGGGAG\n"
          + "GCTGAGGCAGGAGAATCGCTTGAACCCGGGAGGCGGAGGTTGCAGTGAGCCGAGATCGCG\n"
          + "CCACTGCACTCCAGCCTGGGCGACAGAGCGAGACTCCGTCTCAAAAAAAA\n"
          + "\n\n             " 
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
  
  String seq = "GGCCGGGCGCGGTGGCTCACGCCTGTAATCCCAGCACTTTGGGAGGCCGAGGCGGGCGGA\n"
          + "TCACCTGAGGTCGGGAGTTCGAGACCAGCCTGACCAACATGGAGAAACCCCGTCTCTACT\n"
          + "AAAAATACAAAAATTAGCCGGGCGTGGTGGCGCATGCCTGTAATCCCAGCTACTCGGGAG\n"
          + "GCTGAGGCAGGAGAATCGxCTTGAACCCGGGAGGCGGAGGTTGCGGTGAGCCGAGATCGCG\n"
          + "CCATTGCACTCCAGCCTGGGCAACAAGAGCGAAACTCCGTCTCAAAAAAAA";
   
  public SequenceBuilderTest() {
  }

  @Before
  public void setUp() {
    instance = new SequenceBuilder();
  }

  @After
  public void tearDown() {
    instance = null;
  }

  /**
   * Test of isEmptySequences method, of class SequenceBuilder.
   */
  @Test
  public void testIsEmptySequences() {
    System.out.println("isEmptySequences");
    String sequence = "";
    boolean expResult = true;
    boolean result = instance.isEmptySequences(sequence);
    assertEquals(expResult, result);
  }

  /**
   * Test of prepareSequenceList method, of class SequenceBuilder.
   */
  @Test
  public void testPrepareSequenceList() {
    System.out.println("prepareSequenceList");  
    List<String> result = instance.prepareSequenceList(dnaSequence);  
    assertEquals(3, result.size());
  }
  
  /**
   * Test of prepareSequenceList method, of class SequenceBuilder.
   */
  @Test
  public void testPrepareSequenceListWithOneSequence() {
    System.out.println("prepareSequenceList");  
    List<String> result = instance.prepareSequenceList(seq);  
    assertEquals(5, result.size());
  }
  
  /**
   * Test of prepareSequenceList method, of class SequenceBuilder.
   */
  @Test
  public void testPrepareSequenceListWithEmtpySequence() {
    System.out.println("prepareSequenceList");  
    List<String> result = instance.prepareSequenceList("");  
    assertEquals(result, null);
  }

  /**
   * Test of prepareSequenceList method, of class SequenceBuilder.
   */
  @Test
  public void testPrepareSequenceListWithNullSequence() {
    System.out.println("prepareSequenceList");  
    List<String> result = instance.prepareSequenceList(null);  
    assertEquals(result, null);
  }

  /**
   * Test of convertSequencesMapToList method, of class SequenceBuilder.
   */
  @Test
  public void testConvertSequencesMapToList() {
    System.out.println("convertSequencesMapToList");
    Map<String, List<String>> sequencesMap = new HashMap(); 
    List<String> list = new ArrayList();
    list.add("asfasdfasf");
    list.add("sadfsadf");
    
    sequencesMap.put("key1", list);
    sequencesMap.put("key2", list); 
    List<String> result = instance.convertSequencesMapToList(sequencesMap);
    assertEquals(4, result.size()); 
  }
  
  
  /**
   * Test of convertSequencesMapToList method, of class SequenceBuilder.
   */
  @Test
  public void testConvertSequencesMapToListWithOver100Sequence() {
    System.out.println("convertSequencesMapToList");
    Map<String, List<String>> sequencesMap = new HashMap(); 
    List<String> list = new ArrayList();
    IntStream.range(0, 52)
            .forEach(i -> {
              list.add("afasdf");
            });
    sequencesMap.put("key1", list);
    sequencesMap.put("key2", list);  
    List<String> result = instance.convertSequencesMapToList(sequencesMap);
    assertEquals(99, result.size()); 
  }
  
  
  /**
   * Test of convertSequencesMapToList method, of class SequenceBuilder.
   */
  @Test
  public void testConvertSequencesMapToListWithNullMap() {
    System.out.println("convertSequencesMapToList");
    Map<String, List<String>> sequencesMap = null; 
    List<String> expResult = null;
    List<String> result = instance.convertSequencesMapToList(sequencesMap);
    assertEquals(expResult, result); 
  }

  /**
   * Test of convertSequencesMapToList method, of class SequenceBuilder.
   */
  @Test
  public void testConvertSequencesMapToListWithEmptyMap() {
    System.out.println("convertSequencesMapToList");
    Map<String, List<String>> sequencesMap = new HashMap(); 
    List<String> expResult = null;
    List<String> result = instance.convertSequencesMapToList(sequencesMap);
    assertEquals(expResult, result); 
  }
  
  /**
   * Test of buildSequencesFromUploadFile method, of class SequenceBuilder.
   * @throws java.io.IOException
   */
  @Test
  public void testBuildSequencesFromUploadFile() throws IOException {
    System.out.println("buildSequencesFromUploadFile");
    
    PowerMockito.mockStatic(IOUtils.class); 
    UploadedFile uploadedFile = mock(UploadedFile.class);  
    when(IOUtils.toString(any(InputStream.class), eq(StandardCharsets.UTF_8.name()))).thenReturn(dnaSequence); 
    List<String> result = instance.buildSequencesFromUploadFile(uploadedFile);
    assertNotNull(result);
  }
  
  @Test
  public void testBuildSequencesFromUploadFileCatchException() throws IOException {
    System.out.println("buildSequencesFromUploadFile");
    
    PowerMockito.mockStatic(IOUtils.class); 
    UploadedFile uploadedFile = mock(UploadedFile.class);  
    when(IOUtils.toString(any(InputStream.class), eq(StandardCharsets.UTF_8.name()))).thenThrow(IOException.class); 
    List<String> result = instance.buildSequencesFromUploadFile(uploadedFile);
    assertTrue(result == null);
  }

}
