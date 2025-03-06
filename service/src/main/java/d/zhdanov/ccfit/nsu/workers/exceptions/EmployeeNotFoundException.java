package d.zhdanov.ccfit.nsu.workers.exceptions;

public class EmployeeNotFoundException extends EmployeeServiceException {
  public EmployeeNotFoundException() {
  }
  
  public EmployeeNotFoundException(String message) {
    super(message);
  }
  
  public EmployeeNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
  
  public EmployeeNotFoundException(Throwable cause) {
    super(cause);
  }
}
