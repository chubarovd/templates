package com.spsamples.semaphore;

import java.util.concurrent.Semaphore;

public class SharedResource<T> {

  private final Semaphore getSem = new Semaphore(0); // reading denied initially
  private final Semaphore putSem = new Semaphore(1);

  private T value;

  public void read() throws InterruptedException {
    getSem.acquire(); // try to take semaphore for reading
    System.out.println("Read: " + value);
    putSem.release(); // after reading allow modifying
  }

  public void modify(T newVal) throws InterruptedException {
    putSem.acquire(); // try to take semaphore for modifying
    value = newVal;
    System.out.println("Modify: " + value);
    getSem.release(); // after modifying allow reading
  }
}
