package se.nrm.dina.dnakey.portal.errorhandler; 

import java.io.IOException;
import javax.faces.FacesException; 
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;
import java.util.Iterator;
import java.util.Map;
import javax.faces.context.ExternalContext;
import lombok.extern.slf4j.Slf4j;

/**
 * Exception handler. Handles exceptions through from UI.
 * When exception through, redirect to home page
 * 
 * @author idali
 */
@Slf4j
public class CustomExceptionHandler extends ExceptionHandlerWrapper {

  private final ExceptionHandler exceptionHandler;
  private final String home = "/faces/pages/sequence.xhtml";

  public CustomExceptionHandler(ExceptionHandler exceptionHandler) {
    this.exceptionHandler = exceptionHandler;
  }

  @Override
  public ExceptionHandler getWrapped() {
    return exceptionHandler;
  }

  @Override
  public void handle() throws FacesException {
    final Iterator<ExceptionQueuedEvent> queue = getUnhandledExceptionQueuedEvents().iterator(); 
    while (queue.hasNext()) { 
      ExceptionQueuedEvent item = queue.next();
      ExceptionQueuedEventContext exceptionQueuedEventContext = (ExceptionQueuedEventContext) item.getSource();

      try {
        Throwable throwable = exceptionQueuedEventContext.getException();
        log.error(throwable.getMessage());

        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, Object> requestMap = context.getExternalContext().getRequestMap(); 
        
        requestMap.put("error-message", throwable.getMessage());
        requestMap.put("error-stack", throwable.getStackTrace()); 
        context.renderResponse(); 
        ExternalContext externalContext = context.getExternalContext();
        try {
          externalContext.redirect(externalContext.getRequestContextPath() + home);
        } catch (IOException ex) {
        }

      } finally {
        queue.remove();
      }
    }
  }
}
