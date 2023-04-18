import java.util.function.BiFunction;
import java.util.function.Function;

//Visitors.java Final
//Dante LoPriore

import tester.Tester;

//to represent an arith
interface IArith {

  //To return the result of applying the given visitor to this IArith
  <R> R accept(IArithVisitor<R> av);
}

//to represent an ArithVisitor
interface IArithVisitor<R> extends Function<IArith, R> {

  //ask a question about a given IArith
  R apply(IArith input);

  //ask a question about a given Const
  R constVistor(Const c);

  //ask a question about a given UnaryFormula
  R unaryFormulaVistor(UnaryFormula uf);

  //ask a question about a given UnaryFormula
  R binaryFormulaVistor(BinaryFormula bf);
}

//representing plus or addition
class Plus implements BiFunction<Double, Double, Double> {

  //to compute the sum of two given doubles 
  public Double apply(Double t, Double u) {
    // TODO Auto-generated method stub
    return t + u;
  }
}

//representing minus or subtract
class Minus implements BiFunction<Double, Double, Double> {

  //to subtract a given double by another given double 
  public Double apply(Double t, Double u) {
    // TODO Auto-generated method stub
    return t - u;
  }
}

//representing mul or multiply
class Mul implements BiFunction<Double, Double, Double> {

  //to multiply a given double by another given double 
  public Double apply(Double t, Double u) {
    // TODO Auto-generated method stub
    return t * u;
  }

}

//representing div or divide
class Div implements BiFunction<Double, Double, Double> {

  //to divide a given double by another given double 
  public Double apply(Double t, Double u) {
    // TODO Auto-generated method stub
    if (t == 0.0 && u == 0.0) {
      return 0.0;
    }
    else {
      return t / u;
    }
  }
}

//representing neg or negation
class Neg implements Function<Double, Double> {

  //to negate a given double or make a given double negative
  public Double apply(Double t) {
    // TODO Auto-generated method stub
    return -t;
  }
}

//representing sqr or squaring a value
class Sqr implements Function<Double, Double> {

  //to square a given double or multiply a given double by itself
  public Double apply(Double t) {
    // TODO Auto-generated method stub
    return t * t;
  }
}

//to visit an IArith and evaluates the tree to a Double answer.
class EvalVisitor implements IArithVisitor<Double> {

  //ask a question which is to evaluate a tree to a double answer about a given IArith
  public Double apply(IArith input) {
    return input.accept(this);
  }

  //ask a question which is to evaluate a tree to a double answer about a given Const
  public Double constVistor(Const c) {
    // TODO Auto-generated method stub
    return c.num;
  }

  //ask a question which is to evaluate a tree to a double answer about a given UnaryFormula
  public Double unaryFormulaVistor(UnaryFormula uf) {
    // TODO Auto-generated method stub
    return uf.func.apply(uf.child.accept(this));
  }

  //ask a question which is to evaluate a tree to a double answer about a given BinaryFormula
  public Double binaryFormulaVistor(BinaryFormula bf) {
    // TODO Auto-generated method stub
    return bf.func.apply(bf.left.accept(this), bf.right.accept(this));
  }

}

//visits an IArith and produces a String showing the 
//fully-parenthesized expression in Racket-like prefix notation
class PrintVisitor implements IArithVisitor<String> {

  //ask a question which is to produce a string in racket style about a given IArith
  public String apply(IArith input) {
    return input.accept(this);
  }

  //ask a question which is to produce a string in racket style about a given Const
  public String constVistor(Const c) {
    // TODO Auto-generated method stub
    return Double.toString(c.num);
  }

  //ask a question which is to produce a string in racket style about a given UnaryFormula
  public String unaryFormulaVistor(UnaryFormula uf) {
    // TODO Auto-generated method stub
    return "(" + uf.name + " " + uf.child.accept(this) + ")";
  }

  //ask a question which is to produce a string in racket style about a given BinaryFormula
  public String binaryFormulaVistor(BinaryFormula bf) {
    // TODO Auto-generated method stub
    return "(" + bf.name + " " + bf.left.accept(this) + " " + bf.right.accept(this) + ")";
  }
}

//to produce another arith where every const in the tree has been doubled.
class DoublerVisitor implements IArithVisitor<IArith> {

  //ask a question which is to produce an arith that every const is doubled about a given IArith
  public IArith apply(IArith input) {
    return input.accept(this);
  }

  //ask a question which is to produce an arith that every const is doubled about a given Const
  public IArith constVistor(Const c) {
    // TODO Auto-generated method stub
    return new Const(c.num * 2.0);
  }

  //ask a question which is to produce an 
  //arith that every const is doubled about a given UnaryFormula
  public IArith unaryFormulaVistor(UnaryFormula uf) {
    // TODO Auto-generated method stub
    return new UnaryFormula(uf.func, uf.name, uf.child.accept(this));
  }

  //ask a question which is to produce an 
  //arith that every const is doubled about a given BinaryFormula
  public IArith binaryFormulaVistor(BinaryFormula bf) {
    // TODO Auto-generated method stub
    return new BinaryFormula(bf.func, bf.name, bf.left.accept(this), bf.right.accept(this));
  }
}

//to determine whether the given arith is negative
class NoNegativeResults implements IArithVisitor<Boolean> {

  //ask a question which is to determine if an arith is negative about a given IArith
  public Boolean apply(IArith input) {
    return input.accept(this);
  }

  //ask a question which is to determine if an arith is negative about a given Const
  public Boolean constVistor(Const c) {
    // TODO Auto-generated method stub
    return c.num >= 0;
  }

  //ask a question which is to determine if an arith is negative about a given UnaryFormula
  public Boolean unaryFormulaVistor(UnaryFormula uf) {
    // TODO Auto-generated method stub
    return uf.child.accept(this) && uf.accept(new EvalVisitor()) >= 0;
  }

  //ask a question which is to determine if an arith is negative about a given BinaryFormula
  public Boolean binaryFormulaVistor(BinaryFormula bf) {
    // TODO Auto-generated method stub
    return bf.left.accept(this) && bf.right.accept(this) && bf.accept(new EvalVisitor()) >= 0;
  }
}

// to represent an const 
class Const implements IArith {
  double num;

  //constructor
  Const(double num) {
    this.num = num;
  }

  //To return the result of applying the given visitor to this Const
  public <R> R accept(IArithVisitor<R> av) {
    // TODO Auto-generated method stub
    return av.constVistor(this);
  }
}

//to represent an UnaryFormula 
class UnaryFormula implements IArith {
  Function<Double, Double> func;
  String name;
  IArith child;

  //constructor
  UnaryFormula(Function<Double, Double> func, String name, IArith child) {
    this.func = func;
    this.name = name;
    this.child = child;
  }

  //To return the result of applying the given visitor to this UnaryFormula
  public <R> R accept(IArithVisitor<R> av) {
    // TODO Auto-generated method stub
    return av.unaryFormulaVistor(this);
  }
}

//to represent an BinaryFormula 
class BinaryFormula implements IArith {
  BiFunction<Double, Double, Double> func;
  String name;
  IArith left;
  IArith right;

  //constructor
  BinaryFormula(BiFunction<Double, Double, Double> func, String name, IArith left, IArith right) {
    this.func = func;
    this.name = name;
    this.left = left;
    this.right = right;
  }

  //To return the result of applying the given visitor to this BinaryFormula
  public <R> R accept(IArithVisitor<R> av) {
    // TODO Auto-generated method stub
    return av.binaryFormulaVistor(this);
  }
}

//Examples and Tests that represent Visitors.java
class ExamplesIAriths {

  //constructor
  ExamplesIAriths() {

  }

  //Examples of Const
  Const c1 = new Const(0.0);
  Const c2 = new Const(4.0);
  Const c3 = new Const(4.5);
  Const c4 = new Const(89.0);
  Const c5 = new Const(21.0);
  Const c6 = new Const(-1400.0);
  Const c7 = new Const(-4.0);

  //Examples of IArth
  IArith con1 = new Const(0.0);
  IArith con2 = new Const(67.0);
  IArith con3 = new Const(11.0);
  IArith con4 = new Const(22.0);
  IArith con5 = new Const(333.0);
  IArith con6 = new Const(1400.0);
  IArith con7 = new Const(-1400.0);
  IArith con8 = new Const(-4.0);

  //Examples of UnaryFormula
  UnaryFormula uf1 = new UnaryFormula(new Sqr(), "sqr", this.con1);
  UnaryFormula uf2 = new UnaryFormula(new Neg(), "neg", this.con2);
  UnaryFormula uf3 = new UnaryFormula(new Sqr(), "sqr", this.con3);
  UnaryFormula uf4 = new UnaryFormula(new Neg(), "neg", this.con4);
  UnaryFormula uf5 = new UnaryFormula(new Sqr(), "sqr", this.con5);
  UnaryFormula uf6 = new UnaryFormula(new Neg(), "neg", this.con6);

  //Examples of BinaryFormula
  BinaryFormula bf1 = new BinaryFormula(new Plus(), "plus", this.con1, this.con2);
  BinaryFormula bf2 = new BinaryFormula(new Minus(), "minus", this.con3, this.con4);
  BinaryFormula bf3 = new BinaryFormula(new Mul(), "mul", this.con5, this.con6);
  BinaryFormula bf4 = new BinaryFormula(new Div(), "div", this.con1, this.con2);
  BinaryFormula bf5 = new BinaryFormula(new Plus(), "plus", this.con5, this.con2);
  BinaryFormula bf6 = new BinaryFormula(new Minus(), "minus", this.con2, this.con4);
  BinaryFormula bf7 = new BinaryFormula(new Mul(), "mul", this.con1, this.con6);
  BinaryFormula bf8 = new BinaryFormula(new Div(), "div", this.con2, this.con2);
  BinaryFormula bf9 = new BinaryFormula(new Plus(), "plus", this.con3, this.con2);
  BinaryFormula bf10 = new BinaryFormula(new Minus(), "minus", this.con1, this.con4);
  BinaryFormula bf11 = new BinaryFormula(new Mul(), "mul", this.con3, con6);
  BinaryFormula bf12 = new BinaryFormula(new Div(), "div", this.con6, this.con6);

  //tests for Plus class
  boolean testPlus(Tester t) {
    return t.checkExpect(new Plus().apply(0.0, 0.0), 0.0)
        && t.checkExpect(new Plus().apply(9.0, 10.0), 19.0)
        && t.checkExpect(new Plus().apply(9.5, 10.0), 19.5);
  }

  //tests for Minus class
  boolean testMinus(Tester t) {
    return t.checkExpect(new Minus().apply(0.0, 0.0), 0.0)
        && t.checkExpect(new Minus().apply(9.0, 10.0), -1.0)
        && t.checkExpect(new Minus().apply(9.5, 10.0), -0.5);
  }

  //tests for Mul class
  boolean testMul(Tester t) {
    return t.checkExpect(new Mul().apply(0.0, 0.0), 0.0)
        && t.checkExpect(new Mul().apply(9.0, 10.0), 90.0)
        && t.checkExpect(new Mul().apply(2.0, 10.0), 20.0);
  }

  //tests for Div class
  boolean testDiv(Tester t) {
    return t.checkExpect(new Div().apply(0.0, 0.0), 0.0)
        && t.checkExpect(new Div().apply(9.0, 10.0), 0.9)
        && t.checkExpect(new Div().apply(10.0, 10.0), 1.0);
  }

  //tests for Sqr class
  boolean testSqr(Tester t) {
    return t.checkExpect(new Sqr().apply(0.0), 0.0) && t.checkExpect(new Sqr().apply(9.0), 81.0)
        && t.checkExpect(new Sqr().apply(10.0), 100.0);
  }

  //tests for Neg class
  boolean testNeg(Tester t) {
    return t.checkExpect(new Neg().apply(0.0), -0.0) && t.checkExpect(new Neg().apply(-9.0), 9.0)
        && t.checkExpect(new Neg().apply(10.0), -10.0);
  }

  //tests for EvalVisitor method
  boolean testEvalVisitor(Tester t) {
    return t.checkExpect(new EvalVisitor().constVistor(this.c1), 0.0)
        && t.checkExpect(new EvalVisitor().constVistor(this.c3), 4.5)
        && t.checkExpect(new EvalVisitor().constVistor(this.c4), 89.0)
        && t.checkExpect(new EvalVisitor().unaryFormulaVistor(this.uf3), 121.0)
        && t.checkExpect(new EvalVisitor().unaryFormulaVistor(this.uf2), -67.0)
        && t.checkExpect(new EvalVisitor().unaryFormulaVistor(this.uf1), 0.0)
        && t.checkExpect(new EvalVisitor().binaryFormulaVistor(this.bf3), 466200.0)
        && t.checkExpect(new EvalVisitor().binaryFormulaVistor(this.bf2), -11.0)
        && t.checkExpect(new EvalVisitor().binaryFormulaVistor(this.bf1), 67.0);
  }

  //tests for PrintVisitor method
  boolean testPrintVisitor(Tester t) {
    return t.checkExpect(new PrintVisitor().constVistor(this.c1), "0.0")
        && t.checkExpect(new PrintVisitor().constVistor(this.c3), "4.5")
        && t.checkExpect(new PrintVisitor().constVistor(this.c4), "89.0")
        && t.checkExpect(new PrintVisitor().unaryFormulaVistor(this.uf3), "(sqr 11.0)")
        && t.checkExpect(new PrintVisitor().unaryFormulaVistor(this.uf2), "(neg 67.0)")
        && t.checkExpect(new PrintVisitor().unaryFormulaVistor(this.uf1), "(sqr 0.0)")
        && t.checkExpect(new PrintVisitor().binaryFormulaVistor(this.bf2), "(minus 11.0 22.0)")
        && t.checkExpect(new PrintVisitor().binaryFormulaVistor(this.bf3), "(mul 333.0 1400.0)")
        && t.checkExpect(new PrintVisitor().binaryFormulaVistor(this.bf12), "(div 1400.0 1400.0)")
        && t.checkExpect(new PrintVisitor().binaryFormulaVistor(this.bf1), "(plus 0.0 67.0)");
  }

  //tests for DoublerVisitor method
  boolean testDoubleVisitor(Tester t) {
    return t.checkExpect(new DoublerVisitor().constVistor(this.c1), this.c1)
        && t.checkExpect(new DoublerVisitor().constVistor(this.c3), new Const(9.0))
        && t.checkExpect(new DoublerVisitor().constVistor(this.c4), new Const(178.0))
        && t.checkExpect(new DoublerVisitor().unaryFormulaVistor(this.uf3),
            new UnaryFormula(new Sqr(), "sqr", new Const(22.0)))
        && t.checkExpect(new DoublerVisitor().unaryFormulaVistor(this.uf2),
            new UnaryFormula(new Neg(), "neg", new Const(134.0)))
        && t.checkExpect(new DoublerVisitor().unaryFormulaVistor(this.uf1),
            new UnaryFormula(new Sqr(), "sqr", new Const(0.0)))
        && t.checkExpect(new DoublerVisitor().binaryFormulaVistor(this.bf2),
            new BinaryFormula(new Minus(), "minus", new Const(22.0), new Const(44.0)))
        && t.checkExpect(new DoublerVisitor().binaryFormulaVistor(this.bf3),
            new BinaryFormula(new Mul(), "mul", new Const(666.0), new Const(2800.0)))
        && t.checkExpect(new DoublerVisitor().binaryFormulaVistor(this.bf12),
            new BinaryFormula(new Div(), "div", new Const(2800.0), new Const(2800.0)))
        && t.checkExpect(new DoublerVisitor().binaryFormulaVistor(this.bf1),
            new BinaryFormula(new Plus(), "plus", new Const(0.0), new Const(134.0)));
  }

  //tests for NoNegativeResults method
  boolean testNoNegativeResultsVisitor(Tester t) {
    return t.checkExpect(new NoNegativeResults().constVistor(this.c1), true)
        && t.checkExpect(new NoNegativeResults().constVistor(this.c3), true)
        && t.checkExpect(new NoNegativeResults().constVistor(this.c4), true)
        && t.checkExpect(new NoNegativeResults().constVistor(this.c6), false)
        && t.checkExpect(new NoNegativeResults().constVistor(this.c7), false)
        && t.checkExpect(new NoNegativeResults().unaryFormulaVistor(this.uf3), true)
        && t.checkExpect(new NoNegativeResults().unaryFormulaVistor(this.uf2), false)
        && t.checkExpect(new NoNegativeResults().unaryFormulaVistor(this.uf1), true)
        && t.checkExpect(new NoNegativeResults().binaryFormulaVistor(this.bf2), false)
        && t.checkExpect(new NoNegativeResults().binaryFormulaVistor(this.bf3), true)
        && t.checkExpect(new NoNegativeResults().binaryFormulaVistor(this.bf12), true)
        && t.checkExpect(new NoNegativeResults().binaryFormulaVistor(this.bf1), true);
  }

  //tests for accept method
  boolean testAccept(Tester t) {
    return t.checkExpect(this.c1.accept(new EvalVisitor()), 0.0)
        && t.checkExpect(this.c1.accept(new PrintVisitor()), "0.0")
        && t.checkExpect(this.c1.accept(new DoublerVisitor()), this.c1)
        && t.checkExpect(this.c1.accept(new NoNegativeResults()), true)
        && t.checkExpect(this.uf2.accept(new EvalVisitor()), -67.0)
        && t.checkExpect(this.uf2.accept(new PrintVisitor()), "(neg 67.0)")
        && t.checkExpect(this.uf2.accept(new DoublerVisitor()),
            new UnaryFormula(new Neg(), "neg", new Const(134.0)))
        && t.checkExpect(this.uf2.accept(new NoNegativeResults()), false)
        && t.checkExpect(this.bf2.accept(new EvalVisitor()), -11.0)
        && t.checkExpect(this.bf2.accept(new PrintVisitor()), "(minus 11.0 22.0)")
        && t.checkExpect(this.bf2.accept(new DoublerVisitor()),
            new BinaryFormula(new Minus(), "minus", new Const(22.0), new Const(44.0)))
        && t.checkExpect(this.bf2.accept(new NoNegativeResults()), false);
  }

  //tests for apply method
  boolean testApply(Tester t) {
    return t.checkExpect(new EvalVisitor().apply(this.con1), 0.0)
        && t.checkExpect(new PrintVisitor().apply(this.con1), "0.0")
        && t.checkExpect(new DoublerVisitor().apply(this.c1), this.c1)
        && t.checkExpect(new NoNegativeResults().apply(this.c1), true)
        && t.checkExpect(new EvalVisitor().apply(this.uf2), -67.0)
        && t.checkExpect(new PrintVisitor().apply(this.uf2), "(neg 67.0)")
        && t.checkExpect(new DoublerVisitor().apply(this.uf2),
            new UnaryFormula(new Neg(), "neg", new Const(134.0)))
        && t.checkExpect(new NoNegativeResults().apply(this.uf2), false)
        && t.checkExpect(new EvalVisitor().apply(this.bf2), -11.0)
        && t.checkExpect(new PrintVisitor().apply(this.bf2), "(minus 11.0 22.0)")
        && t.checkExpect(new DoublerVisitor().apply(this.bf2),
            new BinaryFormula(new Minus(), "minus", new Const(22.0), new Const(44.0)))
        && t.checkExpect(new NoNegativeResults().apply(this.bf2), false);
  }
}