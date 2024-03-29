package com.griddynamics.blockchain.messages;

import com.griddynamics.blockchain.pojos.Block;
import com.griddynamics.blockchain.blockchain.Blockchain;
import com.griddynamics.blockchain.constants.AppConstants;
import com.griddynamics.blockchain.constants.OutputMessages;
import com.griddynamics.blockchain.pojos.User;
import com.griddynamics.blockchain.validators.MessageValidator;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Messenger implements Runnable {
  public final static List<String> messages = Collections.synchronizedList(new ArrayList<>());
  private static int messageNumber = 1;

  @Override
  @SneakyThrows
  public void run() {
    while (!(Blockchain.getInstance().getBlocks().size() == AppConstants.NUMBER_OF_BLOCKS)) {
      while (messageNumber < AppConstants.NUMBER_OF_MESSAGES) {
        User sender = null;
        User receiver = null;
        do {
          // sleep to pass hyperskill tests
          Thread.sleep(40);
          int length = User.getUsers().size();
          if (length < 2) {
            continue;
          }
          Random random = new Random();
          sender = User.getUsers().get(random.nextInt(length));
          receiver = User.getUsers().get(random.nextInt(length));
        } while (sender == null || receiver == null || sender.equals(receiver));
        int amount = new Random().nextInt(sender.getVcAmount());
        if (amount == 0) {
          continue;
        }
        Message message =
            new Message(
                OutputMessages.GENERIC_MESSAGE.formatted(
                    sender.getName(), amount, receiver.getName(), messageNumber++),
                messages.size() + 1);
        MessageValidator messageValidator = new MessageValidator(message);
        if (messageValidator.validate()) {
          messages.add(message.getMessageData());
          sender.decreaseVcAmount(amount);
          receiver.increaseVcAmount(amount);
        }
      }
    }
  }

  public static synchronized void getMessages(Block block, List<String> usedMessages) {
    block.setMessages(messages);
    block.getMessages().removeAll(usedMessages);
  }
}
