package se.nrm.dina.dnakey.portal.solr;

import se.nrm.dina.dnakey.portal.vo.SolrRecord;
import java.io.IOException;
import java.io.Serializable; 
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import se.nrm.dina.dnakey.logic.config.ConfigProperties;
import se.nrm.dina.dnakey.logic.exception.DinaException;

/**
 *
 * @author idali
 */
@Slf4j
public class SolrClient implements Serializable {
  
  private HttpSolrClient solr;
  private SolrQuery query;
  private final String nrmDb = "nrm";
  private final String searchField = "cn:";
  
  @Inject
  private ConfigProperties config;
  
  public SolrClient() {    
  }
  
  public SolrClient(ConfigProperties config, HttpSolrClient solr) {
    this.solr = solr;
    this.config = config;
  }
  
  @PostConstruct
  public void init() {    
    log.info("init"); 
    solr = new HttpSolrClient.Builder(config.getSolrPath()).build();
    query = new SolrQuery();    
  }
  
  public SolrRecord getRecordByCollectionObjectCatalognumber(String catalognumber, String database) {    
    log.info("getRecordByCollectionObjectCatalognumber : {}", catalognumber);
    
    if(!database.equals(nrmDb)) {
      catalognumber = StringUtils.replace(catalognumber.toLowerCase(), nrmDb, "");
    }
     
    query = new SolrQuery();
    query.setQuery(searchField + catalognumber);
    try {       
      QueryResponse queryResponse = solr.query(query);
      
      SolrDocumentList documents = queryResponse.getResults();
      long numFound = documents.getNumFound();      
      return numFound > 0 ? queryResponse.getBeans(SolrRecord.class).get(0) : null; 
    } catch (IOException | SolrServerException ex) {
      throw new DinaException(ex);
    }
  }  
}
