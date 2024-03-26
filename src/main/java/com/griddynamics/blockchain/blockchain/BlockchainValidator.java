package com.griddynamics.blockchain.blockchain;

import com.griddynamics.blockchain.Block;
import java.util.List;

public class BlockchainValidator {
  // Will be used later
  private final Blockchain blockchain;

  public BlockchainValidator(Blockchain blockchain) {
    this.blockchain = blockchain;
  }

  public boolean validateBlockchain() {
    List<Block> blocks = blockchain.getBlocks();
    for (int i = 1; i < blocks.size(); i++) {
      String previousBlockHash = blocks.get(i).getPreviousBlockHash();
      String actualPreviousBlockHash = blocks.get(i - 1).getHash();
      if (!(actualPreviousBlockHash.equals(previousBlockHash))) {
        return false;
      }
    }
    return true;
  }
}
