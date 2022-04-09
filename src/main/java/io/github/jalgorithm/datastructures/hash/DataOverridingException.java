package io.github.jalgorithm.datastructures.hash;

public class DataOverridingException extends Exception {

  public DataOverridingException(String exceptionMessage) {
    super(exceptionMessage);
  }

  public DataOverridingException() {
    super("This cell is already filled with another object.");
  }
}
