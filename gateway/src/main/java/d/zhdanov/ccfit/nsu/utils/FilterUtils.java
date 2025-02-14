package d.zhdanov.ccfit.nsu.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;


@Component
public class FilterUtils {
  public static final String CORRELATION_ID_HEADER = "X-Correlation-ID";

  public static String getCorrelationId(ServerWebExchange exchange) {
    return exchange.getRequest().getHeaders().getFirst(CORRELATION_ID_HEADER);
  }

  public static void setRequestHeader(ServerWebExchange exchange, String name,
                                      String value) {
    exchange.getRequest().getHeaders().add(name, value);
  }

  public static String generateCorrelationId() {
    return java.util.UUID.randomUUID().toString();
  }

  public static void setCorrelationId(ServerWebExchange exchange,
                                      String correlationId) {
    setRequestHeader(exchange, CORRELATION_ID_HEADER, correlationId);
  }
}