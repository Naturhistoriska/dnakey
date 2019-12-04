package se.nrm.dina.dnakey.logic;
 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Stream;
import org.junit.After; 
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*; 
import org.junit.runner.RunWith; 
import static org.mockito.Matchers.any;
import org.mockito.Mock; 
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when; 
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore; 
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import se.nrm.dina.dnakey.logic.config.ConfigProperties;

/**
 *
 * @author idali
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({BlastDbInfo.class})
@PowerMockIgnore({"javax.management.*", 
  "org.apache.http.conn.ssl.*", 
  "com.amazonaws.http.conn.ssl.*",
  "javax.net.ssl.*"})
public class BlastDbInfoTest {

  private BlastDbInfo instance;
  private String dbPath;
  private String dbInfoPath;

  @Mock
  private ConfigProperties config;
  private Process mockProcess;

  public BlastDbInfoTest() {
  }
 
  @Before
  public void setUp() throws IOException, Exception { 

    dbPath = "dbPath";
    dbInfoPath = "dbInfoPath";

    when(config.getDbPath()).thenReturn(dbPath);
    when(config.getDbinfoPath()).thenReturn(dbInfoPath);

    instance = new BlastDbInfo(config);
    
    final Runtime mockRuntime = PowerMockito.mock(Runtime.class);
    PowerMockito.mockStatic(Runtime.class); 
    Mockito.when(Runtime.getRuntime()).thenReturn(mockRuntime);

    InputStream in = Mockito.mock(InputStream.class);
    mockProcess = PowerMockito.mock(Process.class);
    Mockito.when(mockProcess.getInputStream()).thenReturn(in);
    Mockito.when(mockRuntime.exec(any(String.class))).thenReturn(mockProcess);

    InputStreamReader inr = Mockito.mock(InputStreamReader.class);
    BufferedReader bufferedReader = Mockito.mock(BufferedReader.class);

    PowerMockito.whenNew(InputStreamReader.class).withArguments(in).thenReturn(inr);
    PowerMockito.whenNew(BufferedReader.class).withArguments(inr).thenReturn(bufferedReader);

    PowerMockito.when(bufferedReader.lines())
            .then(i -> Stream.of("120 sequences AGGADAA", "AGGADAA"));
  }

  @After
  public void tearDown() {
    instance = null;
  }

  @Test
  public void testDefaultConstructor() {
    BlastDbInfo testInstance = new BlastDbInfo();
    assertNotNull(testInstance);
  }

  /**
   * Test of init method, of class BlastDbInfo.
   *
   * @throws java.io.IOException
   */
  @Test
  public void testInit() throws Exception {
    System.out.println("init"); 
    
    instance.init();
    verify(config, times(1)).getDbPath();
    assertEquals(dbPath, config.getDbPath());

    verify(config, times(1)).getDbinfoPath();
    assertEquals(dbInfoPath, config.getDbinfoPath());

    verify(mockProcess, times(3)).getInputStream();
  }

  /**
   * Test of getNrmDbTotal method, of class BlastDbInfo.
   */
  @Test
  public void testGetNrmDbTotal() {
    System.out.println("getNrmDbTotal");

    instance.init();
    String expResult = "120";
    String result = instance.getNrmDbTotal();
    
    assertEquals(expResult, result);
  }

  @Test
  public void testGetNrmDbTotalNull() {
    System.out.println("getNrmDbTotal");
 
    String expResult = "120";
    String result = instance.getNrmDbTotal();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getBoldDbTotal method, of class BlastDbInfo.
   */
  @Test
  public void testGetBoldDbTotal() {
    System.out.println("getBoldDbTotal");

    instance.init();
    String expResult = "120";
    String result = instance.getBoldDbTotal();
    assertEquals(expResult, result);
  }
  
  @Test
  public void testGetBoldDbTotalNull() {
    System.out.println("getBoldDbTotal");
 
    String expResult = "120";
    String result = instance.getBoldDbTotal();
    assertEquals(expResult, result);
  }

  /**
   * Test of getGenbankDbTotal method, of class BlastDbInfo.
   */
  @Test
  public void testGetGenbankDbTotal() {
    System.out.println("getGenbankDbTotal");

    String expResult = "120";
    String result = instance.getGenbankDbTotal();
    assertEquals(expResult, result);
  }
  
  @Test
  public void testGetGenbankDbTotalNull() {
    System.out.println("getGenbankDbTotal");
 
    instance.init();
    String expResult = "120";
    String result = instance.getGenbankDbTotal();
    assertEquals(expResult, result);
  }

}
