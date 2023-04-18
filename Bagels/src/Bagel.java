 import tester.Tester;

// BagelRecipe.java
// Dante LoPriore and Jake Simeone

//to represents a bagel recipe
class BagelRecipe {
  double flour;
  double water;
  double yeast;
  double salt;
  double malt;

  // default constructor
  BagelRecipe(double flour, double water, double yeast, double salt, double malt) {
    if (Math.abs(flour - water) <= 0.001) {
      this.flour = flour;
      this.water = water;
    }
    else {
      throw new IllegalArgumentException(
          "the weight of the flour should be equal to the weight of the water");
    }
    if (Math.abs(yeast - malt) <= 0.001 && Math.abs((salt + yeast) - (flour / 20)) <= 0.001) {
      this.yeast = yeast;
      this.salt = salt;
      this.malt = malt;
    }
    else {
      throw new IllegalArgumentException(
          "the weight of the yeast should be equal the weight of the malt"
              + " the weight of the salt + yeast should be 1/20th the weight of the flour");
    }
  }

  /*Fields 
   * ...this.flour... double
   * ...this.water... double
   * ...this.yeast... double
   * ...this.salt... double
   * ...this.malt... double
   *Methods:
   * ...this.sameRecipe(BagelRecipe)... boolean
   */
  
  
  
  // first convenience constructor
  // only requires the weights of flour and yeast, and produces a perfect bagel
  // recipe.
  BagelRecipe(double flour, double yeast) {
    this(flour, flour, yeast, ((flour / 20) - yeast), yeast);
  }

  // second convenience constructor
  // takes in the flour, yeast and salt as volumes rather than weights, and tries
  // to produce a perfect recipe
  BagelRecipe(double flour, double yeast, double salt) {
    this(flour * 17 / 4, flour * 17 / 4, yeast / 48 * 5, salt / 48 * 10, yeast / 48 * 5);
  }

  // to determine if the same ingredients have the same weights to within 0.001
  // ounces.
  boolean sameRecipe(BagelRecipe other) {
    /*Fields 
     * ...this.other.flour... double
     * ...this.other.water... double
     * ...this.other.yeast... double
     * ...this.other.salt... double
     * ...this.other.malt... double
     *Methods:
     * ...this.other.sameRecipe(BagelRecipe)... boolean
     *Parameters:
     * other ... BagelRecipe
     */
    return Math.abs(this.flour - other.flour) <= 0.001
        && Math.abs(this.water - other.water) <= 0.001
        && Math.abs(this.yeast - other.yeast) <= 0.001 && Math.abs(this.salt - other.salt) <= 0.001
        && Math.abs(this.malt - other.malt) <= 0.001;
  }
}

//to represent tests and examples for the Bagel.java classes.
class ExamplesBagelRecipe {
  ExamplesBagelRecipe() {
  }

  // Examples for default constructor
  BagelRecipe plain = new BagelRecipe(0, 0, 0, 0, 0);
  BagelRecipe chocolatechip = new BagelRecipe(1000.0, 1000.0, 25.0, 25.0, 25.0);
  BagelRecipe pumpkin = new BagelRecipe(800.0, 800.0, 20.0, 20.0, 20.0);

  // Examples for the first convenience constructor
  BagelRecipe plain2 = new BagelRecipe(0, 0);
  BagelRecipe vege = new BagelRecipe(1000.0, 25.0);
  BagelRecipe onion = new BagelRecipe(800.0, 20.0);

  // Examples for the second convenience constructor
  BagelRecipe plain3 = new BagelRecipe(0, 0, 0);
  BagelRecipe redPepper = new BagelRecipe((235 * (5 / 17)), (2 * (29 / 48)), (5 * (5 / 24)));

  // tests for default BagelRecipe constructor
  boolean testDefaultBagelRecipe(Tester t) {
    return t.checkConstructorException(
        new IllegalArgumentException(
            "the weight of the flour should be equal to the weight of the water"),
        "BagelRecipe", 2.0, 40.0, 400.0, 5.0, 60.0)
        && t.checkConstructorException(
            new IllegalArgumentException(
                "the weight of the yeast should be equal the weight of the malt"
                    + " the weight of the salt + yeast should be 1/20th the weight of the flour"),
            "BagelRecipe", 40.0, 40.0, 400.0, 5.0, 60.0)
        && t.checkConstructorException(
            new IllegalArgumentException(
                "the weight of the yeast should be equal the weight of the malt"
                    + " the weight of the salt + yeast should be 1/20th the weight of the flour"),
            "BagelRecipe", 1000.0, 1000.0, 25.0, 60.0, 25.0);
  }

  // tests for the same recipe method
  boolean testSameRecipe(Tester t) {
    return t.checkExpect(this.plain.sameRecipe(this.plain2), true)
        && t.checkExpect(this.chocolatechip.sameRecipe(this.vege), true)
        && t.checkExpect(this.redPepper.sameRecipe(this.chocolatechip), false)
        && t.checkExpect(this.onion.sameRecipe(this.onion), true);
  }
}

