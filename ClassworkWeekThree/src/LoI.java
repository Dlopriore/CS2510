//represents a list of ints
interface ILoInt {
  //does this list satisfy the 3 requirements (in lecture 8)?
  boolean satisfies();
  //helps determine if the list satisfies the 3 reqs
  //accumulators: keep track of whether each requirement was satisfied
  boolean satisfiesAcc(boolean even, boolean posAndOdd, boolean bet5And10);
  
  // compute the dot product of this ILoInt and a given one
  //this.list1.dotProduct(this.list2) -> (1*1) + (3*4) + (7*7) 
  int dotProduct(ILoInt other);
  
  //helper to compute the dot product
  int dotProductHelp(int theFirst, ILoInt theRest);
  
}

//represents a non-empty list of integers
class ConsLoInt implements ILoInt {
    int first;
    ILoInt rest;
    
    ConsLoInt(int first, ILoInt rest) {
        this.first = first;
        this.rest = rest;
    }

  @Override
  public boolean satisfies() {
    return this.satisfiesAcc(false, false, false);
  }

  @Override
  public boolean satisfiesAcc(boolean even, boolean posAndOdd, boolean bet5And10) {
    return  (even && posAndOdd && bet5And10 ) ||
        this.rest.satisfiesAcc(even || this.first % 2 == 0, 
                              posAndOdd || this.first > 0 && this.first % 2 == 1, 
                              bet5And10 || this.first >= 5 && this.first <= 10);
  }

  @Override
  public int dotProduct(ILoInt other) {
    return other.dotProductHelp(this.first, this.rest);
  }

  @Override
  public int dotProductHelp(int theFirst, ILoInt theRest) {
    return theFirst * this.first + this.rest.dotProduct(theRest);
  }


}

//represents an empty list of integers
class MtLoInt implements ILoInt {

  @Override
  public boolean satisfies() {
    return false;
  }

  @Override
  public boolean satisfiesAcc(boolean even, boolean posAndOdd, boolean bet5And10) {
    return even && posAndOdd && bet5And10;
  }

  @Override
  public int dotProduct(ILoInt other) {
    return 0;
  }

  @Override
  public int dotProductHelp(int theFirst, ILoInt theRest) {
    return 0;
  }
 

}

class ExamplesLoN {
  ILoInt mt = new MtLoInt();
  ILoInt list1 = new ConsLoInt(1, new ConsLoInt(3, new ConsLoInt(7, new ConsLoInt(2, this.mt))));
  ILoInt list2 = new ConsLoInt(1, new ConsLoInt(4, new ConsLoInt(7, this.mt)));
  
  //this.list1.dotProduct(this.list2) -> (1*1) + (3*4) + (7*7) 
  
  //need tests!

}