package d.zhdanov.ccfit.nsu.workers.exceptions;

public class WorkerPositionAlreadyExistsException
  extends EmployeeServiceException {
  public WorkerPositionAlreadyExistsException(String message) {
    super(message);
  }
  
  public WorkerPositionAlreadyExistsException(Throwable cause) {
    super(cause);
  }
  
  public WorkerPositionAlreadyExistsException() {
  }
  
  public WorkerPositionAlreadyExistsException(String message, Throwable cause) {
    super(message, cause);
  }
}
