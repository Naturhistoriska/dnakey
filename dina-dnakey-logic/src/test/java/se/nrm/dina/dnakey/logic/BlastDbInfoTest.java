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
import static org.mockito.Mockito.doThrow;
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
  private Runtime mockRuntime;

  @Mock
  private ConfigProperties config;
  private Process mockProcess;
  private InputStream in;

  public BlastDbInfoTest() {
  }
 
  @Before
  public void setUp() throws IOException, Exception { 

    dbPath = "dbPath";
    dbInfoPath = "dbInfoPath";

    when(config.getDbPath()).thenReturn(dbPath);
    when(config.getDbinfoPath()).thenReturn(dbInfoPath);

    instance = new BlastDbInfo(config);
    
    mockRuntime = PowerMockito.mock(Runtime.class);
    PowerMockito.mockStatic(Runtime.class); 
    Mockito.when(Runtime.getRuntime()).thenReturn(mockRuntime);

    in = Mockito.mock(InputStream.class);
    mockProcess = PowerMockito.mock(Process.class);
    Mockito.when(mockProcess.getInputStream()).thenReturn(in);
     
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
    
    Mockito.when(mockProcess.isAlive()).thenReturn(true);
    Mockito.when(mockRuntime.exec(any(String.class))).thenReturn(mockProcess);
    instance.init();
    verify(config, times(1)).getDbPath();
    assertEquals(dbPath, config.getDbPath());

    verify(config, times(1)).getDbinfoPath();
    assertEquals(dbInfoPath, config.getDbinfoPath());

    verify(mockRuntime, times(3)).exec(any(String.class));
    verify(mockProcess, times(3)).getInputStream(); 
    verify(mockProcess, times(3)).destroy();
    verify(in, times(3)).close(); 
  }

  /**
   * Test of getNrmDbTotal method, of class BlastDbInfo.
   * @throws java.io.IOException
   */
  @Test
  public void testGetNrmDbTotal() throws IOException {
    System.out.println("getNrmDbTotal");

    Mockito.when(mockRuntime.exec(any(String.class))).thenReturn(mockProcess);
    instance.init();
    String expResult = "120";
    String result = instance.getNrmDbTotal();
    
    assertEquals(expResult, result);
  }

  @Test
  public void testGetNrmDbTotalNull() throws IOException {
    System.out.println("testGetNrmDbTotalNull");
 
    Mockito.when(mockProcess.isAlive()).thenReturn(false);
    Mockito.when(mockRuntime.exec(any(String.class))).thenReturn(mockProcess);
    String expResult = "120";
    String result = instance.getNrmDbTotal();
    assertEquals(expResult, result); 
    
    verify(mockRuntime, times(1)).exec(any(String.class));
    verify(mockProcess, times(1)).getInputStream(); 
    verify(mockProcess, times(0)).destroy();
    verify(in, times(1)).close(); 
  }

  /**
   * Test of getBoldDbTotal method, of class BlastDbInfo.
   * @throws java.io.IOException
   */
  @Test
  public void testGetBoldDbTotal() throws IOException {
    System.out.println("getBoldDbTotal");
 
    Mockito.when(mockRuntime.exec(any(String.class))).thenReturn(mockProcess);
    instance.init();
    String expResult = "120";
    String result = instance.getBoldDbTotal();
    assertEquals(expResult, result);
  }
  
  @Test
  public void testGetBoldDbTotalNull() throws IOException {
    System.out.println("testGetBoldDbTotalNull");
 
    Mockito.when(mockProcess.isAlive()).thenReturn(true);
    Mockito.when(mockRuntime.exec(any(String.class))).thenReturn(mockProcess);
    String expResult = "120";
    String result = instance.getBoldDbTotal();
    assertEquals(expResult, result);
    
    verify(mockRuntime, times(1)).exec(any(String.class));
    verify(mockProcess, times(1)).getInputStream(); 
    verify(mockProcess, times(1)).destroy();
    verify(in, times(1)).close(); 
  }

  /**
   * Test of getGenbankDbTotal method, of class BlastDbInfo.
   * @throws java.io.IOException
   */
  @Test
  public void testGetGenbankDbTotal() throws IOException {
    System.out.println("getGenbankDbTotal");

    Mockito.when(mockRuntime.exec(any(String.class))).thenReturn(mockProcess);
    String expResult = "120";
    
    instance.init();
    String result = instance.getGenbankDbTotal();
    assertEquals(expResult, result);
  }
  
  @Test
  public void testGetGenbankDbTotalNull() throws IOException {
    System.out.println("testGetGenbankDbTotalNull");
 
    Mockito.when(mockProcess.isAlive()).thenReturn(false);
    Mockito.when(mockRuntime.exec(any(String.class))).thenReturn(mockProcess);
   
    String expResult = "120";
    String result = instance.getGenbankDbTotal();
    assertEquals(expResult, result);
    
    verify(mockRuntime, times(1)).exec(any(String.class));
    verify(mockProcess, times(1)).getInputStream(); 
    verify(mockProcess, times(0)).destroy();
    verify(in, times(1)).close();  
  }
  
  @Test
  public void testGetTotalCatchIOException() throws IOException {
    System.out.println("testGetTotalCatchIOException");
 
    Mockito.when(mockProcess.isAlive()).thenReturn(true);
    Mockito.when(mockRuntime.exec(any(String.class))).thenThrow(new IOException()); 
    String result = instance.getGenbankDbTotal();
    assertEquals(null, result);
    
    verify(mockRuntime, times(1)).exec(any(String.class));
    verify(mockProcess, times(0)).getInputStream(); 
    verify(mockProcess, times(0)).destroy();
    verify(in, times(0)).close(); 
  }
   
  @Test
  public void testGetTotalCatchInputStreamCloseIOException() throws IOException {
    System.out.println("testGetTotalCatchInputStreamCloseIOException");
  
    doThrow(new IOException()).when(in).close();
    
    Mockito.when(mockProcess.isAlive()).thenReturn(true);
    Mockito.when(mockRuntime.exec(any(String.class))).thenReturn(mockProcess);
    String result = instance.getGenbankDbTotal();
    assertEquals("120", result); 
    
    verify(mockRuntime, times(1)).exec(any(String.class));
    verify(mockProcess, times(1)).getInputStream(); 
    verify(mockProcess, times(1)).destroy();
    verify(in, times(1)).close(); 
  } 
}
