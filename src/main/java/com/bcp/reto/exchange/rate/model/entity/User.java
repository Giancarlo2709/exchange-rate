package com.bcp.reto.exchange.rate.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {

  @Id
  @Column(name = "user_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long userId;

  @NotNull
  @NotEmpty
  private String username;

  @NotNull
  @NotEmpty
  private String password;

  @NotNull
  @NotEmpty
  private String name;

  @NotNull
  @NotEmpty
  @Column(name = "last_name")
  private String lastName;

  @NotNull
  @Email
  @Size(min = 10, max = 80)
  private String email;

  @NotNull
  private Integer status;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"),
          inverseJoinColumns = @JoinColumn(name = "role_id"),
          uniqueConstraints = { @UniqueConstraint(columnNames = { "user_id", "role_id" }) })
  private List<Role> roles;

}
