package d.zhdanov.ccfit.nsu.objects.exceptions;

public class ObjectServiceException extends RuntimeException {
  public ObjectServiceException() {
  }
  
  public ObjectServiceException(String message) {
    super(message);
  }
  
  public ObjectServiceException(String message, Throwable cause) {
    super(message, cause);
  }
  
  public ObjectServiceException(Throwable cause) {
    super(cause);
  }
}
