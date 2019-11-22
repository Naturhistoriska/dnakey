package se.nrm.dina.dnakey.logic.metadata;

import java.util.ArrayList;
import java.util.List;  
import se.nrm.dina.dnakey.logic.util.MetadataHelper;

/**
 *
 * @author idali
 */
public final class BlastSubjectHsp implements Comparable {

  private static final String TEXT_BLACK = "blacktext";
  private static final String TEXT_GRAY = "graytext";
  private final String plus = "Plus";
  private final String minus = "Minus";
  private final String strand = "Strand = ";
  private final String separator = "/";
  private final String percentageString = "%"; 
  private final String emptyString = ""; 

  private final double hspScore;
  private final double hspBitScore;
  private final double hspEvalue;
  private final int hspQueryFrom;
  private final int hspQueryTo;
  private final int hspHitFrom;
  private final int hspHitTo;
  private final int hspIdentity;
  private final int hspPositive;
  private final int hspGaps;
  private final int hspAlignLen;
  private final String hspQseq;
  private final String hspHseq;
  private final String hspMidline;
  private final int hspQueryFrame;
  private final int hspHitFrame;
  private final long percentage;

  private StringBuilder hspStrandSb;
  
  private static final String NUMBER_FORMAT_ERROR = "Not available.";

  public BlastSubjectHsp(final double hspScore, final double hspBitScore, final double hspEvalue,
          final int hspQueryFrom, final int hspQueryTo, final int hspHitFrom,
          final int hspHitTo, final int hspIdentity, final int hspPositive,
          final int hspGaps, final int hspAlignLen, final long percentage,
          final String hspQseq, final String hspHseq, final String hspMidline,
          final int hspQueryFrame, final int hspHitFrame) {
    this.hspScore = hspScore;
    this.hspBitScore = hspBitScore;
    this.hspEvalue = hspEvalue;
    this.hspQueryFrom = hspQueryFrom;
    this.hspQueryTo = hspQueryTo;
    this.hspHitFrom = hspHitFrom;
    this.hspHitTo = hspHitTo;
    this.hspIdentity = hspIdentity;
    this.hspPositive = hspPositive;
    this.hspGaps = hspGaps;
    this.hspAlignLen = hspAlignLen;
    this.hspQseq = hspQseq;
    this.hspHseq = hspHseq;
    this.hspMidline = hspMidline;
    this.hspQueryFrame = hspQueryFrame;
    this.hspHitFrame = hspHitFrame;
    this.percentage = percentage;
  }

  public static String getNUMBER_FORMAT_ERROR() {
    return NUMBER_FORMAT_ERROR;
  }

  public int getHspAlignLen() {
    return hspAlignLen;
  }

  public double getHspBitScore() {
    return hspBitScore;
  }

  public double getHspEvalue() {
    return hspEvalue;
  }

  public int getHspGaps() {
    return hspGaps;
  }

  public int getHspHitFrame() {
    return hspHitFrame;
  }

  public int getHspHitFrom() {
    return hspHitFrom;
  }

  public int getHspHitTo() {
    return hspHitTo;
  }

  public String getHspHseq() {
    return hspHseq;
  }

  public int getHspIdentity() {
    return hspIdentity;
  }

  public String getHspMidline() {
    return hspMidline;
  }

  public int getHspPositive() {
    return hspPositive;
  }

  public String getHspQseq() {
    return hspQseq;
  }

  public int getHspQueryFrame() {
    return hspQueryFrame;
  }

  public int getHspQueryFrom() {
    return hspQueryFrom;
  }

  public int getHspQueryTo() {
    return hspQueryTo;
  }

  public double getHspScore() {
    return hspScore;
  }

  public String getTextColor() {
    return percentage >= 99 ? TEXT_BLACK : TEXT_GRAY;
  }

  public Long getPercentage() {
    return percentage;
  }

  public String getIdentitiesPercentage() {
    try {
      return String.valueOf(percentage) + percentageString;
    } catch (NumberFormatException ex) {
    }
    return emptyString;
  }

  public String getGapsPercentage() {
    try {
      return String.valueOf((long) Math.floor((Double.valueOf(hspGaps) / Double.valueOf(hspAlignLen)) * 100)) + percentageString;
    } catch (NumberFormatException ex) {
    }
    return emptyString;
  }

  public String getHspStrand() {
    hspStrandSb = new StringBuilder();
    try {
      hspStrandSb.append(strand);
      hspStrandSb.append(hspQueryFrame >= 0 ? plus : minus);
      hspStrandSb.append(separator);
      hspStrandSb.append(hspHitFrame >= 0 ? plus : minus);
    } catch (NumberFormatException ex) {
    }
    return hspStrandSb.toString();
  }
 
  public List<AlignSequence> getSequencesAlignment() {

    List<AlignSequence> alignSequences = new ArrayList<>(); 
    int count = hspAlignLen / 60;
    int remainder = hspAlignLen % 60; 
    return count > 0 ? MetadataHelper.getInstance().buildAlignment(count, alignSequences, remainder, this)
            : MetadataHelper.getInstance().buildNoCountAlignment(alignSequences, this); 
  }
 
  @Override
  public int compareTo(Object o1) {
    return (this.getIdentitiesPercentage().compareTo(((BlastSubjectHsp) o1).getIdentitiesPercentage()));
  }
}
