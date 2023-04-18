import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import tester.Tester;

//Practice Exam Two
//Dante LoPriore

class Runner {
  int age;
  String name;

  Runner(int age, String name) {
    this.age = age;
    this.name = name;
  }

  boolean containsSequence(ArrayList<Runner> source, ArrayList<Runner> sequence) {
    boolean result = false;
    for (int r = 0; source.size() > r; r++) {
      boolean check = true;
      for (int c = 0; sequence.size() > c; c++) {
        check = source.get(r + c) == sequence.get(c);
      }
      return result || check;
    }
    return result;
  }

  boolean containsSequence2(ArrayList<Runner> source, ArrayList<Runner> sequence) {
    for (Runner that : source) {
      for (Runner other : sequence) {
        return !(that.name.equals(name) && that.age == other.age);
      }
    }
    return false;
  }
}

/*
 * lass EveryOtherElement implements Iterable { ArrayList arr;
 * 
 * EveryOtherElement(ArrayList arr) { this.arr = arr; }
 * 
 * public Iterator iterator() { return new EveryOtherElementIterator(this); }
 * 
 * public int size() { // TODO Auto-generated method stub return
 * this.arr.size(); } }
 */

/*
 * class EveryOtherElementIterator implements Iterator { EveryOtherElement elem;
 * int index1;
 * 
 * EveryOtherElementIterator(EveryOtherElement elem) { this.elem = elem;
 * this.index1 = 0; }
 * 
 * @Override public boolean hasNext() { // TODO Auto-generated method stub
 * return index1 < elem.size(); }
 * 
 * @Override public Object next() { // TODO Auto-generated method stub for) }
 * 
 * }
 */

class Utils {
  void removeEveryOther(ArrayList<Integer> list) {
    for (int i = 1; i < list.size(); i++) {
      list.remove(i);
    }
  }

  Iterator EveryOtherElement(Iterator i) {
    return i;

  }
}

class ExamplesExamTwoPractice {
  Runner r0 = new Runner(0, " ");
  Runner r1 = new Runner(10, "Dante");
  Runner r2 = new Runner(-11, "Pete");
  Runner r3 = new Runner(222, "Joe");

  ArrayList<Runner> lineup0 = new ArrayList<Runner>();
  ArrayList<Runner> lineup1 = new ArrayList<Runner>(Arrays.asList(this.r0, this.r1, this.r2));
  ArrayList<Runner> lineup2 = new ArrayList<Runner>(Arrays.asList(this.r0, this.r1, this.r2));
  ArrayList<Runner> lineup3 = new ArrayList<Runner>(Arrays.asList());

  ArrayList<Integer> arrayInt0;
  ArrayList<Integer> arrayInt1;
  ArrayList<Integer> arrayInt2;

  Utils ut;

  void initalData() {
    this.arrayInt0 = new ArrayList<Integer>();
    this.arrayInt1 = new ArrayList<Integer>(Arrays.asList(0));
    this.arrayInt2 = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4));

  }

  boolean testContainsSequence(Tester t) {
    return t.checkExpect(this.r0.containsSequence(this.lineup0, this.lineup0), false)
        && t.checkExpect(this.r1.containsSequence(this.lineup0, this.lineup3), false)
        && t.checkExpect(this.r0.containsSequence(this.lineup1, this.lineup2), true)
        && t.checkExpect(this.r1.containsSequence(this.lineup1, this.lineup1), true)
        && t.checkExpect(this.r3.containsSequence(this.lineup1, this.lineup2), true);
  }

  void testRemoveEveryOther(Tester t) {
    this.initalData();

  }
}
