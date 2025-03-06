package d.zhdanov.ccfit.nsu.workers.exceptions;

public class EngineerPositionNotFoundException extends
                                               EmployeeServiceException {
  public EngineerPositionNotFoundException(String message) {
    super(message);
  }
  
  public EngineerPositionNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
  
  public EngineerPositionNotFoundException(Throwable cause) {
    super(cause);
  }
  
  public EngineerPositionNotFoundException() {
  }
}
