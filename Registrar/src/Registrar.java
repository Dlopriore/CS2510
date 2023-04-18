import tester.Tester;
//Registrar.java
//Dante LoPriore

//to represent a predicate
interface IPred<T> {

  //ask a question about a given t
  boolean apply(T t);
}

//to represents a predicate that asks the question if the students are classmates.
class CheckClassmates implements IPred<Course> {
  Student stud;

  //constructor 
  CheckClassmates(Student stud) {
    this.stud = stud;
  }

  //ask a question which is the check if the students 
  //are classmates about a given Course
  public boolean apply(Course t) {
    // TODO Auto-generated method stub
    return t.students.ormap(new CheckStudent(this.stud));
  }
}

//to represents a predicate that asks the question if the students seen in the same class.
class CheckStudent implements IPred<Student> {
  Student st;

  //constructor
  CheckStudent(Student st) {
    this.st = st;
  }

  //ask a question which is the check if the students 
  //are classmates about a given Course
  public boolean apply(Student t2) {
    // TODO Auto-generated method stub
    return t2.sameStudent(this.st);
  }
}

//to represent a list of t
interface IList<T> {

  // does the list have at least one painting that passes the given predicate?
  boolean ormap(IPred<T> pred);

  //to compute the count of the predicate asking a question 
  int counterPredAcc(IPred<T> pred, int count);
}

//to represent a empty list of t
class MtList<T> implements IList<T> {

  // does the list have at least one t that passes the given predicate?
  public boolean ormap(IPred<T> pred) {
    // TODO Auto-generated method stub
    return false;
  }

  //to compute the count of the predicate asking a question 
  public int counterPredAcc(IPred<T> pred, int count) {
    // TODO Auto-generated method stub
    return count;
  }
}

//to represent a nonempty list of t
class ConsList<T> implements IList<T> {
  T first;
  IList<T> rest;

  //constructor
  ConsList(T first, IList<T> rest) {
    this.first = first;
    this.rest = rest;
  }

  // does the list have at least one t that passes the given predicate?
  public boolean ormap(IPred<T> pred) {
    // TODO Auto-generated method stub
    return pred.apply(this.first) || this.rest.ormap(pred);
  }

  //to compute the count of the predicate asking a question 
  public int counterPredAcc(IPred<T> pred, int count) {
    // TODO Auto-generated method stub
    if (pred.apply(this.first)) {
      return this.rest.counterPredAcc(pred, count + 1);
    }
    else {
      return this.rest.counterPredAcc(pred, count);
    }
  }
}

//to represent a Course
class Course {
  String name;
  Instructor prof;
  IList<Student> students;

  //constructor 
  Course(String name, Instructor prof) {
    this.name = name;
    this.prof = prof;
    this.students = new MtList<Student>();
    this.prof.addCourses(this);
  }

  //Effect: to enroll a Student in the given Course. 
  void enroll(Student c) {
    this.students = new ConsList<Student>(c, this.students);
  }
}

//to represent an instructor
class Instructor {
  String name;
  IList<Course> courses;

  //constructor
  Instructor(String name) {
    this.name = name;
    this.courses = new MtList<Course>();
  }

  //Effect: to add courses on to an object
  public void addCourses(Course course) {
    // TODO Auto-generated method stub
    this.courses = new ConsList<Course>(course, this.courses);
  }

  //to determine whether the given Student is in more than one of this Instructor’s Courses.
  boolean dejavu(Student c) {
    return this.courses.counterPredAcc(new CheckClassmates(c), 0) > 1;
  }
}

//to represent an Student
class Student {
  String name;
  int id;
  IList<Course> courses;

  //constructor 
  Student(String name, int idNumber) {
    this.name = name;
    this.id = idNumber;
    this.courses = new MtList<Course>();
  }

  //to determine whether this student is the same as the given student
  public boolean sameStudent(Student st) {
    // TODO Auto-generated method stub
    return this.name.equals(st.name) && this.id == st.id;
  }

  //Effect: to enroll a Student in the given Course. 
  void enroll(Course c) {
    this.courses = new ConsList<Course>(c, this.courses);
    c.enroll(this);
  }

  //to determine whether the given Student is in 
  //any of the same classes as this Student.
  boolean classmates(Student c) {
    return this.courses.ormap(new CheckClassmates(c));
  }
}

//Examples and Tests that represent Registrar.java
class ExamplesRegistrar {

  //Examples of Students 
  Student s1;
  Student s2;
  Student s3;
  Student s4;
  Student s5;
  Student s6;

  //Examples of Lists of Students 
  IList<Student> mt;
  IList<Student> los1;
  IList<Student> los2;

  //Examples of Instructors 
  Instructor instruct1;
  Instructor instruct2;
  Instructor instruct3;

  //Examples of Lists of Courses 
  IList<Instructor> mtp;
  IList<Instructor> pos1;
  IList<Instructor> pos2;

  //Examples of Course 
  Course c1;
  Course c2;
  Course c3;
  Course c4;

  //Examples of Lists of Courses 
  IList<Course> mts;
  IList<Course> loc1;
  IList<Course> loc2;

  //sets the paintings and artists for testing
  void initData() {
    this.s1 = new Student("Dante", 321);
    this.s2 = new Student("Bob", 212);
    this.s3 = new Student("Bob", 212);
    this.s4 = new Student("Kevin", 301);
    this.s5 = new Student("Leena", 11);
    this.s6 = new Student("Damian", 222);

    this.mt = new MtList<Student>();
    this.los1 = new ConsList<Student>(this.s1, this.mt);
    this.los2 = new ConsList<Student>(this.s2, this.mt);

    this.instruct1 = new Instructor("Joe M");
    this.instruct2 = new Instructor("Blerner");
    this.instruct3 = new Instructor("Vido");

    this.mtp = new MtList<Instructor>();
    this.pos1 = new ConsList<Instructor>(this.instruct1, this.mtp);
    this.pos2 = new ConsList<Instructor>(this.instruct2, this.mtp);

    this.mts = new MtList<Course>();
    this.c1 = new Course("Fundies 2", this.instruct1);
    this.c2 = new Course("OOD", this.instruct2);
    this.c3 = new Course("Calc 1", this.instruct1);
    this.c4 = new Course("Engineering 1", this.instruct2);
    this.loc1 = new ConsList<Course>(this.c2, this.mts);
    this.loc2 = new ConsList<Course>(this.c1, new ConsList<Course>(this.c3, this.mts));

  }

  //tests for the classmates method
  void testClassmates(Tester t) {
    this.initData();
    this.s1.enroll(this.c2);
    this.s2.enroll(this.c3);
    this.s3.enroll(this.c3);
    this.s4.enroll(this.c3);
    this.s4.enroll(this.c4);

    t.checkExpect(this.s1.classmates(this.s2), false);
    t.checkExpect(this.s1.classmates(this.s1), true);
    t.checkExpect(this.s2.classmates(this.s3), true);
    t.checkExpect(this.s3.classmates(this.s4), true);
    t.checkExpect(this.s2.classmates(this.s2), true);

  }

  //tests for the enroll void method
  void testEnroll(Tester t) {
    this.initData();
    this.s1.enroll(this.c2);
    this.s2.enroll(this.c3);
    this.s2.enroll(this.c1);
    this.s2.enroll(this.c2);
    this.s4.enroll(this.c3);
    this.s4.enroll(this.c2);
    this.s3.enroll(this.c1);
    this.s3.enroll(this.c2);
    this.s3.enroll(this.c3);
    this.s3.enroll(this.c4);

    t.checkExpect(this.s1.courses, new ConsList<Course>(this.c2, new MtList<Course>()));
    t.checkExpect(this.s2.courses, new ConsList<Course>(this.c2,
        new ConsList<Course>(this.c1, new ConsList<Course>(this.c3, new MtList<Course>()))));
    t.checkExpect(this.s4.courses,
        new ConsList<Course>(this.c2, new ConsList<Course>(this.c3, this.mts)));
    t.checkExpect(this.s3.courses, new ConsList<Course>(this.c4, new ConsList<Course>(this.c3,
        new ConsList<Course>(this.c2, new ConsList<Course>(this.c1, this.mts)))));
  }

  //tests for the sameStudent method
  void testSameStudent(Tester t) {
    this.initData();

    t.checkExpect(this.s1.sameStudent(this.s1), true);
    t.checkExpect(this.s2.sameStudent(this.s3), true);
    t.checkExpect(this.s1.sameStudent(this.s2), false);
  }

  //tests for the ormap method
  void testOrmap(Tester t) {
    this.initData();

    t.checkExpect(this.los1.ormap(new CheckStudent(this.s2)), false);
    t.checkExpect(this.los1.ormap(new CheckStudent(this.s1)), true);
    t.checkExpect(this.los1.ormap(new CheckStudent(this.s3)), false);
  }

  //tests for the counterPredAcc method
  void testCounterPredAcc(Tester t) {
    this.initData();
    this.s3.enroll(this.c1);
    this.s3.enroll(this.c1);
    this.s3.enroll(this.c2);
    this.s3.enroll(this.c4);

    t.checkExpect(this.los1.counterPredAcc(new CheckStudent(this.s2), 0), 0);
    t.checkExpect(this.los1.counterPredAcc(new CheckStudent(this.s1), 0), 1);
    t.checkExpect(this.los2.counterPredAcc(new CheckStudent(this.s3), 0), 1);
  }

  //tests for the CheckStudent method
  void testCheckStudent(Tester t) {
    this.initData();

    t.checkExpect(new CheckStudent(this.s2).apply(this.s1), false);
    t.checkExpect(new CheckStudent(this.s2).apply(this.s2), true);
    t.checkExpect(new CheckStudent(this.s2).apply(this.s3), true);
  }

  //tests for the CheckClassmates method
  void testCheckClassmates(Tester t) {
    this.initData();
    this.s1.enroll(this.c2);
    this.s2.enroll(this.c1);
    this.s2.enroll(this.c2);

    t.checkExpect(new CheckClassmates(this.s2).apply(this.c1), true);
    t.checkExpect(new CheckClassmates(this.s2).apply(this.c2), true);
    t.checkExpect(new CheckClassmates(this.s3).apply(this.c3), false);
  }

  //tests for the Dejavu method
  void testDejavu(Tester t) {
    this.initData();
    this.s1.enroll(this.c2);
    this.s2.enroll(this.c3);
    this.s1.enroll(this.c2);
    this.s3.enroll(this.c1);
    this.s3.enroll(this.c2);
    this.s3.enroll(this.c3);
    this.s3.enroll(this.c4);

    t.checkExpect(this.instruct1.dejavu(this.s1), false);
    t.checkExpect(this.instruct1.dejavu(this.s2), true);
    t.checkExpect(this.instruct2.dejavu(this.s3), true);
    t.checkExpect(this.instruct1.dejavu(this.s1), false);
    t.checkExpect(this.instruct2.dejavu(this.s1), false);
    t.checkExpect(this.instruct2.dejavu(this.s4), false);
  }
}