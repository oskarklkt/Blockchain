package com.griddynamics.blockchain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
// I don't want to make this class a record yet, will see in future stages
public class Block {
  private final long id;
  private final long timeStamp;
  private final String previousBlockHash;
  private final String hash;

  @Override
  public String toString() {
    return String.format(AppConstants.BLOCK_TO_STRING, id, timeStamp, previousBlockHash, hash);
  }
}
