// Strings.java
// CS 2510, Assignment 3 The Correct Final
// Dante LoPriore and Jake Simeone

import tester.Tester;

// to represent a list of Strings
interface ILoString {

  // combine all Strings in this list into one
  String combine();

  // to produces a new list in sorted in alphabetical order,
  // treating all Strings as if they were given in all lowercase.
  ILoString sort();

  // to check does this String come before the given String lexicographically?
  // In other words, it checks if this String comes before that String, and
  // it sorts each item in the list alphabetical.
  ILoString sorting(String str);

  // determines whether this list is sorted in alphabetical order
  // in a case-insensitive way.
  boolean isSorted();

  // does this String come before the given String lexicographically?
  // to check if this string comes after that String
  boolean doesWordComeAfter(String str);

  // to produce a list that sorts through a list of strings and given lists of
  // strings and combines the elements in a single list where the first, third,
  // fifth...
  // elements are from this list, and the second, fourth, sixth...
  // elements are from the given list.
  ILoString interleave(ILoString los);

  // to produces a sorted list of Strings that contains all items in both original
  // lists, including duplicates.
  ILoString merge(ILoString los);

  // to produce a combined sorted list
  boolean mergeItems(String str);

  // reverse the order of the strings in this list
  ILoString reverse();

  // help for reversing this list of strings
  // accumulator: keeps track of the reversed list so far
  ILoString reverseAcc(ILoString los);

  // that determines if this list contains pairs of identical strings
  boolean isDoubledList();

  // checks to see if the first and second strings are the same,
  // the third and fourth are the same,
  // the fifth and sixth are the same, and etc.
  boolean checkWords(String str);

  // determines whether this list contains the same words reading the list in
  // either order.
  boolean isPalindromeList();
}

// to represent an empty list of Strings
class MtLoString implements ILoString {

  // default constructor
  MtLoString() {
  }

  /*
  TEMPLATE
  
  METHODS
  ... this.combine() ...     -- String
  ... this.sort()...         -- ILoString
  ... this.isSorted() ...    -- boolean
  ... this.interleave(ILoString)...         -- ILoString
  ... this.merge(ILoString)...     -- ILoString
  ... this.reverse()...         -- ILoString
  ... this.isDoubledList()...    -- boolean
  ... this.isPalindromeList()...         -- boolean
  ... this.combine() ...     -- String
  ... this.sorting(String)...         -- ILoString
  ... this.doesWordComeAfter(String) ...    -- boolean
  ... this.mergeItems(String, ILoString)...     -- ILoString
  ... this.reverseAcc(ILoString)...         -- ILoString
  ... this.isDoubledList()...    -- boolean
  */

  // combine all Strings in this list into one
  public String combine() {
    return "";
  }

  // to produces a new list in sorted in alphabetical order,
  // treating all Strings as if they were given in all lowercase.
  public ILoString sort() {
    // TODO Auto-generated method stub
    return this;
  }

  // to check does this String come before the given String lexicographically?
  // In other words, it checks if this String comes before that String, and
  // it sorts each item in the list alphabetical.
  public ILoString sorting(String str) {
    // TODO Auto-generated method stub
    return new ConsLoString(str, this);
  }

  // determines whether this list is sorted in alphabetical order
  // in a case-insensitive way.
  public boolean isSorted() {
    // TODO Auto-generated method stub
    return true;
  }

  // does this String come before the given String lexicographically?
  // to check if this string comes after that String
  public boolean doesWordComeAfter(String str) {
    // TODO Auto-generated method stub
    return true;
  }

  // to produce a list that sorts through a list of strings and given lists of
  // strings and combines the elements in a single list where the first, third,
  // fifth...
  // elements are from this list, and the second, fourth, sixth...
  // elements are from the given list.
  // to sort through a this list of strings and given lists of string,
  // and produce a single list to have same elements in the two lists.
  public ILoString interleave(ILoString los) {
    // TODO Auto-generated method stub
    return los;
  }

  // to produces a sorted list of Strings that contains all items in both original
  // lists, including duplicates.
  public ILoString merge(ILoString los) {
    // TODO Auto-generated method stub
    return los;
  }

  public boolean mergeItems(String str) {
    // TODO Auto-generated method stub
    return true;
  }

  // reverse the order of the strings in this list
  public ILoString reverse() {
    // TODO Auto-generated method stub
    return this;
  }

  // help for reversing this list of strings
  // accumulator: keeps track of the reversed list so far
  public ILoString reverseAcc(ILoString los) {
    // TODO Auto-generated method stub
    return los;
  }

  // that determines if this list contains pairs of identical strings
  public boolean isDoubledList() {
    // TODO Auto-generated method stub
    return true;
  }

  // checks to see if the first and second strings are the same,
  // the third and fourth are the same,
  // the fifth and sixth are the same, and etc.
  public boolean checkWords(String str) {
    // TODO Auto-generated method stub
    return false;
  }

  // determines whether this list contains the same words reading the list in
  // either order.
  public boolean isPalindromeList() {
    // TODO Auto-generated method stub
    return false;
  }
}

// to represent a nonempty list of Strings
class ConsLoString implements ILoString {
  String first;
  ILoString rest;

  // constructor
  ConsLoString(String first, ILoString rest) {
    this.first = first;
    this.rest = rest;
  }

  /*
   TEMPLATE
   FIELDS:
   ... this.first ...         -- String
   ... this.rest ...          -- ILoString
   
   METHODS
   ... this.combine() ...     -- String
   ... this.sort()...         -- ILoString
   ... this.isSorted() ...    -- boolean
   ... this.interleave(ILoString)...         -- ILoString
   ... this.merge(ILoString)...     -- ILoString
   ... this.reverse()...         -- ILoString
   ... this.isDoubledList()...    -- boolean
   ... this.isPalindromeList()...         -- boolean
   ... this.sorting(String)...         -- ILoString
   ... this.doesWordComeAfter(String) ...    -- boolean
   ... this.mergeItems(String, ILoString)...     -- ILoString
   ... this.reverseAcc(ILoString)...         -- ILoString
   ... this.isDoubledList()...    -- boolean
   
   METHODS FOR FIELDS
   ... this.first.concat(String) ...        -- String
   ... this.first.compareTo(String) ...     -- integer
   ... this.first.toLowerCase() ...        -- String
   ... this.first.equals(String) ...     -- boolean
   ... this.rest.combine() ...        -- String
   ... this.rest.sort()...         -- ILoString
   ... this.rest.isSorted() ...    -- boolean
   ... this.rest.interleave(ILoString)...         -- ILoString
   ... this.rest.merge(ILoString)...     -- ILoString
   ... this.rest.reverse()...         -- ILoString
   ... this.rest.checkWords(String)...    -- boolean
   ... this.rest.isPalindromeList()...         -- boolean
   ... this.rest.sorting(String)...         -- ILoString
   ... this.rest.doesWordComeAfter(String) ...    -- boolean
   ... this.rest.mergeItems(String, ILoString)...     -- ILoString
   ... this.rest.reverseAcc(ILoString)...         -- ILoString
   ... this.rest.isDoubledList()...    -- boolean
   */

  // combine all Strings in this list into one
  public String combine() {
    return this.first.concat(this.rest.combine());
  }

  // to produces a new list in sorted in alphabetical order,
  // treating all Strings as if they were given in all lowercase.
  public ILoString sort() {
    // TODO Auto-generated method stub
    return this.rest.sort().sorting(this.first);
  }

  // to check does this String come before the given String lexicographically?
  // In other words, it checks if this String comes before that String, and
  // it sorts each item in the list alphabetical.
  public ILoString sorting(String str) {
    /*Methods:
     * ... this.concat(String) ...        -- String
     * ... this.compareTo(String) ...     -- integer
     * ... this.toLowerCase() ...        -- String
     *Method for Fields:
     * ... this.str.concat(String) ...        -- String
     * ... this.str.compareTo(String) ...     -- integer
     * ... this.str.toLowerCase() ...        -- String
     * ... this.str.equals(String) ...     -- boolean
     *Parameters:
     * str ... String
     * 
     */
    // TODO Auto-generated method stub
    if (0 >= this.first.toLowerCase().compareTo(str.toLowerCase())) {
      return new ConsLoString(this.first, this.rest.sorting(str));
    }
    else {
      return new ConsLoString(str, this);
    }
  }

  // determines whether this list is sorted in alphabetical order
  // in a case-insensitive way.
  public boolean isSorted() {
    if (this.rest.doesWordComeAfter(this.first)) {
      return this.rest.isSorted();
    }
    else {
      return false;
    }
  }

  // does this String come before the given String lexicographically?
  // to check if this string comes after that String
  public boolean doesWordComeAfter(String str) {
    /*Methods:
     * ... this.concat(String) ...        -- String
     *Method for Fields:
     * ... this.str.concat(String) ...        -- String
     * ... this.str.compareTo(String) ...     -- integer
     * ... this.str.toLowerCase() ...        -- String
     * ... this.str.equals(String) ...     -- boolean
     *Parameters:
     * str ... String
     */
    // TODO Auto-generated method stub
    return 0 <= this.first.toLowerCase().compareTo(str.toLowerCase());
  }

  // to produce a list that sorts through a list of strings and given lists of
  // strings and combines the elements in a single list where the first, third,
  // fifth...
  // elements are from this list, and the second, fourth, sixth...
  // elements are from the given list.
  // to sort through a this list of strings and given lists of string,
  // and produce a single list to have same elements in the two lists.
  public ILoString interleave(ILoString los) {
    /*Methods:
     * ... this.combine() ...     -- String
     * ... this.sort()...         -- ILoString
     * ... this.isSorted() ...    -- boolean
     * ... this.interleave(ILoString)...         -- ILoString
     * ... this.merge(ILoString)...     -- ILoString
     * ... this.reverse()...         -- ILoString
     * ... this.isDoubledList()...    -- boolean
     * ... this.isPalindromeList()...         -- boolean
     * ... this.sorting(String)...         -- ILoString
     * ... this.doesWordComeAfter(String) ...    -- boolean
     * ... this.mergeItems(String, ILoString)...     -- ILoString
     * ... this.reverseAcc(ILoString)...         -- ILoString
     * ... this.isDoubledList()...    -- boolean
     *Method for Fields:
     * ... this.los.combine() ...        -- String
     * ... this.los.sort()...         -- ILoString
     * ... this.los.isSorted() ...    -- boolean
     * ... this.los.interleave(ILoString)...         -- ILoString
     * ... this.los.merge(ILoString)...     -- ILoString
     * ... this.los.reverse()...         -- ILoString
     * ... this.los.checkWords(String)...    -- boolean
     * ... this.los.isPalindromeList()...         -- boolean
     * ... this.los.sorting(String)...         -- ILoString
     * ... this.los.doesWordComeAfter(String) ...    -- boolean
     * ... this.los.mergeItems(String, ILoString)...     -- ILoString
     * ... this.los.reverseAcc(ILoString)...         -- ILoString
     * ... this.los.isDoubledList()...    -- boolean
     *Parameters:
     * los ... ILoString
     */
    return new ConsLoString(this.first, los.interleave(this.rest));
  }

  @Override
  // to produces a sorted list of Strings that contains all items in both original
  // lists,
  // including duplicates.
  public ILoString merge(ILoString los) {
    /*Methods:
     * ... this.combine() ...     -- String
     * ... this.sort()...         -- ILoString
     * ... this.isSorted() ...    -- boolean
     * ... this.interleave(ILoString)...         -- ILoString
     * ... this.merge(ILoString)...     -- ILoString
     * ... this.reverse()...         -- ILoString
     * ... this.isDoubledList()...    -- boolean
     * ... this.isPalindromeList()...         -- boolean
     * ... this.sorting(String)...         -- ILoString
     * ... this.doesWordComeAfter(String) ...    -- boolean
     * ... this.sortInterleaveItems(String, ILoString)...         -- ILoString
     * ... this.mergeItems(String, ILoString)...     -- ILoString
     * ... this.reverseAcc(ILoString)...         -- ILoString
     * ... this.isDoubledList()...    -- boolean
     *Method for Fields:
     * ... this.los.combine() ...        -- String
     * ... this.los.sort()...         -- ILoString
     * ... this.los.isSorted() ...    -- boolean
     * ... this.los.interleave(ILoString)...         -- ILoString
     * ... this.los.merge(ILoString)...     -- ILoString
     * ... this.los.reverse()...         -- ILoString
     * ... this.los.checkWords(String)...    -- boolean
     * ... this.los.isPalindromeList()...         -- boolean
     * ... this.los.sorting(String)...         -- ILoString
     * ... this.los.doesWordComeAfter(String) ...    -- boolean
     * ... this.los.mergeItems(String, ILoString)...     -- ILoString
     * ... this.los.reverseAcc(ILoString)...         -- ILoString
     * ... this.los.isDoubledList()...    -- boolean
     *Parameters:
     * los ... ILoString
     */

    // TODO Auto-generated method stub
    if (los.mergeItems(this.first)) {
      return new ConsLoString(this.first, this.rest.merge(los));
    }
    else {
      return los.merge(this);
    }
  }

  // to pro
  public boolean mergeItems(String str) {
    /*Methods:
     * ... this.concat(String) ...        -- String
     *Method for Fields:
     * ... this.str.concat(String) ...        -- String
     * ... this.str.compareTo(String) ...     -- integer
     * ... this.str.toLowerCase() ...        -- String
     *Parameters:
     * str ... String
     */

    // TODO Auto-generated method stub
    return this.rest.mergeItems(str) && str.toLowerCase().compareTo(this.first.toLowerCase()) <= 0;
  }

  // reverse the order of the strings in this list
  public ILoString reverse() {
    return this.reverseAcc(new MtLoString());
  }

  // help for reversing this list of strings
  // accumulator: keeps track of the reversed list so far
  public ILoString reverseAcc(ILoString acc) {
    /*Methods:
     * ... this.combine() ...     -- String
     * ... this.sort()...         -- ILoString
     * ... this.isSorted() ...    -- boolean
     * ... this.interleave(ILoString)...         -- ILoString
     * ... this.merge(ILoString)...     -- ILoString
     * ... this.reverse()...         -- ILoString
     * ... this.isDoubledList()...    -- boolean
     * ... this.isPalindromeList()...         -- boolean
     * ... this.sorting(String)...         -- ILoString
     * ... this.doesWordComeAfter(String) ...    -- boolean
     * ... this.mergeItems(String, ILoString)...     -- ILoString
     * ... this.reverseAcc(ILoString)...         -- ILoString
     * ... this.isDoubledList()...    -- boolean
     *Method for Fields:
     * ... this.acc.combine() ...        -- String
     * ... this.acc.sort()...         -- ILoString
     * ... this.acc.isSorted() ...    -- boolean
     * ... this.acc.interleave(ILoString)...         -- ILoString
     * ... this.acc.merge(ILoString)...     -- ILoString
     * ... this.acc.reverse()...         -- ILoString
     * ... this.acc.checkWords(String)...    -- boolean
     * ... this.acc.isPalindromeList()...         -- boolean
     * ... this.acc.sorting(String)...         -- ILoString
     * ... this.acc.doesWordComeAfter(String) ...    -- boolean
     * ... this.acc.mergeItems(String, ILoString)...     -- ILoString
     * ... this.acc.reverseAcc(ILoString)...         -- ILoString
     * ... this.acc.isDoubledList()...    -- boolean
     *Parameters:
     * acc ... ILoString
     */
    return this.rest.reverseAcc(new ConsLoString(this.first, acc));
  }

  // that determines if this list contains pairs of identical strings
  public boolean isDoubledList() {
    // TODO Auto-generated method stub
    return this.rest.checkWords(this.first);
  }

  // checks to see if the first and second strings are the same,
  // the third and fourth are the same,
  // the fifth and sixth are the same, and etc.
  public boolean checkWords(String str) {
    /*Methods:
     * ... this.concat(String) ...        -- String
     *Method for Fields:
     * ... this.str.concat(String) ...        -- String
     * ... this.str.compareTo(String) ...     -- integer
     * ... this.str.toLowerCase() ...        -- String
     * ... this.str.toLowerCase() ...        -- String
     * ... this.str.equals(String) ...     -- boolean
     *Parameters:
     * str ... String
     */
    if (str.equals(this.first)) {
      return this.rest.isDoubledList();
    }
    else {
      return false;
    }
  }

  // determines whether this list contains the same words reading the list in
  // either order.
  public boolean isPalindromeList() {
    // TODO Auto-generated method stub
    return this.interleave(this.reverse()).isDoubledList();
  }
}

// to represent examples for lists of strings
class ExamplesStrings {

  // Example of an Empty List of Strings
  MtLoString empty = new MtLoString();

  // Examples of Lists of Strings
  ILoString mary = new ConsLoString("Mary ", new ConsLoString("had ", new ConsLoString("a ",
      new ConsLoString("little ", new ConsLoString("lamb.", new MtLoString())))));
  ILoString cousin = new ConsLoString("My ", new ConsLoString("cousin ", new ConsLoString("went ",
      new ConsLoString("to ", new ConsLoString("El-jefes.", new MtLoString())))));
  ILoString friend = new ConsLoString("My ", new ConsLoString("friend ", new ConsLoString("bought ",
      new ConsLoString("an ", new ConsLoString("nft.", new MtLoString())))));
  ILoString aphlabetical = new ConsLoString("a ", new ConsLoString("b ",
      new ConsLoString("c ", new ConsLoString("d ", new ConsLoString("e ", new MtLoString())))));
  ILoString fruits = new ConsLoString("apple ",
      new ConsLoString("bananna ", new ConsLoString("citrus ",
          new ConsLoString("darian ", new ConsLoString("eggplant ", new MtLoString())))));
  ILoString colors = new ConsLoString("black ", new ConsLoString("blue ", new ConsLoString("green ",
      new ConsLoString("red ", new ConsLoString("yellow ", new MtLoString())))));

  // test the method combine for the lists of Strings
  boolean testCombine(Tester t) {
    return t.checkExpect(this.mary.combine(), "Mary had a little lamb.")
        && t.checkExpect(this.cousin.combine(), "My cousin went to El-jefes.")
        && t.checkExpect(this.friend.combine(), "My friend bought an nft.")
        && t.checkExpect(this.aphlabetical.combine(), "a b c d e ");
  }

  // test the method sort for the list of Strings
  boolean testSort(Tester t) {
    return t.checkExpect(this.mary.sort(),
        new ConsLoString("a ",
            new ConsLoString("had ",
                new ConsLoString("lamb.",
                    new ConsLoString("little ", new ConsLoString("Mary ", new MtLoString()))))))
        && t.checkExpect(this.cousin.sort(),
            new ConsLoString("cousin ",
                new ConsLoString("El-jefes.",
                    new ConsLoString("My ",
                        new ConsLoString("to ", new ConsLoString("went ", new MtLoString()))))))
        && t.checkExpect(this.friend.sort(),
            new ConsLoString("an ",
                new ConsLoString("bought ",
                    new ConsLoString("friend ",
                        new ConsLoString("My ", new ConsLoString("nft.", new MtLoString()))))))
        && t.checkExpect(this.empty.sort(), new MtLoString());
  }

  // test the helper method sorting for the method sort for list of Strings
  boolean testSorting(Tester t) {
    return t.checkExpect(this.mary.sorting("joe"),
        new ConsLoString("joe",
            new ConsLoString("Mary ", new ConsLoString("had ",
                new ConsLoString("a ",
                    new ConsLoString("little ", new ConsLoString("lamb.", new MtLoString())))))))
        && t.checkExpect(this.cousin.sorting("an"),
            new ConsLoString("an", new ConsLoString("My ", new ConsLoString("cousin ",
                new ConsLoString("went ",
                    new ConsLoString("to ", new ConsLoString("El-jefes.", new MtLoString())))))))
        && t.checkExpect(this.friend.sorting("free"),
            new ConsLoString("free",
                new ConsLoString("My ",
                    new ConsLoString("friend ",
                        new ConsLoString("bought ",
                            new ConsLoString("an ", new ConsLoString("nft.", new MtLoString())))))))
        && t.checkExpect(this.empty.sorting("j"), new ConsLoString("j", new MtLoString()));
  }

  // test the method isSorted for the list of Strings
  boolean testisSorted(Tester t) {
    return t.checkExpect(this.mary.isSorted(), false)
        && t.checkExpect(this.cousin.isSorted(), false)
        && t.checkExpect(this.friend.isSorted(), false)
        && t.checkExpect(this.aphlabetical.isSorted(), true)
        && t.checkExpect(this.empty.isSorted(), true);
  }

  // test the helper method doesWordComeAfter for the method isSorted for list of
  // Strings
  boolean testDoesWordComeAfter(Tester t) {
    return t.checkExpect(this.mary.doesWordComeAfter("My"), false)
        && t.checkExpect(this.cousin.doesWordComeAfter("cousin"), true)
        && t.checkExpect(this.friend.doesWordComeAfter("girl"), true)
        && t.checkExpect(this.aphlabetical.doesWordComeAfter("e"), false)
        && t.checkExpect(this.empty.doesWordComeAfter(""), true);
  }

  // test the method interleave for the list of Strings
  boolean testInterleave(Tester t) {
    return t
        .checkExpect(this.mary.interleave(this.cousin),
            new ConsLoString("Mary ",
                new ConsLoString("My ",
                    new ConsLoString("had ",
                        new ConsLoString("cousin ",
                            new ConsLoString("a ", new ConsLoString("went ",
                                new ConsLoString("little ", new ConsLoString("to ",
                                    new ConsLoString("lamb.",
                                        new ConsLoString("El-jefes.", new MtLoString())))))))))))
        && t.checkExpect(this.friend.interleave(this.cousin),
            new ConsLoString("My ",
                new ConsLoString("My ",
                    new ConsLoString("friend ",
                        new ConsLoString("cousin ",
                            new ConsLoString("bought ", new ConsLoString("went ",
                                new ConsLoString("an ", new ConsLoString("to ",
                                    new ConsLoString("nft.",
                                        new ConsLoString("El-jefes.", new MtLoString())))))))))))
        && t.checkExpect(this.cousin.interleave(this.aphlabetical),
            new ConsLoString("My ",
                new ConsLoString("a ",
                    new ConsLoString("cousin ",
                        new ConsLoString("b ",
                            new ConsLoString("went ",
                                new ConsLoString("c ",
                                    new ConsLoString("to ",
                                        new ConsLoString("d ",
                                            new ConsLoString("El-jefes.",
                                                new ConsLoString("e ", new MtLoString())))))))))))
        && t.checkExpect(this.empty.interleave(this.empty), new MtLoString());
  }

  // test the method merge for the list of Strings
  boolean testMerge(Tester t) {
    return t
        .checkExpect(this.aphlabetical.merge(this.aphlabetical), new ConsLoString("a ",
            new ConsLoString("a ", new ConsLoString("b ", new ConsLoString("b ",
                new ConsLoString("c ", new ConsLoString("c ", new ConsLoString("d ",
                    new ConsLoString("d ",
                        new ConsLoString("e ", new ConsLoString("e ", new MtLoString())))))))))))
        && t.checkExpect(this.aphlabetical.merge(this.fruits),
            new ConsLoString("a ",
                new ConsLoString("apple ",
                    new ConsLoString("b ",
                        new ConsLoString("bananna ",
                            new ConsLoString("c ", new ConsLoString("citrus ",
                                new ConsLoString("d ", new ConsLoString("darian ",
                                    new ConsLoString("e ",
                                        new ConsLoString("eggplant ", new MtLoString())))))))))))
        && t.checkExpect(this.aphlabetical.merge(this.colors),
            new ConsLoString("a ", new ConsLoString("b ",
                new ConsLoString("black ", new ConsLoString("blue ", new ConsLoString("c ",
                    new ConsLoString("d ",
                        new ConsLoString("e ", new ConsLoString("green ", new ConsLoString("red ",
                            new ConsLoString("yellow ", new MtLoString())))))))))));
  }

  // test the helper method mergeItems for the method merge for list of Strings
  boolean testMergeItems(Tester t) {
    return t.checkExpect(this.aphlabetical.mergeItems("apple"), false)
        && t.checkExpect(this.colors.mergeItems("apple"), true)
        && t.checkExpect(this.fruits.mergeItems("apple"), true);
  }

  // test the method reverse for the list of Strings
  boolean testReverse(Tester t) {
    return t.checkExpect(this.mary.reverse(),
        new ConsLoString("lamb.",
            new ConsLoString("little ",
                new ConsLoString("a ",
                    new ConsLoString("had ", new ConsLoString("Mary ", new MtLoString()))))))
        && t.checkExpect(this.cousin.reverse(),
            new ConsLoString("El-jefes.",
                new ConsLoString("to ",
                    new ConsLoString("went ",
                        new ConsLoString("cousin ", new ConsLoString("My ", new MtLoString()))))))
        && t.checkExpect(this.friend.reverse(),
            new ConsLoString("nft.",
                new ConsLoString("an ",
                    new ConsLoString("bought ",
                        new ConsLoString("friend ", new ConsLoString("My ", new MtLoString()))))))
        && t.checkExpect(this.empty.reverse(), new MtLoString());
  }

  // test the helper method reverseAcc for the method reverse for list of Strings
  boolean testReverseAcc(Tester t) {
    return t
        .checkExpect(this.mary.reverseAcc(this.mary),
            new ConsLoString("lamb.",
                new ConsLoString("little ",
                    new ConsLoString("a ",
                        new ConsLoString("had ",
                            new ConsLoString("Mary ",
                                new ConsLoString("Mary ",
                                    new ConsLoString("had ", new ConsLoString("a ",
                                        new ConsLoString("little ",
                                            new ConsLoString("lamb.", new MtLoString())))))))))))
        && t.checkExpect(this.cousin.reverseAcc(this.cousin),
            new ConsLoString("El-jefes.",
                new ConsLoString("to ",
                    new ConsLoString("went ",
                        new ConsLoString("cousin ",
                            new ConsLoString("My ", new ConsLoString("My ",
                                new ConsLoString("cousin ", new ConsLoString("went ",
                                    new ConsLoString("to ",
                                        new ConsLoString("El-jefes.", new MtLoString())))))))))))
        && t.checkExpect(this.friend.reverseAcc(this.friend), new ConsLoString("nft.",
            new ConsLoString("an ", new ConsLoString("bought ", new ConsLoString("friend ",
                new ConsLoString("My ", new ConsLoString("My ", new ConsLoString("friend ",
                    new ConsLoString("bought ",
                        new ConsLoString("an ", new ConsLoString("nft.", new MtLoString())))))))))))
        && t.checkExpect(this.empty.reverseAcc(this.empty), new MtLoString());
  }

  // test the method doubledList for the list of Strings
  boolean testIsDoubledList(Tester t) {
    return t.checkExpect(this.mary.isDoubledList(), false)
        && t.checkExpect(this.cousin.isDoubledList(), false)
        && t.checkExpect(this.friend.isDoubledList(), false)
        && t.checkExpect(this.aphlabetical.isDoubledList(), false)
        && t.checkExpect(this.empty.isDoubledList(), true);
  }

  // test the helper method checkWords for the method doubledList for list of
  // Strings
  boolean testCheckWords(Tester t) {
    return t.checkExpect(this.mary.checkWords("mary"), false)
        && t.checkExpect(this.cousin.checkWords("My "), false)
        && t.checkExpect(this.friend.checkWords("dude"), false)
        && t.checkExpect(this.aphlabetical.checkWords("laugh"), false)
        && t.checkExpect(this.empty.checkWords(""), false);
  }

  // test the method isPalindromeList for the list of Strings
  boolean TestIsPalindromeList(Tester t) {
    return t.checkExpect(this.mary.isPalindromeList(), false)
        && t.checkExpect(this.cousin.isPalindromeList(), false)
        && t.checkExpect(this.friend.isPalindromeList(), false)
        && t.checkExpect(this.aphlabetical.isPalindromeList(), true)
        && t.checkExpect(this.empty.isPalindromeList(), false);
  }
}