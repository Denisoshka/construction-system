package d.zhdanov.ccfit.nsu.workers.exceptions;

public class PostPositionNotFoundException extends EmployeeServiceException {
  public PostPositionNotFoundException(String message) {
    super(message);
  }
  
  public PostPositionNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
  
  public PostPositionNotFoundException(Throwable cause) {
    super(cause);
  }
  
  public PostPositionNotFoundException() {
  }
}
