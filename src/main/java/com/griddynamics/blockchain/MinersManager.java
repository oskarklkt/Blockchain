package com.griddynamics.blockchain;

import com.griddynamics.blockchain.block.Block;

public abstract class MinersManager {
  public static synchronized void mine(Miner miner, Block block) {
    miner.getController().addNewBlock(block);
  }
}
