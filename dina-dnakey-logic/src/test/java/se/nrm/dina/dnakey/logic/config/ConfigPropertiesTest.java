package se.nrm.dina.dnakey.logic.config;
  
import org.junit.After; 
import org.junit.Before; 
import org.junit.Test;
import static org.junit.Assert.*; 

/**
 *
 * @author idali
 */ 
public class ConfigPropertiesTest {
   
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
  private String username;
  private String password;
  
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
    username = "user";
    password = "password";
  }
  
  @After
  public void tearDown() {
    instance = null;
  }
  
  @Test
  public void testDefaultConstructor() {
    instance = new ConfigProperties();
    assertNotNull(instance);
  }
  
  @Test(expected = RuntimeException.class)
  public void testException1() {
    instance = new ConfigProperties();
    
    instance.getGeoDataFilePath(); 
  }
  
  @Test(expected = RuntimeException.class)
  public void testException2() {
    instance = new ConfigProperties();
    
    instance.getDbPath(); 
  }
   
  @Test(expected = RuntimeException.class)
  public void testException3() {
    instance = new ConfigProperties();
    
    instance.getBinPath(); 
  }
   
  @Test(expected = RuntimeException.class)
  public void testException4() {
    instance = new ConfigProperties();
    
    instance.getDbinfoPath(); 
  }
        
      
  @Test(expected = RuntimeException.class)
  public void testException5() {
    instance = new ConfigProperties();
    
    instance.getBlastnPath(); 
  }
      
  @Test(expected = RuntimeException.class)
  public void testException6() {
    instance = new ConfigProperties();
    
    instance.getFastaFilePath(); 
  } 
      
  @Test(expected = RuntimeException.class)
  public void testException7() {
    instance = new ConfigProperties();
    
    instance.getSolrPath(); 
  } 
  
  @Test(expected = RuntimeException.class)
  public void testException8() {
    instance = new ConfigProperties();
    
    instance.getMapKey(); 
  }
         
      
  @Test(expected = RuntimeException.class)
  public void testException9() {
    instance = new ConfigProperties();
    
    instance.getThumbPath(); 
  }
          
  @Test(expected = RuntimeException.class)
  public void testException10() {
    instance = new ConfigProperties();
    
    instance.getImagePath(); 
  }
  
  @Test(expected = RuntimeException.class)
  public void testException11() {
    instance = new ConfigProperties();
    
    instance.getBlastBasePath(); 
  }
         
         
  /** 
   * Test of getGeoDataFilePath method, of class ConfigProperties.
   */
  @Test
  public void testGetGeoDataFilePath() {
    System.out.println("getGeoDataFilePath");  
       
    instance = new ConfigProperties(geoDataFilePath, blastBasePath, dbPath, binPath, 
            dbinfoPath, blastnPath, fastaFilePath, solrPath, thumbPath, 
            imagePath, mapKey, username, password);
    String result = instance.getGeoDataFilePath(); 
    assertEquals(geoDataFilePath, result); 
  }

  /**
   * Test of getBlastBasePath method, of class ConfigProperties.
   */
  @Test
  public void testGetBlastBasePath() {
    System.out.println("getBlastBasePath"); 
    instance = new ConfigProperties(geoDataFilePath, blastBasePath, dbPath, binPath, 
            dbinfoPath, blastnPath, fastaFilePath, solrPath, thumbPath, 
            imagePath, mapKey, username, password); 
    String result = instance.getBlastBasePath();
    assertEquals(blastBasePath, result); 
  }

  /**
   * Test of getDbPath method, of class ConfigProperties.
   */
  @Test
  public void testGetDbPath() {
    System.out.println("getDbPath");
   instance = new ConfigProperties(geoDataFilePath, blastBasePath, dbPath, binPath, 
            dbinfoPath, blastnPath, fastaFilePath, solrPath, thumbPath, 
           imagePath, mapKey, username, password);
 
    String result = instance.getDbPath();
    assertEquals(dbPath, result);  
  }

  /**
   * Test of getBinPath method, of class ConfigProperties.
   */
  @Test
  public void testGetBinPath() {
    System.out.println("getBinPath");
    instance = new ConfigProperties(geoDataFilePath, blastBasePath, dbPath, binPath, 
            dbinfoPath, blastnPath, fastaFilePath, solrPath, thumbPath, 
            imagePath, mapKey, username, password);
 
    String result = instance.getBinPath();
    assertEquals(binPath, result); 
  }

  /**
   * Test of getDbinfoPath method, of class ConfigProperties.
   */
  @Test
  public void testGetDbinfoPath() {
    System.out.println("getDbinfoPath");
    instance = new ConfigProperties(geoDataFilePath, blastBasePath, dbPath, binPath, 
            dbinfoPath, blastnPath, fastaFilePath, solrPath, thumbPath, 
            imagePath, mapKey, username, password);
 
    String result = instance.getDbinfoPath();
    assertEquals(dbinfoPath, result); 
  }

  /**
   * Test of getBlastnPath method, of class ConfigProperties.
   */
  @Test
  public void testGetBlastnPath() {
    System.out.println("getBlastnPath");
    instance = new ConfigProperties(geoDataFilePath, blastBasePath, dbPath, binPath, 
            dbinfoPath, blastnPath, fastaFilePath, solrPath, thumbPath, 
            imagePath, mapKey, username, password);
 
    String result = instance.getBlastnPath();
    assertEquals(blastnPath, result); 
  }

  /**
   * Test of getFastaFilePath method, of class ConfigProperties.
   */
  @Test
  public void testGetFastaFilePath() {
    System.out.println("getFastaFilePath");
    instance = new ConfigProperties(geoDataFilePath, blastBasePath, dbPath, binPath, 
            dbinfoPath, blastnPath, fastaFilePath, solrPath, thumbPath, 
            imagePath, mapKey, username, password);
    String result = instance.getFastaFilePath();
    assertEquals(fastaFilePath, result); 
  }

  /**
   * Test of getSolrPath method, of class ConfigProperties.
   */
  @Test
  public void testGetSolrPath() {
    System.out.println("getSolrPath");
    instance = new ConfigProperties(geoDataFilePath, blastBasePath, dbPath, binPath, 
            dbinfoPath, blastnPath, fastaFilePath, solrPath, thumbPath, 
            imagePath, mapKey, username, password);
    String result = instance.getSolrPath();
    assertEquals(solrPath, result); 
  }

  /**
   * Test of getMapKey method, of class ConfigProperties.
   */
  @Test
  public void testGetMapKey() {
    System.out.println("getMapKey");
    instance = new ConfigProperties(geoDataFilePath, blastBasePath, dbPath, binPath, 
            dbinfoPath, blastnPath, fastaFilePath, solrPath, thumbPath, 
            imagePath, mapKey, username, password);
    
    String result = instance.getMapKey();
    assertEquals(mapKey, result); 
  }

  /**
   * Test of getThumbPath method, of class ConfigProperties.
   */
  @Test
  public void testGetThumbPath() {
    System.out.println("getThumbPath");
    instance = new ConfigProperties(geoDataFilePath, blastBasePath, dbPath, binPath, 
            dbinfoPath, blastnPath, fastaFilePath, solrPath, thumbPath, 
            imagePath, mapKey, username, password);
    String result = instance.getThumbPath();
    assertEquals(thumbPath, result); 
  }

  /**
   * Test of getImagePath method, of class ConfigProperties.
   */
  @Test
  public void testGetImagePath() {
    System.out.println("getImagePath");
    instance = new ConfigProperties(geoDataFilePath, blastBasePath, dbPath, binPath, 
            dbinfoPath, blastnPath, fastaFilePath, solrPath, thumbPath, 
            imagePath, mapKey, username, password);
    String result = instance.getImagePath();
    assertEquals(imagePath, result); 
  }
  
}
