package se.nrm.dina.dnakey.logic.metadata;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author idali
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({se.nrm.dina.dnakey.logic.metadata.MetadataDataFactoryTest.class, 
  se.nrm.dina.dnakey.logic.metadata.AlignSequenceTest.class, 
  se.nrm.dina.dnakey.logic.metadata.BlastMetadataTest.class, 
  se.nrm.dina.dnakey.logic.metadata.BlastSubjectMetadataTest.class, 
  se.nrm.dina.dnakey.logic.metadata.BlastSubjectHspTest.class, 
  se.nrm.dina.dnakey.logic.metadata.JsonToMetadataTest.class})
public class MetadataSuite {

  @BeforeClass
  public static void setUpClass() throws Exception {
  }

  @AfterClass
  public static void tearDownClass() throws Exception {
  }

  @Before
  public void setUp() throws Exception {
  }

  @After
  public void tearDown() throws Exception {
  }
  
}
