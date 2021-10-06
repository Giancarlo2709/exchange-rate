package com.bcp.reto.exchange.rate.adapter;

import com.bcp.reto.exchange.rate.model.api.response.RoleResponse;
import com.bcp.reto.exchange.rate.model.api.request.UserSaveRequest;
import com.bcp.reto.exchange.rate.model.api.response.UserSaveResponse;
import com.bcp.reto.exchange.rate.model.entity.Role;
import com.bcp.reto.exchange.rate.model.entity.User;
import com.bcp.reto.exchange.rate.model.enums.UserStatusType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class <b>UserAdapter</b>.
 *
 * <p>Allows casting of entities</p>
 *
 * @author Giancarlo
 */
public class UserAdapter {

  /**
   * ConvertUserSaveRequest to UserEntity.
   * @param userSaveRequest UserSaveRequest
   * @return UserEntity
   */
  public static User convertUserSaveRequestToUser(UserSaveRequest userSaveRequest) {
    return User.builder()
            .username(userSaveRequest.getUsername())
            .password(userSaveRequest.getPassword())
            .name(userSaveRequest.getName())
            .lastName(userSaveRequest.getLastName())
            .email(userSaveRequest.getEmail())
            .status(UserStatusType.ACTIVE.getCode())
            .roles(convertRoleIdsToListRoles(userSaveRequest.getRoles()))
            .build();
  }

  /**
   * Convert roles to Role.
   * @param roles List of Roles
   * @return List of Role
   */
  private static List<Role> convertRoleIdsToListRoles(List<Long> roles) {
    List<Role> roleEntityList = new ArrayList<>();
    if (!roles.isEmpty()) {
      roleEntityList = roles.stream()
              .map(id -> Role.builder().roleId(id).build())
              .collect(Collectors.toList());
    }
    return roleEntityList;
  }

  /**
   * Convert User to UserSaveResponse.
   * @param user User
   * @return UserSaveResponse
   */
  public static UserSaveResponse convertUserToUserSaveResponse(User user) {
    return UserSaveResponse.builder()
            .userId(user.getUserId())
            .username(user.getUsername())
            .name(user.getName())
            .lastName(user.getLastName())
            .email(user.getEmail())
            .roles(convertRolesToRoleResponses(user.getRoles()))
            .build();
  }

  /**
   * Convert roleEntities to RoleResponse.
   * @param roles List of Role
   * @return List of RoleResponse
   */
  private static List<RoleResponse> convertRolesToRoleResponses(List<Role> roles) {
    return roles.stream()
            .map(r -> RoleResponse.builder()
                    .roleId(r.getRoleId())
                    .name(r.getName())
                    .build()
            )
            .collect(Collectors.toList());
  }
}
