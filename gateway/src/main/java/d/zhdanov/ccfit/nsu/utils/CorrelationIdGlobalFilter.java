package d.zhdanov.ccfit.nsu.utils;

import org.slf4j.Logger;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import static org.slf4j.LoggerFactory.getLogger;

@Component
public class CorrelationIdGlobalFilter implements GlobalFilter, Ordered {
  private static final Logger log = getLogger(CorrelationIdGlobalFilter.class);

  @Override
  public Mono<Void> filter(ServerWebExchange exchange,
                           org.springframework.cloud.gateway.filter.GatewayFilterChain chain) {
    String correlationId = FilterUtils.getCorrelationId(exchange);
    if (correlationId == null) {
      correlationId = FilterUtils.generateCorrelationId();
      FilterUtils.setCorrelationId(exchange, correlationId);
    }
    log.info("Request with Correlation ID: {}", correlationId);

    return chain.filter(exchange);
  }

  public int getOrder() {
    return Ordered.HIGHEST_PRECEDENCE;
  }
}

