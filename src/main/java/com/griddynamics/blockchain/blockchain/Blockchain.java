package com.griddynamics.blockchain.blockchain;

import com.griddynamics.blockchain.pojos.Block;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Blockchain {
  private final List<Block> blocks;
  private boolean isBlockchainFull = false;
  private static volatile Blockchain instance;
  private int requiredTrailingZeros;

  private Blockchain() {
    this.blocks = new ArrayList<>();
    this.requiredTrailingZeros = 0;
  }

  public static Blockchain getInstance() {
    if (instance == null) {
      synchronized (Blockchain.class) {
        if (instance == null) {
          instance = new Blockchain();
        }
      }
    }
    return instance;
  }

  public void increaseRequiredTrailingZeros() {
    requiredTrailingZeros++;
  }

  public void decreaseRequiredTrailingZeros() {
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
