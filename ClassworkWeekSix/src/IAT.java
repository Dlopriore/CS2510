import tester.Tester;

//represents an ancestor tree
interface IAT {
  public class LoS {

  }
  //does this IAT contain a person with the given name?
  boolean containsName(String name);
  //does any Person in the tree have the same name as any other Person?
  boolean duplicateNames();
  //get the youngest IAT in a given generation
  IAT youngestAncInGen(int gen);
  //get the younger IAT between this IAT and a given one
  IAT youngerIAT(IAT other);
  //helps get the younger IAT
  IAT youngerIATHelp(int yob, IAT other);
}

//represents a leaf on an ancestor tree
class Unknown implements IAT {
  Unknown() { }

  @Override
  public boolean containsName(String name) {
    return false;
  }

  @Override
  public boolean duplicateNames() {
    return false;
  }

  @Override
  public IAT youngestAncInGen(int gen) {
    return this;
  }

  @Override
  public IAT youngerIAT(IAT other) {
    return other;
  }

  @Override
  public IAT youngerIATHelp(int yob, IAT other) {
    return other;
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
  public boolean containsName(String name) {
    return this.name.equals(name) ||
        this.parent1.containsName(name) ||
        this.parent2.containsName(name);
  }

  @Override
  public boolean duplicateNames() {
    return this.parent1.containsName(this.name) ||
        this.parent2.containsName(this.name) ||
        this.parent1.duplicateNames() ||
        this.parent2.duplicateNames();
  }

  @Override
  public IAT youngestAncInGen(int gen) {
    if (gen == 0) {
      return this;
    }
    else {
      return this.parent1.youngestAncInGen(gen - 1).youngerIAT(this.parent2.youngestAncInGen(gen - 1));
    }
  }

  @Override
  public IAT youngerIAT(IAT other) {
    return other.youngerIATHelp(this.yob, this);
  }

  @Override
  public IAT youngerIATHelp(int yob, IAT other) {
    if (this.yob > yob) {
      return this;
    }
    else {
      return other;
    }
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
  
  boolean test(Tester t) {
    return true; 
  }  
}