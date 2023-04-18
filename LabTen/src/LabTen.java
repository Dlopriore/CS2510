import java.util.ArrayList;
import java.util.Iterator;

import tester.Tester;

//to represent a Runner 
class Runner {
  int age;
  String name;

  //constructor
  Runner(int age, String name) {
    this.age = age;
    this.name = name;
  }

  //to override equals method
  public boolean equals(Object o) {
    if (o instanceof Runner) {
      Runner r = (Runner) o;
      return this.age == r.age && this.name.equals(r.name);
    }
    else {
      return false;
    }
  }

  public int hashCode() {
    return this.age * 37 + this.name.hashCode();
  }
}

class Deque<T> {
  Sentinel<T> header;

  //constructor which takes a particular Sentinel value to use.
  Deque(Sentinel<T> header) {
    this.header = header;
  }

  //convenience constructor which takes zero arguments and 
  //initializes the header to a new Sentinel<T>
  Deque() {
    this.header = new Sentinel<T>();
  }

  void addAtHead(T t) {
    this.header.addAtHead(t);

  }

  public boolean isEmpty() {
    // TODO Auto-generated method stub
    return this.header.isEmpty();
  }

  T removeFromHead() {
    return this.header.removeFromHead();
  }
}

//to represent a Sentinel 
class Sentinel<T> extends ANode<T> {

  //constructor which takes zero arguments, and initializes the next and prev 
  //fields of the Sentinel to the Sentinel itself.
  Sentinel() {
    this.next = this;
    this.prev = this;
  }

  public boolean isEmpty() {
    // TODO Auto-generated method stub
    return this.next.checkEmpty();
  }

  void addAtHead(T t) {
    new Node<T>(t, this.next, this);
  }

  @Override
  protected boolean checkEmpty() {
    // TODO Auto-generated method stub
    return false;
  }

  public T removeFromHead() {
    if (this.next.checkEmpty()) {
      throw new RuntimeException("You can't remove from a sentinel");
    }
    else {
      return this.next.remove();
    }
  }

  public T remove() {
    // TODO Auto-generated method stub
    throw new RuntimeException("You can't remove from a sentinel");
  }

}

//to represent the abstract class ANode
abstract class ANode<T> {
  ANode<T> next;
  ANode<T> prev;

  protected abstract boolean checkEmpty();

  protected abstract T remove();

}

//to represent a Node
class Node<T> extends ANode<T> {
  T data;

  //constructor which initializes the data field, and then initializes next and prev to null.
  Node(T data) {
    this.data = data;
    this.next = null;
    this.prev = null;
  }

  //convenience constructor which initialize the data field to the given value, 
  //initialize the next and prev fields to the given nodes, and also update the 
  //given nodes to refer back to this node.
  Node(T data, ANode<T> next, ANode<T> prev) {
    if (next == null || prev == null) {
      throw new IllegalArgumentException("Either Node should not be null");
    }

    this.data = data;
    this.next = next;
    this.prev = prev;
    next.prev = this;
    prev.next = this;
  }

  @Override
  public boolean checkEmpty() {
    // TODO Auto-generated method stub
    return true;
  }

  @Override
  public T remove() {
    // TODO Auto-generated method stub
    this.prev.next = this.next;
    this.next.prev = this.prev;
    return this.data;
  }
}

//to represent a stack
class Stack<T> {
  Deque<T> contents;

  Stack(Deque<T> contents) {
    this.contents = contents;
  }

  void push(T item) {
    contents.addAtHead(item);
  }

  boolean isEmpty() {
    return contents.isEmpty();
  }

  T pop() {
    return contents.removeFromHead();// removes and returns the top of the stack
  }
}

//to represent a Utils class
class Utils {
  <T> ArrayList<T> reverse(ArrayList<T> source) {
    for (int i = source.size(); i > 0; i--) {
      source.add(source.get(i));
    }
    return source;
  }
}

//to represent a String Creator
class StringCreator {
  String field;

  StringCreator(String field) {
    this.field = field;
  }

  void add(Character c) {
    this.field = field + c;
  }

  void remove() {
    this.field = field.substring(0, field.length() - 1);
  }

  String getString() {
    return field;
  }

  void undo() {
    this.field = field + field.substring(0, field.length() - 1);
  }
}

class ListofLists<T> implements Iterator<T> {
  ArrayList<ArrayList<T>> ArrayList;

  ListofLists(ArrayList<ArrayList<T>> ArrayList) {
    this.ArrayList = ArrayList;
  }

  void addNewList() {

  }

  void add(int index, T object) {

  }

  ArrayList<T> get(int index) {
    return null;
  }

  int size() {
    return this.ArrayList.size();
  }

  @Override
  public boolean hasNext() {
    // TODO Auto-generated method stub
    return this.size() > 0;
  }

  @Override
  public T next() {
    // TODO Auto-generated method stub
    return null;
  }
}

//to represent the tests and examples for LabTen.Java
class ExamplesLabTen {
  Runner r0 = new Runner(0, " ");
  Runner r1 = new Runner(10, "Dante");
  Runner r2 = new Runner(-11, "Pete");
  Runner r3 = new Runner(222, "Joe");

  //tests for Equals method
  void testEquals(Tester t) {
    t.checkExpect(this.r0.equals(r2), false);
    t.checkExpect(this.r0.equals(r0), true);
    t.checkExpect(this.r1.equals(r1), true);
  }

  //tests for hashCode method
  void testHashCode(Tester t) {
    t.checkExpect(this.r2.hashCode(), 2483631);
    t.checkExpect(this.r0.hashCode(), 32);
    t.checkExpect(this.r1.hashCode(), 65798932);
  }

  //Example of a Deque
  Deque<String> deqStr;

  //Example of a Sentinel
  Sentinel<String> sentStr;
  Sentinel<String> sentStr2;

  //Example of a Deque
  Deque<String> deqStr2;
  Deque<String> deqStr3;

  //Examples of Node
  //check on this
  Node<String> strNode1;
  Node<String> strNode2;
  Node<String> strNode3;
  Node<String> strNode4;
  Node<String> strNode5;
  Node<String> strNode6;
  Node<String> strNode7;
  Node<String> strNode8;
  Node<String> strNode9;

  //Example of a Deque
  Deque<Integer> deq;
  Deque<Integer> deqInt2;

  //Example of a Sentinel
  Sentinel<Integer> sentInt;

  //Examples of Node
  Node<Integer> intNode1;
  Node<Integer> intNode2;
  Node<Integer> intNode3;
  Node<Integer> intNode4;

  ANode<String> testSent;

  //to represent the initial data which is the tests 
  //and examples for the Deque.java class
  void initialData() {

    this.deqStr = new Deque<String>();

    this.sentStr = new Sentinel<String>();
    this.sentStr2 = new Sentinel<String>();

    this.deqStr2 = new Deque<String>(sentStr);

    this.strNode1 = new Node<String>("abc", sentStr, sentStr);
    this.strNode2 = new Node<String>("bcd", sentStr, strNode1);
    this.strNode3 = new Node<String>("cde", sentStr, strNode2);
    this.strNode4 = new Node<String>("def", sentStr, strNode3);

    this.deqStr3 = new Deque<String>(this.sentStr2);

    this.strNode5 = new Node<String>("alpha", sentStr, sentStr);
    this.strNode6 = new Node<String>("bravo", sentStr, strNode5);
    this.strNode7 = new Node<String>("charlie", sentStr, strNode6);
    this.strNode8 = new Node<String>("delta", sentStr, strNode7);

    this.strNode9 = new Node<String>("echo", strNode8, strNode7);

    this.deq = new Deque<Integer>();

    this.sentInt = new Sentinel<Integer>();

    this.deqInt2 = new Deque<Integer>(sentInt);

    this.intNode1 = new Node<Integer>(012, sentInt, sentInt);
    this.intNode2 = new Node<Integer>(123, sentInt, intNode1);
    this.intNode3 = new Node<Integer>(234, sentInt, intNode2);
    this.intNode4 = new Node<Integer>(345, sentInt, intNode3);
  }
  //tests for the removeFromHead method
  /*
   * void testRemoveFromHead(Tester t) { this.initialData(); t.checkException(new
   * RuntimeException("You can't remove from a sentinel"), this.deqStr,
   * "removeFromHead");
   * 
   * this.deqInt2.removeFromHead(); this.deqInt2.removeFromHead();
   * 
   * Node<Integer> n1Int = new Node<Integer>(2, sentInt, sentInt); Node<Integer>
   * n2Int = new Node<Integer>(1, n1Int, sentInt); Deque<Integer> dqInt = new
   * Deque<Integer>(sentInt); t.checkExpect(this.deqInt2.header.next, n2Int);
   * 
   * this.deqStr2.removeFromHead(); this.deqStr2.removeFromHead();
   * 
   * Node<String> n1String = new Node<String>("d", sentStr, sentStr); Node<String>
   * n2String = new Node<String>("c", n1String, sentStr); Deque<String> dqString =
   * new Deque<String>(sentStr2); t.checkExpect(this.deqStr2.header.next,
   * n2String);
   * 
   * }
   */
}