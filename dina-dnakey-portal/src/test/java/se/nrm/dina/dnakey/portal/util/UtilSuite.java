package se.nrm.dina.dnakey.portal.util;

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
@Suite.SuiteClasses({se.nrm.dina.dnakey.portal.util.CSSNameTest.class,  
  se.nrm.dina.dnakey.portal.util.UtilTest.class, 
  se.nrm.dina.dnakey.portal.util.FastaFilesTest.class, 
  se.nrm.dina.dnakey.portal.util.UUIDGeneratorTest.class, 
  se.nrm.dina.dnakey.portal.util.SequencesBuilderHelperTest.class,  
  se.nrm.dina.dnakey.portal.util.ConstantStringTest.class})
public class UtilSuite {

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
