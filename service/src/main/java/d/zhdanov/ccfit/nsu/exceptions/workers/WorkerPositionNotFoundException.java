package d.zhdanov.ccfit.nsu.exceptions.workers;

public class WorkerPositionNotFoundException extends WorkersServiceException {
  public WorkerPositionNotFoundException(String message) {
    super(message);
  }
  
  public WorkerPositionNotFoundException(Throwable cause) {
    super(cause);
  }
  
  public WorkerPositionNotFoundException() {
  }
  
  public WorkerPositionNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
