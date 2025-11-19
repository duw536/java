package OpenChallenge;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CoffeeVendingMachineFrame extends JFrame {
    private int[] ingredientDetails = {10, 10, 10, 10, 10}; 
    private String[] ingredientNames = {"Cup", "Coffee", "Water", "Sugar", "Cream"};
    
    private CoffeePanel coffeePanel = new CoffeePanel(); 
    
    public CoffeeVendingMachineFrame() {
        setTitle("Open Challenge 14");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.setLayout(new BorderLayout());

        JPanel northPanel = new JPanel();
        northPanel.setBackground(Color.PINK); 
        JLabel title = new JLabel("Welcome, Hot Coffee!!");
        title.setFont(new Font("Arial", Font.BOLD, 20)); 
        northPanel.add(title);
        c.add(northPanel, BorderLayout.NORTH);
        c.add(coffeePanel, BorderLayout.CENTER);

        JPanel southPanel = new JPanel();
        southPanel.setBackground(Color.BLACK); 
        
        JButton btnBlack = new JButton("Black Coffee");
        JButton btnSugar = new JButton("Sugar Coffee");
        JButton btnDabang = new JButton("Dabang Coffee");
        JButton btnReset = new JButton("Reset");

        btnBlack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { operate(0); }
        });
        btnSugar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { operate(1); }
        });
        btnDabang.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { operate(2); }
        });
        btnReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for(int i=0; i<ingredientDetails.length; i++) ingredientDetails[i] = 10;
                coffeePanel.setCoffeeImage(null); 
                coffeePanel.repaint(); 
            }
        });

        southPanel.add(btnBlack);
        southPanel.add(btnSugar);
        southPanel.add(btnDabang);
        southPanel.add(btnReset);
        c.add(southPanel, BorderLayout.SOUTH);

        setSize(600, 500);
        setVisible(true);
    }

    private void operate(int type) {
        if(ingredientDetails[0] == 0 || ingredientDetails[1] == 0 || ingredientDetails[2] == 0) {
            showError("부족한 것이 있습니다. 채워주세요.");
            return;
        }
        if (type == 1 && ingredientDetails[3] == 0) { 
            showError("부족한 것이 있습니다. 채워주세요.");
            return;
        } else if (type == 2 && (ingredientDetails[3] == 0 || ingredientDetails[4] == 0)) { 
            showError("부족한 것이 있습니다. 채워주세요.");
            return;
        }

        ingredientDetails[0]--; 
        ingredientDetails[1]--; 
        ingredientDetails[2]--; 
        if (type == 1) ingredientDetails[3]--;
        else if (type == 2) { ingredientDetails[3]--; ingredientDetails[4]--; }

        ImageIcon icon = new ImageIcon("coffee.png");
        coffeePanel.setCoffeeImage(icon.getImage());
        coffeePanel.repaint();

        JOptionPane.showMessageDialog(
            this, 
            "뜨거워요, 즐거운 하루~~", 
            "커피 나왔습니다", 
            JOptionPane.INFORMATION_MESSAGE
        );

        coffeePanel.setCoffeeImage(null);
        coffeePanel.repaint();
    }

    private void showError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "커피 자판기 경고", JOptionPane.ERROR_MESSAGE);
    }

    class CoffeePanel extends JPanel {
        private Image coffeeImg = null; 

        public void setCoffeeImage(Image img) {
            this.coffeeImg = img;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            
            int panelHeight = getHeight();
            int panelWidth = getWidth();
            int baseLine = panelHeight / 2;
            int barWidth = 40; 
            int gap = (panelWidth - (barWidth * 5)) / 6; 

            for (int i = 0; i < 5; i++) {
                int x = gap + (gap + barWidth) * i;
                g.setColor(Color.LIGHT_GRAY);
                g.fillRect(x, baseLine - 150, barWidth, 150); 
                int fillHeight = ingredientDetails[i] * 15; 
                g.setColor(new Color(150, 50, 50)); 
                g.fillRect(x, baseLine - fillHeight, barWidth, fillHeight);
                g.setColor(Color.BLACK);
                g.setFont(new Font("Arial", Font.PLAIN, 12));
                g.drawString(ingredientNames[i], x, baseLine + 20);
            }

            if (coffeeImg != null) {
                int imgW = 100; 
                int imgH = 100;
                int x = (panelWidth - imgW) / 2;
                int y = baseLine + 40; 
                g.drawImage(coffeeImg, x, y, imgW, imgH, this);
            }
        }
    }

    public static void main(String[] args) {
        new CoffeeVendingMachineFrame();
    }
}