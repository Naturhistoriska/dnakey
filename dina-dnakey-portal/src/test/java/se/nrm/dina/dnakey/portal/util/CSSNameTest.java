package se.nrm.dina.dnakey.portal.util;

import org.junit.After; 
import org.junit.Before; 
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author idali
 */
public class CSSNameTest {
   
  private CSSName instance;
  
  public CSSNameTest() {
  }
  
  @Before
  public void setUp() {
    instance = CSSName.getInstance();
  }
  
  @After
  public void tearDown() {
    instance = null;
  }

  /**
   * Test of getInstance method, of class CSSName.
   */
  @Test
  public void testGetInstance() {
    System.out.println("getInstance");
    
    instance = null;
    instance = CSSName.getInstance();
    assertNotNull(instance);
  }

  /**
   * Test of getCurrentTab method, of class CSSName.
   */
  @Test
  public void testGetCurrentTab() {
    System.out.println("getCurrentTab");
    
    String expResult = "current";
    String result = instance.getCurrentTab();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getDefaultTab method, of class CSSName.
   */
  @Test
  public void testGetDefaultTab() {
    System.out.println("getDefaultTab");
    
    String expResult = "";
    String result = instance.getDefaultTab();
    assertEquals(expResult, result); 
  } 
}
