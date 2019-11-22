package se.nrm.dina.dnakey.logic.metadata;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j; 
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

/**
 *
 * @author idali
 */
@Slf4j
public class MetadataDataFactory implements Serializable {

  private JSONObject blastJson;
  private final String blastOutput = "BlastOutput";
  private final String blastVersion = "BlastOutput_version";
  private final String blastProgram = "BlastOutput_program";
  private final String blastReference = "BlastOutput_reference";
  private final String blastDb = "BlastOutput_db";
  private final String blastQueryId = "BlastOutput_query-ID";
  private final String blastQueryDef = "BlastOutput_query-def";
  private final String blastQueryLen = "BlastOutput_query-len";
  private final String blastOutputItrs = "BlastOutput_iterations";
  private final String iteration = "Iteration";
  private final String statistics = "Statistics";
  private final String itrStat = "Iteration_stat";
  private final String statisticsDbNum = "Statistics_db-num";
  private final String statisticsDbLen = "Statistics_db-len";
  private final String itrHits = "Iteration_hits";
  private final String hit = "Hit";
  private final String hitHsps = "Hit_hsps";
  private final String hsp = "Hsp";
   
  private JsonToMetadata jsonToMetadata;
  
  public static MetadataDataFactory getInstance() {
    return new MetadataDataFactory();
  }

  public BlastMetadata buildBlastMetadataByJson(String metadata) {
    log.info("buildBlastMetadataByJson");
    
    jsonToMetadata = new JsonToMetadata();

    List<BlastSubjectMetadata> subjectMetadataList = new ArrayList<>();
    List<BlastSubjectMetadata> lowMatchList = new ArrayList<>();

    blastJson = XML.toJSONObject(metadata).getJSONObject(blastOutput);
    String version = blastJson.getString(blastVersion);
    String program = blastJson.getString(blastProgram);
    String reference = blastJson.getString(blastReference);
    String blastDatabase = blastJson.getString(blastDb);
    String query = blastJson.getString(blastQueryId) + " " + blastJson.optString(blastQueryDef);;
    int queryLength = blastJson.getInt(blastQueryLen);
    JSONObject itrJson = blastJson.getJSONObject(blastOutputItrs).getJSONObject(iteration);
    JSONObject statJson = itrJson.getJSONObject(itrStat).getJSONObject(statistics);

    try {

      int statisticDbNumber = statJson.getInt(statisticsDbNum);
      int statisticDbLength = statJson.getInt(statisticsDbLen);

      boolean hasHit = false;
      JSONObject itrHitsJson = itrJson.optJSONObject(itrHits);
      if (itrHitsJson != null) {
        JSONArray hitsJsonArray = itrHitsJson.getJSONArray(hit);
        hasHit = true;
        for (int i = 0; i < hitsJsonArray.length(); i++) {
          JSONObject hitJson = hitsJsonArray.getJSONObject(i);

          JSONObject hspJson = hitJson.getJSONObject(hitHsps).getJSONObject(hsp);

          BlastSubjectHsp subHsp = jsonToMetadata.buildSubHits(hspJson);
          List<BlastSubjectHsp> subjectHspList = new ArrayList<>();
          subjectHspList.add(subHsp);

          BlastSubjectMetadata subjectMetadata = jsonToMetadata.buildSubMetadata(hitJson, subjectHspList);

          if (subHsp.getPercentage() < 99) {
            lowMatchList.add(subjectMetadata);
          } else {
            subjectMetadataList.add(subjectMetadata);
          }
        }
      }
      return new BlastMetadata(program, version, reference, blastDatabase, query, queryLength, statisticDbNumber,
              statisticDbLength, subjectMetadataList, lowMatchList, false, hasHit); 
    } catch (JSONException ex) {
      log.error(ex.getMessage());
    }
    return new BlastMetadata();
  }  
}
