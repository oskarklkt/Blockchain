package com.griddynamics.blockchain;

import com.griddynamics.blockchain.blockchain.Blockchain;
import com.griddynamics.blockchain.constant.AppConstants;
import com.griddynamics.blockchain.constant.OutputMessages;
import lombok.Getter;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
public class Messenger implements Runnable {
  public static List<String> messages = Collections.synchronizedList(new ArrayList<>());

  @Override
  @SneakyThrows
  public void run() {
    for (int i = 1; i <= AppConstants.NUMBER_OF_MESSAGES; i++) {
      messages.add(OutputMessages.GENERIC_MESSAGE.formatted(i));
      if (Blockchain.getInstance().getBlocks().size() == AppConstants.NUMBER_OF_BLOCKS - 1) {
        break;
      }
      Thread.sleep(40);
    }
  }

  public static void clearMessages() {
    messages = new ArrayList<>();
  }
}
