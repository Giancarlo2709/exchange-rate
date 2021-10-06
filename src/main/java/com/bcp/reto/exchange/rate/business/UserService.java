package com.bcp.reto.exchange.rate.business;

import com.bcp.reto.exchange.rate.model.api.request.UserSaveRequest;
import com.bcp.reto.exchange.rate.model.api.response.UserSaveResponse;
import io.reactivex.rxjava3.core.Maybe;

/**
 * Interface <b>UserService</b>.
 * @author Giancarlo
 */
public interface UserService {

  Maybe<UserSaveResponse> saveUser(UserSaveRequest userSaveRequest);
}
