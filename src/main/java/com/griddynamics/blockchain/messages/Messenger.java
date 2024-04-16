package com.griddynamics.blockchain.messages;

import com.griddynamics.blockchain.model.Block;
import com.griddynamics.blockchain.blockchain.Blockchain;
import com.griddynamics.blockchain.constants.AppConstants;
import com.griddynamics.blockchain.model.User;
import com.griddynamics.blockchain.validators.MessageValidator;
import lombok.Getter;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Getter
public class Messenger implements Runnable {
  @Getter
  private static final List<String> messages = Collections.synchronizedList(new ArrayList<>());

  private static final int SLEEP_DURATION = 40;

  @Override
  @SneakyThrows
  public void run() {
    while (!(Blockchain.getInstance().getBlocks().size() == AppConstants.NUMBER_OF_BLOCKS)) {
      while (messages.size() < AppConstants.NUMBER_OF_MESSAGES) {
        User sender = null;
        User receiver = null;
        do {
          //todo Awaitility
          Thread.sleep(SLEEP_DURATION);
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
                "%s sent %d VC to %s".formatted(sender.getName(), amount, receiver.getName()),
                messages.size() + 1);
        MessageValidator messageValidator = new MessageValidator(message);
        if (messageValidator.validate()) {
          addMessage( message.getMessageData());
          sender.decreaseVcAmount(amount);
          receiver.increaseVcAmount(amount);
        }
      }
    }
  }

  public static synchronized void addMessage(String messageData) {
    messages.add(messageData);
  }

  public static synchronized void getMessages(Block block, List<String> usedMessages) {
    block.setMessages(messages);
    block.getMessages().removeAll(usedMessages);
  }
}
