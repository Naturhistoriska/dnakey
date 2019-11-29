package se.nrm.dina.dnakey.portal.solr;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient; 
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.junit.After;  
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before; 
import org.junit.Test; 
import org.junit.runner.RunWith; 
import static org.mockito.Matchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner; 
import se.nrm.dina.dnakey.logic.config.ConfigProperties;
import se.nrm.dina.dnakey.portal.vo.SolrRecord;

/**
 *
 * @author idali
 */ 
@RunWith(MockitoJUnitRunner.class) 
public class SolrClientTest {
  
  @Mock
  private ConfigProperties config;
  @Mock
  private HttpSolrClient solr;
  @Mock
  QueryResponse queryResponse;
  @Mock
  SolrDocumentList documents;
   
  private SolrClient instance;
  private List<SolrRecord> list;
  
  public SolrClientTest() {
  }
 
  @Before
  public void setUp() {
    instance = new SolrClient(config, solr);
    when(config.getSolrPath()).thenReturn("https://local-solr.nrm.se/solr/nrm"); 
  }
  
  @After
  public void tearDown() {
    instance = null;
  }

  /**
   * Test of init method, of class SolrClient.
   */
  @Test
  public void testInit() {
    System.out.println("init"); 
    instance.init();  
    verify(config, times(1)).getSolrPath();        
    verify(config, times(0)).getBinPath();    
  }

  /**
   * Test of getRecordByCollectionObjectCatalognumber method, of class SolrClient.
   * @throws java.io.IOException
   * @throws org.apache.solr.client.solrj.SolrServerException
   */
  @Test
  public void testGetRecordByCollectionObjectCatalognumber() throws IOException, SolrServerException {
    System.out.println("getRecordByCollectionObjectCatalognumber");
    
    String catalognumber = "1234567";
    String database = "nrm";  
    
    list = new ArrayList<>();
    SolrRecord record = new SolrRecord();
    record.setCatalogNumber(catalognumber);
    list.add(record);
    
    when(solr.query(any(SolrQuery.class))).thenReturn(queryResponse); 
    when(queryResponse.getResults()).thenReturn(documents); 
    when(documents.getNumFound()).thenReturn(1L); 
    when(queryResponse.getBeans(SolrRecord.class)).thenReturn(list);
  
    SolrRecord result = instance.getRecordByCollectionObjectCatalognumber(catalognumber, database);
    assertNotNull(result);
    assertEquals(record, result); 
  } 
  
  /**
   * Test of testGetRecordByCollectionObjectCatalognumberNoResult method, of class SolrClient.
   * @throws java.io.IOException
   * @throws org.apache.solr.client.solrj.SolrServerException
   */
  @Test
  public void testGetRecordByCollectionObjectCatalognumberNoResult() throws IOException, SolrServerException {
    System.out.println("testGetRecordByCollectionObjectCatalognumberNoResult");
    
    String catalognumber = "7654321";
    String database = "nrm";   
    list = new ArrayList<>();    
    when(solr.query(any(SolrQuery.class))).thenReturn(queryResponse); 
    when(queryResponse.getResults()).thenReturn(documents); 
    when(documents.getNumFound()).thenReturn(0L); 
    when(queryResponse.getBeans(SolrRecord.class)).thenReturn(list);
  
    SolrRecord result = instance.getRecordByCollectionObjectCatalognumber(catalognumber, database);
    assertEquals(result, null);
  } 
}
