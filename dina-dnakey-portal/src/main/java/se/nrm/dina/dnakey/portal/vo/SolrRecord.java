package se.nrm.dina.dnakey.portal.vo;

import java.util.Date;
import org.apache.commons.lang.StringUtils;
import org.apache.solr.client.solrj.beans.Field;

/**
 *
 * @author idali
 */
public class SolrRecord {

  @Field
  String id;
  @Field
  String catalogNumber;
  @Field
  String txFullName;
  @Field
  Date startDate;
  @Field
  String locality;
  @Field
  double latitude;
  @Field
  double longitude;
  @Field
  String latitudeText;
  @Field
  String longitudeText;
  @Field
  String[] commonName;
  @Field
  String collectionName;
  @Field
  public String[] collector;
  @Field
  String country;
  @Field
  String continent;
  @Field
  String morphbankId;
  @Field
  String[] morphbankImageId;
  @Field
  boolean image; 
  @Field
  boolean map;
  
  private StringBuilder localitySb;
  private final String emptySpace = " ";
 
  
  public String getCollectionName() {
    return collectionName;
  }

  public void setCollectionName(String collectionName) {
    this.collectionName = collectionName;
  }

  public String getCatalogNumber() {
    return catalogNumber;
  }

  public void setCatalogNumber(String catalogNumber) {
    this.catalogNumber = catalogNumber;
  }

  public void setId(String id) {
    this.id = id;
  }
   

  public String getId() {
    return id;
  }

  public String getTxFullName() {
    return txFullName;
  }

  public void setTxFullName(String txFullName) {
    this.txFullName = txFullName;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public void setLocality(String locality) {
    this.locality = locality;
  }

  public Date getStartDate() {
    return startDate;
  }

  public String getLocality() {
    return locality;
  }

  public String getLatitudeText() {
    return latitudeText;
  }

  public String getLongitudeText() {
    return longitudeText;
  }

  public double getLatitude() {
    return latitude;
  }

  public void setLatitude(double latitude) {
    this.latitude = latitude;
  }

  public double getLongitude() {
    return longitude;
  }

  public void setLongitude(double longitude) {
    this.longitude = longitude;
  } 

  public String getMorphbankId() {
    return morphbankId;
  }

  public String[] getMorphbankImageId() {
    return morphbankImageId;
  }

  public String[] getCommonName() {
    return commonName;
  }

  public void setCommonName(String[] commonName) {
    this.commonName = commonName;
  }

  public String[] getCollector() {
    return collector;
  }

  public void setCollector(String[] collector) {
    this.collector = collector;
  }

  public String getCollectors() {
    return StringUtils.join(collector, ", ");
  }

  public String getCommonNames() {
    return StringUtils.join(commonName, ", ");
  }

  public String getContinent() {
    return continent;
  }

  public void setContinent(String continent) {
    this.continent = continent;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public boolean isImage() {
    return image;
  }

  public void setImage(boolean image) {
    this.image = image;
  } 

  public boolean isMap() {
    return map;
  }

  public void setMap(boolean map) {
    this.map = map;
  }
   
  public String getCoordinateString() {
    return map ? latitudeText + " --- " + longitudeText : null; 
  }
  
  public boolean getHasCoordinates() {
    return map;
  }

  public void setLatitudeText(String latitudeText) {
    this.latitudeText = latitudeText;
  }

  public void setLongitudeText(String longitudeText) {
    this.longitudeText = longitudeText;
  }

  public void setMorphbankId(String morphbankId) {
    this.morphbankId = morphbankId;
  }

  public void setMorphbankImageId(String[] morphbankImageId) {
    this.morphbankImageId = morphbankImageId;
  }
  
  
  
  public String getLocalityWithCountryAndContinent() {
    localitySb = new StringBuilder();
    localitySb.append(locality);
    if(country != null && !country.isEmpty()) {
      localitySb.append(emptySpace);
      localitySb.append(country);
    }
    if(continent != null && !continent.isEmpty()) {
      localitySb.append(emptySpace);
      localitySb.append(continent);
    } 
    return localitySb.toString();
  }
 
  @Override
  public String toString() {
    return "Detail : [ " + catalogNumber + " ] Locality : " + locality + "  Collection : " + collectionName;
  }
}
