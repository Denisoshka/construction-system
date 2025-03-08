package d.zhdanov.ccfit.nsu.objects.exceptions;

public class ConstructionManagementAbsent
  extends ObjectServiceException {
  public ConstructionManagementAbsent(String message) {
    super(message);
  }
  
  public ConstructionManagementAbsent(
    String message,
    Throwable cause
  ) {
    super(message, cause);
  }
  
  public ConstructionManagementAbsent(Throwable cause) {
    super(cause);
  }
  
  public ConstructionManagementAbsent() {
  }
}
