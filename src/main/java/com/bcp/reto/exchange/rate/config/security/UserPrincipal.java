package com.bcp.reto.exchange.rate.config.security;

import com.bcp.reto.exchange.rate.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class <b>UserPrincipal</b>.
 * <p></p>Class for save data of Authentication in UserPrincipal</p>
 * @author Giancarlo
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class UserPrincipal implements UserDetails, Serializable {

  private static final long serialVersionUID = -1201991407198146837L;

  private Long userId;
  private String username;
  private String fullName;
  private String password;
  private Integer status;

  private Collection<? extends GrantedAuthority> authorities;

  /**
   * Method for create UserPrincipal from User.
   * @param user User
   * @return UserPrincipal
   */
  public static UserPrincipal create(User user) {
    List<GrantedAuthority> grantedAuthorities = user.getRoles().stream()
            .map(role -> new SimpleGrantedAuthority(role.getName()))
            .collect(Collectors.toList());

    return new UserPrincipal(
            user.getUserId(),
            user.getUsername(),
            user.getName() + ' ' + user.getLastName(),
            user.getPassword(),
            user.getStatus(),
            grantedAuthorities
    );
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
