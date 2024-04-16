package com.griddynamics.blockchain.model;

import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Generated
public class Block {
  private final long minerId;
  private final long id;
  private final long timeStamp;
  private final String previousBlockHash;
  private final String hash;
  private final long magicNumber;
  private final long secondsToGenerate;
  // messages are set later - that's why it's not final
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
    return
        """
                  Block:
                  Created by miner%d
                  miner%d gets 100 VC
                  Id: %d
                  Timestamp: %d
                  Magic number: %d
                  Hash of the previous block:
                  %s
                  Hash of the block:
                  %s
                  Block data: %s
                  Block was generating for %d seconds
                  """
        .formatted(
            minerId,
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
