// Represents functions of signature A -> R, for some argument type A and
// result type R
interface IFunc<A, R> {
  R apply(A input);
}

// generic list
interface IList<T> {
  // map over a list, and produce a new list with a (possibly different)
  // element type
  <U> IList<U> map(IFunc<T, U> f);

  <U> U foldr(IFunc2<T, U, U> fun, U base);
}

// empty generic list
class MtList<T> implements IList<T> {
  public <U> IList<U> map(IFunc<T, U> f) {
    return new MtList<U>();
  }

  @Override
  public <U> U foldr(IFunc2<T, U, U> fun, U base) {
    // TODO Auto-generated method stub
    return base;
  }
}

// non-empty generic list
class ConsList<T> implements IList<T> {
  T first;
  IList<T> rest;

  ConsList(T first, IList<T> rest) {
    this.first = first;
    this.rest = rest;
  }

  public <U> IList<U> map(IFunc<T, U> f) {
    return new ConsList<U>(f.apply(this.first), this.rest.map(f));
  }

  public <U> U foldr(IFunc2<T, U, U> fun, U base) {
    // TODO Auto-generated method stub
    return fun.apply(this.first, this.rest.foldr(fun, base));
  }
}
