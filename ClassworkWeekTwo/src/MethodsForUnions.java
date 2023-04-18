import tester.Tester;
import javalib.worldimages.*;

import java.awt.Color;

import javalib.funworld.WorldScene;
import javalib.worldcanvas.*;

interface IGamePiece {
  //moves this IGamePiece by the given increments
  IGamePiece move(int dx, int dy);
  //draws this IGamePiece
  WorldImage draw();
}

//a class to represent a spaceship
class Spaceship implements IGamePiece {
  Location loc;
  String color;
  int speed; //in mph

  Spaceship(Location loc, String color, int speed) {
    this.loc = loc;
    this.color = color;
    this.speed = speed;
  } 

  /* fields:
   *  this.loc ... Location
   *  this.color ... String
   *  this.speed ... int
   * methods:
   *  this.reduceSpeed(int) ... int
   *  this.move(int, int) ... Spaceship
   *  this.draw() ... WorldImage
   * methods for fields:  
   *  this.loc.movePosition(int, int) ... Location
   */

  //produce the speed of this spaceship reduced by a given rate
  int reduceSpeed(int rate) {
    return this.speed - (this.speed * rate) / 100;
  }

  //moves this spaceship by the given x and y
  public IGamePiece move(int dx, int dy) {
    return new Spaceship(this.loc.movePosition(dx, dy), this.color, this.speed);
  }

  //draws this Spaceship as a red circle
  public WorldImage draw() {
    return new CircleImage(50, "solid", Color.RED);
  }
}

//a class to represent an invader in the game
class Invader implements IGamePiece {
  Location loc;
  String color;
  int bullets;
  int size;

  Invader(Location loc, String color, int bullets, int size) {
    this.loc = loc;
    this.color = color;
    this.bullets = bullets;
    this.size = size;
  } 

  /* fields:
   *  this.loc ... Location
   *  this.color ... String
   *  this.bullets ... int
   *  this.size ... int
   * methods:
   *  this.move(int, int) ... Spaceship
   *  this.draw() ... WorldImage
   * methods for fields:  
   *  this.loc.movePosition(int, int) ... Location
   */

  public IGamePiece move(int dx, int dy) {
    return new Invader(this.loc.movePosition(dx, dy), this.color, this.bullets, this.size);
  }

  //draws this Invader as a green circle
  public WorldImage draw() {
    return new CircleImage(50, "solid", Color.GREEN);
  }

}

//a class to represent a point on the Cartesian plane
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
   *   this.movePosition(int, int) ... Location  
   * 
   */

  //moves this location by the given x and y
  Location movePosition(int dx, int dy) {
    return new Location(this.x + dx, this.y + dy);
  }
}



class ExamplesSpaceship {
  Location loc3040 = new Location(30, 40);
  Location loc80120 = new Location(80, 120);
  IGamePiece ship1 = new Spaceship(this.loc3040, "red", 60);
  Spaceship ship3 = new Spaceship(this.loc3040, "red", 60);
  IGamePiece ship2 = new Spaceship(this.loc80120, "green", 100);
  IGamePiece invader1 = new Invader(this.loc3040, "black", 100, 30);

  ExamplesSpaceship() {}

  //tests for reduceSpeed
  boolean testReduceSpeed(Tester t) {
    return t.checkExpect(this.ship3.reduceSpeed(20), 45) &&
        t.checkExpect(this.ship3.reduceSpeed(50), 30); 
  }


  //tests for moving spaceship
  boolean testMove(Tester t) {
    return t.checkExpect(this.ship1.move(10, 20), new Spaceship(new Location(40,60), "red", 60)) &&
        t.checkExpect(this.invader1.move(2, 1), new Invader(new Location(32, 41), "black", 100, 30)) &&
        t.checkExpect(this.loc3040.movePosition(1, -2), new Location(31, 38));
  }



  //draw the spaceship
  boolean testDrawing(Tester t) {
    WorldCanvas c = new WorldCanvas(300, 500);
    WorldImage img = this.ship1.draw();
    WorldScene s = new WorldScene(300, 500).placeImageXY(img, 150, 150);
    c.show();
    c.drawScene(s);
    return true;
  }

}
