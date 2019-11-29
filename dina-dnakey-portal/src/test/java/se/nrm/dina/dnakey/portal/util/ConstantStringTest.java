package se.nrm.dina.dnakey.portal.util;

import org.junit.After; 
import org.junit.Before; 
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author idali
 */
public class ConstantStringTest {
  
  private ConstantString instance;
  
  public ConstantStringTest() {
  }
   
  @Before
  public void setUp() {
    instance = ConstantString.getInstance(); 
  }
  
  @After
  public void tearDown() {
    instance = null;
  }

  /**
   * Test of getInstance method, of class ConstantString.
   */
  @Test
  public void testGetInstance() {
    System.out.println("getInstance");
    instance = null;
    instance = ConstantString.getInstance(); 
    assertNotNull(instance);
  }

  /**
   * Test of getText method, of class ConstantString.
   */
  @Test
  public void testGetText() {
    System.out.println("getText");
    String key = "collector_en"; 
    String expResult = "Collector";
    String result = instance.getText(key);
    assertEquals(expResult, result); 
    
    key = "testNotExist";
    expResult = "";
    result = instance.getText(key);
    assertEquals(expResult, result); 
  }

  /**
   * Test of getNaturarvUrlEncode method, of class ConstantString.
   */
  @Test
  public void testGetNaturarvUrlEncode() {
    System.out.println("getNaturarvUrlEncode"); 
    
    String expResult = "param=dnakey&catalogNumber=";
    String result = instance.getNaturarvUrlEncode();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getDefaultBlastDb method, of class ConstantString.
   */
  @Test
  public void testGetDefaultBlastDb() {
    System.out.println("getDefaultBlastDb"); 
    
    String expResult = "nrm";
    String result = instance.getDefaultBlastDb();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getWhiteSpaceChars method, of class ConstantString.
   */
  @Test
  public void testGetWhiteSpaceChars() {
    System.out.println("getWhiteSpaceChars"); 
    
    String expResult = "[ \t\r\n\f]";
    String result = instance.getWhiteSpaceChars();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getUtf8 method, of class ConstantString.
   */
  @Test
  public void testGetUtf8() {
    System.out.println("getUtf8"); 
    String expResult = "UTF-8";
    String result = instance.getUtf8();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getNrmDbName method, of class ConstantString.
   */
  @Test
  public void testGetNrmDbName() {
    System.out.println("getNrmDbName");
    
    boolean isSwedish = false; 
    String expResult = "Swedish vertebrate animals (COI, 16S)";
    String result = instance.getNrmDbName(isSwedish);
    assertEquals(expResult, result); 
    
    isSwedish = true;
    expResult = "Svenska ryggradsdjur (COI, 16S)";
    result = instance.getNrmDbName(isSwedish);
    assertEquals(expResult, result); 
  }

  /**
   * Test of getBoldDbName method, of class ConstantString.
   */
  @Test
  public void testGetBoldDbName() {
    System.out.println("getBoldDbName");
    
    boolean isSwedish = false; 
    String expResult = "Barcode sequences for Swedish organisms (COI, matK, rbcL, 16S*)";
    String result = instance.getBoldDbName(isSwedish);
    assertEquals(expResult, result); 
    
    isSwedish = true; 
    expResult = "Barkodsekvenser för svenska organismer (COI, matK, rbcL, 16S*)";
    result = instance.getBoldDbName(isSwedish);
    assertEquals(expResult, result); 
  }

  /**
   * Test of getGenbankDbName method, of class ConstantString.
   */
  @Test
  public void testGetGenbankDbName() {
    System.out.println("getGenbankDbName");
      
    boolean isSwedish = false; 
    String expResult = "Barcode sequences from Genbank (COI, matK, rbcL, 16S*)";
    String result = instance.getGenbankDbName(isSwedish);
    assertEquals(expResult, result); 
    
    isSwedish = true; 
    expResult = "Barkodsekvenser från Genbank (COI, matK, rbcL, 16S*)";
    result = instance.getGenbankDbName(isSwedish);
    assertEquals(expResult, result); 
  }
  
}
