package d.zhdanov.ccfit.nsu.exceptions.workers;

public class EmployeeAlreadyExistsException extends WorkersServiceException{
  public EmployeeAlreadyExistsException() {
  }

  public EmployeeAlreadyExistsException(String message) {
    super(message);
  }

  public EmployeeAlreadyExistsException(String message, Throwable cause) {
    super(message, cause);
  }

  public EmployeeAlreadyExistsException(Throwable cause) {
    super(cause);
  }
}
