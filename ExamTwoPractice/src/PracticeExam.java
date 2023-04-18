import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import tester.Tester;

class Runner {
  int age;
  String name;

  Runner(int age, String name) {
    this.age = age;
    this.name = name;
  }

  //is this Runner the same as the given Runner
  boolean sameRunner(Runner r) {
    return this.age == r.age && this.name.equals(r.name);
  }
}

class Utils {
  //does source contain sequence in the same order?
  boolean containsSequence(ArrayList<Runner> source, ArrayList<Runner> sequence) {
    for (int h = 0; h <= source.size() - sequence.size(); h++) {
      boolean found = true;
      for (int n = 0; n < sequence.size(); n++) {
        if (!source.get(h + n).sameRunner(sequence.get(n)))
          found = false;
      }
      if (found)
        return true;
    }
    return false;

  }

  //EFFECT: every other element in the list is removed, starting by removing the second
  void removeEveryOther(ArrayList<Integer> alist) {
    ArrayList<Integer> toRemove = new ArrayList<Integer>();
    for (int i = 0; i < alist.size(); i++) {
      if (i % 2 != 0) {
        toRemove.add(alist.get(i));
      }
    }
    alist.removeAll(toRemove);
  }
}

class EveryOtherElement<T> implements Iterator<T> {
  Iterator<T> iter;

  EveryOtherElement(Iterator<T> iter) {
    this.iter = iter;
  }

  //is there another element to produce?
  public boolean hasNext() {
    return this.iter.hasNext();
  }

  //produce the next element
  //EFFECT: next is called on iter advancing the Iterator at least once
  public T next() {
    if (!this.hasNext()) {
      throw new NoSuchElementException("no more items!");
    }

    T temp = this.iter.next();

    if (this.hasNext()) {
      this.iter.next();
    }

    return temp;
  }

}

class Deque<T> {
  Sentinel header;

  Deque(Sentinel header) {
    this.header = header;
  }

  void append(Deque<T> other) {
    ANode<T> lastOfThis = this.header.prev;
    ANode<T> firstOfThat = other.header.next;
    ANode<T> lastOfThat = other.header.prev;

    this.header.prev = (Node) lastOfThat;
    lastOfThat.next = this.header;
    firstOfThat.prev = (Node<T>) lastOfThis;
    lastOfThis.next = (Node<T>) firstOfThat;
    other.header.next = other.header;
    other.header.prev = other.header;
  }
}

abstract class ANode<T> {
  Sentinel next;
  Node<T> prev;

}

class Sentinel<T> extends ANode<T> {
  Sentinel next;
  Sentinel prev;
}

class Node<T> extends ANode<T> {
  Node<T> next;
  Node<T> prev;
}

class Examples {
  Utils utils = new Utils();

  Runner r0 = new Runner(0, "A");
  Runner r1 = new Runner(10, "B");
  Runner r2 = new Runner(-11, "C");
  Runner r3 = new Runner(222, "D");
  Runner r4 = new Runner(64, "E");

  ArrayList<Runner> lineup0 = new ArrayList<Runner>();
  ArrayList<Runner> lineup1 = new ArrayList<Runner>(Arrays.asList(this.r0, this.r1, this.r2));
  ArrayList<Runner> lineup2 = new ArrayList<Runner>(Arrays.asList(this.r0, this.r1, this.r2));
  ArrayList<Runner> lineup4 = new ArrayList<Runner>(Arrays.asList(this.r0, this.r3, this.r4));
  ArrayList<Runner> lineup3 = new ArrayList<Runner>(Arrays.asList(this.r0, this.r1));

  ArrayList<Integer> arrayInt0;
  ArrayList<Integer> arrayInt1;
  ArrayList<Integer> arrayInt2;
  ArrayList<Integer> arrayInt3;

  void initalData() {
    this.arrayInt0 = new ArrayList<Integer>();
    this.arrayInt1 = new ArrayList<Integer>(Arrays.asList(0));
    this.arrayInt2 = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4));
    this.arrayInt3 = new ArrayList<Integer>();
  }
  // source = {r1 r2 r4 r2 r3 r4}
  //sequence = {r2 r3}
  //sequence2 = {r2 r1}

  // utils.containsSequence(source, sequence) -> true
  // utils.containsSequence(source, sequence2) -> false
  // utils.containsSequence(empty, sequence2) -> false
  // utils.containsSequence(empty, empty) -> true
  // utils.containsSequence(source, empty) -> true
  // utils.containsSequence(source, source) -> true
  // utils.containsSequence(sequence, source) -> false

  boolean testContainsSequence(Tester t) {
    return t.checkExpect(new Utils().containsSequence(this.lineup0, this.lineup0), true)
        && t.checkExpect(new Utils().containsSequence(this.lineup0, this.lineup3), false)
        && t.checkExpect(new Utils().containsSequence(this.lineup1, this.lineup2), true)
        && t.checkExpect(new Utils().containsSequence(this.lineup1, this.lineup1), true)
        && t.checkExpect(new Utils().containsSequence(this.lineup1, this.lineup4), false);
  }

  void testRemoveEveryOther(Tester t) {
    this.initalData();

    new Utils().removeEveryOther(this.arrayInt0);
    new Utils().removeEveryOther(this.arrayInt3);
    new Utils().removeEveryOther(this.arrayInt2);
    new Utils().removeEveryOther(this.arrayInt1);

    t.checkExpect(this.arrayInt0, new ArrayList<Integer>());
    t.checkExpect(this.arrayInt3, new ArrayList<Integer>());
    t.checkExpect(this.arrayInt1, new ArrayList<Integer>(Arrays.asList(0)));
    t.checkExpect(this.arrayInt2, new ArrayList<Integer>(Arrays.asList(1, 3)));
  }

  // list1 = {10,20,30,40,50}
  // utils.removeEveryOther(list1} 
  // list1 -> {10,30,50}
  // utils.removeEveryOther(emptyInts} 
  // emptyInts -> new ArrayList<Integer>()
  // list2 = {10,20,30,40,50,60}
  // utils.removeEveryOther(list2} 
  // list2 -> {10,30,50}
  // list3 = {10}
  // utils.removeEveryOther(list3} 
  // list3 -> {10}

  Iterator<Integer> iterInt = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4)).iterator();

  EveryOtherElement<Integer> eo = new EveryOtherElement<Integer>(iterInt);
  /*
   * eo.hasNext() -> true eo.next() -> 1 eo.hasNext() -> true
   * 
   */

}
