package com.spsamples.semaphore;

public class Application {

  public static void main(String[] args) {
    SemaphoreExecutor sample = new SemaphoreExecutor("Semaphore");
    sample.execute();
  }
}
