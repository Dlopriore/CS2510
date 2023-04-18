import java.util.Iterator;
import java.util.NoSuchElementException;

import tester.Tester;

interface IList<E> extends Iterable<E> {
  //is this IList a cons list?
  boolean isCons();
}

//represents an empty list of E
class MtList<E> implements IList<E> {

  //produces an Iterator for this empty list
  public Iterator<E> iterator() {
    return new IListIter<E>(this);
  }

  @Override
  public boolean isCons() {
    return false;
  }

}

//represents a non-empty list of E
class ConsList<E> implements IList<E> {
  E first;
  IList<E> rest;

  ConsList(E first, IList<E> rest) {
    this.first = first;
    this.rest = rest;
  }

  //produces an Iterator for this non-empty list
  public Iterator<E> iterator() {
    return new IListIter<E>(this);
  }

  @Override
  public boolean isCons() {
    return true;
  }

}

class IListIter<E> implements Iterator<E> {
  IList<E> items;

  IListIter(IList<E> items) {
    this.items = items;
  }

  //is there another item to process?
  public boolean hasNext() {
    return this.items.isCons();
  }

  //get the next item from the list
  //EFFECT: items is set to the rest of items
  public E next() {
    if (!this.hasNext()) {
      throw new NoSuchElementException("no more items!!");
    }

    ConsList<E> cons = (ConsList<E>) items;
    E result = cons.first;
    this.items = cons.rest;
    return result;

  }

}

class Evens implements Iterator<Integer> {
  int current;

  Evens() {
    this.current = 0;
  }

  //is there another even number?
  public boolean hasNext() {
    return true;
  }

  //get the next even number
  public Integer next() {
    current += 2;
    return current - 2;
  }
}

class TakeN<T> implements Iterator<T>, Iterable<T> {
  Iterator<T> iter;
  int n;

  TakeN(Iterator<T> iter, int n) {
    this.iter = iter;
    this.n = n;
  }

  //is there another item to produce?
  public boolean hasNext() {
    return n > 0 && this.iter.hasNext();
  }

  //get the next item
  //EFFECT: n is decremented by 1
  public T next() {
    this.n = n - 1;
    return this.iter.next();
  }

  @Override
  public Iterator<T> iterator() {
    return this;
  }

}

class ExamplesLists {
  IList<String> mtStrings = new MtList<String>();
  IList<String> strings1 = new ConsList<String>("a", this.mtStrings);
  IList<String> strings2 = new ConsList<String>("b", this.strings1);
  IList<String> strings3 = new ConsList<String>("c", this.strings2);
  TakeN<Integer> takeN = new TakeN<Integer>(new Evens(), 10);

  IList<Boolean> bools = new ConsList<Boolean>(true, new MtList<Boolean>());

  IList<Integer> ints = new ConsList<Integer>(1, new MtList<Integer>());

  IList<Double> doubles = new ConsList<Double>(1.0, new MtList<Double>());

  void testIter(Tester t) {
    String result = "";
    for (String s : strings3) {
      result += s;
    }

    t.checkExpect(result, "cba");

    for (Integer i : takeN) {
      System.out.println(i);
    }

  }

}