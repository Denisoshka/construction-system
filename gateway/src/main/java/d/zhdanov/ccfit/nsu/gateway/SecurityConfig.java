package d.zhdanov.ccfit.nsu.gateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
public class SecurityConfig {
  @Value("${spring.security.oauth2.resource server.jwt.jwk-set-uri}")
  private String jwkSetUri;
  
  @Bean
  SecurityWebFilterChain securityFilterChain(ServerHttpSecurity httpSecurity)
  throws Exception {
    return httpSecurity.csrf(ServerHttpSecurity.CsrfSpec::disable)
      .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)
      .authorizeExchange(auth -> auth
        .anyExchange().authenticated()
      )
      .oauth2ResourceServer(oauth2 -> oauth2
        .jwt(jwt -> jwt.jwkSetUri(jwkSetUri))
      )
      .build();
  }
}
