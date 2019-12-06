package se.nrm.dina.dnakey.logic.metadata;
 
import java.util.List;
import org.apache.commons.lang.StringUtils;
import se.nrm.dina.dnakey.logic.vo.NrmData; 

/**
 *
 * @author idali
 */
public class BlastSubjectMetadata {

//    gi|292389084|gb|GU571382|bold|BON074-06.COI-5P|gene|COI|latlon|59.183_N_9.617_E Erithacus rubecula
  private final int hitNumber;
  private final String genbankId;
  private final String genbankAccession;
  private final String boldId;
  private final String targetMarker;
  private final String coordinates;
  private final String catalogNumber;
  private final String scientificName;
  private final int hitLen;
  private final boolean nrm;
   
  public static final String TEXT_BLACK = "blacktext";
  public static final String TEXT_GRAY = "graytext";
 
  private final List<BlastSubjectHsp> subjectHspList;
  private NrmData nrmData;

  public BlastSubjectMetadata(final int hitNumber, final String genbankId,
          final String genbankAccession, final String boldId,
          final String targetMarker, final String coordinates,
          final String catalogNumber, final String scientificName,
          final int hitLen, final List<BlastSubjectHsp> subjectHspList,
          final boolean nrm) {
    this.hitNumber = hitNumber;
    this.genbankId = genbankId;
    this.genbankAccession = genbankAccession;
    this.boldId = boldId;
    this.targetMarker = targetMarker;
    this.coordinates = coordinates;
    this.catalogNumber = catalogNumber;
    this.scientificName = scientificName;
    this.hitLen = hitLen;
    this.subjectHspList = subjectHspList;
    this.nrm = nrm;
  }

  public String getBoldId() {
    return boldId;
  }

  public String getBoldIdWithType() {
    return boldId.contains(".") ? StringUtils.substringBefore(boldId, ".") : boldId;
  }

  public String getCoordinates() {
    return coordinates;
  }
  
  public String getFormattedCoordinates() {
    return isHasCoordinates() ? StringUtils.replaceChars(coordinates, "_", " ") : null;
  }
  
  public boolean isHasCoordinates() {
    return !coordinates.trim().isEmpty();
  }
  
  public boolean isHasImages() {
    return nrmData != null ? nrmData.isHasImages() : false; 
  }

  public String getGenbankAccession() {
    return genbankAccession;
  }

  public String getGenbankId() {
    return genbankId;
  }

  public int getHitNumber() {
    return hitNumber;
  }

  public String getScientificName() {
    return scientificName;
  }

  public List<BlastSubjectHsp> getSubjectHspList() {
    return subjectHspList;
  }

  public String getTargetMarker() {
    return targetMarker;
  }

  public int getHitLen() {
    return hitLen;
  }

  public String getCatalogNumber() {
    return catalogNumber;
  }

  public boolean isNrm() {
    return nrm;
  }

  public NrmData getNrmData() {
    return nrmData;
  }

  public void setNrmData(NrmData nrmData) {
    this.nrmData = nrmData;
  }
   
  public String getTextColor() { 
    return subjectHspList.stream()
            .filter(s -> s.getTextColor().equals(TEXT_BLACK))
            .findAny().isPresent() ? TEXT_BLACK : TEXT_GRAY; 
  }

  public String getStyle() {
    return subjectHspList.stream()
            .filter(s -> s.getTextColor().equals(TEXT_BLACK))
            .findAny().isPresent() ? TEXT_BLACK : null; 
  }
}
