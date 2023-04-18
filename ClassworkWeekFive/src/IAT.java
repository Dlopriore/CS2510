
import tester.Tester;

//represents an ancestor tree
interface IAT {
  //is this IAT the same as the given one?
  boolean sameIAT(IAT that);
  //is this IAT the same as the given Unknown?
  boolean sameUnknown(Unknown other);
  //is this IAT the same as the given Person?
  boolean samePerson(Person p);
  
}

//represents a leaf on an ancestor tree
class Unknown implements IAT {
  Unknown() { }

  //is this Unknown the same as the given IAT?
  public boolean sameIAT(IAT that) {
    return that.sameUnknown(this);
  }

  //is this Unknown the same as the given Unknown
  public boolean sameUnknown(Unknown other) {
    return true;
  }

  //is this Unknown the same as the given person?
  public boolean samePerson(Person p) {
    return false;
  }
  
}

//represents a node on an ancestor tree
class Person implements IAT {
  String name;
  int yob;
  IAT parent1;
  IAT parent2;

  Person(String name, int yob, IAT parent1, IAT parent2) {
    this.name = name;
    this.yob = yob;
    this.parent1 = parent1;
    this.parent2 = parent2;
  }

  @Override
  public boolean sameIAT(IAT that) {
    return that.samePerson(this);
  }

  //is this Person the same as the given Unknown?
  public boolean sameUnknown(Unknown other) {
    return false;
  }

  @Override
  public boolean samePerson(Person p) {
    return this.name.equals(p.name) &&
        this.yob == p.yob &&
        this.parent1.sameIAT(p.parent1) &&
        this.parent2.sameIAT(p.parent2);
  }
  
  /* fields: 
   *   this.name
   *   this.yob
   *   this.parent1 ... IAT
   *   this.parent2 ... IAT
   * methods:
   
   * methods for fields:
   *   
   */

}

class ExamplesIAT {
  IAT enid = new Person("Enid", 1904, new Unknown(), new Unknown());
  IAT edward = new Person("Edward", 1902, new Unknown(), new Unknown());
  IAT emma = new Person("Emma", 1906, new Unknown(), new Unknown());
  IAT eustace = new Person("Eustace", 1907, new Unknown(), new Unknown());

  IAT david = new Person("David", 1925, new Unknown(), this.edward);
  IAT daisy = new Person("Daisy", 1927, new Unknown(), new Unknown());
  IAT dana = new Person("Dana", 1933, new Unknown(), new Unknown());
  IAT darcy = new Person("Darcy", 1930, this.emma, this.eustace);
  IAT darren = new Person("Darren", 1935, this.enid, new Unknown());
  IAT dixon = new Person("Dixon", 1936, new Unknown(), new Unknown());

  IAT clyde = new Person("Clyde", 1955, this.daisy, this.david);
  IAT candace = new Person("Candace", 1960, this.dana, this.darren);
  IAT cameron = new Person("Cameron", 1959, new Unknown(), this.dixon);
  IAT claire = new Person("Claire", 1956, this.darcy, new Unknown());

  IAT bill = new Person("Bill", 1980, this.candace, this.clyde);
  IAT bree = new Person("Bree", 1981, this.claire, this.cameron);

  IAT andrew = new Person("Andrew", 2001, this.bree, this.bill);
  
  boolean testSameIAT(Tester t) {
    return t.checkExpect(this.david.sameIAT(this.darcy), false) &&
        t.checkExpect(this.andrew.sameIAT(andrew), true); 
  }
  
  
}