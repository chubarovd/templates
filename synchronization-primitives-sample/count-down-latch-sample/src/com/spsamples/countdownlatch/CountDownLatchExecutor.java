package com.spsamples.countdownlatch;

import com.spsamples.common.AbstractSampleExecutor;
import java.util.concurrent.CountDownLatch;

public class CountDownLatchExecutor extends AbstractSampleExecutor {

  private final CountDownLatch cdl = new CountDownLatch(5);

  public CountDownLatchExecutor(String sampleName) {
    super(sampleName);
  }

  @Override
  protected void custom() {

    Thread decrementer = new Thread(() -> {
      System.out.println("Decrementer begins decrement...");
      while (cdl.getCount() >= 1) {
        System.out.format(" - Counter current value: %d\n", cdl.getCount());
        try {
          Thread.sleep(1_000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        cdl.countDown();
      }
    });

    Thread waiter = new Thread(() -> {
      System.out.println("Waiter thread begins wait...");
      try {
        cdl.await();
      } catch (InterruptedException e) {
        e.printStackTrace();
        return;
      }
      System.out.println("Waiter thread finally waited!");
    });

    waiter.start();

    try {
      // simulate little waiting to show that Waiter thread really wait
      Thread.sleep(2_000);
    } catch (InterruptedException e) {
      e.printStackTrace();
      waiter.interrupt();
      return;
    }

    decrementer.start();
  }
}
