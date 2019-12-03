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
  public void testGetSequencesCount1() {
    System.out.println("getSequences");
    
    int count = 1;  
    String result = instance.getSequences(count);  
    assertNotNull(result);
    assertTrue(result.contains(">Seq 1"));  
    
    count = 0;
    result = instance.getSequences(count);  
    assertNull(result);
  }
  
  @Test
  public void testGetSequencesCount2() {
    System.out.println("getSequences");
    
    int count = 2;  
    String result = instance.getSequences(count);  
    assertNotNull(result);
    assertTrue(result.contains(">Seq 1"));  
    assertTrue(result.contains(">Seq 2"));
    
    count = 0;
    result = instance.getSequences(count);  
    assertNull(result);
  }
  

  /**
   * Test of getSequences method, of class FastaFiles.
   */
  @Test
  public void testGetSequencesCount3() {
    System.out.println("getSequences");
    
    int count = 3;  
    String result = instance.getSequences(count);  
    assertNotNull(result);
    assertTrue(result.contains(">Seq 1"));
    assertTrue(result.contains(">Seq 2"));
    assertTrue(result.contains(">Seq 3"));
     
    count = 0;
    result = instance.getSequences(count);  
    assertNull(result);
  }
  
  
  /**
   * Test of getSequences method, of class FastaFiles.
   */
  @Test
  public void testGetSequencesCount4() {
    System.out.println("getSequences");
    
    int count = 4;  
    String result = instance.getSequences(count);  
    assertNotNull(result);
    assertTrue(result.contains(">Seq 1"));
    assertTrue(result.contains(">Seq 2"));
    assertTrue(result.contains(">Seq 3"));
    assertTrue(result.contains(">Seq 4"));
    count = 0;
    result = instance.getSequences(count);  
    assertNull(result);
  }
  
  /**
   * Test of getSequences method, of class FastaFiles.
   */
  @Test
  public void testGetSequencesCount5() {
    System.out.println("getSequences");
    
    int count = 5;  
    String result = instance.getSequences(count);  
    assertNotNull(result);
    assertTrue(result.contains(">Seq 1"));
    assertTrue(result.contains(">Seq 2"));
    assertTrue(result.contains(">Seq 3"));
    assertTrue(result.contains(">Seq 4"));
    assertTrue(result.contains(">Seq 5"));
     
    count = 0;
    result = instance.getSequences(count);  
    assertNull(result);
  }
  
  /**
   * Test of getSequences method, of class FastaFiles.
   */
  @Test
  public void testGetSequencesCount6() {
    System.out.println("getSequences");
    
    int count = 6;  
    String result = instance.getSequences(count);  
    assertNotNull(result);
    assertTrue(result.contains(">Seq 1"));
    assertTrue(result.contains(">Seq 2"));
    assertTrue(result.contains(">Seq 3"));
    assertTrue(result.contains(">Seq 4"));
    assertTrue(result.contains(">Seq 5"));
    assertTrue(result.contains(">Seq 6"));
     
    count = 0;
    result = instance.getSequences(count);  
    assertNull(result);
  }
}
