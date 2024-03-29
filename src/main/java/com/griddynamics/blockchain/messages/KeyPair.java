package com.griddynamics.blockchain.messages;

import com.griddynamics.blockchain.constants.AppConstants;
import lombok.Getter;

import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

@Getter
public class KeyPair {

  private final KeyPairGenerator keyGen;
  private PrivateKey privateKey;
  private PublicKey publicKey;

  public KeyPair() throws NoSuchAlgorithmException {
    this.keyGen = KeyPairGenerator.getInstance(AppConstants.KEY_GENERATOR_ALGORITHM);
    this.keyGen.initialize(AppConstants.KEY_LENGTH);
  }

  public void createKeys() {
    java.security.KeyPair pair = this.keyGen.generateKeyPair();
    this.privateKey = pair.getPrivate();
    this.publicKey = pair.getPublic();
  }
}
