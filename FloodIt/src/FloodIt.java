import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import tester.*;
import java.awt.Color;
import javalib.worldimages.*;
import javalib.impworld.*;
//FloodIt.Java Finished Part 1 
//Part 1: Display and Initialize Board with random colors.
//Dante LoPriore and Jake Simeone

//Represents a single square of the game area
class Cell {
  static int CELL_SIZE = 100;
  // In logical coordinates, with the origin at the top-left corner of the screen
  int x;
  int y;
  Color color;
  boolean floodedCells;

  // the four adjacent cells to this one
  Cell left;
  Cell top;
  Cell right;
  Cell bottom;

  //default constructor
  Cell(int x, int y, Color color, boolean floodedCells) {
    this.x = x;
    this.y = y;
    this.color = color;
    this.floodedCells = floodedCells;
    this.left = null;
    this.top = null;
    this.right = null;
    this.bottom = null;
  }

  //the convenience constructor which represents the four adjacent cells
  Cell(Cell left, Cell top, Cell right, Cell bottom) {
    this.left = left;
    this.top = top;
    this.right = right;
    this.bottom = bottom;
  }

  //to draw a single cell
  public WorldImage drawCell() {
    return new RectangleImage(CELL_SIZE, CELL_SIZE, OutlineMode.SOLID, this.color);
  }

  //to set the coordinates to represent each of the four adjacent cells.
  public void setCell(Cell left, Cell right, Cell top, Cell bottom) {
    // TODO Auto-generated method stub
    this.left = left;
    this.right = right;
    this.top = top;
    this.bottom = bottom;
  }

  /*
   * public void floodEffect(Color c, ArrayList<Cell> floodedCells) {
   * 
   * }
   */
}

//to represent a flood it world
class FloodItWorld extends World {
  static double TICKRATE = 1.0 / 28.0; // seconds per frame.
  static int SCREENWIDTH = 500;
  static int SCREENHEIGHT = 500;
  static Color FONTCOLOR = Color.BLACK;
  static int FONTSIZE = 13;
  static int BOARD_SIZE = 220;
  static ArrayList<Color> COLOR_SCENE = new ArrayList<Color>(Arrays.asList(Color.PINK, Color.YELLOW,
      Color.CYAN, Color.RED, Color.YELLOW, Color.BLUE, Color.GREEN, Color.ORANGE));
  static ArrayList<Color> COLOR_SCENE2 = new ArrayList<Color>(Arrays.asList(Color.PINK,
      Color.YELLOW, Color.CYAN, Color.RED, Color.YELLOW, Color.BLUE, Color.GREEN, Color.ORANGE));
  static ArrayList<ArrayList<Color>> OVERALL_COLORSCENE = new ArrayList<ArrayList<Color>>(
      Arrays.asList(COLOR_SCENE, COLOR_SCENE2));

  // All the cells of the game
  ArrayList<ArrayList<Cell>> board;
  ArrayList<Cell> floodTheCells;
  Color floodColor;
  int score;
  int amountTotalMoves;
  int currentMoves;

  //default constructor
  FloodItWorld(ArrayList<ArrayList<Cell>> board, Color floodColor, int score, int amountTotalMoves,
      int currentMoves) {
    this.board = board;
    this.floodColor = floodColor;
    this.score = score;
    this.amountTotalMoves = amountTotalMoves;
    this.currentMoves = currentMoves;
  }

  //Convenience constructor
  FloodItWorld() {
    this.createGameBoard();
  }

  //to create a game board and connect the adjacent cells together in a game board
  public void createGameBoard() {
    this.board = new ArrayList<ArrayList<Cell>>();
    for (int i = 0; BOARD_SIZE > i; i++) {
      this.board.add(new ArrayList<Cell>());
      for (int j = 0; BOARD_SIZE > j; j++) {
        if (i == 0 & j == 0) {
          this.board.get(i).add(new Cell(i, j, this.createColorScene(), true));
        }
        this.board.get(i).add(new Cell(i, j, this.createColorScene(), false));
      }
    }
    for (int x = 0; BOARD_SIZE > x; x++) {
      for (int y = 0; BOARD_SIZE > y; y++) {
        Cell gameCell = board.get(x).get(y);
        Cell left = null;
        Cell right = null;
        Cell top = null;
        Cell bottom = null;
        //handle null value 
        if (BOARD_SIZE - 1 > x) {
          right = board.get(x + 1).get(y);
        }
        //handle null value 
        if (x > 0) {
          left = board.get(x - 1).get(y);
        }
        //handle null value 
        if (BOARD_SIZE - 1 > y) {
          bottom = board.get(x).get(y + 1);
        }
        //handle null value 
        if (y > 0) {
          top = board.get(x).get(y - 1);
        }
        gameCell.setCell(left, right, top, bottom);
      }
    }
  }

  //to draw a board for the cells to be on.
  public WorldImage drawBoard() {

    WorldImage boardGame = new EmptyImage();

    for (ArrayList<Cell> arrayC : this.board) {
      WorldImage rowImage = new EmptyImage();
      for (Cell c : arrayC) {
        rowImage = new BesideImage(rowImage, c.drawCell());
      }
      boardGame = new AboveImage(boardGame, rowImage);
    }
    return boardGame;
  }

  /*
   * public FloodItWorld onTick() { return ; }
   */

  /*
   * //to initialize and refresh the game board public void onKeyEvent(String key)
   * { if (key.equals("r")) { //take in arraylist of arraylist of cells this.board
   * = this.createGameBoard(); } }
   */

  /*
   * //to initialize and refresh the game board public void pressMouseEvent(Posn
   * p) { if (true) { return; } int x = p.x / Cell.CELL_SIZE; int y = p.y /
   * Cell.CELL_SIZE;
   * 
   * if (this.board.size() > (x - 1 + y * BOARD_SIZE) && x > 0 && y > 0) {
   * 
   * } }
   */

  //to produce a random color
  public Color createColorScene() {
    // TODO Auto-generated method stub
    Random rando = new Random();
    return COLOR_SCENE.get(rando.nextInt(COLOR_SCENE.size()));
  }

  //to determine if the board is all flooded 
  boolean checkWinGame() {
    for (ArrayList<Cell> arrayC : this.board) {
      for (Cell c : arrayC) {
        if (this.floodColor.equals(c.color)) {
          return true;
        }
      }
    }
    return false;
  }

  //to check to see if the the game is over and finished
  boolean checkGameOver() {
    // TODO Auto-generated method stub
    return 0 == this.amountTotalMoves - this.currentMoves;
  }

  //to produce the loser end scene
  WorldScene makeLoseCutScene() {
    // TODO Auto-generated method stub
    WorldScene endScene = new WorldScene(SCREENWIDTH, SCREENHEIGHT);
    endScene.placeImageXY(new TextImage(
        "Game Over and You Lose: Your Overall Score is: " + Integer.toString(this.score),
        Color.red), 250, 250);
    return endScene;
  }

  //to produce the winner end scene
  WorldScene makeWinCutScene() {
    // TODO Auto-generated method stub
    WorldScene endScene = new WorldScene(SCREENWIDTH, SCREENHEIGHT);
    endScene.placeImageXY(
        new TextImage("Victory Royale: Your Overall Score is: " + Integer.toString(this.score),
            Color.red),
        250, 250);
    return endScene;
  }

  // Check to see if we need to end the game.
  public WorldEnd worldEnds() {
    if (this.checkWinGame()) {
      return new WorldEnd(true, this.makeWinCutScene());
    }
    else if (this.checkGameOver()) {
      return new WorldEnd(false, this.makeLoseCutScene());
    }
    else {
      return new WorldEnd(false, this.makeScene());
    }
  }

  //makes the scene for the overall world
  public WorldScene makeScene() {
    WorldScene scene = new WorldScene(SCREENWIDTH / 2, SCREENHEIGHT / 2);
    scene.placeImageXY(this.drawBoard(), FloodItWorld.SCREENWIDTH, FloodItWorld.SCREENHEIGHT);
    return scene;
  }
}

//to represent examples and tests for FloodIt.java
class ExamplesFloodIt {

  //Examples of WorldScenes
  WorldScene scene;

  //Examples of Random
  Random rand;
  Random rand1;
  Random rand2;
  Random rand3;

  //Examples of Cells
  Cell c1;
  Cell c2;
  Cell c3;
  Cell c4;
  Cell centerCell;
  Cell c5;
  Cell c6;
  Cell c7;
  Cell c8;
  Cell centerCell2;
  Cell c9;
  Cell c10;
  Cell c11;
  Cell c12;
  Cell c13;
  Cell c14;
  Cell c15;
  Cell c16;

  //Examples of ArrayList<Cell>
  ArrayList<Cell> a2Cells;
  ArrayList<Cell> aCells;
  ArrayList<Cell> aCells2;
  ArrayList<Cell> aCells3;
  ArrayList<Cell> aCells4;

  //Examples of ArrayList<ArrayList<Cell>>
  ArrayList<ArrayList<Cell>> arrayCells;
  ArrayList<ArrayList<Cell>> array2Cells;
  ArrayList<ArrayList<Cell>> array3Cells;

  //Examples of FloodItWorld
  FloodItWorld world;
  FloodItWorld world1;

  //to represent the initial data
  void initData() {
    //Examples of WorldScenes
    this.scene = new WorldScene(FloodItWorld.SCREENWIDTH, FloodItWorld.SCREENHEIGHT);

    //Examples of FloodItWorld
    this.world = new FloodItWorld(this.arrayCells, Color.RED, 1, 10, 1);
    this.world1 = new FloodItWorld(null, Color.RED, 1, 10, 1);

    //Examples of Random
    this.rand = new Random(37);
    this.rand2 = new Random(37);
    this.rand3 = new Random(12);

    //Examples of Cells
    this.c1 = new Cell(0, 0, this.world.createColorScene(), true);
    this.c2 = new Cell(1, 0, this.world.createColorScene(), false);
    this.c3 = new Cell(2, 0, this.world.createColorScene(), false);
    this.c4 = new Cell(0, 1, this.world.createColorScene(), false);
    this.centerCell = new Cell(c1, c2, c3, c4);
    this.c5 = new Cell(0, 2, this.world.createColorScene(), false);
    this.c6 = new Cell(1, 1, this.world.createColorScene(), false);
    this.c7 = new Cell(2, 2, this.world.createColorScene(), false);
    this.c8 = new Cell(2, 2, this.world.createColorScene(), false);
    this.centerCell2 = new Cell(c5, c6, c7, c8);
    this.c9 = new Cell(0, 0, this.world.createColorScene(), false);
    this.c10 = new Cell(4, 0, this.world.createColorScene(), false);
    this.c11 = new Cell(3, 3, this.world.createColorScene(), false);
    this.c12 = new Cell(0, 4, this.world.createColorScene(), false);
    this.c13 = new Cell(4, 0, this.world.createColorScene(), false);
    this.c14 = new Cell(4, 4, this.world.createColorScene(), false);
    this.c15 = new Cell(0, 5, this.world.createColorScene(), false);
    this.c16 = new Cell(5, 0, this.world.createColorScene(), false);

    //Examples of ArrayList<Cell>
    this.a2Cells = new ArrayList<Cell>(Arrays.asList(centerCell, centerCell2));
    this.aCells = new ArrayList<Cell>(Arrays.asList(c1, c2, c3, c4));
    this.aCells2 = new ArrayList<Cell>(Arrays.asList(c5, c6, c7, c8));
    this.aCells3 = new ArrayList<Cell>(Arrays.asList(c9, c10, c11, c12));
    this.aCells4 = new ArrayList<Cell>(Arrays.asList(c13, c14, c15, c16));

    //Examples of ArrayList<ArrayList<Cell>>
    this.arrayCells = new ArrayList<ArrayList<Cell>>(
        Arrays.asList(aCells, aCells2, aCells3, aCells4));
    this.array2Cells = new ArrayList<ArrayList<Cell>>(Arrays.asList(this.a2Cells));
    this.array3Cells = new ArrayList<ArrayList<Cell>>(Arrays.asList(aCells, aCells2));

    //Examples of FloodItWorld
    this.world = new FloodItWorld(this.arrayCells, Color.RED, 1, 10, 1);
    this.world1 = new FloodItWorld(null, Color.RED, 1, 10, 1);
  }

  //to test the big bang and game's overall functionality.
  FloodItWorld testBigBang(Tester t) {
    // width, height, tickrate = 0.5 means every 0.5 seconds the onTick method will
    // get called.
    this.initData();
    FloodItWorld world = new FloodItWorld(this.arrayCells, Color.RED, 0, 10, 10);
    world.bigBang(FloodItWorld.SCREENWIDTH, FloodItWorld.SCREENHEIGHT, FloodItWorld.TICKRATE);
    return world;
  }

  //to test CreateColorScene method 
  boolean testCreateColorScene(Tester t) {
    this.initData();
    Color col1 = this.world.createColorScene();
    Color col2 = this.world1.createColorScene();
    return t.checkOneOf(col1, Color.PINK, Color.YELLOW, Color.CYAN, Color.RED, Color.YELLOW,
        Color.BLUE, Color.GREEN, Color.ORANGE)
        && t.checkOneOf(col2, Color.PINK, Color.YELLOW, Color.CYAN, Color.RED, Color.YELLOW,
            Color.BLUE, Color.GREEN, Color.ORANGE);
  }

  //to test MakeScene method 
  boolean testMakeScene(Tester t) {
    this.initData();
    WorldScene scene = new WorldScene(FloodItWorld.SCREENWIDTH, FloodItWorld.SCREENHEIGHT);
    scene.placeImageXY(this.world.drawBoard(), FloodItWorld.SCREENWIDTH / 2,
        FloodItWorld.SCREENHEIGHT / 2);
    return t.checkExpect(this.world.makeScene(), scene);
  }

  //to test checkWinGame method 
  boolean testCheckWinGame(Tester t) {
    this.initData();
    return t.checkOneOf(this.world.checkWinGame(), true, false);
  }

  //to test worldEnds method 
  boolean testWorldEnds(Tester t) {
    this.initData();
    return t.checkExpect(this.world.worldEnds(), new WorldEnd(true, this.world.makeWinCutScene()));
  }

  //to test MakeLoseCutScene method 
  boolean testMakeLoseCutScene(Tester t) {
    this.initData();
    this.scene.placeImageXY(
        new TextImage("Game Over and You Lose: Your Overall Score is: " + Integer.toString(1),
            Color.red),
        250, 250);
    return t.checkExpect(this.world.makeLoseCutScene(), this.scene);
  }

  //to test MakeWinCutScene method 
  boolean testMakeWinCutScene(Tester t) {
    this.initData();
    WorldScene scene = new WorldScene(FloodItWorld.SCREENWIDTH, FloodItWorld.SCREENHEIGHT);
    scene.placeImageXY(
        new TextImage("Victory Royale: Your Overall Score is: " + Integer.toString(1), Color.red),
        250, 250);
    return t.checkExpect(this.world.makeWinCutScene(), scene);
  }

  //to test Draw Board method 
  boolean testDrawBoard(Tester t) {
    this.initData();
    return t.checkExpect(this.world.drawBoard(), this.world.drawBoard());
  }

  //to test checkGameOver method 
  boolean testCheckGameOver(Tester t) {
    this.initData();
    return t.checkExpect(this.world.checkGameOver(), false);
  }

  //to test the createGameBoard method
  void testCreateGameBoard(Tester t) {
    this.initData();
    world.createGameBoard();
    world1.createGameBoard();
    ArrayList<ArrayList<Cell>> board = world.board;
    t.checkExpect(board.size(), 220);
    t.checkExpect(world.board.equals(board), true);

  }

  //to test drawCell method
  void testDrawCell(Tester t) {
    initData();
    t.checkExpect(c1.drawCell(),
        new RectangleImage(c1.CELL_SIZE, c1.CELL_SIZE, OutlineMode.SOLID, c1.color));
    t.checkExpect(c2.drawCell(),
        new RectangleImage(c2.CELL_SIZE, c2.CELL_SIZE, OutlineMode.SOLID, c2.color));
    t.checkExpect(c3.drawCell(),
        new RectangleImage(c3.CELL_SIZE, c3.CELL_SIZE, OutlineMode.SOLID, c3.color));

  }

  //to test setCell method
  void testSetCell(Tester t) {
    initData();
    c1.setCell(c5, c4, c3, c1);
    t.checkExpect(c1.left, c5);
    c5.setCell(c1, c2, c3, c4);
    t.checkExpect(c5.right, c2);

  }
}
