import tester.Tester;


// to represent a geometric shape
interface IShape {
    // to compute the area of this shape
    public double area();
    
    // to compute the distance form this shape to the origin
    public double distTo0();
    
    // to increase the size of this shape by the given increment
    public IShape grow(int inc);
    
    // is the area of this shape is bigger than the area of the given shape?
    public boolean biggerThan(IShape that);
    
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
  
  //compute the area of this AShape
  public abstract double area();
  
  //computes the distance to 0 of this AShape
    public double distTo0(){
        return this.location.distTo0();
    }
    
    //grow this shape by the given increment
    public abstract IShape grow(int inc);
    
    // is the area of this shape is bigger than the area of the given shape?
    public boolean biggerThan(IShape that){
        return this.area() >= that.area();
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
     ... this.area() ...                  -- double 
     ... this.distTo0() ...               -- double
     ... this.grow(int) ...               -- IShape
     ... this.biggerThan(IShape) ...      -- boolean
     ... this.contains(CartPt) ...        -- boolean 
     ... this.sameCircle(Circle) ...      -- boolean
     Methods for fields:
     ... this.location.distTo0() ...           -- double 
     ... this.location.distTo(CartPt) ...      -- double 
     */
    
    // to compute the area of this shape
    public double area(){
        return Math.PI * this.radius * this.radius;
    }
    
    // to compute the distance form this shape to the origin
    public double distTo0(){
        return this.location.distTo0() - this.radius;
    }
    
    // to increase the size of this shape by the given increment
    public IShape grow(int inc){
        return new Circle(this.location, this.radius + inc, this.color);
    }
    
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
     ... this.area() ...                  -- double 
     ... this.distTo0() ...               -- double
     ... this.grow(int) ...               -- IShape
     ... this.biggerThan(IShape) ...      -- boolean
     ... this.contains(CartPt) ...        -- boolean 
     Methods for fields:
     ... this.location.distTo0() ...            -- double 
     ... this.location.distTo(CartPt) ...       -- double 
     */
    
    
    
    // to increase the size of this shape by the given increment
    public IShape grow(int inc){
        return new Square(this.location, this.height + inc, this.color);
    }
    
  //is this Square the same as the given Square?
    boolean sameSquare(Square s) {
      
      return this.location.sameLocation(s.location) &&
          this.height == s.height &&
          this.color.equals(s.color);
    }
    
    @Override
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
     ... this.area() ...                  -- double 
     ... this.distTo0() ...               -- double 
     ... this.grow(int inc) ...           -- IShape
     ... this.biggerThan(IShape that) ... -- boolean
     ... this.contains(CartPt pt) ...     -- boolean
     METHODS FOR FIELDS:
     ... this.location.distTo0() ...        -- double
     ... this.location.distTo(CartPt) ...   -- double
     */
    
    // to compute the area of this shape
    public double area(){
        return this.width * this.height;
    }
    
    
    // to increase the size of this shape by the given increment
    public IShape grow(int inc){
        return new Rect(this.location, this.width + inc, this.height + inc, 
                        this.color);
    }
    
  //is this Square the same as the given Square?
    boolean sameRect(Rect r) {
      
      return this.location.sameLocation(r.location) &&
          this.height == r.height &&
          this.width == r.width &&
          this.color.equals(r.color);
    }

  @Override
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

  @Override
  public double area() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public double distTo0() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public IShape grow(int inc) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean biggerThan(IShape that) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
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

  @Override
  public boolean isCircle() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean isSquare() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean isRect() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean isCombo() {
    // TODO Auto-generated method stub
    return true;
  }
  
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
    
    // test the method area in the class Circle
    boolean testCircleArea(Tester t) { 
        return
        t.checkInexact(this.c1.area(), 314.15, 0.01);
    }
    
    // test the method area in the class Square
    boolean testSquareArea(Tester t) { 
        return
        t.checkInexact(this.s1.area(), 900.0, 0.01);
    }
    
    // test the method area in the class Rect
    boolean testRectArea(Tester t) { 
        return
        t.checkInexact(this.r1.area(), 600.0, 0.01);
    }
    
    // test the method distTo0 in the class Circle
    boolean testCircleDistTo0(Tester t) { 
        return
        t.checkInexact(this.c1.distTo0(), 60.71, 0.01) &&
        t.checkInexact(this.c3.distTo0(), 74.40, 0.01);
    }
    
    // test the method distTo0 in the class Square
    boolean testSquareDistTo0(Tester t) { 
        return
        t.checkInexact(this.s1.distTo0(), 70.71, 0.01) &&
        t.checkInexact(this.s3.distTo0(), 44.72, 0.01);
    }  
    
    // test the method distTo0 in the class Rect
    boolean testRectDistTo0(Tester t) { 
        return
        t.checkInexact(this.r1.distTo0(), 70.71, 0.01) &&
        t.checkInexact(this.r3.distTo0(), 44.72, 0.01);
    }
    
    // test the method grow in the class Circle
    boolean testCircleGrow(Tester t) { 
        return
        t.checkExpect(this.c1.grow(20), this.c2);
    }
    
    // test the method grow in the class Square
    boolean testSquareGrow(Tester t) { 
        return
        t.checkExpect(this.s1.grow(20), this.s2);
    }
    
    // test the method grow in the class Rect
    boolean testRectGrow(Tester t) { 
        return
        t.checkExpect(this.r1.grow(20), this.r2);
    }
    
    // test the method biggerThan in the class Circle
    boolean testCircleBiggerThan(Tester t) { 
        return
        t.checkExpect(this.c1.biggerThan(this.c2), false) && 
        t.checkExpect(this.c2.biggerThan(this.c1), true) && 
        t.checkExpect(this.c1.biggerThan(this.s1), false) && 
        t.checkExpect(this.c1.biggerThan(this.s3), true);
    }
    
    // test the method biggerThan in the class Square
    boolean testSquareBiggerThan(Tester t) { 
        return
        t.checkExpect(this.s1.biggerThan(this.s2), false) && 
        t.checkExpect(this.s2.biggerThan(this.s1), true) && 
        t.checkExpect(this.s1.biggerThan(this.c1), true) && 
        t.checkExpect(this.s3.biggerThan(this.c1), false);
    }
    
    // test the method biggerThan in the class Rect
    boolean testRectBiggerThan(Tester t) { 
        return
        t.checkExpect(this.r1.biggerThan(this.r2), false) && 
        t.checkExpect(this.r2.biggerThan(this.r1), true) && 
        t.checkExpect(this.r1.biggerThan(this.c1), true) && 
        t.checkExpect(this.r3.biggerThan(this.s1), false);
    }
    
  
}
