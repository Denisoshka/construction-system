package d.zhdanov.ccfit.nsu.exceptions.workers;

public class UnknownPostException extends RuntimeException {
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
