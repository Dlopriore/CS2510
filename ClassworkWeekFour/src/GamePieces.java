
import tester.Tester;
import javalib.worldcanvas.*;
import javalib.worldimages.*;
import java.awt.Color;
import javalib.funworld.*; 


//an interface to represent a game piece in the game
interface IGamePiece {
  Location START_POSITION = new Location(0, 30);
  //produce a new game piece that is shifted by x an y from this IGamePiece
  IGamePiece move(int x, int y);
  //draws this GamePiece onto the canvas
  WorldImage draw();
}

abstract class AGamePiece implements IGamePiece {
  Location loc;
  Color color;
  
  AGamePiece(Location loc, Color col) {
    this.loc = loc;
    this.color = col;
  }
  
  //convenience constructor
  AGamePiece(Color col) {
    //this.loc = new Location(0, 30);
    //this.color = col;
    this(START_POSITION, col);
  }
}

//a class to represent an invader in the game
class Invader extends AGamePiece {
  int bullets;
  int size;

  Invader(Location loc, Color color, int bullets, int size) {
    super(loc, color);
    this.bullets = bullets;
    this.size = size;
  }
  
  Invader(Color color, int bullets, int size) {
    super(color);
    this.bullets = bullets;
    this.size = size;
  }
  
  /* fields:
   *  this.loc ... Location
   *  this.color ... Color
   *  this.bullets ... int
   *  this.size   .... int
   * methods:
   *  this.move ... IGamePiece
   *  this.draw ... WorldImage
   * methods for fields:
   *  this.loc.moveLocation(int,int) ... Location  
   * 
   */

  //produce an Invader that is shifted by x and y from this Invader
  public IGamePiece move(int x, int y) {
    return new Invader(this.loc.moveLocation(x, y), this.color, this.bullets, this.size);
  }

  //draw this spaceship as a circle
  public WorldImage draw() {
    return new EllipseImage(50, 60, "solid", this.color);
  }
}


//a class to represent a spaceship in the game
class Spaceship  extends AGamePiece {
  int speed; // in miles per hour

  Spaceship(Location loc, Color color, int speed) {
    super(loc, color);
    this.speed = speed;
  }
  
  Spaceship(Color color, int speed) {
    super(color);
    this.speed = speed;
  }
  


  /* fields:
   *  this.loc ... Location
   *  this.color ... Color
   *  this.speed ... int
   * methods:
   *  this.move ... IGamePiece
   *  this.draw ... WorldImage
   * methods for fields:
   *  this.loc.moveLocation(int,int) ... Location  
   * 
   */
  
  //produce an Spaceship that is shifted by x and y from this Spaceship
  public IGamePiece move(int x, int y) {
    return new Spaceship(this.loc.moveLocation(x, y), this.color, this.speed);
  }

  //draw this spaceship as a circle
  public WorldImage draw() {
    return new CircleImage(50, "solid", this.color);
  }

}



//a class to represent a location on the Cartesian plane
class Location {
  int x;
  int y;

  Location(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /* fields:
   *   this.x ... int
   *   this.y ... int
   * methods:  
   *   this.moveLocation(int, int) ... Location
   */

  //produces a new Location that is shifted by x and y from this Location
  Location moveLocation(int x, int y) {
    return new Location(this.x + x, this.y + y);
  }
}




class ExamplesGamePieces {
  Location loc1 = new Location(30, 40);
  Location loc2 = new Location(60,80);

  IGamePiece ship1 = new Spaceship(this.loc1, Color.BLUE, 55);
  IGamePiece invader1 = new Invader(this.loc2, Color.PINK, 30, 3);
  IGamePiece ship3 = new Spaceship(Color.GREEN, 100);
  Spaceship ship2 = new Spaceship(this.loc1, Color.YELLOW, 55);


  //tests for move
  boolean testMove(Tester t) {
    return t.checkExpect(this.ship1.move(3, 2), new Spaceship(new Location(33, 42), Color.BLUE, 55)) &&
        t.checkExpect(this.invader1.move(3, 2), new Invader(new Location(63, 82), Color.PINK, 30, 3));
  }

  //test for drawing a spaceship
  boolean testDraw(Tester t) {
    return t.checkExpect(this.ship1.draw(), new CircleImage(50, "solid", Color.BLUE));
    
  }

  //draw the spaceship
  boolean toDrawTest(Tester t) {
    WorldCanvas c = new WorldCanvas(300, 500);
    WorldImage img1 = this.ship2.draw();
    WorldImage img2 = new VisiblePinholeImage(img1).movePinholeTo(new Posn(20,0));
    WorldImage img3 = new OverlayImage(new RectangleImage(20, 20, "solid", Color.BLACK), img2);
    WorldScene s = new WorldScene(300, 500).placeImageXY(img3, 150, 150);
    c.show();
    c.drawScene(s);
    return true;
  }


}