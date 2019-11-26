package se.nrm.dina.dnakey.logic;

import org.junit.After; 
import org.junit.Before; 
import org.junit.Test;
import static org.junit.Assert.*; 
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import se.nrm.dina.dnakey.logic.config.ConfigProperties;

/**
 *
 * @author idali
 */
@RunWith(MockitoJUnitRunner.class) 
public class BlastDbInfoTest {
  
  private static BlastDbInfo instance;
  private static String dbPath; 
  private static String dbInfoPath;  
  
  @Mock
  static ConfigProperties config;
  
  
  
  public BlastDbInfoTest() {
  }
 
  @Before
  public void setUp() { 
    instance = new BlastDbInfo(config);
    
    when(config.getDbPath()).thenReturn("/usr/local/ncbi//blast/db/");
    when(config.getDbinfoPath()).thenReturn("/usr/local/ncbi//blast/bin/blastdbcmd");
    
    dbPath = "/usr/local/ncbi//blast/db/"; 
    dbInfoPath = "/usr/local/ncbi//blast/bin/blastdbcmd";
  }
  
  @After
  public void tearDown() { 
    instance = null;
  }

  /**
   * Test of init method, of class BlastDbInfo.
   */
  @Test
  public void testInit() {
    System.out.println("init");  
    
    instance.init(); 
    verify(config, times(1)).getDbPath();
    assertEquals(dbPath, config.getDbPath());
     
    verify(config, times(1)).getDbinfoPath();
    assertEquals(dbInfoPath, config.getDbinfoPath());
  }

  /**
   * Test of getNrmDbTotal method, of class BlastDbInfo.
   */
  @Test
  public void testGetNrmDbTotal() {
    System.out.println("getNrmDbTotal"); 
    String expResult = "1,882";
    String result = instance.getNrmDbTotal(); 
    assertEquals(expResult, result); 
  }

  /**
   * Test of getBoldDbTotal method, of class BlastDbInfo.
   */
  @Test
  public void testGetBoldDbTotal() {
    System.out.println("getBoldDbTotal"); 
    String expResult = "23,269";
    String result = instance.getBoldDbTotal(); 
    assertEquals(expResult, result); 
  }

  /**
   * Test of getGenbankDbTotal method, of class BlastDbInfo.
   */
  @Test
  public void testGetGenbankDbTotal() {
    System.out.println("getGenbankDbTotal"); 
    String expResult = "347,668";
    String result = instance.getGenbankDbTotal(); 
    assertEquals(expResult, result); 
  }
  
}
