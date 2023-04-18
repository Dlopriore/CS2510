import tester.Tester;

//represents a playing card 
class Card {
  String suit;
  String value;

  Card(String v, String s) {
    this.value = v;
    this.suit = s;
  }
}

//represents a Cartesian point with x and y coordinates
class CartPt {
  int x;
  int y;

  CartPt(int x, int y) {
    this.x = x;
    this.y = y;
  }

  //produce a string representation of this Cartesian point
  public String toString() {
    return "[" + this.x + ", " + this.y + "]";
  }

  //Effect: shift the point by the x and y increments
  public void shift(int i, int j) {
    // TODO Auto-generated method stub
    this.x = i + this.x;
    this.y = j + this.y;
  }
}
