package com.bcp.reto.exchange.rate.config.security;

import com.bcp.reto.exchange.rate.model.entity.User;
import com.bcp.reto.exchange.rate.model.enums.UserStatusType;
import com.bcp.reto.exchange.rate.repository.UserRepository;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Class <b>CustomUserDetailsService</b>.
 * <p>Class for get Users from DataBase</p>
 * @author Giancarlo
 */
@ToString
@Service
public class CustomUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

  @Autowired
  public CustomUserDetailsService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  @Transactional(readOnly = true)
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<User> user =  this.userRepository.findByUsernameAndStatus(
            username, UserStatusType.ACTIVE.getCode());

    if (!user.isPresent()) {
      throw new UsernameNotFoundException("The user "
              + username + " is not registered in the system");
    }

    return UserPrincipal.create(user.get());
  }
}
