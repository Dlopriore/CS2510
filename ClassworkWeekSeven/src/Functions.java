
interface IFunc<X, Y> {
  //applies an operation to the given x and produces a Y
  Y apply(X x);
}

interface IShapeVisitor<R> {
  R visitCircle(Circle c);

  R visitSquare(Square s);

  R visitCombo(Combo c);
}

class ShapeToArea implements IShapeVisitor<Double>, IFunc<IShape, Double> {

  @Override
  public Double visitCircle(Circle c) {
    return c.radius * c.radius * Math.PI;
  }

  @Override
  public Double visitSquare(Square s) {
    return s.size * s.size * 1.0;
  }

  @Override
  public Double visitCombo(Combo c) {
    return 1.0;
  }

  @Override
  public Double apply(IShape x) {
    return x.accept(this);
  }
}

class BiggerThan implements IShapeVisitor<Boolean> {
  IShape other;

  BiggerThan(IShape other) {
    this.other = other;
  }

  @Override
  public Boolean visitCircle(Circle c) {
    return c.accept(new ShapeToArea()) > other.accept(new ShapeToArea());
  }

  @Override
  public Boolean visitSquare(Square s) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Boolean visitCombo(Combo c) {
    // TODO Auto-generated method stub
    return null;
  }

}

interface IPred<T> {
  //asks a question about a given t
  boolean apply(T t);
}

class EvenHuh implements IPred<Integer> {

  @Override
  public boolean apply(Integer t) {
    return t % 2 == 0;
  }

}

interface IComparator<T> {
  //determines whether t1 comes before t2
  int compare(T t1, T t2);
}

interface IFunc2<X, Y, Z> {
  //combines x and y and produces a Z
  Z apply(X x, Y y);
}

class Sum implements IFunc2<Integer, Integer, Integer> {

  //sums the given numbers
  public Integer apply(Integer x, Integer y) {
    return x + y;
  }

}

interface IListVisitor<U, V> {
  V visitEmpty(MtList<U> mt);

  V visitConsList(ConsList<U> cons);
}

class FilterVisitor<U> implements IListVisitor<U, IList<U>> {
  IPred<U> pred;

  FilterVisitor(IPred<U> pred) {
    this.pred = pred;
  }

  @Override
  public IList<U> visitEmpty(MtList<U> mt) {
    // TODO Auto-generated method stub
    return new MtList<U>();
  }

  @Override
  public IList<U> visitConsList(ConsList<U> cons) {
    // TODO Auto-generated method stub
    if (this.pred.apply(cons.first)) {
      return new ConsList<U>(cons.first, cons.rest.accept(this));
    }
    else {
      return cons.rest.accept(this);
    }
  }

}
