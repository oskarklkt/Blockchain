package com.griddynamics.blockchain.miners;

import com.griddynamics.blockchain.blockchain.Blockchain;
import com.griddynamics.blockchain.controllers.BlockchainController;
import com.griddynamics.blockchain.model.Block;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import static org.mockito.Mockito.*;

class MinerTest {
  @Test
  void testRunMethodCreatesAndSubmitsBlock() throws InterruptedException {
    // given
    Blockchain blockchain = mock(Blockchain.class);
    BlockchainController controller = mock(BlockchainController.class);
    // when
    when(blockchain.isBlockchainFull()).thenReturn(false, false, true);
    when(blockchain.getBlocks()).thenReturn(new ArrayList<>());
    when(blockchain.getRequiredTrailingZeros()).thenReturn(0);
    Miner miner = new Miner(controller, blockchain);
    Thread minerThread = new Thread(miner);
    minerThread.start();
    minerThread.join();
    // then
    verify(controller, atLeastOnce()).addNewBlock(any(Block.class));
  }

  @Test
  void testRunMethodWhenBlockIsNotNull() throws InterruptedException {
    // given
    Blockchain blockchain = mock(Blockchain.class);
    BlockchainController controller = mock(BlockchainController.class);
    // when
    when(blockchain.isBlockchainFull()).thenReturn(false, true);
    ArrayList<Block> blockList = new ArrayList<>();
    when(blockchain.getBlocks()).thenReturn(blockList);
    when(blockchain.getRequiredTrailingZeros()).thenReturn(0);
    Miner miner = new Miner(controller, blockchain);
    Thread minerThread = new Thread(miner);
    minerThread.start();
    minerThread.join();
    // then
    verify(controller, atLeastOnce()).addNewBlock(any(Block.class));
  }
}
