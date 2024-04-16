package com.griddynamics.blockchain.controllers;

import com.griddynamics.blockchain.blockchain.Blockchain;
import com.griddynamics.blockchain.constants.AppConstants;
import com.griddynamics.blockchain.model.Block;
import com.griddynamics.blockchain.validators.BlockchainValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class BlockchainControllerTest {
  @Mock private Blockchain blockchain = Mockito.mock(Blockchain.class);

  @Mock private BlockchainValidator validator = Mockito.mock(BlockchainValidator.class);

  private BlockchainController blockchainController;

  @BeforeEach
  public void setUp() {
    blockchainController = new BlockchainController(blockchain, validator);
  }

  @Test
  public void testAddNewBlockValidBlock() {
    // given
    Block block = mock(Block.class);
    // when
    when(block.getId()).thenReturn(2L);
    when(block.getSecondsToGenerate()).thenReturn(5L);
    when(validator.validateNewBlock(block)).thenReturn(true);
    List<Block> blocks = new ArrayList<>();
    when(blockchain.getBlocks()).thenReturn(blocks);
    blockchainController.addNewBlock(block);
    // then
    verify(blockchain, times(1)).increaseRequiredTrailingZeros();
    assert (blocks.contains(block));
  }

  @Test
  public void testAddNewBlockInvalidBlock() {
    // given
    Block block = mock(Block.class);
    // when
    when(validator.validateNewBlock(block)).thenReturn(false);
    when(blockchain.getBlocks()).thenReturn(new ArrayList<>());
    blockchainController.addNewBlock(block);
    // then
    assertTrue(blockchain.getBlocks().isEmpty());
  }

  @Test
  public void testAddNewBlockFirstBlock() {
    // given
    Block block = Mockito.mock(Block.class);
    when(block.getId()).thenReturn(1L);
    when(block.getPreviousBlockHash()).thenReturn("0");
    when(validator.validateNewBlock(block)).thenReturn(true);
    List<Block> blockList = new ArrayList<>();
    when(blockchain.getBlocks()).thenReturn(blockList);
    // when
    blockchainController.addNewBlock(block);
    // then
    assertEquals(1, blockList.size());
    assertEquals("0", blockList.get(0).getPreviousBlockHash());
  }

  @Test
  public void testDecreaseTrailingZeros() {
    // given
    Block block = Mockito.mock(Block.class);
    // when
    when(block.getSecondsToGenerate())
        .thenReturn((long) (AppConstants.SECONDS_TO_GENERATE_DECREASE + 1));
    when(blockchain.getRequiredTrailingZeros()).thenReturn(1);
    when(validator.validateNewBlock(block)).thenReturn(true);
    when(blockchain.getBlocks()).thenReturn(new ArrayList<>());
    blockchainController.addNewBlock(block);
    // then
    verify(blockchain, times(1)).decreaseRequiredTrailingZeros();
  }

  @Test
  public void testTrailingZerosStayTheSame() {
    // given
    Block block = Mockito.mock(Block.class);
    // when
    when(block.getSecondsToGenerate())
        .thenReturn(Long.valueOf(AppConstants.SECONDS_TO_GENERATE_DECREASE));
    when(blockchain.getRequiredTrailingZeros()).thenReturn(0);
    when(validator.validateNewBlock(block)).thenReturn(true);
    when(blockchain.getBlocks()).thenReturn(new ArrayList<>());
    blockchainController.addNewBlock(block);
    // then
    verify(blockchain, never()).decreaseRequiredTrailingZeros();
    verify(blockchain, never()).increaseRequiredTrailingZeros();
  }
}
