package d.zhdanov.ccfit.nsu.objects.exceptions;

public class ConstructionSiteAbsent extends ObjectServiceException {
  
  public ConstructionSiteAbsent() {
  }
  
  public ConstructionSiteAbsent(String message) {
    super(message);
  }
  
  public ConstructionSiteAbsent(String message, Throwable cause) {
    super(message, cause);
  }
  
  public ConstructionSiteAbsent(Throwable cause) {
    super(cause);
  }
}
