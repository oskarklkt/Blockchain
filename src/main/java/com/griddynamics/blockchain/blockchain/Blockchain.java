package com.griddynamics.blockchain.blockchain;

import com.griddynamics.blockchain.block.Block;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class Blockchain {
  private final List<Block> blocks;
  private boolean isBlockchainFull = false;
  private static Blockchain instance;
  public volatile int requiredTrailingZeros;

  private Blockchain() {
    this.blocks = new ArrayList<>();
    this.requiredTrailingZeros = 0;
  }

  public static Blockchain getInstance() {
    if (instance == null) {
      instance = new Blockchain();
    }
    return instance;
  }

  public synchronized List<Block> getBlocks() {
    return blocks;
  }

  public synchronized void increaseRequiredTrailingZeros() {
    requiredTrailingZeros++;
  }

  public synchronized void decreaseRequiredTrailingZeros() {
    requiredTrailingZeros--;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (Block block : blocks) {
      sb.append(block);
      sb.append('\n');
    }
    return sb.toString();
  }
}
