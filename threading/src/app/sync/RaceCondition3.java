package app.sync;

/**
 * RaceCondition2
 */
public class RaceCondition3 {

  public static void main(String[] args) {
    LongWrapper longWrapper = new LongWrapper(0l);

    // Runnable r = new Thread() {
    // public void run() {
    // for (int i = 0; i < 100; i++) {
    // longWrapper.incrementValue();
    // System.out.printf("id: %d = %d\n", this.getId(), longWrapper.getValue());
    // }
    // }
    // };

    Thread[] threads = new Thread[10];
    for (int i = 0; i < threads.length; i++) {
      Runnable r = new Thread() {
        public void run() {
          for (int i = 0; i < 10; i++) {
            longWrapper.incrementValue();
            System.out.printf("id: %d = %d\n", i, longWrapper.getValue());
          }
        }
      };
      threads[i] = new Thread(r);
      threads[i].start();
    }

    for (int i = 0; i < threads.length; i++) {
      try {
        threads[i].join();
      } catch (Exception e) {
        System.err.println(e.getMessage());
      }
    }
    /// the reason is read and write of l in longwrapper
    System.out.println("Value = " + longWrapper.getValue());
  }
}