package com.spsamples.semaphore;

import com.spsamples.common.AbstractSampleExecutor;

public class Application {

  public static void main(String[] args) {
    AbstractSampleExecutor sample = new SemaphoreExecutor("Semaphore");
    sample.execute();
  }
}
