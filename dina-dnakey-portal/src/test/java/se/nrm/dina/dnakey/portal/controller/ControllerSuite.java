package se.nrm.dina.dnakey.portal.controller;

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
@Suite.SuiteClasses({se.nrm.dina.dnakey.portal.controller.LanguagesTest.class, 
  se.nrm.dina.dnakey.portal.controller.IdleMonitorControllerTest.class, 
  se.nrm.dina.dnakey.portal.controller.NavigatorTest.class, 
  se.nrm.dina.dnakey.portal.controller.FileHandlerTest.class, 
  se.nrm.dina.dnakey.portal.controller.BlastBeanTest.class, 
  se.nrm.dina.dnakey.portal.controller.MapTest.class})
public class ControllerSuite {

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
