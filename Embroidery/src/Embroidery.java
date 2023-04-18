//Embroidery.java
//Dante LoPriore

import tester.Tester;

//to represent a Motif 
interface IMotif {
  // to compute the average difficulty of motifs
  double averageDifficulty();

  // to count the amount of motifs
  double countOfMotifs();

  // to get the amount of difficulty
  double amountMotifDiffuculty();

  // to produce a string that contains the descriptions of
  // the cross-stitch and chain-piece motifs in an EmbroideryPiece
  String embroideryInfo();

  // get the overall descriptions of a motif
  String getDescription();
}

//to represent a List of Motifs
interface ILoMotif {
  // to compute the average difficulty of motifs
  double averageDifficulty();

  // to count the amount of motifs
  double countOfMotifs();

  // to get the amount of difficulty
  double amountMotifDiffuculty();

  // to produce a string that contains the descriptions of
  // the cross-stitch and chain-piece motifs in an EmbroideryPiece
  String embroideryInfo();

  // get the overall descriptions of a motif
  String getDescription();
}

//to represent an empty in the List of Motifs
class MtLoMotif implements ILoMotif {
  // to compute the average difficulty of motifs
  public double averageDifficulty() {
    // TODO Auto-generated method stub
    return 0.0;
  }

  // to count the amount of motifs
  public double countOfMotifs() {
    // TODO Auto-generated method stub
    return 0.0;
  }

  // to get the amount of difficulty
  public double amountMotifDiffuculty() {
    // TODO Auto-generated method stub
    return 0.0;
  }

  // to produce a string that contains the descriptions of
  // the cross-stitch and chain-piece motifs in an EmbroideryPiece
  public String embroideryInfo() {
    // TODO Auto-generated method stub
    return "";
  }

  // get the overall descriptions of a motif
  public String getDescription() {
    // TODO Auto-generated method stub
    return "";
  }

}

//to represent a Cons List of Motif
class ConsLoMotif implements ILoMotif {
  IMotif first;
  ILoMotif rest;

  // constructor
  ConsLoMotif(IMotif first, ILoMotif rest) {
    this.first = first;
    this.rest = rest;
  }

  /*
   * Fields: this.first ... IMotif this.rest ... ILoMotif Methods:
   * this.averageDifficulty() ... double this.embroideryInfo() ... String Methods
   * For Fields: this.first.countOfMotifs() ... double
   * this.first.amountMotifDiffuculty() ... double this.first.getDescription() ...
   * String this.rest.countOfMotifs() ... double this.rest.amountMotifDiffuculty()
   * ... double this.rest.getDescription() ... String
   */

  // to compute the average difficulty of motifs
  public double averageDifficulty() {
    return this.amountMotifDiffuculty() / this.countOfMotifs();
  }

  // to count the amount of motifs
  public double countOfMotifs() {
    // TODO Auto-generated method stub
    return this.first.countOfMotifs() + this.rest.countOfMotifs();
  }

  // to get the amount of difficulty
  public double amountMotifDiffuculty() {
    // TODO Auto-generated method stub
    return this.first.amountMotifDiffuculty() + this.rest.amountMotifDiffuculty();
  }

  // to produce a string that contains the descriptions of
  // the cross-stitch and chain-piece motifs in an EmbroideryPiece
  public String embroideryInfo() {
    // TODO Auto-generated method stub
    return this.getDescription();
  }

  // get the overall descriptions of a motif
  public String getDescription() {
    if (this.rest.getDescription().equals("")) {
      return this.first.getDescription();
    }
    else {
      return this.first.getDescription() + ", " + this.rest.getDescription();
    }
  }
}

//to represent an EmbroideryPiece
class EmbroideryPiece {
  String name;
  IMotif motif;

  // constructor
  EmbroideryPiece(String name, IMotif motif) {
    this.name = name;
    this.motif = motif;
  }

  /*
   * Fields: this.name ... String this.motif ... IMotif Methods:
   * this.averageDifficulty() ... double this.embroideryInfo() ... String Methods
   * For Fields: this.motif.amountMotifDiffuculty() ... double
   * this.motif.countOfMotifs() ... double this.motif.getDescription() ... String
   */

  // to compute the average difficulty of motifs like CrossStitchMotif and
  // ChainStitchMotif by calculating the amount of difficulty and dividing
  // by the number or amount of the motifs.
  double averageDifficulty() {
    if (this.motif.countOfMotifs() != 0) {
      return this.motif.amountMotifDiffuculty() / this.motif.countOfMotifs();
    }
    else {
      return 0;
    }
  }

  // to produce a string that contains the descriptions of
  // the cross-stitch and chain-piece motifs in an EmbroideryPiece
  String embroideryInfo() {
    // TODO Auto-generated method stub
    return this.name + ": " + this.motif.getDescription() + ".";
  }
}

//to represent a CrossStitch Motif
class CrossStitchMotif implements IMotif {
  String description;
  double difficulty;
  // interpretation: difficulty is a number between 0 and 5, with 5 being the most
  // difficult

  // constructor
  CrossStitchMotif(String description, double difficulty) {
    this.description = description;
    this.difficulty = difficulty;
  }

  /*
   * Fields: this.description ... String this.difficulty ... double Methods:
   * this.averageDifficulty() ... double this.countOfMotifs() ... double
   * this.amountMotifDiffuculty() ... double this.embroideryInfo() ... String
   * this.getDescription() ... String
   */

  // to compute the average difficulty of motifs like CrossStitchMotif and
  // ChainStitchMotif by calculating the amount of difficulty and dividing
  // by the number or amount of the motifs.
  public double averageDifficulty() {
    return this.amountMotifDiffuculty() / countOfMotifs();
  }

  // to count the amount of motifs
  public double countOfMotifs() {
    return 1;
  }

  // to get the amount of difficulty
  public double amountMotifDiffuculty() {
    return this.difficulty;
  }

  // to produce a string that contains the descriptions of
  // the cross-stitch and chain-piece motifs in an EmbroideryPiece
  public String embroideryInfo() {
    // TODO Auto-generated method stub
    return this.getDescription();
  }

  // get the overall descriptions of a motif
  public String getDescription() {
    // TODO Auto-generated method stub
    return this.description + " (cross stitch)";
  }
}

//to represent a ChainStitchMotif
class ChainStitchMotif implements IMotif {
  String description;
  double difficulty;
  // interpretation: difficulty is a number between 0 and 5, with 5 being the most
  // difficult

  // constructor
  ChainStitchMotif(String description, double difficulty) {
    this.description = description;
    this.difficulty = difficulty;
  }

  /*
   * Fields: this.description ... String this.difficulty ... double Methods:
   * this.averageDifficulty() ... double this.countOfMotifs() ... double
   * this.amountMotifDiffuculty() ... double this.embroideryInfo() ... String
   * this.getDescription() ... String
   */

  // to compute the average difficulty of motifs like CrossStitchMotif and
  // ChainStitchMotif by calculating the amount of difficulty and dividing
  // by the number or amount of the motifs.
  public double averageDifficulty() {
    return this.amountMotifDiffuculty() / countOfMotifs();
  }

  // to count the amount of motifs
  public double countOfMotifs() {
    return 1;
  }

  // to get the amount of difficulty
  public double amountMotifDiffuculty() {
    return this.difficulty;
  }

  // to produce a string that contains the descriptions of
  // the cross-stitch and chain-piece motifs in an EmbroideryPiece
  public String embroideryInfo() {
    return this.getDescription();
  }

  // get the overall descriptions of a motif
  public String getDescription() {
    return this.description + " (chain stitch)";
  }
}

//to represent a Group Motif
class GroupMotif implements IMotif {
  String description;
  ILoMotif motifs;

  // constructor
  GroupMotif(String description, ILoMotif motifs) {
    this.description = description;
    this.motifs = motifs;
  }

  /*
   * Fields: this.description ... String this.motifs ... ILoMotif Methods:
   * this.averageDifficulty() .. double this.countOfMotifs() ... double
   * this.amountMotifDiffuculty() ... double this.embroideryInfo() ... String
   * this.getDescription() ... String Methods for Fields:
   * this.motifs.averageDifficulty(); ... double this.motifs.countOfMotifs() ...
   * double this.motifs.amountMotifDiffuculty() ... double
   * this.motifs.embroideryInfo() ... String this.motifs.getDescription() ...
   * String
   */

  // to compute the average difficulty of motifs
  public double averageDifficulty() {
    return this.motifs.averageDifficulty();
  }

  // to count the amount of motifs
  public double countOfMotifs() {
    // TODO Auto-generated method stub
    return this.motifs.countOfMotifs();
  }

  // to get the amount of difficulty
  public double amountMotifDiffuculty() {
    return this.motifs.amountMotifDiffuculty();
  }

  // to produce a string that contains the descriptions of
  // the cross-stitch and chain-piece motifs in an EmbroideryPiece
  public String embroideryInfo() {
    // TODO Auto-generated method stub
    return this.motifs.embroideryInfo();
  }

  // get the overall descriptions of a motif
  public String getDescription() {
    // TODO Auto-generated method stub
    return this.motifs.getDescription();
  }
}

//the tests class which tests all the methods and examples for Embroidery.java
class ExamplesEmbroidery {
  // default constructor
  ExamplesEmbroidery() {
  }

  // Examples of IMotif
  IMotif birdMotif = new CrossStitchMotif("bird", 4.5);
  IMotif treeMotif = new ChainStitchMotif("tree", 3.0);
  IMotif roseMotif = new CrossStitchMotif("rose", 5.0);
  IMotif poppyMotif = new ChainStitchMotif("poppy", 4.75);
  IMotif daisyMotif = new CrossStitchMotif("daisy", 3.2);

  // Examples of ILoMotif
  ILoMotif mt = new MtLoMotif();
  ILoMotif flowerslist1 = new ConsLoMotif(this.daisyMotif, this.mt);
  ILoMotif flowerslist2 = new ConsLoMotif(this.poppyMotif, this.flowerslist1);
  ILoMotif flowerslist3 = new ConsLoMotif(this.roseMotif, this.flowerslist2);

  // Example of Group Motif
  GroupMotif flowers = new GroupMotif("flowers", this.flowerslist3);

  // Examples of ILoMotif
  ILoMotif natureList1 = new ConsLoMotif(this.flowers, this.mt);
  ILoMotif natureList2 = new ConsLoMotif(this.treeMotif, this.natureList1);
  ILoMotif natureList3 = new ConsLoMotif(this.birdMotif, this.natureList2);

  // Example of Group Motif
  GroupMotif nature = new GroupMotif("nature", this.natureList3);

  // Example of an EmbroideryPiece
  EmbroideryPiece pillowCover = new EmbroideryPiece("Pillow Cover", this.nature);

  // tests the countOfMotifs() method
  boolean testCountOfMotifs(Tester t) {
    return t.checkExpect(this.birdMotif.countOfMotifs(), 1.0)
        && t.checkExpect(this.treeMotif.countOfMotifs(), 1.0)
        && t.checkExpect(this.flowers.countOfMotifs(), 3.0)
        && t.checkExpect(this.mt.countOfMotifs(), 0.0);
  }

  // tests the amountMotifDiffuculty() method
  boolean testAmountMotifDiffuculty(Tester t) {
    return t.checkExpect(this.birdMotif.amountMotifDiffuculty(), 4.5)
        && t.checkExpect(this.treeMotif.amountMotifDiffuculty(), 3.0)
        && t.checkExpect(this.flowers.amountMotifDiffuculty(), 12.95)
        && t.checkExpect(this.mt.amountMotifDiffuculty(), 0.0);
  }

  // tests the averageDifficulty() method
  boolean testAverageDifficulty(Tester t) {
    return t.checkInexact(this.birdMotif.averageDifficulty(), 4.5, 1.0e-05)
        && t.checkInexact(this.treeMotif.averageDifficulty(), 3.0, 1.0e-05)
        && t.checkInexact(this.flowers.averageDifficulty(), 4.316666666666666, 1.0e-05)
        && t.checkInexact(this.mt.averageDifficulty(), 0.0, 1.0e-05);
  }

  // tests the getDescription() method
  boolean testGetDescription(Tester t) {
    return t.checkExpect(this.birdMotif.getDescription(), "bird (cross stitch)")
        && t.checkExpect(this.treeMotif.getDescription(), "tree (chain stitch)")
        && t.checkExpect(this.flowers.getDescription(),
            "rose (cross stitch), poppy (chain stitch), daisy (cross stitch)");
  }

  // tests the embroideryInfo() method
  boolean testEmbroideryInfo(Tester t) {
    return t.checkExpect(this.birdMotif.embroideryInfo(), "bird (cross stitch)")
        && t.checkExpect(this.treeMotif.embroideryInfo(), "tree (chain stitch)")
        && t.checkExpect(this.flowers.embroideryInfo(),
            "rose (cross stitch), poppy (chain stitch), daisy (cross stitch)")
        && t.checkExpect(this.pillowCover.embroideryInfo(),
            "Pillow Cover: bird (cross stitch), tree (chain stitch), "
                + "rose (cross stitch), poppy (chain stitch), daisy (cross stitch).");
  }
}