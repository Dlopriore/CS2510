//represents a list of strings
interface ILoString {
  //unzips this ILoString into a PairOfLists
  PairOfLists unzip();
  //add the first of this list (if there is one) to the first of the pair of lists
  PairOfLists unzipFirst();
  //add the first of this list (if there is one) to the second of the pair of lists
  PairOfLists unzipSecond();
}

//represents a non-empty list of strings
class ConsLoString implements ILoString {
  String first;
  ILoString rest;

  ConsLoString(String first, ILoString rest) {
    this.first = first;
    this.rest = rest;
  }

  @Override
  public PairOfLists unzip() {
    return this.unzipFirst();
  }

  @Override
  public PairOfLists unzipFirst() {
    return this.rest.unzipSecond().addToFirst(this.first);
  }

  @Override
  public PairOfLists unzipSecond() {
    return this.rest.unzipFirst().addToSecond(this.first);
  }


  /* fields:
   *   this.first ... String
   *   this.rest ... ILoString
   * methods:
   *   this.rest.append(ILoString) ... ILoString
   *  ...
   * methods for fields:
   *   
   * 
   */



}

//represents an empty list of strings
class MtLoString implements ILoString {
  MtLoString() { }

  @Override
  public PairOfLists unzip() {
    return new PairOfLists(this, this);
  }

  @Override
  public PairOfLists unzipFirst() {
    return new PairOfLists(this, this);
  }

  @Override
  public PairOfLists unzipSecond() {
    return new PairOfLists(this, this);
  }



}


//represents a pair of lists of strings
class PairOfLists {
  ILoString first;
  ILoString second;
  
  PairOfLists(ILoString first, ILoString second) {
    this.first = first;
    this.second = second;
  }
  
  //Produces a new pair of lists, with the given String added to 
  //the front of the first list of this pair
  PairOfLists addToFirst(String first) {
    return new PairOfLists(new ConsLoString(first, this.first), this.second);
  }
  
  //Produces a new pair of lists, with the given String added to
  //the front of the second list of this pair
  PairOfLists addToSecond(String second) {
    return new PairOfLists(this.first, new ConsLoString(second, this.second));
  }
}

class ExamplesLoS {
  ILoString mt = new MtLoString();
  ILoString list1 = new ConsLoString("dog", this.mt);
  ILoString list2 = new ConsLoString("bird", this.list1);
  ILoString list3 = new ConsLoString("cat", this.list2);




}