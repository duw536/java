package PracticalExercise14;

import java.util.Scanner;

public class GraphicEditor {
    private Shape head = null;
    private Shape tail = null;
    private Scanner scn = new Scanner(System.in);

    public void run() {
        System.out.println("그래픽 에디터 Beauty Graphic Editor를 실행합니다.");
        while (true) {
            System.out.print("삽입(1), 삭제(2), 모두 보기(3), 종료(4)>>");
            int choice = scn.nextInt();
            switch (choice) {
                case 1:
                    insert();
                    break;
                case 2:
                    delete();
                    break;
                case 3:
                    showAll();
                    break;
                case 4:
                    System.out.println("Beauty Graphic Editor를 종료합니다.");
                    return;
                default:
                    System.out.println("잘못된 입력입니다.");
            }
        }
    }

    private void insert() {
        System.out.print("Line(1), Rect(2), Circle(3)>>");
        int type = scn.nextInt();
        Shape shape = null;

        switch (type) {
            case 1: shape = new Line(); break;
            case 2: shape = new Rect(); break;
            case 3: shape = new Circle(); break;
            default:
                System.out.println("잘못된 도형 선택입니다.");
                return;
        }

        if (head == null) {
            head = shape;
            tail = shape;
        } else {
            tail.setNext(shape);
            tail = shape;
        }

    }

    private void delete() {
        System.out.print("삭제할 도형의 위치>>");
        int index = scn.nextInt();

        if (head == null) {
            System.out.println("삭제할 도형이 없습니다.");
            return;
        }

        if (index == 1) {
            head = head.getNext();
            if (head == null) tail = null;
            System.out.println("삭제되었습니다.");
            return;
        }

        Shape prev = head;
        Shape current = head.getNext();
        int count = 2;

        while (current != null) {
            if (count == index) {
                prev.setNext(current.getNext());
                if (current == tail) tail = prev;
                System.out.println("삭제되었습니다.");
                return;
            }
            prev = current;
            current = current.getNext();
            count++;
        }

        System.out.println("삭제할 수 없습니다.");
    }

    private void showAll() {
        Shape current = head;
        if (current == null) {
            System.out.println("저장된 도형이 없습니다.");
            return;
        }
        while (current != null) {
            current.draw();
            current = current.getNext();
        }
    }

    public static void main(String[] args) {
        GraphicEditor editor = new GraphicEditor();
        editor.run();
    }
}