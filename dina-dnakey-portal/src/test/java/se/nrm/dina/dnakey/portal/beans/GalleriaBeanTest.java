package se.nrm.dina.dnakey.portal.beans;

import java.util.Date;
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
import se.nrm.dina.dnakey.logic.vo.MorphBankImage;
import se.nrm.dina.dnakey.logic.vo.NrmData;

/**
 *
 * @author idali
 */
@RunWith(MockitoJUnitRunner.class) 
public class GalleriaBeanTest {
   
  private GalleriaBean instance;
  private NrmData data;
  
  @Mock
  private ConfigProperties config;
  
  public GalleriaBeanTest() {
  }
   
  @Before
  public void setUp() {
    
    instance = new GalleriaBean(config); 
    when(config.getThumbPath()).thenReturn("http://images.morphbank.nrm.se/?id="); 
    String[] morphbankImageIds = new String[3];
    morphbankImageIds[0] = "100";
    morphbankImageIds[1] = "200";
    morphbankImageIds[2] = "300";
    data = new NrmData("1234567", "taxon", "collection", "commonName", "Tyreso", "54.5N12.8E", 
            new Date(), "John D", true, morphbankImageIds, "123", "Sweden", "Europ"); 
  }
  
  @After
  public void tearDown() {
    instance = null;
  }
  
  @Test(expected = NullPointerException.class)
  public void testDefaultConstructor() {
    instance = new GalleriaBean();  
    instance.imageSwitch(data);  
  }

  /**
   * Test of imageSwitch method, of class GalleriaBean.
   */
  @Test
  public void testImageSwitch() {
    System.out.println("imageSwitch");  
     
    assertNull(instance.getGroupImages());
    instance.imageSwitch(data); 
    assertNotNull(instance.getGroupImages());
  }

  /**
   * Test of getGroupImages method, of class GalleriaBean.
   */
  @Test
  public void testGetGroupImages() {
    System.out.println("getGroupImages"); 
     
    instance.imageSwitch(data); 
    List<MorphBankImage> result = instance.getGroupImages();
    assertEquals(3, result.size()); 
  }

  /**
   * Test of getMbid method, of class GalleriaBean.
   */
  @Test
  public void testGetMbid() {
    System.out.println("getMbid"); 
    
    instance.imageSwitch(data); 
    String expResult = "123";
    String result = instance.getMbid();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getCatalogNumber method, of class GalleriaBean.
   */
  @Test
  public void testGetCatalogNumber() {
    System.out.println("getCatalogNumber"); 
    
    instance.imageSwitch(data); 
    String expResult = "1234567";
    String result = instance.getCatalogNumber();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getScientificName method, of class GalleriaBean.
   */
  @Test
  public void testGetScientificName() {
    System.out.println("getScientificName"); 
    
    instance.imageSwitch(data);
    String expResult = "taxon";
    String result = instance.getScientificName();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getNameAndCatalogNumber method, of class GalleriaBean.
   */
  @Test
  public void testGetNameAndCatalogNumber() {
    System.out.println("getNameAndCatalogNumber");
   
    instance.imageSwitch(data);
    String expResult = "taxon [Catalog nr: 1234567 ]";
    String result = instance.getNameAndCatalogNumber(); 
    assertEquals(expResult, result); 
  }

  /**
   * Test of setNameAndCatalogNumber method, of class GalleriaBean.
   */
  @Test
  public void testSetNameAndCatalogNumber() {
    System.out.println("setNameAndCatalogNumber"); 
    
    String nameAndCatalogNumber = "Text [cat. 9999]"; 
    instance.setNameAndCatalogNumber(nameAndCatalogNumber); 
     
    assertEquals(instance.getNameAndCatalogNumber(), nameAndCatalogNumber);
  }
  
}
