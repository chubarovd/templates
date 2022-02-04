package com.spsampes.cyclicbarrier;

public class Application {

  public static void main(String[] args) {
    CyclicBarrierExecutor sample = new CyclicBarrierExecutor("CyclicBarrier");
    sample.execute();
  }
}
