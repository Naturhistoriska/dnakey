package se.nrm.dina.dnakey.logic.vo;

import java.util.Date;
import org.junit.After; 
import org.junit.Before; 
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author idali
 */
public class NrmDataTest {
  
  private NrmData instance;
  private String catalogNumber;
  private String name;
  private String collectionName;
  private String commonNames;
  private String locality;
  private String coordinates;
  private Date startDate;
  private String collectors;
  private String[] morphbankImageIds;
  private String morphbankId;
  private String country;
  private String continent;
  
  public NrmDataTest() {
  }
   
  @Before
  public void setUp() {
    commonNames = "common name"; 
           
    catalogNumber = "cat123";
    name = "taxon";
    collectionName = "collection name";
    locality = "Tyreso, Sweden";
    coordinates = "58N18E";
    startDate = new Date();
    
    collectors = "John Doe"; 
    
    morphbankImageIds = new String[2];
    morphbankImageIds[0] = "18";
    morphbankImageIds[1] = "38";
    
    morphbankId = "388";
    country = "Sweden";
    continent = "Europe";
    
    instance = new NrmData(catalogNumber, name, collectionName, commonNames, 
            locality, coordinates, startDate, collectors, true, morphbankImageIds, 
            morphbankId, country, continent);
  }
  
  @After
  public void tearDown() {
    instance = null;
  }

  /**
   * Test of getCatalogNumber method, of class NrmData.
   */
  @Test
  public void testGetCatalogNumber() {
    System.out.println("getCatalogNumber");  
    String result = instance.getCatalogNumber();
    assertEquals(catalogNumber, result); 
  }

  /**
   * Test of getTaxonName method, of class NrmData.
   */
  @Test
  public void testGetTaxonName() {
    System.out.println("getTaxonName"); 
    String result = instance.getTaxonName();
    assertEquals(name, result); 
  }

  /**
   * Test of getCollectionName method, of class NrmData.
   */
  @Test
  public void testGetCollectionName() {
    System.out.println("getCollectionName");  
    String result = instance.getCollectionName();
    assertEquals(collectionName, result); 
  }

  /**
   * Test of getCommonNames method, of class NrmData.
   */
  @Test
  public void testGetCommonNames() {
    System.out.println("getCommonNames"); 
    String result = instance.getCommonNames();
    assertEquals(commonNames, result); 
  }

  /**
   * Test of getLocality method, of class NrmData.
   */
  @Test
  public void testGetLocality() {
    System.out.println("getLocality"); 
    String result = instance.getLocality();
    assertEquals(locality, result); 
  }

  /**
   * Test of getCoordinates method, of class NrmData.
   */
  @Test
  public void testGetCoordinates() {
    System.out.println("getCoordinates"); 
    String result = instance.getCoordinates();
    assertEquals(coordinates, result); 
  }

  /**
   * Test of getStartDate method, of class NrmData.
   */
  @Test
  public void testGetStartDate() {
    System.out.println("getStartDate"); 
    Date result = instance.getStartDate();
    assertEquals(startDate, result); 
  }

  /**
   * Test of getCollectors method, of class NrmData.
   */
  @Test
  public void testGetCollectors() {
    System.out.println("getCollectors"); 
    String result = instance.getCollectors();
    assertEquals(collectors, result); 
  }

  /**
   * Test of isHasImages method, of class NrmData.
   */
  @Test
  public void testIsHasImages() {
    System.out.println("isHasImages");  
    boolean result = instance.isHasImages();
    assertEquals(true, result); 
  }

  /**
   * Test of getMorphbankImageIds method, of class NrmData.
   */
  @Test
  public void testGetMorphbankImageIds() {
    System.out.println("getMorphbankImageIds"); 
    String[] result = instance.getMorphbankImageIds();
    assertArrayEquals(morphbankImageIds, result); 
  }

  /**
   * Test of getMorphbankId method, of class NrmData.
   */
  @Test
  public void testGetMorphbankId() {
    System.out.println("getMorphbankId"); 
    String result = instance.getMorphbankId();
    assertEquals(morphbankId, result); 
  }

  /**
   * Test of getCountry method, of class NrmData.
   */
  @Test
  public void testGetCountry() {
    System.out.println("getCountry"); 
    String result = instance.getCountry();
    assertEquals(country, result); 
  }

  /**
   * Test of getContinent method, of class NrmData.
   */
  @Test
  public void testGetContinent() {
    System.out.println("getContinent"); 
    String result = instance.getContinent();
    assertEquals(continent, result); 
  }

  /**
   * Test of getNameAndCatalogNumber method, of class NrmData.
   */
  @Test
  public void testGetNameAndCatalogNumber() {
    System.out.println("getNameAndCatalogNumber"); 
    String expResult = "taxon [Catalog nr: cat123 ]";
    String result = instance.getNameAndCatalogNumber();
    System.out.println("result..." + result);
    assertEquals(expResult, result); 
  }
  
}
