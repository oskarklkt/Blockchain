package com.griddynamics.blockchain.messages;

import com.griddynamics.blockchain.constants.AppConstants;
import org.junit.jupiter.api.Test;

import java.security.Signature;

import static org.junit.jupiter.api.Assertions.*;

class MessageTest {
  @Test
  void constructorInitializesCorrectly() {
    // given
    Message message = new Message("Hello, world!", 123L);
    // then
    assertNotNull(message.getPrivateKey());
    assertNotNull(message.getPublicKey());
    assertEquals("Hello, world!", message.getMessageData());
    assertEquals(123L, message.getId());
    assertEquals(2, message.getList().size());
  }

  @Test
  void signAndVerifySignature() throws Exception {
    // given
    Message message = new Message("Test data", 1L);
    byte[] signature = message.getList().get(1);
    Signature sig = Signature.getInstance(AppConstants.SIGNATURE_ALGORITHM);
    // when
    sig.initVerify(message.getPublicKey());
    sig.update("Test data".getBytes());
    // then
    assertTrue(sig.verify(signature));
  }
}
