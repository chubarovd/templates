package com.spsamples.countdownlatch;

import com.spsamples.common.AbstractSampleExecutor;

public class Application {

  public static void main(String[] args) {
    AbstractSampleExecutor sample = new CountDownLatchExecutor("CountDownLatch");
    sample.execute();
  }
}
