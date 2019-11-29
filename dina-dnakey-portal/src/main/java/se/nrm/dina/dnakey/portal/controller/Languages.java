package se.nrm.dina.dnakey.portal.controller;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped; 
import javax.inject.Named;
import lombok.extern.slf4j.Slf4j;
import org.primefaces.context.RequestContext;

/**
 *
 * @author idali
 */
@SessionScoped
@Named
@Slf4j
public class Languages implements Serializable {

  private String locale = "sv";
  private RequestContext requestContext;
 
  public Languages() {
    log.info("Languages");
  }

  public String getLocale() {
    return locale;
  }

  public void setLocale(String locale) {
    this.locale = locale;
  }

  public void changelanguage(String locale) {
    log.info("changelanguage - locale: {}", locale);

    if(!locale.equals(this.locale)) {
      setLocale(locale); 
      requestContext = RequestContext.getCurrentInstance();
      requestContext.update("topMenuForm:topmenupanel");
      requestContext.update("headerPanel");
      requestContext.update("footerPanel");
      requestContext.update("footerPanel");
      requestContext.update("mainpanel"); 
    } 
  }

  public String getLanguage() {
    return locale.equals("sv") ? "English" : "Svenska";
  }

  public boolean isIsSwedish() {
    return locale.equals("sv");
  }
}
