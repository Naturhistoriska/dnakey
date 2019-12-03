package se.nrm.dina.dnakey.portal.util;

import java.util.UUID;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.runner.RunWith;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 *
 * @author idali
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({UUIDGenerator.class})
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
 
  @Test
  public void testGenerateUUIDConstructor() {
    UUIDGenerator instance = new UUIDGenerator();
    
    assertNotNull(instance);
  }
}
