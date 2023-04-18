import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import tester.*;
import javalib.impworld.*;
import java.awt.Color;
import javalib.worldimages.*;

//Represents a single square of the game area
class Cell {
  // In logical coordinates, with the origin at the top-left corner of the screen
  int x;
  int y;
  Color color;
  ArrayList<Cell> floodedCells;

  Cell(int x, int y, Color color, ArrayList<Cell> floodedCells) {
    this.x = x;
    this.y = y;
    this.color = color;
    this.floodedCells = floodedCells;
  }

  public WorldImage drawCell() {
    WorldImage boardGame = new RectangleImage(FloodItWorld.BOARD_SIZE, FloodItWorld.BOARD_SIZE,
        OutlineMode.SOLID, Color.BLACK);
    return new OverlayOffsetAlign(AlignModeX.LEFT, AlignModeY.TOP,
        new RectangleImage(1, 1, OutlineMode.SOLID, this.color), this.x, this.y, boardGame);
  }

  /*
   * public void floodEffect(Color c, ArrayList<Cell> floodedCells) {
   * 
   * }
   */
}

class FloodItWorld extends World {
  static double TICKRATE = 1.0 / 28.0; // seconds per frame.
  static int SCREENWIDTH = 50;
  static int SCREENHEIGHT = 50;
  static Color FONTCOLOR = Color.BLACK;
  static int FONTSIZE = 13;
  static ArrayList<Color> COLOR_SCENE = new ArrayList<Color>(Arrays.asList(Color.PINK, Color.YELLOW,
      Color.CYAN, Color.RED, Color.YELLOW, Color.BLUE, Color.GREEN, Color.ORANGE));
  static ArrayList<Color> COLOR_SCENE2 = new ArrayList<Color>(Arrays.asList(Color.PINK,
      Color.YELLOW, Color.CYAN, Color.RED, Color.YELLOW, Color.BLUE, Color.GREEN, Color.ORANGE));

  static ArrayList<ArrayList<Color>> OVERALL_COLORSCENE = new ArrayList<ArrayList<Color>>(
      Arrays.asList(COLOR_SCENE, COLOR_SCENE2));
  // All the cells of the game
  ArrayList<Cell> board;
  Color floodColor;
  int score;
  int amountTotalMoves;
  int currentMoves;
  static int BOARD_SIZE = 22;

  FloodItWorld(ArrayList<Cell> board, Color floodColor, int score, int amountTotalMoves,
      int currentMoves) {
    this.board = board;
    this.floodColor = floodColor;
    this.score = score;
    this.amountTotalMoves = amountTotalMoves;
    this.currentMoves = currentMoves;
  }

  void createGameBoard(ArrayList<Color> colors) {
    for (int x = 0; BOARD_SIZE > x; x++) {
      for (int y = 0; BOARD_SIZE > y; y++) {
        ArrayList<Cell> connectingCells = new ArrayList<Cell>();
        if (this.checkLocation(x + 1, y)) {
          connectingCells.add(this.computeCell(x + 1, y));
        }
        if (this.checkLocation(x, y + 1)) {
          connectingCells.add(this.computeCell(x, y + 1));
        }
        if (this.checkLocation(x - 1, y)) {
          connectingCells.add(this.computeCell(x - 1, y));
        }
        if (this.checkLocation(x, y - 1)) {
          connectingCells.add(this.computeCell(x, y - 1));
        }
        this.board.add(new Cell(x, y, this.produceRandomColor(colors), connectingCells));
      }
    }
  }

  boolean checkLocation(int x, int y) {
    // TODO Auto-generated method stub
    return x >= 0 && y >= 0 && x <= this.board.size() && y <= this.board.size();
  }

  Cell computeCell(int x, int y) {
    // TODO Auto-generated method stub
    return this.board.get(x + y);
  }

  public WorldImage drawBoard() {
    WorldImage boardGame = new RectangleImage(BOARD_SIZE, BOARD_SIZE, OutlineMode.SOLID, FONTCOLOR);
    for (Cell c : this.board) {
      boardGame = c.drawCell();
    }
    return boardGame;
  }

  /*
   * public FloodItWorld onTick() { return null; }
   */

  public void onKeyEvent(String key) {
    if (key.equals("r")) {
      this.createGameBoard(this.createVariousColorScene());
    }
  }

  private ArrayList<Color> createVariousColorScene() {
    // TODO Auto-generated method stub
    Random rand = new Random();
    return OVERALL_COLORSCENE.get(rand.nextInt(OVERALL_COLORSCENE.size()));
  }

  public Color createColorScene() {
    // TODO Auto-generated method stub
    Random rand = new Random();
    return COLOR_SCENE.get(rand.nextInt(COLOR_SCENE.size()));
  }

  public Color produceRandomColor(ArrayList<Color> colors) {
    // TODO Auto-generated method stub
    Random rand = new Random();
    return colors.get(rand.nextInt(colors.size()));
  }

  boolean checkLoseCurrentGame() {
    for (Cell c : this.board) {
      if (this.floodColor.equals(c.color)) {
        return true;
      }
    }
    return false;
  }

  // Check to see if we need to end the game.
  public WorldEnd worldEnds() {
    if (!this.checkLoseCurrentGame()) {
      return new WorldEnd(true, this.makeWinCutScene());
    }
    else if (this.checkGameOver()) {
      return new WorldEnd(false, this.makeLoseCutScene());
    }
    else {
      return new WorldEnd(false, this.makeScene());
    }
  }

  boolean checkGameOver() {
    // TODO Auto-generated method stub
    return 0 == this.amountTotalMoves - this.currentMoves;
  }

  WorldScene makeLoseCutScene() {
    // TODO Auto-generated method stub
    WorldScene endScene = new WorldScene(SCREENWIDTH, SCREENHEIGHT);
    endScene.placeImageXY(new TextImage(
        "Game Over and You Lose: Your Overall Score is: " + Integer.toString(this.score),
        Color.red), 250, 250);
    return endScene;
  }

  WorldScene makeWinCutScene() {
    // TODO Auto-generated method stub
    WorldScene endScene = new WorldScene(SCREENWIDTH, SCREENHEIGHT);
    endScene.placeImageXY(
        new TextImage("Victory Royale: Your Overall Score is: " + Integer.toString(this.score),
            Color.red),
        30, 30);
    return endScene;
  }

  public WorldScene makeScene() {
    WorldScene scene = new WorldScene(SCREENWIDTH, SCREENHEIGHT);
    scene.placeImageXY(this.drawBoard(), 0, 0);
    return scene;
  }

  class ExamplesFlood {

    //Examples of worldScenes
    WorldScene scene = new WorldScene(FloodItWorld.SCREENWIDTH, FloodItWorld.SCREENHEIGHT);

    Cell c1 = new Cell(0, 0, Color.BLUE, new ArrayList<Cell>());
    Cell c2 = new Cell(1, 0, Color.RED, new ArrayList<Cell>());
    Cell c3 = new Cell(2, 0, Color.BLUE, new ArrayList<Cell>());
    Cell c4 = new Cell(0, 1, Color.RED, new ArrayList<Cell>());
    Cell c5 = new Cell(0, 2, Color.BLUE, new ArrayList<Cell>());
    Cell c6 = new Cell(1, 1, Color.RED, new ArrayList<Cell>());
    Cell c7 = new Cell(2, 2, Color.BLUE, new ArrayList<Cell>());

    ArrayList<Cell> aCells = new ArrayList<Cell>(Arrays.asList(c1, c2, c3, c4, c5, c6, c7));

    //to test the big bang and game's overall functionality.
    FloodItWorld testBigBang(Tester t) {
      // width, height, tickrate = 0.5 means every 0.5 seconds the onTick method will
      // get called.
      FloodItWorld world = new FloodItWorld(this.aCells, Color.BLACK, 0, 10, 1);
      world.bigBang(FloodItWorld.SCREENWIDTH, FloodItWorld.SCREENHEIGHT, FloodItWorld.TICKRATE);
      return world;
    }
  }
}