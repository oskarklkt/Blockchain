package com.griddynamics.blockchain.constant;

public abstract class AppConstants {
  public static final String HASHING_ALGORITHM = "SHA-256";
  public static final String CHARSET = "UTF-8";
  public static final String ZERO = "0";

  public static final Integer numberOfBlocks = 5;

  public static final Integer numberOfMiners = 8;
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
                Block was generating for %d seconds
                """;
}
