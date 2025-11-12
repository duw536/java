package PracticalExercise8;

class Student {
 private String name;
 private String major;
 private int studentId;
 private double gpa;

 public Student(String name, String major, int studentId, double gpa) {
     this.name = name;
     this.major = major;
     this.studentId = studentId;
     this.gpa = gpa;
 }

 public String getName() {
     return name;
 }

 public double getGpa() {
     return gpa;
 }

 @Override
 public String toString() {
     return "이름:" + name + "  전공:" + major + 
            "  학번:" + studentId + "  학점평균:" + gpa;
 }
}
