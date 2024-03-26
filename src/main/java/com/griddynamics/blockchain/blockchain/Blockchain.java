package com.griddynamics.blockchain.blockchain;

import com.griddynamics.blockchain.Block;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Blockchain {
  private final List<Block> blocks;

  public Blockchain() {
    this.blocks = new ArrayList<>();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (Block block : blocks) {
      sb.append(block);
      sb.append(System.lineSeparator());
    }
    return sb.toString();
  }
}
