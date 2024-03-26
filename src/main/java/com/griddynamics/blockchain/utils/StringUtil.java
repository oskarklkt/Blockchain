package com.griddynamics.blockchain.utils;

import com.griddynamics.blockchain.constants.AppConstants;
import java.security.MessageDigest;

public class StringUtil {
  // this class is created by hyperskill
  /* Applies Sha256 to a string and returns a hash. */
  public static String applySha256(String input) {
    try {
      MessageDigest digest = MessageDigest.getInstance(AppConstants.HASHING_ALGORITHM);
      /* Applies sha256 to our input */
      byte[] hash = digest.digest(input.getBytes(AppConstants.CHARSET));
      StringBuilder hexString = new StringBuilder();
      for (byte elem : hash) {
        String hex = Integer.toHexString(0xff & elem);
        if (hex.length() == 1) hexString.append(AppConstants.ZERO);
        hexString.append(hex);
      }
      return hexString.toString();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
