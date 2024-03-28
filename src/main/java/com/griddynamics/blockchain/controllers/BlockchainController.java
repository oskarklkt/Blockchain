package com.griddynamics.blockchain.controllers;

import com.griddynamics.blockchain.block.Block;
import com.griddynamics.blockchain.blockchain.Blockchain;
import com.griddynamics.blockchain.constant.AppConstants;
import com.griddynamics.blockchain.constant.OutputMessages;
import com.griddynamics.blockchain.validators.BlockchainValidator;

public class BlockchainController {
  private final Blockchain blockchain;
  private final BlockchainValidator validator;

  public BlockchainController(Blockchain blockchain, BlockchainValidator validator) {
    this.blockchain = blockchain;
    this.validator = validator;
  }

  public synchronized void addNewBlock(Block block) {
    if (validator.validateNewBlock(block)) {
      blockchain.getBlocks().add(block);
      System.out.print(block);
      if (block.getSecondsToGenerate() < 10) {
        blockchain.increaseRequiredTrailingZeros();
        System.out.println(OutputMessages.N_INCREASED + blockchain.getRequiredTrailingZeros());
        System.out.print(System.lineSeparator());
      } else if (block.getSecondsToGenerate() > 60 && blockchain.getRequiredTrailingZeros() > 0) {
        blockchain.decreaseRequiredTrailingZeros();
        System.out.println(OutputMessages.N_DECREASED + blockchain.getRequiredTrailingZeros());
        System.out.print(System.lineSeparator());
      } else {
        System.out.println(OutputMessages.N_STAYS_THE_SAME);
        System.out.print(System.lineSeparator());
      }
    }
    if (blockchain.getBlocks().size() == AppConstants.NUMBER_OF_BLOCKS) {
      blockchain.setBlockchainFull(true);
    }
  }
}
