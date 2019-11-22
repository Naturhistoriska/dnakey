package se.nrm.dina.dnakey.portal.controller;
 
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files; 
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;  
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import se.nrm.dina.dnakey.logic.config.ConfigProperties;
import se.nrm.dina.dnakey.portal.util.UUIDGenerator;

/**
 * This class handles test fasta files It creates fasta file and save it into
 * temp directory
 *
 * @author idali
 */
@Slf4j
public class FileHandler implements Serializable {

  private String fastaTempPath;
  private final String FASTA_FILE_TYPE = ".fa"; 
  private StringBuilder sb;
  private final String fileSeparator = "/";

  @Inject
  private ConfigProperties config;

  public FileHandler() {
    log.info("FileHandler");
  }

  @PostConstruct
  public void init() {
    fastaTempPath = config.getFastaFilePath();
  }

  /**
   * Create fasta file and save to temp directory
   * 
   * @param sequence -String
   * @return String - file path
   */
  public String createFastaFile(String sequence) { 
    log.info("createFastaFile");
    sb = new StringBuilder();
    sb.append(fastaTempPath);
    sb.append(fileSeparator);
    sb.append(String.valueOf(UUIDGenerator.generateUUID()));
    sb.append(FASTA_FILE_TYPE); 
    try {
      Files.write(Paths.get(sb.toString().trim()), sequence.getBytes()); 
    } catch (IOException ex) { 
      log.error(ex.getMessage());
    }
    return sb.toString().trim(); 
  }
  
  
  /**
   * Create fasta files and save to temp directory
   * 
   * @param sequences -String
   * @return String - file path
   */
  public List<String> createFastaFiles(List<String> sequences) { 
//    List<String> fastaFilesPath = new ArrayList<>();
    return sequences.stream()
            .map(s -> createFastaFile(s))
            .collect(Collectors.toList());
//            .forEach(seq -> {
//              fastaFilesPath.add(createFastaFile(seq)); 
//            });
//    return fastaFilesPath;
  }
    
  /**
   * Delete fasta files in temp directory
   * @param filesPath - List
   */
  public void deleteTempFiles(List<String> filesPath) { 
    filesPath.stream()
            .forEach(f -> {
                try { 
                  Files.deleteIfExists(Paths.get(f));
                } catch (IOException ex) {
                  log.error(ex.getMessage());
                }
            }); 
  }
}
