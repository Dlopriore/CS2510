import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.Random;
import javalib.impworld.World;
import javalib.impworld.WorldScene;
import javalib.worldimages.OutlineMode;
import javalib.worldimages.RectangleImage;
import javalib.worldimages.TextImage;
import javalib.worldimages.WorldEnd;
import javalib.worldimages.WorldImage;
import tester.Tester;
//Maze.java
//Dante LoPriore and Jake Simeone 

interface ICollection<T> {
  //EFFECT: adds an item to the collection
  void add(T t);

  //EFFECT: removes an item from the collection
  //returns the item that was removed
  T remove();

  //returns the size of the worklist
  int size();
}

//to represent a Stack
class Stack<T> implements ICollection<T> {
  ArrayList<T> items;

  Stack() {
    this.items = new ArrayList<T>();
  }

  @Override
  public void add(T t) {
    this.items.add(t);
  }

  @Override
  public T remove() {
    return this.items.remove(0);
  }

  @Override
  public int size() {
    return this.items.size();
  }
}

//to represent a Queue
class Queue<T> implements ICollection<T> {
  ArrayList<T> items;

  Queue() {
    this.items = new ArrayList<T>();
  }

  @Override
  public void add(T t) {
    this.items.add(this.items.size(), t);
  }

  @Override
  public T remove() {
    return this.items.remove(0);
  }

  @Override
  public int size() {
    return this.items.size();
  }
}

//to represent a vertex
class Vertex {
  int x;
  int y;
  ArrayList<Edge> neighbors;
  ArrayList<Vertex> path;
  Color c;

  //constructor
  Vertex(int x, int y, ArrayList<Edge> neighbors) {
    this.x = x;
    this.y = y;
    this.neighbors = new ArrayList<Edge>();
    this.path = new ArrayList<Vertex>();
  }

  //constructor
  Vertex(int x, int y) {
    this.x = x;
    this.y = y;
    this.neighbors = new ArrayList<Edge>();
    this.path = new ArrayList<Vertex>();
    this.c = Color.GRAY;
  }

  //to add an edge on the vertex
  void addEdge(Edge e) {
    this.neighbors.add(e);
  }

  //return the representatives of Vertex's key
  Vertex find(HashMap<Vertex, Vertex> reps, Vertex cell1) {
    if (reps.get(cell1).equals(cell1)) {
      return cell1;
    }
    else {
      return this.find(reps, reps.get(cell1));
    }
  }

  public WorldImage fillIn() {
    return new RectangleImage(10, 10, OutlineMode.SOLID, Color.RED);

  }

  /*
   * //to override equals public boolean equals(Object o) { if (o instanceof
   * Vertex) { Vertex p = (Vertex) o; return this.x == p.x && this.y == p.y &&
   * this.neighbors.equals(p.neighbors) && this.c.equals(p.c); } else { return
   * false; } }
   * 
   * //to override hashCode public int hashCode() { return this.x * 3 + this.y * 5
   * + this.neighbors.hashCode() + this.c.hashCode(); }
   */
}

//to represent a user
class User {
  Vertex currentCell;

  //constructor
  User(Vertex currentCell) {
    this.currentCell = currentCell;
  }

  /*
   * //to override equals public boolean equals(Object o) { if (o instanceof User)
   * { User p = (User) o; return this.currentCell.equals(p.currentCell); } else {
   * return false; } }
   * 
   * //to override hashCode public int hashCode() { return
   * this.currentCell.hashCode(); }
   */
}

//to represent an edge
class Edge {
  Vertex from;
  Vertex to;
  int weight;

  //constructor
  Edge(Vertex from, Vertex to, int weight) {
    this.from = from;
    this.to = to;
    this.weight = weight;
  }

  //convenience constructor
  Edge(Vertex from, Vertex to) {
    this.from = from;
    this.to = to;
    this.weight = new Random().nextInt(100000);
  }

  //to draw the edges 
  WorldImage drawEdgeLine() {
    if ((this.from.x != this.to.x)) {
      if ((this.to.x - this.from.x) > 0) {
        return new RectangleImage(1, 10, OutlineMode.SOLID, Color.GRAY);
      }
      return new RectangleImage(1, -10, OutlineMode.SOLID, Color.GRAY);
    }
    if ((this.to.y - this.from.y) > 0) {
      return new RectangleImage(10, 1, OutlineMode.SOLID, Color.GRAY);
    }
    return new RectangleImage(-10, 1, OutlineMode.SOLID, Color.GRAY);
  }

  /*
   * //to override equals public boolean equals(Object o) { if (o instanceof
   * Vertex) { Edge p = (Edge) o; return this.weight == p.weight &&
   * this.from.equals(p.from) && this.to.equals(p.to); } else { return false; } }
   * 
   * //to override hashCode public int hashCode() { return this.weight * 56 +
   * this.from.hashCode() + this.to.hashCode(); }
   */
}

//to represent a MazeWorld
class MazeWorld extends World {

  static final int GAME_WIDTH = 100;
  static final int GAME_HEIGHT = 60;
  static final int SCREENWIDTH = 101;
  static final int SCREENHEIGHT = 61;
  static final int VERTEX_SPACE_SIZE = 10;
  static final double TICKRATE = 1.0 / 28.0; // seconds per frame.

  int score;
  User player;
  ArrayList<Edge> worklist;
  ArrayList<Edge> walls = new ArrayList<Edge>();
  ArrayList<Vertex> path = new ArrayList<Vertex>();
  ArrayList<Vertex> seen = new ArrayList<Vertex>();
  ArrayList<ArrayList<Vertex>> board;
  HashMap<Vertex, Integer> distances = new HashMap<Vertex, Integer>();
  HashMap<Vertex, Vertex> predecessors = new HashMap<Vertex, Vertex>();
  Stack<Vertex> dfs;
  Queue<Vertex> bfs;
  boolean depthFirst;
  boolean breadthForth;

  //constructor
  MazeWorld(int score, ArrayList<ArrayList<Vertex>> board) {
    this.score = score;
    this.board = board;
  }

  //to create the new game board for the maze
  public void createNewGameBoard() {
    // TODO Auto-generated method stub
    Vertex startingPosition = new Vertex(0, 0, new ArrayList<Edge>());
    player = new User(startingPosition);
    this.board = new ArrayList<ArrayList<Vertex>>();
    this.path = new ArrayList<Vertex>();
    this.worklist = new ArrayList<Edge>();
    for (int i = 0; i <= GAME_WIDTH; i = i + 1) {
      this.board.add(new ArrayList<Vertex>());
      for (int j = 0; j <= GAME_HEIGHT; j = j + 1) {
        this.board.get(i).add(new Vertex(i, j));
        predecessors.put(this.board.get(i).get(j), this.board.get(i).get(j));
      }
    }

    for (int i = 0; i < GAME_WIDTH; i = i + 1) {
      for (int j = 0; j < GAME_HEIGHT; j = j + 1) {
        Vertex collectionEdges = new Vertex(j, i, new ArrayList<Edge>());
        if ((this.board.get(i).get(j).y != GAME_HEIGHT)) {
          collectionEdges.addEdge(new Edge(this.board.get(i).get(j), this.board.get(i + 1).get(j),
              new Random().nextInt(100000)));
          this.worklist.add(new Edge(this.board.get(i).get(j), this.board.get(i + 1).get(j),
              new Random().nextInt(100000)));

        }
        if ((this.board.get(i).get(j).x != GAME_WIDTH)) {
          collectionEdges.addEdge(new Edge(this.board.get(i).get(j), this.board.get(i).get(j + 1),
              new Random().nextInt(100000)));
          this.worklist.add(new Edge(this.board.get(i).get(j), this.board.get(i).get(j + 1),
              new Random().nextInt(100000)));
        }
        player.currentCell = this.board.get(0).get(0);
        this.board.get(0).add(player.currentCell);
        this.board.get(i).add(collectionEdges);
      }
    }
    Comparator<Edge> compareEdges = ((e1, e2) -> e1.weight - e2.weight);
    this.worklist.sort(compareEdges);
    this.kruskalsAlgorithmSearch();
  }

  //Methods Need to be Done to create board
  //-Create Game Board
  //-Draw Game Board
  //-Create Vertex 
  //-Draw Vertex Image
  //-Link or Map the Vertex to Themselves using union 
  //-Create the Tree

  //////////////////////////////////////////////////////////
  ///////////////////////DRAW METHODS///////////////////////
  //////////////////////////////////////////////////////////

  //to create the image that displays the score
  public WorldImage drawScore() {
    // TODO Auto-generated method stub
    return new TextImage("Your Current Score is: " + Integer.toString(this.score), Color.BLACK);
  }

  ///////////////////////////////////////////////////////////////////////
  ///////////////////////ALGORITHIM SEARCH METHODS///////////////////////
  ///////////////////////////////////////////////////////////////////////

  //to represent the minimum spanning tree for Kruskals Algorithm
  //seen in the maze
  ArrayList<Edge> kruskalsAlgorithmSearch() {
    //check if every vertex in representatives hashmap is the value
    //get method to find key
    //find methods that check group between 1 vertex
    //initialize every node's representative to itself
    ArrayList<Edge> inTree = new ArrayList<Edge>();
    boolean testTree = inTree.size() < (this.board.get(0).size() * this.board.size() - 1);
    this.walls = new ArrayList<Edge>();
    ArrayList<Edge> worklistCop = new ArrayList<Edge>();
    worklistCop.addAll(this.worklist);

    while (testTree && worklistCop.size() > 0) {
      Edge edgeWall = worklistCop.get(0);
      if (edgeWall.from.find(predecessors,
          edgeWall.from) == (edgeWall.to.find(predecessors, edgeWall.to))) {
        worklistCop.remove(0);
      }
      else {
        this.walls.add(edgeWall);
        union(predecessors, edgeWall.from.find(predecessors, edgeWall.from),
            edgeWall.to.find(predecessors, edgeWall.to));
      }
    }
    return this.walls;
  }

  //to combine or union two Vertex's into the hashmap of representatives
  void union(HashMap<Vertex, Vertex> reps, Vertex cell1, Vertex cell2) {
    reps.put(cell1, cell2);
  }

  ArrayList<Vertex> cheapestPath(Vertex a, Vertex b) {
    ArrayList<Vertex> unvisited = new ArrayList<Vertex>();
    HashMap<Vertex, Integer> distances = new HashMap<Vertex, Integer>();
    HashMap<Vertex, Vertex> predecessors = new HashMap<Vertex, Vertex>();

    unvisited.add(a);
    distances.put(a, 0);

    while (unvisited.size() > 0) {
      Vertex v = unvisited.remove(0);

      for (Edge e : v.neighbors) {
        if (distances.get(e.to) == null || distances.get(e.to) > distances.get(v) + e.weight) {
          distances.put(e.to, distances.get(v) + e.weight);
          predecessors.put(e.to, v);
          unvisited.add(e.to);
        }
      }
    }

    ArrayList<Vertex> path = new ArrayList<Vertex>();

    if (distances.get(b) == null) {
      return path;
    }

    path.add(b);

    Vertex step = b;

    while (step != a) {
      step = predecessors.get(step);
      path.add(0, step);
    }

    return path;
  }

  //is there a path from the from vertex to the to vertex in this graph
  boolean hasPath(Vertex from, Vertex to, ICollection<Vertex> worklist) {
    ArrayList<Vertex> visited = new ArrayList<Vertex>();

    worklist.add(from);

    while (worklist.size() > 0) {
      Vertex next = worklist.remove();

      if (next == to) {
        return true;
      }

      else if (visited.contains(next)) {
      }

      else {
        for (Edge e : next.neighbors) {
          worklist.add(e.to);
        }
        visited.add(next);
      }
    }
    return false;
  }

  //is there a path from the from vertex to the to vertex in this graph, using DFS
  boolean hasPathDFS(Vertex from, Vertex to) {
    return this.hasPath(from, to, new Stack<Vertex>());
  }

  //is there a path from the from vertex to the to vertex in this graph, using BFS
  boolean hasPathBFS(Vertex from, Vertex to) {
    return this.hasPath(from, to, new Queue<Vertex>());
  }

  public ArrayList<Vertex> performBreadthForthSearch() {
    // TODO Auto-generated method stub
    this.bfs = new Queue<Vertex>();
    this.bfs.add(player.currentCell);
    Queue<Vertex> worklist = this.bfs;
    HashMap<Vertex, Edge> cameFromEdge = new HashMap<Vertex, Edge>();
    this.seen = new ArrayList<Vertex>();
    this.bfs.items.add(new Vertex(0, 0));
    while (worklist.size() > 0) {
      Vertex next = worklist.remove();
      if (this.seen.contains(next)) {
        ;
      }
      else if (this.board.get(GAME_WIDTH).get(GAME_HEIGHT).equals(next)) {
        return reconstruct(cameFromEdge, next);
      }
      else {
        for (Edge e : next.neighbors) {
          worklist.add(e.to);
          cameFromEdge.put(e.to, e);
        }
        this.seen.add(next);
      }
    }
    return reconstruct(cameFromEdge, this.board.get(0).get(0));
  }

  public ArrayList<Vertex> performDepthFirstSearch() {
    // TODO Auto-generated method stub
    this.dfs = new Stack<Vertex>();
    this.dfs.add(player.currentCell);
    Stack<Vertex> worklist = this.dfs;
    HashMap<Vertex, Edge> cameFromEdge = new HashMap<Vertex, Edge>();
    this.seen = new ArrayList<Vertex>();

    Vertex next = worklist.remove();
    if (this.seen.contains(next)) {
      ;
    }
    else if (this.board.get(GAME_WIDTH).get(GAME_HEIGHT).equals(next)) {
      return reconstruct(cameFromEdge, next);
    }
    else {
      for (Edge e : next.neighbors) {
        worklist.add(e.to);
        cameFromEdge.put(e.to, e);
      }
      this.seen.add(next);
    }
    return reconstruct(cameFromEdge, this.board.get(0).get(0));
  }

  public ArrayList<Vertex> reconstruct(HashMap<Vertex, Edge> cameFromEdge, Vertex next) {
    // TODO Auto-generated method stub
    if (this.board.get(0).get(0).equals(next)) {
      this.path.add(next);
      return this.path;
    }
    else {
      this.path.add(next);
      reconstruct(cameFromEdge, cameFromEdge.get(next).from);
    }
    return this.path;
  }

  /////////////////////////////////////////////////////////////
  ///////////////////////HANDLER METHODS///////////////////////
  /////////////////////////////////////////////////////////////

  public void onTick() {
    // TODO Auto-generated method stub
    if (this.depthFirst) {
      this.performDepthFirstSearch();
    }

    if (this.breadthForth) {
      this.performBreadthForthSearch();
    }

  }

  public void onKeyEvent(String key) {
    if (key.equals("r")) {
      this.createNewGameBoard();
    }

    if (key.equals("d")) {
      this.depthFirst = true;
      this.breadthForth = false;
    }

    if (key.equals("b")) {
      this.breadthForth = true;
      this.depthFirst = false;
    }
  }

  ///////////////////////////////////////////////////////////
  ///////////////////////SCENE METHODS///////////////////////
  ///////////////////////////////////////////////////////////

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

  //makes the scene for the overall world
  public WorldScene makeScene() {
    // TODO Auto-generated method stub
    WorldScene scene = new WorldScene(VERTEX_SPACE_SIZE * SCREENWIDTH,
        VERTEX_SPACE_SIZE * SCREENHEIGHT);
    for (Edge e : this.worklist) {
      scene.placeImageXY(e.drawEdgeLine(),
          VERTEX_SPACE_SIZE * (e.from.x + e.to.x) / 2 + (VERTEX_SPACE_SIZE - 1 / 2),
          VERTEX_SPACE_SIZE * (e.from.y + e.to.y) / 2 + (VERTEX_SPACE_SIZE - 1 / 2));
      scene.placeImageXY(new RectangleImage(VERTEX_SPACE_SIZE * 3, VERTEX_SPACE_SIZE * 3,
          OutlineMode.SOLID, Color.GREEN), 0, 0);
      scene.placeImageXY(new RectangleImage(VERTEX_SPACE_SIZE, VERTEX_SPACE_SIZE, OutlineMode.SOLID,
          Color.MAGENTA), VERTEX_SPACE_SIZE * GAME_WIDTH, VERTEX_SPACE_SIZE * GAME_HEIGHT);
    }
    for (Vertex v : this.path) {

      scene.placeImageXY(v.fillIn(), VERTEX_SPACE_SIZE * v.x, VERTEX_SPACE_SIZE * v.y);
    }
    return scene;

  }

  // Check to see if we need to end the game.
  public WorldEnd worldEnds() {
    if (this.score == 1) {
      return new WorldEnd(true, this.makeWinCutScene());
    }
    else {
      return new WorldEnd(false, this.makeScene());
    }
  }
}

//to represent examples and tests for Maze.Java
class ExamplesMaze {

  //Examples of Vertex
  Vertex a;
  Vertex b;
  Vertex c;
  Vertex d;
  Vertex e;
  Vertex f;
  Vertex g;
  Vertex h;
  Vertex i;
  Vertex j;
  Vertex k;
  Vertex l;

  //Examples of Edges 
  Edge aToB;
  Edge eToB;
  Edge cToA;
  Edge bToC;
  Edge bToD;
  Edge dToF;
  Edge cToF;

  //Examples of ArrayList<Vertex> 
  ArrayList<Vertex> boardGame1DArray;
  ArrayList<Vertex> boardGame1DArray15;
  ArrayList<Vertex> boardGame1DArray2;
  ArrayList<Vertex> boardGame1DArray25;

  //Examples of ArrayList<ArrayList<Vertex>> 
  ArrayList<ArrayList<Vertex>> boardGame2DArray;

  //to represent the initial Data
  void initData() {
    //Examples of Vertex
    this.a = new Vertex(0, 0, new ArrayList<Edge>());
    this.b = new Vertex(1, 0, new ArrayList<Edge>());
    this.c = new Vertex(0, 1, new ArrayList<Edge>());
    this.d = new Vertex(2, 1, new ArrayList<Edge>());
    this.e = new Vertex(2, 2, new ArrayList<Edge>());
    this.f = new Vertex(2, 3, new ArrayList<Edge>());
    this.g = new Vertex(0, 0, new ArrayList<Edge>());
    this.h = new Vertex(1, 0, new ArrayList<Edge>());
    this.i = new Vertex(0, 1, new ArrayList<Edge>());
    this.j = new Vertex(2, 1, new ArrayList<Edge>());
    this.k = new Vertex(2, 2, new ArrayList<Edge>());
    this.l = new Vertex(2, 3, new ArrayList<Edge>());

    //Examples of Edges 
    this.aToB = new Edge(this.a, this.b, 1);
    this.eToB = new Edge(this.e, this.b, 3);
    this.cToA = new Edge(this.c, this.a, 2);
    this.bToC = new Edge(this.b, this.c, 10);
    this.bToD = new Edge(this.b, this.d, 1);
    this.dToF = new Edge(this.d, this.f, 5);
    this.cToF = new Edge(this.c, this.f, 1);

    //Examples of ArrayList<ArrayList<Vertex>> 
    this.a.neighbors.add(this.aToB);
    this.b.neighbors.add(this.bToD);
    this.b.neighbors.add(this.bToC);
    this.e.neighbors.add(this.eToB);
    this.c.neighbors.add(this.cToA);
    this.c.neighbors.add(this.cToF);
    this.d.neighbors.add(this.dToF);
    this.boardGame1DArray = new ArrayList<Vertex>(Arrays.asList(this.a, this.b, this.c));
    this.boardGame1DArray15 = new ArrayList<Vertex>(Arrays.asList(this.d, this.e, this.f));
    this.boardGame1DArray2 = new ArrayList<Vertex>(Arrays.asList(this.g, this.h, this.i));
    this.boardGame1DArray25 = new ArrayList<Vertex>(
        Arrays.asList(this.g, this.h, this.i, this.j, this.k, this.l));
    this.boardGame2DArray = new ArrayList<ArrayList<Vertex>>(
        Arrays.asList(this.boardGame1DArray, this.boardGame1DArray15, this.boardGame1DArray2));
  }

  //to test the big gang and game's overall functionality.
  void testBigBang(Tester t) {
    // width, height, tickrate = 0.5 means every 0.5 seconds the onTick method will
    // get called.
    this.initData();
    MazeWorld world = new MazeWorld(0, this.boardGame2DArray);
    world.createNewGameBoard();
    world.bigBang(MazeWorld.SCREENWIDTH * MazeWorld.VERTEX_SPACE_SIZE,
        MazeWorld.SCREENHEIGHT * MazeWorld.VERTEX_SPACE_SIZE, MazeWorld.TICKRATE);
  }

  //to test createNewGameBoard method
  void testCreateNewGameBoard(Tester t) {
    this.initData();
    MazeWorld world = new MazeWorld(0, this.boardGame2DArray);
    world.createNewGameBoard();
    t.checkExpect(world.board.equals(this.boardGame2DArray), false);
    t.checkExpect(world.board.get(0).size(), 6121);
  }

  //to test kruskalsAlgorithmSearch method
  void testKruskalsAlgorithmSearch(Tester t) {
    this.initData();
    MazeWorld world = new MazeWorld(0, this.boardGame2DArray);
    t.checkExpect(world.walls.size(), 0);
    world.createNewGameBoard();
    t.checkExpect(world.walls.size(), 6159);
  }

  //to test find method
  void testFind(Tester t) {
    this.initData();
    MazeWorld world = new MazeWorld(0, this.boardGame2DArray);
    world.createNewGameBoard();
    t.checkExpect(
        a.find(world.predecessors, world.board.get(0).get(0)).equals(world.board.get(0).get(0)),
        false);
  }

  //to test union method
  void testUnion(Tester t) {
    this.initData();
    MazeWorld world = new MazeWorld(0, this.boardGame2DArray);
    world.createNewGameBoard();
    t.checkExpect(world.predecessors.containsKey(a), false);
    world.predecessors.put(a, b);
    t.checkExpect(world.predecessors.containsKey(a), true);
  }

  //to test the big gang and game's overall functionality.
  void testMakeLoseCutScene(Tester t) {
    // width, height, tickrate = 0.5 means every 0.5 seconds the onTick method will
    // get called.
    this.initData();
    MazeWorld world = new MazeWorld(0, this.boardGame2DArray);
    WorldScene endScene = new WorldScene(MazeWorld.SCREENWIDTH, MazeWorld.SCREENHEIGHT);
    endScene.placeImageXY(new TextImage(
        "Game Over and You Lose: Your Overall Score is: " + Integer.toString(world.score),
        Color.red), 250, 250);
    t.checkExpect(world.makeLoseCutScene(), endScene);
  }

  //to test makeWinCutScene method
  boolean testMakeWinCutScene(Tester t) {
    this.initData();
    MazeWorld world = new MazeWorld(0, this.boardGame2DArray);
    WorldScene endScene = new WorldScene(MazeWorld.SCREENWIDTH, MazeWorld.SCREENHEIGHT);
    endScene.placeImageXY(
        new TextImage("Victory Royale: Your Overall Score is: " + Integer.toString(world.score),
            Color.red),
        250, 250);
    return t.checkExpect(world.makeWinCutScene(), endScene);
  }

  //to test drawScore method
  void testDrawScore(Tester t) {
    this.initData();
    MazeWorld world = new MazeWorld(0, this.boardGame2DArray);
    t.checkExpect(world.drawScore(),
        new TextImage("Your Current Score is: " + Integer.toString(world.score), Color.BLACK));
  }

  //to test drawEdgeLine method
  void testDrawEdgeLine(Tester t) {
    this.initData();
    t.checkExpect(this.aToB.drawEdgeLine(),
        new RectangleImage(1, 10, OutlineMode.SOLID, Color.GRAY));
    t.checkExpect(this.bToC.drawEdgeLine(),
        new RectangleImage(1, -10, OutlineMode.SOLID, Color.GRAY));
    t.checkExpect(this.cToF.drawEdgeLine(),
        new RectangleImage(1, 10, OutlineMode.SOLID, Color.GRAY));
    t.checkExpect(this.eToB.drawEdgeLine(),
        new RectangleImage(1, -10, OutlineMode.SOLID, Color.GRAY));
  }

  //to test addEdge method
  void testAddEdge(Tester t) {
    this.initData();
    this.a.addEdge(aToB);
    this.a.addEdge(bToC);
    this.b.addEdge(bToC);
    t.checkExpect(this.a.neighbors.size(), 3);
    t.checkExpect(this.b.neighbors.size(), 3);
    t.checkExpect(this.c.neighbors.size(), 2);
    t.checkExpect(this.d.neighbors.size(), 1);
  }

  //to test WorldEnds method
  void testWorldEnds(Tester t) {
    this.initData();
    MazeWorld world = new MazeWorld(0, this.boardGame2DArray);
    MazeWorld world2 = new MazeWorld(1, this.boardGame2DArray);
    t.checkExpect(world.worldEnds(), new WorldEnd(false, world.makeScene()));
    t.checkExpect(world2.worldEnds(), new WorldEnd(true, world2.makeWinCutScene()));
  }

  //to test MakeScene method
  void testMakeScene(Tester t) {
    this.initData();
    MazeWorld world = new MazeWorld(0, this.boardGame2DArray);
    WorldScene scene = new WorldScene(MazeWorld.VERTEX_SPACE_SIZE * MazeWorld.SCREENWIDTH,
        MazeWorld.VERTEX_SPACE_SIZE * MazeWorld.SCREENHEIGHT);
    for (Edge e : world.walls) {
      scene.placeImageXY(e.drawEdgeLine(),
          MazeWorld.VERTEX_SPACE_SIZE * (e.from.x + e.to.x) / 2
              + (MazeWorld.VERTEX_SPACE_SIZE - 1 / 2),
          MazeWorld.VERTEX_SPACE_SIZE * (e.from.y + e.to.y) / 2
              + (MazeWorld.VERTEX_SPACE_SIZE - 1 / 2));
      scene.placeImageXY(new RectangleImage(MazeWorld.VERTEX_SPACE_SIZE * 2,
          MazeWorld.VERTEX_SPACE_SIZE * 2, OutlineMode.SOLID, Color.GREEN), 0, 0);
      scene.placeImageXY(
          new RectangleImage(MazeWorld.VERTEX_SPACE_SIZE, MazeWorld.VERTEX_SPACE_SIZE,
              OutlineMode.SOLID, Color.MAGENTA),
          MazeWorld.VERTEX_SPACE_SIZE * MazeWorld.GAME_WIDTH,
          MazeWorld.VERTEX_SPACE_SIZE * MazeWorld.GAME_HEIGHT);
    }
    t.checkExpect(world.makeScene(), scene);
  }

  void testOnTick(Tester t) {
    MazeWorld world = new MazeWorld(0, this.boardGame2DArray);
    world.createNewGameBoard();
    ArrayList<Vertex> test = world.performDepthFirstSearch();
    t.checkExpect(test, 1);
  }
}