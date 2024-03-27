package com.griddynamics.blockchain.controllers;

import com.griddynamics.blockchain.block.Block;
import com.griddynamics.blockchain.blockchain.Blockchain;
import com.griddynamics.blockchain.constant.AppConstants;
import com.griddynamics.blockchain.constant.OutputMessages;
import com.griddynamics.blockchain.validators.BlockchainValidator;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BlockchainController {
  private final Blockchain blockchain;
  private final BlockchainValidator validator;

  public synchronized void addNewBlock(Block block) {
    if (validator.validateNewBlock(block)) {
      blockchain.getBlocks().add(block);
      System.out.print(block);
      if (block.getSecondsToGenerate() < 10) {
        blockchain.increaseRequiredTrailingZeros();
        System.out.println(OutputMessages.nIncreased + blockchain.getRequiredTrailingZeros());
        System.out.print(System.lineSeparator());
      } else if (block.getSecondsToGenerate() > 60 && blockchain.getRequiredTrailingZeros() > 0) {
        blockchain.decreaseRequiredTrailingZeros();
        System.out.println(OutputMessages.nDecreased + blockchain.getRequiredTrailingZeros());
        System.out.print(System.lineSeparator());
      } else {
        System.out.println(OutputMessages.nStaysTheSame);
        System.out.print(System.lineSeparator());
      }
    }
    if (blockchain.getBlocks().size() == AppConstants.numberOfBlocks) {
      blockchain.setBlockchainFull(true);
    }
  }
}
