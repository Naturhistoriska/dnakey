package se.nrm.dina.dnakey.portal.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors; 
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.extern.slf4j.Slf4j;
import se.nrm.dina.dnakey.logic.config.ConfigProperties; 
import se.nrm.dina.dnakey.logic.metadata.BlastSubjectMetadata;
import se.nrm.dina.dnakey.logic.vo.MorphBankImage;
import se.nrm.dina.dnakey.logic.vo.NrmData;
import se.nrm.dina.dnakey.portal.controller.BlastBean;
import se.nrm.dina.dnakey.portal.vo.SolrRecord;

/**
 *
 * @author idali
 */
@Named("galleriaBean")
@SessionScoped 
@Slf4j
public class GalleriaBean implements Serializable {

  private List<MorphBankImage> groupImages;
  private String mbid;
  private String catalogNumber;
  private String scientificName;
  private String nameAndCatalogNumber;
  private final String imgType = "&imgType=JPG";
   
  @Inject
  private ConfigProperties config;
  @Inject
  private BlastBean blast;

  public GalleriaBean() {
  }

  /**
   * Build a list of MorphBankImage with morphbank image id and thumb id
   * 
   * @param data - NrmData
   */
  public void imageSwitch(NrmData data) {
    log.info("imageSwitch : {}", data);

    groupImages = new ArrayList<>();  
    
    String[] imageIds = data.getMorphbankImageIds();
    
    mbid = data.getMorphbankId();
    catalogNumber = data.getCatalogNumber();
    scientificName = data.getTaxonName();
    nameAndCatalogNumber = data.getNameAndCatalogNumber();
    groupImages = Arrays.asList(imageIds)
            .stream() 
            .map(id -> buildMorphyBankImage(id))
            .collect(Collectors.toList()); 
  }

  public List<MorphBankImage> getGroupImages() {
    return groupImages;
  }

  public String getMbid() {
    return mbid;
  }

  public String getCatalogNumber() {
    return catalogNumber;
  }

  public String getScientificName() {
    return scientificName;
  } 

  public String getNameAndCatalogNumber() {
    return nameAndCatalogNumber;
  }

  public void setNameAndCatalogNumber(String nameAndCatalogNumber) {
    this.nameAndCatalogNumber = nameAndCatalogNumber;
  }
    
  private MorphBankImage buildMorphyBankImage(String id) {
    return new MorphBankImage(Integer.parseInt(mbid), config.getThumbPath() + id, 
            catalogNumber, scientificName, "", config.getThumbPath() + id + imgType);
  }
}
