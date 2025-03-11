package d.zhdanov.ccfit.nsu.objects.exceptions;

public class ConstructionSiteCreateException extends ObjectServiceException {
  public ConstructionSiteCreateException(String message) {
    super(message);
  }
  
  public ConstructionSiteCreateException(String message, Throwable cause) {
    super(message, cause);
  }
  
  public ConstructionSiteCreateException(Throwable cause) {
    super(cause);
  }
  
  public ConstructionSiteCreateException() {
  }
}
