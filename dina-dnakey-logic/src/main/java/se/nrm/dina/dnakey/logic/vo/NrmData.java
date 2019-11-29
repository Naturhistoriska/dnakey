package se.nrm.dina.dnakey.logic.vo;

import java.util.Date;

/**
 *
 * @author idali
 */
public class NrmData {
  
  private final String catalogNumber;
  private final String taxonName;
  private final String collectionName;
  private final String commonNames;
  private final String locality;
  private final String coordinates;
  private final Date startDate;
  private final String collectors;
  private final boolean hasImages; 
  private final String[] morphbankImageIds;
  private final String morphbankId; 
  private final String country;
  private final String continent;
      
  private StringBuilder nameAndCatlogNumberSb;
  private final String catNr = " [Catalog nr: ";
  private final String endBlacket = " ]";

  
  public NrmData(final String catalogNumber, final String taxonName, final String collectionName,
           final String commonNames, final String locality, final String coordinates, final Date startDate, 
           final String collectors, final boolean hasImages, String[] morphbankImageIds, 
           final String morphbankId, final String country, final String continent) {
    this.catalogNumber = catalogNumber;
    this.taxonName = taxonName;
    this.collectionName = collectionName;
    this.commonNames = commonNames;
    this.locality = locality;
    this.coordinates = coordinates;
    this.startDate = startDate;
    this.collectors = collectors;
    this.hasImages = hasImages; 
    this.morphbankImageIds = morphbankImageIds;
    this.morphbankId = morphbankId;
    this.country = country;
    this.continent = continent;
  }

  public String getCatalogNumber() {
    return catalogNumber;
  }

  public String getTaxonName() {
    return taxonName;
  }

  public String getCollectionName() {
    return collectionName;
  }

  public String getCommonNames() {
    return commonNames;
  }

  public String getLocality() {
    return locality;
  }

  public String getCoordinates() {
    return coordinates;
  }

  public Date getStartDate() {
    return startDate;
  }

  public String getCollectors() {
    return collectors;
  }

  public boolean isHasImages() {
    return hasImages;
  } 

  public String[] getMorphbankImageIds() {
    return morphbankImageIds;
  } 

  public String getMorphbankId() {
    return morphbankId;
  } 
 
  public String getCountry() {
    return country;
  }

  public String getContinent() {
    return continent;
  }
   
  public String getNameAndCatalogNumber() {
    nameAndCatlogNumberSb = new StringBuilder();
    nameAndCatlogNumberSb.append(taxonName)
            .append(catNr)
            .append(catalogNumber)
            .append(endBlacket);
    return nameAndCatlogNumberSb.toString();
  } 
}
