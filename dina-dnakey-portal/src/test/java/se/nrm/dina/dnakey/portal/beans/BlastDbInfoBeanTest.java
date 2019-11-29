package se.nrm.dina.dnakey.portal.beans;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before; 
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.runner.RunWith; 
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import se.nrm.dina.dnakey.logic.BlastDbInfo;

/**
 *
 * @author idali
 */
@RunWith(MockitoJUnitRunner.class) 
public class BlastDbInfoBeanTest {

  private BlastDbInfoBean instance;
   
  @Mock
  private BlastDbInfo dbInfo;
  
  public BlastDbInfoBeanTest() {
  }

  @BeforeClass
  public static void setUpClass() throws Exception {
  }

  @AfterClass
  public static void tearDownClass() throws Exception {
  }
    
  @Before
  public void setUp() {
    instance = new BlastDbInfoBean();
    instance.setDbInfo(dbInfo);
     
    when(dbInfo.getNrmDbTotal()).thenReturn("1888");
    when(dbInfo.getBoldDbTotal()).thenReturn("23800");
    when(dbInfo.getGenbankDbTotal()).thenReturn("36876");
    
    instance.init();
  }
  
  @After
  public void tearDown() {
    instance = null;
  }

  /**
   * Test of init method, of class BlastDbInfoBean.
   */
  @Test
  public void testInit() {
    System.out.println("init");  
    
    verify(dbInfo, times(1)).getNrmDbTotal();
    verify(dbInfo, times(1)).getBoldDbTotal();
    verify(dbInfo, times(1)).getGenbankDbTotal();
  }

  /**
   * Test of getBoldTotalSequence method, of class BlastDbInfoBean.
   */
  @Test
  public void testGetBoldTotalSequence() {
    System.out.println("getBoldTotalSequence"); 
     
    String expResult = "23800";
    String result = instance.getBoldTotalSequence();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getGenBankTotalSequence method, of class BlastDbInfoBean.
   */
  @Test
  public void testGetGenBankTotalSequence() {
    System.out.println("getGenBankTotalSequence");
   
    String expResult = "36876";
    String result = instance.getGenBankTotalSequence();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getNrmTotalSequence method, of class BlastDbInfoBean.
   */
  @Test
  public void testGetNrmTotalSequence() {
    System.out.println("getNrmTotalSequence");
     
    String expResult = "1888";
    String result = instance.getNrmTotalSequence();
    assertEquals(expResult, result); 
  }

  /**
   * Test of setDbInfo method, of class BlastDbInfoBean.
   */
  @Test
  public void testSetDbInfo() {
    System.out.println("setDbInfo"); 
    instance.setDbInfo(dbInfo); 
    assertNotNull(dbInfo);
  } 
}
