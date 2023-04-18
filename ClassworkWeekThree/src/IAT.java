import tester.Tester;

//represents an ancestor tree
interface IAT {
  //list the names of the persons in this IAT
  ILoString names();
  
  //list the names of the persons in this IAT
  ILoString names2();
  
  //helps list the names of the persons in this IAT
  //accumulator: keeps track of the names in parent2's side of the tree
  ILoString namesAcc(ILoString acc);
  
  //is this IAT well-formed? (is every child younger than their parents?
  boolean wellFormed();
  
  //compares this IAT's age to the given year of birth
  boolean isOlderThan(int childsYob);
  
  //get the IAT that is younger between this and the given one
  IAT youngerIAT(IAT other);
  
  //get the younger parent of this IAT
  IAT youngerParent();
  
  //get the youngest grandparent of this IAT
  IAT youngestGrand();
  
  //youngest IAT in a given generation
  IAT youngestInGen(int gen);
}

//represents a leaf on an ancestor tree
class Unknown implements IAT {
  Unknown() { }

  //list the names of the persons in this IAT
  public ILoString names() {
    return new MtLoString();
  }

  @Override
  public ILoString names2() {
    return new MtLoString();
  }

  @Override
  public ILoString namesAcc(ILoString acc) {
    return acc;
  }

  @Override
  public boolean wellFormed() {
    return true;
  }

  @Override
  public boolean isOlderThan(int childsYob) {
    return true;
  }

  @Override
  public IAT youngerIAT(IAT other) {
    return other;
  }

  @Override
  public IAT youngerParent() {
    return this;
  }

  @Override
  public IAT youngestGrand() {
    return this;
  }

  @Override
  public IAT youngestInGen(int gen) {
    return this;
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
  
  /* fields: 
   *   this.name
   *   this.yob
   *   this.parent1
   *   this.parent2
   * methods:
   *   this.names()
   *   this.names2()
   *   this.namesAcc(ILoString)
   *   this.wellFormed()
   *   this.youngerIAT(IAT)
   * methods for fields:
   *   this.parent1.names()
   *   this.parent1.names2()
   *   this.parent1.namesAcc(ILoString)
   *   this.parent1.wellFormed()  
   *   this.parent2.names()
   *   this.parent2.names2()
   *   this.parent2.namesAcc(ILoString)
   *   this.parent2.wellFormed() 
   *   this.parent1.youngerIAT(IAT)
   *   this.parent2.youngerIAT(IAT)
   */

  @Override
  public ILoString names() {
    return new ConsLoString(this.name, this.parent1.names().append(this.parent2.names()));
  }

  @Override
  public ILoString names2() {
    return this.namesAcc(new MtLoString());
  }

  @Override
  public ILoString namesAcc(ILoString acc) {
    return new ConsLoString(this.name, this.parent1.namesAcc(this.parent2.namesAcc(acc)));
  }

  @Override
  public boolean wellFormed() {
    return this.parent1.isOlderThan(this.yob) &&
        this.parent2.isOlderThan(this.yob) &&
        this.parent1.wellFormed() &&
        this.parent2.wellFormed();
  }

  @Override
  public boolean isOlderThan(int childsYob) {
    return this.yob < childsYob;
  }

  @Override
  public IAT youngerIAT(IAT other) {
    /* fields of other: none
     * methods: 
     *   other.names()
     *   other.names2()
     *   other.wellFormed()
     *   other.isOlderThan(int)
     *   other.youngerIAT(IAT)
     */
    if (other.isOlderThan(this.yob)) {
      return this;
    }
    else {
      return other;
    }
  }

  @Override
  public IAT youngerParent() {
    return this.parent1.youngerIAT(this.parent2);
  }

  @Override
  public IAT youngestGrand() {
    return this.parent1.youngerParent().youngerIAT(this.parent2.youngerParent());
  }

  @Override
  public IAT youngestInGen(int gen) {
    if (gen == 0) {
      return this;
    }
    else {
      return this.parent1.youngestInGen(gen - 1).youngerIAT(this.parent2.youngestInGen(gen - 1)); 
    }
  }
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
  
  // andrew.wellFormed() --> true
  // add some examples of trees that are not well formed
  // unknown.wellFormed() --> true
  
  boolean testNames(Tester t) {
    return t.checkExpect(this.andrew.names2(), true);
  }
}