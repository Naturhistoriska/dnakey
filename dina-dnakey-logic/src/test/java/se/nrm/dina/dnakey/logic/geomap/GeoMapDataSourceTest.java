package se.nrm.dina.dnakey.logic.geomap;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import static org.mockito.Matchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import se.nrm.dina.dnakey.logic.config.ConfigProperties;
import se.nrm.dina.dnakey.logic.vo.GeoMapData;

/**
 *
 * @author idali
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({GeoMapData.class, Files.class, Paths.class})
@PowerMockIgnore({"javax.management.*", "org.apache.http.conn.ssl.*", "com.amazonaws.http.conn.ssl.*", "javax.net.ssl.*"})
public class GeoMapDataSourceTest {
     
  private GeoMapDataSource instance;
  private String mapKey;
  private String filePath;
 
  private ConfigProperties config;

  public GeoMapDataSourceTest() {

  }

  @Before
  public void setUp() {
    mapKey = "APasdfslf";
    filePath = "src/test/resources/geo_coords.tsv";
     
    config = PowerMockito.mock(ConfigProperties.class); 
    PowerMockito.when(config.getGeoDataFilePath()).thenReturn(filePath);
    instance = new GeoMapDataSource(config);
  }

  @After
  public void tearDown() {
    instance = null;
  }
  
  @Test
  public void testDefaultConstructor() {
    instance = new GeoMapDataSource();
    assertNotNull(instance);
  }

  /**
   * Test of init method, of class GeoMapDataSource.
   * @throws java.io.IOException
   */
  @Test
  public void testInit() throws IOException {
    System.out.println("init");
   

    PowerMockito.mockStatic(Files.class);
    PowerMockito.mockStatic(Paths.class);

    Path path = Paths.get(filePath);
    when(Paths.get(filePath)).thenReturn(path);
      
    PowerMockito.when(Files.lines(path))
            .then(i -> Stream.of("AGGADAA\nfsadfasdf\n", "AGGADAA")); 

    instance.init();
    List<GeoMapData> result = instance.getGeoMapData();
    assertNotNull(result);
    assertEquals(736, result.size()); 
  }
  
  @Test
  public void testInitWithException() throws IOException {
    System.out.println("init");
    
    PowerMockito.mockStatic(Files.class);
    PowerMockito.mockStatic(Paths.class);

    Path path = Paths.get(filePath);
    when(Paths.get(filePath)).thenReturn(path);
    when(Files.lines(path)).thenThrow(new IOException());

    instance.init();  
  }

  /**
   * Test of getMapKey method, of class GeoMapDataSource.
   */
  @Test
  public void testGetMapKey() {
    System.out.println("getMapKey");

    when(config.getMapKey()).thenReturn(mapKey);
    String result = instance.getMapKey();
    assertEquals(mapKey, result);
  }

  /**
   * Test of getGeoMapData method, of class GeoMapDataSource.
   */
  @Test
  public void testGetGeoMapData() {
    System.out.println("getGeoMapData"); 
 
    instance.init();
    List<GeoMapData> result = instance.getGeoMapData(); 
    assertNotNull(result);
    assertEquals(736, result.size());
  }
}
