// Dog.java
// represents a Dog in the American Kennel Club
class Dog {
  String name;
  String breed;
  int yob;
  String state;
  boolean hypoallergenic;

  // the constructor
  Dog(String name, String breed, int yob, String state, boolean hypoallergenic) {
    this.name = name;
    this.breed = breed;
    this.yob = yob;
    this.state = state;
    this.hypoallergenic = hypoallergenic;
  }
}

/*
 * TEMPLATE Fields: ... this.name ... -- String ... this.breed ... -- String ...
 * this.yob ... -- int ... this.state ... -- String ... this.hypoallergenic ...
 * -- boolean
 */
class ExamplesDog {
  ExamplesDog() {
  }

  Dog huffle = new Dog("Hufflepuff", "Wheaten Terrier", 2012, "TX", true);
  Dog pearl = new Dog("Pearl", "Labrador Retriever", 2016, "MA", false);
  Dog doggo = new Dog("Scooby Doo", "Great Dane", 1969, "CA", false);
}

