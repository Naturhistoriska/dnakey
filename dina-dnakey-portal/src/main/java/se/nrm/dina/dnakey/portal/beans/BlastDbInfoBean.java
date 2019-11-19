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

  @PostConstruct
  public void init() {
    nrmTotal = dbInfo.getNrmDbTotal();
    boldTotal = dbInfo.getBoldDbTotal();
    genbankTotal = dbInfo.getGenbankDbTotal();
  }
 
  public String getBoldTotalSequence() {
    return boldTotal;
  }

  public String getGenBankTotalSequence() {
    return genbankTotal;
  }

  public String getNrmTotalSequence() {
    return nrmTotal;
  } 
}
