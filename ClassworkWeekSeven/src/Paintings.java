import tester.Tester;

//a class represent a painting
class Painting {
  Artist artist;
  String title;
  double value; // in millions of dollars
  int year;

  Painting(Artist artist, String title, double value, int year) {
    this.artist = artist;
    this.title = title;
    this.value = value;
    this.year = year;
    this.artist.addPainting(this);
  }

  //is this Painting the same as the given one?
  boolean samePainting(Painting p) {
    return this.artist.sameArtist(p.artist) && this.title.equals(p.title) && this.value == p.value
        && this.year == p.year;
  }

}

class Artist {
  String name;
  int yob;
  IList<Painting> paintings;

  Artist(String n, int yob) {
    this.name = n;
    this.yob = yob;
    this.paintings = new MtList<Painting>();
  }

  /*
   * fields: this.name ... String this.yob ... int this.painting ... Painting
   * methods: this.sameArtist(Artist) ... boolean this.checkName(String) ...
   * boolean methods for fields: this.painting.samePainting(Painting)
   */

  //is this artist the same as the given one?
  boolean sameArtist(Artist that) {
    return this.name.equals(that.name) && this.yob == that.yob; // &&
    //this.painting.samePainting(that.painting);
  }

  //EFFECT: assign the given painting to this Artist
  void addPainting(Painting p) {
    this.paintings = new ConsList<Painting>(p, this.paintings);
  }

}

class Examples {

  Artist daVinci;
  Painting mona;
  Painting last;

  /*
   * Artist monet = new Artist("Monet", 1840); Painting mona = new Painting(new
   * Artist("Da Vinci", 1452, new Painting()), "Mona Lisa", 10, 1503); Painting
   * last = new Painting(this.daVinci, "The Last Supper", 11, 1480); Painting
   * sunflowers = new Painting(new Artist("Van Gogh", 1853), "sunflowers", 9,
   * 1889); Painting waterlilies = new Painting(this.monet, "Water Lilies", 20,
   * 1915);
   */

  //sets the paintings and artists for testing
  void initData() {
    this.daVinci = new Artist("Da Vinci", 1452);

    this.mona = new Painting(this.daVinci, "Mona Lisa", 100, 1503);

    this.last = new Painting(this.daVinci, "The Last Supper", 200, 1520);
  }

  void testPainting1(Tester t) {
    this.initData();
    t.checkExpect(this.daVinci.paintings, new ConsList<Painting>(this.last,
        new ConsList<Painting>(this.mona, new MtList<Painting>())));
    t.checkExpect(this.mona.artist, this.daVinci);
    t.checkExpect(this.last.artist, this.daVinci);
  }

  /*
   * void testPainting2(Tester t) { this.initData();
   * this.daVinci.setPainting(this.last);
   * t.checkExpect(this.daVinci.painting.artist, this.daVinci) ;
   * 
   * }
   */
}

class Counter {
  int val;

  Counter() {
    this(0);
  }

  Counter(int initialVal) {
    this.val = initialVal;
  }

  // adds a 1 to val and return the original val
  // EFFECT: this.val gets incremented by 1
  int get() {
    int ans = this.val;
    this.val = this.val + 1;
    return ans;
  }
}

class ExampleCounters {

  boolean testCounter(Tester t) {
    Counter c1 = new Counter();
    Counter c2 = new Counter(2);

    // What should these tests be?
    return t.checkExpect(c1.get(), 0) // Test 1
        && t.checkExpect(c2.get(), 2) // Test 2
        && t.checkExpect(c1.get() == c1.get(), false) // Test 3
        && t.checkExpect(c2.get() == c1.get(), true) // Test 4
        && t.checkExpect(c2.get() == c1.get(), true) // Test 5
        && t.checkExpect(c1.get() == c1.get(), false) // Test 6
        && t.checkExpect(c2.get() == c1.get(), false); // Test 7
  }

  boolean testCounter2(Tester t) {
    Counter c1 = new Counter();
    Counter c2 = new Counter(2);

    // What should these tests be?
    return t.checkExpect(c1.get(), 0) // Test 1
        && t.checkExpect(c2.get(), 2) // Test 2
        && t.checkExpect(c1.get() == c1.get(), false) // Test 3
        && t.checkExpect(c2.get() == c1.get(), true) // Test 4
        && t.checkExpect(c2.get() == c1.get(), true) // Test 5
        && t.checkExpect(c1.get() == c1.get(), false) // Test 6
        && t.checkExpect(c2.get() == c1.get(), false); // Test 7
  }

}
