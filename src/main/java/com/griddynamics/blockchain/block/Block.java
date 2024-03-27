package com.griddynamics.blockchain.block;

import com.griddynamics.blockchain.blockchain.Blockchain;
import com.griddynamics.blockchain.constant.AppConstants;
import lombok.Getter;
import lombok.Setter;

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

  public Block(
      long minerId, long timeStamp, String previousBlockHash, String hash, long magicNumber) {
    this.minerId = minerId;
    this.id = Blockchain.getInstance().getBlocks().size() + 1;
    this.timeStamp = timeStamp;
    this.previousBlockHash = previousBlockHash;
    this.hash = hash;
    this.magicNumber = magicNumber;
  }

  @Override
  public String toString() {
    return String.format(
        AppConstants.BLOCK_TO_STRING,
        minerId,
        id,
        timeStamp,
        magicNumber,
        previousBlockHash,
        hash,
        secondsToGenerate);
  }
}
