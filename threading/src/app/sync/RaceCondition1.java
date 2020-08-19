package app.sync;

/**
 * RaceCondition
 */
public class RaceCondition1 {

  public static void main(String[] args) {
    LongWrapper longWrapper = new LongWrapper(0l);

    Runnable runnable = () -> {
      for (int i = 0; i < 1_000; i++) {
        longWrapper.incrementValue();
      }
    };

    // runnable.run();

    // the state of the thread is new
    Thread t = new Thread(runnable);
    // the thread is now new;
    t.start();
    //  and runnable;


    // t.sleep(200);

    // int i = 1;
    // for (i = 1; i < 100; i++) {
    //   if ((i % 2) == 0)
    //     System.out.println(i);
    // }

    try {
      // wait for this thread to finish before continuing the application
      t.join();
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }

    System.out.println("Value = " + longWrapper.getValue());
  }
}