package app.sync;

public class Deadlock {
  private String key = "res";
  public static void main(String[] args) {
    final String key1 = "spankie";
    final String key2 = "dee";
    // t1 tries to lock key1 then key2
    Thread t1 = new Thread() {
      public void run() {
        synchronized (key1) {
          System.out.println("Thread 1: locked resource 1");

          try {
            Thread.sleep(100); // mimick some workload
          } catch (Exception e) {
          }

          /// waiting for key2
          synchronized (key2) {
            System.out.println("Thread 1: locked resource 2");
          }
        }
      }
    };

    // t2 tries to lock key2 then key1
    Thread t2 = new Thread() {
      public void run() {
        synchronized (key2) {
          System.out.println("Thread 2: locked resource 2");

          try {
            Thread.sleep(100); 
          } catch (Exception e) {
          }

          // waiting...
          synchronized (key1) {
            System.out.println("Thread 2: locked resource 1");
          }
        }
      }
    };

    t1.start();
    t2.start();
  }
}