import tester.Tester;

interface IList<E> {

  //accepts a visitor to the list 
  <X> X accept(IListVisitor<E, X> ilv);

  //filter this list by the given predicate
  IList<E> filter(IPred<E> pred);

  //applies a function to every member of the list and produces a list of the results
  <T> IList<T> map(IFunc<E, T> fun);

  <T> T foldr(IFunc2<E, T, T> fun, T base);
}

//represents an empty list of E
class MtList<E> implements IList<E> {

  @Override
  public IList<E> filter(IPred<E> pred) {
    return this;
  }

  //applies a function to every member of the list and produces a list of the results
  public <T> IList<T> map(IFunc<E, T> fun) {
    return new MtList<T>();
  }

  @Override
  public <T> T foldr(IFunc2<E, T, T> fun, T base) {
    return base;
  }

  @Override
  public <X> X accept(IListVisitor<E, X> ilv) {
    // TODO Auto-generated method stub
    return ilv.visitEmpty(this);
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
  public IList<E> filter(IPred<E> pred) {
    if (pred.apply(this.first)) {
      return new ConsList<E>(this.first, this.rest.filter(pred));
    }
    else {
      return this.rest.filter(pred);
    }
  }

  //apply the given operation to every member of this list
  public <T> IList<T> map(IFunc<E, T> fun) {
    return new ConsList<T>(fun.apply(this.first), this.rest.map(fun));
  }

  @Override
  public <T> T foldr(IFunc2<E, T, T> fun, T base) {
    return fun.apply(this.first, this.rest.foldr(fun, base));
  }

  @Override
  public <X> X accept(IListVisitor<E, X> ilv) {
    // TODO Auto-generated method stub
    return ilv.visitConsList(this);
  }
}

class ExamplesLists {
  IList<String> mtStrings = new MtList<String>();
  IList<String> strings1 = new ConsList<String>("a", this.mtStrings);

  IList<Boolean> bools = new ConsList<Boolean>(true, new MtList<Boolean>());

  IList<Integer> ints = new ConsList<Integer>(1, new MtList<Integer>());

  IList<Double> doubles = new ConsList<Double>(1.0, new MtList<Double>());

  boolean testFold(Tester t) {
    return t.checkExpect(this.ints.foldr(new Sum(), 0), 1)
        && t.checkExpect(this.ints.filter(i -> i % 2 == 0), new MtList<Integer>());
  }

}