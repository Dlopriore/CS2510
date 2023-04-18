interface JSON {
  <T> T accept(JSON input);
}

//a list of JSON pairs
class JSONObject implements JSON {
  IList<Pair<String, JSON>> pairs;

  JSONObject(IList<Pair<String, JSON>> pairs) {
    this.pairs = pairs;
  }
}

//generic pairs
class Pair<X, Y> {
  X x;
  Y y;

  Pair(X x, Y y) {
    this.x = x;
    this.y = y;
  }
}

// no value
class JSONBlank implements JSON {
}

// a number
class JSONNumber implements JSON {
  int number;

  JSONNumber(int number) {
    this.number = number;
  }
}

// a boolean
class JSONBool implements JSON {
  boolean bool;

  JSONBool(boolean bool) {
    this.bool = bool;
  }
}

// a string
class JSONString implements JSON {
  String str;

  JSONString(String str) {
    this.str = str;
  }
}

class ExamplesJSON {
  ExamplesJSON() {

  }

  //list.values.map(new JSONToNumber())
  JSONBlank mt = new JSONBlank();

  JSONNumber num1 = new JSONNumber(69);
  JSONNumber num2 = new JSONNumber(420);
  JSONNumber num3 = new JSONNumber(80085);

  IList<JSONNumber> lon1 = new ConsList<JSONNumber>(this.num1, new MtList<JSONNumber>());
  IList<JSONNumber> lon2 = new ConsList<JSONNumber>(this.num2, (IList<JSONNumber>) this.num1);
  IList<JSONNumber> lon3 = new ConsList<JSONNumber>(this.num3, (IList<JSONNumber>) this.num2);

  boolean testVistorClass(Tester t) {
    return t.checkExpect(th)
  }
}
