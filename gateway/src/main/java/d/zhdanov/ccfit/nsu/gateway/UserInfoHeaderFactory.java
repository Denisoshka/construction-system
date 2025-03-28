package d.zhdanov.ccfit.nsu.gateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class UserInfoHeaderFactory
  extends AbstractGatewayFilterFactory<Object> {
  
  public static String     USER_ID_HEADER    = "X-User-Id";
  public static String     USER_EMAIL_HEADER = "X-User-Email";
  public static String     USER_ROLES_HEADER = "X-User-Roles";
  @Value("${app.config.login-uri}")
  private       String     loginUri;
  
  
 /* public UserInfoHeaderGatewayFilterFactory(
    @Autowired JwtDecoder jwtDecoder
  ) {
    this.jwtDecoder = jwtDecoder;
  }*/
  
  private Mono<Void> redirectToRegistration(ServerWebExchange exchange) {
    exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
    return exchange.getResponse().setComplete();
  }
  
  @Override
  public GatewayFilter apply(Object config) {
    return (exchange, chain) -> ReactiveSecurityContextHolder
      .getContext().map(SecurityContext::getAuthentication)
      .cast(JwtAuthenticationToken.class)
      .flatMap(auth -> {
        Jwt jwt = auth.getToken();
        ServerHttpRequest modifiedRequest = exchange.getRequest().mutate()
          .header(USER_ID_HEADER, jwt.getSubject())
          .header(
            USER_ROLES_HEADER,
            String.join(",", jwt.getClaimAsStringList("roles"))
          )
          .header(USER_EMAIL_HEADER, jwt.getClaimAsString("email"))
          .build();
        
        return chain.filter(exchange.mutate().request(modifiedRequest).build());
      });
  }
}