package d.zhdanov.ccfit.nsu.config;

import d.zhdanov.ccfit.nsu.CustomHeaders;
import d.zhdanov.ccfit.nsu.Roles;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class HeaderAuthFilter extends OncePerRequestFilter {
  
  @Override
  protected void doFilterInternal(
    HttpServletRequest request,
    @NotNull HttpServletResponse response,
    @NotNull FilterChain filterChain
  ) throws ServletException, IOException {
    final var userId      = request.getHeader(CustomHeaders.USER_ID_HEADER);
    final var rolesHeader = request.getHeader(CustomHeaders.USER_ROLES_HEADER);
    
    if(userId != null) {
      List<GrantedAuthority> authorities = parseRoles(rolesHeader);
      Authentication auth = new PreAuthenticatedAuthenticationToken(
        userId, null, authorities
      );
      log.debug("user id: {} roles: {}", userId, authorities);
      final var context = SecurityContextHolder.getContext();
      context.setAuthentication(auth);
    }
    
    filterChain.doFilter(request, response);
  }
  
  private List<GrantedAuthority> parseRoles(String rolesHeader) {
    if(rolesHeader == null || rolesHeader.isEmpty()) {
      return Collections.emptyList();
    }
    
    return Arrays.stream(rolesHeader.split(","))
      .map(
        role -> new SimpleGrantedAuthority(
          Roles.ROLE_PREFIX + role.trim().toUpperCase()))
      .collect(Collectors.toList());
  }
}