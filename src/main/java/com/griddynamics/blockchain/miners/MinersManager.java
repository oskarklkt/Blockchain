package com.griddynamics.blockchain.miners;

import com.griddynamics.blockchain.pojos.Block;

public abstract class MinersManager {
  public static synchronized void mine(Miner miner, Block block) {
    miner.getController().addNewBlock(block);
  }
}
