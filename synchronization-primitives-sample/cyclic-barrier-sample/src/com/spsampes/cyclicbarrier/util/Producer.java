package com.spsampes.cyclicbarrier.util;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Producer<T> {

  private final Queue<T> data;

  public Producer(List<T> data) {
    this.data = new LinkedList<>(data);
  }

  public synchronized T getNext() {
    return data.poll();
  }
}
