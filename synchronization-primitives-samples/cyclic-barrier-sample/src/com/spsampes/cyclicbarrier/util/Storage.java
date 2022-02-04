package com.spsampes.cyclicbarrier.util;

import java.util.ArrayList;
import java.util.List;

public class Storage<T> {

  private final List<T> storage;

  public Storage(int initalSize) {
    this.storage = new ArrayList<>(initalSize);
  }

  public synchronized void put(T data) {
    storage.add(data);
  }

  public synchronized String showData() {
    return storage.toString();
  }
}
