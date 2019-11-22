package se.nrm.dina.dnakey.logic;
 
import org.apache.commons.lang.StringUtils;
import java.io.*;
import lombok.extern.slf4j.Slf4j;
import org.biojava.nbio.ws.alignment.qblast.BlastProgramEnum;
import org.biojava.nbio.ws.alignment.qblast.NCBIQBlastAlignmentProperties;
import org.biojava.nbio.ws.alignment.qblast.NCBIQBlastService;

/**
 *
 * @author idali
 */
//@Stateless
@Slf4j
public class GenbankBlaster implements Serializable {

  private NCBIQBlastService service;
  private NCBIQBlastAlignmentProperties props;
  private final String newLine = "\n";
  private final String replaceChars = "%0A";
  private final String nr = "nr";

  public GenbankBlaster() {
  }

  /**
   * Blast from remote genbank database
   * 
   * @param fastSequence
   * @return String
   */
  public String remoteGenbankBlast(String fastSequence) {
    log.info("remoteGenbankBlast");
    service = new NCBIQBlastService();
    props = new NCBIQBlastAlignmentProperties();
    
    // set alignment options 
    props.setBlastProgram(BlastProgramEnum.blastn);
    props.setBlastDatabase(nr);
    try { 
      fastSequence = StringUtils.replace(fastSequence, newLine, replaceChars);
      return service.sendAlignmentRequest(fastSequence, props);
    } catch (Exception ex) {
      return null;
    }
  }
}
