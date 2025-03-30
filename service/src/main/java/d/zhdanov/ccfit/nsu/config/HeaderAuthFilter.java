package d.zhdanov.ccfit.nsu.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
public class HeaderAuthFilter extends OncePerRequestFilter {
    
    @Override
    protected void doFilterInternal(
      HttpServletRequest request,
      @NotNull HttpServletResponse response,
      @NotNull FilterChain filterChain
    ) throws ServletException, IOException {
        
        // Извлекаем данные из заголовков
        String userId = request.getHeader("X-User-Id");
        String rolesHeader = request.getHeader("X-User-Roles");
        
        // Если заголовки есть - создаем аутентифицированный контекст
        if (userId != null) {
            List<GrantedAuthority> authorities = parseRoles(rolesHeader);
            Authentication auth = new PreAuthenticatedAuthenticationToken(
              userId,
              null,
              authorities
            );
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        
        filterChain.doFilter(request, response);
    }
    
    private List<GrantedAuthority> parseRoles(String rolesHeader) {
        if (rolesHeader == null) return Collections.emptyList();
        
        return Arrays.stream(rolesHeader.split(","))
          .map(role -> new SimpleGrantedAuthority("ROLE_" + role.trim().toUpperCase()))
          .collect(Collectors.toList());
    }
}