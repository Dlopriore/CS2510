import tester.Tester;

class Person {
  String name;
  int phone;

  Person(String name, int phone) {
    this.name = name;
    this.phone = phone;
  }

  // Returns true when the given person has the same name and phone number as this person
  boolean samePerson(Person that) {
    return this.name.equals(that.name) && this.phone == that.phone;
  }

  // Returns true when the given person has the same name as a given String
  boolean sameName(String name) {
    return this.name.equals(name);
  }

  //EFFECT: changes this person's number to the given one
  void changeNum(int newNum) {
    this.phone = newNum;
  }

}

interface ILoPerson {
  // Returns true if this list contains a person with the given name
  boolean contains(String name);

  //gets the number of the person with the given name
  int findPhoneNum(String name);

  //EFFECT: the number of the person with the given name is changed to newNum
  void changeNum(String name, int newNum);

  //remove the person with the given name from this list
  void removePerson(String name);

  //helps remove a person from the list by keeping track of the thing before
  void removeHelp(String name, ConsLoPerson prev);
}

class MtLoPerson implements ILoPerson {

  // Returns true if this empty list contains a person with the given name
  public boolean contains(String name) {
    return false;
  }

  //return a -1 because the person with the given name is not found
  public int findPhoneNum(String name) {
    return -1;
  }

  @Override
  public void changeNum(String name, int newNum) {

  }

  @Override
  public void removePerson(String name) {

  }

  @Override
  public void removeHelp(String name, ConsLoPerson prev) {

  }

}

class ConsLoPerson implements ILoPerson {
  Person first;
  ILoPerson rest;

  ConsLoPerson(Person first, ILoPerson rest) {
    this.first = first;
    this.rest = rest;
  }

  // In ConsLoPerson
  // Returns true if this non-empty list contains a person with the given name
  public boolean contains(String name) {
    return this.first.sameName(name) || this.rest.contains(name);
  }

  @Override
  public int findPhoneNum(String name) {
    if (this.first.sameName(name)) {
      return this.first.phone;
    }
    else {
      return this.rest.findPhoneNum(name);
    }
  }

  @Override
  public void changeNum(String name, int newNum) {
    if (this.first.sameName(name)) {
      this.first.changeNum(newNum);
    }
    else {
      this.rest.changeNum(name, newNum);
    }
  }

  @Override
  public void removePerson(String name) {
    this.rest.removeHelp(name, this);
  }

  @Override
  public void removeHelp(String name, ConsLoPerson prev) {
    if (this.first.sameName(name)) {
      prev.rest = this.rest;
    }
    else {
      this.rest.removeHelp(name, this);
    }
  }
}

class Examples {
  Person anne = new Person("Anne", 1234);
  Person bob = new Person("Bob", 3456);
  Person clyde = new Person("Clyde", 6789);
  Person dana = new Person("Dana", 1357);
  Person eric = new Person("Eric", 12469);
  Person frank = new Person("Frank", 7294);
  Person gail = new Person("Gail", 9345);
  Person henry = new Person("Henry", 8602);
  Person irene = new Person("Irene", 91302);
  Person jenny = new Person("Jenny", 8675309);

  ILoPerson friends, family, work;

  void initData() {
    anne = new Person("Anne", 1234);
    bob = new Person("Bob", 3456);
    clyde = new Person("Clyde", 6789);
    dana = new Person("Dana", 1357);
    eric = new Person("Eric", 12469);
    frank = new Person("Frank", 7294);
    gail = new Person("Gail", 9345);
    henry = new Person("Henry", 8602);
    irene = new Person("Irene", 91302);
    jenny = new Person("Jenny", 8675309);
    this.friends = new ConsLoPerson(this.anne,
        new ConsLoPerson(this.clyde, new ConsLoPerson(this.gail,
            new ConsLoPerson(this.frank, new ConsLoPerson(this.jenny, new MtLoPerson())))));
    this.family = new ConsLoPerson(this.anne,
        new ConsLoPerson(this.dana, new ConsLoPerson(this.frank, new MtLoPerson())));
    this.work = new ConsLoPerson(this.bob,
        new ConsLoPerson(this.clyde, new ConsLoPerson(this.dana, new ConsLoPerson(this.eric,
            new ConsLoPerson(this.henry, new ConsLoPerson(this.irene, new MtLoPerson()))))));
  }

  void testRemoveLast(Tester t) {
    this.initData();
    this.friends.removePerson("Frank");
    t.checkExpect(this.friends.findPhoneNum("Frank"), -1);
    t.checkExpect(this.family.findPhoneNum("Frank"), 7294);

  }

  void testRemoveMiddle(Tester t) {
    this.initData();
    this.family.removePerson("Dana");
    t.checkExpect(this.family.findPhoneNum("Dana"), -1);
    t.checkExpect(this.work.findPhoneNum("Dana"), 1357);

  }

  void testRemoveFirst(Tester t) {
    this.initData();
    this.friends.removePerson("Anne");
    t.checkExpect(this.friends.findPhoneNum("Anne"), -1);
    t.checkExpect(this.family.findPhoneNum("Anne"), 1234);

  }

  void testChangeNum(Tester t) {
    this.initData();
    this.friends.changeNum("Frank", 4927);
    t.checkExpect(this.friends.findPhoneNum("Frank"), 4927);
    t.checkExpect(this.family.findPhoneNum("Frank"), 4927);
    t.checkExpect(this.frank.phone, 4927);

  }

  void testFindPhoneNum(Tester t) {
    this.initData();
    t.checkExpect(this.friends.contains("Frank"), true);
    t.checkExpect(this.work.contains("Zelda"), false);
    t.checkExpect(this.friends.findPhoneNum("Anne"), 1234);
    t.checkExpect(this.friends.findPhoneNum("Zelda"), -1);

  }

}