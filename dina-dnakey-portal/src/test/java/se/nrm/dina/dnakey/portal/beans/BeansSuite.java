package se.nrm.dina.dnakey.portal.beans;

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
@Suite.SuiteClasses({se.nrm.dina.dnakey.portal.beans.MessageBeanTest.class, 
  se.nrm.dina.dnakey.portal.beans.StyleBeanTest.class, 
  se.nrm.dina.dnakey.portal.beans.BlastDbInfoBeanTest.class, 
  se.nrm.dina.dnakey.portal.beans.GalleriaBeanTest.class, 
  se.nrm.dina.dnakey.portal.beans.geo.GeoSuite.class, 
  se.nrm.dina.dnakey.portal.beans.ImageBeansTest.class})
public class BeansSuite {

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
