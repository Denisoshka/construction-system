package d.zhdanov.ccfit.nsu.exceptions.workers;

public class PostPositionNotFoundException extends RuntimeException {
  public PostPositionNotFoundException(String message) {
    super(message);
  }

  public PostPositionNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

  public PostPositionNotFoundException(Throwable cause) {
    super(cause);
  }

  public PostPositionNotFoundException() {
  }
}
