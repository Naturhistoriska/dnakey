package se.nrm.dina.dnakey.portal.controller;

import java.io.Serializable; 
import javax.enterprise.context.SessionScoped; 
import javax.inject.Named;
import lombok.extern.slf4j.Slf4j; 
import org.apache.commons.lang3.StringUtils;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import se.nrm.dina.dnakey.logic.metadata.BlastSubjectMetadata;  
import se.nrm.dina.dnakey.logic.util.HelpClass;
import se.nrm.dina.dnakey.logic.vo.NrmData; 

/**
 *
 * @author idali
 */
@Named("map")
@SessionScoped
@Slf4j
public class Map implements Serializable {
  
  private final String MAP_MARK_PATH = "http://maps.google.com/mapfiles/ms/micons/blue-dot.png";
  private MapModel advancedModel;
  
  private double centLat = 0;
  private double centLnt = 0; 
  private int zoom = 6;
  private String locality;
  private String country;
  private String continent;
  private String catalogNumber;
  private String taxonName;
  private boolean hasData;
  private String coordinates;
  private String nameAndCatalogNumber;
  
  private final String north = "_N_"; 
  private final String south = "_S_"; 
  private final String underScore = "_"; 
  private final String west = "_W";
  
  private NrmData data;
   
  public void getSingleMap(BlastSubjectMetadata subMetada) {
    log.info("getSingleMap : {}", subMetada);
     
    advancedModel = new DefaultMapModel();  
    coordinates  = subMetada.getCoordinates();  
    String lat; 
    String lng; 
    if(coordinates.contains(north)) {
      lat = StringUtils.substringBefore(coordinates, north);
      lng = StringUtils.substringBetween(coordinates, north, underScore); 
      centLat = HelpClass.isNumeric(lat) ? Double.valueOf(lat) : 0;
    } else {
      lat = StringUtils.substringBefore(coordinates, south);
      lng = StringUtils.substringBetween(coordinates, south, underScore); 
      centLat = HelpClass.isNumeric(lat) ? Double.valueOf(lat) * (-1) : 0;
    }   
    
    if(coordinates.contains(west)) {
      centLnt = HelpClass.isNumeric(lng) ? Double.valueOf(lng) * (-1) : 0;
    } else {
      centLnt = HelpClass.isNumeric(lng) ? Double.valueOf(lng) : 0;
    }
            
    LatLng coord = new LatLng(centLat, centLnt);
    
    data = subMetada.getNrmData() != null ? subMetada.getNrmData() : null;
    advancedModel.addOverlay(new Marker(coord, subMetada.getFormattedCoordinates(), data, MAP_MARK_PATH)); 
  }
  
  public void onMarkerSelect(OverlaySelectEvent event) { 
    log.info("onMark selected ");
    Marker marker = (Marker) event.getOverlay(); 
    
    hasData = marker.getData() != null;
    if(hasData) { 
      data = (NrmData) marker.getData();
      locality = data.getLocality();
      taxonName = data.getTaxonName();
      catalogNumber = data.getCatalogNumber();
      coordinates = data.getCoordinates();
      country = data.getCountry();
      continent = data.getContinent();
      nameAndCatalogNumber = data.getNameAndCatalogNumber();
    }  
  }

  public String getLocality() {
    return locality;
  }

  public void setLocality(String locality) {
    this.locality = locality;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getContinent() {
    return continent;
  }

  public void setContinent(String continent) {
    this.continent = continent;
  }

  public String getCatalogNumber() {
    return catalogNumber;
  }

  public void setCatalogNumber(String catalogNumber) {
    this.catalogNumber = catalogNumber;
  }

  public String getTaxonName() {
    return taxonName;
  }

  public void setTaxonName(String taxonName) {
    this.taxonName = taxonName;
  }

  public boolean isHasData() {
    return hasData;
  }

  public void setHasData(boolean hasData) {
    this.hasData = hasData;
  }

  public String getCoordinates() {
    return coordinates;
  }

  public void setCoordinates(String coordinates) {
    this.coordinates = coordinates;
  }

  public String getNameAndCatalogNumber() {
    return nameAndCatalogNumber;
  }

  public void setNameAndCatalogNumber(String nameAndCatalogNumber) {
    this.nameAndCatalogNumber = nameAndCatalogNumber;
  }

  
  public MapModel getAdvancedModel() {
    return advancedModel;
  }

  public void setAdvancedModel(MapModel advancedModel) {
    this.advancedModel = advancedModel;
  }

  public double getCentLat() {
    return centLat;
  }

  public void setCentLat(double centLat) {
    this.centLat = centLat;
  }

  public double getCentLnt() {
    return centLnt;
  }

  public void setCentLnt(double centLnt) {
    this.centLnt = centLnt;
  }

  public int getZoom() {
    return zoom;
  }

  public void setZoom(int zoom) {
    this.zoom = zoom;
  } 
}
