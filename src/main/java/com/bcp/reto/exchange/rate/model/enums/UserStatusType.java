package com.bcp.reto.exchange.rate.model.enums;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
public enum UserStatusType {

  ACTIVE(Integer.valueOf(1), "ACTIVE"),

  INACTIVE(Integer.valueOf(0), "INACTIVE");

  private static final List<UserStatusType> list = new ArrayList<>();

  private static final Map<Integer, UserStatusType> lookup = new HashMap<>();

  private Integer code;

  private String value;

  static {
    for (UserStatusType c : UserStatusType.values()) {
      lookup.put(c.getCode(), c);
      list.add(c);
    }
  }

  public Integer getCode() {
    return code;
  }

  public String getValue() {
    return value;
  }

  public static List<UserStatusType> getList() {
    return list;
  }

  public static UserStatusType getUserStatusType(Integer code) {
    return lookup.get(code);
  }
}
