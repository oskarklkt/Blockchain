package com.griddynamics.blockchain.blockchain;

import com.griddynamics.blockchain.Block;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class BlockchainValidatorTest {
  private Blockchain blockchain;
  private BlockchainController controller;
  private BlockchainValidator validator;

  @BeforeEach
  void init() {
    blockchain = new Blockchain();
    controller = new BlockchainController(blockchain);
    validator = new BlockchainValidator(blockchain);
  }

  @Test
  void validateBlockchainShouldReturnTrueWhenBlockchainIsValid() {
    // when
    controller.addNewBlock();
    controller.addNewBlock();
    // then
    assertTrue(validator.validateBlockchain());
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
    assertFalse(validator.validateBlockchain());
  }
}
