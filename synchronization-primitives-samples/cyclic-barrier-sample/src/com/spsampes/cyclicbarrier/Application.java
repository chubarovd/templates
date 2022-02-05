package com.spsampes.cyclicbarrier;

import com.spsamples.common.AbstractSampleExecutor;

public class Application {

  public static void main(String[] args) {
    AbstractSampleExecutor sample = new CyclicBarrierExecutor("CyclicBarrier");
    sample.execute();
  }
}
