package se.nrm.dina.dnakey.logic.exception;

import org.junit.After; 
import org.junit.Before; 
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author idali
 */
public class DinaExceptionTest {
  
  private DinaException instance;
  
  public DinaExceptionTest() {
  }
   
  
  @Before
  public void setUp() {
  }
  
  @After
  public void tearDown() {
  }
  
  @Test
  public void testDefaultConstructor() {
    instance = new DinaException();
    assertTrue(instance.getMessage() == null);
  } 
  
  @Test
  public void testConstructoreWithMessag() {
    String errorMsg = "Error";
    instance = new DinaException(errorMsg);
    assertNotNull(instance.getMessage());
  } 
    
  @Test
  public void testConstructoreWithMessagAndThrowable() {
    String errorMsg = "Error";
    Throwable throwable = new Throwable(errorMsg);
     
    instance = new DinaException(errorMsg, throwable);
    assertNotNull(instance.getMessage());
    assertNotNull(instance.getCause());
  } 
  
  @Test
  public void testConstructoreWithThrowable() {
    String errorMsg = "Error";
    Throwable throwable = new Throwable(errorMsg);
     
    instance = new DinaException(throwable);
    assertNotNull(instance.getMessage());
    assertNotNull(instance.getCause());
  } 
}
