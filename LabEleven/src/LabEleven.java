import java.util.ArrayList;

import tester.Tester;
//Dante LoPriore and Jake Simeone

//to represent a Curriculum
class Curriculum {
  ArrayList<Course> courses;

  //constructor
  Curriculum() {
    this.courses = new ArrayList<Course>();
  }

  // EFFECT: adds another course to the set of known courses
  void addCourse(Course c) {
    this.courses.add(c);
  }

  // determines whether the course comes after all of its prereqs
  boolean comesAfterPrereqs(ArrayList<Course> schedule, Course c) {
    return this.prereqHelper(schedule, c, c.prereqs.size());

  }

  //to determine of the given schedule has preqs and whether the course comes after all of its prereqs
  public boolean prereqHelper(ArrayList<Course> schedule, Course c, int size) {
    // TODO Auto-generated method stub
    for (Course course : schedule) {
      if (c.prereqs.contains(course)) {
        size = size - 1;
      }
      if (c.equals(course) && size == 0) {
        return true;
      }

    }
    return false;
  }

  // determines whether all courses come after their prereqs
  boolean validSchedule(ArrayList<Course> schedule) {
    for (Course course : schedule) {
      return this.comesAfterPrereqs(schedule, course);
    }
    return false;
  }
}

//to represent a Course
class Course {
  String name;
  ArrayList<Course> prereqs;

  //constructor
  Course(String name) {
    this.name = name;
    this.prereqs = new ArrayList<Course>();
  }

  // EFFECT: adds a course as a prereq to this one
  void addPrereq(Course c) {
    this.prereqs.add(c);
  }
  // add methods here

  //Effect: processes unprocessed courses and adds them to the list
  void process(ArrayList<Course> processed) {
    if (processed.contains(this)) {
      ;
    }

    for (int i = 0; processed.size() > i; i++) {
      for (int j = 0; prereqs.size() > j; j++) {
        if (this.prereqs.get(i).equals(processed.get(j))) {

        }
        processed.add(this.prereqs.get(i));
      }

    }

  }

  //to process courses with same schedule and add to list
  ArrayList<Course> topsort(ArrayList<Course> given) {
    return null;
  }
}

//to represent the examples and tests for LabTen
class ExamplesLabEleven {
  Course fundies1;
  Course fundies2;
  Course databaseDesign;
  Course objectOrientedDesign;
  Course algorithms;
  Course compilers;
  Course computerSystems;
  Course dataProcessing;
  Course programmingLanguages;
  Course theoryOfComputation;
  Curriculum csAcademics;

  //to represent the Intial Data
  void initData() {
    this.fundies1 = new Course("Fundamentals 1");
    this.fundies2 = new Course("Fundamentals 2");
    this.databaseDesign = new Course("Database Design");
    this.objectOrientedDesign = new Course("Object-Oriented Design");
    this.algorithms = new Course("Algorithms and Data");
    this.compilers = new Course("Compilers");
    this.computerSystems = new Course("Computer Systems");
    this.dataProcessing = new Course("Large-Scale Parallel Data Processing");
    this.programmingLanguages = new Course("Programming Languages");
    this.theoryOfComputation = new Course("Theory of Computation");
    this.csAcademics = new Curriculum();
  }

  //to test the comesAfterPrereqs
  void testComesAfterPrereqs(Tester t) {
    this.initData();
    this.fundies2.addPrereq(this.fundies1);
    this.databaseDesign.addPrereq(this.fundies1);
    this.objectOrientedDesign.addPrereq(this.fundies2);
    this.algorithms.addPrereq(this.fundies2);
    this.computerSystems.addPrereq(this.fundies2);
    this.theoryOfComputation.addPrereq(this.fundies2);
    this.programmingLanguages.addPrereq(this.objectOrientedDesign);
    this.programmingLanguages.addPrereq(this.theoryOfComputation);
    this.dataProcessing.addPrereq(this.algorithms);
    this.dataProcessing.addPrereq(this.computerSystems);
    this.compilers.addPrereq(this.programmingLanguages);

    this.csAcademics.addCourse(this.fundies1);

    this.csAcademics.addCourse(this.fundies2);

    this.csAcademics.addCourse(this.databaseDesign);

    this.csAcademics.addCourse(this.objectOrientedDesign);

    this.csAcademics.addCourse(this.algorithms);

    this.csAcademics.addCourse(this.dataProcessing);

    this.csAcademics.addCourse(this.computerSystems);

    this.csAcademics.addCourse(this.theoryOfComputation);

    this.csAcademics.addCourse(this.programmingLanguages);

    this.csAcademics.addCourse(this.compilers);

    t.checkExpect(this.csAcademics.comesAfterPrereqs(this.csAcademics.courses, compilers), true);
    t.checkExpect(this.csAcademics.comesAfterPrereqs(this.csAcademics.courses, dataProcessing),
        false);

  }

  //to test the validSchedule
  void testValidSchedule(Tester t) {
    this.initData();

    this.fundies2.addPrereq(this.fundies1);

    this.databaseDesign.addPrereq(this.fundies1);

    this.objectOrientedDesign.addPrereq(this.fundies2);

    this.algorithms.addPrereq(this.fundies2);

    this.computerSystems.addPrereq(this.fundies2);

    this.theoryOfComputation.addPrereq(this.fundies2);

    this.programmingLanguages.addPrereq(this.objectOrientedDesign);

    this.programmingLanguages.addPrereq(this.theoryOfComputation);

    this.dataProcessing.addPrereq(this.algorithms);

    this.dataProcessing.addPrereq(this.computerSystems);

    this.compilers.addPrereq(this.programmingLanguages);

    this.csAcademics.addCourse(this.fundies1);

    this.csAcademics.addCourse(this.fundies2);

    this.csAcademics.addCourse(this.databaseDesign);

    this.csAcademics.addCourse(this.objectOrientedDesign);

    this.csAcademics.addCourse(this.algorithms);

    this.csAcademics.addCourse(this.computerSystems);

    this.csAcademics.addCourse(this.theoryOfComputation);

    this.csAcademics.addCourse(this.programmingLanguages);

    this.csAcademics.addCourse(this.dataProcessing);

    this.csAcademics.addCourse(this.compilers);

    t.checkExpect(this.csAcademics.validSchedule(this.csAcademics.courses), true);

  }

  //to test the PrereqHelper
  void testPrereqHelper(Tester t) {
    this.initData();
    this.fundies2.addPrereq(this.fundies1);
    this.databaseDesign.addPrereq(this.fundies1);
    this.objectOrientedDesign.addPrereq(this.fundies2);
    this.algorithms.addPrereq(this.fundies2);
    this.computerSystems.addPrereq(this.fundies2);
    this.theoryOfComputation.addPrereq(this.fundies2);
    this.programmingLanguages.addPrereq(this.objectOrientedDesign);
    this.programmingLanguages.addPrereq(this.theoryOfComputation);
    this.dataProcessing.addPrereq(this.algorithms);
    this.dataProcessing.addPrereq(this.computerSystems);
    this.compilers.addPrereq(this.programmingLanguages);

    this.csAcademics.addCourse(this.fundies1);

    this.csAcademics.addCourse(this.fundies2);

    this.csAcademics.addCourse(this.databaseDesign);

    this.csAcademics.addCourse(this.objectOrientedDesign);

    this.csAcademics.addCourse(this.algorithms);

    this.csAcademics.addCourse(this.dataProcessing);

    this.csAcademics.addCourse(this.computerSystems);

    this.csAcademics.addCourse(this.theoryOfComputation);

    this.csAcademics.addCourse(this.programmingLanguages);

    this.csAcademics.addCourse(this.compilers);

    t.checkExpect(this.csAcademics.prereqHelper(this.csAcademics.courses, compilers,
        this.compilers.prereqs.size()), true);
    t.checkExpect(this.csAcademics.prereqHelper(this.csAcademics.courses, dataProcessing,
        this.dataProcessing.prereqs.size()), false);

  }

  //to test the process
  void testProcess(Tester t) {
    this.initData();
    this.fundies2.addPrereq(this.fundies1);
    this.databaseDesign.addPrereq(this.fundies1);
    this.objectOrientedDesign.addPrereq(this.fundies2);
    this.algorithms.addPrereq(this.fundies2);
    this.computerSystems.addPrereq(this.fundies2);
    this.theoryOfComputation.addPrereq(this.fundies2);
    this.programmingLanguages.addPrereq(this.objectOrientedDesign);
    this.programmingLanguages.addPrereq(this.theoryOfComputation);
    this.dataProcessing.addPrereq(this.algorithms);
    this.dataProcessing.addPrereq(this.computerSystems);
    this.compilers.addPrereq(this.programmingLanguages);

    this.csAcademics.addCourse(this.fundies1);

    this.csAcademics.addCourse(this.fundies2);

    this.csAcademics.addCourse(this.databaseDesign);

    this.csAcademics.addCourse(this.objectOrientedDesign);

    this.csAcademics.addCourse(this.algorithms);

    this.csAcademics.addCourse(this.dataProcessing);

    this.csAcademics.addCourse(this.computerSystems);

    this.csAcademics.addCourse(this.theoryOfComputation);

    this.csAcademics.addCourse(this.programmingLanguages);

    this.csAcademics.addCourse(this.compilers);
  }

  //to test the topsort
  void testTopsort(Tester t) {
    this.initData();
    this.fundies2.addPrereq(this.fundies1);
    this.databaseDesign.addPrereq(this.fundies1);
    this.objectOrientedDesign.addPrereq(this.fundies2);
    this.algorithms.addPrereq(this.fundies2);
    this.computerSystems.addPrereq(this.fundies2);
    this.theoryOfComputation.addPrereq(this.fundies2);
    this.programmingLanguages.addPrereq(this.objectOrientedDesign);
    this.programmingLanguages.addPrereq(this.theoryOfComputation);
    this.dataProcessing.addPrereq(this.algorithms);
    this.dataProcessing.addPrereq(this.computerSystems);
    this.compilers.addPrereq(this.programmingLanguages);

    this.csAcademics.addCourse(this.fundies1);

    this.csAcademics.addCourse(this.fundies2);

    this.csAcademics.addCourse(this.databaseDesign);

    this.csAcademics.addCourse(this.objectOrientedDesign);

    this.csAcademics.addCourse(this.algorithms);

    this.csAcademics.addCourse(this.dataProcessing);

    this.csAcademics.addCourse(this.computerSystems);

    this.csAcademics.addCourse(this.theoryOfComputation);

    this.csAcademics.addCourse(this.programmingLanguages);

    this.csAcademics.addCourse(this.compilers);

  }

}
