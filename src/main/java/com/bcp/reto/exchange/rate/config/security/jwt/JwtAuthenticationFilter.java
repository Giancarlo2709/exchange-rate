package com.bcp.reto.exchange.rate.config.security.jwt;

import com.bcp.reto.exchange.rate.config.security.CustomUserDetailsService;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Nullable;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * Class <b>JwtAuthenticationFilter</b>.
 * <p>Class filter for authentication with jwt</p>
 * @author Giancarlo
 */
@ToString
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

  public static final String AUTHORIZATION_HEADER = "Authorization";

  private final JwtTokenProvider tokenProvider;

  private final CustomUserDetailsService customUserDetailsService;

  @Autowired
  public JwtAuthenticationFilter(JwtTokenProvider tokenProvider,
                                 CustomUserDetailsService customUserDetailsService) {
    this.tokenProvider = tokenProvider;
    this.customUserDetailsService = customUserDetailsService;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                  FilterChain filterChain) throws ServletException, IOException {

    try {
      String jwt = getJwtFromRequest(request);

      if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
        String username = tokenProvider.getUserNameFromJwt(jwt);

        UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(userDetails, null,
                        userDetails.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        if (validExpiration(jwt)) {
          response.addHeader(JwtAuthenticationFilter.AUTHORIZATION_HEADER,
                  "Bearer " + tokenProvider.generateToken(authentication));
        }
      }
    } catch (Exception exc) {
      logger.error("Could not establish user authentication in security context", exc);
    }

    filterChain.doFilter(request, response);
  }

  @Nullable
  private String getJwtFromRequest(HttpServletRequest request) {
    String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
    if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
      return bearerToken.substring(7);
    }
    return null;
  }

  private boolean validExpiration(String token) {
    Date expirationDate = tokenProvider.getExpirationFromJwt(token);
    Date now = new Date();
    long differenceInSeconds = (expirationDate.getTime() - now.getTime()) / 1000;
    return differenceInSeconds <= 90;
  }
}
