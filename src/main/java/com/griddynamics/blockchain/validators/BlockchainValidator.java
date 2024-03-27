package com.griddynamics.blockchain.validators;

import com.griddynamics.blockchain.block.Block;
import com.griddynamics.blockchain.blockchain.Blockchain;
import com.griddynamics.blockchain.constant.AppConstants;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BlockchainValidator {
  private final Blockchain blockchain;

  public synchronized boolean validateNewBlock(Block block) {
    String previousBlockHash =
        blockchain.getBlocks().isEmpty()
            ? AppConstants.ZERO
            : blockchain.getBlocks().get(blockchain.getBlocks().size() - 1).getHash();
    return previousBlockHash.equals(block.getPreviousBlockHash());
  }
}
