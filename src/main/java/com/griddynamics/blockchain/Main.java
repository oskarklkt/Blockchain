package com.griddynamics.blockchain;

import com.griddynamics.blockchain.blockchain.Blockchain;
import com.griddynamics.blockchain.constant.AppConstants;
import com.griddynamics.blockchain.controllers.BlockchainController;
import com.griddynamics.blockchain.validators.BlockchainValidator;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;

public class Main {
  @SneakyThrows
  public static void main(String[] args) {
    List<Thread> threads = new ArrayList<>();
    for (int i = 0; i < AppConstants.numberOfMiners; i++) {
      Thread thread =
          new Thread(
              new Miner(
                  new BlockchainController(
                      Blockchain.getInstance(), new BlockchainValidator(Blockchain.getInstance())),
                  Blockchain.getInstance()));
      threads.add(thread);
    }
    threads.forEach(Thread::start);
    threads.forEach(Thread::interrupt);
  }
}
