package com.griddynamics.blockchain.validators;

import com.griddynamics.blockchain.pojos.Block;
import com.griddynamics.blockchain.blockchain.Blockchain;
import com.griddynamics.blockchain.constants.AppConstants;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
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
