package com.spsamples.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Sample of {@link ReentrantLock} usage.
 *
 * Description: main thread takes lock. Side thread starts and tries to take lock and waits while
 * main thread will release it. Main thread releases lock and side thread takes lock. Side thread re takes
 * lock. Side thread should release lock twice to main thread could takes lock. Side thread releases
 * lock then main thread could takes lock.
 */
public class ReentrantLockSample {

  public static void execute() throws InterruptedException {
    ReentrantLock lock = new ReentrantLock();

    // main thread takes lock that means side thread can't take lock
    lock.lock();
    System.out.println("Main: lock taken");

    // see sideThread()...
    new Thread(() -> sideThread(lock)).start();

    for (int i = 0; i < 3; i++) {
      Thread.sleep(500);
      System.out.println("tick...");
    }

    // main thread released lock - side thread cat take lock
    lock.unlock();
    System.out.println("Main: lock released");

    Thread.sleep(100);
    if (!lock.tryLock()) {
      System.out.println("Main: lock is already taken, waiting for unlock...");
      lock.lock();
    }
    System.out.println("Main: lock taken");

    System.out.println("Main: Hello, side!");

    lock.unlock();
    System.out.println("Main: lock released");
  }

  private static void sideThread(Lock lock) {
    System.out.println();
    try {
      // side thread will take lock only when main thread released lock
      if (!lock.tryLock()) {
        System.out.println("Side: lock is already taken, waiting for unlock...");
        lock.lock();
      }
      System.out.println("Side: lock taken");

      System.out.println("Side: Hello, main!");

      // side thread takes lock again - therefore lock should be released it twice
      lock.lock();
      System.out.println("Side: lock taken one more time");

      Thread.sleep(500);

      System.out.println("Side: Hey, main, hello again!");

      // side thread released lock once - main thread still can't take lock
      lock.unlock();
      System.out.println("Side: lock released 1");
      Thread.sleep(500);

      // side thread released lock second time - main thread now can take lock
      lock.unlock();
      System.out.println("Side: lock released 2\n");
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
