package com.griddynamics.blockchain.validators;

import com.griddynamics.blockchain.messages.Message;
import com.griddynamics.blockchain.messages.Messenger;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MessageValidatorTest {
  @Test
  @SneakyThrows
  void validateSuccessful() {
    // given
    Messenger.getMessages().clear();
    String data = "Test data";
    Message message = new Message(data, 1);
    // then
    assertTrue(new MessageValidator(message).validate());
  }

  @Test
  @SneakyThrows
  void validateFailsDueToInvalidSignature() {
    // given
    Messenger.getMessages().clear();
    Message message = new Message("Test data", 1);
    message.getList().set(0, "Tampered data".getBytes());
    // then
    assertFalse(new MessageValidator(message).validate());
  }

  @Test
  @SneakyThrows
  void validateFailsDueToIncorrectID() {
    // given
    Messenger.getMessages().clear();
    Messenger.getMessages().add(String.valueOf(new Message("Dummy data", 1)));
    Message message = new Message("Test data", 1);
    // then
    assertFalse(new MessageValidator(message).validate());
  }
}
