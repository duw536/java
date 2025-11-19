package PracticalExercise1;

import javax.swing.*;

public class PracticalExercise1 extends JFrame {
    public PracticalExercise1() {
        setTitle("문제 1: 메뉴 만들기");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        createMenu();

        setVisible(true);
    }

    private void createMenu() {
        JMenuBar mb = new JMenuBar();

        JMenu fileMenu = new JMenu("파일");
        JMenu editMenu = new JMenu("편집");
        JMenu viewMenu = new JMenu("보기");
        JMenu inputMenu = new JMenu("입력");

        viewMenu.add(new JMenuItem("화면확대"));
        viewMenu.add(new JMenuItem("쪽윤곽"));

        mb.add(fileMenu);
        mb.add(editMenu);
        mb.add(viewMenu);
        mb.add(inputMenu);

        setJMenuBar(mb);
    }

    public static void main(String[] args) {
        new PracticalExercise1();
    }
}
