package common;

public abstract class AbstractSampleExecutor {

  public final void execute(String sampleName) {
    System.out.println("\n********************");
    System.out.println(sampleName);
    System.out.println("********************\n");

    execute();
  }

  protected abstract void execute();
}
