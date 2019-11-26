package se.nrm.dina.dnakey.logic.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.lang.math.NumberUtils;

/**
 *
 * @author idali
 */
public class HelpClass {

//    private static final String MB_BASE_URL = "http://morphbank.nrm.se/";
//  private static final String MB_THUMB_URL = "http://images.morphbank.nrm.se";
//  private static final String MB_IMAGE_URL = "http://morphbank.nrm.se/Browse/ByImage/";
//  private static final String QUERY_THUMB = "&imgType=thumb";
//  private static final String QUERY_ID = "?id=";

  private static final DateFormat DFT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:SSS'Z'");
  private static final DateFormat DF = new SimpleDateFormat("yyyy-MM-dd");

  private final String db = " -db ";
  private final String dbInfo = " -info";
  private final String query = " -query ";
  
  private final String outputStyle2 = " -task blastn -dust no -outfmt 5 -num_alignments 10 -num_descriptions 10 -parse_deflines";       // xml output
  
  private final String genbankQueryUrl = "http://www.ncbi.nlm.nih.gov/blast/Blast.cgi?QUERY=";
  private final String genbankSearchQuery = "&DATABASE=nr&HITLIST_SIZE=10&FILTER=L&EXPECT=10&FORMAT_TYPE=HTML&PROGRAM=blastn&CLIENT=web&SERVICE=plain&NCBI_GI=on&PAGE=Nucleotides&CMD=Put";

  private StringBuilder genbankSearchSb;
    
  private StringBuilder execDbInfoCommandSb;
  private StringBuilder execBlastCommandSb;

  private static HelpClass instance = null;

  public static HelpClass getInstance() {
    synchronized (HelpClass.class) {
      if (instance == null) {
        instance = new HelpClass();
      }
    }
    return instance;
  }

//  public static String getMorphybankImageURLById(String imageid) {
//    StringBuilder sb = new StringBuilder();
//    sb.append(MB_IMAGE_URL);
//    sb.append(imageid);
//    return sb.toString();
//  }
//
//  public static String getMorphybankThumbURLById(String imageid) {
//    StringBuilder sb = new StringBuilder();
//    sb.append(MB_THUMB_URL);
//    sb.append(QUERY_ID);
//    sb.append(imageid);
//    sb.append(QUERY_THUMB);
//    return sb.toString();
//  }

  public static double intToDouble(int value) {
    return (double) value;
  }

  public static int stringToInteger(String strNumber) {
    return isNumeric(strNumber) ? Integer.parseInt(strNumber) : 0;
  }

  public static String stringToPercentageString(String strNumber) {
    if (isNumeric(strNumber)) {
      Double d = Double.valueOf(strNumber);
      d = Math.round(d * 100) / 100.0d;
      return String.valueOf(d);
    } else {
      return strNumber;
    }
  }

  public static boolean isNumeric(String arg) {
    return NumberUtils.isNumber(arg);
  }

  public static String dateToStringWithTime(Date date) {
    if (date == null) {
      return null;
    } else {
      return DFT.format(date);
    }
  }

  /**
   * Convert Date to String with "yyyy-MM-dd" format
   *
   * @param date - Date
   * @return String
   */
  public static String dateToString(Date date) {
    if (date == null) {
      return null;
    } else {
      return DF.format(date);
    }
  }

  public String buildExecDbInfoCommand(String dbInfoPath, String dbPath, String database) { 
    execDbInfoCommandSb = new StringBuilder();
    execDbInfoCommandSb.append(dbInfoPath);
    execDbInfoCommandSb.append(db);
    execDbInfoCommandSb.append(dbPath);
    execDbInfoCommandSb.append(database);
    execDbInfoCommandSb.append(dbInfo);
    return execDbInfoCommandSb.toString().trim();
  }
   
  public String buildBlastCommand(String fastafilePath, String dbName, String blastPath, String blastDbPath) { 
    execBlastCommandSb = new StringBuilder();
    execBlastCommandSb.append(blastPath);
    execBlastCommandSb.append(query);
    execBlastCommandSb.append(fastafilePath);
    execBlastCommandSb.append(db);
    execBlastCommandSb.append(blastDbPath);
    execBlastCommandSb.append(dbName);
    execBlastCommandSb.append(outputStyle2);

    return execBlastCommandSb.toString();
  }
  
  public String buildGenbankSearch(String sequence) {
    genbankSearchSb = new StringBuilder();
    genbankSearchSb.append(genbankQueryUrl);
    genbankSearchSb.append(sequence);
    genbankSearchSb.append(genbankSearchQuery);
    return genbankSearchSb.toString();
  }
}
