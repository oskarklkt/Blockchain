package com.griddynamics.blockchain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class BlockchainTest {

  private Blockchain blockchain;

  @BeforeEach
  void init() {
    blockchain = new Blockchain();
  }

  @Test
  void addNewBlockShouldCreateBlockWithIndex1() {
    // when
    blockchain.addNewBlock();
    // then
    assertEquals(1, blockchain.getBlocks().size());
    assertEquals(1, blockchain.getBlocks().get(0).getId());
  }

  @Test
  void addNewBlockShouldCreateBlockWithProperIndexAndPrevHashIfSizeNotGreaterThan0() {
    // when
    blockchain.addNewBlock();
    blockchain.addNewBlock();
    // then
    assertEquals(2, blockchain.getBlocks().size());
    assertEquals(2, blockchain.getBlocks().get(1).getId());
    assertEquals(
        blockchain.getBlocks().get(0).getHash(),
        blockchain.getBlocks().get(1).getPreviousBlockHash());
  }

  @Test
  void validateBlockchainShouldReturnTrueWhenBlockchainIsValid() {
    // when
    blockchain.addNewBlock();
    blockchain.addNewBlock();
    // then
    assertTrue(blockchain.validateBlockchain());
  }

  @Test
  void validateBlockchainShouldReturnFalseWhenBlockchainIsNotValid() {
    // given
    Block mockBlock1 = Mockito.mock(Block.class);
    Block mockBlock2 = Mockito.mock(Block.class);
    // when
    when(mockBlock1.getHash()).thenReturn("wrongHash");
    when(mockBlock2.getPreviousBlockHash()).thenReturn("differentHash");
    blockchain.getBlocks().add(mockBlock1);
    blockchain.getBlocks().add(mockBlock2);
    // then
    assertFalse(blockchain.validateBlockchain());
  }
}
