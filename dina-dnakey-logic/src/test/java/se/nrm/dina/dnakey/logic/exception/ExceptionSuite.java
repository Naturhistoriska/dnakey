package se.nrm.dina.dnakey.logic.exception;

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
@Suite.SuiteClasses({se.nrm.dina.dnakey.logic.exception.DnaKeyExceptionTest.class, 
  se.nrm.dina.dnakey.logic.exception.InvalidMetadataExceptionTest.class, 
  se.nrm.dina.dnakey.logic.exception.DinaExceptionTest.class})
public class ExceptionSuite {

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
