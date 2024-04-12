package com.griddynamics.blockchain.constants;

public abstract class AppConstants {
  public static final String HASHING_ALGORITHM = "SHA-256";
  public static final String CHARSET = "UTF-8";
  public static final String ZERO = "0";

  public static final Integer KEY_LENGTH = 1024;

  public static final String KEY_GENERATOR_ALGORITHM = "RSA";

  public static final String SIGNATURE_ALGORITHM = "SHA1withRSA";

  public static final Integer NUMBER_OF_BLOCKS = 15;

  public static final Integer SECONDS_TO_GENERATE_INCREASE = 10;
  public static final Integer SECONDS_TO_GENERATE_DECREASE = 20;

  public static final Integer NUMBER_OF_MINERS = 10;

  public static final Integer BARRIER = 4;

  public static final Integer NUMBER_OF_MESSAGES = 150;
}
