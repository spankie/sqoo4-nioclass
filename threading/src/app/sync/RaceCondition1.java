package app.sync;

/**
 * RaceCondition
 */
public class RaceCondition1 {

  public static void main(String[] args) {
    LongWrapper longWrapper = new LongWrapper(0l);

    Runnable r = () -> {
      for (int i = 0; i < 1_000; i++) {
        longWrapper.incrementValue();
      }
    };

    Thread t = new Thread(r);
    t.start();

    // try {
    // // wait for this thread to finish before continuing the application
    // t.join();
    // } catch (Exception e) {
    // // TODO: handle exception
    // System.err.println(e.getMessage());
    // }

    System.out.println("Value = " + longWrapper.getValue());
  }
}