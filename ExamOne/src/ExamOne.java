//I applied for extra time and when i took the test it did allow me to see question during the extra time period.
//Final Version
import tester.Tester;
//represents an ancestor tree
interface IAT {
  
  //to compute the generation of the IAT that has the same name
  int numGensAway(String name, int count);
  //to compute the greatest secret number between the current and the given IAT
  public int greaterSecretNumber(int otherSecretNumber);
  //to compute the greatest secret number
  int largestSecretNumber();
  //to compute the greatest secret number with the first generation of IATs
  int largestGenerationSecretNumber();
  //to compute the greatest secret number with the second generation of IATs
  int largestSecondGenerationSecretNumber();

  
  //does this IAT contain a person with the given name?
  boolean containsName(String name);
}
//represents a leaf on an ancestor tree
class Unknown implements IAT {

  //to compute the generation of the IAT that has the same name
  public int numGensAway(String name, int count) {
    // TODO Auto-generated method stub
    return -1;
  }

  //to compute the greatest secret number
  public int largestSecretNumber() {
    // TODO Auto-generated method stub
    throw new IllegalStateException("No secret number since there are no numbers to compare between the IATS");
  }

  //to compute the greatest secret number between the current and the given IAT
  public int greaterSecretNumber(int otherSecretNumber) {
    // TODO Auto-generated method stub
    return otherSecretNumber;
  }
  
  //does this IAT contain a person with the given name?
  public boolean containsName(String name) {
    // TODO Auto-generated method stub
    return false;
  }

  //to compute the greatest secret number with the first generation of IATs
  public int largestGenerationSecretNumber() {
    // TODO Auto-generated method stub
    return 0;
  }

  //to compute the greatest secret number with the second generation of IATs
  public int largestSecondGenerationSecretNumber() {
    // TODO Auto-generated method stub
    return 0;
  } 
}
//represents a person in an ancestor tree
class Person implements IAT { 
  String name;
  int secretNumber;
  IAT dad; 
  IAT mom;
  
  Person(String name, int secretNumber, IAT dad, IAT mom) {
this.name = name; 
this.secretNumber = secretNumber;
this.dad = dad; 
this.mom = mom;
}

//to compute the greatest secret number
public int largestSecretNumber() {
  // TODO Auto-generated method stub
  return Math.max(this.mom.largestSecondGenerationSecretNumber(), 
      this.dad.largestSecondGenerationSecretNumber());
} 

//to compute the greatest secret number with the second generation of IATs
public int largestSecondGenerationSecretNumber() {
  // TODO Auto-generated method stub
  return Math.max(this.mom.largestGenerationSecretNumber(), 
      this.dad.largestGenerationSecretNumber());
}

//to compute the greatest secret number with the first generation of IATs
public int largestGenerationSecretNumber() {
  // TODO Auto-generated method stub
  return Math.max(this.mom.greaterSecretNumber(this.secretNumber), 
      this.dad.greaterSecretNumber(this.secretNumber));
}

//to compute the greatest secret number between the current and the given IAT
public int greaterSecretNumber(int otherSecretNumber) 
{
  if (this.secretNumber > otherSecretNumber) 
  {
    return this.secretNumber;
  }
  else {
    return otherSecretNumber;
  }
}

//to compute the generation of the IAT that has the same name
public int numGensAway(String name, int count) {
  if(this.containsName(name))
  {
    return Math.max(this.dad.numGensAway(name, count+1), this.mom.numGensAway(name, count+1));
  }
  else
  {
    return count;
  }
}

//does this IAT contain a person with the given name?
public boolean containsName(String name) {
  return this.name.equals(name) ||
      this.mom.containsName(name) ||
      this.dad.containsName(name);
}

}

//tests and examples for IAT.java
class ExamplesIAT {
  ExamplesIAT(){}
  Unknown mt = new Unknown();
  
  IAT davisSr = new Person("DavisSr", -23, new Unknown(), new Unknown());
  IAT edna = new Person("Edna", 67, new Unknown(), new Unknown());
  IAT davisJr = new Person("DavisJr", 23, davisSr, edna);
  IAT carl = new Person("Carl", 0, new Unknown(), new Unknown());
  IAT candace = new Person("Candace",  2, davisJr, new Unknown());
  IAT claire = new Person("Claire", 99, new Unknown(), new Unknown()); 
  IAT bill = new Person("Bill", 0, carl, candace);
  IAT bree = new Person("Bree", 10, new Unknown(), claire);
  IAT anthony = new Person("Anthony", -12, bill, bree);

//tests testLargestSecretNumber
boolean testLargestSecretNumber(Tester t)
{
  return t.checkExpect(this.carl.largestSecretNumber(), 0)
      && t.checkExpect(this.candace.largestSecretNumber(), 67)
      && t.checkExpect(this.anthony.largestSecretNumber(), 99)
      && t.checkExpect(this.bill.largestSecretNumber(), 67);
  // checkexpect for empty 
  // this.mt.largestSecretNumber() -> 
  // new IllegalStateException("No secret number since there are no numbers to compare between the IATS");
}

//tests testlargestSecondGenerationSecretNumber
boolean testlargestSecondGenerationSecretNumber(Tester t)
{
return t.checkExpect(this.carl.largestSecondGenerationSecretNumber(), 0)
    && t.checkExpect(this.candace.largestSecondGenerationSecretNumber(), 67)
    && t.checkExpect(this.anthony.largestSecondGenerationSecretNumber(), 99)
    && t.checkExpect(this.bill.largestSecondGenerationSecretNumber(), 23)
    && t.checkExpect(this.mt.largestSecondGenerationSecretNumber(), 0);
}

//tests testlargestGenerationSecretNumber
boolean testlargestGenerationSecretNumber(Tester t)
{
return t.checkExpect(this.carl.largestGenerationSecretNumber(), 0)
  && t.checkExpect(this.candace.largestGenerationSecretNumber(), 23)
  && t.checkExpect(this.anthony.largestGenerationSecretNumber(), 10)
  && t.checkExpect(this.bill.largestGenerationSecretNumber(), 2)
  && t.checkExpect(this.mt.largestGenerationSecretNumber(), 0);
}

//tests testNumGensAway
boolean testNumGensAway(Tester t)
{
  return t.checkExpect(this.carl.numGensAway("bill", -1), -1)
      && t.checkExpect(this.candace.numGensAway("DavisJr", 0), 2)
      && t.checkExpect(this.anthony.numGensAway("Carl", 0), 2)
      && t.checkExpect(this.edna.numGensAway("Enda", 0), 0)
      && t.checkExpect(this.mt.numGensAway("Enda", 0), -1);
}

//tests containsName
boolean testContainsName(Tester t)
{
return t.checkExpect(this.carl.containsName("Carl"), true)
    && t.checkExpect(this.candace.containsName("DavisJr"), true)
    && t.checkExpect(this.anthony.containsName("Carl"), true)
    && t.checkExpect(this.edna.containsName("paul"), false)
    && t.checkExpect(this.mt.containsName("Enda"), false);
}

//tests greaterSecretNumber
boolean testGreaterSecretNumber(Tester t)
{
return t.checkExpect(this.carl.greaterSecretNumber(69), 69)
  && t.checkExpect(this.candace.greaterSecretNumber(33), 33)
  && t.checkExpect(this.anthony.greaterSecretNumber(1), 1)
  && t.checkExpect(this.edna.greaterSecretNumber(99), 99)
  && t.checkExpect(this.mt.greaterSecretNumber(0), 0);
}


}
