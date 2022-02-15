package com.cocnsamples.forkjoin;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class RecursiveTaskSample {

  public static void recursiveTaskSample() {
    Long[] array = new Long[10_000];
    Arrays.fill(array, 1L);
    List<Long> list = Arrays.asList(array);

    System.out.println(new SplitterArraySummator(list, 1_000).invoke());
  }

  static class SplitterArraySummator extends RecursiveTask<Long> {

    private final List<Long> array;
    private final int splitLimit;

    public SplitterArraySummator(List<Long> array, int splitLimit) {
      this.array = array;
      this.splitLimit = splitLimit;
    }

    @Override
    protected Long compute() {
      // if split limit reached - calculate given array pie
      if (array.size() <= splitLimit) {
        return array.stream()
            .reduce((sum, e) -> sum += e)
            .orElseThrow();
      }

      // else - split given array one more time
      SplitterArraySummator leftSum =
          new SplitterArraySummator(array.subList(0, array.size() / 2), splitLimit);
      SplitterArraySummator rightSum =
          new SplitterArraySummator(array.subList(array.size() / 2, array.size()), splitLimit);

      // start task execution by non-blocking method fork()
      leftSum.fork();
      rightSum.fork();

      // then join both tasks
      return leftSum.join() + rightSum.join();
    }
  }
}
