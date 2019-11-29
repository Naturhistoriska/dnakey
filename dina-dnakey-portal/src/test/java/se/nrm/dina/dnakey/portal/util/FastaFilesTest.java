package se.nrm.dina.dnakey.portal.util;

import org.junit.After; 
import org.junit.Before; 
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author idali
 */

public class FastaFilesTest {
  
  private FastaFiles instance;
  
  public FastaFilesTest() {
  }
 
  @Before
  public void setUp() {
    instance = FastaFiles.getInstance();
  }
  
  @After
  public void tearDown() {
    instance = null;
  }

  /**
   * Test of getInstance method, of class FastaFiles.
   */
  @Test
  public void testGetInstance() {
    System.out.println("getInstance"); 
    
    instance = null;
    instance = FastaFiles.getInstance();
    assertNotNull(instance);
  }

  /**
   * Test of getSequences method, of class FastaFiles.
   */
  @Test
  public void testGetSequences() {
    System.out.println("getSequences");
    
    int count = 3;  
    String result = instance.getSequences(count);  
    assertNotNull(result);
    assertTrue(result.contains(">Seq 1"));
    assertTrue(result.contains(">Seq 2"));
    assertTrue(result.contains(">Seq 3"));
    
    System.out.println("result : " + result);
    
    count = 0;
    result = instance.getSequences(count);  
    assertNull(result);
  }
  
}
