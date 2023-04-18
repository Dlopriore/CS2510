import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import tester.*;
import java.awt.Color;
import javalib.worldimages.*;
import javalib.impworld.*;
//FloodIt.Java
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

  //default constructors
  Cell(int x, int y, Color color, boolean floodedCells) {
    this.x = x;
    this.y = y;
    Random rando = new Random();
    this.color = FloodItWorld.COLOR_SCENE.get(rando.nextInt(FloodItWorld.COLOR_SCENE.size()));
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
}

//to represent a flood it world
class FloodItWorld extends World {
  static double TICKRATE = 0.000003; // seconds per frame.
  static int SCREENWIDTH = 500;
  static int SCREENHEIGHT = 500;
  static Color FONTCOLOR = Color.BLACK;
  static int FONTSIZE = 13;
  static int BOARD_SIZE = 4;
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
  public ArrayList<ArrayList<Cell>> createGameBoard() {
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
    for (int y = 0; BOARD_SIZE > y; y++) {
      for (int x = 0; BOARD_SIZE > x; x++) {
        Cell gameCell = board.get(y).get(x);
        Cell left = null;
        Cell right = null;
        Cell top = null;
        Cell bottom = null;
        //handle null value 
        if (BOARD_SIZE - 1 > y) {
          right = board.get(y + 1).get(x);
        }
        //handle null value 
        if (y > 0) {
          left = board.get(y - 1).get(x);
        }
        //handle null value 
        if (BOARD_SIZE - 1 > x) {
          bottom = board.get(y).get(x + 1);
        }
        //handle null value 
        if (x > 0) {
          top = board.get(y).get(x - 1);
        }
        gameCell.setCell(left, right, top, bottom);
      }
    }
    return this.board;
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

  //Effect: to flood the cells that are to be flooded 
  //and create the waterfall effect of the flooding cells
  public void onTick() {
    attemptingFlood();
  }

  //Effect: to flood the cells that are true to be flooded and change the cells flood states 
  public void attemptingFlood() {
    // TODO Auto-generated method stub
    Color c = this.board.get(0).get(0).color;
    for (ArrayList<Cell> aC : this.board) {
      for (Cell cel : aC) {
        if (cel.floodedCells) {
          cel.color = c;
          if (cel.left != null && !(cel.left.floodedCells) && cel.left.color.equals(c)) {
            cel.left.floodedCells = true;
          }
          if (cel.right != null && !(cel.right.floodedCells) && cel.right.color.equals(c)) {
            cel.right.floodedCells = true;
          }
          if (cel.top != null && !(cel.top.floodedCells) && cel.top.color.equals(c)) {
            cel.top.floodedCells = true;
          }
          if (cel.bottom != null && !(cel.bottom.floodedCells) && cel.bottom.color.equals(c)) {
            cel.bottom.floodedCells = true;
          }
        }
      }
    }
  }

  //to initialize and refresh the game board 
  public void onKeyEvent(String key) {
    if (key.equals("r")) {
      //take in arraylist of arraylist of cells
      this.board = this.createGameBoard();
      this.score = 0;
      this.currentMoves = 0;
    }
  }

  //Mouse pressed event at the given position by the mouse click
  //Effect: to check to see if it possible to flood a Cell and 
  //true location based on the user's mouse click 
  public void onMouseClicked(Posn p) {
    int x;
    int y;

    if (p.x > this.board.get(0).size() * Cell.CELL_SIZE) {
      x = this.board.get(0).size() - 1;
    }
    else {
      x = p.x / Cell.CELL_SIZE;
    }
    if (p.y > this.board.get(0).size() * Cell.CELL_SIZE) {
      y = this.board.get(0).size() - 1;
    }
    else {
      y = p.y / Cell.CELL_SIZE;
    }

    this.floodColor = this.board.get(y).get(x).color;
    if (x >= 0 && y >= 0 && this.board.size() > x && this.board.size() > y
        && this.board.size() > y + x * Math.sqrt(this.board.size())) {
      this.floodEffect(this.board.get(y).get(x));
    }
    this.score = score + 1;
    this.currentMoves = currentMoves + 1;
  }

  //Effect: flood the board if the cell's given color is different 
  //that the origin cell's color and the existing cell's flood color
  public void floodEffect(Cell cell) {
    // TODO Auto-generated method stub
    this.floodColor = cell.color;

    for (int i = 0; this.board.size() > i; i++) {
      for (int j = 0; this.board.size() > j; j++) {
        if (this.board.get(i).get(j).floodedCells) {
          this.board.get(i).get(j).color = this.floodColor;
          cell.floodedCells = true;
        }
      }
    }
  }

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
        if (!(this.floodColor.equals(c.color))) {
          return false;
        }
      }
    }
    return true;
  }

  // Check to see if we need to end the game.
  public WorldEnd worldEnds() {
    if (this.checkWinGame()) {
      return new WorldEnd(true, this.makeWinCutScene());
    }
    if (this.checkGameOver()) {
      return new WorldEnd(true, this.makeLoseCutScene());
    }
    else {
      return new WorldEnd(false, this.makeScene());
    }
  }

  //to check to see if the the game is over and finished
  boolean checkGameOver() {
    // TODO Auto-generated method stub
    return this.amountTotalMoves == this.currentMoves;
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

  //to create the image that displays the score
  public WorldImage drawScore() {
    // TODO Auto-generated method stub
    return new TextImage(
        "Your Current Score is: " + Integer.toString(this.score) + "  Amount of Moves Left: "
            + Integer.toString(this.currentMoves) + " / " + Integer.toString(this.amountTotalMoves),
        Color.BLACK);
  }

  //makes the scene for the overall world
  public WorldScene makeScene() {
    WorldScene scene = new WorldScene(SCREENWIDTH, SCREENHEIGHT);
    scene.placeImageXY(new AboveImage(this.drawBoard(), this.drawScore()), SCREENWIDTH / 2,
        SCREENHEIGHT / 2);
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
  FloodItWorld world2;

  void initData() {
    //Examples of WorldScenes
    this.scene = new WorldScene(FloodItWorld.SCREENWIDTH, FloodItWorld.SCREENHEIGHT);

    //Examples of FloodItWorld
    this.world = new FloodItWorld(this.arrayCells, Color.RED, 1, 10, 1);
    this.world1 = new FloodItWorld(null, Color.RED, 1, 10, 1);
    this.world2 = new FloodItWorld(this.arrayCells, Color.RED, 1, 10, 1);

    //Examples of Random
    this.rand = new Random(37);
    this.rand2 = new Random(37);
    this.rand3 = new Random(12);

    //Examples of Cells
    this.c1 = new Cell(0, 0, this.world.createColorScene(), true);
    this.c2 = new Cell(1, 0, this.world.createColorScene(), false);
    this.c3 = new Cell(2, 0, this.world.createColorScene(), false);
    this.c4 = new Cell(3, 0, this.world.createColorScene(), false);
    this.centerCell = new Cell(c1, c2, c3, c4);
    this.c5 = new Cell(0, 1, this.world.createColorScene(), false);
    this.c6 = new Cell(1, 1, this.world.createColorScene(), false);
    this.c7 = new Cell(2, 1, this.world.createColorScene(), false);
    this.c8 = new Cell(3, 1, this.world.createColorScene(), false);
    this.centerCell2 = new Cell(c5, c6, c7, c8);
    this.c9 = new Cell(0, 2, this.world.createColorScene(), false);
    this.c10 = new Cell(1, 2, this.world.createColorScene(), false);
    this.c11 = new Cell(2, 2, this.world.createColorScene(), false);
    this.c12 = new Cell(3, 2, this.world.createColorScene(), false);
    this.c13 = new Cell(0, 3, this.world.createColorScene(), false);
    this.c14 = new Cell(1, 3, this.world.createColorScene(), false);
    this.c15 = new Cell(2, 3, this.world.createColorScene(), false);
    this.c16 = new Cell(3, 3, this.world.createColorScene(), false);

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
    this.world = new FloodItWorld(this.arrayCells, Color.RED, 0, 10, 0);
    this.world1 = new FloodItWorld(null, Color.RED, 10, 10, 0);
  }

  //to test the big bang and game's overall functionality.
  FloodItWorld testBigBang(Tester t) {
    this.initData();
    FloodItWorld world = new FloodItWorld(this.arrayCells, Color.RED, 0, 10, 0);
    int realsize = ((world.board.get(0).size() + 1) * Cell.CELL_SIZE);
    // width, height, tickrate = 0.5 means every 0.5 seconds the onTick method will
    // get called.
    world.bigBang(realsize, realsize, FloodItWorld.TICKRATE);
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
    scene.placeImageXY(new AboveImage(this.world.drawBoard(), this.world.drawScore()),
        FloodItWorld.SCREENWIDTH / 2, FloodItWorld.SCREENHEIGHT / 2);
    return t.checkExpect(this.world.makeScene(), scene);
  }

  //to test checkWinGame method 
  boolean testCheckWinGame(Tester t) {
    this.initData();
    return t.checkExpect(this.world.checkWinGame(), false);
  }

  //to test Gameover method 
  boolean testCheckGameOver(Tester t) {
    this.initData();
    return t.checkExpect(this.world.checkGameOver(), false);
  }

  // tests for worldEnds() method
  boolean testWorldEnds(Tester t) {
    return t.checkExpect(this.world.worldEnds(), new WorldEnd(false, this.world.makeScene()));
  }

  //to test MakeLoseCutScene method 
  boolean testMakeLoseCutScene(Tester t) {
    this.initData();
    this.scene.placeImageXY(
        new TextImage("Game Over and You Lose: Your Overall Score is: " + Integer.toString(0),
            Color.red),
        250, 250);
    return t.checkExpect(this.world.makeLoseCutScene(), this.scene);
  }

  //to test MakeWinCutScene method 
  boolean testMakeWinCutScene(Tester t) {
    this.initData();
    WorldScene scene = new WorldScene(FloodItWorld.SCREENWIDTH, FloodItWorld.SCREENHEIGHT);
    scene.placeImageXY(
        new TextImage("Victory Royale: Your Overall Score is: " + Integer.toString(0), Color.red),
        250, 250);
    return t.checkExpect(this.world.makeWinCutScene(), scene);
  }

  //to test Draw Board method 
  boolean testDrawBoard(Tester t) {
    this.initData();
    return t.checkExpect(this.world.drawBoard(), this.world.drawBoard());
  }

  // to test onKey event
  boolean testonKeyEvent(Tester t) {
    this.initData();

    this.world.onKeyEvent("r");

    return t.checkExpect(this.world.createGameBoard().size(), 4)
        && t.checkExpect(this.world.equals(this.world), true);
  }

  //to test drawCell method
  boolean testDrawCell(Tester t) {
    this.initData();
    WorldImage im1 = c1.drawCell();
    WorldImage im2 = new RectangleImage(c1.CELL_SIZE, c1.CELL_SIZE, OutlineMode.SOLID, c1.color);
    return t.checkExpect(im1, im2);
  }

  //to test setCell method
  void testSetCell(Tester t) {
    initData();
    c1.setCell(c5, c4, c3, c1);
    t.checkExpect(c1.left, c5);
    c5.setCell(c1, c2, c3, c4);
    t.checkExpect(c5.right, c2);

  }

  //to test the createGameBoard method
  boolean testCreateGameBoard(Tester t) {
    this.initData();
    world.createGameBoard();
    world1.createGameBoard();
    ArrayList<ArrayList<Cell>> board = world.board;
    return t.checkExpect(board.size(), 4) && t.checkExpect(world.board.equals(board), true);

  }

  //to test the AttemptingFlood method
  void testAttempingFlood(Tester t) {
    this.initData();
    c1.setCell(null, c2, null, c5);
    c1.color = Color.RED;
    c2.color = Color.RED;
    t.checkExpect(c2.floodedCells, false);
    this.world.attemptingFlood();
    t.checkExpect(c2.floodedCells, true);
  }

  //to test the floodEffect method
  void testFloodEffect(Tester t) {
    this.initData();
    c2.color = Color.RED;
    this.world.floodEffect(c2);
    c1.color = Color.BLUE;
    this.world.floodEffect(c2);
    t.checkExpect(c2.floodedCells, true);
    t.checkExpect(c2.color, Color.RED);
    t.checkExpect(c1.floodedCells, true);
    t.checkExpect(c1.color, Color.RED);
  }

  //to test the onMousedClicked method
  void testOnMouseClicked(Tester t) {
    this.initData();
    c1.setCell(null, c2, null, c5);
    c1.color = Color.RED;
    c2.color = Color.BLUE;
    t.checkExpect(world.board.get(0).get(1).floodedCells, false);
    this.world.onMouseClicked(new Posn(100, 0));
    t.checkExpect(world.board.get(0).get(1).floodedCells, true);

  }
}
