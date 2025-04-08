package d.zhdanov.ccfit.nsu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
//@EnableMethodSecurity
public class SecurityConfig {
  private final HeaderAuthFilter headerAuthFilter;
  
  public SecurityConfig(@Autowired HeaderAuthFilter headerAuthFilter) {
    this.headerAuthFilter = headerAuthFilter;
  }
  
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http)
  throws Exception {
    http.csrf(AbstractHttpConfigurer::disable)
      .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
      .addFilterBefore(
        headerAuthFilter, UsernamePasswordAuthenticationFilter.class
      );
    
    return http.build();
  }
}