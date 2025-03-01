package d.zhdanov.ccfit.nsu.exceptions.workers;

public class EngineerPositionNotFoundException extends WorkersServiceException {
  public EngineerPositionNotFoundException(String message) {
    super(message);
  }
  
  public EngineerPositionNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
  
  public EngineerPositionNotFoundException(Throwable cause) {
    super(cause);
  }
  
  public EngineerPositionNotFoundException() {
  }
}
