import tester.Tester;

// to represent a geometric shape
interface IShape {
   
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
    
    /*
     // ** TEMPLATE ** 
     public returnType methodName() {
     ... this.center ...              -- CartPt
     ... this.radius ...              -- int
     ... this.color ...               -- String
     
     ... this.area() ...                  -- double 
     ... this.distToOrigin() ...          -- double 
     ... this.grow(int inc) ...           -- IShape
     ... this.biggerThan(IShape that) ... -- boolean
    
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
    
    /*
     // ** TEMPLATE ** 
     returnType methodName() {
     ... this.nw ...              -- CartPt
     ... this.size ...            -- int
     ... this.color ...           -- String
     
     ... this.area() ...                  -- double 
     ... this.distToOrigin() ...          -- double 
     ... this.grow(int inc) ...           -- IShape
     ... this.biggerThan(IShape that) ... -- boolean
     }
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
    public double distToOrigin(){
        return Math.sqrt(this.x * this.x + this.y * this.y);
    }
    
    // to compute the distance form this point to the given point
    public double distTo(CartPt pt){
        return Math.sqrt((this.x - pt.x) * (this.x - pt.x) + 
                         (this.y - pt.y) * (this.y - pt.y));
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
    
    IShape s1 = new Square(new CartPt(50, 50), 30, "red");
    IShape s2 = new Square(new CartPt(50, 50), 50, "red");
    IShape s3 = new Square(new CartPt(20, 40), 10, "green");
    
    // test the method distToOrigin in the class CartPt
    boolean testDistToOrigin(Tester t) { 
        return
        t.checkInexact(this.pt1.distToOrigin(), 0.0, 0.001) &&
        t.checkInexact(this.pt2.distToOrigin(), 5.0, 0.001);
    }
    
    // test the method distTo in the class CartPt
    boolean testDistTo(Tester t) { 
        return
        t.checkInexact(this.pt1.distTo(this.pt2), 5.0, 0.001) &&
        t.checkInexact(this.pt2.distTo(this.pt3), 5.0, 0.001);
    }
    
  
}
