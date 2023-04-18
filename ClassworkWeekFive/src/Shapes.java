import tester.Tester;


// to represent a geometric shape
interface IShape {
  //is this IShape the same as the given IShape?
  boolean sameShape(IShape that);

  //is this IShape a Circle?
  boolean isCircle();
  //is this IShape a Square?
  boolean isSquare();
  //is this IShape a Rect?
  boolean isRect();
  //is this IShape a Combo?
  boolean isCombo();
}

abstract class AShape implements IShape {
  CartPt location;
  String color;

  AShape(CartPt loc, String color) {
    this.location = loc;
    this.color = color;
  }

  //is this IShape a Circle?
  public boolean isCircle() {return false;}
  //is this IShape a Square?
  public boolean isSquare() {return false;}
  //is this IShape a Rect?
  public boolean isRect() {return false;}
  //is this IShape a Combo?
  public boolean isCombo()  {return false;}
}

// to represent a circle
class Circle extends AShape {
  int radius;

  Circle(CartPt center, int radius, String color) {
    super(center, color);
    this.radius = radius;
  }

  /*  TEMPLATE 
     Fields:
     ... this.location ...             -- CartPt
     ... this.radius ...             -- int
     ... this.color ...           -- String
     Methods:
     ... this.sameCircle(Circle) ...      -- boolean
     ... this.sameShape(IShape) ...       -- boolean
     Methods for fields:
     ... this.location.distTo0() ...           -- double 
     ... this.location.distTo(CartPt) ...      -- double 
   */


  //is this Circle the same as the given Circle?
  boolean sameCircle(Circle c) {
    /* fields of c:
     *   c.location
     *   c.radius
     *   c.color
     * methods of c:
     *  ....
     */
    return this.location.sameLocation(c.location) &&
        this.radius == c.radius &&
        this.color.equals(c.color);
  }

  //is this Circle the same as the given IShape?
  public boolean sameShape(IShape that) {
    /* fields of that: none
     * methods of that: all of the methods in IShape  
     */
    if (that.isCircle()) {
      return this.sameCircle((Circle)that);
    }
    else {
      return false;
    }
  }

  //is this IShape a Circle?
  public boolean isCircle() {return true;}

}

// to represent a square
class Square extends Rect {

  Square(CartPt nw, int size, String color) {
    super(nw, size, size, color);
  }

  /*  TEMPLATE 
     Fields:
     ... this.location ...              -- CartPt
     ... this.height ...            -- int
     ... this.width ...            -- int
     ... this.color ...           -- String
     Methods:
     ... this.sameSquare(Square) ...      -- boolean
     ... this.sameShape(IShape) ...       -- boolean
     Methods for fields:
     ... this.location.distTo0() ...            -- double 
     ... this.location.distTo(CartPt) ...       -- double 
   */

  //is this Square the same as the given Square?
  boolean sameSquare(Square s) {

    return this.location.sameLocation(s.location) &&
        this.height == s.height &&
        this.color.equals(s.color);
  }

  //is this Square the same as the given shape?
  public boolean sameShape(IShape that) {
    if (that.isSquare() ) {
      return this.sameSquare((Square)that);
    }
    else {
      return false;
    }
  }

  //is this IShape a Square?
  public boolean isSquare() {return true;}

  //is this IShape a Rect?
  public boolean isRect() {return false;}
}

// to represent a rectangle
class Rect extends AShape {
  int width;
  int height;

  Rect(CartPt nw, int width, int height, String color) {
    super(nw, color);
    this.width = width;
    this.height = height;
  }

  /* TEMPLATE
     FIELDS
     ... this.location ...              -- CartPt
     ... this.width ...           -- int
     ... this.height ...          -- int
     ... this.color ...           -- String
     METHODS
     ... this.sameRect(Rect) ...      -- boolean
     ... this.sameShape(IShape) ...       -- boolean
     METHODS FOR FIELDS:
     ... this.location.distTo0() ...        -- double
     ... this.location.distTo(CartPt) ...   -- double
   */

  //is this Square the same as the given Square?
  boolean sameRect(Rect r) {  
    return this.location.sameLocation(r.location) &&
        this.height == r.height &&
        this.width == r.width &&
        this.color.equals(r.color);
  }

  //is this Rect the same as the given shape?
  public boolean sameShape(IShape that) {
    if (that.isRect()) {
      return this.sameRect((Rect)that);
    }
    else {
      return false;
    }
  }

  //is this IShape a Rect?
  public boolean isRect() {return true;}
}

class Combo implements IShape {
  IShape top;
  IShape bottom;

  Combo(IShape top, IShape bottom) {
    this.top = top;
    this.bottom = bottom;
  }

  //is this Combo the same as the given IShape?
  public boolean sameShape(IShape that) {
    if (that instanceof Combo) {
      return this.sameCombo((Combo)that);
    }
    else {
      return false;
    }
  }

  //is this Combo the same as the given Combo?
  boolean sameCombo(Combo c) {
    return this.top.sameShape(c.top) &&
        this.bottom.sameShape(c.bottom);
  }

  //is this Combo a Circle?
  public boolean isCircle() {return false;}

  //is this Combo a Square?
  public boolean isSquare() {return false;}

  //is this Combo a Rect?
  public boolean isRect() {return false;}

  //is this Combo a Combo?
  public boolean isCombo() {return true;}

}

// to represent a Cartesian point
class CartPt {
  int x;
  int y;

  CartPt(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /* TEMPLATE
     FIELDS
     ... this.x ...          -- int
     ... this.y ...          -- int
     METHODS
     ... this.distTo0() ...        -- double
     ... this.distTo(CartPt) ...   -- double
   */

  // to compute the distance form this point to the origin
  public double distTo0(){
    return Math.sqrt(this.x * this.x + this.y * this.y);
  }

  // to compute the distance form this point to the given point
  public double distTo(CartPt pt){
    return Math.sqrt((this.x - pt.x) * (this.x - pt.x) + 
        (this.y - pt.y) * (this.y - pt.y));
  }

  //is this CartPt the same as the given one?
  boolean sameLocation(CartPt other) {
    /* other.x
     * other.y
     * other.distTo0
     * ...
     */
    return this.x == other.x &&
        this.y == other.y;
  }
}

class ExamplesShapes {
  ExamplesShapes() {}

  CartPt pt1 = new CartPt(0, 0);
  CartPt pt2 = new CartPt(3, 4);
  CartPt pt3 = new CartPt(7, 1);

  IShape c1 = new Circle(new CartPt(50, 50), 10, "red");
  IShape c2 = new Circle(new CartPt(50, 50), 30, "red");
  IShape c3 = new Circle(new CartPt(30, 100), 30, "blue");
  IShape c5 = new Circle(new CartPt(50, 50), 10, "red");
  IShape c6 = new Circle(new CartPt(50, 50), 10, "red");

  IShape s1 = new Square(new CartPt(50, 50), 30, "red");
  IShape s2 = new Square(new CartPt(50, 50), 50, "red");
  IShape s3 = new Square(new CartPt(20, 40), 10, "green");

  IShape r1 = new Rect(new CartPt(50, 50), 30, 20, "red");
  IShape r2 = new Rect(new CartPt(50, 50), 50, 40, "red");
  IShape r3 = new Rect(new CartPt(20, 40), 10, 20, "green");
  IShape r4 = new Rect(new CartPt(50, 50), 30, 30, "red");

  AShape c4 = new Circle(pt1, 5, "red");

  // c1.sameCircle(c1) -> true
  // c1.sameCircle(c2) -> false
  // c2.sameCircle(c1) -> false
  // c1.sameCircle(c5) --> true
  // c5.sameCircle(c6) --> true
  // c1.sameCircle(c6) --> true

  //tests for sameness
  boolean testSame(Tester t) {
    return t.checkExpect(this.c1.sameShape(c5), true) &&
        t.checkExpect(this.c1.sameShape(c2), false) &&
        t.checkExpect(this.c1.sameShape(r1), false) &&
        t.checkExpect(this.r4.sameShape(s1), false) &&
        t.checkExpect(this.s1.sameShape(r4), false);
  }

  // test the method distTo0 in the class CartPt
  boolean testDistTo0(Tester t) { 
    return
        t.checkInexact(this.pt1.distTo0(), 0.0, 0.001) &&
        t.checkInexact(this.pt2.distTo0(), 5.0, 0.001);
  }

  // test the method distTo in the class CartPt
  boolean testDistTo(Tester t) { 
    return
        t.checkInexact(this.pt1.distTo(this.pt2), 5.0, 0.001) &&
        t.checkInexact(this.pt2.distTo(this.pt3), 5.0, 0.001);
  }


}

