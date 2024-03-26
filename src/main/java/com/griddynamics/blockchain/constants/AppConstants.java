package com.griddynamics.blockchain.constants;

public class AppConstants {
  public static final String HASHING_ALGORITHM = "SHA-256";
  public static final String CHARSET = "UTF-8";
  public static final String ZERO = "0";

  public static int REQUIRED_TRAILING_ZEROS;

  public static final String BLOCK_TO_STRING =
      """
                Block:
                Id: %d
                Timestamp: %d
                Magic number: %d
                Hash of the previous block:
                %s
                Hash of the block:
                %s
                Block was generating for %d seconds
                """;
}
