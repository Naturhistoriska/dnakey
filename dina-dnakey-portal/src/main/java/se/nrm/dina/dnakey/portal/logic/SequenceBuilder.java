package se.nrm.dina.dnakey.portal.logic;
  
import java.io.IOException;
import java.io.Serializable; 
import java.nio.charset.StandardCharsets; 
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.primefaces.model.UploadedFile;
import se.nrm.dina.dnakey.portal.util.SequencesBuilderHelper;

/**
 *
 * @author idali
 */
@Slf4j
public class SequenceBuilder implements Serializable {
 
  private final String HEADER_TAG = ">"; 

  public SequenceBuilder() {
    log.info("SequenceBuilder");
  }
  
  public boolean isEmptySequences(String sequence) {
    return StringUtils.isEmpty(sequence.trim());
  }
  
  /**
   * This method build one sequence string into list of sequences
   * 
   * @param sequence
   * @return List
   */
  public List<String> prepareSequenceList(String sequence) {
    
    if (StringUtils.isEmpty(sequence.trim())) {
      return null;
    }  
     
    StringTokenizer tokens = new StringTokenizer(sequence, HEADER_TAG, true);
    if (tokens.countTokens() <= 1) {                                            // sequences without header
      return SequencesBuilderHelper.getInstance().buildSequenceList(sequence);
    }
       
    List<String> sequences = new ArrayList<>();
    StringBuilder sb = new StringBuilder();
    List<String> strings; 
    while (tokens.hasMoreTokens()) {
      String string = tokens.nextToken(); 
      if (string.contains(HEADER_TAG)) {
        sb.append(string);
        sb.append(tokens.nextToken()); 
        strings = SequencesBuilderHelper.getInstance().buildStringList(sb.toString());

        sb = new StringBuilder();
        for (String s : strings) { 
          if (!StringUtils.isEmpty(s.trim())) {
            if (!s.contains(HEADER_TAG)) {                         // if sequence has no head 
              s = SequencesBuilderHelper.getInstance().addSequenceHeader(s);
            }
            sequences.add(s);
          }
        }
      } else {
        sequences = SequencesBuilderHelper.getInstance().buildSequenceList(string);
      }
    }
    return sequences;
  }
  
  /**
   * Convert map to list 
   * 
   * @param sequencesMap
   * @return List
   */
  public List<String> convertSequencesMapToList(Map<String, List<String>> sequencesMap) {
    final List<String> sequences = new ArrayList<>();
    if (sequencesMap == null || sequencesMap.isEmpty()) {
      return null;
    } 
    sequencesMap.entrySet().stream()
            .forEach((entry) -> {
              sequences.addAll(entry.getValue());
            });
    if (sequences.size() > 100) {
      return sequences.subList(0, 99);
    }
    return sequences;
  }
  
  /**
   * Build sequences from uploaded file
   * 
   * @param uploadedFile - UploadedFile
   * @return List
   */
  public List<String> buildSequencesFromUploadFile(UploadedFile uploadedFile) {
    String seq = null;
    try {
      seq = IOUtils.toString(uploadedFile.getInputstream(), StandardCharsets.UTF_8.name());  
    } catch (IOException ex) {
      log.error(ex.getMessage());
    } 
    return prepareSequenceList(seq); 
  } 
}
