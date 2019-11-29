package se.nrm.dina.dnakey.portal.controller;
 
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path; 
import java.util.ArrayList;
import java.util.List; 
import java.util.UUID;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;  
import static org.mockito.Matchers.any; 
import static org.mockito.Mockito.times;
//import org.mockito.Mock; 
import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;  
import org.powermock.api.mockito.PowerMockito; 
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import se.nrm.dina.dnakey.logic.config.ConfigProperties;
import se.nrm.dina.dnakey.portal.util.UUIDGenerator;

/**
 *
 * @author idali
 */
//@RunWith(MockitoJUnitRunner.class) 
@RunWith(PowerMockRunner.class)
@PrepareForTest({Files.class, UUIDGenerator.class}) 
@PowerMockIgnore({"javax.management.*", "org.apache.http.conn.ssl.*", "com.amazonaws.http.conn.ssl.*", "javax.net.ssl.*"})
public class FileHandlerTest {

  @Rule
  TemporaryFolder tempFolder = new TemporaryFolder();

  private FileHandler instance;  
  private ConfigProperties config;

  public FileHandlerTest() {
  }

  @Before
  public void setUp() {
    config = PowerMockito.mock(ConfigProperties.class); 
    String path = tempFolder.getRoot().getAbsolutePath();
    PowerMockito.when(config.getFastaFilePath()).thenReturn(path); 
     
    instance = new FileHandler(config);
    instance.init();
  }

  @After
  public void tearDown() {
    instance = null;
  }

  /**
   * Test of init method, of class FileHandler.
   */
  @Test
  public void testInit() {
    System.out.println("init");   
    verify(config, times(1)).getFastaFilePath();
  }

  /**
   * Test of createFastaFile method, of class FileHandler.
   * @throws java.io.IOException
   */
  @Test
  public void testCreateFastaFile() throws IOException {
    System.out.println("createFastaFile");
  
    String sequence = "sfasfdasf"; 
    PowerMockito.mockStatic(Files.class);  
    when(Files.write(any(Path.class), any(byte[].class))).thenReturn(any(Path.class));  
     
    String result = instance.createFastaFile(sequence);   
    assertNotNull(result); 
    assertTrue(result.contains(".fa"));  
    File file = null; 
    try {
      file = new File(result);
      assertTrue(file.exists());
    } finally {
      if(file != null && file.exists()) {
        file.delete();
      } 
    }  
  }

  /**
   * Test of createFastaFiles method, of class FileHandler.
   * @throws java.io.IOException
   */
  @Test
  public void testCreateFastaFiles() throws IOException {
    System.out.println("createFastaFiles");
    
    List<String> sequences = new ArrayList<>(); 
    sequences.add("sadfasf");
    sequences.add("deraef");
    sequences.add("adsfd"); 
    
    PowerMockito.mockStatic(Files.class);  
    when(Files.write(any(Path.class), any(byte[].class))).thenReturn(any(Path.class));  
     
    List<String> result = instance.createFastaFiles(sequences);
    assertNotNull(result); 
    assertEquals(result.size(), 3);
      
  }

  /**
   * Test of deleteTempFiles method, of class FileHandler.
   */
//  @Test
  public void testDeleteTempFiles() {
    System.out.println("deleteTempFiles");
    List<String> filesPath = null;
    FileHandler instance = new FileHandler();
    instance.deleteTempFiles(filesPath); 
  } 
}
