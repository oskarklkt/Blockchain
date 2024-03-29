package com.griddynamics.blockchain.utils;

import com.griddynamics.blockchain.constants.AppConstants;
import lombok.SneakyThrows;
import java.security.MessageDigest;

public abstract class StringUtil {

  @SneakyThrows
  public static String applySha256(String input) {
    MessageDigest digest;
    digest = MessageDigest.getInstance(AppConstants.HASHING_ALGORITHM);
    byte[] hash;
    hash = digest.digest(input.getBytes(AppConstants.CHARSET));

    StringBuilder hexString = new StringBuilder();
    for (byte elem : hash) {
      String hex = Integer.toHexString(0xff & elem);
      if (hex.length() == 1) hexString.append(AppConstants.ZERO);
      hexString.append(hex);
    }
    return hexString.toString();
  }
}
