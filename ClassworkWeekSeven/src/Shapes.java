import tester.Tester;

// to represent a geometric shape
interface IShape {
  <R> R accept(IShapeVisitor<R> sd);
}

// to represent a circle
class Circle implements IShape {
  CartPt center;
  int radius;
  String color;

  Circle(CartPt center, int radius, String color) {
    this.center = center;
    this.radius = radius;
    this.color = color;
  }

  @Override
  public <R> R accept(IShapeVisitor<R> sd) {
    return sd.visitCircle(this);
  }

  /*
   * // ** TEMPLATE ** public returnType methodName() { ... this.center ... --
   * CartPt ... this.radius ... -- int ... this.color ... -- String
   * 
   * 
   */

}

// to represent a square
class Square implements IShape {
  CartPt nw;
  int size;
  String color;

  Square(CartPt nw, int size, String color) {
    this.nw = nw;
    this.size = size;
    this.color = color;
  }

  @Override
  public <R> R accept(IShapeVisitor<R> sd) {
    return sd.visitSquare(this);
  }

  /*
   * // ** TEMPLATE ** returnType methodName() { ... this.nw ... -- CartPt ...
   * this.size ... -- int ... this.color ... -- String
   * 
   * 
   * }
   */

}

//class to represent a combo shape
class Combo implements IShape {
  IShape top;
  IShape bottom;

  Combo(IShape top, IShape bottom) {
    this.top = top;
    this.bottom = bottom;
  }

  @Override
  public <R> R accept(IShapeVisitor<R> sd) {
    return sd.visitCombo(this);
  }

  /*
   * fields: this.top ... IShape this.bottom ... IShape methods:
   *
   * methods for fields:
   * 
   * 
   */

}

// to represent a Cartesian point
class CartPt {
  int x;
  int y;

  CartPt(int x, int y) {
    this.x = x;
    this.y = y;
  }

  // to compute the distance form this point to the origin
  public double distToOrigin() {
    return Math.sqrt(this.x * this.x + this.y * this.y);
  }

  // to compute the distance form this point to the given point
  public double distTo(CartPt pt) {
    return Math.sqrt((this.x - pt.x) * (this.x - pt.x) + (this.y - pt.y) * (this.y - pt.y));
  }
}

class ExamplesShapes {
  ExamplesShapes() {
  }

  CartPt pt1 = new CartPt(0, 0);
  CartPt pt2 = new CartPt(3, 4);
  CartPt pt3 = new CartPt(7, 1);

  IShape c1 = new Circle(new CartPt(50, 50), 10, "red");
  IShape c2 = new Circle(new CartPt(50, 50), 30, "red");
  IShape c3 = new Circle(new CartPt(30, 100), 30, "blue");

  IShape s1 = new Square(new CartPt(50, 50), 30, "red");
  IShape s2 = new Square(new CartPt(50, 50), 50, "red");
  IShape s3 = new Square(new CartPt(20, 40), 10, "green");

  IShape combo1 = new Combo(this.c1, this.s1);
  IShape combo2 = new Combo(this.s2, this.combo1);

  IList<IShape> shapes = new ConsList<IShape>(this.c1,
      new ConsList<IShape>(s1, new MtList<IShape>()));

  boolean testShapeArea(Tester t) {
    return t.checkExpect(this.shapes.map(new ShapeToArea()),
        new ConsList<Double>(Math.PI * 100, new ConsList<Double>(900.0, new MtList<Double>())));
  }
}
