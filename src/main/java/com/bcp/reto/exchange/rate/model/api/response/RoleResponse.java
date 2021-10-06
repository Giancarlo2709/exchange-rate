package com.bcp.reto.exchange.rate.model.api.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleResponse {

  @Schema(type = "Long",
          name = "roleId",
          description = "Role ID",
          example = "1"
  )
  private Long roleId;

  @Schema(type = "String",
          name = "name",
          description = "Name Of Role",
          example = "ADMIN"
  )
  private String name;

}
