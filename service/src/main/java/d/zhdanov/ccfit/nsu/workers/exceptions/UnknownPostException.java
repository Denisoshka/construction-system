package d.zhdanov.ccfit.nsu.workers.exceptions;

public class UnknownPostException extends EmployeeServiceException {
  public UnknownPostException(String message) {
    super(message);
  }
  
  public UnknownPostException(String message, Throwable cause) {
    super(message, cause);
  }
  
  public UnknownPostException(Throwable cause) {
    super(cause);
  }
  
  public UnknownPostException() {
  }
}
