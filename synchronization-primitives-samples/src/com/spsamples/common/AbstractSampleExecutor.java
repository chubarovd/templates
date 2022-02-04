package com.spsamples.common;

public abstract class AbstractSampleExecutor {

  private final String sampleName;

  protected AbstractSampleExecutor(String sampleName) {
    this.sampleName = sampleName;
  }

  public final void execute() {
    System.out.println("\n********************");
    System.out.println(sampleName);
    System.out.println("********************\n");

    custom();
  }

  protected abstract void custom();
}
