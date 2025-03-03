package d.zhdanov.ccfit.nsu.workers.exceptions;

public class WorkersServiceException extends RuntimeException {
  public WorkersServiceException() {
  }
  
  public WorkersServiceException(String message) {
    super(message);
  }
  
  public WorkersServiceException(String message, Throwable cause) {
    super(message, cause);
  }
  
  public WorkersServiceException(Throwable cause) {
    super(cause);
  }
}
