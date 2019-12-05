package se.nrm.dina.dnakey.logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Stream;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Matchers.any;
import org.mockito.Mockito;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import se.nrm.dina.dnakey.logic.metadata.BlastMetadata;
import se.nrm.dina.dnakey.logic.metadata.MetadataDataFactory;

/**
 *
 * @author idali
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({BlastCallableTask.class, MetadataDataFactory.class})
@PowerMockIgnore({"javax.management.*",
  "org.apache.http.conn.ssl.*",
  "com.amazonaws.http.conn.ssl.*",
  "javax.net.ssl.*"})
public class BlastCallableTaskTest {

  private BlastCallableTask testInstance;
  private String fastafilePath;
  private String dbName;
  private String blastPath;
  private String blastDbPath;
  
  private Runtime mockRuntime;
  private InputStream in;
  private Process mockProcess;
  private InputStreamReader inr;
  private BufferedReader bufferedReader;
  private BlastMetadata mockMetadata;

  public BlastCallableTaskTest() {
  }

  @Before
  public void setUp() throws IOException, Exception {
    fastafilePath = "src/test/resources/test.fa";
    dbName = "nrm";
    blastPath = "/usr/local/ncbi//blast/bin/blastn";
    blastDbPath = "/usr/local/ncbi//blast/";

    testInstance = new BlastCallableTask(fastafilePath, dbName, blastPath, blastDbPath);
    
    mockRuntime = PowerMockito.mock(Runtime.class);
    in = Mockito.mock(InputStream.class);
    mockProcess = PowerMockito.mock(Process.class);
    inr = Mockito.mock(InputStreamReader.class);
    bufferedReader = Mockito.mock(BufferedReader.class);
    mockMetadata = mock(BlastMetadata.class);
    
    mockRuntime = PowerMockito.mock(Runtime.class);
    PowerMockito.mockStatic(Runtime.class);
    Mockito.when(Runtime.getRuntime()).thenReturn(mockRuntime);
 
    Mockito.when(mockProcess.getInputStream()).thenReturn(in); 
    PowerMockito.whenNew(InputStreamReader.class).withArguments(in).thenReturn(inr);
    PowerMockito.whenNew(BufferedReader.class).withArguments(inr).thenReturn(bufferedReader);

    PowerMockito.when(bufferedReader.lines())
            .then(i -> Stream.of("AGGADAA\nfsadfasdf\n", "AGGADAA"));

    MetadataDataFactory mockFactory = PowerMockito.mock(MetadataDataFactory.class);
    PowerMockito.mockStatic(MetadataDataFactory.class);
    when(MetadataDataFactory.getInstance()).thenReturn(mockFactory); 
    Mockito.when(mockFactory.buildBlastMetadataByJson(any(String.class))).thenReturn(mockMetadata); 
  }

  @After
  public void tearDown() {
    testInstance = null;
  }

  @Test
  public void testDefaultContructor() {
    System.out.println("testDefaultContructor");
    testInstance = new BlastCallableTask();
    assertNotNull(testInstance);
  }

  /** 
   * Test of call method, of class BlastCallableTask.
   *
   * @throws java.io.IOException
   * @throws java.lang.Exception
   */
  @Test
  public void testCall() throws IOException, Exception {
    System.out.println("testCall");
 
    Mockito.when(mockRuntime.exec(any(String.class))).thenReturn(mockProcess);
    BlastMetadata metadata = testInstance.call();
    assertNotNull(metadata);  
    verify(mockRuntime, times(1)).exec(any(String.class));
    verify(mockProcess, times(1)).getInputStream();
    verify(in, times(1)).close();  
  }

  @Test
  public void testCallThrowException() throws IOException, Exception {
    System.out.println("testCallThrowException");
 
    Mockito.when(mockProcess.isAlive()).thenReturn(true);
    Mockito.when(mockRuntime.exec(any(String.class))).thenThrow(new IOException());

    BlastMetadata metadata = testInstance.call();
    
    System.out.println("testCallThrowException...");
    assertEquals(metadata, null);
    verify(mockRuntime, times(1)).exec(any(String.class));
    verify(mockProcess, times(0)).getInputStream(); 
    verify(mockProcess, times(0)).destroy(); 
  }

  @Test
  public void testCallWithInputStreamException() throws IOException, Exception {
    System.out.println("testCallWithInputStreamException");
 
    doThrow(new IOException()).when(in).close();
    Mockito.when(mockRuntime.exec(any(String.class))).thenReturn(mockProcess);
// 
    BlastMetadata metadata = testInstance.call();
    assertNotNull(metadata);

    verify(mockRuntime, times(1)).exec(any(String.class));
    verify(mockProcess, times(1)).getInputStream();
    verify(in, times(1)).close();  
  }

  @Test
  public void testCallProcessDestroy() throws IOException, Exception {
    System.out.println("testCallProcessDestroy"); 
    Mockito.when(mockProcess.isAlive()).thenReturn(true);
    Mockito.when(mockRuntime.exec(any(String.class))).thenReturn(mockProcess);
 
    BlastMetadata metadata = testInstance.call();
    assertNotNull(metadata);

    verify(mockRuntime, times(1)).exec(any(String.class));
    verify(mockProcess, times(1)).getInputStream();
    verify(mockProcess, times(1)).destroy();
  }
  
  @Test
  public void testCallProcessNotAlive() throws IOException, Exception {
    System.out.println("testCallNotProcessDestroy"); 
    Mockito.when(mockProcess.isAlive()).thenReturn(false);
    Mockito.when(mockRuntime.exec(any(String.class))).thenReturn(mockProcess);
 

    BlastMetadata metadata = testInstance.call();
    assertNotNull(metadata);

    verify(mockRuntime, times(1)).exec(any(String.class));
    verify(mockProcess, times(1)).getInputStream();
    verify(mockProcess, times(0)).destroy();
  }
  
  @Test
  public void testCallProcessIsNull() throws IOException, Exception {
    System.out.println("testCallNotProcessDestroy"); 
    Mockito.when(mockProcess.isAlive()).thenReturn(false); 
    Mockito.when(mockRuntime.exec(any(String.class))).thenThrow(new IOException()); 

    BlastMetadata metadata = testInstance.call();
    assertEquals(metadata, null);

    verify(mockRuntime, times(1)).exec(any(String.class)); 
    verify(mockProcess, times(0)).destroy();
  }
}
