package com.bcp.reto.exchange.rate.model.api.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserSaveResponse {

  @Schema(type = "Long",
          name = "userId",
          description = "User ID",
          example = "1"
  )
  private Long userId;

  @Schema(type = "String",
          name = "username",
          description = "Username",
          example = "philip"
  )
  private String username;

  @Schema(type = "String",
          name = "name",
          description = "Name of user",
          example = "philip"
  )
  private String name;

  @Schema(type = "String",
          name = "lastName",
          description = "Lastname of user",
          example = "philip"
  )
  private String lastName;

  @Schema(type = "String",
          name = "email",
          description = "Email of user",
          example = "philip@gmail.com"
  )
  private String email;

  @Schema(type = "[com.bcp.reto.exchange.rate.model.api.response.RoleResponse;]",
          name = "roles",
          description = "List of roles",
          example = "[{ 'roleId' : 1, 'name' : 'ADMIN'}]"
  )
  private List<RoleResponse> roles;

}
