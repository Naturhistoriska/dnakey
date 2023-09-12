package se.nrm.dina.dnakey.portal.util;

import java.io.BufferedReader;
import java.io.StringReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List; 
import java.util.stream.Collectors; 
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author idali
 */
@Slf4j
public class SequencesBuilderHelper {

  private LocalDateTime timestamp;

  private final String HEADER_TAG = ">";
  private final String REGEX = "[\r\n]+";
  private final String NEW_LINE = "\n";

  private static SequencesBuilderHelper instance = null;

  public static SequencesBuilderHelper getInstance() {
    synchronized (SequencesBuilderHelper.class) {
      if (instance == null) {
        instance = new SequencesBuilderHelper();
      }
    }
    return instance;
  }
  
  /**
   * buildSequenceList - build string into list of sequences
   *
   * @param sequence - String
   * @return List
   */
  public List<String> buildSequenceList(String sequence) {
    log.info("buildSequenceList : {}", sequence);
    
    String[] strings = sequence.split(REGEX);
 
    return Arrays.stream(strings, 0, 
            Util.getInstance().getMaxCount(strings))
            .filter(string -> !Util.getInstance().isEmptyLine(string))
            .map(s -> addSequenceHeader(s.trim()))
            .collect(Collectors.toList());
  }

  /**
   * Add fasta header into sequence
   *
   * @param string
   * @return String
   */
  public String addSequenceHeader(String string) {
    timestamp = LocalDateTime.now();
    StringBuilder sb = new StringBuilder(HEADER_TAG);
    sb.append(timestamp);
    sb.append(NEW_LINE);
    sb.append(string);
    return sb.toString().trim();
  }

  public List<String> buildStringList(String string) {
    List<String> strings = new ArrayList<>();
    StringBuilder sb = new StringBuilder();
    new BufferedReader(new StringReader(string)).lines()
            .forEach(s -> {
              if (!Util.getInstance().isEmptyLine(s)) {
                sb.append(s);
                if (s.contains(HEADER_TAG)) {
                  sb.append(NEW_LINE);
                }
              } else {
                if (!Util.getInstance().isEmptyLine(sb.toString())) {
                  strings.add(sb.toString());
                }
                sb.setLength(0);
              }
            });
    if (!Util.getInstance().isEmptyLine(sb.toString().trim())) {
      strings.add(sb.toString());
    }
    return strings;
  }
}
