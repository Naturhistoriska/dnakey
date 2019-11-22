package se.nrm.dina.dnakey.logic.util;

import java.util.List;
import org.apache.commons.lang.StringUtils;
import se.nrm.dina.dnakey.logic.metadata.AlignSequence;
import se.nrm.dina.dnakey.logic.metadata.BlastSubjectHsp;

/**
 *
 * @author idali
 */
public class MetadataHelper {
  
  private final String dash = "-";
  private final String q = "q";
  private final String s = "s";
  private final String m = "m";
  private final String e = "e";
  private final String emptyString = "";


  private static MetadataHelper instance = null;

  public static MetadataHelper getInstance() {
    synchronized (MetadataHelper.class) {
      if (instance == null) {
        instance = new MetadataHelper();
      }
    }
    return instance;
  }
   
  public List<AlignSequence> buildNoCountAlignment(List<AlignSequence> alignSequences, BlastSubjectHsp subject) {
    alignSequences.add(new AlignSequence(String.valueOf(subject.getHspQueryFrom()), String.valueOf(subject.getHspHitTo()), subject.getHspQseq(), q));
    alignSequences.add(new AlignSequence(emptyString, emptyString, subject.getHspMidline(), m));
    alignSequences.add(new AlignSequence(String.valueOf(subject.getHspQueryFrom()), String.valueOf(subject.getHspHitTo()), subject.getHspQseq(), s));
    return alignSequences;
  }
  
  public List<AlignSequence> buildAlignment(int count, List<AlignSequence> alignSequences, int remainder, BlastSubjectHsp subject) {
    String qrySeq = subject.getHspQseq();
    String sbjSeq = subject.getHspHseq();
    String midLine = subject.getHspMidline();
    int qstart = subject.getHspQueryFrom();
    int qend = subject.getHspHitTo();

    int hstart = subject.getHspHitFrom(); 
    int hend = subject.getHspHitTo(); 

    for (int i = 1; i <= count; i++) {
      String qry = StringUtils.substring(qrySeq, 0, 60);
      String seq = StringUtils.substring(sbjSeq, 0, 60);
      String mid = StringUtils.substring(midLine, 0, 60);

      qrySeq = StringUtils.substring(qrySeq, 60);
      sbjSeq = StringUtils.substring(sbjSeq, 60);
      midLine = StringUtils.substring(midLine, 60);

      int numOfChartq = MetadataHelper.getInstance().getNumOfChart(qry);
      int numOfCharth = MetadataHelper.getInstance().getNumOfChart(seq); 

      int qnumEnd = qstart + 59 - numOfChartq;
      int hnumEnd = hstart + 59 - numOfCharth;

      alignSequences.add(new AlignSequence(String.valueOf(qstart), String.valueOf(qnumEnd), qry, q));
      alignSequences.add(new AlignSequence(emptyString, emptyString, mid, m));
      alignSequences.add(new AlignSequence(String.valueOf(hstart), String.valueOf(hnumEnd), seq, s));
      alignSequences.add(new AlignSequence(emptyString, emptyString, emptyString, e));

      qstart = qnumEnd + 1;
      hstart = hnumEnd + 1;
    }

    if (remainder > 0) {
      alignSequences.add(new AlignSequence(String.valueOf(qstart), String.valueOf(qend), qrySeq, q));
      alignSequences.add(new AlignSequence(emptyString, emptyString, midLine, m));
      alignSequences.add(new AlignSequence(String.valueOf(hstart), String.valueOf(hend), sbjSeq, s));
    }
    return alignSequences;
  } 
  
  private int getNumOfChart(String sequence) {
    return StringUtils.contains(sequence, dash) ? 
            StringUtils.countMatches(sequence, dash) : 0;
  }
}
