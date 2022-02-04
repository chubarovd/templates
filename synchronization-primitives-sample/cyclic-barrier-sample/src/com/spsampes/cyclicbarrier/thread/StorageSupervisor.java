package com.spsampes.cyclicbarrier.thread;

import com.spsampes.cyclicbarrier.util.Storage;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class StorageSupervisor extends Thread {

  private final CyclicBarrier cb;
  private final Storage storage;

  public StorageSupervisor(CyclicBarrier cb, Storage storage) {
    this.cb = cb;
    this.storage = storage;
  }

  @Override
  public void run() {
    try {
      System.out.println("StorageSupervisor: waiting for storage to fill up...");
      cb.await();
      System.out.println("StorageSupervisor: storage contains " + storage.showData());
    } catch (InterruptedException | BrokenBarrierException e) {
      e.printStackTrace();
    }
  }
}
