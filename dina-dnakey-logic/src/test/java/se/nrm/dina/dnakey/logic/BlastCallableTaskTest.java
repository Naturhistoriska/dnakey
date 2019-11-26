package se.nrm.dina.dnakey.logic;

import org.junit.After;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import se.nrm.dina.dnakey.logic.metadata.BlastMetadata;

/**
 *
 * @author idali
 */
//@RunWith(MockitoJUnitRunner.class) 
public class BlastCallableTaskTest {

  private BlastCallableTask testInstance;

  public BlastCallableTaskTest() {
  }

  @Before
  public void setUp() {
    testInstance = new BlastCallableTask("src/test/resources/test.fa", "nrm", "/usr/local/ncbi//blast/bin/blastn", "/usr/local/ncbi//blast/"); 
  }

  @After
  public void tearDown() {
  }
 
  /**
   * Test of call method, of class BlastCallableTask.
   *
   * @throws java.lang.Exception
   */
//  @Test
  public void testCall() throws Exception {
    System.out.println("call");
    
    BlastMetadata metadata = testInstance.call();
    System.out.println("metadata : " + metadata);
 

//    BlastCallableTask testInstance = Mockito.mock(BlastCallableTask.class);
// 
//    Mockito.verify(testInstance).call();  
  }

}
