package com.griddynamics.blockchain.validators;

import com.griddynamics.blockchain.messages.Message;
import com.griddynamics.blockchain.messages.Messenger;
import lombok.SneakyThrows;
import java.security.Signature;
import java.util.List;

public class MessageValidator {

  private final List<byte[]> list;
  private final Message message;

  public MessageValidator(Message message) {
    this.message = message;
    this.list = message.getList();
  }

  @SneakyThrows
  public boolean validate() {
    Signature sig = Signature.getInstance("SHA1withRSA");
    sig.initVerify(message.getPublicKey());
    sig.update(list.get(0));

    return sig.verify(list.get(1)) && message.getId() == Messenger.messages.size() + 1;
  }
}
