package se.nrm.dina.dnakey.logic.geomap;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.annotation.PostConstruct;
import javax.ejb.Startup;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import se.nrm.dina.dnakey.logic.config.ConfigProperties;
import se.nrm.dina.dnakey.logic.vo.GeoMapData;

/**
 *
 * @author idali
 */
@ApplicationScoped
@Startup
@Slf4j 
public class GeoMapDataSource implements Serializable {
 
  private final String SEPARATOR = "\t"; 
  private List<GeoMapData> list;

  @Inject
  private ConfigProperties config;

  public GeoMapDataSource() {
  }

  public GeoMapDataSource(ConfigProperties config) {
    this.config = config;
  }
  
  /**
   * Start ejb every time deploy into glassfish or glassfish start
   */
  @PostConstruct
  void init() {
    buildGeoMapData();
  }

  public String getMapKey() {
    return config.getMapKey();
  }

  public List<GeoMapData> getGeoMapData() {
    return list;
  }

  private void buildGeoMapData() {
    log.info("buildGeoMapData");
 
    list = new ArrayList<>();  
    try (Stream<String> stream = Files.lines(Paths.get(config.getGeoDataFilePath()))) {
      list = stream.map(l -> mapperToGeoMapData(l))
              .collect(Collectors.toList());  
    } catch (IOException e) {
      log.error(e.getMessage());
    }
  } 
   
  private GeoMapData mapperToGeoMapData(String value) {
    String[] geo = value.split(SEPARATOR);
    return new GeoMapData(Double.parseDouble(geo[2]), Double.parseDouble(geo[1]), Integer.parseInt(geo[0]));
  } 
}
