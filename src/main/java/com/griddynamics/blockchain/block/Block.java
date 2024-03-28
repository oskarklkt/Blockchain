package com.griddynamics.blockchain.block;

import com.griddynamics.blockchain.constant.AppConstants;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Block {
  private final long minerId;
  private final long id;
  private final long timeStamp;
  private final String previousBlockHash;
  private final String hash;

  private final long magicNumber;

  private long secondsToGenerate;

  private List<String> messages;

  public Block(
      long minerId,
      long timeStamp,
      String previousBlockHash,
      String hash,
      long magicNumber,
      long id,
      long secondsToGenerate) {
    this.minerId = minerId;
    this.id = id;
    this.timeStamp = timeStamp;
    this.previousBlockHash = previousBlockHash;
    this.hash = hash;
    this.magicNumber = magicNumber;
    this.secondsToGenerate = secondsToGenerate;
  }

  @Override
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    if (this.messages == null || this.messages.isEmpty()) {
      stringBuilder.append("no messages");
    } else {
      for (String message : messages) {
        stringBuilder.append(System.lineSeparator());
        stringBuilder.append(message);
      }
    }
    String data = stringBuilder.toString();
    return String.format(
        AppConstants.BLOCK_TO_STRING,
        minerId,
        id,
        timeStamp,
        magicNumber,
        previousBlockHash,
        hash,
        data,
        secondsToGenerate);
  }
}
