package com.griddynamics.blockchain;

import com.griddynamics.blockchain.constants.AppConstants;
import com.griddynamics.blockchain.utils.StringUtil;
import lombok.Getter;

import java.util.Random;

@Getter
public class Block {
  private final long id;
  private final long timeStamp;
  private final String previousBlockHash;
  private String hash;
  private long magicNumber;
  private final long secondsToGenerate;

  public Block(long id, long timeStamp, String previousBlockHash) {
    long start = System.currentTimeMillis();
    this.id = id;
    this.timeStamp = timeStamp;
    this.previousBlockHash = previousBlockHash;
    StringBuilder requiredBeginningOfHash = new StringBuilder();
    requiredBeginningOfHash.append(
        AppConstants.ZERO.repeat(Math.max(0, AppConstants.REQUIRED_TRAILING_ZEROS)));
    do {
      magicNumber = new Random().nextInt();
      hash =
          StringUtil.applySha256(String.valueOf(id) + timeStamp + previousBlockHash + magicNumber);
    } while (!hash.startsWith(requiredBeginningOfHash.toString()));
    this.secondsToGenerate = System.currentTimeMillis() - start;
  }

  @Override
  public String toString() {
    return String.format(
        AppConstants.BLOCK_TO_STRING,
        id,
        timeStamp,
        magicNumber,
        previousBlockHash,
        hash,
        secondsToGenerate);
  }
}
