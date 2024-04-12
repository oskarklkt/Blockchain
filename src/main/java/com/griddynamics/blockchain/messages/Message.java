package com.griddynamics.blockchain.messages;

import com.griddynamics.blockchain.constants.AppConstants;
import lombok.Getter;
import lombok.SneakyThrows;
import java.security.*;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Message {
  private final PrivateKey privateKey;
  private final PublicKey publicKey;
  private final List<byte[]> list;
  private final long id;

  @SneakyThrows
  public Message(String data, long id) {
    list = new ArrayList<>();
    KeyPair keys;
    keys = new KeyPair();
    keys.createKeys();
    this.privateKey = keys.getPrivateKey();
    this.publicKey = keys.getPublicKey();
    list.add(data.getBytes());
    list.add(sign(data));
    this.id = id;
  }

  @SneakyThrows
  private byte[] sign(String data) {
    Signature rsa = Signature.getInstance(AppConstants.SIGNATURE_ALGORITHM);
    rsa.initSign(getPrivateKey());
    rsa.update(data.getBytes());
    return rsa.sign();
  }

  public String getMessageData() {
    byte[] bytes = list.get(0);
    StringBuilder stringBuilder = new StringBuilder();
    for (byte b : bytes) {
      stringBuilder.append((char) b);
    }
    return stringBuilder.toString();
  }
}
