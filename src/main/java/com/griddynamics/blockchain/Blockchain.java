package com.griddynamics.blockchain;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
public class Blockchain {
  private final List<Block> blocks;

  public Blockchain() {
    this.blocks = new ArrayList<>();
  }

  public void addNewBlock() {
    long id = blocks.isEmpty() ? 1 : blocks.size() + 1;
    long timestamp = new Date().getTime();
    String previousBlockHash =
        blocks.isEmpty() ? AppConstants.ZERO : blocks.get(blocks.size() - 1).getHash();
    String hash = StringUtil.applySha256(String.valueOf(id) + timestamp + previousBlockHash);
    blocks.add(new Block(id, timestamp, previousBlockHash, hash));
  }

  // will be used later
  public boolean validateBlockchain() {
    for (int i = 1; i < blocks.size(); i++) {
      String previousBlockHash = blocks.get(i).getPreviousBlockHash();
      String actualPreviousBlockHash = blocks.get(i - 1).getHash();
      if (!(actualPreviousBlockHash.equals(previousBlockHash))) {
        return false;
      }
    }
    return true;
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
