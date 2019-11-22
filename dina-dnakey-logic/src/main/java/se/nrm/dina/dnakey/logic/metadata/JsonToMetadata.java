package se.nrm.dina.dnakey.logic.metadata;

import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;

/**
 *
 * @author idali
 */
public class JsonToMetadata {
  
  private final String hitNumKey = "Hit_num";
  private final String hitDefKey = "Hit_def";
  private final String hitLenKey = "Hit_len";
  private final String giKey = "gi|";
  private final String gbKey = "|gb|";
  private final String boldKey = "|bold|";
  private final String geneKey= "|gene|";
  private final String latLonKey = "|latlon|";
  private final String catnrKey = "|catnr|";
  private final String emptySpace = " ";
  
  private final String hspScoreKey = "Hsp_score";
  private final String hspBitScoreKey = "Hsp_bit-score";
  private final String hspIdentityKey = "Hsp_identity";
  private final String hspQseqKey = "Hsp_qseq";
  private final String hspHseqKey = "Hsp_hseq";
  private final String hspMidlineKey = "Hsp_midline";
  private final String hspQueryFromKey = "Hsp_query-from";
  private final String hspQueryToKey = "Hsp_query-to";
  private final String hspHitFromKey = "Hsp_hit-from";
  private final String hspHitToKey = "Hsp_hit-to";
  private final String hspPositiveKey = "Hsp_positive";
  private final String hspGapsKey = "Hsp_gaps";
  private final String hspAlignLenKey = "Hsp_align-len";
  private final String hspQueryFrameKey = "Hsp_query-frame";
  private final String hspHitFrameKey = "Hsp_hit-frame";
  private final String hspEvalueKey = "Hsp_evalue";
  
  public BlastSubjectMetadata buildSubMetadata(JSONObject hitJson, List<BlastSubjectHsp> subjectHspList) {

    int hitNumber = hitJson.getInt(hitNumKey);
    String hitDef = hitJson.getString(hitDefKey);
    int hitLen = hitJson.getInt(hitLenKey);

    String genbankId = StringUtils.substringBetween(hitDef, giKey, gbKey);
    String genbankAccession = StringUtils.substringBetween(hitDef, gbKey, boldKey);
    String boldId = StringUtils.substringBetween(hitDef, boldKey, geneKey);
    String targetMarker = StringUtils.substringBetween(hitDef, geneKey, latLonKey); 
    String coordinates;
    String catalogNumber;
    String scientificName;

    if (hitDef.contains(catnrKey)) {
      coordinates = StringUtils.substringBetween(hitDef, latLonKey, catnrKey);
      catalogNumber = StringUtils.substringBetween(hitDef, catnrKey, emptySpace);
      scientificName = StringUtils.substringAfter(hitDef, catnrKey + catalogNumber);
    } else {
      catalogNumber = "";
      coordinates = StringUtils.substringBetween(hitDef, latLonKey, emptySpace);
      scientificName = StringUtils.substringAfter(hitDef, latLonKey + coordinates);
    }

    return new BlastSubjectMetadata(hitNumber, genbankId, genbankAccession, boldId, targetMarker, coordinates,
            catalogNumber, scientificName, hitLen, subjectHspList, !catalogNumber.isEmpty());
  }
  
  public BlastSubjectHsp buildSubHits(JSONObject hspJson) {
    double hspScore = hspJson.getDouble(hspScoreKey);
    double bitScore = hspJson.getDouble(hspBitScoreKey);

    int hspIdentity = hspJson.getInt(hspIdentityKey);
    String qseq = hspJson.getString(hspQseqKey);
    String hseq = hspJson.getString(hspHseqKey);
    String midline = hspJson.getString(hspMidlineKey);
    int hspQueryFrom = hspJson.getInt(hspQueryFromKey);
    int hspQueryTo = hspJson.getInt(hspQueryToKey);
    int hspHitFrom = hspJson.getInt(hspHitFromKey);
    int hspHitTo = hspJson.getInt(hspHitToKey);
    int hspPositive = hspJson.getInt(hspPositiveKey);
    int hspGaps = hspJson.getInt(hspGapsKey);
    int hspAlignLen = hspJson.getInt(hspAlignLenKey);
    int hspQueryFrame = hspJson.getInt(hspQueryFrameKey);
    int hspHitFrame = hspJson.getInt(hspHitFrameKey);

    return new BlastSubjectHsp(hspScore, bitScore, calculateEvalue(hspJson.getDouble(hspEvalueKey)), hspQueryFrom,
            hspQueryTo, hspHitFrom, hspHitTo, hspIdentity, hspPositive, hspGaps, hspAlignLen,
            calculatePercentage(hspIdentity, hspAlignLen), qseq, hseq, midline, hspQueryFrame, hspHitFrame);

  }
  
  private long calculatePercentage(int hspIdentity, int hspAlignLen) {
    double identities = (double) hspIdentity;
    double alignLength = (double) hspAlignLen;
    return (long) Math.floor((identities / alignLength) * 100 + 0.5d);
  }

  private double calculateEvalue(double dEvalue) {
    try {
      return Math.round(dEvalue * 100) / 100.0d;
    } catch (NumberFormatException e) {
      return 0;
    }
  }
}
