package se.nrm.dina.dnakey.portal.solr;

import java.io.IOException;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils; 
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.request.QueryRequest;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import se.nrm.dina.dnakey.logic.config.ConfigProperties;
import se.nrm.dina.dnakey.logic.exception.DinaException;
import se.nrm.dina.dnakey.portal.vo.SolrRecord;

/**
 *
 * @author idali
 */
@Slf4j
public class SolrWithAuth implements Serializable {

    private String username;
    private String password;
//    private String solrUrl;
    private SolrQuery query;
    private QueryRequest request;
    
    
    private final String searchField = "cn:";
    
    private final String nrmDb = "nrm";
    private final String emptyString = "";
    
    private HttpSolrClient solr;

//    private CredentialsProvider provider;

    @Inject
    private ConfigProperties properties;

    public SolrWithAuth() {

    }

    @PostConstruct
    public void init() {
        log.info("init");
        this.username = properties.getUsername();
        this.password = properties.getPassword(); 
        
        solr = new HttpSolrClient.Builder(properties.getSolrPath()).build();
        query = new SolrQuery();    

        log.info("username and password : {} -- {}", username, password); 
    }

    public SolrRecord getRecordByDataByCatalognumber(
            String catalognumber, String database) {
        log.info("getRecordByCollectionObjectCatalognumber : {} -- {}", catalognumber, database);

        if (!database.equals(nrmDb)) {
            catalognumber = StringUtils.replace(catalognumber.toLowerCase(), nrmDb, emptyString);
        } else if (catalognumber.toLowerCase().contains(nrmDb)) {
            catalognumber = StringUtils.replace(catalognumber.toLowerCase(), nrmDb, emptyString);
        }
        log.info("catalogNumber : {}", catalognumber);

        query = new SolrQuery();
        query.setQuery(searchField + catalognumber);
        try {
//            QueryResponse queryResponse = solr.query(query);
            
            request = new QueryRequest(query);
            request.setBasicAuthCredentials(username, password);
            QueryResponse queryResponse = request.process(solr);

            SolrDocumentList documents = queryResponse.getResults();
            long numFound = documents.getNumFound();
            return numFound > 0 ? queryResponse.getBeans(SolrRecord.class).get(0) : null;
        } catch (IOException | SolrServerException ex) {
            throw new DinaException(ex);
        }
    } 
}
