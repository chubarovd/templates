package com.spsamples.phaser;

import com.spsamples.common.AbstractSampleExecutor;
import java.util.concurrent.Phaser;

/**
 * TODO ...
 */
public class PhaserExecutor extends AbstractSampleExecutor {

  public PhaserExecutor(String sampleName) {
    super(sampleName);
  }

  @Override
  protected void custom() {
    Phaser phaser = new Phaser();

    new Participant(phaser, "A", 3).start();
    new Participant(phaser, "BB", 3).start();
    new Participant(phaser, "CCC", 1).start();
  }
}
