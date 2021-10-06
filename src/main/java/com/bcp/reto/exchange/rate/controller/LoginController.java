package com.bcp.reto.exchange.rate.controller;

import com.bcp.reto.exchange.rate.config.security.UserPrincipal;
import com.bcp.reto.exchange.rate.config.security.jwt.JwtAuthenticationFilter;
import com.bcp.reto.exchange.rate.config.security.jwt.JwtTokenProvider;
import com.bcp.reto.exchange.rate.model.api.request.LoginRequest;
import com.bcp.reto.exchange.rate.model.api.response.LoginResponse;
import com.bcp.reto.exchange.rate.model.enums.UserStatusType;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.stream.Collectors;

/**
 * Class <b>LoginController</b>.
 * <p>Class for endpoints of authentication</p>
 * @author Giancarlo
 */
@ToString
@RestController
@RequestMapping("/api/exchange/v1")
public class LoginController {

  private final AuthenticationManager authenticationManager;

  private final PasswordEncoder passwordEncoder;

  private final JwtTokenProvider tokenProvider;

  /**
   * Method constructor.
   * @param authenticationManager AuthenticationManager
   * @param passwordEncoder PasswordEncoder
   * @param tokenProvider JwtTokenProvider
   */
  @Autowired
  public LoginController(AuthenticationManager authenticationManager,
                         PasswordEncoder passwordEncoder,
                         JwtTokenProvider tokenProvider) {
    this.authenticationManager = authenticationManager;
    this.passwordEncoder = passwordEncoder;
    this.tokenProvider = tokenProvider;
  }

  /**
   * Method for authentication.
   * @param loginRequest LoginRequest
   * @return ResponseEntity
   */
  @PostMapping("/authenticate")
  public ResponseEntity authenticate(@Valid @RequestBody LoginRequest loginRequest) {
    Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    loginRequest.getUsername(),
                    loginRequest.getPassword()
            )
    );

    SecurityContext securityContext = SecurityContextHolder.getContext();
    securityContext.setAuthentication(authentication);

    UserPrincipal userPrincipal = (UserPrincipal) securityContext.getAuthentication()
            .getPrincipal();

    if (UserStatusType.ACTIVE.getCode().equals(userPrincipal.getStatus())) {
      String jwt = this.tokenProvider.generateToken(authentication);
      HttpHeaders httpHeaders = new HttpHeaders();
      httpHeaders.add(JwtAuthenticationFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
      return new ResponseEntity<>(getLoginResponseFromUserPrincipal(userPrincipal),
              httpHeaders, HttpStatus.OK);
    }

    return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .build();
  }

  private LoginResponse getLoginResponseFromUserPrincipal(UserPrincipal userPrincipal) {
    return LoginResponse.builder()
            .userId(userPrincipal.getUserId())
            .username(userPrincipal.getUsername())
            .fullName(userPrincipal.getFullName())
            .status(userPrincipal.getStatus())
            .roles(userPrincipal.getAuthorities()
                    .stream()
                    .map(GrantedAuthority::getAuthority)
            .collect(Collectors.toList()))
            .build();
  }

}
