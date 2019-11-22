package se.nrm.dina.dnakey.portal.beans;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.extern.slf4j.Slf4j;
import se.nrm.dina.dnakey.logic.BlastDbInfo; 

/**
 *
 * Controller for fetch blaster database information
 * 
 * @author idali
 */
@Named("dbInfo")
@ApplicationScoped
@Slf4j
public class BlastDbInfoBean implements Serializable {
 
  private String nrmTotal;
  private String boldTotal;
  private String genbankTotal;
  
  @Inject
  private BlastDbInfo dbInfo;

  public BlastDbInfoBean() {
  }

  /**
   * init method runs after this controller has initialized
   */ 
  @PostConstruct
  public void init() {
    nrmTotal = dbInfo.getNrmDbTotal();
    boldTotal = dbInfo.getBoldDbTotal();
    genbankTotal = dbInfo.getGenbankDbTotal();
  }
 
  /**
   * This method returns total sequences in bold blastdb as string
   * 
   * @return String
   */
  public String getBoldTotalSequence() {
    return boldTotal;
  }

  /**
   * This method returns total sequences in genbank blastdb as string
   * 
   * @return String
   */
  public String getGenBankTotalSequence() {
    return genbankTotal;
  }

  /**
   * This method returns total sequences in nrm blastdb as string
   * 
   * @return String
   */
  public String getNrmTotalSequence() {
    return nrmTotal;
  } 
}
