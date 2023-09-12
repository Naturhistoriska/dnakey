package se.nrm.dina.dnakey.logic.metadata;
 
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
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

/**
 *
 * @author idali
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({MetadataDataFactory.class, XML.class})
@PowerMockIgnore({"javax.management.*",
  "org.apache.http.conn.ssl.*",
  "com.amazonaws.http.conn.ssl.*",
  "javax.net.ssl.*"})
public class MetadataDataFactoryTest {
  
  private MetadataDataFactory instance;
   
  private String metadata; 
  private String blastVersion;
  private String blastProgram;
  private String blastReference;
  private String blastDb;
  
  private JSONObject iteration;
  
  public MetadataDataFactoryTest() {
  }
   
  @Before
  public void setUp() {
    instance = MetadataDataFactory.getInstance();
   
    metadata = ""; 
    blastVersion = "22.4+";
    blastProgram = "blastn";
    blastReference = "reference";
    blastDb = "nrm";
 
    JSONObject blastJson = mock(JSONObject.class);
    JSONObject mockJson = mock(JSONObject.class);
    
    PowerMockito.mockStatic(XML.class);
    when(XML.toJSONObject(any(String.class))).thenReturn(mockJson); 
    when(mockJson.getJSONObject("BlastOutput")).thenReturn(blastJson);
    
    
    when(blastJson.getString("BlastOutput_version")).thenReturn(blastVersion);
    when(blastJson.getString("BlastOutput_program")).thenReturn(blastProgram);
    when(blastJson.getString("BlastOutput_reference")).thenReturn(blastReference);
    when(blastJson.getString("BlastOutput_db")).thenReturn(blastDb);
    
    JSONObject iterations = mock(JSONObject.class);
    iteration = mock(JSONObject.class);
    JSONObject itrStat = mock(JSONObject.class);
    JSONObject statistics = mock(JSONObject.class);
     
    when(blastJson.getJSONObject("BlastOutput_iterations")).thenReturn(iterations);   
    when(iterations.getJSONObject("Iteration")).thenReturn(iteration); 
    when(iteration.getJSONObject("Iteration_stat")).thenReturn(itrStat);
    when(itrStat.getJSONObject("Statistics")).thenReturn(statistics);
     

    int statisticDbNumber = 288;
    int statisticDbLength = 290;
    when(iteration.getInt("Statistics_db-num")).thenReturn(statisticDbNumber);
    when(iteration.getInt("Statistics_db-len")).thenReturn(statisticDbLength); 
  }
  
  @After
  public void tearDown() {
    instance = null;
  }

  /**
   * Test of getInstance method, of class MetadataDataFactory.
   */
  @Test
  public void testGetInstance() {
    System.out.println("getInstance"); 
    
    assertNotNull(instance); 
  }

  /**
   * Test of buildBlastMetadataByJson method, of class MetadataDataFactory.
   */
  @Test
  public void testBuildBlastMetadataByJsonNoHits() {
    System.out.println("buildBlastMetadataByJson");
    
    when(iteration.optJSONObject("Iteration_hits")).thenReturn(null);
    BlastMetadata result = instance.buildBlastMetadataByJson(metadata);
    assertNotNull(result);
    assertEquals(result.getDatabase(), blastDb);
    assertEquals(result.getProgram(), blastProgram);
    assertFalse(result.isHasHit());
  }
  
//  @Test
  public void testBuildBlastMetadataByJsonHasHits() throws Exception {
    System.out.println("buildBlastMetadataByJson"); 
    
    JSONObject itrHitsJson = mock(JSONObject.class);
    when(iteration.optJSONObject("Iteration_hits")).thenReturn(itrHitsJson);  
    
    JSONArray array = mock(JSONArray.class);
    when(itrHitsJson.getJSONArray("Hit")).thenReturn(array);   
    when(array.length()).thenReturn(3);
    
    JSONObject hitJson = mock(JSONObject.class);
    when(array.getJSONObject(any(Integer.class))).thenReturn(hitJson);  
     
    
    JSONObject hitHspsJson = mock(JSONObject.class);
    JSONObject hspJson = mock(JSONObject.class); 
     
    when(hitJson.getJSONObject("Hit_hsps")).thenReturn(hitHspsJson);   
    when(hitHspsJson.getJSONObject("Hsp")).thenReturn(hspJson);   
      
    JsonToMetadata jsonToMetadata = mock(JsonToMetadata.class);
    PowerMockito.whenNew(JsonToMetadata.class).withNoArguments().thenReturn(jsonToMetadata);
     
    BlastSubjectHsp subHsp = mock(BlastSubjectHsp.class);
    when(jsonToMetadata.buildSubHits(hspJson)).thenReturn(subHsp); 
    when(subHsp.getPercentage()).thenReturn(80l).thenReturn(100l);
            
    BlastSubjectMetadata subjectMetadata = mock(BlastSubjectMetadata.class);
     
    when(jsonToMetadata.buildSubMetadata(eq(hitJson), any(List.class))).thenReturn(subjectMetadata);
    
           
    BlastMetadata result = instance.buildBlastMetadataByJson(metadata);
    assertNotNull(result);
    assertEquals(result.getDatabase(), blastDb);
    assertEquals(result.getProgram(), blastProgram);
    assertTrue(result.isHasHit());
    assertTrue(result.isHasHighMatach());
    assertTrue(result.isHasLowMatch());
    assertFalse(result.isOpenLowMatch());
  }
  @Test
  public void testBuildBlastMetadataByJsonThrowException() {
    System.out.println("buildBlastMetadataByJson");
    
    when(iteration.optJSONObject("Iteration_hits")).thenReturn(null);
    BlastMetadata result = instance.buildBlastMetadataByJson(metadata);
    assertNotNull(result);
    assertEquals(result.getDatabase(), blastDb);
    assertEquals(result.getProgram(), blastProgram);
    assertFalse(result.isHasHit());
  }
  
}
