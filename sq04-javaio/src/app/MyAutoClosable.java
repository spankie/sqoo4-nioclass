package app;

/**
 * MyAutoClosable
 */
public class MyAutoClosable implements AutoCloseable {

  public void DoSomething() throws Exception {
    System.out.println("I am extinguishing fire!!!");
    throw new Exception("Fire Could not be quenched");
  }

  @Override
  public void close() throws Exception {
    System.out.println("I am closing");
    throw new Exception("Closing exception");
  }

}