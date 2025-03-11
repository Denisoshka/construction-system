package d.zhdanov.ccfit.nsu.objects.exceptions;

public class ConstructionManagementCreateException
  extends ObjectServiceException {
  public ConstructionManagementCreateException() {
  }
  
  public ConstructionManagementCreateException(String message) {
    super(message);
  }
  
  public ConstructionManagementCreateException(
    String message,
    Throwable cause
  ) {
    super(message, cause);
  }
  
  public ConstructionManagementCreateException(Throwable cause) {
    super(cause);
  }
}
