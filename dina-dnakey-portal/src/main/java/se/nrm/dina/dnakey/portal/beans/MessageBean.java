package se.nrm.dina.dnakey.portal.beans;

import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.RequestScoped; 
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import lombok.extern.slf4j.Slf4j;

/**
 * This class build message to display in ui
 * 
 * @author idali
 */
@Named("message")
@RequestScoped
@Slf4j
public class MessageBean implements Serializable {

  public MessageBean() {
    log.info("MessageBean");
  }

  /**
   * This method build multiple error messages to display in ui
   * 
   * @param errorTitle
   * @param errors 
   */
  public void createErrorMsgs(String errorTitle, List<String> errors) {
    errors.stream().forEach((error) -> {
      addError(errorTitle, error);
    });
  }

  /**
   * This method build single error message to display in ui
   * 
   * @param errorTitle
   * @param errorMsg 
   */
  public void addError(String errorTitle, String errorMsg) {
    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, errorTitle, errorMsg));
  }

  /**
   * This method build single info message to display in ui
   * 
   * @param infoTitle
   * @param infoMsg 
   */
  public void addInfo(String infoTitle, String infoMsg) {
    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, infoTitle, infoMsg));
  }

  /**
   * This method build single warning message to display in ui
   * 
   * @param warnTitle
   * @param warnMsg 
   */
  public void addWarning(String warnTitle, String warnMsg) {
    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, warnTitle, warnMsg));
  }

}
