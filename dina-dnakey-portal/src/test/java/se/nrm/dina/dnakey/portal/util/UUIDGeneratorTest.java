package se.nrm.dina.dnakey.portal.util;

import java.util.UUID; 
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author idali
 */
public class UUIDGeneratorTest {
   
  public UUIDGeneratorTest() {
  }
 
  /**
   * Test of generateUUID method, of class UUIDGenerator.
   */
  @Test
  public void testGenerateUUID() {
    System.out.println("generateUUID"); 
    
    UUID result1 = UUIDGenerator.generateUUID();
    assertNotNull(result1);
    
    UUID result2 = UUIDGenerator.generateUUID();
    assertNotNull(result2);
    
    assertNotEquals(result1, result2);
  }
  
}
