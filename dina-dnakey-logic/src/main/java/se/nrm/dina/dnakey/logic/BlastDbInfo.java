package se.nrm.dina.dnakey.logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.Startup;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import se.nrm.dina.dnakey.logic.config.ConfigProperties;
import se.nrm.dina.dnakey.logic.util.HelpClass; 

/**
 *
 * @author idali
 */
@ApplicationScoped
@Startup
@Slf4j
public class BlastDbInfo implements Serializable {

  private final String NRM_DB = "nrm";
  private final String BOLD_DB = "bold";
  private final String GENBANK_DB = "genbank";

  private final String sequences = "sequences";
  private final String sequencesWithSpace = " sequences";
  private final String emptyString = "";

  private static String dbPath;
  private static String dbInfoPath;

  private String nrmCount;
  private String boldCount;
  private String genbankCount;

  @Inject
  private ConfigProperties config;

  public BlastDbInfo() {
  }

  public BlastDbInfo(ConfigProperties config) {
    this.config = config;
  }
  /**
   * This method runs when application startup
   */
  @PostConstruct
  public void init() {
    log.info("init");

    dbPath = config.getDbPath();
    dbInfoPath = config.getDbinfoPath();
     
    nrmCount = getTotal(NRM_DB);
    boldCount = getTotal(BOLD_DB);
    genbankCount = getTotal(GENBANK_DB); 
  }

  /**
   * To return total number of sequences in nrm blastDatabase
   * @return 
   */
  public String getNrmDbTotal() {
    return nrmCount == null ? getTotal(NRM_DB) : nrmCount;
  }
  
  /**
   * To return total number of sequences in bold blastDatabase
   * @return 
   */
  public String getBoldDbTotal() {
    return boldCount == null ? getTotal(BOLD_DB) : boldCount;
  }
  
  /**
   * To return total number of sequences in genbank blastDatabase
   * @return 
   */
  public String getGenbankDbTotal() {
    return genbankCount == null ? getTotal(GENBANK_DB) : genbankCount;
  }
 
  private String getTotal(String db) { 
    InputStream is = null;
    Process process = null;
    Runtime runtime = Runtime.getRuntime();
    try {
      process = runtime.exec(HelpClass.getInstance().buildExecDbInfoCommand(dbInfoPath, dbPath, db));
      is = process.getInputStream();
      String line = new BufferedReader(new InputStreamReader(is))
              .lines()
              .filter(l -> l.contains((sequences)))
              .findFirst().orElse(emptyString);
      return StringUtils.substringBefore(line, sequencesWithSpace).trim(); 
    } catch (IOException ex) {
      log.error(ex.getMessage());
    } finally {
      if (is != null) {
        try {
          is.close();
        } catch (IOException ex) {
          log.error(ex.getMessage());
        }
      }
      if (process != null && process.isAlive()) {
        process.destroy();
      }
    }
    return null;
  }
}
