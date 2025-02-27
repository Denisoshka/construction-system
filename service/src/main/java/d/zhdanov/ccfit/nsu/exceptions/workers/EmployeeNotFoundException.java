package d.zhdanov.ccfit.nsu.exceptions.workers;

public class EmployeeNotFoundException extends WorkersServiceException {
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
