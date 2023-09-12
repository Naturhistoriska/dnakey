package se.nrm.dina.dnakey.logic.config;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.wildfly.swarm.spi.runtime.annotations.ConfigurationValue;

/**
 * Environment properties
 *
 *
 * @author idali
 */
@ApplicationScoped
@Slf4j
public class ConfigProperties {

    private final static String CONFIG_INITIALLISING_ERROR = "config property not initialised";

    private String geoDataFilePath;
    private String blastBasePath;
    private String dbPath;
    private String binPath;
    private String dbinfoPath;
    private String blastnPath;
    private String fastaFilePath;
    private String solrPath;
    private String mapKey;
    private String thumbPath;
    private String imagePath;

    private String username;
    private String password;

    public ConfigProperties() {
    }

    @Inject
    public ConfigProperties(@ConfigurationValue("swarm.geodata") String geoDataFilePath,
            @ConfigurationValue("swarm.blast.base.path") String blastBasePath,
            @ConfigurationValue("swarm.blast.base.db") String dbPath,
            @ConfigurationValue("swarm.blast.base.bin.path") String binPath,
            @ConfigurationValue("swarm.blast.base.bin.dbinfo") String dbinfoPath,
            @ConfigurationValue("swarm.blast.base.bin.blastn") String blastnPath,
            @ConfigurationValue("swarm.blast.tempfile.path") String fastaFilePath,
            @ConfigurationValue("swarm.solr.path") String solrPath,
            @ConfigurationValue("swarm.images.morphbank.thumb") String thumbPath,
            @ConfigurationValue("swarm.images.morphbank.image") String imagePath,
            @ConfigurationValue("swarm.map.key") String mapKey,
            @ConfigurationValue("swarm.solr.username") String username,
            @ConfigurationValue("swarm.solr.password") String password) {
        this.geoDataFilePath = geoDataFilePath;
        this.blastBasePath = blastBasePath;
        this.dbPath = dbPath;
        this.binPath = binPath;
        this.dbinfoPath = dbinfoPath;
        this.blastnPath = blastnPath;
        this.fastaFilePath = fastaFilePath;
        this.solrPath = solrPath;
        this.mapKey = mapKey;
        this.thumbPath = thumbPath;
        this.imagePath = imagePath;
        this.username = username;
        this.password = password;
        log.info("test injection : {} -- {}", username, password);
    }

    public String getGeoDataFilePath() {
        if (geoDataFilePath == null) {
            throw new RuntimeException(CONFIG_INITIALLISING_ERROR);
        }
        return geoDataFilePath;
    }

    public String getBlastBasePath() {
        if (blastBasePath == null) {
            throw new RuntimeException(CONFIG_INITIALLISING_ERROR);
        }
        return blastBasePath;
    }

    public String getDbPath() {
        if (dbPath == null) {
            throw new RuntimeException(CONFIG_INITIALLISING_ERROR);
        }
        return dbPath;
    }

    public String getBinPath() {
        if (binPath == null) {
            throw new RuntimeException(CONFIG_INITIALLISING_ERROR);
        }
        return binPath;
    }

    public String getDbinfoPath() {
        if (dbinfoPath == null) {
            throw new RuntimeException(CONFIG_INITIALLISING_ERROR);
        }
        return dbinfoPath;
    }

    public String getBlastnPath() {
        if (blastnPath == null) {
            throw new RuntimeException(CONFIG_INITIALLISING_ERROR);
        }
        return blastnPath;
    }

    public String getFastaFilePath() {
        if (fastaFilePath == null) {
            throw new RuntimeException(CONFIG_INITIALLISING_ERROR);
        }
        return fastaFilePath;
    }

    public String getSolrPath() {
        if (solrPath == null) {
            throw new RuntimeException(CONFIG_INITIALLISING_ERROR);
        }
        return solrPath;
    }

    public String getMapKey() {
        if (mapKey == null) {
            throw new RuntimeException(CONFIG_INITIALLISING_ERROR);
        }
        return mapKey;
    }

    public String getThumbPath() {
        if (thumbPath == null) {
            throw new RuntimeException(CONFIG_INITIALLISING_ERROR);
        }
        return thumbPath;
    }

    public String getImagePath() {
        if (imagePath == null) {
            throw new RuntimeException(CONFIG_INITIALLISING_ERROR);
        }
        return imagePath;
    }
    
    public String getUsername() {
        if (username == null) {
            throw new RuntimeException(CONFIG_INITIALLISING_ERROR);
        }
        return username;
    }
        
    public String getPassword() {
        if (password == null) {
            throw new RuntimeException(CONFIG_INITIALLISING_ERROR);
        }
        return password;
    }
}
