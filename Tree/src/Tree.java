//Tree.java
//Dante LoPriore and Jake Simeone

import java.awt.Color;

import javalib.funworld.WorldScene;
import javalib.worldcanvas.WorldCanvas;
import javalib.worldimages.CircleImage;
import javalib.worldimages.LineImage;
import javalib.worldimages.OutlineMode;
import javalib.worldimages.OverlayImage;
import javalib.worldimages.Posn;
import javalib.worldimages.RectangleImage;
import javalib.worldimages.RotateImage;
import javalib.worldimages.VisiblePinholeImage;
import javalib.worldimages.WorldImage;
import tester.Tester;

//represents an Tree
interface ITree { /* see methods below */

  // renders your ITree to a picture
  WorldImage draw();

  // to compute whether any of the twigs in the tree (either stems or branches)
  // are pointing downward rather than upward
  boolean isDrooping();

  // takes the current tree and a given tree and produces a Branch using the given
  // arguments,
  // with this tree on the left and the given tree on the right
  ITree combine(int leftLength, int rightLength, double leftTheta, double rightTheta,
      ITree thatTree);

  // to attach those two stems to form a branch
  // in other words, it is the combination step
  ITree rotate(int degree);

  // to produce the width of an ITree
  double getWidth();

  // computes right width
  double computeRightWidth();

  // computes left width
  double computeLeftWidth();

  // returns the smallest value for the left side
  double leftCompare();

  // returns the largest value for the right side
  double rightCompare();

  // computes the right stem of a branch
  double rightComputeBase();

  // computes the left stem of a branch
  double leftComputeBase();

}

//represents a leaf 
class Leaf implements ITree {
  int size; // represents the radius of the leaf
  Color color; // the color to draw it

  // constructor
  Leaf(int size, Color color) {
    this.size = size;
    this.color = color;
  }

  /* TEMPLATE
  FIELDS
  ... this.size ...              -- int
  ... this.color ...           -- Color
  METHODS
  ... this.draw() ...                  -- WorldImage 
  ... this.isDrooping() ...            -- boolean 
  ... this.combine(int, int, double, double, ITree) ...     -- ITree
  ... this.rotate(int) ... -- ITree
  ... this.getWidth() ...     -- Double
  ... this.computeRightWidth() ...     -- double
  ... this.computeLeftWidth() ...     -- double
  ... this.leftCompare() ...  -- double
  ... this.rightCompare() ...  -- double
  ... this.rightComputeBase() ...  -- double
  ... this.leftComputeBase() ...  -- double
  
  
  METHODS FOR FIELDS:
  NONE
  */

  // renders your ITree to a picture
  public WorldImage draw() {
    // TODO Auto-generated method stub
    return new CircleImage(this.size, OutlineMode.SOLID, this.color);
  }

  // to compute whether any of the twigs in the tree (either stems or branches)
  // are pointing downward rather than upward
  public boolean isDrooping() {
    // TODO Auto-generated method stub
    return false;
  }

  // takes the current tree and a given tree and produces a Branch using the given
  // arguments,
  // with this tree on the left and the given tree on the right
  public ITree combine(int leftLength, int rightLength, double leftTheta, double rightTheta,
      ITree thatTree) {
    /*FIELDS
     * ... this.size ...              -- int
     * ... this.color ...           -- Color
     *Methods for Fields
     *   ... this.thatTree.draw() ...                  -- WorldImage
     *   ... this.thatTree.isDrooping() ...            -- boolean 
     *   ... this.thatTree.combine(int, int, double, double, ITree) ...     -- ITree
     *   ... this.thatTree.rotate(int) ... -- ITree
     *   ... this.thatTree.getWidth() ...     -- Double
     *   ... this.thatTree.computeRightWidth() ...    -- Double
     *   ... this.thatTree.computeLeftWidth() ...     -- Double
     *   ... this.thatTree.leftCompare() ...     -- Double
     *   ... this.thatTree.rightCompare() ...     -- Double
     *   ... this.thatTree.rightComputeBase() ...     -- Double
     *   ... this.thatTree.leftComputeBase() ...     -- Double
     *Parameters:
     *   ... leftlength ...              -- int
     *   ... rightlength ...              -- int
     *   ... lefttheta ...           -- double
     *   ... righttheta ...           -- double
     *   ... thatTree ...           -- ITree
     */
    // TODO Auto-generated method stub
    return new Branch(leftLength, rightLength, leftTheta, rightTheta,
        this.rotate((int) (leftTheta - 90)), thatTree.rotate((int) rightTheta - 90));
  }

  // to attach those two stems to form a branch
  // in other words, it is the combination step
  public ITree rotate(int i) {
    /*Feilds: None
     *Parameters:
     * degree ... int
     */
    // TODO Auto-generated method stub
    return this;
  }

  // to produce the width of an ITree
  public double getWidth() {
    // TODO Auto-generated method stub
    return this.size * 2;
  }

  // computes right width
  public double computeRightWidth() {
    // TODO Auto-generated method stub
    return 0;
  }

  // computes left width
  public double computeLeftWidth() {
    // TODO Auto-generated method stub
    return 0;
  }

  // returns the smallest value for the left side
  public double leftCompare() {
    // TODO Auto-generated method stub
    return 0;
  }

  // returns the largest value for the right side
  public double rightCompare() {
    // TODO Auto-generated method stub
    return 0;
  }

  // computes the right stem of a branch
  public double rightComputeBase() {
    // TODO Auto-generated method stub
    return 0;
  }

  // computes the left stem of a branch
  public double leftComputeBase() {
    // TODO Auto-generated method stub
    return 0;
  }

}

//represents an Stem
class Stem implements ITree {
  // How long this stick is
  int length;
  // The angle (in degrees) of this stem, relative to the +x axis
  double theta;
  // The rest of the tree
  ITree tree;

  // constructor
  Stem(int length, double theta, ITree tree) {
    this.length = length;
    this.theta = theta;
    this.tree = tree;
  }

  /* TEMPLATE
  FIELDS
  ... this.length ...              -- int
  ... this.theta ...           -- double
  ... this.tree ...           -- ITree
  
  METHODS
  ... this.draw() ...                  -- WorldImage 
  ... this.isDrooping() ...            -- boolean 
  ... this.combine(int, int, double, double, ITree) ...     -- ITree
  ... this.rotate(int) ... -- ITree
  ... this.getWidth() ...     -- Double
  ... this.computeRightWidth() ...    -- Double
  ... this.computeLeftWidth() ...     -- Double
  ... this.computeBase() ...          -- Double
  ... this.leftCompare() ...          -- Double
  ... this.rightCompare() ...          -- Double
  ... this.rightComputeBase() ...          -- Double
  ... this.leftComputeBase() ...          -- Double
  
  METHODS FOR FIELDS:
  ... this.tree.draw() ...                  -- WorldImage 
  ... this.tree.isDrooping() ...            -- boolean 
  ... this.tree.combine(int, int, double, double, ITree) ...     -- ITree
  ... this.tree.rotate(int) ... -- ITree
  ... this.tree.getWidth() ...     -- Double
  ... this.tree.computeRightWidth() ...    -- Double
  ... this.tree.computeLeftWidth() ...     -- Double
  ... this.leftCompare() ...          -- Double
  ... this.rightCompare() ...          -- Double
  ... this.rightComputeBase() ...          -- Double
  ... this.leftComputeBase() ...          -- Double
  
  
  */

  // renders your ITree to a picture
  public WorldImage draw() {

    if (this.theta > 0 && this.theta <= 90) {
      this.theta = this.theta;
    }
    else if (this.theta > 90 && this.theta < 180) {
      this.theta = this.theta - 180;
    }
    else if (this.theta >= 270 && this.theta < 360) {
      this.theta = this.theta - 180;
    }

    WorldImage stem = new VisiblePinholeImage(
        new LineImage(new Posn(0, this.length), Color.BLUE).movePinhole(0, -this.length / 2));
    WorldImage overlay = new VisiblePinholeImage(
        new OverlayImage(stem, this.tree.draw()).movePinhole(0, this.length));
    WorldImage rotated = new RotateImage(overlay, this.theta);
    return rotated;
  }

  // to compute whether any of the twigs in the tree (either stems or branches)
  // are pointing downward rather than upward
  public boolean isDrooping() {
    return this.theta > 180 || this.tree.isDrooping();
  }

  // takes the current tree and a given tree and produces a Branch using the given
  // arguments,
  // with this tree on the left and the given tree on the right
  public ITree combine(int leftLength, int rightLength, double leftTheta, double rightTheta,
      ITree thatTree) {
    /*FIELDS
     * ... this.length ...              -- int
     * ... this.theta ...           -- double
     * ... this.tree ...           -- ITree
     *Methods for Fields
     *   ... this.thatTree.draw() ...                  -- WorldImage
     *   ... this.thatTree.isDrooping() ...            -- boolean 
     *   ... this.thatTree.combine(int, int, double, double, ITree) ...     -- ITree
     *   ... this.thatTree.rotate(int) ... -- ITree
     *   ... this.thatTree.getWidth() ...     -- Double
     *   ... this.thatTree.computeRightWidth() ...    -- Double
     *   ... this.thatTree.computeLeftWidth() ...     -- Double
     *   ... this.thatTree.leftCompare() ...     -- Double
     *   ... this.thatTree.rightCompare() ...     -- Double
     *   ... this.thatTree.rightComputeBase() ...     -- Double
     *   ... this.thatTree.leftComputeBase() ...     -- Double
     *Parameters:
     *   ... leftlength ...              -- int
     *   ... rightlength ...              -- int
     *   ... lefttheta ...           -- double
     *   ... righttheta ...           -- double
     *   ... thatTree ...           -- ITree
     */
    // TODO Auto-generated method stub
    return new Branch(leftLength, rightLength, leftTheta, rightTheta,
        this.rotate((int) (leftTheta - 90)), thatTree.rotate((int) rightTheta - 90));
  }

  // to attach those two stems to form a branch
  // in other words, it is the combination step
  public ITree rotate(int degree) {
    /*Feilds: None
     *Parameters:
     * degree ... int
     */
    // TODO Auto-generated method stub
    return new Stem(this.length, this.theta + degree, this.tree.rotate(degree));
  }

  // to produce the width of an ITree
  public double getWidth() {
    // TODO Auto-generated method stub
    return this.computeRightWidth() - this.computeLeftWidth();
  }

  // computes right width
  public double computeRightWidth() {
    // TODO Auto-generated method stub
    return Math.max(this.computeBase() + this.tree.computeRightWidth(), 0);
  }

  // computes left width
  public double computeLeftWidth() {
    // TODO Auto-generated method stub
    return Math.min(this.computeBase() + this.tree.computeLeftWidth(), 0);
  }

  // to compute the horizontal base
  double computeBase() {
    // TODO Auto-generated method stub
    return this.length * Math.cos(Math.toRadians(this.theta));
  }

  // returns the smallest value for the left side
  public double leftCompare() {
    // TODO Auto-generated method stub
    return 0;
  }

  // returns the largest value for the right side
  public double rightCompare() {
    // TODO Auto-generated method stub
    return 0;
  }

  // computes the right stem of a branch
  public double rightComputeBase() {
    // TODO Auto-generated method stub
    return 0;
  }

  // computes the left stem of a branch
  public double leftComputeBase() {
    // TODO Auto-generated method stub
    return 0;
  }
}

//represents a Branch
class Branch implements ITree {
  // How long the left and right branches are
  int leftLength;
  int rightLength;
  // The angle (in degrees) of the two branches, relative to the +x axis,
  double leftTheta;
  double rightTheta;
  // The remaining parts of the tree
  ITree left;
  ITree right;

  // constructor
  Branch(int leftLength, int rightLength, double leftTheta, double rightTheta, ITree left,
      ITree right) {
    this.leftLength = leftLength;
    this.rightLength = rightLength;
    this.leftTheta = leftTheta;
    this.rightTheta = rightTheta;
    this.left = left;
    this.right = right;
  }
  /* TEMPLATE
  FIELDS
  ... this.leftlength ...              -- int
  ... this.rightlength ...              -- int
  ... this.lefttheta ...           -- double
  ... this.righttheta ...           -- double
  ... this.left ...           -- ITree
  ... this.right ...           -- ITree
  
  METHODS
  ... this.draw() ...                  -- WorldImage 
  ... this.isDrooping() ...            -- boolean 
  ... this.combine(int, int, double, double, ITree) ...     -- ITree
  ... this.rotate(int) ... -- ITree
  ... this.getWidth() ...     -- Double
  ... this.computeRightWidth() ...    -- Double
  ... this.computeLeftWidth() ...     -- Double
  ... this.leftCompare() ...     -- Double
  ... this.rightCompare() ...     -- Double
  ... this.rightComputeBase() ...     -- Double
  ... this.leftComputeBase() ...     -- Double
  
  METHODS FOR FIELDS:
  ... this.left.draw() ...                  -- WorldImage 
  ... this.left.isDrooping() ...            -- boolean 
  ... this.left.combine(int, int, double, double, ITree) ...     -- ITree
  ... this.left.rotate(int) ... -- ITree
  ... this.left.getWidth() ...     -- Double
  ... this.left.computeRightWidth() ...    -- Double
  ... this.left.computeLeftWidth() ...     -- Double
  ... this.left.leftCompare() ...     -- Double
  ... this.left.rightCompare() ...     -- Double
  ... this.left.rightComputeBase() ...     -- Double
  ... this.left.leftComputeBase() ...     -- Double
  ... this.right.draw() ...                  -- WorldImage 
  ... this.right.isDrooping() ...            -- boolean 
  ... this.right.combine(int, int, double, double, ITree) ...     -- ITree
  ... this.right.rotate(int) ... -- ITree
  ... this.right.getWidth() ...     -- Double
  ... this.right.computeRightWidth() ...    -- Double
  ... this.right.computeLeftWidth() ...     -- Double
  ... this.right.leftCompare() ...     -- Double
  ... this.right.rightCompare() ...     -- Double
  ... this.right.rightComputeBase() ...     -- Double
  ... this.right.leftComputeBase() ...     -- Double
  */

  // renders your ITree to a picture
  public WorldImage draw() {

    if (this.leftTheta > 0 && this.leftTheta <= 90) {
      this.leftTheta = this.leftTheta;
    }
    else if (this.leftTheta > 90 && this.leftTheta < 180) {
      this.leftTheta = this.leftTheta - 180;
    }
    else if (this.leftTheta >= 270 && this.leftTheta < 360) {
      this.leftTheta = this.leftTheta - 180;
    }

    WorldImage stemLeft = new VisiblePinholeImage(
        new LineImage(new Posn(0, 2 * this.leftLength), Color.BLUE).movePinhole(0,
            -this.leftLength / 2));
    WorldImage overlayLeft = new VisiblePinholeImage(
        new OverlayImage(stemLeft, this.left.draw()).movePinhole(0, -this.leftTheta));
    WorldImage rotatedLeft = new RotateImage(overlayLeft, this.leftTheta);

    if (this.rightTheta > 0 && this.rightTheta <= 90) {
      this.rightTheta = this.rightTheta;
    }
    else if (this.rightTheta > 90 && this.rightTheta < 180) {
      this.rightTheta = this.leftTheta - 180;
    }
    else if (this.rightTheta >= 270 && this.rightTheta < 360) {
      this.rightTheta = this.rightTheta - 180;
    }

    WorldImage stemRight = new VisiblePinholeImage(
        new LineImage(new Posn(0, 2 * this.rightLength), Color.BLUE).movePinhole(0,
            -this.rightLength / 2));
    WorldImage overlayRight = new VisiblePinholeImage(
        new OverlayImage(stemRight, this.right.draw()).movePinhole(0, this.rightTheta));
    WorldImage rotatedRight = new RotateImage(overlayRight, this.rightTheta);

    WorldImage combined = new OverlayImage(rotatedLeft, rotatedRight);

    WorldImage finished = new VisiblePinholeImage(
        new OverlayImage(rotatedLeft, combined).movePinhole(100, this.leftTheta));

    return finished;

  }

  // to compute whether any of the twigs in the tree (either stems or branches)
  // are pointing downward rather than upward
  public boolean isDrooping() {
    return this.left.isDrooping() || this.leftTheta > 180 || this.right.isDrooping()
        || this.rightTheta > 180;
  }

  // takes the current tree and a given tree and produces a Branch using the given
  // arguments,
  // with this tree on the left and the given tree on the right
  public ITree combine(int leftLength, int rightLength, double leftTheta, double rightTheta,
      ITree thatTree) {
    /*FIELDS
     * this.leftlength ...              -- int
     * this.rightlength ...              -- int
     * this.lefttheta ...           -- double
     * this.righttheta ...           -- double
     * this.left ...           -- ITree
     * this.right ...           -- ITree
     *Methods for Fields
     *   ... this.thatTree.draw() ...                  -- WorldImage
     *   ... this.thatTree.isDrooping() ...            -- boolean 
     *   ... this.thatTree.combine(int, int, double, double, ITree) ...     -- ITree
     *   ... this.thatTree.rotate(int) ... -- ITree
     *   ... this.thatTree.getWidth() ...     -- Double
     *   ... this.thatTree.computeRightWidth() ...    -- Double
     *   ... this.thatTree.computeLeftWidth() ...     -- Double
     *   ... this.thatTree.leftCompare() ...     -- Double
     *   ... this.thatTree.rightCompare() ...     -- Double
     *   ... this.thatTree.rightComputeBase() ...     -- Double
     *   ... this.thatTree.leftComputeBase() ...     -- Double
     *Parameters:
     *   ... leftlength ...              -- int
     *   ... rightlength ...              -- int
     *   ... lefttheta ...           -- double
     *   ... righttheta ...           -- double
     *   ... thatTree ...           -- ITree
     */

    return new Branch(leftLength, rightLength, leftTheta, rightTheta,
        this.rotate((int) rightTheta - 90), thatTree.rotate((int) leftLength - 90));
  }

  // to attach those two stems to form a branch
  // in other words, it is the combination step
  public ITree rotate(int degree) {
    /*Fields: None
     *Parameters:
     * degree ... int
     */
    return new Branch(this.leftLength, this.rightLength, degree + this.leftTheta,
        degree + this.rightTheta, left.rotate(degree), right.rotate(degree));
  }

  // to produce the width of the tree
  public double getWidth() {
    // TODO Auto-generated method stub
    return this.computeRightWidth() - this.computeLeftWidth();
  }

  // computes right width
  public double computeRightWidth() {
    // TODO Auto-generated method stub
    return Math.max(this.rightCompare(),
        Math.max(this.rightComputeBase() + this.right.computeRightWidth(), 0));
  }

  // computes left width
  public double computeLeftWidth() {
    // TODO Auto-generated method stub
    return Math.min(this.leftCompare(),
        Math.min(this.leftComputeBase() + this.left.computeLeftWidth(), 0));
  }

  // returns the smallest value for the left side
  public double leftCompare() {
    return Math.min(this.rightComputeBase() + this.right.computeRightWidth(),
        this.leftComputeBase() + this.left.computeLeftWidth());
  }

  // returns the largest value for the right side
  public double rightCompare() {
    return Math.max(this.leftComputeBase() + this.left.computeLeftWidth(),
        this.rightComputeBase() + this.right.computeRightWidth());
  }

  // computes the right stem of a branch
  public double rightComputeBase() {
    // TODO Auto-generated method stub
    return this.rightLength * Math.cos(Math.toRadians(this.rightTheta));
  }

  // computes the left stem of a branch
  public double leftComputeBase() {
    // TODO Auto-generated method stub
    return this.leftLength * Math.cos(Math.toRadians(this.leftTheta));
  }
}

//tests and examples for the Tree.java classes
class ExamplesTree {
  ExamplesTree() {

  }

  // Examples of Trees
  ITree myTree = new Branch(30, 30, 135, 40, new Leaf(10, Color.RED), new Leaf(15, Color.BLUE));
  ITree myTree1 = new Branch(30, 30, 200, 200, new Leaf(10, Color.RED), new Leaf(15, Color.BLUE));
  ITree myTree2 = new Branch(30, 30, 100, 75, new Leaf(10, Color.RED), new Leaf(15, Color.BLUE));

  // Examples of leafs
  ITree leaf1 = new Leaf(20, Color.RED);
  ITree leaf2 = new Leaf(340, Color.BLUE);

  // Examples of stems
  ITree stem1 = new Stem(135, 100, this.leaf1);
  ITree stem2 = new Stem(40, 90, this.myTree1);
  Stem stem3 = new Stem(135, 100, this.leaf1);

  // tests for images
  boolean testImages(Tester t) {
    return t.checkExpect(new RectangleImage(30, 20, OutlineMode.SOLID, Color.GRAY),
        new RectangleImage(30, 20, OutlineMode.SOLID, Color.GRAY));
  }

  // tests for the draw method
  boolean testDrawTree(Tester t) {
    WorldCanvas c = new WorldCanvas(500, 500);
    WorldScene s = new WorldScene(500, 500);
    return c.drawScene(s.placeImageXY(myTree.draw(), 250, 250)) && c.show();
  }

  // tests for the isDrooping method
  boolean testIsDrooping(Tester t) {
    return t.checkExpect(this.myTree.isDrooping(), false)
        && t.checkExpect(this.myTree1.isDrooping(), true)
        && t.checkExpect(this.leaf2.isDrooping(), false)
        && t.checkExpect(this.leaf1.isDrooping(), false)
        && t.checkExpect(this.stem1.isDrooping(), false);
  }

  // tests for the combine method
  boolean testCombine(Tester t) {
    return t.checkExpect(this.leaf1.combine(10, 10, 45.0, 45.0, this.leaf2),
        new Branch(10, 10, 45.0, 45.0, this.leaf1, this.leaf2))
        && t.checkExpect(this.leaf1.combine(10, 10, 45.0, 45.0, this.stem1),
            new Branch(10, 10, 45.0, 45.0, this.leaf1, new Stem(135, 55.0, this.leaf1)))
        && t.checkExpect(this.myTree1.combine(10, 10, 45.0, 45.0, this.leaf1),
            new Branch(10, 10, 45.0, 45.0,
                new Branch(30, 30, 155.0, 155.0, new Leaf(10, Color.RED), new Leaf(15, Color.BLUE)),
                new Leaf(20, Color.RED)));
  }

  // tests for the rotate method which is a helper method for the combine method
  boolean testRotate(Tester t) {
    return t.checkExpect(this.leaf1.rotate(100), new Leaf(20, Color.RED))
        && t.checkExpect(this.myTree1.rotate(120),
            new Branch(30, 30, 320.0, 320.0, new Leaf(10, Color.RED), new Leaf(15, Color.BLUE)))
        && t.checkExpect(this.stem1.rotate(120), new Stem(135, 220.0, new Leaf(20, Color.RED)));
  }

  // tests for the getWidth method
  boolean testGetWidth(Tester t) {
    return t.checkExpect(this.myTree.getWidth(), 44.19453672916576)
        && t.checkExpect(this.leaf1.getWidth(), 40.0)
        && t.checkExpect(this.stem1.getWidth(), 23.44250398503559);
  }

  // tests for leftCompare
  boolean testLeftCompare(Tester t) {
    return t.checkExpect(this.leaf1.leftCompare(), 0.0)
        && t.checkExpect(this.myTree1.leftCompare(), -28.190778623577252)
        && t.checkExpect(this.stem1.leftCompare(), 0.0);
  }

  // tests for rightCompare
  boolean testRightCompare(Tester t) {
    return t.checkExpect(this.leaf1.rightCompare(), 0.0)
        && t.checkExpect(this.myTree1.rightCompare(), -28.190778623577252)
        && t.checkExpect(this.stem1.rightCompare(), 0.0);
  }

  // tests for computeBase
  boolean testComputeBase(Tester t) {
    return t.checkExpect(this.stem3.computeBase(), -23.44250398503559);
  }

  // tests for rightComputeBase
  boolean testRightComputeBase(Tester t) {
    return t.checkExpect(this.leaf1.rightComputeBase(), 0.0)
        && t.checkExpect(this.myTree1.rightComputeBase(), -28.190778623577252)
        && t.checkExpect(this.stem1.rightComputeBase(), 0.0);
  }

  // tests for leftComputeBase
  boolean testLeftComputeBase(Tester t) {
    return t.checkExpect(this.leaf1.leftComputeBase(), 0.0)
        && t.checkExpect(this.myTree1.leftComputeBase(), -28.190778623577252)
        && t.checkExpect(this.stem1.leftComputeBase(), 0.0);
  }

  // tests for computeLeftWidth
  boolean testComputeLeftWidth(Tester t) {
    return t.checkExpect(this.leaf1.computeLeftWidth(), 0.0)
        && t.checkExpect(this.myTree1.computeLeftWidth(), -28.190778623577252)
        && t.checkExpect(this.stem1.computeLeftWidth(), -23.44250398503559);
  }

  // computes for computeRightWidth
  boolean testComputeRightWidth(Tester t) {
    return t.checkExpect(this.leaf1.computeRightWidth(), 0.0)
        && t.checkExpect(this.myTree1.computeRightWidth(), 0.0)
        && t.checkExpect(this.stem1.computeRightWidth(), 0.0);
  }

}

