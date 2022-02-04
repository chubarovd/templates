package com.spsampes.cyclicbarrier.thread;

import com.spsampes.cyclicbarrier.util.Producer;
import com.spsampes.cyclicbarrier.util.Storage;
import java.util.Objects;
import java.util.concurrent.CyclicBarrier;

public class Transporter<T> extends Thread {

  private final String threadName;
  private final CyclicBarrier cb;
  private final Producer<T> producer;
  private final Storage<T> storage;

  public Transporter(
      String threadName,
      CyclicBarrier cb,
      Producer<T> producer,
      Storage<T> storage
  ) {
    this.threadName = threadName;
    this.cb = cb;
    this.producer = producer;
    this.storage = storage;
  }

  @Override
  public void run() {
    try {
      while (true) {
        T data = producer.getNext();
        if (Objects.isNull(data)) {
          break;
        }
        storage.put(data);
        System.out.format("%s puts %s\n", threadName, data);
        Thread.sleep(500);
      }
      System.out.println(threadName + " finished transfer");
      cb.await();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
