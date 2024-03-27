package com.griddynamics.blockchain;

import com.griddynamics.blockchain.block.Block;
import com.griddynamics.blockchain.blockchain.Blockchain;
import com.griddynamics.blockchain.constant.AppConstants;
import com.griddynamics.blockchain.controllers.BlockchainController;
import com.griddynamics.blockchain.utils.StringUtil;
import lombok.Getter;

import java.time.LocalTime;
import java.util.Date;
import java.util.Random;

@Getter
public class Miner implements Runnable {
  private static long numberOfMiners = 1;
  private final long id;

  private final Blockchain blockchain;
  private final BlockchainController controller;

  public Miner(BlockchainController controller, Blockchain blockchain) {
    id = numberOfMiners++;
    this.blockchain = blockchain;
    this.controller = controller;
  }

  @Override
  public synchronized void run() {
    while (!Blockchain.getInstance().isBlockchainFull()) {
      long startTime = LocalTime.now().getSecond();
      long timeStamp = new Date().getTime();
      long magicNumber;
      StringBuilder requiredBeginningOfHash;
      String previousBlockHash =
          blockchain.getBlocks().isEmpty()
              ? AppConstants.ZERO
              : blockchain.getBlocks().get(blockchain.getBlocks().size() - 1).getHash();
      String hash;
      do {
        requiredBeginningOfHash = new StringBuilder();
        requiredBeginningOfHash.append(
            AppConstants.ZERO.repeat(Math.max(0, blockchain.requiredTrailingZeros)));
        magicNumber = new Random().nextInt();
        hash =
            StringUtil.applySha256(
                String.valueOf(id) + timeStamp + previousBlockHash + magicNumber + id);
      } while (!hash.startsWith(requiredBeginningOfHash.toString()));
      Block block = new Block(id, timeStamp, previousBlockHash, hash, magicNumber);
      block.setSecondsToGenerate(Math.abs(LocalTime.now().getSecond() - startTime));
      MinersManager.mine(this, block);
    }
  }
}
