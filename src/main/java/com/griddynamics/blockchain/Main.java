package com.griddynamics.blockchain;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
  public static void main(String[] args) {
    Blockchain blockchain = new Blockchain();
    for (int i = 0; i < 5; i++) {
      blockchain.addNewBlock();
    }
    log.info(blockchain.toString());
  }
}
