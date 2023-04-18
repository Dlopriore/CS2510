import tester.Tester;


interface ILoPainting {
  //sort this list by the given comparison operator
  ILoPainting sort(IPaintingCompare comp);
  //insert into this sorted list by the given comparison operator
  ILoPainting insert(Painting p, IPaintingCompare comp);
  
  //filter this list of Paintings by the given predicate
  ILoPainting filter(IPaintingPred pred);
  //does the list have at least one painting that passes the given predicate?
  boolean ormap(IPaintingPred pred);
  //do all of the paintings in the list pass the given predicate?
  boolean andmap(IPaintingPred pred);
  
  //gets the best painting by a given comparator
  Painting getBestByCriteria(IPaintingCompare comp);  
  //helps get the best painting by keeping track of the best painting so far
  Painting getBestHelp(Painting acc, IPaintingCompare comp);
}

class MtLoPainting implements ILoPainting {
  //filter this empty list of Paintings by the given predicate
  public ILoPainting filter(IPaintingPred pred) {
    return this;
  }

  //does the list have at least one painting that passes the given predicate?
  public boolean ormap(IPaintingPred pred) {
    return false;
  }

  //do all of the paintings in the list pass the given predicate?
  public boolean andmap(IPaintingPred pred) {
    return true;
  }

  @Override
  public ILoPainting sort(IPaintingCompare comp) {
    return this;
  }

  //insert into this sorted list by the given comparison operator
  public ILoPainting insert(Painting p, IPaintingCompare comp) {
    return new ConsLoPainting(p, this);
  }

  //gets the best painting by a given comparator
  public Painting getBestByCriteria(IPaintingCompare comp) {
    throw new RuntimeException("no best of an empty list");
  }

  //helps get the best painting by keeping track of the best painting so far
  public Painting getBestHelp(Painting acc, IPaintingCompare comp) {
    return acc;
  }
  
}

class ConsLoPainting implements ILoPainting {
  Painting first;
  ILoPainting rest;
  
  ConsLoPainting(Painting first, ILoPainting rest) {
    this.first = first;
    this.rest = rest;
  }
  
  /* fields: 
   *  this.first ... Painting
   *  this.rest ... ILoPainting
   * methods:
   *  this.filter(IPaintingPred) ... ILoPainting
   *  this.ormap(IPaintingPred) ... boolean
   *  this.andmap(IPaintingPred)... boolean
   *  this.sort(IPaintingCompare) ... ILoPainting
   *  this.insertByTitle(Painting, IPaintingCompare) ... ILoPainting
   * methods for fields:
   *  this.first.samePainting(Painting) ... boolean
   *  this.first.checkArtistName(String) ... boolean
   *  this.first.titleComesBefore(Painting) ... boolean
   *  this.rest.filter(String) ... ILoPainting 
   *  this.rest.ormap(IPaintingPred) ... boolean
   *  this.rest.andmap(IPaintingPred)... boolean
   *  this.rest.sort(IPaintingCompare) ... ILoPainting
   *  this.rest.insert(Painting, IPaintingCompare) ... ILoPainting 
   * 
   */


  //filter this list of Paintings by the given predicate
  public ILoPainting filter(IPaintingPred pred) {
    if (pred.apply(this.first)) {
      return new ConsLoPainting(this.first, this.rest.filter(pred));
    }
    else {
      return this.rest.filter(pred);
    }
  }

  //does the list have at least one painting that passes the given predicate?
  public boolean ormap(IPaintingPred pred) {
    return pred.apply(this.first) || this.rest.ormap(pred);
  
  }

  //do all of the paintings in the list pass the given predicate?
  public boolean andmap(IPaintingPred pred) {
    return pred.apply(this.first) && this.rest.andmap(pred);
  }

  //sort this non-empty list by the given comparator
  public ILoPainting sort(IPaintingCompare comp) {
    return this.rest.sort(comp).insert(this.first, comp);
  }

  //insert into this sorted list by the given comparison operator
  public ILoPainting insert(Painting p, IPaintingCompare comp) {
    if (comp.compare(this.first, p) < 0) {
      return new ConsLoPainting(this.first, this.rest.insert(p, comp));
    }
    else {
      return new ConsLoPainting(p, this);
    }
  }

  //gets the best painting by a given comparator
  public Painting getBestByCriteria(IPaintingCompare comp) {
    return this.rest.getBestHelp(this.first, comp);
  }

  //helps get the best painting by keeping track of the best painting so far
  public Painting getBestHelp(Painting acc, IPaintingCompare comp) {
    if (comp.compare(this.first, acc) < 0) {
      return this.rest.getBestHelp(this.first, comp);
    }
    else {
      return this.rest.getBestHelp(acc, comp);
    }
  }
}

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
  }

  /* fields:
   *  this.artist ... Artist
   *  this.title ... String
   *  this.value ... double
   *  this.year ... int
   * methods:
   *   this.samePainting(Painting) ... boolean
   *   this.checkArtistName(String) ... boolean
   *   this.titleComesBefore(Painting) ... boolean
   * methods for fields:
   *   this.artist.checkName(String)
   */

  //is this Painting the same as the given one?
  boolean samePainting(Painting p) {
    /* fields of p:
     *   p.artist ... Artist
     *   p.title ... String
     *   p.value ... int
     *   p.year ... int
     *  methods of p: 
     *   p.samePainting(Painting)
     *   ...
     *  methods of fields of p:
     *   p.artist.sameArtist(Artist) ... boolean
     */
    return this.artist.sameArtist(p.artist) &&
        this.title.equals(p.title) &&
        this.value == p.value &&
        this.year == p.year;

  }
  
  //does the artist of this painting have the given name?
  boolean checkArtistName(String name) {
    return this.artist.checkName(name);
  }
  

  //does the title of this painting come before the title of the given one 
  boolean titleComesBefore(Painting p) {
    /* fields of p:
     *   p.artist
     *   p.title
     *   p.value
     *   p.year
     *  methods of p: 
     *   p.titleComesBefore(Painting)
     *   ...
     *   ...
     */
    
    return this.title.compareTo(p.title) < 0;

  }

}

class Artist { 
  String name;
  int yob;

  Artist(String n, int yob) {
    this.name = n;
    this.yob = yob;
  }

  /* fields:
   *  this.name ... String
   *  this.yob ... int
   * methods:
   *  this.sameArtist(Artist) ... boolean
   *  this.checkName(String) ... boolean 
   */

  //is this artist the same as the given one?
  boolean sameArtist(Artist that) {
    /* fields of that:
     *  that.name ... String
     *  that.yob ... int
     * methods of that:
     *  that.sameArtist(Artist) ... boolean 
     *  that.checkName(String) ... boolean
     */
    return this.name.equals(that.name) &&
        this.yob == that.yob;
  }
  
  //is the name of this artist the same as the given name
  boolean checkName(String name) {
    return this.name.equals(name);
  }


}

interface IPaintingPred {
  //asks a question about a Painting
  boolean apply(Painting p);
}

//represents a question about Paintings and their artists
class ByDaVinci implements IPaintingPred {
  //is the given painting by DaVinci
  public boolean apply(Painting p) {
    return p.artist.checkName("Da Vinci");
  }
}

class ByArtist implements IPaintingPred {
  String name;
  
  ByArtist(String name) {
    this.name = name;
  }
  
  //is the given painting by the artist with this.name?
  public boolean apply(Painting p) {
    return p.artist.checkName(this.name);
  }
  
}

class Before1900 implements IPaintingPred {
  //is the given painting from before 1900?
  public boolean apply(Painting p) {
    return p.year < 1900;
  }
}

class Over9 implements IPaintingPred {
  //is the value of the Painting over 9 million dollars?
  public boolean apply(Painting p) {
    return p.value > 9;
  }
}

//represents a higher order predicate
class AndPredicate implements IPaintingPred {
  IPaintingPred left;
  IPaintingPred right;
  
  AndPredicate(IPaintingPred left, IPaintingPred right) {
    this.left = left;
    this.right = right;
  }
  
  /* fields:
   *  this.left
   *  this.right
   * methods:
   *  this.apply(Painting)
   * methods for fields:
   *  this.left.apply(Painting)
   *  this.right.apply(Painting)  
   * 
   */
  
  @Override
  public boolean apply(Painting p) {
    return this.left.apply(p) && this.right.apply(p);
  }
  
}

//comparator for paintings
interface IPaintingCompare {
  //compares the given paintings to see which one comes first
  // returns a negative int if p1 comes before p2
  // returns a positive int if p1 comes after p2
  // returns a 0 is p1 and p2 are the same
  int compare(Painting p1, Painting p2);
}


class ByYear implements IPaintingCompare {

  //was p1 painted before p2?
  public int compare(Painting p1, Painting p2) {
    return p1.year - p2.year;
  } 
}

class ByTitle implements IPaintingCompare {

  //does the title of p1 come before the title of p2 alphabetically?
  public int compare(Painting p1, Painting p2) {
    return p1.title.compareTo(p2.title);
  } 
}

class FlipCompare implements IPaintingCompare {
  IPaintingCompare comparator;
  
  FlipCompare(IPaintingCompare c) {
    this.comparator = c;
  }

  @Override
  public int compare(Painting p1, Painting p2) {
    return this.comparator.compare(p1, p2) * -1;
  }
}


class ExamplesPaintings {
  Artist daVinci = new Artist("Da Vinci", 1452);
  Artist monet = new Artist("Monet", 1840);
  Painting mona = new Painting(this.daVinci, "Mona Lisa", 10, 1503);
  Painting last = new Painting(this.daVinci, "The Last Supper", 11, 1480);
  Painting sunflowers = new Painting(new Artist("Van Gogh", 1853), 
      "sunflowers", 9, 1889);
  Painting waterlilies = new Painting(this.monet, "Water Lilies", 20, 1915);
  
  ILoPainting mt = new MtLoPainting();
  ILoPainting list1 = new ConsLoPainting(this.mona, this.mt);
  ILoPainting list2 = new ConsLoPainting(this.sunflowers, this.list1);
  IPaintingPred andPred1 = new AndPredicate(new ByArtist("Van Gogh"), new Over9());
  IPaintingPred andPred2 = new AndPredicate(this.andPred1, new Before1900());

  //default constructor
  ExamplesPaintings () { }

  //tests for filter:
  boolean testFilter(Tester t) {
    return t.checkExpect(this.mt.filter(new ByDaVinci()), this.mt) &&
        t.checkExpect(this.list2.filter(new ByDaVinci()), this.list1) &&
        t.checkExpect(this.list2.filter(new Over9()), this.list1) &&
        t.checkExpect(this.list2.filter(new ByArtist("Van Gogh")), 
            new ConsLoPainting(this.sunflowers, this.mt));
  }
  
  
  // this.mt.getByArtist("Monet") -> this.mt
  // this.list2.getByArtist("Monet") -> this.mt
  // this.list2.getByArtist("Da Vinci") -> list1
  
  // this.mt.sortByTitle() --> mt
  // this.list1.sortByTitle() --> list1
  // this.list2.sortByTitle() --> [ mona, sunflowers, mt]
  
  // this.mt.insertByTitle(mona) --> list1
  // this.list1.insertByTitle(sunflowers) --> [ mona, sunflowers, mt]
  
  
  //tests for samePainting
  boolean testSame(Tester t) {
    return t.checkExpect(this.mona.samePainting(this.last), false) &&
        t.checkExpect(this.waterlilies.samePainting(this.waterlilies), true) &&
        t.checkExpect(this.daVinci.sameArtist(this.monet), false) &&
        t.checkExpect(this.monet.sameArtist(this.monet), true);
  }


}