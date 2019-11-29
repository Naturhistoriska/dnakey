package se.nrm.dina.dnakey.portal.controller;

import org.junit.After; 
import org.junit.Before; 
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.primefaces.context.RequestContext;
import se.nrm.dina.dnakey.portal.RequestContextMocker;

/**
 *
 * @author idali
 */
public class LanguagesTest {
  
  private Languages instance;
  private final String swedish = "Svenska";
  private final String english = "English";
  
  public LanguagesTest() { 
  } 
  
  @Before
  public void setUp() {
    instance = new Languages();
  }
  
  @After
  public void tearDown() {
    instance = null;
  }

  /**
   * Test of getLocale method, of class Languages.
   */
  @Test
  public void testGetLocale() {
    System.out.println("getLocale"); 
    String expResult = "sv";
    String result = instance.getLocale();
    assertEquals(expResult, result); 
    assertTrue(instance.isIsSwedish());
  }

  /**
   * Test of setLocale method, of class Languages.
   */
  @Test
  public void testSetLocale() {
    System.out.println("setLocale");
    String locale = "en"; 
    instance.setLocale(locale); 
    assertEquals(instance.getLocale(), "en");
    assertFalse(instance.isIsSwedish());
  }

  /**
   * Test of changelanguage method, of class Languages.
   */
  @Test
  public void testChangelanguage() {
    System.out.println("changelanguage");
    
    RequestContext requestContext = RequestContextMocker.mockRequestContext();  
    String locale = "sv"; 
    instance.changelanguage(locale); 
    verify(requestContext, times(0)).update(any(String.class));  
    assertEquals(instance.getLocale(), "sv");
    assertEquals(instance.getLanguage(), english);
    assertTrue(instance.isIsSwedish());
    
    locale = "en"; 
    instance.changelanguage(locale); 
    verify(requestContext, times(5)).update(any(String.class));  
    assertEquals(instance.getLocale(), "en");
    assertEquals(instance.getLanguage(), swedish);
    assertFalse(instance.isIsSwedish()); 
    
  }

  /**
   * Test of getLanguage method, of class Languages.
   */
  @Test
  public void testGetLanguage() {
    System.out.println("getLanguage"); 
     
    String result = instance.getLanguage();
    assertEquals(english, result); 
  }

  /**
   * Test of isIsSwedish method, of class Languages.
   */
  @Test
  public void testIsIsSwedish() {
    System.out.println("isIsSwedish"); 
    boolean expResult = true;
    boolean result = instance.isIsSwedish();
    assertEquals(expResult, result); 
  }
  
}
