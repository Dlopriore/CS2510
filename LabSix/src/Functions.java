interface IFunc<X, Y> {
  //applies an operation to the given x and produces a Y
  Y apply(X x);
}

interface IPred<T> {
  //asks a question about a given t
  boolean apply(T t);
}

interface IJSONVistor<T> extends IFunc<JSON, T> {
  public T apply(JSON input);

  T visitJSONBlank(JSONBlank b);

  T visitJSONNumber(JSONNumber n);

  T visitJSONBool(JSONBool b);

  T visitJSONString(JSONString s);
}

interface IComparator<T> {
  //asks a question about a given t
  int compare(T t1, T t2);
}

interface IFunc2<X, Y, Z> {
  //applies an operation to the given x and produces a Y
  Z apply(X x, Y y);
}

class JSONToNumber implements IJSONVistor<Integer> {

  @Override
  public Integer visitJSONBlank(JSONBlank b) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public Integer visitJSONNumber(JSONNumber n) {
    // TODO Auto-generated method stub
    return n.number;
  }

  @Override
  public Integer visitJSONBool(JSONBool b) {
    // TODO Auto-generated method stub
    if (b.bool) {
      return 1;
    }
    else {
      return 0;
    }
  }

  @Override
  public Integer visitJSONString(JSONString s) {
    // TODO Auto-generated method stub
    return s.str.length();
  }

  @Override
  public Integer apply(JSON input) {
    // TODO Auto-generated method stub
    return this.apply(input);
  }

}

public Integer visitJSONString(JSONString s) {
  // TODO Auto-generated method stub
  return s.str.length();
}