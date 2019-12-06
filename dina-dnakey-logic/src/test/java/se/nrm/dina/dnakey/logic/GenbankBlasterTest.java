package se.nrm.dina.dnakey.logic;
 
import java.io.IOException;
import org.biojava.nbio.ws.alignment.qblast.NCBIQBlastAlignmentProperties;
import org.biojava.nbio.ws.alignment.qblast.NCBIQBlastService;
import org.junit.After; 
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before; 
import org.junit.Test; 
import org.junit.runner.RunWith;
import static org.mockito.Matchers.any; 
import static org.mockito.Matchers.eq;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import org.mockito.runners.MockitoJUnitRunner; 

/**
 *
 * @author idali
 */
@RunWith(MockitoJUnitRunner.class) 
public class GenbankBlasterTest {
  
  @Mock
  private NCBIQBlastService service;
  @Mock
  private NCBIQBlastAlignmentProperties props;
   
  private GenbankBlaster instance;
  
  private final String fastSequence = ">Seq 1\n"
          + "CTTATCTTCGGGGCATGAGCCGGAATAATTGGCACAGCACTCAGCCTACTGATCCGCGCAGAACTAGGCCAACCAGGAACCCTCCTG"
          + "GGCGACGACCAAATTTACAACGTAATCGTCACCGCCCACGCCTTTGTAATAATCTTCTTCATGGTAATACCCATCATGATTGGAGGA"
          + "GCCGGAGGAGGAGACCCAATCCTGTACCAACACCTATTT\n\n";
  
  public GenbankBlasterTest() {
  } 
  
  @Before
  public void setUp()  {
    instance = new GenbankBlaster(service, props);  
  }
  
  @After
  public void tearDown() {
    instance = null;
  }
  
  @Test
  public void testDefaultConstructor() {
    GenbankBlaster testInstance = new GenbankBlaster();
    assertNotNull(testInstance);
  }

  /**
   * Test of remoteGenbankBlast method, of class GenbankBlaster.
   * @throws java.lang.Exception
   */
  @Test
  public void testRemoteGenbankBlast() throws Exception {
    System.out.println("remoteGenbankBlast"); 
     
    when(service.sendAlignmentRequest(any(String.class), eq(props))).thenReturn("XUBHAGKN016");
    String result = instance.remoteGenbankBlast(fastSequence);
    String expResult = "XUBHAGKN016";
    assertEquals(expResult, result);  
    
    verify(service, times(1)).sendAlignmentRequest(any(String.class), eq(props));
  }  
  
  @Test
  public void testRemoteGenbankBlastWithException() throws Exception {
    System.out.println("remoteGenbankBlast"); 
     
    when(service.sendAlignmentRequest(any(String.class), eq(props))).thenThrow(new IOException());
    String result = instance.remoteGenbankBlast(fastSequence);
    
    assertEquals(null, result);   
    verify(service, times(1)).sendAlignmentRequest(any(String.class), eq(props));
  }  
}
 