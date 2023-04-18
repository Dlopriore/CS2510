import tester.Tester;


interface ILoPainting {
  //get all of the paintings by an Artist with the given name
  ILoPainting getByArtist(String name);
  //sort the list by painting title
  ILoPainting sortByTitle();
  //inserts the given painting into this *sorted* list resulting in a sorted list
  ILoPainting insertByTitle(Painting p);
}

class MtLoPainting implements ILoPainting {

  //get all of the paintings in this empty list by an Artist with the given name
  public ILoPainting getByArtist(String name) {
    return this;
  }

  //sort the list by painting title of this empty list
  public ILoPainting sortByTitle() {
    return this;
  }

  //inserts the given painting into this *sorted* list resulting in a sorted list
  public ILoPainting insertByTitle(Painting p) {
    return new ConsLoPainting(p, this);
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
   *  this.getByArtist(String) ... ILoPainting
   *  this.sortByTitle() ... ILoPainting
   *  this.insertByTitle(Painting) ... ILoPainting
   * methods for fields:
   *  this.first.samePainting(Painting) ... boolean
   *  this.first.checkArtistName(String) ... boolean
   *  this.first.titleComesBefore(Painting) ... boolean
   *  this.rest.getByArtist(String) ... ILoPainting 
   *  this.rest.sortByTitle() ... ILoPainting
   *  this.rest.insertByTitle(Painting) ... ILoPainting 
   * 
   */

  //get all of the paintings in this non-empty list by an Artist with the given name
  public ILoPainting getByArtist(String name) {
    if (this.first.checkArtistName(name)) {
      return new ConsLoPainting(this.first, this.rest.getByArtist(name));
    }
    else {
      return this.rest.getByArtist(name);
    }
  }

  //sort the list by painting title of this non-empty list
  public ILoPainting sortByTitle() {
    return this.rest.sortByTitle().insertByTitle(this.first);
  }

  //inserts the given painting into this *sorted* list resulting in a sorted list
  public ILoPainting insertByTitle(Painting p) {
    if (this.first.titleComesBefore(p)) {
      return new ConsLoPainting(this.first, this.rest.insertByTitle(p));
    }
    else {
      return new ConsLoPainting(p, this);
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


  //default constructor
  ExamplesPaintings () { }

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