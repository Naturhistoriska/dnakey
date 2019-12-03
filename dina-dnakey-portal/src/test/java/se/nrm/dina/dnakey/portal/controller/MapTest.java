package se.nrm.dina.dnakey.portal.controller;

import java.util.ArrayList;
import java.util.List;
import org.junit.After; 
import org.junit.Before; 
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import se.nrm.dina.dnakey.logic.metadata.BlastSubjectHsp;
import se.nrm.dina.dnakey.logic.metadata.BlastSubjectMetadata;
import se.nrm.dina.dnakey.logic.vo.NrmData;

/**
 *
 * @author idali
 */
@RunWith(MockitoJUnitRunner.class)  
public class MapTest {
   
  private final String MAP_MARK_PATH = "http://maps.google.com/mapfiles/ms/micons/blue-dot.png";
  private Map instance;
  private final String locality = "123 Tyreso";
  private final String country = "Sweden";
  private final String continent = "Europe";
  private final String catalogNumber = "cat1223";
  private final String taxon = "taxon";
  private final String coordinates = "56N18E"; 
          
  public MapTest() {
  }
    
  @Before
  public void setUp() {
    instance = new Map();
  }
  
  @After
  public void tearDown() {
    instance = null;
  }

  /**
   * Test of getSingleMap method, of class Map.
   */
  @Test
  public void testGetSingleMapNorth() {
    System.out.println("getSingleMap");
    List<BlastSubjectHsp> subjectHspList = new ArrayList<>();
    BlastSubjectMetadata subMetada = new BlastSubjectMetadata(
            1, "gen111", "gen222", "bold123", "target", "56_N_18_E", catalogNumber, taxon,  
            10, subjectHspList, true); 
    instance.getSingleMap(subMetada); 
    
    MapModel model = instance.getAdvancedModel();
    assertNotNull(model); 
    assertEquals(model.getMarkers().size(), 1);
    assertEquals(model.getMarkers().get(0).getIcon(), MAP_MARK_PATH);
  }
  
  
  /**
   * Test of getSingleMap method, of class Map.
   */
  @Test
  public void testGetSingleMapSouth() {
    System.out.println("getSingleMap");
    List<BlastSubjectHsp> subjectHspList = new ArrayList<>();
    BlastSubjectMetadata subMetada = new BlastSubjectMetadata(
            1, "gen111", "gen222", "bold123", "target", "5_S_18_E", catalogNumber, taxon,  
            10, subjectHspList, true); 
    instance.getSingleMap(subMetada); 
    
    MapModel model = instance.getAdvancedModel();
    assertNotNull(model); 
    assertEquals(model.getMarkers().size(), 1);
    assertEquals(model.getMarkers().get(0).getIcon(), MAP_MARK_PATH);
  }
   
  /**
   * Test of getSingleMap method, of class Map.
   */
  @Test
  public void testGetSingleMapWest() {
    System.out.println("getSingleMap");
    List<BlastSubjectHsp> subjectHspList = new ArrayList<>();
    BlastSubjectMetadata subMetada = new BlastSubjectMetadata(
            1, "gen111", "gen222", "bold123", "target", "56_N_18_W", catalogNumber, taxon,  
            10, subjectHspList, true); 
    instance.getSingleMap(subMetada); 
    
    MapModel model = instance.getAdvancedModel();
    assertNotNull(model); 
    assertEquals(model.getMarkers().size(), 1);
    assertEquals(model.getMarkers().get(0).getIcon(), MAP_MARK_PATH);
  }
  
  /**
   * Test of getSingleMap method, of class Map.
   */
  @Test
  public void testGetSingleMapWhitNrmData() {
    System.out.println("getSingleMap");
    List<BlastSubjectHsp> subjectHspList = new ArrayList<>();
    BlastSubjectMetadata subMetada = new BlastSubjectMetadata(
            1, "gen111", "gen222", "bold123", "target", "56_N_18_W", catalogNumber, taxon,  
            10, subjectHspList, true); 
    
    NrmData data = mock(NrmData.class);
    subMetada.setNrmData(data);
    instance.getSingleMap(subMetada); 
    
    MapModel model = instance.getAdvancedModel();
    assertNotNull(model); 
    assertEquals(model.getMarkers().size(), 1);
    assertEquals(model.getMarkers().get(0).getIcon(), MAP_MARK_PATH);
  }
  
    /**
   * Test of getSingleMap method, of class Map.
   */
  @Test
  public void testGetSingleMapWhitInvalidCoordinateSW() {
    System.out.println("getSingleMap");
    List<BlastSubjectHsp> subjectHspList = new ArrayList<>();
    BlastSubjectMetadata subMetada = new BlastSubjectMetadata(
            1, "gen111", "gen222", "bold123", "target", "56s_S_18w_W", catalogNumber, taxon,  
            10, subjectHspList, true); 
    
    NrmData data = mock(NrmData.class);
    subMetada.setNrmData(data);
    instance.getSingleMap(subMetada); 
    
    MapModel model = instance.getAdvancedModel();
    assertNotNull(model); 
    assertEquals(model.getMarkers().size(), 1);
    assertEquals(model.getMarkers().get(0).getIcon(), MAP_MARK_PATH);
  }
  
  /**
   * Test of getSingleMap method, of class Map.
   */
  @Test
  public void testGetSingleMapWhitInvalidCoordinateNE() {
    System.out.println("getSingleMap");
    List<BlastSubjectHsp> subjectHspList = new ArrayList<>();
    BlastSubjectMetadata subMetada = new BlastSubjectMetadata(
            1, "gen111", "gen222", "bold123", "target", "56n_N_18e_E", catalogNumber, taxon,  
            10, subjectHspList, true); 
    
    NrmData data = mock(NrmData.class);
    subMetada.setNrmData(data);
    instance.getSingleMap(subMetada); 
    
    MapModel model = instance.getAdvancedModel();
    assertNotNull(model); 
    assertEquals(model.getMarkers().size(), 1);
    assertEquals(model.getMarkers().get(0).getIcon(), MAP_MARK_PATH);
  }


  /**
   * Test of onMarkerSelect method, of class Map.
   */
  @Test
  public void testOnMarkerSelect() {
    System.out.println("onMarkerSelect");
    OverlaySelectEvent event = mock(OverlaySelectEvent.class);
    Marker marker = mock(Marker.class);
    
    NrmData data = new NrmData(catalogNumber, taxon, "collection1", "commonName", locality, 
            coordinates, null, null, true, null, "21", country, continent);
    
    when(event.getOverlay()).thenReturn(marker); 
    when(marker.getData()).thenReturn(data);
    instance.onMarkerSelect(event); 
    assertEquals(instance.getLocality(), locality);
  }
  
    /**
   * Test of onMarkerSelect method, of class Map.
   */
  @Test
  public void testOnMarkerSelectWithoutData() {
    System.out.println("onMarkerSelect");
    OverlaySelectEvent event = mock(OverlaySelectEvent.class);
    Marker marker = mock(Marker.class);
     
    
    when(event.getOverlay()).thenReturn(marker); 
    when(marker.getData()).thenReturn(null);
    instance.onMarkerSelect(event); 
    assertTrue(instance.getLocality() == null);
  }

  /**
   * Test of getLocality method, of class Map.
   */
  @Test
  public void testGetLocality() {
    System.out.println("getLocality");  
    
    instance.setLocality(locality);
    String result = instance.getLocality();
    assertEquals(locality, result); 
  }

  /**
   * Test of setLocality method, of class Map.
   */
  @Test
  public void testSetLocality() {
    System.out.println("setLocality");  
    instance.setLocality(locality); 
    assertEquals(instance.getLocality(), locality); 
  }

  /**
   * Test of getCountry method, of class Map.
   */
  @Test
  public void testGetCountry() {
    System.out.println("getCountry"); 
 
    instance.setCountry(country);
    String result = instance.getCountry();
    assertEquals(country, result); 
  }

  /**
   * Test of setCountry method, of class Map.
   */
  @Test
  public void testSetCountry() {
    System.out.println("setCountry");
 
    instance.setCountry(country); 
    assertEquals(country, instance.getCountry());
  }

  /**
   * Test of getContinent method, of class Map.
   */
  @Test
  public void testGetContinent() {
    System.out.println("getContinent"); 
 
    instance.setContinent(continent);
    String result = instance.getContinent();
    assertEquals(continent, result); 
  }

  /**
   * Test of setContinent method, of class Map.
   */
  @Test
  public void testSetContinent() {
    System.out.println("setContinent");
    instance.setContinent(continent); 
    assertEquals(continent, instance.getContinent());
  }

  /**
   * Test of getCatalogNumber method, of class Map.
   */
  @Test
  public void testGetCatalogNumber() {
    System.out.println("getCatalogNumber");
 
    instance.setCatalogNumber(catalogNumber);
    String result = instance.getCatalogNumber();
    assertEquals(catalogNumber, result); 
  }

  /**
   * Test of setCatalogNumber method, of class Map.
   */
  @Test
  public void testSetCatalogNumber() {
    System.out.println("setCatalogNumber");
    instance.setCatalogNumber(catalogNumber); 
    assertEquals(catalogNumber, instance.getCatalogNumber());
  }

  /**
   * Test of getTaxonName method, of class Map.
   */
  @Test
  public void testGetTaxonName() {
    System.out.println("getTaxonName");
    instance.setTaxonName(taxon);
    String result = instance.getTaxonName();
    assertEquals(taxon, result); 
  }

  /**
   * Test of setTaxonName method, of class Map.
   */
  @Test
  public void testSetTaxonName() {
    System.out.println("setTaxonName");
    instance.setTaxonName(taxon); 
    assertEquals(taxon, instance.getTaxonName());
  }

  /**
   * Test of isHasData method, of class Map.
   */
  @Test
  public void testIsHasData() {
    System.out.println("isHasData");
 
    instance.setHasData(true);
    boolean result = instance.isHasData();
    assertEquals(true, result); 
  }

  /**
   * Test of setHasData method, of class Map.
   */
  @Test
  public void testSetHasData() {
    System.out.println("setHasData");
    boolean hasData = false; 
    instance.setHasData(hasData); 
    assertEquals(false, instance.isHasData()); 
  }

  /**
   * Test of getCoordinates method, of class Map.
   */
  @Test
  public void testGetCoordinates() {
    System.out.println("getCoordinates");
  
    instance.setCoordinates(coordinates);
    String result = instance.getCoordinates();
    assertEquals(coordinates, result); 
  }

  /**
   * Test of setCoordinates method, of class Map.
   */
  @Test
  public void testSetCoordinates() {
    System.out.println("setCoordinates");
 
    instance.setCoordinates(coordinates); 
    assertEquals(coordinates, instance.getCoordinates());
  }

  /**
   * Test of getNameAndCatalogNumber method, of class Map.
   */
  @Test
  public void testGetNameAndCatalogNumber() {
    System.out.println("getNameAndCatalogNumber");
 
    instance.setNameAndCatalogNumber(catalogNumber);
    String result = instance.getNameAndCatalogNumber();
    assertEquals(catalogNumber, result); 
  }

  /**
   * Test of setNameAndCatalogNumber method, of class Map.
   */
  @Test
  public void testSetNameAndCatalogNumber() {
    System.out.println("setNameAndCatalogNumber"); 
    instance.setNameAndCatalogNumber(catalogNumber); 
    assertEquals(catalogNumber, instance.getNameAndCatalogNumber());
  }

  /**
   * Test of getAdvancedModel method, of class Map.
   */
  @Test
  public void testGetAdvancedModel() {
    System.out.println("getAdvancedModel");  
    MapModel advancedModel = new DefaultMapModel(); 
    instance.setAdvancedModel(advancedModel); 
    MapModel result = instance.getAdvancedModel();
    assertNotNull(result); 
  }

  /**
   * Test of setAdvancedModel method, of class Map.
   */
  @Test
  public void testSetAdvancedModel() {
    System.out.println("setAdvancedModel");
    MapModel advancedModel = new DefaultMapModel(); 
    instance.setAdvancedModel(advancedModel); 
    assertNotNull(instance.getAdvancedModel());
  }

  /**
   * Test of getCentLat method, of class Map.
   */
  @Test
  public void testGetCentLat() {
    System.out.println("getCentLat"); 
    double expResult = 50.2;
    instance.setCentLat(expResult);
    double result = instance.getCentLat();
    assertEquals(expResult, result, 50.2); 
  }

  /**
   * Test of setCentLat method, of class Map.
   */
  @Test
  public void testSetCentLat() {
    System.out.println("setCentLat");
    double centLat = 50.2; 
    instance.setCentLat(centLat); 
    assertEquals(instance.getCentLat(), centLat, 50.2); 
  }

  /**
   * Test of getCentLnt method, of class Map.
   */
  @Test
  public void testGetCentLnt() {
    System.out.println("getCentLnt"); 
    double expResult = 18.2;
    double cenLnt = 18.2; 
    instance.setCentLnt(cenLnt); 
    double result = instance.getCentLnt();
    assertEquals(expResult, result, 0.0); 
  }

  /**
   * Test of setCentLnt method, of class Map.
   */
  @Test
  public void testSetCentLnt() {
    System.out.println("setCentLnt");
    double cenLnt = 18.2; 
    instance.setCentLnt(cenLnt);
    assertEquals(instance.getCentLnt(), cenLnt, 50.2); 
  }

  /**
   * Test of getZoom method, of class Map.
   */
  @Test
  public void testGetZoom() {
    System.out.println("getZoom"); 
    int expResult = 6;
    int result = instance.getZoom();
    assertEquals(expResult, result); 
  }

  /**
   * Test of setZoom method, of class Map.
   */
  @Test
  public void testSetZoom() {
    System.out.println("setZoom");
    int zoom = 0; 
    instance.setZoom(zoom); 
    assertEquals(0, instance.getZoom());
  }
  
}
