package d.zhdanov.ccfit.nsu.workers.exceptions;

public class WorkerPositionNotFoundException extends EmployeeServiceException {
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
