package se.nrm.dina.dnakey.logic;

import java.io.BufferedReader; 
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import javax.enterprise.concurrent.ManagedExecutorService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith; 
import static org.mockito.Matchers.any;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when; 
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore; 
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import se.nrm.dina.dnakey.logic.config.ConfigProperties;
import se.nrm.dina.dnakey.logic.metadata.BlastMetadata;

/**
 *
 * @author idali
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({BlastQueue.class, BlastCallableTask.class})
@PowerMockIgnore({"javax.management.*", 
  "org.apache.http.conn.ssl.*", 
  "com.amazonaws.http.conn.ssl.*",
  "javax.net.ssl.*"})
public class BlastQueueTest {

  private BlastQueue instance;
  private String blastPath;
  private String blastDbPath;

  @Mock
  private ConfigProperties config;
  @Mock
  ManagedExecutorService service;

  public BlastQueueTest() {
  }

  @Before
  public void setUp() {
    blastDbPath = "dbPath";
    blastPath = "blastPath";

    when(config.getBlastnPath()).thenReturn(blastPath);
    when(config.getDbPath()).thenReturn(blastDbPath);
    instance = new BlastQueue(service, config); 
  }

  @After
  public void tearDown() {
    instance = null;
  }

  @Test
  public void testDefaultConstuctor() {
    BlastQueue testInstance = new BlastQueue();
    assertNotNull(testInstance);
  }

  /**
   * Test of init method, of class BlastQueue.
   */
  @Test
  public void testInit() {
    System.out.println("init");
    instance.init();
    
    verify(config, times(1)).getBlastnPath();
    assertEquals(blastPath, config.getBlastnPath());
    
    verify(config, times(1)).getDbPath();
    assertEquals(blastDbPath, config.getDbPath()); 
  }

  /**
   * Test of run method, of class BlastQueue.
   * @throws java.lang.Exception
   */
  @Test
  public void testRun() throws Exception {
    System.out.println("run");
    
    List<String> list = new ArrayList();
    list.add("test1");
    list.add("test2");
    String dbname = "nrm"; 
     
    Future mockFuture = mock(Future.class);
    when(mockFuture.isDone()).thenReturn(false).thenReturn(true);
    
    BlastMetadata mockMetadata = mock(BlastMetadata.class);
    when(mockFuture.get()).thenReturn(mockMetadata);
    
    BlastCallableTask mockTask = Mockito.mock(BlastCallableTask.class);
     
    PowerMockito.whenNew(BlastCallableTask.class)
            .withArguments(any(String.class), any(String.class), any(String.class), any(String.class))
            .thenReturn(mockTask); 
    when(service.submit(mockTask)).thenReturn(mockFuture);
     
    List<BlastMetadata> result = instance.run(list, dbname); 
    assertNotNull(result);
    assertEquals(2, result.size()); 
    
    verify(service, times(2)).submit(mockTask);
  }

}
