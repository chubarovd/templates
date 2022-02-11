package com.concsamples.execservice;

import com.concsamples.common.NamedThreadFactory;
import com.concsamples.common.util.SamplesUtils;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CachedThreadPoolSample {

  private static final String PREFIX = "cached";

  public static void cachedThreadPoolSample() throws ExecutionException, InterruptedException {
    NamedThreadFactory cachedFactory = new NamedThreadFactory(PREFIX);
    ExecutorService cachedExecutor = Executors.newCachedThreadPool(cachedFactory);

    // shows that no active threads yet
    SamplesUtils.printNowActiveWithPrefix(PREFIX);

    Future<?> f1 = cachedExecutor.submit(CachedThreadPoolSample::usefulWork);
    Thread.sleep(500); // should use Semaphor instead, but i was lazy

    // shows that just one active thread after starting first task
    SamplesUtils.printNowActiveWithPrefix(PREFIX);

    // while first task is not completed yet, start second task for force new thread creation
    Future<?> f2 = cachedExecutor.submit(CachedThreadPoolSample::usefulWork);

    f1.get();
    System.out.println("Task 1 finished");
    f2.get();
    System.out.println("Task 2 finished");

    // all tasks are finished, but two threads is idle now
    SamplesUtils.printNowActiveWithPrefix(PREFIX);

    Future<?> f3 = cachedExecutor.submit(CachedThreadPoolSample::usefulWork);
    f3.get();
    System.out.println("Task 3 finished");

    // shows that there no creation of third thread - executor service used one of idle threads
    SamplesUtils.printNowActiveWithPrefix(PREFIX);

    System.out.println("Shutdown thread pool...");
    cachedExecutor.shutdown();

    Thread.sleep(1_000);

    // shows that no active thread after executor service shutdown
    SamplesUtils.printNowActiveWithPrefix(PREFIX);
  }

  private static void usefulWork() {
    try {
      System.out.println(Thread.currentThread().getName() + " begin execution...");
      Thread.sleep(2_000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
