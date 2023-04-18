import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.NoSuchElementException;
import tester.Tester;

interface IBT<T> extends Iterable<T> {
  //is this tree a node?
  boolean isNode();
}

class BTLeaf<T> implements IBT<T> {

  //this is not a node
  public boolean isNode() {
    return false;
  }

  //creates an Iterator for this leaf
  public Iterator<T> iterator() {
    return new DFIter<T>(this);
  }

}

class BTNode<T> implements IBT<T> {
  T data;
  IBT<T> left;
  IBT<T> right;

  BTNode(T data, IBT<T> left, IBT<T> right) {
    this.data = data;
    this.left = left;
    this.right = right;
  }

  //this is a node
  public boolean isNode() {
    return true;
  }

  //create an Iterator for this Node
  public Iterator<T> iterator() {
    return new DFIter<T>(this);
  }
}

class BFIter<T> implements Iterator<T> {
  ArrayDeque<IBT<T>> worklist;

  BFIter(IBT<T> source) {
    this.worklist = new ArrayDeque<IBT<T>>();
    this.addIfNode(source);
  }

  //EFFECT: add a tree to the worklist if it's a BTNode
  void addIfNode(IBT<T> tree) {
    if (tree.isNode()) {
      this.worklist.addLast(tree);
    }
  }

  //is there another item to produce?
  public boolean hasNext() {
    return this.worklist.size() > 0;
  }

  //return the next item from the tree
  //EFFECT: the next node is removed from the worklist, its children are added if they are nodes
  public T next() {
    if (!this.hasNext()) {
      throw new NoSuchElementException("no more items!");
    }
    BTNode<T> temp = (BTNode<T>) this.worklist.removeFirst();
    T dataToReturn = temp.data;
    this.addIfNode(temp.left);
    this.addIfNode(temp.right);
    return dataToReturn;
  }

}

class DFIter<T> implements Iterator<T> {
  ArrayDeque<IBT<T>> worklist;

  DFIter(IBT<T> source) {
    this.worklist = new ArrayDeque<IBT<T>>();
    this.addIfNode(source);
  }

  //EFFECT: add a tree to the worklist if it's a BTNode
  void addIfNode(IBT<T> tree) {
    if (tree.isNode()) {
      this.worklist.addFirst(tree);
    }
  }

  //is there another item to produce?
  public boolean hasNext() {
    return this.worklist.size() > 0;
  }

  //return the next item from the tree
  //EFFECT: the next node is removed from the worklist, its children are added if they are nodes
  public T next() {
    if (!this.hasNext()) {
      throw new NoSuchElementException("no more items!");
    }
    BTNode<T> temp = (BTNode<T>) this.worklist.removeFirst();
    T dataToReturn = temp.data;

    this.addIfNode(temp.right);
    this.addIfNode(temp.left);

    return dataToReturn;
  }

}

class Book {
  String name;
  double price;
  int year;

  Book(String name, double price, int year) {
    this.name = name;
    this.price = price;
    this.year = year;
  }
}

class Author {
  String name;
  int yob;

  Author(String name, int yob) {
    this.name = name;
    this.yob = yob;
  }

  //is this author the same as the given object?
  public boolean equals(Object o) {
    if (o instanceof Author) {
      Author that = (Author) o;
      return this.name.equals(that.name) && this.yob == that.yob;
    }
    else {
      return false;
    }
  }

  //produces a hashCode for this author
  public int hashCode() {
    return this.name.hashCode() * this.yob * 31;
  }
}

class Examples {
  IBT<String> leaf = new BTLeaf<String>();
  IBT<String> gNode = new BTNode<String>("G", this.leaf, this.leaf);
  IBT<String> fNode = new BTNode<String>("F", this.leaf, this.leaf);
  IBT<String> eNode = new BTNode<String>("E", this.leaf, this.leaf);
  IBT<String> dNode = new BTNode<String>("D", this.leaf, this.leaf);
  IBT<String> cNode = new BTNode<String>("C", this.fNode, this.gNode);
  IBT<String> bNode = new BTNode<String>("B", this.dNode, this.eNode);
  IBT<String> aNode = new BTNode<String>("A", this.bNode, this.cNode);
  BFIter<String> bfIter = new BFIter<String>(this.aNode);

  HashMap<String, Integer> phoneBook = new HashMap<String, Integer>();
  HashMap<Author, Book> library = new HashMap<Author, Book>();

  Author matthias = new Author("Matthias Felliesen", 1960);
  Book htdp = new Book("HtDP", 10.00, 2013);

  void testMap(Tester t) {
    this.phoneBook.put("Bob", 1234);
    this.phoneBook.put("Alice", 2345);

    t.checkExpect(this.phoneBook.get("Bob"), 1234);
    t.checkExpect(this.phoneBook.get("Sally"), null);
    t.checkExpect(this.phoneBook.containsKey("Sally"), false);

    this.library.put(matthias, htdp);
    t.checkExpect(this.matthias.equals(new Author("Matthias Felliesen", 1960)), true);
    t.checkExpect(this.library.get(new Author("Matthias Felliesen", 1960)), htdp);

    System.out.println(matthias.hashCode());
    System.out.println(new Author("Matthias Felliesen", 1960).hashCode());
  }

  void testIter(Tester t) {
    t.checkExpect(this.bfIter.hasNext(), true);
    t.checkExpect(this.bfIter.next(), "A");
    t.checkExpect(this.bfIter.hasNext(), true);
    t.checkExpect(this.bfIter.next(), "B");
    t.checkExpect(this.bfIter.hasNext(), true);
    t.checkExpect(this.bfIter.next(), "C");

    for (String s : this.aNode) {
      System.out.println(s);
    }
  }

}