package com.griddynamics.blockchain.controllers;


import com.griddynamics.blockchain.Messenger;
import com.griddynamics.blockchain.block.Block;
import com.griddynamics.blockchain.blockchain.Blockchain;
import com.griddynamics.blockchain.constant.AppConstants;
import com.griddynamics.blockchain.constant.OutputMessages;
import com.griddynamics.blockchain.validators.BlockchainValidator;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
public class BlockchainController {
  private final Blockchain blockchain;
  private final BlockchainValidator validator;
  public static List<String> usedMessages = Collections.synchronizedList(new ArrayList<>());

  public synchronized void addNewBlock(Block block) {
    if (validator.validateNewBlock(block)) {
      if (block.getId() == 1) {
        block.setMessages(null);
      } else {
        Messenger.getMessages(block, usedMessages);
        usedMessages.addAll(block.getMessages());
      }
      System.out.print(block);
      if (block.getSecondsToGenerate() < AppConstants.SECONDS_TO_GENERATE_INCREASE) {
        blockchain.increaseRequiredTrailingZeros();
        System.out.println(OutputMessages.N_INCREASED + blockchain.getRequiredTrailingZeros());
        System.out.print(System.lineSeparator());
      } else if (block.getSecondsToGenerate() > AppConstants.SECONDS_TO_GENERATE_DECREASE
              && blockchain.getRequiredTrailingZeros() > 0) {
        blockchain.decreaseRequiredTrailingZeros();
        System.out.println(OutputMessages.N_DECREASED + blockchain.getRequiredTrailingZeros());
        System.out.print(System.lineSeparator());
      } else {
        System.out.println(OutputMessages.N_STAYS_THE_SAME);
        System.out.print(System.lineSeparator());
      }
      blockchain.getBlocks().add(block);
    }
    if (blockchain.getBlocks().size() == AppConstants.NUMBER_OF_BLOCKS) {
      blockchain.setBlockchainFull(true);
    }
  }
}
