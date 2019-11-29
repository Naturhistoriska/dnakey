package se.nrm.dina.dnakey.portal.beans;

import org.junit.After; 
import org.junit.Before; 
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author idali
 */
public class ImageBeansTest {
  
  private ImageBeans instance;
  
  public ImageBeansTest() {
  }
  
  @Before
  public void setUp() {
    instance = new ImageBeans(); 
  }
  
  @After
  public void tearDown() {
    instance = null;
  }

  /**
   * Test of getImage method, of class ImageBeans.
   */
  @Test
  public void testGetImage() {
    System.out.println("getImage");  
    String result = instance.getImage();
    assertNotNull(result); 
    assertTrue(result.contains("/resources/images/randomImages"));
  }
  
}
