package com.griddynamics.blockchain;

import com.griddynamics.blockchain.blockchain.Blockchain;
import com.griddynamics.blockchain.blockchain.BlockchainController;
import com.griddynamics.blockchain.utils.AppConstantsUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
  public static void main(String[] args) {
    AppConstantsUtil.setRequiredTrailingZeros();
    Blockchain blockchain = new Blockchain();
    BlockchainController controller = new BlockchainController(blockchain);
    controller.addMultipleBlocks(5);
    log.info(blockchain.toString());
  }
}
