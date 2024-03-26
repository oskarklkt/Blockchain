package com.griddynamics.blockchain.blockchain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BlockchainControllerTest {
  private Blockchain blockchain;
  private BlockchainController controller;

  @BeforeEach
  void init() {
    blockchain = new Blockchain();
    controller = new BlockchainController(blockchain);
  }

  @Test
  void addNewBlockShouldCreateBlockWithIndex1() {
    // when
    controller.addNewBlock();
    // then
    assertEquals(1, blockchain.getBlocks().size());
    assertEquals(1, blockchain.getBlocks().get(0).getId());
  }

  @Test
  void addNewBlockShouldCreateBlockWithProperIndexAndPrevHashIfSizeNotGreaterThan0() {
    // when
    controller.addNewBlock();
    controller.addNewBlock();
    // then
    assertEquals(2, blockchain.getBlocks().size());
    assertEquals(2, blockchain.getBlocks().get(1).getId());
    assertEquals(
        blockchain.getBlocks().get(0).getHash(),
        blockchain.getBlocks().get(1).getPreviousBlockHash());
  }
}
