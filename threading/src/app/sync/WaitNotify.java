package app.sync;

class Customer {
  int amount = 10000;

  synchronized void withdraw(int amount) {
    System.out.println("going to withdraw...");

    if (this.amount < amount) {
      System.out.println("Less balance; waiting for deposit...");
      try {
        wait();
        System.out.println(amount + " has been withdrawn...");
      } catch (Exception e) {
      }
    }
    this.amount -= amount;
    System.out.println("withdraw completed...");
  }

  synchronized void deposit(int amount) {
    System.out.println("going to deposit...");
    this.amount += amount;
    System.out.println("deposit completed... ");
    notify();
  }
}

class WaitNotify {
  public static void main(String args[]) {
    final Customer c = new Customer();
    new Thread() {
      public void run() {
        c.withdraw(15000);
        // c2.withdraw();
      }
    }.start();
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    new Thread() {
      public void run() {
        c.deposit(10000);
      }
    }.start();

  }
}