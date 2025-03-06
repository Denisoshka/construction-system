package d.zhdanov.ccfit.nsu.workers.exceptions;

public class EmployeeServiceException extends RuntimeException {
  public EmployeeServiceException() {
  }
  
  public EmployeeServiceException(String message) {
    super(message);
  }
  
  public EmployeeServiceException(String message, Throwable cause) {
    super(message, cause);
  }
  
  public EmployeeServiceException(Throwable cause) {
    super(cause);
  }
}
