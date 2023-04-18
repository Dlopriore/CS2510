//I applied for extra time and when i took the test it did allow me to see question during the extra time period.
//Finial Version
import tester.Tester;

//represents a list of strings
interface ILoString {
  
  //it returns a list of strings from this list in the same order, 
  //starting from the given start position and including all stings up to and including the last string
  ILoString remainingElements(int start);
  
  //the accumulator that checks the start and removes the item by the amount if the start.
  ILoString remainingElementsAcc(int start, int currentIndex);
  
  //to determine if all the elements of other are contained in this list.
  boolean allContainedIn(ILoString other);
  
  //to check if the items in the list are the same despite order.
  boolean sameItemsInContainedList(ILoString that, String other);

}

//represents an empty list of strings
class MtLoString implements ILoString {

  @Override
  public ILoString remainingElements(int start) {
    // TODO Auto-generated method stub
    return new MtLoString();
  }

  //to determine if all the elements of other are contained in this list.
  public boolean allContainedIn(ILoString other) {
    // TODO Auto-generated method stub
    return true;
  }

  //the accumulator that checks the start and removes the item by the amount if the start.
  public ILoString remainingElementsAcc(int start, int currentIndex) {
    // TODO Auto-generated method stub
    return this;
  }
  
  //to check if the items in the list are the same despite order.
  public boolean sameItemsInContainedList(ILoString that, String other) {
    return false;
  }
}

//represents a non-empty list of strings
class ConsLoString implements ILoString {
  String first;
  ILoString rest;
  
  //constructor
  ConsLoString(String first, ILoString rest) {
    this.first = first;
    this.rest = rest;
  }

  //it returns a list of strings from this list in the same order, 
  //starting from the given start position and including all stings up to and including the last string
  public ILoString remainingElements(int start) {
    // TODO Auto-generated method stub
    return this.remainingElementsAcc(start, 0);
  }
  
  //the accumulator that checks the start and removes the item by the amount if the start.
  public ILoString remainingElementsAcc(int start, int currentIndex) {
    // TODO Auto-generated method stub
    if(start < 0)
    {
      return new ConsLoString(this.first, this.rest.remainingElementsAcc(start, currentIndex));
    }
    
    if(start > currentIndex)
    {
      return new ConsLoString(this.first, this.rest.remainingElementsAcc(start, currentIndex + 1));
    }
    else
    {
      return this.rest.remainingElementsAcc(start, currentIndex + 1);
    }
  }

  //to determine if all the elements of other are contained in this list.
  public boolean allContainedIn(ILoString other) {
    // TODO Auto-generated method stub

    if(other.sameItemsInContainedList(other, this.first) || 
        this.rest.sameItemsInContainedList(other, this.first))
    {
      return this.rest.allContainedIn(other);
    }
    else
    {
      return false;
    }
  }

  //to check if the items in the list are the same despite order.
  public boolean sameItemsInContainedList(ILoString that, String other) {
    // TODO Auto-generated method stub
    return this.first.equals(other) || this.rest.sameItemsInContainedList(that, other);
  }
}

class ExamplesLists {
  ExamplesLists(){}
  
  //Examples to represent an empty list
  MtLoString mt = new MtLoString();
  
  //Examples to represent a list of strings
  ILoString los1 = new ConsLoString("you", this.mt);
  ILoString los2 = new ConsLoString("are", this.los1);
  ILoString los3 = new ConsLoString("how", this.los2);
  ILoString los4 = new ConsLoString("there", this.los3);
  ILoString los5 = new ConsLoString("Hello", this.los4);
  
  ILoString los6 = new ConsLoString("aye", this.mt);
  
  //tests for remainingElementsAcc
  boolean testRemainingElementsAcc(Tester t)
  {
    return t.checkExpect(this.mt.remainingElementsAcc(42, 0), this.mt)
    && t.checkExpect(this.los5.remainingElementsAcc(5, 0), this.los5)
    && t.checkExpect(this.los5.remainingElementsAcc(1, 0), new ConsLoString("Hello", this.mt))
    && t.checkExpect(this.los5.remainingElementsAcc(-55, 0), this.los5);
  }
  
  //tests for remaining elements
  boolean testRemainingElements(Tester t)
  {
    return t.checkExpect(this.mt.remainingElements(42), this.mt)
    && t.checkExpect(this.los5.remainingElements(56), this.los5)
    && t.checkExpect(this.los5.remainingElements(5), this.los5)
    && t.checkExpect(this.los5.remainingElements(3), 
        new ConsLoString("Hello", new ConsLoString("there", new ConsLoString("how", this.mt))))
    && t.checkExpect(this.los5.remainingElements(-55), this.los5);
  }
  
  //tests for allContainedIn
  boolean testAllContainedIn(Tester t)
  {
    return t.checkExpect(this.mt.allContainedIn(this.mt), true)
    && t.checkExpect(this.mt.allContainedIn(this.los3), true)
    && t.checkExpect(this.los2.allContainedIn(this.los2), true)
    && t.checkExpect(this.los2.allContainedIn(this.los6), false)
    && t.checkExpect(this.los1.allContainedIn(this.los6), false)
    && t.checkExpect(this.los1.allContainedIn(this.los2), true);
  }
  
  //tests for sameItemsInContainedList
  boolean testSameItemsInContainedList(Tester t)
  {
    return t.checkExpect(this.mt.sameItemsInContainedList(this.los1, " "), false)
    && t.checkExpect(this.mt.sameItemsInContainedList(this.los3, "hi"), false)
    && t.checkExpect(this.los2.sameItemsInContainedList(this.los2 , "are"), true)
    && t.checkExpect(this.los2.sameItemsInContainedList(this.los6, "you"), true)
    && t.checkExpect(this.los1.sameItemsInContainedList(this.los6, "balls"), false)
    && t.checkExpect(this.los1.sameItemsInContainedList(this.los2, "have you watched chef?"), false);
  }
  
}