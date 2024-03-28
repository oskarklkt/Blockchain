package com.griddynamics.blockchain;

import com.griddynamics.blockchain.block.Block;
import com.griddynamics.blockchain.blockchain.Blockchain;
import com.griddynamics.blockchain.constant.AppConstants;
import com.griddynamics.blockchain.constant.OutputMessages;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Messenger implements Runnable {
  public static List<String> messages = Collections.synchronizedList(new ArrayList<>());

  private static int messageNumber = 1;

  @Override
  @SneakyThrows
  public void run() {
    while (!(Blockchain.getInstance().getBlocks().size() == AppConstants.NUMBER_OF_BLOCKS)) {
      while (messageNumber < AppConstants.NUMBER_OF_MESSAGES) {
        Thread.sleep(40);
        messages.add(OutputMessages.GENERIC_MESSAGE.formatted(messageNumber++));
      }
    }
  }

  public static synchronized void getMessages(Block block, List<String> usedMessages) {
    block.setMessages(messages);
    block.getMessages().removeAll(usedMessages);
  }
}
