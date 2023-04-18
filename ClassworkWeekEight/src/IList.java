import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import tester.Tester;

interface IList<E> {
  //filter this list by the given predicate
  IList<E> filter(Predicate<E> pred);

  //applies a function to every member of the list and produces a list of the results
  <T> IList<T> map(Function<E, T> fun);

  //modify an item in the list with the given function
  void modify(Predicate<E> pred, Consumer<E> fun);
}

//represents an empty list of E
class MtList<E> implements IList<E> {

  @Override
  public IList<E> filter(Predicate<E> pred) {
    return this;
  }

  @Override
  public <T> IList<T> map(Function<E, T> fun) {
    return new MtList<T>();
  }

  @Override
  public void modify(Predicate<E> pred, Consumer<E> fun) {

  }

  //remove the person with the given name with this list
  void removePerson(String name) {

  }

}

//represents a non-empty list of E
class ConsList<E> implements IList<E> {
  E first;
  IList<E> rest;

  ConsList(E first, IList<E> rest) {
    this.first = first;
    this.rest = rest;
  }

  @Override
  public IList<E> filter(Predicate<E> pred) {
    if (pred.test(this.first)) {
      return new ConsList<E>(this.first, this.rest.filter(pred));
    }
    else {
      return this.rest.filter(pred);
    }
  }

  //apply the given operation to every member of this list
  public <T> IList<T> map(Function<E, T> fun) {
    return new ConsList<T>(fun.apply(this.first), this.rest.map(fun));
  }

  @Override
  public void modify(Predicate<E> pred, Consumer<E> fun) {
    if (pred.test(this.first)) {
      fun.accept(this.first);
    }
    else {
      this.rest.modify(pred, fun);
    }

  }
}

class CheckName implements Predicate<Person> {
  String name;

  CheckName(String s) {
    this.name = s;
  }

  @Override
  public boolean test(Person t) {
    return t.name.equals(this.name);
  }
}

class NewNum implements Consumer<Person> {
  int newNum;

  NewNum(int n) {
    this.newNum = n;
  }

  @Override
  public void accept(Person t) {
    t.phone = this.newNum;

  }

}

class ExamplesLists {
  IList<String> mtStrings = new MtList<String>();
  IList<String> strings1 = new ConsList<String>("a", this.mtStrings);

  IList<Boolean> bools = new ConsList<Boolean>(true, new MtList<Boolean>());

  IList<Integer> ints = new ConsList<Integer>(1, new MtList<Integer>());

  IList<Double> doubles = new ConsList<Double>(1.0, new MtList<Double>());

  Person anne = new Person("Anne", 1234);
  Person bob = new Person("Bob", 3456);
  Person clyde = new Person("Clyde", 6789);
  Person dana = new Person("Dana", 1357);
  Person eric = new Person("Eric", 12469);
  Person frank = new Person("Frank", 7294);
  Person gail = new Person("Gail", 9345);
  Person henry = new Person("Henry", 8602);
  Person irene = new Person("Irene", 91302);
  Person jenny = new Person("Jenny", 8675309);

  IList<Person> friends, family, work;

  void initData() {
    anne = new Person("Anne", 1234);
    bob = new Person("Bob", 3456);
    clyde = new Person("Clyde", 6789);
    dana = new Person("Dana", 1357);
    eric = new Person("Eric", 12469);
    frank = new Person("Frank", 7294);
    gail = new Person("Gail", 9345);
    henry = new Person("Henry", 8602);
    irene = new Person("Irene", 91302);
    jenny = new Person("Jenny", 8675309);
    this.friends = new ConsList<Person>(this.anne,
        new ConsList<Person>(this.clyde,
            new ConsList<Person>(this.gail, new ConsList<Person>(this.frank,
                new ConsList<Person>(this.jenny, new MtList<Person>())))));
    this.family = new ConsList<Person>(this.anne,
        new ConsList<Person>(this.dana, new ConsList<Person>(this.frank, new MtList<Person>())));
    this.work = new ConsList<Person>(this.bob,
        new ConsList<Person>(this.clyde,
            new ConsList<Person>(this.dana,
                new ConsList<Person>(this.eric, new ConsList<Person>(this.henry,
                    new ConsList<Person>(this.irene, new MtList<Person>()))))));
  }

  void testChange(Tester t) {
    this.friends.modify(new CheckName("Frank"), new NewNum(321));
    t.checkExpect(this.frank.phone, 321);
    //need more tests to check the friends and family ILists. A find method for generic lists 
    //would be helpful! 
  }

}