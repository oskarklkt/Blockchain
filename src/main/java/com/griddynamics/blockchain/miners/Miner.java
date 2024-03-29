package com.griddynamics.blockchain.miners;

import com.griddynamics.blockchain.pojos.Block;
import com.griddynamics.blockchain.blockchain.Blockchain;
import com.griddynamics.blockchain.constants.AppConstants;
import com.griddynamics.blockchain.controllers.BlockchainController;
import com.griddynamics.blockchain.utils.StringUtil;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Getter
@Setter
public class Miner implements Runnable {
  private static long numberOfMiners = 1;
  private final long id;
  public final static List<String> usedMessages = new ArrayList<>();
  private final Blockchain blockchain;
  private final BlockchainController controller;

  public Miner(BlockchainController controller, Blockchain blockchain) {
    id = numberOfMiners++;
    this.blockchain = blockchain;
    this.controller = controller;
  }

  @Override
  public synchronized void run() {
    while (!blockchain.isBlockchainFull()) {
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
        hash = StringUtil.applySha256(previousBlockHash + blockId + magicNumber + timeStamp);
      } while (!hash.startsWith(AppConstants.ZERO.repeat(blockchain.getRequiredTrailingZeros())));
      MinersManager.mine(
          this,
          new Block(
              id,
              timeStamp,
              previousBlockHash,
              hash,
              magicNumber,
              blockId,
              (-1) * (timeStamp - new Date().getTime()) / 1000));
    }
  }
}
