package se.nrm.dina.dnakey.portal.beans;

import org.junit.After; 
import org.junit.Before; 
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author idali
 */
public class StyleBeanTest {
  
  private StyleBean instance;
  
  public StyleBeanTest() {
  }
 
  @Before
  public void setUp() {
    instance = new StyleBean();
  }
  
  @After
  public void tearDown() {
  }

  /**
   * Test of getEnbtn method, of class StyleBean.
   */
  @Test
  public void testGetEnbtn() {
    System.out.println("getEnbtn"); 
    String expResult = "English"; 
    instance.setEnbtn("English");
    String result = instance.getEnbtn(); 
    assertEquals(expResult, result); 
  }

  /**
   * Test of setEnbtn method, of class StyleBean.
   */
  @Test
  public void testSetEnbtn() {
    System.out.println("setEnbtn");
    String enbtn = "English"; 
    instance.setEnbtn(enbtn); 
    
    assertEquals(enbtn, instance.getEnbtn());
  }

  /**
   * Test of getSvbtn method, of class StyleBean.
   */
  @Test
  public void testGetSvbtn() {
    System.out.println("getSvbtn"); 
    
    String expResult = "Swedish";
    instance.setSvbtn("Swedish");
    String result = instance.getSvbtn();
    assertEquals(expResult, result); 
  }

  /**
   * Test of setSvbtn method, of class StyleBean.
   */
  @Test
  public void testSetSvbtn() {
    System.out.println("setSvbtn");
    String svbtn = "Swedish"; 
    instance.setSvbtn(svbtn); 
    
    assertEquals(svbtn, instance.getSvbtn());
  }

  /**
   * Test of getTab1 method, of class StyleBean.
   */
  @Test
  public void testGetTab1() {
    System.out.println("getTab1"); 
    String expResult = "current";
    
    instance.resetTabStyle(0);
    String result = instance.getTab1(); 
    assertEquals(expResult, result); 
    
    instance.resetTabStyle(1);
    
    expResult = "";
    result = instance.getTab1(); 
    assertEquals(expResult, result); 
  }

  /**
   * Test of setTab1 method, of class StyleBean.
   */
  @Test
  public void testSetTab1() {
    System.out.println("setTab1");
    String tab1 = "current"; 
    instance.setTab1(tab1); 
    assertEquals("current", instance.getTab1());
  }

  /**
   * Test of getTab2 method, of class StyleBean.
   */
  @Test
  public void testGetTab2() {
    System.out.println("getTab2"); 
    String expResult = "current";
    instance.resetTabStyle(1);
    String result = instance.getTab2();
    assertEquals(expResult, result); 
    
    expResult = "";
    instance.resetTabStyle(0);
    result = instance.getTab2();
    assertEquals(expResult, result);  
  }

  /**
   * Test of setTab2 method, of class StyleBean.
   */
  @Test
  public void testSetTab2() {
    System.out.println("setTab2");
    String tab2 = "current"; 
    instance.setTab2(tab2); 
    
    assertEquals(tab2, instance.getTab2());
  }

  /**
   * Test of getTab3 method, of class StyleBean.
   */
  @Test
  public void testGetTab3() {
    System.out.println("getTab3"); 
    
    String expResult = "current";
    instance.resetTabStyle(2);
    String result = instance.getTab3();
    assertEquals(expResult, result); 
    
    expResult = "";
    instance.resetTabStyle(0);
    result = instance.getTab3();
    assertEquals(expResult, result);  
  }

  /**
   * Test of setTab3 method, of class StyleBean.
   */
  @Test
  public void testSetTab3() {
    System.out.println("setTab3");
    String tab3 = "current"; 
    instance.setTab3(tab3); 
    
    assertEquals(tab3, instance.getTab3());
  }

  /**
   * Test of getTab4 method, of class StyleBean.
   */
  @Test
  public void testGetTab4() {
    System.out.println("getTab4");
    
    String expResult = "current";
    instance.resetTabStyle(3);
    String result = instance.getTab4();
    assertEquals(expResult, result); 
    
    expResult = "";
    instance.resetTabStyle(0);
    result = instance.getTab4();
    assertEquals(expResult, result);  
  }

  /**
   * Test of setTab4 method, of class StyleBean.
   */
  @Test
  public void testSetTab4() {
    System.out.println("setTab4");
    String tab4 = "current"; 
    instance.setTab4(tab4); 
    
    assertEquals(tab4, instance.getTab4());
  }

  /**
   * Test of resetTabStyle method, of class StyleBean.
   */
  @Test
  public void testResetTabStyle() {
    System.out.println("resetTabStyle");
    int tabIndex = 0; 
    instance.resetTabStyle(tabIndex);  
    assertEquals("current", instance.getTab1());
    assertEquals("", instance.getTab2());
    
    tabIndex = 4;
    instance.resetTabStyle(tabIndex);  
    assertEquals("", instance.getTab1());
    assertEquals("", instance.getTab2());
    assertEquals("", instance.getTab3());
    assertEquals("", instance.getTab4());
    
    
    tabIndex = 5;
    instance.resetTabStyle(tabIndex);  
    assertEquals("current", instance.getTab1());
    assertEquals("", instance.getTab2());
    assertEquals("", instance.getTab3());
    assertEquals("", instance.getTab4());
  } 
} 