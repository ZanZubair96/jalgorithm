package io.github.jalgorithm.datastructures.hash;

public class TableIndexOutOfBoundException extends Exception {
  public TableIndexOutOfBoundException(){
    super("The key field of the object that you are assigning is out of the table");
  }
}
