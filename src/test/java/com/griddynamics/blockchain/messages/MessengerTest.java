package com.griddynamics.blockchain.messages;

import com.griddynamics.blockchain.blockchain.Blockchain;

import com.griddynamics.blockchain.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessengerTest {
  @BeforeEach
  void setUp() {
    User.getUsers().clear();
    Blockchain.getInstance().getBlocks().clear();
  }

  @AfterEach
  void clearUp() {
    Blockchain.getInstance().getBlocks().clear();
  }

  @Test
  void testRunMethodWithTwoUsers() throws InterruptedException {
    // given
    User user1 = new User("User1", 100);
    User user2 = new User("User2", 50);
    Messenger messenger = new Messenger();
    Thread messengerThread = new Thread(messenger);
    // when
    messengerThread.start();
    Thread.sleep(1000);
    Blockchain.getInstance().setBlockchainFull(true);
    messengerThread.interrupt();
    messengerThread.join();
    // then
    assertFalse(
        Messenger.getMessages().isEmpty(), "Messages should be added after running Messenger");
    assertTrue(
        user1.getVcAmount() != 100 || user2.getVcAmount() != 50,
        "User balances should be updated after message transactions");
  }
}
