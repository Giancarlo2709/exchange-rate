package com.bcp.reto.exchange.rate.config.security;

import com.bcp.reto.exchange.rate.config.security.jwt.JwtAuthenticationEntryPoint;
import com.bcp.reto.exchange.rate.config.security.jwt.JwtAuthenticationFilter;
import com.bcp.reto.exchange.rate.config.security.jwt.JwtTokenProvider;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


/**
 * Class <b>SecurityConfig</b>.
 * Class for configuration de spring
 * @author Giancarlo
 */
@ToString
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final CustomUserDetailsService customUserDetailsService;
  private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
  private final JwtTokenProvider tokenProvider;

  /**
   * Method constructor.
   * @param customUserDetailsService CustomUserDetailsService
   * @param jwtAuthenticationEntryPoint JwtAuthenticationEntryPoint
   * @param tokenProvider JwtTokenProvider
   */
  @Autowired
  public SecurityConfig(CustomUserDetailsService customUserDetailsService,
                        JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
                        JwtTokenProvider tokenProvider) {
    this.customUserDetailsService = customUserDetailsService;
    this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
    this.tokenProvider = tokenProvider;
  }

  @Bean
  public JwtAuthenticationFilter jwtAuthenticationFilter() {
    return new JwtAuthenticationFilter(this.tokenProvider, this.customUserDetailsService);
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean(BeanIds.AUTHENTICATION_MANAGER)
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(this.customUserDetailsService)
            .passwordEncoder(passwordEncoder());
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
            .cors().and()
            .csrf().disable()
            .exceptionHandling()
            .authenticationEntryPoint(jwtAuthenticationEntryPoint)
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
            .antMatchers("/h2/**").permitAll()
            .antMatchers("/api/exchange/v1/authenticate").permitAll()
            .antMatchers(HttpMethod.POST, "/api/exchange/v1/users").permitAll()
            .antMatchers("/api/**").authenticated();

    http.headers().frameOptions().disable();
    http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
  }
}
