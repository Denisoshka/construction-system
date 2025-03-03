package d.zhdanov.ccfit.nsu.workers.exceptions;

public class EngineerPositionAlreadyExistsException
  extends WorkersServiceException {
  public EngineerPositionAlreadyExistsException() {
  }
  
  public EngineerPositionAlreadyExistsException(String message) {
    super(message);
  }
  
  public EngineerPositionAlreadyExistsException(String message,
                                                Throwable cause
  ) {
    super(message, cause);
  }
  
  public EngineerPositionAlreadyExistsException(Throwable cause) {
    super(cause);
  }
}
