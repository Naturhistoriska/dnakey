package se.nrm.dina.dnakey.logic.config;
 
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.Logger;
import org.junit.After; 
import org.junit.Before; 
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner; 

/**
 *
 * @author idali
 */
@RunWith(MockitoJUnitRunner.class)
public class ConfigPropertiesTest {
  
  
  Logger mockLogger = Mockito.mock(Logger.class);
  
  private ConfigProperties instance;
  
  
  private String geoDataFilePath;
  private String blastBasePath;
  private String dbPath;
  private String binPath;
  private String dbinfoPath;
  private String blastnPath;
  private String fastaFilePath;
  private String solrPath;
  private String thumbPath;
  private String imagePath;
  private String mapKey;
  
  public ConfigPropertiesTest() {
  }
 
  @Before
  public void setUp() {
    geoDataFilePath = "/usr/geo_coords.tsv";
    blastBasePath = "/usr/local/blast/";
    dbPath = "/usr/local/nbci/blast/db/";
    binPath = "/usr/local/blast/bin/";
    dbinfoPath = "/usr/local/blast/bin/blastdbcmd";
    blastnPath = "/usr/local/blast/bin/blastn";
    fastaFilePath = "/usr/local/ncbi/blast/tempfastafiles";
    solrPath = "https://local-solr.nrm.se/solr/nrm";
    thumbPath = "http://images.morphbank.nrm.se/?id=";
    imagePath = "http://morphbank.nrm.se/Browse/ByImage/?specimenId=";
    mapKey = "map_key";
    
  
        
    instance = new ConfigProperties(geoDataFilePath, blastBasePath, dbPath, binPath, 
            dbinfoPath, blastnPath, fastaFilePath, solrPath, thumbPath, imagePath, mapKey);
 
  }
  
  @After
  public void tearDown() {
  }

  /**
   * Test of getGeoDataFilePath method, of class ConfigProperties.
   */
  @Test
  public void testGetGeoDataFilePath() {
    System.out.println("getGeoDataFilePath");  
      
    String result = instance.getGeoDataFilePath(); 
    assertEquals(geoDataFilePath, result); 
  }

  /**
   * Test of getBlastBasePath method, of class ConfigProperties.
   */
  @Test
  public void testGetBlastBasePath() {
    System.out.println("getBlastBasePath"); 
    String expResult = "/usr/local/blast/";
    String result = instance.getBlastBasePath();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getDbPath method, of class ConfigProperties.
   */
//  @Test
  public void testGetDbPath() {
    System.out.println("getDbPath");
    ConfigProperties instance = new ConfigProperties();
    String expResult = "";
    String result = instance.getDbPath();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getBinPath method, of class ConfigProperties.
   */
//  @Test
  public void testGetBinPath() {
    System.out.println("getBinPath");
    ConfigProperties instance = new ConfigProperties();
    String expResult = "";
    String result = instance.getBinPath();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getDbinfoPath method, of class ConfigProperties.
   */
//  @Test
  public void testGetDbinfoPath() {
    System.out.println("getDbinfoPath");
    ConfigProperties instance = new ConfigProperties();
    String expResult = "";
    String result = instance.getDbinfoPath();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getBlastnPath method, of class ConfigProperties.
   */
//  @Test
  public void testGetBlastnPath() {
    System.out.println("getBlastnPath");
    ConfigProperties instance = new ConfigProperties();
    String expResult = "";
    String result = instance.getBlastnPath();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getFastaFilePath method, of class ConfigProperties.
   */
//  @Test
  public void testGetFastaFilePath() {
    System.out.println("getFastaFilePath");
    ConfigProperties instance = new ConfigProperties();
    String expResult = "";
    String result = instance.getFastaFilePath();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getSolrPath method, of class ConfigProperties.
   */
//  @Test
  public void testGetSolrPath() {
    System.out.println("getSolrPath");
    ConfigProperties instance = new ConfigProperties();
    String expResult = "";
    String result = instance.getSolrPath();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getMapKey method, of class ConfigProperties.
   */
//  @Test
  public void testGetMapKey() {
    System.out.println("getMapKey");
    ConfigProperties instance = new ConfigProperties();
    String expResult = "";
    String result = instance.getMapKey();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getThumbPath method, of class ConfigProperties.
   */
//  @Test
  public void testGetThumbPath() {
    System.out.println("getThumbPath");
    ConfigProperties instance = new ConfigProperties();
    String expResult = "";
    String result = instance.getThumbPath();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getImagePath method, of class ConfigProperties.
   */
//  @Test
  public void testGetImagePath() {
    System.out.println("getImagePath");
    ConfigProperties instance = new ConfigProperties();
    String expResult = "";
    String result = instance.getImagePath();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }
  
}
