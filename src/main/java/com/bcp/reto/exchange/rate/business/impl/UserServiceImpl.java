package com.bcp.reto.exchange.rate.business.impl;

import com.bcp.reto.exchange.rate.adapter.UserAdapter;
import com.bcp.reto.exchange.rate.business.UserService;
import com.bcp.reto.exchange.rate.model.api.request.UserSaveRequest;
import com.bcp.reto.exchange.rate.model.api.response.UserSaveResponse;
import com.bcp.reto.exchange.rate.model.entity.User;
import com.bcp.reto.exchange.rate.repository.UserRepository;
import io.reactivex.rxjava3.core.Maybe;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Class <b>UserServiceImpl</b>.
 * <p>Services for Users</p>
 * @author Giancarlo
 */
@ToString
@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  @Autowired
  public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  @Transactional
  public Maybe<UserSaveResponse> saveUser(UserSaveRequest userSaveRequest) {
    userSaveRequest.setPassword(passwordEncoder.encode(userSaveRequest.getPassword()));
    User user = UserAdapter.convertUserSaveRequestToUser(userSaveRequest);
    return Maybe.just(UserAdapter
            .convertUserToUserSaveResponse(this.userRepository.save(user)));
  }
}
