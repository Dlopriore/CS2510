import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import javalib.funworld.WorldScene;
import javalib.worldimages.EmptyImage;
import javalib.worldimages.OutlineMode;
import javalib.worldimages.OverlayOffsetImage;
import javalib.worldimages.RectangleImage;
import javalib.worldimages.WorldImage;
import tester.Tester;
//Fifteen.Java Lab Nine
//Dante LoPriore

//a predicate which checks to see if given integer is the same as the number 10
class SameNumber implements Predicate<Integer> {

  @Override
  public boolean test(Integer t) {
    // TODO Auto-generated method stub
    return t == 10;
  }
}

//a predicate which checks to see if given string is the same as the word a
class SameString implements Predicate<String> {

  @Override
  public boolean test(String t) {
    // TODO Auto-generated method stub
    return t.equals("a");
  }
}

//a predicate which checks to see if given char is the same as the character !
class SameChar implements Predicate<Character> {

  @Override
  public boolean test(Character t) {
    // TODO Auto-generated method stub
    return t.equals('!');
  }
}

//Represents an individual tile

class Tile {

  //The number on the tile.  Use 0 to represent the hole

  int value;

  //Draws this tile onto the background at the specified logical coordinates

  WorldImage drawAt(int col, int row, WorldImage background) {
    WorldImage whole = new EmptyImage();
    for (int r = row; r > 0; r = r - 1) {
      for (int c = col; c > 0; c = c - 1) {
        WorldImage tile = new RectangleImage(10, 10, OutlineMode.OUTLINE, Color.BLACK);
        whole = new OverlayOffsetImage(tile, col, row, background);
        return whole;
      }
    }
    return whole;
  }
}

class FifteenGame {
  //represents the rows of tiles

  ArrayList<ArrayList<Tile>> tiles;

  static double TICKRATE = 1.0 / 28.0; // seconds per frame.

  static int SCREENWIDTH = 500;

  static int SCREENHEIGHT = 500;

  static Color FONTCOLOR = Color.BLACK;

  static int FONTSIZE = 13;

  //draws the game

  public WorldScene makeScene() {
    WorldScene scene = new WorldScene(SCREENWIDTH, SCREENHEIGHT);
    scene.placeImageXY(this.drawAt(8, 8, new WorldImage(1, 1)), FifteenGame.SCREENWIDTH / 2,
        FifteenGame.SCREENHEIGHT / 2);
    return scene;

  }

  public void onKeyEvent(String k) {
    // needs to handle up, down, left, right to move the hole
    // extra: handle "u" to undo moves
    if (k.equals("u")) {
      this.drawboard();
    }
  }

  public WorldImage drawboard() {

  }

  // containing all the items of the given list that pass the predicate. 
  <T> ArrayList<T> filter(ArrayList<T> arrayL, Predicate<T> pred) {
    ArrayList<T> endResult = new ArrayList<T>();
    for (T t : arrayL) {
      if (pred.test(t)) {
        endResult.add(t);
      }
    }
    return endResult;
  }

  //that modifies the given list to remove everything that fails the predicate.
  <T> void removeExcept(ArrayList<T> arrayL, Predicate<T> pred) {
    for (int i = arrayL.size(); i >= 0; i = i - 1) {
      if (!(pred.test(arrayL.get(i)))) {
        arrayL.remove(i);
      }
    }
  }
}

//to represent the tests and examples for FifteenGame
class ExamplesFifteenGame {

  //Examples of ArrayLists
  ArrayList<String> arrayS1 = new ArrayList<String>(Arrays.asList("a", "b", "c"));
  ArrayList<Integer> arrayI1 = new ArrayList<Integer>(Arrays.asList(1, 2, 10));
  ArrayList<Character> arrayC1 = new ArrayList<Character>(Arrays.asList('w', '!', 's'));
  ArrayList<Character> arrayC2 = new ArrayList<Character>(Arrays.asList('1', '2', '3'));
  ArrayList<String> arrayS2;
  ArrayList<Integer> arrayI2;
  ArrayList<Character> arrayC3;

  FifteenGame fg;
  FifteenGame fg1;

  //to represent the initial data
  void initData() {
    this.arrayS2 = new ArrayList<String>(Arrays.asList("z", "y", "x"));
    this.arrayI2 = new ArrayList<Integer>(Arrays.asList(4, 34, 10));
    this.arrayC3 = new ArrayList<Character>(Arrays.asList('1', '1', '1'));
  }

  //to test the big bang and game's overall functionality.
  void testGame(Tester t) {
    FifteenGame g = new FifteenGame();
    g.bigBang(120, 120);

  }

  //tests for the filter methods
  boolean testFilter(Tester t) {
    return t.checkExpect(new FifteenGame().filter(arrayS1, new SameString()),
        new ArrayList<String>(Arrays.asList("a")))
        && t.checkExpect(new FifteenGame().filter(arrayI1, new SameNumber()),
            new ArrayList<Integer>(Arrays.asList(10)))
        && t.checkExpect(new FifteenGame().filter(arrayC1, new SameChar()),
            new ArrayList<Character>(Arrays.asList('!')))
        && t.checkExpect(new FifteenGame().filter(arrayC2, new SameChar()),
            new ArrayList<Character>(Arrays.asList()));
  }

  //tests for the removeExcept methods
  void testRemoveExcept(Tester t) {
    this.initData();
    this.fg = new FifteenGame().removeExcept(this.arrayS2, new SameString());
    this.fg1 = new FifteenGame().removeExcept(this.arrayS2, new SameString());
    t.checkExpect(new FifteenGame().removeExcept(this.arrayS2, new SameString()), this.arrayS2);
  }
}
