package com.bcp.reto.exchange.rate.config.security.jwt;

import com.bcp.reto.exchange.rate.config.security.UserPrincipal;
import com.bcp.reto.exchange.rate.utils.Util;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;


/**
 * Class <b>JwtTokenProvider</b>.
 * <p>Class for generate token jwt</p>
 * @author Giancarlo
 */
@Component
public class JwtTokenProvider {

  private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

  @Value("${application.jwtSecret}")
  private String jwtSecret;

  @Value("${application.jwtExpirationInSeconds}")
  private int jwtExpirationInSeconds;

  /**
   * Method for generate token.
   * @param authentication Authentication
   * @return Token
   */
  public String generateToken(Authentication authentication) {
    UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

    Date now = new Date();
    Date expirationDate = new Date(now.getTime() + (jwtExpirationInSeconds * 1000L));

    return Jwts.builder()
            .setSubject(Util.encodeBase64(userPrincipal.getUsername()))
            .setIssuedAt(new Date())
            .setExpiration(expirationDate)
            .signWith(SignatureAlgorithm.HS512, jwtSecret)
            .compact();
  }

  /**
   * Method for get subject from token.
   * @param token Token
   * @return Subject
   */
  public String getUserNameFromJwt(String token) {
    Claims claims = getClaimsFromToken(token);

    return Util.decodeBase64(claims.getSubject());
  }

  /**
   * Method for get expiration token.
   * @param token Token
   * @return Date
   */
  public Date getExpirationFromJwt(String token) {
    Claims claims = getClaimsFromToken(token);

    return claims.getExpiration();
  }

  /**
   * Method for get Claims from token.
   * @param token Token
   * @return Claims
   */
  private Claims getClaimsFromToken(String token) {
    return Jwts.parser()
            .setSigningKey(jwtSecret)
            .parseClaimsJws(token)
            .getBody();
  }

  /**
   * Method for validate token.
   * @param authToken Token
   * @return Boolean
   */
  public boolean validateToken(String authToken) {
    try {
      Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
      return true;
    } catch (SignatureException ex) {
      logger.error("Invalid JWT signature");
    } catch (MalformedJwtException ex) {
      logger.error("Invalid JWT token");
    } catch (ExpiredJwtException ex) {
      logger.error("Expired JWT token");
    } catch (UnsupportedJwtException ex) {
      logger.error("Unsupported JWT token");
    } catch (IllegalArgumentException ex) {
      logger.error("JWT claims string is empty.");
    }
    return false;
  }

}
