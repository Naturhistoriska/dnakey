package se.nrm.dina.dnakey.logic;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import se.nrm.dina.dnakey.logic.metadata.BlastMetadata;

/**
 *
 * @author idali
 */
public class BlastQueueTest {
  
  public BlastQueueTest() {
  }
  
  @BeforeClass
  public static void setUpClass() {
  }
  
  @AfterClass
  public static void tearDownClass() {
  }
  
  @Before
  public void setUp() {
  }
  
  @After
  public void tearDown() {
  }

  /**
   * Test of init method, of class BlastQueue.
   */
//  @Test
  public void testInit() {
    System.out.println("init");
    BlastQueue instance = new BlastQueue();
    instance.init();
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of run method, of class BlastQueue.
   */
//  @Test
  public void testRun() {
    System.out.println("run");
    List<String> filePathList = null;
    String dbname = "";
    BlastQueue instance = new BlastQueue();
    List<BlastMetadata> expResult = null;
    List<BlastMetadata> result = instance.run(filePathList, dbname);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }
  
}
