package se.nrm.dina.dnakey.portal.util;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author idali
 */
public class ConstantString {

    private static ConstantString instance = null;

    private static final Map<String, String> MAP;
    private final String emptyString = "";

    private final String NATURARV_URL_ENCODE = "param=dnakey&catalogNumber=";
    private final String DEFAULT_BLASTDB = "nrm";
    private final String UTF_8 = "UTF-8";

    /**
     * white space chars - " \t\r\n\f"
     */
    private final String WHITE_SPACE_CHARS = "[ \t\r\n\f]";

//  public final String XML_OUTPUT = "xml";
//  public final String TABULAR_OUTPUT = "tabular";
    private final String nrmDbNameSv = "Svenska ryggradsdjur (COI, 16S)";
    private final String nrmDbNameEn = "Swedish vertebrate animals (COI, 16S)";
    private final String boldDbNameSv = "Barkodsekvenser för svenska organismer (COI, matK, rbcL, 16S*)";
    private final String boldDbNameEn = "Barcode sequences for Swedish organisms (COI, matK, rbcL, 16S*)";
    private final String genbankDbNameSv = "Barkodsekvenser från Genbank (COI, matK, rbcL, 16S*)";
    private final String genbankDbNameEn = "Barcode sequences from Genbank (COI, matK, rbcL, 16S*)";

    static {
        MAP = new HashMap<>();
        MAP.put("feedback_en", "Feedback");
        MAP.put("feedback_sv", "Återkoppling");
        MAP.put("catalognum_en", "Catalog #");
        MAP.put("catalognum_sv", "Katalognr");
        MAP.put("commenname_en", "Swedish name");
        MAP.put("commenname_sv", "Svenskt namn");
        MAP.put("latinname_en", "Scientific name");
        MAP.put("latinname_sv", "Vetenskapligt namn");
        MAP.put("institution_en", "Institution");
        MAP.put("institution_sv", "Institution");
        MAP.put("collection_en", "Collection");
        MAP.put("collection_sv", "Samling");

        MAP.put("remarks_en", "Remarks");
        MAP.put("remarks_sv", "Änmärkningar");
        MAP.put("locality_en", "Locality");
        MAP.put("locality_sv", "Lokal");
        MAP.put("coordinates_en", "Coordinates");
        MAP.put("coordinates_sv", "Koordinater");
        MAP.put("date_en", "Date");
        MAP.put("date_sv", "Datum");

        MAP.put("collector_en", "Collector");
        MAP.put("collector_sv", "Insamlare");

        MAP.put("description_en", "Description");
        MAP.put("description_sv", "Beskrivning");

        MAP.put("sendmail_en", "Mail has been sent");
        MAP.put("sendmail_sv", "Post har skickats");
        MAP.put("sendmail_es", "Mail has been sent");

        MAP.put("validationfailed_en", "Sequence validation failed: ");
        MAP.put("validationfailed_sv", "Sekvens validering misslyckades: ");
        MAP.put("inputdata_en", "Input sequence(s) in FASTA format or upload file(s) in FASTA format.");
        MAP.put("inputdata_sv", "Mata in sekvens(er) i FASTA - format eller ladda upp fil(er) i FASTA - format.");
        MAP.put("blastfailed_en", "Blast failed:");
        MAP.put("blastfailed_sv", "Blast misslyckades");
        MAP.put("invalidSequence_en", "Invalid sequence or fasta file");
        MAP.put("invalidSequence_sv", "Ogiltig sekvens eller FASTA fil");
        MAP.put("blastnoresult_en", "Unable to match any records in the selected database");
        MAP.put("blastnoresult_sv", "Det går inte att matcha några poster i den valda databasen");
        MAP.put("searchanothdb_en", "Search in ");
        MAP.put("searchanothdb_sv", "Sök i ");

        MAP.put("file_uploaded_en", "File is uploaded");
        MAP.put("file_uploaded_sv", "Filen är uppladdad");

        MAP.put("query_sv", "Fråga");
        MAP.put("query_en", "Query");

        MAP.put("subject_sv", "Subjk");
        MAP.put("subject_en", "Subjc");
    }

    public static synchronized ConstantString getInstance() {
        if (instance == null) {
            instance = new ConstantString();
        }

        return instance;
    }

    public String getText(String key) {
        return MAP.containsKey(key) ? MAP.get(key) : emptyString;
//        
//        String text = MAP.get(key);
//        if (text == null) {
//            return emptyString;
//        }
//        return text;
    }

    public String getNaturarvUrlEncode() {
        return NATURARV_URL_ENCODE;
    }

    public String getDefaultBlastDb() {
        return DEFAULT_BLASTDB;
    }

    /**
     * white space chars - " \t\r\n\f"
     *
     * @return
     */
    public String getWhiteSpaceChars() {
        return WHITE_SPACE_CHARS;
    }

    public String getUtf8() {
        return UTF_8;
    }

    public String getNrmDbName(boolean isSwedish) {
        return isSwedish ? nrmDbNameSv : nrmDbNameEn;
    }

    public String getBoldDbName(boolean isSwedish) {
        return isSwedish ? boldDbNameSv : boldDbNameEn;
    }

    public String getGenbankDbName(boolean isSwedish) {
        return isSwedish ? genbankDbNameSv : genbankDbNameEn;
    }

}
