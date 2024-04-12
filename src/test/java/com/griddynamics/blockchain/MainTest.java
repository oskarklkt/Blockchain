package com.griddynamics.blockchain;

import com.griddynamics.blockchain.blockchain.Blockchain;
import com.griddynamics.blockchain.constants.AppConstants;
import com.griddynamics.blockchain.pojos.Block;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {
  @Test
  @SneakyThrows
  void testMainMethod() {
    // given
    Blockchain blockchain = Blockchain.getInstance();
    for (int i = 0; i < AppConstants.NUMBER_OF_BLOCKS; i++) {
      blockchain.getBlocks().add(Mockito.mock(Block.class));
    }
    // when
    Thread mainThread = new Thread(() -> Main.main(new String[] {}));
    mainThread.start();
    mainThread.join();
    // then
    assertFalse(blockchain.getBlocks().isEmpty());
    // clean up singleton
    Blockchain.getInstance().getBlocks().clear();
  }
}
