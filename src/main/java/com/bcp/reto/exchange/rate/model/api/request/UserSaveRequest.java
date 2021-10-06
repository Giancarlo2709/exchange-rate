package com.bcp.reto.exchange.rate.model.api.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserSaveRequest {

  @Schema(type = "String",
          name = "username",
          description = "Username",
          example = "philip"
  )
  @NotBlank(message = "{message.user.username.required}")
  private String username;

  @Schema(type = "String",
          name = "password",
          description = "Password",
          example = "12345678"
  )
  @NotBlank(message = "{message.user.password.required}")
  private String password;

  @Schema(type = "String",
          name = "name",
          description = "Name of User",
          example = "Philip"
  )
  @NotBlank(message = "{message.user.name.required}")
  private String name;

  @Schema(type = "String",
          name = "lastName",
          description = "lastname of User",
          example = "Philip"
  )
  @NotBlank(message = "{message.user.lastName.required}")
  private String lastName;

  @Schema(type = "String",
          name = "email",
          description = "Email",
          example = "philip@gmail.com"
  )
  @NotBlank(message = "{message.user.email.required}")
  @Size(min = 10, max = 80, message = "{message.customer.email.length}")
  @Email(message = "{message.user.email.invalid}")
  private String email;

  @Schema(type = "[java.lang.Long;]",
          name = "email",
          description = "Email",
          example = "philip@gmail.com"
  )
  @NotNull(message = "{message.user.roles.required}")
  private List<Long> roles;
}
