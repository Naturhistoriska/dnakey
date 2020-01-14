package se.nrm.dina.dnakey.portal.controller;

import java.io.IOException; 
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.junit.After; 
import org.junit.Before; 
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import static org.mockito.Matchers.any;
import org.mockito.Mock; 
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;  
import org.powermock.api.mockito.PowerMockito;
import org.primefaces.context.RequestContext;
import se.nrm.dina.dnakey.portal.ContextMocker;
import se.nrm.dina.dnakey.portal.RequestContextMocker;
import se.nrm.dina.dnakey.portal.beans.StyleBean;

/**
 *
 * @author idali
 */
@RunWith(MockitoJUnitRunner.class)  
public class NavigatorTest {
  
  private Navigator instance;
  private FacesContext context;  
  
  private final String id = "blast";
  
  @Mock
  private ExternalContext externalContext; 
  
  @Mock
  private StyleBean style; 
  @Mock
  private Languages language;
  
  public NavigatorTest() {
  }
    
  @Before
  public void setUp() {
    context = ContextMocker.mockFacesContext(); 
    when(externalContext.getRequestContextPath()).thenReturn("https://dnakey.nrm.se");        
    when(context.getExternalContext()).thenReturn(externalContext);
    instance = new Navigator(style, language);
  }
  
  @After
  public void tearDown() {
    instance = null;
  }
  
  @Test
  public void testDefaultContructor() {
    instance = new Navigator();
    assertNotNull(instance);
  }

  /**
   * Test of home method, of class Navigator.
   * @throws java.io.IOException
   */
  @Test
  public void testHome() throws IOException {
    System.out.println("home"); 
    try {
      instance.home(); 
      verify(style, times(1)).resetTabStyle(0);  
      verify(externalContext, times(1)).redirect(any(String.class));  
    } finally {
      context.release();
    } 
  }
  
 /**
   * Test of home method, of class Navigator.
   * @throws java.io.IOException
   */
  @Test
  public void testHomeCatchException() throws IOException {
    System.out.println("home"); 
    PowerMockito.doThrow(new IOException()).when(externalContext ).redirect(any(String.class)); 
    try { 
      instance.home(); 
      verify(style, times(1)).resetTabStyle(0);   
    } finally {
      context.release();
    } 
  }

  /**
   * Test of dnakey method, of class Navigator.
   * @throws java.io.IOException
   */
  @Test
  public void testDnakey() throws IOException {
    System.out.println("dnakey");  
    try {
      instance.dnakey();
      verify(style, times(1)).resetTabStyle(1);  
      verify(externalContext, times(1)).redirect(any(String.class));  
    } finally {
      context.release();
    }
  }

  /**
   * Test of about method, of class Navigator.
   * @throws java.io.IOException
   */
  @Test
  public void testAbout() throws IOException {
    System.out.println("about");
    try {
      instance.about();
      verify(style, times(1)).resetTabStyle(2);  
      verify(externalContext, times(1)).redirect(any(String.class));  
    } finally {
      context.release();
    } 
  }

  /**
   * Test of externalLinks method, of class Navigator.
   * @throws java.io.IOException
   */
  @Test
  public void testExternalLinks() throws IOException {
    System.out.println("externalLinks");
    try {
      instance.externalLinks();
      verify(style, times(1)).resetTabStyle(3);  
      verify(externalContext, times(1)).redirect(any(String.class));  
    } finally {
      context.release();
    }  
  }

  /**
   * Test of contact method, of class Navigator.
   * @throws java.io.IOException
   */
  @Test
  public void testContact() throws IOException {
    System.out.println("contact");
    try {
      instance.contact();
      verify(style, times(1)).resetTabStyle(4);  
      verify(externalContext, times(1)).redirect(any(String.class));  
    } finally {
      context.release();
    }   
  }

  /**
   * Test of result method, of class Navigator.
   * @throws java.io.IOException
   */
  @Test
  public void testResult() throws IOException {
    System.out.println("result");
    try {
      instance.result();
      verify(style, times(1)).resetTabStyle(0);  
      verify(externalContext, times(1)).redirect(any(String.class));  
    } finally {
      context.release();
    }   
  }

  /**
   * Test of scrollToSection method, of class Navigator.
   */
  @Test
  public void testScrollToSection() {
    System.out.println("scrollToSection");
    
    RequestContext requestContext = RequestContextMocker.mockRequestContext();  
    instance.scrollToSection(); 
    verify(requestContext, times(0)).scrollTo(any(String.class)); 
    
    instance.scrollToClientId(id);
    instance.scrollToSection();   
    verify(requestContext, times(1)).scrollTo(id);
  }

  /**
   * Test of scrollToClientId method, of class Navigator.
   * @throws java.io.IOException
   */
  @Test
  public void testScrollToClientId() throws IOException {
    System.out.println("scrollToClientId");
    
    RequestContext requestContext = RequestContextMocker.mockRequestContext();  
    instance.scrollToClientId(id);  
    verify(requestContext, times(1)).update(any(String.class));
    verify(externalContext, times(1)).redirect(any(String.class));  
  }

  /**
   * Test of resetTopMenuCSS method, of class Navigator.
   */
  @Test
  public void testResetTopMenuCSS() {
    System.out.println("resetTopMenuCSS");
    RequestContext requestContext = RequestContextMocker.mockRequestContext();  
    int tabIndex = 0; 
    instance.resetTopMenuCSS(tabIndex); 
    verify(style, times(1)).resetTabStyle(0);  
    verify(requestContext, times(1)).update(any(String.class));
  }

  /**
   * Test of getDnakeyIncludePage method, of class Navigator.
   */
  @Test
  public void testGetDnakeyIncludePage() {
    System.out.println("getDnakeyIncludePage"); 
  
    when(language.isIsSwedish()).thenReturn(true);     
    String expResult = "/pages/dnakeysv.xhtml";
    String result = instance.getDnakeyIncludePage();
    assertEquals(expResult, result); 
    
    when(language.isIsSwedish()).thenReturn(false);   
    expResult = "/pages/dnakeyen.xhtml";
    result = instance.getDnakeyIncludePage();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getAboutIncludePage method, of class Navigator.
   */
  @Test
  public void testGetAboutIncludePage() {
    System.out.println("getAboutIncludePage");
    when(language.isIsSwedish()).thenReturn(true);     
    String expResult = "/pages/aboutsv.xhtml";
    String result = instance.getAboutIncludePage();
    assertEquals(expResult, result); 
    
    when(language.isIsSwedish()).thenReturn(false);   
    expResult = "/pages/abouten.xhtml";
    result = instance.getAboutIncludePage();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getAlignmentView method, of class Navigator.
   */
  @Test
  public void testGetAlignmentView() {
    System.out.println("getAlignmentView");
         
    String expResult = "/pages/alignment.xhtml";
    String result = instance.getAlignmentView();
    assertEquals(expResult, result);  
  }

  /**
   * Test of getMapView method, of class Navigator.
   */
  @Test
  public void testGetMapView() {
    System.out.println("getMapView");
    
    String expResult = "/pages/mapview.xhtml";
    String result = instance.getMapView();
    assertEquals(expResult, result);    
  }

  /**
   * Test of getImageView method, of class Navigator.
   */
  @Test
  public void testGetImageView() {
    System.out.println("getImageView"); 
    
    String expResult = "/pages/imageview.xhtml";
    String result = instance.getImageView();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getExport method, of class Navigator.
   */
  @Test
  public void testGetExport() {
    System.out.println("getExport"); 
    String expResult = "/pages/export.xhtml";
    String result = instance.getExport();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getStatistic method, of class Navigator.
   */
  @Test
  public void testGetStatistic() {
    System.out.println("getStatistic"); 
    
    String expResult = "/pages/statistic.xhtml";
    String result = instance.getStatistic();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getNoHits method, of class Navigator.
   */
  @Test
  public void testGetNoHits() {
    System.out.println("getNoHits"); 
    String expResult = "/pages/nohits.xhtml";
    String result = instance.getNoHits();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getNoHighMatch method, of class Navigator.
   */
  @Test
  public void testGetNoHighMatch() {
    System.out.println("getNoHighMatch"); 
    String expResult = "/pages/nohighmatch.xhtml";
    String result = instance.getNoHighMatch();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getHighHits method, of class Navigator.
   */
  @Test
  public void testGetHighHits() {
    System.out.println("getHighHits"); 
    String expResult = "/pages/highhits.xhtml";
    String result = instance.getHighHits();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getLowHits method, of class Navigator.
   */
  @Test
  public void testGetLowHits() {
    System.out.println("getLowHits"); 
    String expResult = "/pages/lowhits.xhtml";
    String result = instance.getLowHits();
    assertEquals(expResult, result); 
  } 
}
