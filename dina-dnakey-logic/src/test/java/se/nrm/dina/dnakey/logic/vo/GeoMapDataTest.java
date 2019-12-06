package se.nrm.dina.dnakey.logic.vo;

import org.junit.After; 
import org.junit.Before; 
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author idali
 */
public class GeoMapDataTest {
  
  private GeoMapData instance;
  
  public GeoMapDataTest() {
  }
 
  @Before
  public void setUp() {
    instance = new GeoMapData(58.2d, 18.3d, 18);
  }
  
  @After
  public void tearDown() {
  }

  /**
   * Test of getLatitude method, of class GeoMapData.
   */
  @Test
  public void testGetLatitude() {
    System.out.println("getLatitude");  
    double result = instance.getLatitude();
    assertEquals(58.2d, result, 0.0); 
  }

  /**
   * Test of getLongitude method, of class GeoMapData.
   */
  @Test
  public void testGetLongitude() {
    System.out.println("getLongitude");  
    double result = instance.getLongitude();
    assertEquals(18.3d, result, 0.0); 
  }

  /**
   * Test of getCount method, of class GeoMapData.
   */
  @Test
  public void testGetCount() {
    System.out.println("getCount");  
    int result = instance.getCount();
    assertEquals(18, result); 
  }
  
}
