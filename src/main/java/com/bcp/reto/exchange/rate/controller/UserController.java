package com.bcp.reto.exchange.rate.controller;


import com.bcp.reto.exchange.rate.business.UserService;
import com.bcp.reto.exchange.rate.model.api.request.UserSaveRequest;
import com.bcp.reto.exchange.rate.model.api.response.ExchangeRateGetResponse;
import com.bcp.reto.exchange.rate.model.api.response.UserSaveResponse;
import io.reactivex.rxjava3.core.Maybe;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.awt.*;

/**
 * Class <b>UserController</b>.
 * <p>Class for endpoints of Users</p>
 * @author Giancarlo
 */
@ToString
@RestController
@RequestMapping("/api/exchange/v1/users")
@Schema(description = "User", name = "User")
public class UserController {

  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
  @Operation(summary = "Creacion de Usuario sin token",
          method = "POST",
          description = "Creacion de Usuario sin token",
          tags = {"User"})
  @ApiResponse(responseCode = "200",
          description = "Usuario creado",
          content = {
                  @Content(schema = @Schema(implementation = UserSaveResponse.class))
          })
  @ApiResponse(responseCode = "500",
          description = "ERROR",
          content = {
                  @Content(schema = @Schema(implementation = Exception.class))
          })
  public Maybe<UserSaveResponse> createUser(@Valid @RequestBody UserSaveRequest userSaveRequest) {
    return this.userService.saveUser(userSaveRequest);
  }

}
