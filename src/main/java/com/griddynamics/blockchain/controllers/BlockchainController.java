package com.griddynamics.blockchain.controllers;

import com.griddynamics.blockchain.messages.Messenger;
import com.griddynamics.blockchain.pojos.User;
import com.griddynamics.blockchain.pojos.Block;
import com.griddynamics.blockchain.blockchain.Blockchain;
import com.griddynamics.blockchain.constants.AppConstants;
import com.griddynamics.blockchain.validators.BlockchainValidator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@Slf4j
@Getter
public class BlockchainController {
  private final Blockchain blockchain;
  private final BlockchainValidator validator;
  //todo move usedMessages and the whole logic somewhere else (SRP)
  public final static List<String> usedMessages = Collections.synchronizedList(new ArrayList<>());

  public synchronized void addNewBlock(Block block) {
    if (validator.validateNewBlock(block)) {
      if (block.getId() == 1) {
        block.setMessages(null);
      } else {
        Messenger.getMessages(block, usedMessages);
        usedMessages.addAll(block.getMessages());
      }
      log.info(String.valueOf(block));
      if (block.getSecondsToGenerate() < AppConstants.SECONDS_TO_GENERATE_INCREASE
          && blockchain.getRequiredTrailingZeros() <= AppConstants.BARRIER) {
        blockchain.increaseRequiredTrailingZeros();
        log.info(
            "N increased to {} {}",
            blockchain.getRequiredTrailingZeros(),
            System.lineSeparator().repeat(2));
      } else if (block.getSecondsToGenerate() > AppConstants.SECONDS_TO_GENERATE_DECREASE
          && blockchain.getRequiredTrailingZeros() > 0) {
        blockchain.decreaseRequiredTrailingZeros();
        log.info(
            "N decreased to {} {}",
            blockchain.getRequiredTrailingZeros(),
            System.lineSeparator().repeat(2));
      } else {
        log.info("N stays the same {}", System.lineSeparator().repeat(2));
      }
      blockchain.getBlocks().add(block);
      new User("miner%d".formatted(block.getMinerId()), 100);
    }
    if (blockchain.getBlocks().size() >= AppConstants.NUMBER_OF_BLOCKS) {
      blockchain.setBlockchainFull(true);
    }
  }
}
