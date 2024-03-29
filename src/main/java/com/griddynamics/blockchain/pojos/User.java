package com.griddynamics.blockchain.pojos;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@EqualsAndHashCode
public class User {
  private final String name;
  private int vcAmount;
  @Getter
  private static final List<User> users = new ArrayList<>();

  public User(String name, int vcAmount) {
    this.name = name;
    this.vcAmount = vcAmount;
    users.add(this);
  }

  public void increaseVcAmount(int amount) {
    vcAmount += amount;
  }

  public void decreaseVcAmount(int amount) {
    vcAmount -= amount;
  }
}
