package com.griddynamics.blockchain.validators;

import com.griddynamics.blockchain.blockchain.Blockchain;
import com.griddynamics.blockchain.pojos.Block;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class BlockchainValidatorTest {

  private static Blockchain blockchain;

  @BeforeEach
  void setUp() {
    blockchain = Blockchain.getInstance();
  }

  @Test
  void validateNewBlockShouldReturnFalseWhenBlockIsIncorrect() {
    // given
    Block block1 = Mockito.mock(Block.class);
    Block block2 = Mockito.mock(Block.class);
    blockchain.getBlocks().add(block1);
    BlockchainValidator blockchainValidator = new BlockchainValidator(blockchain);
    // when
    Mockito.when(block1.getHash()).thenReturn("wantedHash");
    Mockito.when(block2.getPreviousBlockHash()).thenReturn("Wrong");
    // then
    blockchainValidator.validateNewBlock(block2);
    // cleanup
    blockchain.getBlocks().clear();
  }

  @Test
  void validateNewBlockShouldReturnTrueWhenBlockIsCorrect() {
    // given
    blockchain.getBlocks().clear();
    Block block1 = Mockito.mock(Block.class);
    Block block2 = Mockito.mock(Block.class);
    blockchain.getBlocks().add(block1);
    BlockchainValidator blockchainValidator = new BlockchainValidator(blockchain);
    // when
    Mockito.when(block1.getHash()).thenReturn("wantedHash");
    Mockito.when(block2.getPreviousBlockHash()).thenReturn("wantedHash");
    // then
    blockchainValidator.validateNewBlock(block2);
    // cleanup
    blockchain.getBlocks().clear();
  }

  @Test
  void validateShouldReturnTrueWhenFirstBlockHasZeroAsPreviousBlock() {
    // given
    Block block1 = Mockito.mock(Block.class);
    BlockchainValidator blockchainValidator = new BlockchainValidator(blockchain);
    // when
    Mockito.when(block1.getPreviousBlockHash()).thenReturn("0");
    // then
    blockchainValidator.validateNewBlock(block1);
  }

  @Test
  void validateShouldReturnFalseWhenFirstBlockHasNotZeroAsPreviousBlock() {
    // given
    Block block1 = Mockito.mock(Block.class);
    BlockchainValidator blockchainValidator = new BlockchainValidator(blockchain);
    // when
    Mockito.when(block1.getPreviousBlockHash()).thenReturn("1");
    // then
    blockchainValidator.validateNewBlock(block1);
  }
}
