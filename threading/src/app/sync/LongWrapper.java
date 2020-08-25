package app.sync;

/**
 * A
 */
public class LongWrapper {
  private Object key = new Object();
  private long l;

  public LongWrapper(long l) {
    this.l = l;
  }

  public long getValue() {
    return l;
  }

  public void incrementValue() {
    synchronized (key) {
      // System.out.println(l);
      l = l + 1;
      // 1. get the initial value of l 
      // 2. add 1 to the value of l 
      // 3. set the value of l to the sum we get from step 2
    }
  }
}