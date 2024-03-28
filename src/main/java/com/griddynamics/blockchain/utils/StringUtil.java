package com.griddynamics.blockchain.utils;

import com.griddynamics.blockchain.constant.AppConstants;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public abstract class StringUtil {

  public static String applySha256(String input) {

    MessageDigest digest;
    try {
      digest = MessageDigest.getInstance(AppConstants.HASHING_ALGORITHM);
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException(e);
    }
    byte[] hash;
    try {
      hash = digest.digest(input.getBytes(AppConstants.CHARSET));
    } catch (UnsupportedEncodingException e) {
      throw new RuntimeException(e);
    }
    StringBuilder hexString = new StringBuilder();
    for (byte elem : hash) {
      String hex = Integer.toHexString(0xff & elem);
      if (hex.length() == 1) hexString.append(AppConstants.ZERO);
      hexString.append(hex);
    }
    return hexString.toString();
  }
}
