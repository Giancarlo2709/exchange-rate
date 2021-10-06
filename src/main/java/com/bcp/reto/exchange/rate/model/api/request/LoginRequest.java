package com.bcp.reto.exchange.rate.model.api.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class LoginRequest {

  @NotBlank(message = "{message.user.username.required}")
  private String username;

  @NotBlank(message = "{message.user.password.required}")
  private String password;
}
