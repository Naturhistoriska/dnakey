package se.nrm.dina.dnakey.logic.metadata;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import se.nrm.dina.dnakey.logic.util.HelpClass;

/**
 *
 * @author idali
 */
@Slf4j
public class BlastMetadata {

  private String program;
  private String version;
  private String reference;
  private String database;
  private String query;
  private int queryLen;
  private int statisticDbNumber;
  private int statisticDbLength;
  private String sequence;
  private boolean openLowMatch = false;
  private boolean hasHit;

  private List<BlastSubjectMetadata> subjectMetadataList;
  private List<BlastSubjectMetadata> lowMatchsubjectMetadataList;
 
  public BlastMetadata() {

  }

  public BlastMetadata(String program, String version, String reference, String database, String query, int queryLen,
          int statisticDbNumber, int statisticDbLength, List<BlastSubjectMetadata> subjectMetadataList,
          List<BlastSubjectMetadata> lowMatchSubjectMetadataList, boolean openLowMatch, boolean hasHit) {
    this.program = program;
    this.version = version;
    this.reference = reference;
    this.database = database;
    this.query = query;
    this.queryLen = queryLen;
    this.statisticDbNumber = statisticDbNumber;
    this.statisticDbLength = statisticDbLength;

    this.subjectMetadataList = subjectMetadataList;
    this.lowMatchsubjectMetadataList = lowMatchSubjectMetadataList;
    this.openLowMatch = openLowMatch;
    this.hasHit = hasHit;
  }

  public String getProgram() {
    return program;
  }

  public String getDatabase() {
    return database;
  }

  public String getQuery() {
    return query;
  }

  public int getQueryLen() {
    return queryLen;
  }

  public String getReference() {
    return reference;
  }

  public int getStatisticDbLength() {
    return statisticDbLength;
  }

  public int getStatisticDbNumber() {
    return statisticDbNumber;
  }

  public List<BlastSubjectMetadata> getSubjectMetadataList() {
    return subjectMetadataList;
  }

  public String getVersion() {
    return version;
  }

  public void setSequence(String sequence) {
    this.sequence = sequence;
  }

  public String getSequence() {
    return sequence;
  }

  public List<BlastSubjectMetadata> getLowMatchsubjectMetadataList() {
    return lowMatchsubjectMetadataList;
  }

  public boolean isOpenLowMatch() {
    return openLowMatch;
  }

  public void setOpenLowMatch(boolean openLowMatch) {
    this.openLowMatch = openLowMatch;
  }

  public boolean isHasHighMatach() {
    return !subjectMetadataList.isEmpty();
  }

  public boolean isHasLowMatch() {
    return !lowMatchsubjectMetadataList.isEmpty();
  }

  public boolean isHasHit() {
    return hasHit;
  }

  public String getGenbankUrl() { 
    return subjectMetadataList.isEmpty() ? HelpClass.getInstance().buildGenbankSearch(sequence): null;
  } 
}
