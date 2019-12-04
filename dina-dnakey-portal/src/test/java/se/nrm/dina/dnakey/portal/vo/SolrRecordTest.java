package se.nrm.dina.dnakey.portal.vo;
 
import java.text.ParseException;
import java.text.SimpleDateFormat; 
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author idali
 */
public class SolrRecordTest {
  
  private static SolrRecord instance;
  private static String catalogNumber;
  private static String collectionName; 
  private static String[] commonNames;
  private static String[] collectors;
  private static String continent;
  private static String country;
  private static double lat;
  private static double lng;
  private static boolean map;
  private static boolean image;
  private static String id;
  private static String taxon;
  private static Date startDate;
  private static String locality;
  private static String latitudeText; 
  private static String longitudeText;
  private static String morphbankId;
  private static String[] morphbankImageId;
  
  public SolrRecordTest() {
  }
  
  @BeforeClass
  public static void setUpClass() throws ParseException {
    commonNames = new String[1];
    commonNames[0] = "myro";
    
    collectors = new String[1];
    collectors[0] = "John Doe";
    
    morphbankImageId = new String[2];
    morphbankImageId[0] = "3";
    morphbankImageId[1] = "8";
    
    catalogNumber = "cat123";
    collectionName = "first collection";
    continent = "Europe";
    country = "Sweden";
    lat = 58.2d;
    lng = 18.5d;
    image = true;
    map = true;
    id = "123";
    taxon = "Afrobalta testacea";
    String strDate = "2000-02-02";
    startDate = new SimpleDateFormat("yyyy-MM-dd").parse(strDate);   
    locality = "Tyreso, Sweden";
    latitudeText = "58N";
    longitudeText = "12E";
    
    morphbankId = "18";
    
    instance = new SolrRecord();
    instance.setCatalogNumber(catalogNumber);
    instance.setCollectionName(collectionName);
    instance.setCollector(collectors);
    instance.setCommonName(commonNames);
    instance.setContinent(continent);
    instance.setCountry(country);
    instance.setImage(image);
    instance.setLatitude(lat);
    instance.setLongitude(lng);
    instance.setMap(map); 
    instance.setId(id);
    instance.setTxFullName(taxon);
    instance.setStartDate(startDate);
    instance.setLocality(locality);
    instance.setMorphbankId(morphbankId);
    instance.setLatitudeText(latitudeText); 
    instance.setLongitudeText(longitudeText);
    instance.setMorphbankImageId(morphbankImageId);
  }
  
  @AfterClass
  public static void tearDownClass() {
    instance = null;
  }
  
  @Before
  public void setUp() {
  }
  
  @After
  public void tearDown() {
  }

  /**
   * Test of getCollectionName method, of class SolrRecord.
   */
  @Test
  public void testGetCollectionName() {
    System.out.println("getCollectionName");  
    String result = instance.getCollectionName();
    assertEquals(collectionName, result); 
  }

  /**
   * Test of setCollectionName method, of class SolrRecord.
   */
  @Test
  public void testSetCollectionName() {
    System.out.println("setCollectionName"); 
    instance.setCollectionName(collectionName); 
    assertEquals(collectionName, instance.getCollectionName());
  }

  /**
   * Test of getCatalogNumber method, of class SolrRecord.
   */
  @Test
  public void testGetCatalogNumber() {
    System.out.println("getCatalogNumber");  
    String result = instance.getCatalogNumber();
    assertEquals(catalogNumber, result); 
  }

  /**
   * Test of setCatalogNumber method, of class SolrRecord.
   */
  @Test
  public void testSetCatalogNumber() {
    System.out.println("setCatalogNumber"); 
    instance.setCatalogNumber(catalogNumber); 
    assertEquals(catalogNumber, instance.getCatalogNumber());
  }

  /**
   * Test of getId method, of class SolrRecord.
   */
  @Test
  public void testGetId() {
    System.out.println("getId");  
    String result = instance.getId();
    assertEquals(id, result); 
  }

  /**
   * Test of getTxFullName method, of class SolrRecord.
   */
  @Test
  public void testGetTxFullName() {
    System.out.println("getTxFullName");  
    String result = instance.getTxFullName();
    assertEquals(taxon, result); 
  }

  /**
   * Test of getStartDate method, of class SolrRecord.
   */
  @Test
  public void testGetStartDate() {
    System.out.println("getStartDate");  
    Date result = instance.getStartDate();
    assertNotNull(result);  
  }

  /**
   * Test of getLocality method, of class SolrRecord.
   */
  @Test
  public void testGetLocality() {
    System.out.println("getLocality");  
    String result = instance.getLocality();
    assertEquals(locality, result); 
  }

  /**
   * Test of getLatitudeText method, of class SolrRecord.
   */
  @Test
  public void testGetLatitudeText() {
    System.out.println("getLatitudeText"); 
    String result = instance.getLatitudeText();
    assertEquals(latitudeText, result); 
  }

  /**
   * Test of getLongitudeText method, of class SolrRecord.
   */
  @Test
  public void testGetLongitudeText() {
    System.out.println("getLongitudeText"); 
    String result = instance.getLongitudeText();
    assertEquals(longitudeText, result); 
  }

  /**
   * Test of getLatitude method, of class SolrRecord.
   */
  @Test
  public void testGetLatitude() {
    System.out.println("getLatitude"); 
    double result = instance.getLatitude();
    assertEquals(lat, result, 0.0); 
  }

  /**
   * Test of setLatitude method, of class SolrRecord.
   */
  @Test
  public void testSetLatitude() {
    System.out.println("setLatitude"); 
    instance.setLatitude(lat); 
    assertEquals(lat, instance.getLatitude(), 0.0); 
  }

  /**
   * Test of getLongitude method, of class SolrRecord.
   */
  @Test
  public void testGetLongitude() {
    System.out.println("getLongitude"); 
    double result = instance.getLongitude();
    assertEquals(lng, result, 0.0); 
  }

  /**
   * Test of setLongitude method, of class SolrRecord.
   */
  @Test
  public void testSetLongitude() {
    System.out.println("setLongitude"); 
    instance.setLongitude(lng); 
    assertEquals(lng, instance.getLongitude(), 0.0); 
  }

  /**
   * Test of getMorphbankId method, of class SolrRecord.
   */
  @Test
  public void testGetMorphbankId() {
    System.out.println("getMorphbankId");  
    String result = instance.getMorphbankId();
    assertEquals(morphbankId, result); 
  }

  /**
   * Test of getMorphbankImageId method, of class SolrRecord.
   */
  @Test
  public void testGetMorphbankImageId() {
    System.out.println("getMorphbankImageId"); 
    String[] result = instance.getMorphbankImageId();
    assertArrayEquals(morphbankImageId, result); 
    assertEquals(result.length, 2);
  }

  /**
   * Test of getCommonName method, of class SolrRecord.
   */
  @Test
  public void testGetCommonName() {
    System.out.println("getCommonName"); 
    String[] result = instance.getCommonName();
    assertArrayEquals(commonNames, result); 
  }

  /**
   * Test of setCommonName method, of class SolrRecord.
   */
  @Test
  public void testSetCommonName() {
    System.out.println("setCommonName"); 
    instance.setCommonName(commonNames); 
    assertArrayEquals(commonNames, instance.getCommonName()); 
  }

  /**
   * Test of getCollector method, of class SolrRecord.
   */
  @Test
  public void testGetCollector() {
    System.out.println("getCollector");  
    String[] result = instance.getCollector();
    assertArrayEquals(collectors, result); 
  }

  /**
   * Test of setCollector method, of class SolrRecord.
   */
  @Test
  public void testSetCollector() {
    System.out.println("setCollector"); 
    instance.setCollector(collectors); 
    assertArrayEquals(collectors, instance.getCollector()); 
  }

  /**
   * Test of getCollectors method, of class SolrRecord.
   */
  @Test
  public void testGetCollectors() {
    System.out.println("getCollectors"); 
    String result = instance.getCollectors();
    assertEquals("John Doe", result); 
  }

  /**
   * Test of getCommonNames method, of class SolrRecord.
   */
  @Test
  public void testGetCommonNames() {
    System.out.println("getCommonNames"); 
    String result = instance.getCommonNames();
    assertEquals("myro", result);  
  }

  /**
   * Test of getContinent method, of class SolrRecord.
   */
  @Test
  public void testGetContinent() {
    System.out.println("getContinent"); 
    String result = instance.getContinent();
    assertEquals(continent, result); 
  }

  /**
   * Test of setContinent method, of class SolrRecord.
   */
  @Test
  public void testSetContinent() {
    System.out.println("setContinent");  
    instance.setContinent(continent); 
    assertEquals(continent, instance.getContinent());  
  }

  /**
   * Test of getCountry method, of class SolrRecord.
   */
  @Test
  public void testGetCountry() {
    System.out.println("getCountry"); 
    String result = instance.getCountry();
    assertEquals(country, result); 
  }

  /**
   * Test of setCountry method, of class SolrRecord.
   */
  @Test
  public void testSetCountry() {
    System.out.println("setCountry"); 
    instance.setCountry(country); 
    assertEquals(country, instance.getCountry());
  }

  /**
   * Test of isImage method, of class SolrRecord.
   */
  @Test
  public void testIsImage() {
    System.out.println("isImage"); 
    boolean result = instance.isImage();
    assertTrue(result); 
  }

  /**
   * Test of setImage method, of class SolrRecord.
   */
  @Test
  public void testSetImage() {
    System.out.println("setImage"); 
    instance.setImage(false); 
    assertFalse(instance.isImage()); 
  }

  /**
   * Test of isMap method, of class SolrRecord.
   */
  @Test
  public void testIsMap() {
    System.out.println("isMap");  
    boolean result = instance.isMap();
    assertTrue(result); 
  }

  /**
   * Test of setMap method, of class SolrRecord.
   */
  @Test
  public void testSetMap() {
    System.out.println("setMap"); 
    instance.setMap(map); 
    assertTrue(instance.isMap());
  }

  /**
   * Test of getCoordinateString method, of class SolrRecord.
   */
  @Test
  public void testGetCoordinateString() {
    System.out.println("getCoordinateString"); 
    String expResult = latitudeText + " --- " + longitudeText;
    String result = instance.getCoordinateString();
    assertEquals(expResult, result); 
  }
  
  
  /**
   * Test of getCoordinateString method, of class SolrRecord.
   */
  @Test
  public void testGetCoordinateStringNoCoordinates() {
    System.out.println("getCoordinateString"); 
    String expResult = latitudeText + " --- " + longitudeText;
    
    instance.setMap(false);
    String result = instance.getCoordinateString();
    assertEquals(null, result); 
  }

  /**
   * Test of getHasCoordinates method, of class SolrRecord.
   */
  @Test
  public void testGetHasCoordinates() {
    System.out.println("getHasCoordinates"); 
    boolean expResult = true;
    boolean result = instance.getHasCoordinates();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getLocalityWithCountryAndContinent method, of class SolrRecord.
   */
  @Test
  public void testGetLocalityWithCountryAndContinent() {
    System.out.println("getLocalityWithCountryAndContinent"); 
    String expResult = locality + " " + country + " " + continent;
    instance.setContinent(continent);
    instance.setCountry(country);
    String result = instance.getLocalityWithCountryAndContinent();
    assertEquals(expResult, result); 
  }
  
  
  /**
   * Test of getLocalityWithCountryAndContinent method, of class SolrRecord.
   */
  @Test
  public void testGetLocalityWithCountryAndContinentAreNull() {
    System.out.println("getLocalityWithCountryAndContinent"); 
 
    instance.setContinent(null);
    instance.setCountry(null);
    String result = instance.getLocalityWithCountryAndContinent();
    assertEquals(locality, result); 
  }

  /**
   * Test of toString method, of class SolrRecord.
   */
  @Test
  public void testToString() {
    System.out.println("toString"); 
    String expResult = "Detail : [ " + catalogNumber + " ] Locality : " + locality + "  Collection : " + collectionName;
    String result = instance.toString();
    assertEquals(expResult, result); 
  }

  /**
   * Test of setId method, of class SolrRecord.
   */
  @Test
  public void testSetId() {
    System.out.println("setId"); 
    instance.setId(id); 
    assertEquals(id, instance.getId());
  }

  /**
   * Test of setTxFullName method, of class SolrRecord.
   */
  @Test
  public void testSetTxFullName() {
    System.out.println("setTxFullName"); 
    instance.setTxFullName(taxon); 
    assertEquals(taxon, instance.getTxFullName());
  }

  /**
   * Test of setStartDate method, of class SolrRecord.
   */
  @Test
  public void testSetStartDate() {
    System.out.println("setStartDate"); 
    instance.setStartDate(startDate); 
    assertEquals(instance.getStartDate(), startDate);
  }

  /**
   * Test of setLocality method, of class SolrRecord.
   */
  @Test
  public void testSetLocality() {
    System.out.println("setLocality"); 
    instance.setLocality(locality); 
    assertEquals(locality, instance.getLocality());
  }
  
}
