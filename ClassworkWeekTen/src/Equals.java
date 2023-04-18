import java.awt.Color;
import java.util.HashMap;

import tester.Tester;

class Posn {
  int x;
  int y;

  Posn(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public boolean equals(Object o) {
    if (o instanceof Posn) {
      Posn p = (Posn) o;
      return this.x == p.x && this.y == p.y;
    }
    else {
      return false;
    }
  }

  public int hashCode() {
    return this.x * 37 + this.y * 43;
  }

}

class ColorPosn extends Posn {
  String color;

  ColorPosn(int x, int y, String color) {
    super(x, y);
    this.color = color;
  }

  public boolean equals(Object o) {
    if (o instanceof ColorPosn) {
      ColorPosn p = (ColorPosn) o;
      return this.x == p.x && this.y == p.y && this.color.equals(p.color);
    }
    else {
      return false;
    }
  }

  public int hashCode() {
    return this.x * 37 + this.y * 43 + this.color.hashCode();
  }

}

class GamePiece {
  int score;
  String description;

  GamePiece(int score, String des) {
    this.score = score;
    this.description = des;
  }
}

class ExamplesPosns {
  Posn p1a = new Posn(3, 4);
  Posn p1b = new Posn(3, 4);
  Posn p1c = new Posn(3, 5);
  Posn p2 = new Posn(6, 8);
  ColorPosn cp = new ColorPosn(3, 4, "red");

  GamePiece spaceship = new GamePiece(100, "spaceship");
  GamePiece invader = new GamePiece(50, "invader");
  HashMap<Posn, GamePiece> gameDetails = new HashMap<Posn, GamePiece>();

  void init() {
    this.gameDetails.put(this.p1a, this.spaceship);
    this.gameDetails.put(this.p2, this.invader);

  }

  void testHash(Tester t) {

    this.init();
    t.checkExpect(this.gameDetails.get(this.p1a), this.spaceship);
    t.checkExpect(this.gameDetails.get(this.p1b), this.spaceship);
    t.checkExpect(this.p1b.equals(p1c), false);
    t.checkExpect(this.p1b.hashCode() == p1c.hashCode(), false);
    t.checkExpect(this.p1b.equals(p1a), true);
    t.checkExpect(this.p1b.hashCode() == p1a.hashCode(), true);

    t.checkExpect(p1a.equals(cp), false);
    t.checkExpect(cp.equals(p1a), false);

  }

}