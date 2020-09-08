package ch.peax.demo.interceptor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Slf4j
public class AuthInterceptor extends HandlerInterceptorAdapter {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
    log.info("Interceptor called");

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    Object principal = authentication.getPrincipal();
    if (principal instanceof UserDetails) {
      UserDetails userDetails = (UserDetails) principal;
      Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();

      List<GrantedAuthority> updatedAuthorities = new ArrayList<>(authorities);
      updatedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
      SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
          SecurityContextHolder.getContext().getAuthentication().getPrincipal(),
          SecurityContextHolder.getContext().getAuthentication().getCredentials(),
          updatedAuthorities
      ));
    }

    List<GrantedAuthority> updatedAuthorities = new ArrayList<>();
    updatedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
    SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
        SecurityContextHolder.getContext().getAuthentication().getPrincipal(),
        SecurityContextHolder.getContext().getAuthentication().getCredentials(),
        updatedAuthorities
    ));
    return true;
  }
}
