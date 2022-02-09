package com.spsamples.exchanger;

import com.spsamples.common.AbstractSampleExecutor;

public class Application {

  public static void main(String[] args) {
    AbstractSampleExecutor sample = new ExchangerExecutor("Exchanger");
    sample.execute();
  }
}
