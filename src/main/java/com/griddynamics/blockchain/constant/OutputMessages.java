package com.griddynamics.blockchain.constant;

public abstract class OutputMessages {

  public static final String N_INCREASED = "N was increased to ";
  public static final String N_DECREASED = "N was decreased to ";
  public static final String N_STAYS_THE_SAME = "N stays the same";

  public static final String GENERIC_MESSAGE = "user: message %d";

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
