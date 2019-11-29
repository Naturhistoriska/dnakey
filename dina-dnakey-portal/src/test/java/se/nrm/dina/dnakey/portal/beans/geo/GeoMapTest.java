package se.nrm.dina.dnakey.portal.beans.geo;
 
import java.util.ArrayList;
import java.util.List;
import org.junit.After; 
import org.junit.Before; 
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import se.nrm.dina.dnakey.logic.geomap.GeoMapDataSource;
import se.nrm.dina.dnakey.logic.vo.GeoMapData;

/**
 *
 * @author idali
 */
@RunWith(MockitoJUnitRunner.class) 
public class GeoMapTest {
  
  private GeoMap instance;
  private GeoMapData data;
  private List<GeoMapData> geoMap;
  
  @Mock
  private GeoMapDataSource geo;
  
  public GeoMapTest() {
  }
 
  @Before
  public void setUp() { 
    geoMap = new ArrayList<>();
    data = new GeoMapData(56.2, 18.3, 20);
    geoMap.add(data);
     
    instance = new GeoMap(geo);
    when(geo.getGeoMapData()).thenReturn(geoMap);
  }
  
  @After
  public void tearDown() {
    instance = null;
  }

  /**
   * Test of init method, of class GeoMap.
   */
  @Test
  public void testInit() {
    System.out.println("init"); 
    instance.init(); 
     
    assertNotNull(instance.getGeoMap());
    assertEquals(instance.getGeoMap(), geoMap); 
  }

  /**
   * Test of getGeoMap method, of class GeoMap.
   */
  @Test
  public void testGetGeoMap() {
    System.out.println("getGeoMap");  
     
    instance.init(); 
    List<GeoMapData> result = instance.getGeoMap();
    assertNotNull(result);
  }

  /**
   * Test of getMapKey method, of class GeoMap.
   */
  @Test
  public void testGetMapKey() {
    System.out.println("getMapKey");  
    
    String mapKey = "abdcadsfas";
    when(geo.getMapKey()).thenReturn(mapKey);
    
    String result = instance.getMapKey();
    assertEquals(result, mapKey); 
  } 
}
