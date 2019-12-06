package se.nrm.dina.dnakey.logic.metadata;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
import org.junit.After; 
import org.junit.Before; 
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author idali
 */
@RunWith(MockitoJUnitRunner.class) 
public class JsonToMetadataTest {
  
  private JsonToMetadata instance;
  
  public JsonToMetadataTest() {
  }
  
 
  @Before
  public void setUp() {
    instance = new JsonToMetadata();
  }
  
  @After
  public void tearDown() {
    instance = null;
  }

  /**
   * Test of buildSubMetadata method, of class JsonToMetadata.
   */
  @Test
  public void testBuildSubMetadata() {
    System.out.println("buildSubMetadata");
    JSONObject mockJson = mock(JSONObject.class);
//    gi|292389084|gb|GU571382|bold|BON074-06.COI-5P|gene|COI|latlon|59.183_N_9.617_E Erithacus rubecula
    String hitDef = "gi|292389084|gb|GU571382|bold|BON074-06.COI-5P|gene|COI|latlon|59.183_N_9.617_E|catnr|nrm1234 Erithacus rubecula"; 
    when(mockJson.getInt(any(String.class))).thenReturn(2);
    when(mockJson.getString(any(String.class))).thenReturn(hitDef);
    when(mockJson.getInt(any(String.class))).thenReturn(3); 
    
    List<BlastSubjectHsp> subjectHspList = new ArrayList<>();  
    BlastSubjectMetadata result = instance.buildSubMetadata(mockJson, subjectHspList);
  
    assertNotNull(result);
    assertEquals(result.getCatalogNumber(), "nrm1234"); 
    assertEquals(result.getScientificName(), " Erithacus rubecula"); 
    assertEquals(result.getCoordinates(), "59.183_N_9.617_E");
    assertEquals(result.getBoldId(), "BON074-06.COI-5P");
    assertEquals(result.getGenbankAccession(), "GU571382"); 
  }
  
  @Test
  public void testBuildSubMetadataWithCatalogNumber() {
    System.out.println("buildSubMetadata");
    JSONObject mockJson = mock(JSONObject.class);
    
    String hitDef = "gi|292389084|gb|GU571382|bold|BON074-06.COI-5P|gene|COI|latlon|59.183_N_9.617_E Erithacus rubecula"; 
    when(mockJson.getInt(any(String.class))).thenReturn(2);
    when(mockJson.getString(any(String.class))).thenReturn(hitDef);
    when(mockJson.getInt(any(String.class))).thenReturn(3); 
    
    List<BlastSubjectHsp> subjectHspList = new ArrayList<>();  
    BlastSubjectMetadata result = instance.buildSubMetadata(mockJson, subjectHspList);
  
    assertNotNull(result);
    assertEquals(result.getCatalogNumber(), ""); 
    assertEquals(result.getScientificName(), " Erithacus rubecula"); 
    assertEquals(result.getCoordinates(), "59.183_N_9.617_E");
    assertEquals(result.getBoldId(), "BON074-06.COI-5P");
    assertEquals(result.getGenbankAccession(), "GU571382"); 
  }

  /**
   * Test of buildSubHits method, of class JsonToMetadata.
   */
  @Test
  public void testBuildSubHits() {
    System.out.println("buildSubHits");
    
    double hspScore = 12d;
    double bitScore = 18d;
    int hspIdentity = 28;
    String qseq = "qseq";
    String hseq ="hseq";
    String midline = "midline";
    
    int hspQueryFrom = 20;
    int hspQueryTo = 80;
    int hspHitFrom = 30;
    int hspHitTo = 90;
    int hspPositive = 3;
    int hspGaps = 1; 
    int hspAlignLen = 2;
    int hspQueryFrame = 5;
    int hspHitFrame = 8;
    
    JSONObject mockJson = mock(JSONObject.class);
    when(mockJson.getDouble(any(String.class))).thenReturn(hspScore);
    when(mockJson.getDouble(any(String.class))).thenReturn(bitScore); 
    when(mockJson.getInt(any(String.class))).thenReturn(hspIdentity); 
    when(mockJson.getString(any(String.class))).thenReturn(qseq); 
    when(mockJson.getString(any(String.class))).thenReturn(hseq); 
    when(mockJson.getString(any(String.class))).thenReturn(midline); 
    when(mockJson.getInt(any(String.class))).thenReturn(hspQueryFrom); 
    when(mockJson.getInt(any(String.class))).thenReturn(hspQueryTo); 
    when(mockJson.getInt(any(String.class))).thenReturn(hspHitFrom); 
    when(mockJson.getInt(any(String.class))).thenReturn(hspHitTo); 
    when(mockJson.getInt(any(String.class))).thenReturn(hspPositive); 
    when(mockJson.getInt(any(String.class))).thenReturn(hspGaps); 
    when(mockJson.getInt(any(String.class))).thenReturn(hspAlignLen); 
    when(mockJson.getInt(any(String.class))).thenReturn(hspQueryFrame); 
    when(mockJson.getInt(any(String.class))).thenReturn(hspHitFrame);
 
      
    BlastSubjectHsp result = instance.buildSubHits(mockJson);
    assertNotNull(result);   
  }
  
}
