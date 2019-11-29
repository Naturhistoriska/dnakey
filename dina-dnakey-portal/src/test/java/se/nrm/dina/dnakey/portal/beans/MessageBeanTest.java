package se.nrm.dina.dnakey.portal.beans;

import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage; 
import javax.faces.context.FacesContext;
import org.junit.After; 
import org.junit.Before; 
import org.junit.Test; 
import org.junit.runner.RunWith; 
import static org.mockito.Matchers.any; 
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify; 
import org.mockito.runners.MockitoJUnitRunner;
import se.nrm.dina.dnakey.portal.ContextMocker;
 
/**
 *
 * @author idali
 */
@RunWith(MockitoJUnitRunner.class)  
public class MessageBeanTest {
  
  private MessageBean instance;  
  
  public MessageBeanTest() {
  }
 
  @Before
  public void setUp() {  
    instance = new MessageBean();  
  } 
  
  @After
  public void tearDown() {
    instance = null;
  }

  /**
   * Test of createErrorMsgs method, of class MessageBean.
   */
  @Test
  public void testCreateErrorMsgs() {
    System.out.println("createErrorMsgs");
     
    FacesContext context = ContextMocker.mockFacesContext(); 
    try {
      String errorTitle = "Data not found";
      List<String> errors = new ArrayList<>();
      errors.add("test error 1");
      errors.add("test error 2"); 
      instance.createErrorMsgs(errorTitle, errors);  
      verify(context, times(2)).addMessage(any(String.class), any(FacesMessage.class));  
    } finally {
      context.release();
    } 
  }

  /**
   * Test of addError method, of class MessageBean.
   */
  @Test
  public void testAddError() {
    System.out.println("addError");
    String errorTitle = "Error";
    String errorMsg = "Error"; 
    FacesContext context = ContextMocker.mockFacesContext();  
    try { 
    instance.addError(errorTitle, errorMsg); 
    verify(context, times(1)).addMessage(any(String.class), any(FacesMessage.class)); 
    } finally {
      context.release();
    } 
  }

  /**
   * Test of addInfo method, of class MessageBean.
   */
  @Test
  public void testAddInfo() {
    System.out.println("addInfo");
    String infoTitle = "Info";
    String infoMsg = "Info"; 
    
    FacesContext context = ContextMocker.mockFacesContext();  
    try { 
      instance.addInfo(infoTitle, infoMsg);
      verify(context, times(1)).addMessage(any(String.class), any(FacesMessage.class)); 
    } finally {
      context.release();
    } 
  }

  /**
   * Test of addWarning method, of class MessageBean.
   */
  @Test
  public void testAddWarning() {
    System.out.println("addWarning");
    String warnTitle = "warning";
    String warnMsg = "warning"; 
    FacesContext context = ContextMocker.mockFacesContext();  
    try { 
      instance.addWarning(warnTitle, warnMsg); 
      verify(context, times(1)).addMessage(any(String.class), any(FacesMessage.class)); 
    } finally {
      context.release();
    }  
  }
  
}
