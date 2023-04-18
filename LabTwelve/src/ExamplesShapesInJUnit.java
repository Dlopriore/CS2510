import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import tester.Tester;

public class ExamplesShapesInJUnit {
  public ExamplesShapesInJUnit() {
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

  @Test
  // test the method distToOrigin in the class CartPt
  void testDistToOrigin(Tester t) {
    assertEquals(0.0, this.pt1.distToOrigin(), 0.001);
    assertEquals(5.0, this.pt2.distToOrigin(), 0.001);
  }

  // test the method distTo in the class CartPt
  boolean testDistTo(Tester t) {
    return t.checkInexact(this.pt1.distTo(this.pt2), 5.0, 0.001)
        && t.checkInexact(this.pt2.distTo(this.pt3), 5.0, 0.001);
  }

  // test the method area in the class Circle
  boolean testCircleArea(Tester t) {
    return t.checkInexact(this.c1.area(), 314.15, 0.01);
  }

  // test the method grow in the class Circle
  boolean testSquareArea(Tester t) {
    return t.checkInexact(this.s1.area(), 900.0, 0.01);
  }

  // test the method distToOrigin in the class Circle
  boolean testCircleDistToOrigin(Tester t) {
    return t.checkInexact(this.c1.distToOrigin(), 60.71, 0.01)
        && t.checkInexact(this.c3.distToOrigin(), 74.40, 0.01);
  }

  // test the method distTo in the class Circle
  boolean testSquareDistToOrigin(Tester t) {
    return t.checkInexact(this.s1.distToOrigin(), 70.71, 0.01)
        && t.checkInexact(this.s3.distToOrigin(), 44.72, 0.01);
  }

  @Test
  // test the method grow in the class Circle
  void testCircleGrow(Tester t) {
    assertEquals(this.c2, this.c1.grow(20));
  }

  // test the method grow in the class Circle
  boolean testSquareGrow(Tester t) {
    return t.checkExpect(this.s1.grow(20), this.s2);
  }

  // test the method biggerThan in the class Circle
  boolean testCircleBiggerThan(Tester t) {
    return t.checkExpect(this.c1.biggerThan(this.c2), false)
        && t.checkExpect(this.c2.biggerThan(this.c1), true)
        && t.checkExpect(this.c1.biggerThan(this.s1), false)
        && t.checkExpect(this.c1.biggerThan(this.s3), true);
  }

  // test the method biggerThan in the class Square
  boolean testSquareBiggerThan(Tester t) {
    return t.checkExpect(this.s1.biggerThan(this.s2), false)
        && t.checkExpect(this.s2.biggerThan(this.s1), true)
        && t.checkExpect(this.s1.biggerThan(this.c1), true)
        && t.checkExpect(this.s3.biggerThan(this.c1), false);
  }

  @Test
  // test the method contains in the class Circle
  public void testCircleContains(Tester t) {
    assertEquals(false, this.c1.contains(new CartPt(100, 100)));
    assertEquals(true, this.c2.contains(new CartPt(40, 60)));
    assertFalse(false, this.c1.contains(new CartPt(50, 50)));
    assertTrue(true, this.c2.contains(new CartPt(10, 60)));
  }

  // test the method contains in the class Square
  boolean testSquareContains(Tester t) {
    return t.checkExpect(this.s1.contains(new CartPt(100, 100)), false)
        && t.checkExpect(this.s2.contains(new CartPt(55, 60)), true);
  }

  @Test
  public void testCircleException1() throws IllegalAccessException {
    IShape c = new Circle(new CartPt(30, 30), -10, "blue");
    assertEquals(new IllegalAccessException("Radius is negative"), c);
  }

  @Test
  public void testCircleException2() {
    IShape c;

    try {
      c = new Circle(30, 30, -10, "blue");
      fail("This test did not throw any exception");
    }
    catch (IllegalArgumentException e) {
    }
    catch (Exception e) {
      fail("This test did not throw the correct kind of exception");
    }
  }
}