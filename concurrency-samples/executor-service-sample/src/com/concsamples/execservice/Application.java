package com.concsamples.execservice;

import java.util.concurrent.ExecutionException;

public class Application {

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    //CachedThreadPoolSample.cachedThreadPoolSample();

    //FixedThreadPoolSample.fixedThreadPoolSample();

    ScheduledThreadPoolSample.scheduledThreadPoolSample();
  }
}
