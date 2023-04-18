import tester.Tester;
//Entertainment.java
//Dante LoPriore and Jake Simeone

//to represent Entertainment
interface IEntertainment {

  // compute the total price of this Entertainment
  double totalPrice();

  // computes the minutes of entertainment of this IEntertainment
  int duration();

  // produce a String that shows the name and price of this IEntertainment
  String format();

  // is this IEntertainment the same as that one?
  boolean sameEntertainment(IEntertainment that);

  // is this IEntertainment the same as Magazine?
  boolean sameMagazine(Magazine m);

  // is this IEntertainment the same as TVSeries?
  boolean sameTVSeries(TVSeries tv);

  // is this IEntertainment the same as Podcast?
  boolean samePodcast(Podcast p);

}

//to represent the abstract class for Entertainment
abstract class AEntertainment implements IEntertainment {
  String name;
  double price; // represents price per issue

  // constructor
  AEntertainment(String name, double price) {
    this.name = name;
    this.price = price;
  }

  /*Fields:
   * this.name... String
   * this.price... double
   *Methods
   * this.sameEntertainment(IEntertainment)... boolean
   * this.sameMagazine(Magazine)... boolean
   * this.sameTVSeries(TVSeries)... boolean
   * this.samePodcast(Podcast p)... boolean
   */

  // is this IEntertainment the same as that one?
  public boolean sameEntertainment(IEntertainment that) {
    return false;
  }

  // is this IEntertainment the same as Magazine?
  public boolean sameMagazine(Magazine that) {
    return false;
  }

  // is this IEntertainment the same as TVSeries?
  public boolean sameTVSeries(TVSeries tv) {
    return false;
  }

  // is this IEntertainment the same as Podcast?
  public boolean samePodcast(Podcast p) {
    return false;
  }
}

//to represent a Magazine
class Magazine extends AEntertainment {
  String genre;
  int pages;
  int installments; // number of issues per year

  // constructor
  Magazine(String name, double price, String genre, int pages, int installments) {
    super(name, price);
    this.genre = genre;
    this.pages = pages;
    this.installments = installments;
  }

  /*Fields:
   * this.name... String
   * this.price... double
   * this.genre... String
   * this.pages... int
   * this.installments... int
   * 
   *Methods
   * this.totalPrice()... double
   * this.duration()... int
   * this.sameEntertainment(IEntertainment)... boolean
   * this.sameMagazine(Magazine)... boolean
   * this.format()... String
   */

  // computes the price of a yearly subscription to this Magazine
  public double totalPrice() {
    return this.price * this.installments;
  }

  // computes the minutes of entertainment of this Magazine, (includes all
  // installments)
  public int duration() {
    return 5 * this.pages * this.installments;
  }

  // is this Magazine the same as that IEntertainment?
  public boolean sameEntertainment(IEntertainment that) {
    /*Fields:
     * this.that.name... String
     * this.that.price... double
     * this.that.genre... String
     * this.that.pages... int
     * this.that.installments... int
     * 
     *Methods
     * this.that.totalPrice()... double
     * this.that.duration()... int
     * this.that.sameEntertainment(IEntertainment)... boolean
     * this.that.sameMagazine(Magazine)... boolean
     * this.that.format()... String
     *
     *Parameters
     * that .... IEntertainment 
     */
    return that.sameMagazine(this);
  }

  // is this IEntertainment the same as Magazine?
  public boolean sameMagazine(Magazine that) {
    /*Fields:
     * this.that.name... String
     * this.that.price... double
     * this.that.genre... String
     * this.that.pages... int
     * this.that.installments... int
     * 
     *Methods
     * this.that.totalPrice()... double
     * this.that.duration()... int
     * this.that.sameEntertainment(IEntertainment)... boolean
     * this.that.sameMagazine(Magazine)... boolean
     * this.that.format()... String
     *
     *Parameters
     * that .... Magazine 
     */
    return this.name.equals(that.name) && this.price == that.price && this.genre.equals(that.genre)
        && this.pages == that.pages && this.installments == that.installments;
  }

  // produce a String that shows the name and price of this Magazine
  public String format() {
    return this.name + ", " + this.price + ".";
  }
}

//to represent a TVSeries
class TVSeries extends AEntertainment {
  int installments; // number of episodes of this series
  String corporation;

  // constructor
  TVSeries(String name, double price, int installments, String corporation) {
    super(name, price);
    this.installments = installments;
    this.corporation = corporation;
  }

  /*Fields:
   * this.name... String
   * this.price... double
   * this.installments... int
   * this.corporation... String
   * 
   *Methods
   * this.totalPrice()... double
   * this.duration()... int
   * this.sameEntertainment(IEntertainment)... boolean
   * this.sameTVSeries(TVSeries)... boolean
   * this.format()... String
   */

  // computes the price of a yearly subscription to this TVSeries
  public double totalPrice() {
    return this.price * this.installments;
  }

  // computes the minutes of entertainment of this TVSeries
  public int duration() {
    return 50 * this.installments;
  }

  // is this TVSeries the same as that IEntertainment?
  public boolean sameEntertainment(IEntertainment that) {
    /*Fields:
     * this.that.name... String
     * this.that.price... double
     * this.that.installments... int
     * this.that.corporation... String
     * 
     *Methods
     * this.that.totalPrice()... double
     * this.that.duration()... int
     * this.that.sameEntertainment(IEntertainment)... boolean
     * this.that.sameTVSeries(TVSeries)... boolean
     * this.that.format()... String
     *
     *Parameters
     * that... IEntertainment
     */
    return that.sameTVSeries(this);
  }

  // is this IEntertainment the same as TVSeries?
  public boolean sameTVSeries(TVSeries tv) {
    /*Fields:
     * this.tv.name... String
     * this.tv.price... double
     * this.tv.installments... int
     * this.tv.corporation... String
     * 
     *Methods
     * this.tv.totalPrice()... double
     * this.tv.duration()... int
     * this.tv.sameEntertainment(IEntertainment)... boolean
     * this.tv.sameTVSeries(TVSeries)... boolean
     * this.tv.format()... String
     *
     *Parameters
     * tv... TVSeries
     */
    return this.name.equals(tv.name) && this.price == tv.price
        && this.installments == tv.installments && this.corporation.equals(tv.corporation);
  }

  // produce a String that shows the name and price of this TVSeries
  public String format() {
    return this.name + ", " + this.price + ".";
  }
}

//to represent a Podcast
class Podcast extends AEntertainment {
  int installments; // number of episodes in this Podcast

  // constructor
  Podcast(String name, double price, int installments) {
    super(name, price);
    this.installments = installments;
  }

  /*Fields:
   * this.name... String
   * this.price... double
   * this.installments... int
   * 
   *Methods
   * this.totalPrice()... double
   * this.duration()... int
   * this.sameEntertainment(IEntertainment)... boolean
   * this.samePodcast(Podcast)... boolean
   * this.format()... String
   */

  // computes the price of a yearly subscription to this Podcast
  public double totalPrice() {
    return this.price * this.installments;
  }

  // computes the minutes of entertainment of this Podcast
  public int duration() {
    return 50 * this.installments;
  }

  // is this Podcast the same as that IEntertainment?
  public boolean sameEntertainment(IEntertainment that) {
    /*Fields:
     * this.that.name... String
     * this.that.price... double
     * this.that.installments... int
     * 
     *Methods
     * this.that.totalPrice()... double
     * this.that.duration()... int
     * this.that.sameEntertainment(IEntertainment)... boolean
     * this.that.samePodcast(Podcast)... boolean
     * this.that.format()... String
     * 
     *Parameters
     * that... IEntertainment
     */
    return that.samePodcast(this);
  }

  // is this IEntertainment the same as Podcast?
  public boolean samePodcast(Podcast p) {
    /*Fields:
     * this.p.name... String
     * this.p.price... double
     * this.p.installments... int
     * 
     *Methods
     * this.p.totalPrice()... double
     * this.p.duration()... int
     * this.p.sameEntertainment(IEntertainment)... boolean
     * this.p.samePodcast(Podcast)... boolean
     * this.p.format()... String
     * 
     *Parameters
     * p... Podcast
     */
    return this.name.equals(p.name) && this.price == p.price && this.installments == p.installments;
  }

  // produce a String that shows the name and price of this Podcast
  public String format() {
    return this.name + ", " + this.price + ".";
  }

}

//tests and examples for the Entertainment.java classes
class ExamplesEntertainment {
  IEntertainment rollingStone = new Magazine("Rolling Stone", 2.55, "Music", 60, 12);
  IEntertainment houseOfCards = new TVSeries("House of Cards", 5.25, 13, "Netflix");
  IEntertainment serial = new Podcast("Serial", 0.0, 8);
  IEntertainment newyorker = new Magazine("New Yorker", 2.00, "Comedy", 20, 12);

  // testing total price method
  boolean testTotalPrice(Tester t) {
    return t.checkInexact(this.rollingStone.totalPrice(), 2.55 * 12, .0001)
        && t.checkInexact(this.houseOfCards.totalPrice(), 5.25 * 13, .0001)
        && t.checkInexact(this.serial.totalPrice(), 0.0, .0001)
        && t.checkInexact(this.newyorker.totalPrice(), 2.00 * 12, .0001);
  }
  
  // tests for the format method
  boolean testFormat(Tester t) {
    return t.checkExpect(this.rollingStone.format(), "Rolling Stone, 2.55.")
        && t.checkExpect(this.houseOfCards.format(), "House of Cards, 5.25.")
        && t.checkExpect(this.serial.format(), "Serial, 0.0.");
  }

  // tests for the SameEntertainment method
  boolean testSameEntertainment(Tester t) {
    return t.checkExpect(this.rollingStone.sameEntertainment(this.rollingStone), true)
        && t.checkExpect(this.rollingStone.sameEntertainment(this.houseOfCards), false)
        && t.checkExpect(this.serial.sameEntertainment(this.houseOfCards), false);
  }

  // tests for the SameMagazine method
  boolean testSameMagazine(Tester t) {
    return t.checkExpect(this.rollingStone.sameMagazine((Magazine) this.rollingStone), true)
        && t.checkExpect(this.houseOfCards.sameMagazine((Magazine) this.rollingStone), false)
        && t.checkExpect(this.serial.sameMagazine((Magazine) this.rollingStone), false);
  }

  // tests for the SameTVSeries method
  boolean testSameTVSeries(Tester t) {
    return t.checkExpect(this.rollingStone.sameTVSeries((TVSeries) this.houseOfCards), false)
        && t.checkExpect(this.houseOfCards.sameTVSeries((TVSeries) this.houseOfCards), true)
        && t.checkExpect(this.serial.sameTVSeries((TVSeries) this.houseOfCards), false);
  }

  // tests for the SamePodcast method
  boolean testSamePodcast(Tester t) {
    return t.checkExpect(this.rollingStone.samePodcast((Podcast) this.serial), false)
        && t.checkExpect(this.houseOfCards.samePodcast((Podcast) this.serial), false)
        && t.checkExpect(this.serial.samePodcast((Podcast) this.serial), true);
  }

  // tests for the duration method
  boolean testDuration(Tester t) {
    return t.checkExpect(this.rollingStone.sameEntertainment(this.rollingStone), true)
        && t.checkExpect(this.rollingStone.sameEntertainment(this.houseOfCards), false)
        && t.checkExpect(this.houseOfCards.sameEntertainment(this.rollingStone), false)
        && t.checkExpect(this.serial.sameEntertainment(this.houseOfCards), false);
  }
}

