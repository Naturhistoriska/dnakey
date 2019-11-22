package se.nrm.dina.dnakey.logic.metadata;

import org.apache.commons.lang.StringUtils;

/**
 *
 * @author idali
 */
public class AlignSequence {

  private String start;
  private String end;
  private String seq;

  private final String q = "q";
  private final String s ="s";
  private final String subjc = "Subjc  ";
  private final String query = "Query  ";
  private final String emptyString = "";

  public AlignSequence() {

  }

  public AlignSequence(String start, String end, String seq, String type) {
    this.start = StringUtils.contains(type, q) ? query + start : StringUtils.contains(type, s) ? subjc + start : emptyString; 
    this.end = end;
    this.seq = seq;
  }

  public String getEnd() {
    return end;
  }

  public String getSeq() {
    return seq;
  }

  public String getStart() {
    return start;
  }
}
