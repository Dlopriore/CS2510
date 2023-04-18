import tester.Tester;

//Pictures.java
//Dante LoPriore and Jacob Simeone
//Problem 2

//to represent a Picture
interface IPicture {

  // computes the overall width of this picture
  int getWidth();

  // produces the size of IPicture
  int getSize();

  // computes the number of single shapes involved in producing the final image.
  int countShapes();

  // computes how deeply operations are nested in the construction of this picture
  int comboDepth();

  // to flip two sub-images only in the Beside class, and the other classes remain
  // untouched
  IPicture mirror();

  // produces the contents of this picture, where the recipe for
  // the picture has been expanded only depth times.
  String pictureRecipe(int depth);

}

//to represent an Operation
interface IOperation {

  // computes the overall width of this picture
  int getWidth();

  // produces the size of IPicture
  int getSize();

  // computes the number of single shapes involved in producing the final image.
  int countShapes();

  // computes how deeply operations are nested in the construction of this picture
  int comboDepth();

  // to flip two sub-images only in the Beside class, and the other classes remain
  // untouched
  IOperation mirror();

  // produces the contents of this picture, where the recipe for
  // the picture has been expanded only depth times.
  String pictureRecipe(int depth);
}

// to represents a Shape
class Shape implements IPicture {
  String kind;
  int size;

  // constructor
  Shape(String kind, int size) {
    this.kind = kind;
    this.size = size;
  }

  /*Fields:
   * this.kind ... String
   * this.size ... int
   *Methods:
   * this.getWidth() ... int
   * this.countShapes() ... int
   * this.comboDepth() ... int
   * this.mirror() ... IPicture
   * this.pictureRecipe(int depth) ... String
   *Methods For Fields
   * this.getSize() ... int
   */

  // computes the overall width of this picture
  public int getWidth() {
    return this.getSize();
  }

  // produces the size of IPicture
  public int getSize() {
    // TODO Auto-generated method stub
    return this.size;
  }

  // computes the number of single shapes involved in producing the final image.
  public int countShapes() {
    return 1;
  }

  // computes how deeply operations are nested in the construction of this picture
  public int comboDepth() {
    // TODO Auto-generated method stub
    return 0;
  }

  // to flip two sub-images only in the Beside class, and the other classes remain
  // untouched
  public IPicture mirror() {
    // TODO Auto-generated method stub
    return this;
  }

  // produces the contents of this picture, where the recipe for
  // the picture has been expanded only depth times.
  public String pictureRecipe(int depth) {
    /*Fields:
     * this.kind ... String
     * this.size ... int
     *Methods:
     * this.getWidth() ... int
     * this.countShapes() ... int
     * this.comboDepth() ... int
     * this.mirror() ... IPicture
     * this.pictureRecipe(int depth) ... String
     *Methods For Fields:
     * this.getSize() ... int
     *Parameters:
     * depth ... int
     */
    return this.kind;
  }
}

//to represent a Combo
class Combo implements IPicture {
  String name;
  IOperation operation;

  // constructor
  Combo(String name, IOperation operation) {
    this.name = name;
    this.operation = operation;
  }

  /*Fields:
   * this.name ... String
   * this.operation ... IOperation
   *Methods:
   * this.getWidth() ... int
   * this.countShapes() ... int
   * this.comboDepth() ... int
   * this.mirror() ... IPicture
   * this.pictureRecipe(int depth) ... String
   *Methods For Fields
   * this.operation.getWidth() ... int
   * this.operation.countShapes() ... int
   * this.operation.comboDepth() ... int
   * this.operation.mirror() ... IPicture
   * this.operation.pictureRecipe(int depth) ... String
   */

  // computes the overall width of this picture
  public int getWidth() {
    return this.operation.getWidth();
  }

  // produces the size of IPicture
  public int getSize() {
    // TODO Auto-generated method stub
    return this.operation.getSize();
  }

  // computes the number of single shapes involved in producing the final image.
  public int countShapes() {
    return this.operation.countShapes();
  }

  // computes how deeply operations are nested in the construction of this picture
  public int comboDepth() {
    return this.operation.comboDepth();
  }

  // to flip two sub-images only in the Beside class, and the other classes remain
  // untouched
  public IPicture mirror() {
    // TODO Auto-generated method stub
    return new Combo(this.name, this.operation.mirror());
  }

  // produces the contents of this picture, where the recipe for
  // the picture has been expanded only depth times.
  public String pictureRecipe(int depth) {
    /*Fields:
     * this.name ... String
     * this.operation ... IOperation
     *Methods:
     * this.getWidth() ... int
     * this.countShapes() ... int
     * this.comboDepth() ... int
     * this.mirror() ... IPicture
     * this.pictureRecipe(int depth) ... String
     *Methods For Fields
     * this.operation.getWidth() ... int
     * this.operation.countShapes() ... int
     * this.operation.comboDepth() ... int
     * this.operation.mirror() ... IPicture
     * this.operation.pictureRecipe(int depth) ... String
     *Parameters:
     * depth ... int
     */
    if (depth <= 0) {
      return this.name;
    }
    else {
      return this.operation.pictureRecipe(depth - 1);
    }
  }
}

//to represent a Scale
class Scale implements IOperation {
  IPicture picture;

  // constructor
  Scale(IPicture picture) {
    this.picture = picture;
  }

  /*Fields:
   * this.picture ... IPicture
   *Methods:
   * this.getWidth() ... int
   * this.countShapes() ... int
   * this.comboDepth() ... int
   * this.mirror() ... IOperation
   * this.pictureRecipe(int depth) ... String
   *Methods For Fields
   * this.picture.getWidth() ... int
   * this.picture.countShapes() ... int
   * this.picture.comboDepth() ... int
   * this.picture.mirror() ... IPicture
   * this.picture.pictureRecipe(int depth) ... String
   */

  // computes the overall width of this picture
  public int getWidth() {
    // TODO Auto-generated method stub
    return this.getSize();
  }

  // produces the size of IPicture
  public int getSize() {
    // TODO Auto-generated method stub
    return this.picture.getSize() * 2;
  }

  // computes the number of single shapes involved in producing the final image.
  public int countShapes() {
    // TODO Auto-generated method stub
    return this.picture.countShapes();
  }

  // computes how deeply operations are nested in the construction of this picture
  public int comboDepth() {
    // TODO Auto-generated method stub
    return 1 + this.picture.comboDepth();
  }

  // to flip two sub-images only in the Beside class, and the other classes remain
  // untouched
  public IOperation mirror() {
    // TODO Auto-generated method stub
    return new Scale(this.picture.mirror());
  }

  // produces the contents of this picture, where the recipe for
  // the picture has been expanded only depth times.
  public String pictureRecipe(int depth) {
    /*Fields:
     * this.picture ... IPicture
     *Methods:
     * this.getWidth() ... int
     * this.countShapes() ... int
     * this.comboDepth() ... int
     * this.mirror() ... IOperation
     * this.pictureRecipe(int depth) ... String
     *Methods For Fields
     * this.picture.getWidth() ... int
     * this.picture.countShapes() ... int
     * this.picture.comboDepth() ... int
     * this.picture.mirror() ... IPicture
     * this.picture.pictureRecipe(int depth) ... String
     *Parameters
     * depth ... int
     */
    return "scale(" + this.picture.pictureRecipe(depth) + ")";
  }
}

//to represent Beside
class Beside implements IOperation {
  IPicture picture1;
  IPicture picture2;

  // constructor
  Beside(IPicture picture1, IPicture picture2) {
    this.picture1 = picture1;
    this.picture2 = picture2;
  }

  /*Fields:
   * this.picture1 ... IPicture
   * this.picture2 ... IPicture
   *Methods:
   * this.getWidth() ... int
   * this.countShapes() ... int
   * this.comboDepth() ... int
   * this.mirror() ... IOperation
   * this.pictureRecipe(int depth) ... String
   *Methods For Fields
   * this.picture1.getWidth() ... int
   * this.picture1.countShapes() ... int
   * this.picture1.comboDepth() ... int
   * this.picture1.mirror() ... IPicture
   * this.picture1.pictureRecipe(int depth) ... String
   * this.picture2.getWidth() ... int
   * this.picture2.countShapes() ... int
   * this.picture2.comboDepth() ... int
   * this.picture2.mirror() ... IPicture
   * this.picture2.pictureRecipe(int depth) ... String
   */

  // computes the overall width of this picture
  public int getWidth() {
    // TODO Auto-generated method stub
    return this.getSize();
  }

  // produces the size of IPicture
  public int getSize() {
    // TODO Auto-generated method stub
    return this.picture1.getSize() + this.picture2.getSize();
  }

  // computes the number of single shapes involved in producing the final image.
  public int countShapes() {
    // TODO Auto-generated method stub
    return this.picture1.countShapes() + this.picture2.countShapes();
  }

  // computes how deeply operations are nested in the construction of this picture
  public int comboDepth() {
    // TODO Auto-generated method stub
    return 1 + Math.max(this.picture1.comboDepth(), this.picture2.comboDepth());
  }

  // to flip two sub-images only in the Beside class, and the other classes remain
  // untouched
  public IOperation mirror() {
    // TODO Auto-generated method stub
    return new Beside(this.picture2.mirror(), this.picture1.mirror());
  }

  // produces the contents of this picture, where the recipe for
  // the picture has been expanded only depth times.
  public String pictureRecipe(int depth) {
    /*Fields:
     * this.picture1 ... IPicture
     * this.picture2 ... IPicture
     *Methods:
     * this.getWidth() ... int
     * this.countShapes() ... int
     * this.comboDepth() ... int
     * this.mirror() ... IOperation
     * this.pictureRecipe(int depth) ... String
     *Methods For Fields
     * this.picture1.getWidth() ... int
     * this.picture1.countShapes() ... int
     * this.picture1.comboDepth() ... int
     * this.picture1.mirror() ... IPicture
     * this.picture1.pictureRecipe(int depth) ... String
     * this.picture2.getWidth() ... int
     * this.picture2.countShapes() ... int
     * this.picture2.comboDepth() ... int
     * this.picture2.mirror() ... IPicture
     * this.picture2.pictureRecipe(int depth) ... String
     *Parameters
     * depth ... int
     */
    return "beside(" + this.picture1.pictureRecipe(depth) + ", "
        + this.picture2.pictureRecipe(depth) + ")";
  }
}

//to represent an Overlay
class Overlay implements IOperation {
  IPicture topPicture;
  IPicture bottomPicture;

  // constructor
  Overlay(IPicture topPicture, IPicture bottomPicture) {
    this.topPicture = topPicture;
    this.bottomPicture = bottomPicture;
  }

  /*Fields:
   * this.topPicture ... IPicture
   * this.bottomPicture ... IPicture
   *Methods:
   * this.getWidth() ... int
   * this.countShapes() ... int
   * this.comboDepth() ... int
   * this.mirror() ... IOperation
   * this.pictureRecipe(int depth) ... String
   *Methods For Fields
   * this.topPicture.getWidth() ... int
   * this.topPicture.countShapes() ... int
   * this.topPicture.comboDepth() ... int
   * this.topPicture.mirror() ... IPicture
   * this.topPicture.pictureRecipe(int depth) ... String
   * this.bottomPicture.getWidth() ... int
   * this.bottomPicture.countShapes() ... int
   * this.bottomPicture.comboDepth() ... int
   * this.bottomPicture.mirror() ... IPicture
   * this.bottomPicture.pictureRecipe(int depth) ... String
   */

  // computes the overall width of this picture
  public int getWidth() {
    // TODO Auto-generated method stub
    return this.getSize();
  }

  // produces the size of IPicture
  public int getSize() {
    // TODO Auto-generated method stub
    return this.bottomPicture.getSize();
  }

  // computes the number of single shapes involved in producing the final image.
  public int countShapes() {
    // TODO Auto-generated method stub
    return this.topPicture.countShapes() + this.bottomPicture.countShapes();
  }

  // computes how deeply operations are nested in the construction of this picture
  public int comboDepth() {
    // TODO Auto-generated method stub
    return 1 + Math.max(this.topPicture.comboDepth(), this.bottomPicture.comboDepth());
  }

  // to flip two sub-images only in the Beside class, and the other classes remain
  // untouched
  public IOperation mirror() {
    // TODO Auto-generated method stub
    return new Overlay(this.topPicture.mirror(), this.bottomPicture.mirror());
  }

  // produces the contents of this picture, where the recipe for
  // the picture has been expanded only depth times.
  public String pictureRecipe(int depth) {
    /*Fields:
     * this.topPicture ... IPicture
     * this.bottomPicture ... IPicture
     *Methods:
     * this.getWidth() ... int
     * this.countShapes() ... int
     * this.comboDepth() ... int
     * this.mirror() ... IOperation
     * this.pictureRecipe(int depth) ... String
     *Methods For Fields
     * this.topPicture.getWidth() ... int
     * this.topPicture.countShapes() ... int
     * this.topPicture.comboDepth() ... int
     * this.topPicture.mirror() ... IPicture
     * this.topPicture.pictureRecipe(int depth) ... String
     * this.bottomPicture.getWidth() ... int
     * this.bottomPicture.countShapes() ... int
     * this.bottomPicture.comboDepth() ... int
     * this.bottomPicture.mirror() ... IPicture
     * this.bottomPicture.pictureRecipe(int depth) ... String
     *Parameters:
     * depth ... int
     */
    return "overlay(" + this.topPicture.pictureRecipe(depth) + ", "
        + this.bottomPicture.pictureRecipe(depth) + ")";
  }
}

//to represent the tests for examples within the Picture.java classes.
class ExamplesPicture {

  // default constructor
  ExamplesPicture() {
  }

  // Required Tests

  // to represent an example of a Shape
  IPicture circle = new Shape("circle", 20);
  IPicture square = new Shape("square", 30);

  // to represent an example of scale
  IOperation bigCircleExample = new Scale(this.circle);
  // to represent an example of a Combo
  Combo bigCircle = new Combo("big circle", this.bigCircleExample);

  // to represent an example of overlay
  IOperation squareOnCircleExample = new Overlay(this.square, this.bigCircle);
  // to represent an example of a Combo
  Combo squareOnCircle = new Combo("square on circle", this.squareOnCircleExample);

  // to represent an example of beside
  IOperation doubledSquareOnCircleExample = new Beside(this.squareOnCircle, this.squareOnCircle);
  // to represent an example of a Combo
  Combo doubledSquareOnCircle = new Combo("doubled square on circle",
      this.doubledSquareOnCircleExample);

  // My Test to show I know the concept

  // to represent an example of a Shape
  IPicture triangle = new Shape("triangle", 30);
  IPicture rectangle = new Shape("rectangle", 60);

  // to represent an example of scale
  IOperation bigRectangleExample = new Scale(this.rectangle);
  // to represent an example of a Combo
  Combo bigRectangle = new Combo("big rectangle", this.bigRectangleExample);

  // to represent an example of overlay
  IOperation triangleOnRectangleExample = new Overlay(this.triangle, this.bigRectangle);
  // to represent an example of a Combo
  Combo triangleOnRectangle = new Combo("triangle on rectangle", this.triangleOnRectangleExample);

  // to represent an example of beside
  IOperation doubledTriangleOnRectangleExample = new Beside(this.triangleOnRectangle,
      this.triangleOnRectangle);
  // to represent an example of a Combo
  Combo doubleTriangleOnRectangle = new Combo("doubled triangle on rectangle",
      this.doubledTriangleOnRectangleExample);

  // tests for the getWidth() method
  boolean testGetWidth(Tester t) {
    return t.checkExpect(this.circle.getWidth(), 20) && t.checkExpect(this.square.getWidth(), 30)
        && t.checkExpect(this.bigCircle.getWidth(), 40)
        && t.checkExpect(this.squareOnCircle.getWidth(), 40)
        && t.checkExpect(this.doubledSquareOnCircle.getWidth(), 80);
  }

  // tests for the getSize() method
  boolean testGetSize(Tester t) {
    return t.checkExpect(this.circle.getSize(), 20) && t.checkExpect(this.square.getSize(), 30)
        && t.checkExpect(this.bigCircle.getSize(), 40)
        && t.checkExpect(this.squareOnCircle.getSize(), 40)
        && t.checkExpect(this.doubledSquareOnCircle.getSize(), 80);
  }

  // tests for the countShapes() method
  boolean testCountShapes(Tester t) {
    return t.checkExpect(this.circle.countShapes(), 1)
        && t.checkExpect(this.bigCircle.countShapes(), 1)
        && t.checkExpect(this.squareOnCircle.countShapes(), 2)
        && t.checkExpect(this.doubledSquareOnCircle.countShapes(), 4);
  }

  // tests for the comboDepth() method
  boolean testComboDepth(Tester t) {
    return t.checkExpect(this.circle.comboDepth(), 0)
        && t.checkExpect(this.bigCircle.comboDepth(), 1)
        && t.checkExpect(this.squareOnCircle.comboDepth(), 2)
        && t.checkExpect(this.doubledSquareOnCircle.comboDepth(), 3);
  }

  // tests for the mirror() method
  boolean testMirror(Tester t) {
    return t.checkExpect(this.doubledSquareOnCircleExample.mirror(),
        new Beside(this.squareOnCircle, this.squareOnCircle))
        && t.checkExpect(this.squareOnCircleExample.mirror(),
            new Overlay(this.square, this.bigCircle))
        && t.checkExpect(this.square.mirror(), this.square)
        && t.checkExpect(this.circle.mirror(), this.circle);
  }

  // tests for the pictureRecipe() method
  boolean testPictureRecipe(Tester t) {
    return t.checkExpect(this.doubledSquareOnCircle.pictureRecipe(0), "doubled square on circle")
        && t.checkExpect(this.doubledSquareOnCircle.pictureRecipe(1),
            "beside(square on circle, square on circle)")
        && t.checkExpect(this.doubledSquareOnCircle.pictureRecipe(2),
            "beside(overlay(square, big circle), overlay(square, big circle))")
        && t.checkExpect(this.doubledSquareOnCircle.pictureRecipe(3),
            "beside(overlay(square, scale(circle)), overlay(square, scale(circle)))");
  }

}