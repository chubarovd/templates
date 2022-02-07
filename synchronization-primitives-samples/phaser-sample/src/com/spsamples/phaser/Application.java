package com.spsamples.phaser;

import com.spsamples.common.AbstractSampleExecutor;

public class Application {

  public static void main(String[] args) {
    AbstractSampleExecutor sample = new PhaserExecutor("Phaser");
    sample.execute();
  }
}
