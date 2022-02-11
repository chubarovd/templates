package com.concsamples.execservice;

import com.concsamples.common.NamedThreadFactory;
import com.concsamples.common.util.SamplesUtils;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolSample {

  private static final String PREFIX = "scheduled";

  public static void scheduledThreadPoolSample() throws InterruptedException {
    NamedThreadFactory scheduledFactory = new NamedThreadFactory(PREFIX);
    ScheduledExecutorService scheduledExecutor =
        Executors.newScheduledThreadPool(1, scheduledFactory);

    // shows that no active threads initially
    SamplesUtils.printNowActiveWithPrefix(PREFIX);

    System.out.println("Schedule task...");
    Future<?> f = scheduledExecutor.schedule(
        () -> System.out.println("Hello, world!"),
        3,
        TimeUnit.SECONDS
    );

    // shows one active thread after scheduling task
    SamplesUtils.printNowActiveWithPrefix(PREFIX);

    // do something to shows that task really scheduled delayed
    for (int i = 0; i < 5; i++) {
      Thread.sleep(500);
      System.out.println("tick...");
    }

    Thread.sleep(1_000);

    // shows that one idle thread
    SamplesUtils.printNowActiveWithPrefix(PREFIX);

    System.out.println("Shutdown scheduled thread pool...");
    scheduledExecutor.shutdown();

    // shows that no active threads after thread pool shutdown
    SamplesUtils.printNowActiveWithPrefix(PREFIX);
  }
}
