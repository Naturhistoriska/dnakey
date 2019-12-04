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
public class InvalidMetadataExceptionTest {
  
  private InvalidMetadataException instance;
  
  public InvalidMetadataExceptionTest() {
  } 
  
  @Before
  public void setUp() {
  }
  
  @After
  public void tearDown() {
  }
 
  @Test
  public void testConstructoreWithMessag() {
    String errorMsg = "Error";
    instance = new InvalidMetadataException(errorMsg);
    assertNotNull(instance.getMessage());
  } 
    
  @Test
  public void testConstructoreWithMessagAndThrowable() {
    String errorMsg = "Error";
    Throwable throwable = new Throwable(errorMsg);
     
    instance = new InvalidMetadataException(errorMsg, throwable);
    assertNotNull(instance.getMessage());
    assertNotNull(instance.getCause());
  } 
  
  @Test
  public void testConstructoreWithThrowable() {
    String errorMsg = "Error";
    Throwable throwable = new Throwable(errorMsg);
     
    instance = new InvalidMetadataException(throwable);
    assertNotNull(instance.getMessage());
    assertNotNull(instance.getCause());
  } 
}
