import tester.Tester;

//represents a list of strings
interface ILoString {
  //reverse the order of the strings in this list
  ILoString reverse();
  //add the given string to the end of this list
  ILoString addToEnd(String s);
  
  //reverse the order of the strings in this list
  ILoString reverse2();
  
  //help for reversing this list of strings
  //accumulator: keeps track of the reversed list so far
  ILoString reverseAcc(ILoString acc);
  
  //appends a given list to the end of this list
  ILoString append(ILoString other);
}

//represents a non-empty list of strings
class ConsLoString implements ILoString {
    String first;
    ILoString rest;
    
    ConsLoString(String first, ILoString rest) {
        this.first = first;
        this.rest = rest;
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
    
    @Override
  public ILoString reverse() {
    return this.rest.reverse().addToEnd(first);
  }

  @Override
  public ILoString addToEnd(String s) {
    return new ConsLoString(this.first, this.rest.addToEnd(s));
  }


  @Override
  public ILoString reverse2() {
    return this.reverseAcc(new MtLoString());
  }


  @Override
  public ILoString reverseAcc(ILoString acc) {
    return this.rest.reverseAcc(new ConsLoString(this.first, acc));
  }


  @Override
  public ILoString append(ILoString other) {
    return new ConsLoString(this.first, this.rest.append(other));
  }
}

//represents an empty list of strings
class MtLoString implements ILoString {
    MtLoString() { }

  @Override
  public ILoString reverse() {
    return this;
  }

  @Override
  public ILoString addToEnd(String s) {
    return new ConsLoString(s, this);
  }

  @Override
  public ILoString reverse2() {
    return this;
  }

  @Override
  public ILoString reverseAcc(ILoString acc) {
    return acc;
  }

  @Override
  public ILoString append(ILoString other) {
    return other;
  }

}

class ExamplesLoS {
  ILoString mt = new MtLoString();
  ILoString list1 = new ConsLoString("dog", this.mt);
  ILoString list2 = new ConsLoString("bird", this.list1);
  ILoString list3 = new ConsLoString("cat", this.list2);
  
  //list3.reverse() -> (dog, bird, cat)

  
}
