package PracticalExercise9;

import java.util.Scanner;
import java.util.Vector;

public class Editor {

    public static void main(String[] args) {

        Vector<Shape> v = new Vector<>();
        
        Scanner sc = new Scanner(System.in);
        
        System.out.println("그래픽 에디터 Beauty Graphic Editor를 실행합니다.");

        while (true) {
            System.out.print("삽입(1), 삭제(2), 모두 보기(3), 종료(4)>>");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Line(1), Rect(2), Circle(3)>>");
                    int shapeType = sc.nextInt();
                    
                    if (shapeType == 1) {
                        v.add(new Line());
                    } else if (shapeType == 2) {
                        v.add(new Rect());
                    } else if (shapeType == 3) {
                        v.add(new Circle());
                    } else {
                        System.out.println("잘못된 입력입니다.");
                    }
                    break;

                case 2:
                    System.out.print("삭제할 도형의 위치>>");
                    int index = sc.nextInt();

                    if (index >= 0 && index < v.size()) {
                        v.remove(index);
                    } else {
                        System.out.println("삭제할 수 없습니다.");
                    }
                    break;

                case 3:
                    if (v.isEmpty()) {
                        System.out.println("저장된 도형이 없습니다.");
                    } else {
                        for (Shape s : v) {
                            s.draw();
                        }
                    }
                    break;

                case 4:
                    System.out.println("Beauty Graphic Editor를 종료합니다.");
                    sc.close();
                    return;

                default:
                    System.out.println("잘못된 메뉴 번호입니다.");
            }
        }
    }
}