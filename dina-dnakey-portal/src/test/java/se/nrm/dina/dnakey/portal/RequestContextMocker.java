package se.nrm.dina.dnakey.portal;

import javax.faces.context.FacesContext;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.primefaces.context.RequestContext;

/**
 *
 * @author idali
 */
public abstract class RequestContextMocker extends RequestContext {
  
  private RequestContextMocker() {
  }

  private static final Release RELEASE = new Release();

  private static class Release implements Answer<Void> {
    @Override
    public Void answer(InvocationOnMock invocation) throws Throwable {
      setCurrentInstance(null, null);
      return null;
    }  
  }

  public static RequestContext mockRequestContext() { 
    FacesContext facesContext = Mockito.mock(FacesContext.class);
    RequestContext requestContext = Mockito.mock(RequestContext.class);
    setCurrentInstance(requestContext, facesContext);
    Mockito.doAnswer(RELEASE)
        .when(requestContext)
        .release();
    return requestContext;
  } 
}
