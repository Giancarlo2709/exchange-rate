package com.bcp.reto.exchange.rate.model.api.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResponse {

  private Long userId;
  private String username;
  private String fullName;
  private Integer status;
  private List<String> roles;
}
