package GraphicEx;

import javax.swing.*;
import java.awt.*;

public class MyName extends JFrame {

    public MyName() {
        setTitle("조주영");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // 그림을 그릴 패널 추가
        MyPanel panel = new MyPanel();
        add(panel);
        
        setVisible(true);
    }

    // 내부 클래스로 패널 정의
    class MyPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            
            // 펜 색상 및 두께 설정
            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(3)); // 선 두께 3
            g2.setColor(Color.BLUE); // 파란색
            

            // 지읒 (ㅈ)
            g.drawLine(50, 50, 150, 50);   // ㅡ (위 가로)
            g.drawLine(100, 50, 50, 120);  // / (왼쪽 대각선)
            g.drawLine(100, 50, 150, 120); // \ (오른쪽 대각선)
            
            // 오 (ㅗ)
            g.drawLine(100, 120, 100, 140); // ㅣ (짧은 세로)
            g.drawLine(50, 140, 150, 140);  // ㅡ (긴 가로 받침)

            int offsetX = 150; // 두 번째 글자 시작 위치 이동
            
            
            // 지읒 (ㅈ)
            g.drawLine(50 + offsetX, 50, 150 + offsetX, 50);   // ㅡ
            g.drawLine(100 + offsetX, 50, 50 + offsetX, 120);  // /
            g.drawLine(100 + offsetX, 50, 150 + offsetX, 120); // \
            
            // 우 (ㅜ)
            g.drawLine(50 + offsetX, 150, 150 + offsetX, 150); // ㅡ (가로)
            g.drawLine(100 + offsetX, 150, 100 + offsetX, 180);// ㅣ (아래로 긴 세로)

            offsetX = 300; // 세 번째 글자 시작 위치 이동

            
            // 이응 (ㅇ) - 초성
            g.drawOval(50 + offsetX, 50, 50, 50); // (x, y, width, height)

            // 여 (ㅕ)
            g.drawLine(110 + offsetX, 65, 130 + offsetX, 65); // ㅡ (위 짧은 가로)
            g.drawLine(110 + offsetX, 85, 130 + offsetX, 85); // ㅡ (아래 짧은 가로)
            g.drawLine(130 + offsetX, 50, 130 + offsetX, 110);// ㅣ (긴 세로)

            // 이응 (ㅇ) - 받침
            g.drawOval(65 + offsetX, 120, 60, 60); // 받침은 조금 더 크게
        }
    }

    public static void main(String[] args) {
        new MyName();
    }
}