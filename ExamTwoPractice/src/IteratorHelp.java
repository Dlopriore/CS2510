import java.util.ArrayList;
import java.util.Iterator;

//ITERATOR CLASS FOR ARRAYLIST OF ARRAYLIST
class ListOfLists<T> implements Iterable<T> {
  ArrayList<ArrayList<T>> arr;

  ListOfLists(ArrayList<ArrayList<T>> arr) {
    this.arr = arr;
  }

  public Iterator<T> iterator() {
    // TODO Auto-generated method stub
    return new ListOfListsIterator<T>(this);
  }

  public int size() {
    // TODO Auto-generated method stub
    return this.arr.size();
  }

  public ArrayList<T> get(int index) {
    // TODO Auto-generated method stub
    return this.arr.get(index);
  }
}

class ListOfListsIterator<T> implements Iterator<T> {
  ListOfLists<T> list;
  int index;
  int index2;

  ListOfListsIterator(ListOfLists<T> list) {
    this.list = list;
    this.index = 0;
    this.index2 = 0;
  }

  public boolean hasNext() {
    // TODO Auto-generated method stub
    return index < this.list.size();
  }

  @Override
  public T next() {
    // TODO Auto-generated method stub
    if (this.index2 < this.list.get(index).size() - 1) {
      index2++;
    }
    else {
      index++;
      index2 = 0;
    }
    return this.list.get(index).get(index2);
  }
}

//ITERATOR CLASS FOR DEQUE
class Deque<T> implements Iterable<T> {
  Sentinel<T> header;

  @Override
  public Iterator<T> iterator() {
    // TODO Auto-generated method stub
    return new DequeIterator<T>(this);
  }
}

class ANode<T> {
  ANode<T> next;
  ANode<T> prev;
}

class Sentinel<T> extends ANode<T> {
}

class Node<T> extends ANode<T> {
  T data;
}

class DequeIterator<T> implements Iterator<T> {
  Deque<T> dq;
  ANode<T> currentNode;

  DequeIterator(Deque<T> dq) {
    this.dq = dq;
    this.currentNode = this.dq.header;
  }

  @Override
  public boolean hasNext() {
    // TODO Auto-generated method stub
    return this.currentNode != this.dq.header;
  }

  @Override
  public T next() {
    // TODO Auto-generated method stub
    this.currentNode = this.currentNode.next;
    Node<T> actualNode = (Node<T>) this.currentNode;
    return actualNode.data;
  }
}

//ITERATOR FOR AN ARRAYLIST
class ArrayListIterator<T> implements Iterator<T> {
  ArrayList<T> arr;
  int currentIndex;

  ArrayListIterator(ArrayList<T> arr) {
    this.arr = arr;
    this.currentIndex = -1;
  }

  public boolean hasNext() {
    // TODO Auto-generated method stub
    return this.currentIndex + 1 <= arr.size();
  }

  @Override
  public T next() {
    this.currentIndex = this.currentIndex + 1;
    return this.arr.get(currentIndex);
  }

}
