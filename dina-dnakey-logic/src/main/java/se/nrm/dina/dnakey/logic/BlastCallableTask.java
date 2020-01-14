package se.nrm.dina.dnakey.logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j; 
import se.nrm.dina.dnakey.logic.metadata.BlastMetadata;
import se.nrm.dina.dnakey.logic.metadata.MetadataDataFactory;
import se.nrm.dina.dnakey.logic.util.HelpClass;

/**
 * Run Blast
 *
 * @author idali
 */
@Slf4j
public class BlastCallableTask implements Callable<BlastMetadata> { 
  private String blastCommand; 
  
  private final String newLine = "\n";

  public BlastCallableTask() {

  }

  public BlastCallableTask(final String fastafilePath, final String dbName, String blastPath, String blastDbPath) { 
    log.info("BlastCallableTask");

    blastCommand = HelpClass.getInstance().buildBlastCommand(fastafilePath, dbName, blastPath, blastDbPath);
  }

  /**
   * Call to process blast
   *
   * @return BlastMetadata
   */
  @Override
  public BlastMetadata call() {

    InputStream is = null;
    Process process = null;
    try {
      process = Runtime.getRuntime().exec(blastCommand);
      is = process.getInputStream(); 
      String s = new BufferedReader(new InputStreamReader(is)).lines()
              .collect(Collectors.joining(newLine));

      return MetadataDataFactory.getInstance().buildBlastMetadataByJson(s);
    } catch (IOException ex) {
      log.error(ex.getMessage());
    } finally {
      try {
        if (is != null) {
          is.close();
        }
      } catch (IOException ex) {
        log.info(ex.getMessage());
      } 
      if(process != null && process.isAlive()) {
        process.destroy();
      }
    }
    return null;
  } 
}
