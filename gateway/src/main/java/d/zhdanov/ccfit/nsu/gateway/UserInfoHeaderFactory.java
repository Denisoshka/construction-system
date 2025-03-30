package d.zhdanov.ccfit.nsu.gateway;

import lombok.extern.slf4j.Slf4j;
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

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class UserInfoHeaderFactory
  extends AbstractGatewayFilterFactory<Object> {
  
  public static final String USER_ID_HEADER    = "X-User-Id";
  public static final String USER_EMAIL_HEADER = "X-User-Email";
  public static final String USER_ROLES_HEADER = "X-User-Roles";
  
  public static final String RESOURCE_ACCESS_CLAIM = "resource_access";
  public static final String FRONTEND_CLAIM        = "frontend";
  public static final String ROLE_CLAIM            = "roles";
  public static final String EMAIL_CLAIM           = "email";
  
  public static List<String> getFrontendResourceClaims(Jwt jwt) {
    Map<String, Object> resourceAccess = jwt.getClaim(RESOURCE_ACCESS_CLAIM);
    if(resourceAccess == null) {
      return Collections.emptyList();
    }
    resourceAccess = (Map<String, Object>) resourceAccess.get(FRONTEND_CLAIM);
    if(resourceAccess != null) {
      return (List<String>) resourceAccess.getOrDefault(ROLE_CLAIM, Collections.emptyList());
    }
    return Collections.emptyList();
  }
  
  private Mono<Void> redirectToRegistration(ServerWebExchange exchange) {
    exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
    return exchange.getResponse().setComplete();
  }
  
  @Override
  public GatewayFilter apply(Object config) {
    return (exchange, chain) -> ReactiveSecurityContextHolder.getContext()
      .map(SecurityContext::getAuthentication)
      .cast(JwtAuthenticationToken.class).flatMap(auth -> {
        Jwt jwt = auth.getToken();
        log.info("JWT: {}", jwt.getClaims());
        log.info(
          "JWT {}: {}", RESOURCE_ACCESS_CLAIM,
          getFrontendResourceClaims(jwt)
        );
        log.info("JWT {}: {}", EMAIL_CLAIM, jwt.getClaim(EMAIL_CLAIM));
        ServerHttpRequest modifiedRequest = exchange.getRequest().mutate()
          .header(USER_ID_HEADER, jwt.getSubject()).header(
            USER_ROLES_HEADER,
            String.join(
              ",",
              getFrontendResourceClaims(
                jwt)
            )
          ).header(USER_EMAIL_HEADER, jwt.getClaimAsString(EMAIL_CLAIM))
          .build();
        
        return chain.filter(exchange.mutate().request(modifiedRequest).build());
      });
  }
}