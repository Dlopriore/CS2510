import java.util.function.Predicate;
import tester.Tester;
//Deque.java
//Dante LoPriore and Jake Simeone

//to represent a predicate class which 
//checks to see if the given integer is greater than 100
class CheckGreaterThan100 implements Predicate<Integer> {

  //to determine if the given integer is greater than 100.
  public boolean test(Integer t) {
    // TODO Auto-generated method stub
    return t > 100;
  }
}

//to represent a predicate class which 
class CheckLessThan100 implements Predicate<Integer> {

  //to determine if the given integer is less than 100.
  public boolean test(Integer t) {
    // TODO Auto-generated method stub
    return t < 100;
  }
}

//to represent a predicate class which 
//checks to see if the given string is the same word as ABC
class CheckSameWord implements Predicate<String> {

  //to determine if the given string is the same as ABC.
  public boolean test(String t) {
    // TODO Auto-generated method stub
    return t.equals("abc");
  }
}

//to represent a Deque 
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

  //to count the number of nodes in a list Deque, not including the header node
  int size() {
    return this.header.next.countSize();
  }

  //Effect: to consume a value of type T and inserts it at the front of the list
  void addAtHead(T t) {
    this.header.addAtHead(t);
  }

  //Effect: to consume a value of type T and inserts it at the end of the list
  void addAtTail(T t) {
    this.header.addAtTail(t);
  }

  //to remove the first node from this Deque
  T removeFromHead() {
    return this.header.removeFromHead();
  }

  //to remove the last node from this Deque
  T removeFromTail() {
    return this.header.removeFromTail();
  }

  //that takes an Predicate<T> and produces the first node in this Deque 
  //for which the given predicate returns true.
  public ANode<T> find(Predicate<T> pred) {
    return this.header.find(pred);
  }

  //to remove the given node from this Deque.
  void removeNode(ANode<T> n) {
    n.remove();
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

  //to compute the amount of nodes in a list Deque, not including the header node
  public int countSize() {
    return 0;
  }

  //Effect: to consume a value of type T and inserts it at the front of the list
  void addAtHead(T t) {
    new Node<T>(t, this.next, this);
  }

  //Effect: to consume a value of type T and inserts it at the end of the list
  void addAtTail(T t) {
    new Node<T>(t, this, this.prev);
  }

  //to remove the first node from this Deque
  public T removeFromHead() {
    if (this.next.countSize() == 0) {
      throw new RuntimeException("You can't remove from a sentinel");
    }
    else {
      return this.next.remove();
    }
  }

  //to remove the last node from this Deque
  public T removeFromTail() {
    if (this.prev.countSize() == 0) {
      throw new RuntimeException("You can't remove from a sentinel");
    }
    else {
      return this.prev.remove();
    }
  }

  //to remove a node from Deque
  T remove() {
    // TODO Auto-generated method stub
    throw new RuntimeException("You can't remove from a sentinel");
  }

  //that takes an Predicate<T> and produces the first node in this Deque 
  //for which the given predicate returns true.
  public ANode<T> find(Predicate<T> pred) {
    // TODO Auto-generated method stub
    return this.next.checkFindApply(pred);
  }

  //to check if the given predicate is true 
  ANode<T> checkFindApply(Predicate<T> pred) {
    // TODO Auto-generated method stub
    return this;
  }

  //Effect: to remove the given node from this Deque.
  void removeNode(ANode<T> n) {
    throw new RuntimeException("You can't remove from a sentinel");
  }

}

//to represent the abstract class ANode
abstract class ANode<T> {
  ANode<T> next;
  ANode<T> prev;

  //to compute the amount of nodes in a list Deque, not including the header node
  abstract int countSize();

  //to remove a node from Deque
  abstract T remove();

  //Effect: to remove the given node from this Deque.
  abstract void removeNode(ANode<T> n);

  //to check if the given predicate is true 
  abstract ANode<T> checkFindApply(Predicate<T> pred);
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

  //to compute the amount of nodes in a list Deque, not including the header node
  int countSize() {
    // TODO Auto-generated method stub
    return 1 + this.next.countSize();
  }

  //to remove a node from Deque
  public T remove() {
    // TODO Auto-generated method stub
    this.prev.next = this.next;
    this.next.prev = this.prev;
    return this.data;
  }

  //to check if the given predicate is true 
  public ANode<T> checkFindApply(Predicate<T> pred) {
    // TODO Auto-generated method stub
    if (pred.test(this.data)) {
      return this;
    }
    else {
      return this.next.checkFindApply(pred);
    }
  }

  //Effect: to remove the given node from this Deque.
  void removeNode(ANode<T> n) {
    // TODO Auto-generated method stub
    n.remove();
  }
}

//to represent tests and examples for the Deque.java class
class ExamplesDeque {

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

  //tests for the CheckGreaterThan100 method
  void testCheckGreaterThan100(Tester t) {
    t.checkExpect(new CheckGreaterThan100().test(0), false);
    t.checkExpect(new CheckGreaterThan100().test(10), false);
    t.checkExpect(new CheckGreaterThan100().test(100), false);
    t.checkExpect(new CheckGreaterThan100().test(1000), true);
  }

  //tests for the CheckLessThan100 method
  void testCheckLessThan100(Tester t) {
    t.checkExpect(new CheckLessThan100().test(0), true);
    t.checkExpect(new CheckLessThan100().test(10), true);
    t.checkExpect(new CheckLessThan100().test(100), false);
    t.checkExpect(new CheckLessThan100().test(1000), false);
  }

  //tests for the CheckSameWord method
  void testCheckSameWord(Tester t) {
    t.checkExpect(new CheckSameWord().test(""), false);
    t.checkExpect(new CheckSameWord().test("lol"), false);
    t.checkExpect(new CheckSameWord().test("party at Jake's"), false);
    t.checkExpect(new CheckSameWord().test("abc"), true);
  }

  //tests for the node constructor method
  void tesNodeConstructor(Tester t) {
    this.initialData();

    t.checkException(new IllegalArgumentException("Either Node should not be null"), this.strNode1,
        "Node");
    t.checkException(new IllegalArgumentException("Either Node should not be null"), this.intNode1,
        "Node");
    t.checkException(new IllegalArgumentException("Either Node should not be null"), this.strNode2,
        "Node");

  }

  //tests for the size method
  void testSize(Tester t) {
    this.initialData();

    t.checkExpect(this.deq.size(), 0);
    t.checkExpect(this.deqInt2.size(), 4);
    t.checkExpect(this.deqStr3.size(), 0);
    t.checkExpect(this.deqStr2.size(), 5);
  }

  //tests for the countSize method
  void testCountSize(Tester t) {
    this.initialData();

    t.checkExpect(this.strNode1.countSize(), 4);
    t.checkExpect(this.strNode2.countSize(), 3);
    t.checkExpect(this.strNode3.countSize(), 2);
    t.checkExpect(this.strNode4.countSize(), 1);
    t.checkExpect(this.sentStr.countSize(), 0);
  }

  //tests for the addAtHead method
  void testAddAtHead(Tester t) {
    this.initialData();

    this.deqStr.addAtHead("A");
    this.deqStr.addAtHead("B");
    Sentinel<String> testSent = new Sentinel<String>();
    Node<String> n1 = new Node<String>("B", testSent, testSent);
    Node<String> n2 = new Node<String>("A", testSent, n1);
    Deque<String> dq = new Deque<String>(testSent);

    t.checkExpect(this.deqStr, dq);

    this.deqStr2.addAtHead("A");
    this.deqStr2.addAtHead("B");
    Node<String> n1Test = new Node<String>("B", sentStr, sentStr);
    Node<String> n2Test = new Node<String>("A", sentStr, n1Test);
    Deque<String> dqTest = new Deque<String>(sentStr);

    t.checkExpect(this.deqStr2, dqTest);

    this.deqInt2.addAtHead(1);
    this.deqInt2.addAtHead(2);
    Node<Integer> n1Int = new Node<Integer>(2, sentInt, sentInt);
    Node<Integer> n2Int = new Node<Integer>(1, sentInt, n1Int);
    Deque<Integer> dqInt = new Deque<Integer>(sentInt);

    //t.checkExpect((Node<String>) this.deqStr.header.next, n2);
    t.checkExpect(this.deqInt2, dqInt);

  }

  //tests for the addAtTail method
  void testAddAtTail(Tester t) {
    this.initialData();

    this.deqStr.addAtTail("A");
    this.deqStr.addAtTail("B");
    Sentinel<String> testSent = new Sentinel<String>();
    Node<String> n1 = new Node<String>("A", testSent, testSent);
    Node<String> n2 = new Node<String>("B", testSent, n1);
    Deque<String> dq = new Deque<String>(testSent);
    t.checkExpect(this.deqStr, dq);

    this.deqStr2.addAtTail("A");
    this.deqStr2.addAtTail("B");
    Node<String> n1Test = new Node<String>("A", sentStr, sentStr);
    Node<String> n2Test = new Node<String>("B", sentStr, n1Test);
    Deque<String> dqTest = new Deque<String>(sentStr);

    t.checkExpect(this.deqStr2, dqTest);

    this.deqInt2.addAtTail(1);
    this.deqInt2.addAtTail(2);
    Node<Integer> n1Int = new Node<Integer>(1, sentInt, sentInt);
    Node<Integer> n2Int = new Node<Integer>(2, sentInt, n1Int);
    Deque<Integer> dqInt = new Deque<Integer>(sentInt);

    //t.checkExpect((Node<String>) this.deqStr.header.next, n2);
    t.checkExpect(this.deqInt2, dqInt);

  }

  //tests for the removeFromHead method
  void testRemoveFromHead(Tester t) {
    this.initialData();
    t.checkException(new RuntimeException("You can't remove from a sentinel"), this.deqStr,
        "removeFromHead");

    this.deqInt2.removeFromHead();
    this.deqInt2.removeFromHead();
    this.deqInt2.removeFromTail();
    this.deqInt2.removeFromTail();
    Node<Integer> n1Int = new Node<Integer>(2, sentInt, sentInt);
    Node<Integer> n2Int = new Node<Integer>(1, n1Int, sentInt);
    Deque<Integer> dqInt = new Deque<Integer>(sentInt);
    t.checkExpect(this.deqInt2.header.next, n2Int);

    this.deqStr2.removeFromHead();
    this.deqStr2.removeFromHead();
    this.deqStr2.removeFromTail();
    this.deqStr2.removeFromTail();
    Node<String> n1String = new Node<String>("d", sentStr, sentStr);
    Node<String> n2String = new Node<String>("c", n1String, sentStr);
    Deque<String> dqString = new Deque<String>(sentStr2);
    t.checkExpect(this.deqStr2.header.next, n2String);

  }

  //tests for the removeFromTail method
  void testRemoveFromTail(Tester t) {
    this.initialData();
    t.checkException(new RuntimeException("You can't remove from a sentinel"), this.deqStr,
        "removeFromTail");

    this.deqInt2.removeFromHead();
    this.deqInt2.removeFromHead();
    this.deqInt2.removeFromTail();
    this.deqInt2.removeFromTail();
    Node<Integer> n1Int = new Node<Integer>(2, sentInt, sentInt);
    Node<Integer> n2Int = new Node<Integer>(1, n1Int, sentInt);
    Deque<Integer> dqInt = new Deque<Integer>(sentInt);
    t.checkException(new RuntimeException("You can't remove from a sentinel"), this.deqStr,
        "removeFromTail");
    t.checkExpect(this.deqInt2.header.next, n2Int);

    this.deqStr2.removeFromHead();
    this.deqStr2.removeFromHead();
    this.deqStr2.removeFromTail();
    this.deqStr2.removeFromTail();
    Node<String> n1String = new Node<String>("d", sentStr, sentStr);
    Node<String> n2String = new Node<String>("c", n1String, sentStr);
    Deque<String> dqString = new Deque<String>(sentStr2);
    t.checkException(new RuntimeException("You can't remove from a sentinel"), this.deqStr,
        "removeFromTail");
    t.checkExpect(this.deqStr2.header.next, n2String);
  }

  //tests for the remove method
  void testRemove(Tester t) {
    this.initialData();

    t.checkException(new RuntimeException("You can't remove from a sentinel"), this.sentStr,
        "remove");
    t.checkExpect(this.intNode1.remove(), 10);
    t.checkExpect(this.intNode3.remove(), 234);
    t.checkExpect(this.strNode3.remove(), "cde");
    t.checkExpect(this.strNode2.remove(), "bcd");
    t.checkExpect(this.strNode8.remove(), "delta");
    t.checkExpect(this.strNode7.remove(), "charlie");
  }

  //tests for the find method
  void testFind(Tester t) {
    this.initialData();

    t.checkExpect(this.deqStr.find(new CheckSameWord()), this.deqStr.header);
    t.checkExpect(this.deqStr2.find(new CheckSameWord()), this.sentStr);
    t.checkExpect(this.deqStr3.find(new CheckSameWord()), this.sentStr2);
  }

  //tests for the checkFindApply method
  void testCheckFindApply(Tester t) {
    this.initialData();

    t.checkExpect(this.sentStr.checkFindApply(new CheckSameWord()), this.sentStr);
    t.checkExpect(this.strNode1.checkFindApply(new CheckSameWord()), this.strNode1);
  }

  //tests for the removeNode method
  void testRemoveNode(Tester t) {
    this.initialData();

    //this.deqStr2.removeNode(new Node<String>("j"));

  }
}
