package PracticalExercise8;

import java.util.Scanner;
import java.util.Vector;

public class StudentManager {

    public static void main(String[] args) {
        Vector<Student> studentVector = new Vector<>();
        Scanner sc = new Scanner(System.in);

        System.out.println("학생 4명의 이름, 전공, 학번, 학점평균을 입력하세요.");

        for (int i = 0; i < 4; i++) {
            System.out.print(">>");
            String input = sc.nextLine();
            String[] tokens = input.split(", ");

            String name = tokens[0].trim();
            String major = tokens[1].trim();
            int id = Integer.parseInt(tokens[2].trim());
            double gpa = Double.parseDouble(tokens[3].trim());

            studentVector.add(new Student(name, major, id, gpa));
        }
        System.out.println("--------------------");

        for (Student s : studentVector) {
            System.out.println(s.toString());
        }
        System.out.println("--------------------");

        System.out.print("장학생: ");
        for (Student s : studentVector) {
            if (s.getGpa() >= 4.0) {
                System.out.print(s.getName() + " ");
            }
        }
        System.out.println("\n--------------------");

        while (true) {
            System.out.print("학생 이름 >> ");
            String searchName = sc.next();

            if (searchName.equals("그만")) {
                break;
            }

            boolean found = false;

            for (Student s : studentVector) {
                if (s.getName().equals(searchName)) {
                    System.out.println(s.toString());
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println(searchName + " 학생이 없습니다.");
            }
        }
        
        sc.close();
    }
}
