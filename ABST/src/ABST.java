import tester.Tester;
import java.util.Comparator;

//ABST.java
//Dante LoPriore and Jake Simeone
//Final Version

//to represent the abstract class of ABST
abstract class ABST<T> {
  Comparator<T> order;

  //constructor
  ABST(Comparator<T> order) {
    this.order = order;
  }

  //that takes an item and produces a new binary search tree 
  //with the given item inserted in the correct place
  public ABST<T> insert(T t) {
    // TODO Auto-generated method stub
    return new Node<T>(this.order, t, new Leaf<T>(order), new Leaf<T>(order));
  }

  //to determine whether that item is present in the binary search tree.
  public boolean present(T that) {
    return false;
  }

  //to produce the leftmost item contained in this tree. 
  abstract T getLeftmost();

  //to produce the tree containing all but the leftmost item of this tree.
  abstract ABST<T> getRight();

  //determines whether this binary search tree is the same as the given one: 
  //that is, they have matching structure and matching data in all nodes.
  abstract boolean sameTree(ABST<T> that);

  //to determine whether the given leaf is a leaf 
  boolean sameLeaf(Leaf<T> t) {
    return false;
  }

  //to determine whether the given node is a node 
  boolean sameNode(Node<T> t) {
    return false;
  }

  //to recur through the tree until it reaches the leaf case.
  abstract T leftAcc(T acc);

  //determines whether this binary search tree 
  //contains the same data in the same order as the given tree.
  abstract boolean sameData(ABST<T> that);

  //to represent the binary search tree of type T 
  //that produces a list of items in the tree in the sorted order.
  abstract IList<T> buildList();

}

//to represent a leaf
class Leaf<T> extends ABST<T> {

  //constructor
  Leaf(Comparator<T> order) {
    super(order);
  }

  //to produce the leftmost item contained in this tree. 
  T getLeftmost() {
    throw new RuntimeException("No leftmost item of an empty tree");
  }

  //to recur through the tree until it reaches the leaf case.
  T leftAcc(T acc) {
    // TODO Auto-generated method stub
    return acc;
  }

  //to produce the tree containing all but the leftmost item of this tree.
  ABST<T> getRight() {
    throw new RuntimeException("No right of an empty tree");
  }

  //determines whether this binary search tree is the same as the given one: 
  //that is, they have matching structure and matching data in all nodes.
  boolean sameTree(ABST<T> that) {
    // TODO Auto-generated method stub
    return that.sameLeaf(this);
  }

  //to determine whether the given leaf is a leaf 
  boolean sameLeaf(Leaf<T> t) {
    // TODO Auto-generated method stub
    return true;
  }

  //determines whether this binary search tree 
  //contains the same data in the same order as the given tree.
  boolean sameData(ABST<T> that) {
    // TODO Auto-generated method stub
    return that.sameTree(this);
  }

  //to determine whether that item is present in the binary search tree.
  public boolean present(T that) {
    return true;
  }

  //to represent the binary search tree of type T 
  //that produces a list of items in the tree in the sorted order.
  IList<T> buildList() {
    // TODO Auto-generated method stub
    return new MtList<T>();
  }
}

//to represent a node
class Node<T> extends ABST<T> {
  T data;
  ABST<T> left;
  ABST<T> right;

  //constructor
  Node(Comparator<T> order, T data, ABST<T> left, ABST<T> right) {
    super(order);
    this.data = data;
    this.left = left;
    this.right = right;
  }

  //that takes an item and produces a new binary search tree 
  //with the given item inserted in the correct place
  public ABST<T> insert(T t) {
    if (this.order.compare(this.data, t) <= 0) {
      return new Node<T>(this.order, this.data, this.left, this.right.insert(t));
    }
    else {
      return new Node<T>(this.order, this.data, this.left.insert(t), this.right);
    }
  }

  //to determine if an item is present in the binary search tree.
  public boolean present(T that) {
    // TODO Auto-generated method stub
    return this.order.compare(this.data, that) == 0 || this.left.present(that)
        || this.right.present(that);
  }

  //to produce the leftmost item contained in this tree. 
  T getLeftmost() {
    return this.left.leftAcc(this.data);
  }

  //to recur through the tree until it reaches the leaf case.
  T leftAcc(T acc) {
    return this.left.leftAcc(this.data);
  }

  //to produce the tree containing all but the leftmost item of this tree.
  ABST<T> getRight() {
    if (this.order.compare(this.data, this.getLeftmost()) == 0) {
      return this.right;
    }
    else {
      return new Node<T>(this.order, this.data, this.left.getRight(), this.right);
    }
  }

  //determines whether this binary search tree is the same as the given one: 
  //that is, they have matching structure and matching data in all nodes.
  boolean sameTree(ABST<T> that) {
    // TODO Auto-generated method stub
    return that.sameNode(this);
  }

  //to determine whether the given node is a node 
  boolean sameNode(Node<T> t) {
    return t.data.equals(this.data) && this.left.sameTree(t.left) && this.right.sameTree(t.right);
  }

  //determines whether this binary search tree 
  //contains the same data in the same order as the given tree.
  boolean sameData(ABST<T> that) {
    // TODO Auto-generated method stub
    if (this.getLeftmost().equals(that.getLeftmost())) {
      return this.getRight().sameData(that.getRight());
    }
    else {
      return false;
    }
  }

  //to represent the binary search tree of type T 
  //that produces a list of items in the tree in the sorted order.
  public IList<T> buildList() {
    return new ConsList<T>(this.getLeftmost(), this.getRight().buildList());
  }

}

//to represent a Book
class Book {
  String title;
  String author;
  int price;

  //constructor
  Book(String title, String author, int price) {
    this.title = title;
    this.author = author;
    this.price = price;
  }
}

//to represent BooksByTitle which compares the books by their title
class BooksByTitle implements Comparator<Book> {

  //to compare two given objects lexigraphically.
  public int compare(Book t1, Book t2) {
    // TODO Auto-generated method stub
    return t1.title.compareTo(t2.title);
  }
}

//to represent BooksByAuthor which compares the books by their author
class BooksByAuthor implements Comparator<Book> {

  //to compare two given objects lexigraphically.
  public int compare(Book t1, Book t2) {
    // TODO Auto-generated method stub
    return t1.author.compareTo(t2.author);
  }
}

//to represent BooksByPrice which compares the books by their price
class BooksByPrice implements Comparator<Book> {

  //to compare two given objects lexigraphically.
  public int compare(Book t1, Book t2) {
    // TODO Auto-generated method stub
    return t1.price - t2.price;
  }
}

//to represent a list of t
interface IList<T> {
}

//to represent an empty list of t
class MtList<T> implements IList<T> {
}

//to represent an non empty list of t
class ConsList<T> implements IList<T> {
  T first;
  IList<T> rest;

  //constructor
  ConsList(T first, IList<T> rest) {
    this.first = first;
    this.rest = rest;
  }
}

//tests and examples for the ABST.java class.
class ExamplesABST {

  //Examples of the Compare Books Classes and Leafs in a Tree
  ABST<Book> aBook1 = new Leaf<Book>(new BooksByAuthor());
  ABST<Book> aBook2 = new Leaf<Book>(new BooksByTitle());
  ABST<Book> aBook3 = new Leaf<Book>(new BooksByPrice());

  //Examples of the Nodes in a Tree
  ABST<Book> node1 = new Node<Book>(new BooksByAuthor(), this.b1,
      new Leaf<Book>(new BooksByAuthor()), new Leaf<Book>(new BooksByAuthor()));
  ABST<Book> node2 = new Node<Book>(new BooksByTitle(), this.b1, new Leaf<Book>(new BooksByTitle()),
      new Leaf<Book>(new BooksByTitle()));
  ABST<Book> node3 = new Node<Book>(new BooksByPrice(), this.b2, new Leaf<Book>(new BooksByPrice()),
      new Leaf<Book>(new BooksByPrice()));
  ABST<Book> node4 = new Node<Book>(new BooksByPrice(), this.b2,
      new Node<Book>(new BooksByPrice(), this.b1, new Leaf<Book>(new BooksByPrice()),
          new Leaf<Book>(new BooksByPrice())),
      new Node<Book>(new BooksByPrice(), this.b1, new Leaf<Book>(new BooksByPrice()),
          new Leaf<Book>(new BooksByPrice())));

  //Examples of Empty List of Books
  MtList<Book> mt = new MtList<Book>();

  //Examples of Books
  Book b1 = new Book("Chef and Good Eats", "My mom", 68);
  Book b2 = new Book("Fundies 2 Textbook", "A very smart professor", 8000);
  Book b3 = new Book("How to Write Good Variable Names", "Not me :(", 12);
  Book b4 = new Book("Fundies 1 Textbook", "Nate Derbinsky", 1);
  Book b5 = new Book("The Real ODD Textbook", "THE GOAT VIDO", 10);
  Book b6 = new Book("The Bad ODD Textbook", "Nat Tuck", 1);
  Book b7 = new Book("How to make handins", "Lerner", 111);

  //Examples of list of Books.
  IList<Book> lob1 = new ConsList<Book>(this.b1, this.mt);
  IList<Book> lob2 = new ConsList<Book>(this.b2, new ConsList<Book>(this.b1, this.mt));
  IList<Book> lob3 = new ConsList<Book>(this.b3,
      new ConsList<Book>(this.b2, new ConsList<Book>(this.b1, this.mt)));
  IList<Book> lob4 = new ConsList<Book>(this.b4, new ConsList<Book>(this.b3,
      new ConsList<Book>(this.b2, new ConsList<Book>(this.b1, this.mt))));
  IList<Book> lob5 = new ConsList<Book>(this.b5,
      new ConsList<Book>(this.b4, new ConsList<Book>(this.b3,
          new ConsList<Book>(this.b2, new ConsList<Book>(this.b1, this.mt)))));

  //Tests for BooksByAuthor class
  boolean testBooksByAuthor(Tester t) {
    return t.checkExpect(new BooksByAuthor().compare(b2, b1), -12)
        && t.checkExpect(new BooksByAuthor().compare(b1, b3), -1)
        && t.checkExpect(new BooksByAuthor().compare(b4, b4), 0);
  }

  //Tests for BooksByTitle class
  boolean testBooksByTitle(Tester t) {
    return t.checkExpect(new BooksByTitle().compare(b2, b1), 3)
        && t.checkExpect(new BooksByTitle().compare(b1, b3), -5)
        && t.checkExpect(new BooksByTitle().compare(b4, b4), 0);
  }

  //Tests for BooksByPrice class
  boolean testBooksByPrice(Tester t) {
    return t.checkExpect(new BooksByPrice().compare(b2, b1), 7932)
        && t.checkExpect(new BooksByPrice().compare(b1, b3), 56)
        && t.checkExpect(new BooksByPrice().compare(b4, b4), 0);
  }

  //Tests for Insert Method
  boolean testInsert(Tester t) {
    return t.checkExpect(this.aBook1.insert(b1),
        new Node<Book>(new BooksByAuthor(), this.b1, new Leaf<Book>(new BooksByAuthor()),
            new Leaf<Book>(new BooksByAuthor())))
        && t.checkExpect(this.aBook2.insert(b1),
            new Node<Book>(new BooksByTitle(), this.b1, new Leaf<Book>(new BooksByTitle()),
                new Leaf<Book>(new BooksByTitle())))
        && t.checkExpect(this.aBook3.insert(b2).insert(b1),
            new Node<Book>(
                new BooksByPrice(), this.b2, new Node<Book>(new BooksByPrice(), this.b1,
                    new Leaf<Book>(new BooksByPrice()), new Leaf<Book>(new BooksByPrice())),
                new Leaf<Book>(new BooksByPrice())));
  }

  //Tests for GetLeftMost method
  boolean testGetLeftmost(Tester t) {
    return t.checkException(new RuntimeException("No leftmost item of an empty tree"), this.aBook3,
        "getLeftmost") && t.checkExpect(node4.getLeftmost(), null)
        && t.checkExpect(this.aBook3.insert(b2).insert(b1).getLeftmost(), this.b1)
        && t.checkExpect(this.aBook3.insert(b2).insert(b3).insert(b1).getLeftmost(), this.b3);
  }

  //Tests for leftAcc method
  boolean testLeftAcc(Tester t) {
    return t.checkExpect(node4.leftAcc(this.b1), null)
        && t.checkExpect(this.aBook3.leftAcc(this.b1), this.b1)
        && t.checkExpect(this.aBook3.insert(b2).insert(b1).leftAcc(this.b2), this.b1)
        && t.checkExpect(this.aBook3.insert(b2).insert(b3).insert(b1).leftAcc(this.b6), this.b3);
  }

  //tests for GetRight method
  boolean testGetRight(Tester t) {
    return t.checkException(new RuntimeException("No right of an empty tree"), this.aBook3,
        "getRight")
        && t.checkExpect(this.aBook1.insert(b2).insert(b1).getRight(),
            new Node<Book>(new BooksByAuthor(), this.b1, new Leaf<Book>(new BooksByAuthor()),
                new Leaf<Book>(new BooksByAuthor())))
        && t.checkExpect(this.aBook1.insert(b2).insert(b3).insert(b1).getRight(),
            new Node<Book>(new BooksByAuthor(), this.b3,
                new Node<Book>(new BooksByAuthor(), this.b1, new Leaf<Book>(new BooksByAuthor()),
                    new Leaf<Book>(new BooksByAuthor())),
                new Leaf<Book>(new BooksByAuthor())))
        && t.checkExpect(this.aBook1.insert(b2).getRight(), new Leaf<Book>(new BooksByAuthor()));
  }

  //Tests for present method
  boolean testPresent(Tester t) {
    return t.checkExpect(this.aBook3.insert(b2).present(this.b2), true)
        && t.checkExpect(this.aBook3.insert(b2).insert(b1).present(this.b5), true)
        && t.checkExpect(this.aBook1.present(this.b7), true)
        && t.checkExpect(this.aBook1.present(this.b7), true)
        && t.checkExpect(this.aBook1.insert(b2).present(this.b3), true);
  }

  //Tests for SameTree method
  boolean testSameTree(Tester t) {
    return t.checkExpect(this.aBook3.insert(b2).sameTree(this.aBook3), false)
        && t.checkExpect(this.aBook3.insert(b2).insert(b1).sameTree(this.aBook2), false)
        && t.checkExpect(this.aBook1.sameTree(this.aBook3), true);
  }

  //Tests for SameData method
  boolean testSameData(Tester t) {
    return t.checkExpect(this.aBook3.sameData(this.aBook3), true)
        && t.checkExpect(this.aBook2.sameData(this.aBook2), true)
        && t.checkExpect(this.aBook1.sameData(this.aBook3.insert(b1)), false);
  }

  //Tests for SameLeaf method
  boolean testSameLeaf(Tester t) {
    return t.checkExpect(this.aBook2.sameLeaf(new Leaf<Book>(new BooksByAuthor())), true)
        && t.checkExpect(this.aBook2.insert(b1).sameLeaf(new Leaf<Book>(new BooksByTitle())), false)
        && t.checkExpect(this.aBook1.insert(b1).insert(b3).insert(b2)
            .sameLeaf(new Leaf<Book>(new BooksByTitle())), false);
  }

  //Tests for SameLeaf method
  boolean testSameNode(Tester t) {
    return t
        .checkExpect(
            this.node3.sameNode(new Node<Book>(new BooksByAuthor(), this.b2,
                new Node<Book>(new BooksByAuthor(), this.b1, new Leaf<Book>(new BooksByAuthor()),
                    new Leaf<Book>(new BooksByAuthor())),
                new Leaf<Book>(new BooksByAuthor()))),
            false)
        && t.checkExpect(this.aBook2.insert(b1)
            .sameNode(new Node<Book>(new BooksByPrice(), this.b2,
                new Leaf<Book>(new BooksByPrice()), new Leaf<Book>(new BooksByPrice()))),
            false)
        && t.checkExpect(this.aBook1.insert(b1).insert(b3).insert(b2)
            .sameNode(new Node<Book>(new BooksByPrice(), this.b2,
                new Leaf<Book>(new BooksByPrice()), new Leaf<Book>(new BooksByPrice()))),
            false)
        && t.checkExpect(this.node1.sameNode(new Node<Book>(new BooksByPrice(), this.b1,
            new Leaf<Book>(new BooksByPrice()), new Leaf<Book>(new BooksByPrice()))), false)
        && t.checkExpect(this.node1.sameNode(new Node<Book>(new BooksByPrice(), this.b1,
            new Leaf<Book>(new BooksByPrice()), new Leaf<Book>(new BooksByPrice()))), false);
  }

  //tests for buildList
  boolean testBuildList(Tester t) {
    return t.checkExpect(this.aBook1.insert(b2).insert(b1).buildList(),
        new ConsList<Book>(this.b2, new ConsList<Book>(this.b1, new MtList<Book>())))
        && t.checkExpect(this.aBook1.insert(b2).insert(b3).insert(b1).buildList(),
            new ConsList<Book>(this.b2,
                new ConsList<Book>(this.b1, new ConsList<Book>(this.b3, new MtList<Book>()))))
        && t.checkExpect(
            this.aBook1.insert(b2).insert(b3).insert(b4).insert(b5).insert(b1).buildList(),
            new ConsList<Book>(this.b2, new ConsList<Book>(this.b1,
                new ConsList<Book>(this.b4,
                    new ConsList<Book>(this.b3, new ConsList<Book>(this.b5, new MtList<Book>()))))))
        && t.checkExpect(this.aBook1.buildList(), new MtList<Book>());
  }
}
