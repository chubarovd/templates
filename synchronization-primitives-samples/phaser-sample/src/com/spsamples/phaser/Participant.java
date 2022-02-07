package com.spsamples.phaser;

import java.util.Random;
import java.util.concurrent.Phaser;

public class Participant extends Thread {

  private final Phaser phaser;
  private final String tag;
  private final int phasesCount;
  private final Random rand = new Random();

  public Participant(Phaser phaser, String tag, int phasesCount) {
    this.phaser = phaser;
    this.tag = tag;
    this.phasesCount = phasesCount;
  }

  @Override
  public void run() {
    try {
      for (int i = 0; i < phasesCount; i++) {
        int millis = (rand.nextInt(7) + 7) * 100;
        System.out.println(tag + " - sleep for " + millis);
        Thread.sleep(millis);
        System.out.println(tag + " - finished phase " + phaser.getPhase());
        phaser.arriveAndAwaitAdvance();
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      System.out.println(tag + " finished");
      phaser.arriveAndDeregister();
    }
  }

  @Override
  public synchronized void start() {
    phaser.register();
    super.start();
  }
}
