package se.nrm.dina.dnakey.portal.util;

import org.junit.After; 
import org.junit.Before; 
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author idali
 */
public class UtilTest {
  
  private Util instance;
  
  public UtilTest() {
  }
 
  @Before
  public void setUp() {
    instance = Util.getInstance();
  }
  
  @After
  public void tearDown() {
    instance = null;
  }

  /**
   * Test of getInstance method, of class Util.
   */
  @Test
  public void testGetInstance() {
    System.out.println("getInstance"); 
    
    instance = null;
    instance = Util.getInstance();
    assertNotNull(instance);
  }

  /**
   * Test of getRandomNumber method, of class Util.
   */
  @Test
  public void testGetRandomNumber() {
    System.out.println("getRandomNumber");
    int min = 0;
    int max = 12; 
 
    int result = instance.getRandomNumber(min, max);
    assertTrue("Not in range", min <= result && result <= max); 
  }

  /**
   * Test of isEmptyLine method, of class Util.
   */
  @Test
  public void testIsEmptyLine() {
    System.out.println("isEmptyLine");
    String  line = "";  
    boolean result = instance.isEmptyLine(line);
    assertTrue("Not empty line", result);
    
    line = "test a line";
    result = instance.isEmptyLine(line); 
    assertFalse("Is empty line", result);
  }

  /**
   * Test of getMaxCount method, of class Util.
   */
  @Test
  public void testGetMaxCount() {
    System.out.println("getMaxCount");
    
    String[] strings = new String[20]; 
    int result = instance.getMaxCount(strings);
    assertEquals(20, result); 
    
    strings = new String[102];
    
    result = instance.getMaxCount(strings);
    assertEquals(99, result); 
  }
  
}
