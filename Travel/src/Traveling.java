// Travel.java
// Dante LoPriore

//to represent Housing
interface IHousing {
}

// to represent a Hut as a type of housing
class Hut implements IHousing {
  int capacity;
  int population;

  /*
   * Fields: ...this.capacity... int ...this.population... int
   */
  // constructor
  public Hut(int capacity, int population) {
    this.capacity = capacity;
    this.population = population;
  }
}

//to represent a Inn as a type of housing
class Inn implements IHousing {
  String name;
  int capacity;
  int population;
  int stalls;

  /*
   * Fields: ...this.name... String ...this.capacity... int ...this.population...
   * int ...this.stalls... int
   */
  // constructor
  Inn(String name, int capacity, int population, int stalls) {
    this.name = name;
    this.capacity = capacity;
    this.population = population;
    this.stalls = stalls;
  }

}

//to represent a Castle as a type of housing
class Castle implements IHousing {
  String name;
  String familyName;
  int population;
  int carriageHouse;

  // constructor
  /*
   * Fields: ...this.name... String ...this.familyName... String
   * ...this.population... int ...this.carriageHouse... int
   */
  Castle(String name, String familyName, int population, int carriageHouse) {
    this.name = name;
    this.familyName = familyName;
    this.population = population;
    this.carriageHouse = carriageHouse;
  }
}

//to represent Transportation
interface ITransportation {
}

//to represent a Horse as a type of transportation
class Horse implements ITransportation {
  IHousing from;
  IHousing to;
  String name;
  String color;

  /*
   * Fields: ...this.from... IHousing ...this.to... IHousing ...this.name...
   * String ...this.color... String
   */
  // constructor
  Horse(IHousing from, IHousing to, String name, String color) {
    this.from = from;
    this.to = to;
    this.name = name;
    this.color = color;
  }
}

//to represent a Carriage as a type of transportation
class Carriage implements ITransportation {
  IHousing from;
  IHousing to;
  int tonnage;

  /*
   * Fields: ...this.from... IHousing ...this.to... IHousing ...this.tonnage...
   * int
   */
  // constructor
  Carriage(IHousing from, IHousing to, int tonnage) {
    this.from = from;
    this.to = to;
    this.tonnage = tonnage;
  }
}

// examples and tests for the class hierarchy that represents IHousing and ITransportation
// within Travel.java
class ExamplesTravel {
  ExamplesTravel() {
  }

  IHousing hovel = new Hut(5, 1);
  IHousing winterfell = new Castle("Winterfell", "Stark", 500, 6);
  IHousing crossroads = new Inn("Inn At The Crossroads", 40, 20, 12);

  ITransportation horse1 = new Horse(this.winterfell, this.crossroads, "Bronco", "Brown");
  ITransportation horse2 = new Horse(this.crossroads, this.winterfell, "Hass", "Black");
  ITransportation carriage1 = new Carriage(this.winterfell, this.crossroads, 420);
  ITransportation carriage2 = new Carriage(this.crossroads, this.winterfell, 68);
}