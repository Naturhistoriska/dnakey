package se.nrm.dina.dnakey.logic.geomap;

import java.util.List;
import org.junit.After;
import org.junit.Before; 
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import se.nrm.dina.dnakey.logic.config.ConfigProperties;
import se.nrm.dina.dnakey.logic.vo.GeoMapData;

/**
 *
 * @author idali
 */
@RunWith(MockitoJUnitRunner.class) 
public class GeoMapDataSourceTest {
  
  private GeoMapDataSource instance = new GeoMapDataSource();
  
  @Mock
  static ConfigProperties config;
  
  public GeoMapDataSourceTest() {
     
  }
 
  @Before
  public void setUp() {
    instance = new GeoMapDataSource(config);   
  }
  
  @After
  public void tearDown() {
  }

  /**
   * Test of init method, of class GeoMapDataSource.
   */
  @Test
  public void testInit() {
    System.out.println("init"); 
    when(config.getGeoDataFilePath()).thenReturn("src/test/resources/geo_coords.tsv"); 
    instance.init(); 
    
    
  }

  /**
   * Test of getMapKey method, of class GeoMapDataSource.
   */
  @Test
  public void testGetMapKey() {
    System.out.println("getMapKey"); 
    
    when(config.getMapKey()).thenReturn("AbcdEfg"); 
    String expResult = "AbcdEfg";
    String result = instance.getMapKey();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getGeoMapData method, of class GeoMapDataSource.
   */
  @Test
  public void testGetGeoMapData() {
    System.out.println("getGeoMapData"); 
    
    when(config.getGeoDataFilePath()).thenReturn("src/test/resources/geo_coords.tsv"); 
    instance.init();
    List<GeoMapData> result = instance.getGeoMapData();
    System.out.println("result : " + result.size());
    assertNotNull(result);
    assertEquals(736, result.size());
  } 
}
