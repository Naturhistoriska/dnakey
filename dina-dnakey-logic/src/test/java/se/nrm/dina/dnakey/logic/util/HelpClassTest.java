package se.nrm.dina.dnakey.logic.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Date;
import org.junit.After; 
import org.junit.Before; 
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author idali
 */
public class HelpClassTest {
  
  private HelpClass instance;
  
  public HelpClassTest() {
  }
   
  @Before
  public void setUp() {
    instance = HelpClass.getInstance();
  }
  
  @After
  public void tearDown() {
  }

  /**
   * Test of getInstance method, of class HelpClass.
   */
  @Test
  public void testGetInstance() {
    System.out.println("getInstance"); 
    assertNotNull(instance); 
  }

  /**
   * Test of intToDouble method, of class HelpClass.
   */
  @Test
  public void testIntToDouble() {
    System.out.println("intToDouble");
    int value = 18;
    double expResult = 18d;
    double result = HelpClass.intToDouble(value);
    assertEquals(expResult, result, 0.0); 
  }

  /**
   * Test of stringToInteger method, of class HelpClass.
   */
  @Test
  public void testStringToInteger() {
    System.out.println("stringToInteger");
    String strNumber = "18";
    int expResult = 18;
    int result = HelpClass.stringToInteger(strNumber);
    assertEquals(expResult, result); 
  }
  
  /**
   * Test of stringToInteger method, of class HelpClass.
   */
  @Test
  public void testStringToIntegerNotNumber() {
    System.out.println("stringToInteger");
    String strNumber = "ee";
    int expResult = 0;
    int result = HelpClass.stringToInteger(strNumber);
    assertEquals(expResult, result); 
  }

  /**
   * Test of stringToPercentageString method, of class HelpClass.
   */
  @Test
  public void testStringToPercentageString() {
    System.out.println("stringToPercentageString");
    String strNumber = "18";
    String expResult = "18.0";
    String result = HelpClass.stringToPercentageString(strNumber);
    assertEquals(expResult, result); 
  }

 /**
   * Test of stringToPercentageString method, of class HelpClass.
   */
  @Test
  public void testStringToPercentageStringNotNumber() {
    System.out.println("stringToPercentageString");
    String strNumber = "ee";
    String expResult = "ee";
    String result = HelpClass.stringToPercentageString(strNumber);
    assertEquals(expResult, result); 
  }
  
  /**
   * Test of isNumeric method, of class HelpClass.
   */
  @Test
  public void testIsNumeritcTrue() {
    System.out.println("isNumeric");
    String arg = "18";
    boolean expResult = true;
    boolean result = HelpClass.isNumeric(arg);
    assertEquals(expResult, result); 
  }
  
  /**
   * Test of isNumeric method, of class HelpClass.
   */
  @Test
  public void testIsNumericFalse() {
    System.out.println("isNumeric");
    String arg = "ee";
    boolean expResult = false;
    boolean result = HelpClass.isNumeric(arg);
    assertEquals(expResult, result); 
  }

  /**
   * Test of dateToStringWithTime method, of class HelpClass.
   */
  @Test
  public void testDateToStringWithTimeWithNull() {
    System.out.println("dateToStringWithTime");
    Date date = null;
    String expResult = null;
    String result = HelpClass.dateToStringWithTime(date);
    assertEquals(expResult, result); 
  }
  
  /**
   * Test of dateToStringWithTime method, of class HelpClass.
   */
  @Test
  public void testDateToStringWithTime() {
    System.out.println("dateToStringWithTime"); 
    LocalDateTime localDate = LocalDateTime.of(2015, Month.MARCH, 1, 10, 18); 
    Date date = java.sql.Timestamp.valueOf(localDate); 
    
    String expResult = "2015-03-01T10:18:000Z";
    String result = HelpClass.dateToStringWithTime(date); 
    assertNotNull(result);
    assertEquals(expResult, result); 
  }
   
  /**
   * Test of dateToString method, of class HelpClass.
   */
  @Test
  public void testDateToString() {
    System.out.println("dateToString");
    
    LocalDate localDate = LocalDate.parse("2015-02-20");
    
    Date date = java.sql.Date.valueOf(localDate); 
    String expResult = "2015-02-20";
    String result = HelpClass.dateToString(date);  
    assertEquals(expResult, result); 
  }

  /**
   * Test of dateToString method, of class HelpClass.
   */
  @Test
  public void testDateToStringWithNull() {
    System.out.println("dateToString");
     
    Date date = null; 
    String result = HelpClass.dateToString(date);  
    assertEquals(null, result); 
  }

  /**
   * Test of buildExecDbInfoCommand method, of class HelpClass.
   */
  @Test
  public void testBuildExecDbInfoCommand() {
    System.out.println("buildExecDbInfoCommand");
    String dbInfoPath = "dbInfoPath";
    String dbPath = "dbPath";
    String database = "nrm"; 
    String expResult = "dbInfoPath -db dbPathnrm -info";
    String result = instance.buildExecDbInfoCommand(dbInfoPath, dbPath, database); 
    assertEquals(expResult, result); 
  }

  /**
   * Test of buildBlastCommand method, of class HelpClass.
   */
  @Test
  public void testBuildBlastCommand() {
    System.out.println("buildBlastCommand");
    String fastafilePath = "filePath";
    String dbName = "nrm";
    String blastPath = "blastPath";
    String blastDbPath = "blastDbPath"; 
    String expResult = "blastPath -query filePath -db blastDbPathnrm -task blastn -dust no -outfmt 5 -num_alignments 10 -num_descriptions 10 -parse_deflines";
    String result = instance.buildBlastCommand(fastafilePath, dbName, blastPath, blastDbPath); 
    assertEquals(expResult, result);   
  }

  /**
   * Test of buildGenbankSearch method, of class HelpClass.
   */
  @Test
  public void testBuildGenbankSearch() {
    System.out.println("buildGenbankSearch");
    String sequence = "eeee";  
    String expResult = "http://www.ncbi.nlm.nih.gov/blast/Blast.cgi?QUERY=eeee&DATABASE=nr&HITLIST_SIZE=10&FILTER=L&EXPECT=10&FORMAT_TYPE=HTML&PROGRAM=blastn&CLIENT=web&SERVICE=plain&NCBI_GI=on&PAGE=Nucleotides&CMD=Put";
    String result = instance.buildGenbankSearch(sequence); 
    assertEquals(expResult, result); 
  }
  
}
