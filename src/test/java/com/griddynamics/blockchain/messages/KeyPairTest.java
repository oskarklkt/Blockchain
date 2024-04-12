package com.griddynamics.blockchain.messages;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KeyPairTest {
  @Test
  @SneakyThrows
  void testKeyPairGeneratorInitialization() {
    // when
    KeyPair keyPair = new KeyPair();
    // then
    assertNotNull(keyPair.getKeyGen());
  }

  @Test
  @SneakyThrows
  void testCreateKeys() {
    // given
    KeyPair keyPair = new KeyPair();
    // when
    keyPair.createKeys();
    // then
    assertNotNull(keyPair.getPrivateKey());
    assertNotNull(keyPair.getPublicKey());
  }
}
