package d.zhdanov.ccfit.nsu.exceptions.workers;

public class WorkerPositionAlreadyExistsException
    extends WorkersServiceException {
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
