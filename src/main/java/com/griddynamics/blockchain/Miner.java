package com.griddynamics.blockchain;

import com.griddynamics.blockchain.block.Block;
import com.griddynamics.blockchain.blockchain.Blockchain;
import com.griddynamics.blockchain.constant.AppConstants;
import com.griddynamics.blockchain.controllers.BlockchainController;
import com.griddynamics.blockchain.utils.StringUtil;
import lombok.Getter;
import lombok.SneakyThrows;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Getter
public class Miner implements Runnable {
  private static long numberOfMiners = 1;
  private final long id;

  public static List<String> usedMessages = new ArrayList<>();
  private final Blockchain blockchain;
  private final BlockchainController controller;

  public Miner(BlockchainController controller, Blockchain blockchain) {
    id = numberOfMiners++;
    this.blockchain = blockchain;
    this.controller = controller;
  }

  @Override
  @SneakyThrows
  public synchronized void run() {
    while (!blockchain.isBlockchainFull()) {
      long startTime = LocalTime.now().getSecond();
      long timeStamp = new Date().getTime();
      long magicNumber;
      long blockId = blockchain.getBlocks().size() + 1;
      String hash;
      String previousBlockHash =
          blockchain.getBlocks().isEmpty()
              ? AppConstants.ZERO
              : blockchain.getBlocks().get(blockchain.getBlocks().size() - 1).getHash();
      do {
        magicNumber = new Random().nextInt();
        hash =
            StringUtil.applySha256(
                previousBlockHash + blockId + magicNumber + timeStamp + startTime);
      } while (!hash.startsWith(AppConstants.ZERO.repeat(blockchain.getRequiredTrailingZeros())));
      Thread.sleep(20);
      MinersManager.mine(
          this, new Block(id, timeStamp, previousBlockHash, hash, magicNumber, blockId));
    }
  }
}
