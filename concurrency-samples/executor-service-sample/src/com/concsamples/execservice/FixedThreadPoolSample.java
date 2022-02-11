package com.concsamples.execservice;

import com.concsamples.common.NamedThreadFactory;
import com.concsamples.common.util.SamplesUtils;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FixedThreadPoolSample {

  private static final String PREFIX = "fixed";

  public static void fixedThreadPoolSample() throws InterruptedException, ExecutionException {
    NamedThreadFactory fixedFactory = new NamedThreadFactory(PREFIX);
    ExecutorService fixedExecutor = Executors.newFixedThreadPool(1, fixedFactory);

    Thread.sleep(1_500);

    // shows that no active threads initially
    SamplesUtils.printNowActiveWithPrefix(PREFIX);

    // submit two task with some useful work inside
    // first task begin executing firstly
    Future<String> f1 = fixedExecutor.submit(() -> {
      System.out.println("Task 1 begin execution via " + Thread.currentThread().getName());
      String result = UUID.randomUUID().toString();
      Thread.sleep(1_500);
      System.out.println("Task 1 finished with " + result);
      return result;
    });
    // second task will waiting for first task completion because thread pool is fixed and has 1
    // number of threads
    Future<String> f2 = fixedExecutor.submit(() -> {
      System.out.println("Task 2 begin execution via " + Thread.currentThread().getName());
      String result = UUID.randomUUID().toString();
      Thread.sleep(1_500);
      System.out.println("Task 2 finished with " + result);
      return result;
    });

    Thread.sleep(500);

    // shows just one active thread
    SamplesUtils.printNowActiveWithPrefix(PREFIX);

    f1.get();
    f2.get();

    Thread.sleep(500);

    // shows one idle thread
    SamplesUtils.printNowActiveWithPrefix(PREFIX);

    System.out.println("Shutdown thread pool...");
    fixedExecutor.shutdown();

    Thread.sleep(500);

    // shows that no active threads after pool shutdown
    SamplesUtils.printNowActiveWithPrefix(PREFIX);
  }
}
