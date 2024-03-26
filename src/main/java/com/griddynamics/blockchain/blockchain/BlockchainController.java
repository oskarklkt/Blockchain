package com.griddynamics.blockchain.blockchain;

import com.griddynamics.blockchain.Block;
import com.griddynamics.blockchain.constants.AppConstants;
import lombok.AllArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
public class BlockchainController {
  private final Blockchain blockchain;

  public void addNewBlock() {
    List<Block> blocks = blockchain.getBlocks();
    long id = blocks.isEmpty() ? 1 : blocks.size() + 1;
    long timestamp = new Date().getTime();
    String previousBlockHash =
        blocks.isEmpty() ? AppConstants.ZERO : blocks.get(blocks.size() - 1).getHash();
    blocks.add(new Block(id, timestamp, previousBlockHash));
  }

  public void addMultipleBlocks(int numberOfBlocksToAdd) {
    for (int i = 0; i < numberOfBlocksToAdd; i++) {
      addNewBlock();
    }
  }
}
