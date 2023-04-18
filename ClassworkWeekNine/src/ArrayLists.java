import tester.Tester;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.BiFunction;
import java.util.function.Function;

class ArrayUtils {
  //EFFECT: swaps the items at the given indices
  <T> void swap(ArrayList<T> alist, int index1, int index2) {
    //T oldItemAtIndex1 = alist.get(index1);
    //T oldItemAtIndex2 = alist.get(index2);

    //alist.set(index2, alist.get(index1));
    //alist.set(index1, oldItemAtIndex2);

    alist.set(index2, alist.set(index1, alist.get(index2)));
  }

  <T, U> ArrayList<U> map(ArrayList<T> alist, Function<T, U> fun) {
    return this.mapHelp(alist, fun, new ArrayList<U>(), 0);
  }

  <T, U> ArrayList<U> mapHelp(ArrayList<T> alist, Function<T, U> fun, ArrayList<U> result,
      int current) {
    if (current >= alist.size()) {
      return result;
    }
    result.add(fun.apply(alist.get(current)));
    return this.mapHelp(alist, fun, result, current + 1);
  }

  <T, U> ArrayList<U> map2(ArrayList<T> alist, Function<T, U> fun) {
    ArrayList<U> result = new ArrayList<U>();

    for (T t : alist) {
      result.add(fun.apply(t));
    }

    return result;
  }

  <T, U> U foldl(ArrayList<T> alist, BiFunction<T, U, U> fun, U base) {
    U result = base;

    for (T t : alist) {
      result = fun.apply(t, result);
    }

    return result;
  }

  <T, U> U foldr(ArrayList<T> alist, BiFunction<T, U, U> fun, U base) {
    U result = base;

    ArrayList<T> reversed = new ArrayList<T>();

    for (T t : alist) {
      reversed.add(0, t);
    }

    for (T t : reversed) {
      result = fun.apply(t, result);
    }

    return result;
  }

  //returns the index of the given item in the list or -1 if not found
  <T> int find(ArrayList<T> alist, T target, Comparator<T> comp) {
    return this.findHelp(alist, target, comp, 0);
  }

  //helps find the given item in the list by keeping track of the current index
  <T> int findHelp(ArrayList<T> alist, T target, Comparator<T> comp, int currentIndex) {
    if (currentIndex >= alist.size()) {
      return -1;
    }
    else if (comp.compare(alist.get(currentIndex), target) == 0) {
      return currentIndex;
    }
    else {
      return this.findHelp(alist, target, comp, currentIndex + 1);
    }
  }

  //returns the index of the given item in the list or -1 if not found
  <T> int find_v2(ArrayList<T> alist, T target, Comparator<T> comp) {
    int result = -1;

    for (int i = 0; i < alist.size(); i = i + 1) {
      if (comp.compare(target, alist.get(i)) == 0) {
        return i;
      }
    }

    return result;
  }

  //returns the index of the given item in the *sorted* list or -1 if not found
  <T> int binarySearch(ArrayList<T> alist, T target, Comparator<T> comp) {
    return this.binaryHelp(alist, target, comp, 0, alist.size() - 1);
  }

  //returns the index of the given item in the *sorted* list or -1 if not found
  <T> int binaryHelp(ArrayList<T> alist, T target, Comparator<T> comp, int lo, int hi) {
    if (lo > hi) {
      return -1;
    }

    int mid = (lo + hi) / 2;

    if (comp.compare(alist.get(mid), target) == 0) {
      return mid;
    }

    else if (comp.compare(alist.get(mid), target) > 0) {
      return this.binaryHelp(alist, target, comp, lo, mid - 1);
    }
    else {
      return this.binaryHelp(alist, target, comp, mid + 1, hi);
    }
  }

  //find the index of the min item according to the given Comparator
  <T> int findIndexOfMin(ArrayList<T> alist, Comparator<T> comp) {
    if (alist.size() == 0) {
      throw new RuntimeException("no min of an empty list");
    }

    int min = 0;

    for (int i = 0; i < alist.size(); i = i + 1) {
      if (comp.compare(alist.get(min), alist.get(i)) > 0) {
        min = i;
      }
    }

    return min;
  }

  //builds a list using the given function
  <T> ArrayList<T> buildList(int n, Function<Integer, T> fun) {
    ArrayList<T> result = new ArrayList<T>();
    //i += 1 does same thing as i + 1;
    for (int i = 0; i < n; i = i + 1) {
      result.add(fun.apply(i));
    }
    return result;
  }

  ArrayList<Card> makeDeck() {
    ArrayList<String> suits = new ArrayList<String>(
        Arrays.asList("hearts", "spades", "diamonds", "cloves"));
    ArrayList<String> values = new ArrayList<String>(Arrays.asList("ace", "two", "three", "four",
        "five", "six", "seven", "eight", "nine", "ten", "jack", "king", "queen"));
    ArrayList<Card> deck = new ArrayList<Card>();
    for (int i = 0; i < 4; i++) {

      for (int j = 0; j < 13; j++) {
        deck.add(new Card(values.get(j), values.get(i)));
      }
    }
    return deck;
  }
}

class Utils {

  //does it reach n? (n must be greater than 0
  boolean coliatz(int n) {
    int step = 0;
    while (n > 1) {
      if (n % 2 == 0) {
        n = n / 2;
      }
      else {
        n = 1 + n * 3;
      }
      step += 0;
    }
    return true;
  }
}

class Examples {
  ArrayList<Integer> ints;
  ArrayList<String> strings;
  ArrayUtils au = new ArrayUtils();
  ArrayList<CartPt> points;

  // i represents rows and j represent columns

  void initData() {
    this.ints = new ArrayList<Integer>();
    this.strings = new ArrayList<String>();
    this.points = new ArrayList<CartPt>(Arrays.asList(new CartPt(30, 90), new CartPt(100, 22)));
  }

  void testShift(Tester t) {
    this.initData();

    for (CartPt p : this.points) {
      p.shift(2, 3);
    }

    for (CartPt p : this.points) {
      System.out.println(p);
    }
  }

  void test2DArray(Tester t) {
    ArrayList<ArrayList<CartPt>> test2DList = new ArrayList<ArrayList<CartPt>>();
    ArrayList<CartPt> temp;
    for (int i = 0; i < 3; i++) {
      temp = new ArrayList<CartPt>();
      for (int j = 0; j < 2; j++) {
        temp.add(new CartPt(i, j));
      }
      test2DList.add(temp);
    }

    for (int i = 0; i < test2DList.size(); i++) {
      System.out.println(test2DList.get(i));
    }

    for (int i = 0; i < test2DList.size(); i++) {
      for (int j = 0; j < 3; j++) {
        test2DList.get(i).get(j).shift(5, 10);
      }
    }

    for (int i = 0; i < test2DList.size(); i++) {
      System.out.println(test2DList.get(i));
    }
  }

  void testBinarySearch(Tester t) {
    this.initData();
    this.strings = new ArrayList<String>(Arrays.asList("a", "bb", "ccc"));

    t.checkExpect(this.au.binarySearch(this.strings, "bb", (s1, s2) -> s1.compareTo(s2)), 1);
    t.checkExpect(this.au.binarySearch(this.strings, "bbb", (s1, s2) -> s1.compareTo(s2)), -1);

  }

  void testSwap(Tester t) {
    this.initData();
    this.ints = new ArrayList<Integer>(Arrays.asList(10, 20, 30, 40, 50));

    this.au.swap(this.ints, 2, 4);
    t.checkExpect(this.ints.get(2), 50);
    t.checkExpect(this.ints.get(4), 30);

    for (int i = 10; i > 0; i = i - 1) {
      System.out.println(i);
    }
  }

  void testFold(Tester t) {
    this.initData();
    this.strings = new ArrayList<String>(Arrays.asList("a", "bb", "ccc"));
    t.checkExpect(this.au.foldl(this.strings, (s, i) -> s.length() + i, 0), 6);
  }

  void testMap(Tester t) {
    this.initData();
    this.ints = new ArrayList<Integer>(Arrays.asList(10, 20, 30, 40, 50));
    t.checkExpect(this.au.map(this.ints, i -> i + 1),
        new ArrayList<Integer>(Arrays.asList(11, 21, 31, 41, 51)));
    t.checkExpect(this.au.map(new ArrayList<Integer>(), i -> i + 1), new ArrayList<Integer>());

    t.checkExpect(this.au.map2(this.ints, i -> i + 1),
        new ArrayList<Integer>(Arrays.asList(11, 21, 31, 41, 51)));
    t.checkExpect(this.au.map2(new ArrayList<Integer>(), i -> i + 1), new ArrayList<Integer>());
  }

  void testArrayList(Tester t) {
    this.initData();
    this.ints.add(1);
    this.ints.add(2);
    t.checkExpect(this.ints.get(0), 1);
    this.ints.set(1, 3);
    t.checkExpect(this.ints.set(1, 4), 3);

  }
}