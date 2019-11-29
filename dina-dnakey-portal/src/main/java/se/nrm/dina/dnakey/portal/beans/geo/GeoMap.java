package se.nrm.dina.dnakey.portal.beans.geo;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.extern.slf4j.Slf4j; 
import se.nrm.dina.dnakey.logic.geomap.GeoMapDataSource;
import se.nrm.dina.dnakey.logic.vo.GeoMapData;

/**
 *
 * @author idali
 */
@Named("geoMap")
@ApplicationScoped
@Slf4j
public class GeoMap implements Serializable {

  private List<GeoMapData> geoMap;

  @Inject
  private GeoMapDataSource geo;

  public GeoMap() {
    log.info("GeoMap");
  }
  
  public GeoMap(GeoMapDataSource geo) {
    log.info("GeoMap");
    this.geo = geo;
  }

  @PostConstruct
  public void init() {
    log.info("init");
    if (geoMap == null) {
      geoMap = geo.getGeoMapData();
    }
  }

  public List<GeoMapData> getGeoMap() {
    return geoMap;
  }

  public String getMapKey() {
    return geo.getMapKey();
  }
}
