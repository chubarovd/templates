package com.spsamples.semaphore;

import com.spsamples.common.AbstractSampleExecutor;

/**
 * Sample of simple {@link java.util.concurrent.Semaphore} usage.
 *
 * There is {@link SharedResource} object - any resource that can be read and modified, - and two
 * threads: one of them for reading, second is for modifying. Concurrent access to resource is
 * governed by an instance of {@link java.util.concurrent.Semaphore} inside the resource, that makes
 * access consistent.
 */
public class SemaphoreExecutor extends AbstractSampleExecutor {

  private final SharedResource<String> resource = new SharedResource<>();

  public SemaphoreExecutor(String sampleName) {
    super(sampleName);
  }

  @Override
  protected void custom() {
    Thread reader = new Thread(() -> {
      for (int i = 0; i < 5; i++) {
        try {
          resource.read();
        } catch (Exception e) {
          e.printStackTrace();
          return;
        }
      }
    });

    Thread writer = new Thread(() -> {
      char letter = 'A';
      for (int i = 0; i < 5; i++, letter++) {
        try {
          resource.modify(String.valueOf(letter));
        } catch (Exception e) {
          e.printStackTrace();
          return;
        }
      }
    });

    reader.start();
    writer.start();
  }
}
