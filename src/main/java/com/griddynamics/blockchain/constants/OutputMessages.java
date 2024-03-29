package com.griddynamics.blockchain.constants;

public abstract class OutputMessages {

  public static final String N_INCREASED = "N was increased to ";
  public static final String N_DECREASED = "N was decreased to ";
  public static final String N_STAYS_THE_SAME = "N stays the same";

  public static final String GENERIC_MESSAGE = "%s sent %d VC to %s | message number: %d";

  public static final String BLOCK_TO_STRING =
      """
                    Block:
                    Created by miner%d
                    miner%d gets 100 VC
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
