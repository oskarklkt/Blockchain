package com.griddynamics.blockchain.constant;

public abstract class AppConstants {
  public static final String HASHING_ALGORITHM = "SHA-256";
  public static final String CHARSET = "UTF-8";
  public static final String ZERO = "0";

  public static final Integer NUMBER_OF_BLOCKS = 7;

  public static final Integer NUMBER_OF_MINERS = 10;

  public static final Integer NUMBER_OF_MESSAGES = 10;
  public static final Integer SECONDS_TO_GENERATE_INCREASE = 10;
  public static final Integer SECONDS_TO_GENERATE_DECREASE = 60;

  public static final String BLOCK_TO_STRING =
      """
                Block:
                Created by miner # %d
                Id: %d
                Timestamp: %d
                Magic number: %d
                Hash of the previous block:
                %s
                Hash of the block:
                %s
                Block data: %s
                Block was generating for %d seconds
                """;
}
