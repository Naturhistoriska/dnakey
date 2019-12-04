package se.nrm.dina.dnakey.logic.exception;

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
public class DnaKeyExceptionTest {
  
  private DnaKeyException instance;
  
  public DnaKeyExceptionTest() {
  }
  
  @BeforeClass
  public static void setUpClass() {
  }
  
  @AfterClass
  public static void tearDownClass() {
  }
  
  @Before
  public void setUp() {
  }
  
  @After
  public void tearDown() {
  }

 @Test
  public void testDefaultConstructor() {
    instance = new DnaKeyException();
    assertTrue(instance.getMessage() == null);
  } 
  
  @Test
  public void testConstructoreWithMessag() {
    String errorMsg = "Error";
    instance = new DnaKeyException(errorMsg);
    assertNotNull(instance.getMessage());
  } 
    
  @Test
  public void testConstructoreWithMessagAndThrowable() {
    String errorMsg = "Error";
    Throwable throwable = new Throwable(errorMsg);
     
    instance = new DnaKeyException(errorMsg, throwable);
    assertNotNull(instance.getMessage());
    assertNotNull(instance.getCause());
  } 
  
  @Test
  public void testConstructoreWithThrowable() {
    String errorMsg = "Error";
    Throwable throwable = new Throwable(errorMsg);
     
    instance = new DnaKeyException(throwable);
    assertNotNull(instance.getMessage());
    assertNotNull(instance.getCause());
  } 
  
}
