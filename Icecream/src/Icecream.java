
interface IIceCream {
}

// to represent an empty serving within the IceCream tree
class EmptyServing implements IIceCream {
  boolean cone;

  // constructor
  /*
   * Fields: ...this.cone... boolean
   */
  EmptyServing(boolean cone) {
    this.cone = cone;
  }
}

// to represent a stop on a commuter line
class Scooped implements IIceCream {
  IIceCream more;
  String flavor;

  // constructor
  /*
   * Fields: ...this.more... IceCream ...this.flavor... String
   */
  Scooped(IIceCream more, String flavor) {
    this.more = more;
    this.flavor = flavor;
  }
}

// examples and tests for the class hierarchy that represents IceCream
class ExamplesIceCream {
  ExamplesIceCream() {
  }

  IIceCream empty = new EmptyServing(false);
  IIceCream fill = new EmptyServing(true);

  IIceCream first = new Scooped(this.empty, "mint chip");
  IIceCream second = new Scooped(this.first, "coffee");
  IIceCream third = new Scooped(this.second, "black raspberry");
  IIceCream order1 = new Scooped(this.third, "caramel swirl");

  IIceCream uno = new Scooped(this.fill, "chocolate");
  IIceCream due = new Scooped(this.uno, "vanilla");
  IIceCream order2 = new Scooped(this.due, "strawberry");
}