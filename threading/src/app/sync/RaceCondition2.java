package app.sync;

/**
 * RaceCondition2
 */
public class RaceCondition2 {

  public static void main(String[] args) {
    LongWrapper longWrapper = new LongWrapper(0l);

    Runnable r = () -> {
      for (int i = 0; i < 1_000; i++) {
        longWrapper.incrementValue();
      }
    };

    Thread[] threads = new Thread[1_000];
    for (int i = 0; i < threads.length; i++) {
      threads[i] = new Thread(r);
      threads[i].start();
    }

    for (int i = 0; i < threads.length; i++) {
      try {
        threads[i].join();
      } catch (Exception e) {
        // TODO: handle exception
        System.err.println(e.getMessage());
      }
    }
    /// the reason is read and write of l in longwrapper
    System.out.println("Value = " + longWrapper.getValue());
  }
}