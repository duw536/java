package GraphicEx;

import javax.swing.*;
import java.awt.*;

public class MyNameOp extends JFrame {

    public MyNameOp() {
        setTitle("조주영");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // 창을 화면 가운데로
        
        add(new MyPanel());
        setVisible(true);
    }

    class MyPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            
            Graphics2D g2 = (Graphics2D) g;
            
            // 1. 안티앨리어싱 설정 (선이 부드럽게 보임)
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // 2. 공통 스타일 설정
            g2.setStroke(new BasicStroke(3));
            g2.setColor(Color.BLUE);

            // 3. 글자 그리기 (시작 위치만 지정하면 알아서 그려짐)
            int startY = 50;
            
            drawJo(g2, 50, startY);   // 조
            drawJu(g2, 200, startY);  // 주 (간격 150 띄움)
            drawYoung(g2, 350, startY); // 영 (간격 150 띄움)
        }

        // '조' 그리는 메소드
        private void drawJo(Graphics2D g, int x, int y) {
            // 지읒 (ㅈ)
            g.drawLine(x, y, x + 100, y);          // ㅡ
            g.drawLine(x + 50, y, x, y + 70);      // /
            g.drawLine(x + 50, y, x + 100, y + 70); // \
            
            // 오 (ㅗ)
            g.drawLine(x + 50, y + 70, x + 50, y + 90); // ㅣ (세로 연결)
            g.drawLine(x, y + 90, x + 100, y + 90);     // ㅡ (받침)
        }

        // '주' 그리는 메소드
        private void drawJu(Graphics2D g, int x, int y) {
            // 지읒 (ㅈ)
            g.drawLine(x, y, x + 100, y);           // ㅡ
            g.drawLine(x + 50, y, x, y + 70);       // /
            g.drawLine(x + 50, y, x + 100, y + 70); // \
            
            // 우 (ㅜ) - 위치를 'ㅈ' 아래로 살짝 조정
            int uY = y + 100; // 우 시작 Y좌표
            g.drawLine(x, uY, x + 100, uY);         // ㅡ
            g.drawLine(x + 50, uY, x + 50, uY + 30);// ㅣ
        }

        // '영' 그리는 메소드
        private void drawYoung(Graphics2D g, int x, int y) {
            // 이응 (ㅇ) - 초성
            g.drawOval(x, y, 50, 50);

            // 여 (ㅕ)
            g.drawLine(x + 60, y + 15, x + 80, y + 15); // ㅡ (위)
            g.drawLine(x + 60, y + 35, x + 80, y + 35); // ㅡ (아래)
            g.drawLine(x + 80, y, x + 80, y + 60);      // ㅣ (세로)

            // 이응 (ㅇ) - 받침 (중심을 맞추기 위해 x좌표 조정)
            g.drawOval(x + 15, y + 70, 60, 60); 
        }
    }

    public static void main(String[] args) {
        new MyNameOp();
    }
}
