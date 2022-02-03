package com.spsamples.countdownlatch;

public class Application {

  public static void main(String[] args) {
    CountDownLatchExecutor sample = new CountDownLatchExecutor("CountDownLatch");
    sample.execute();
  }
}
