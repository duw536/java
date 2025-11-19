package PracticalExercise2;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;

public class PracticalExercise2 extends JFrame {
    private ImagePanel imagePanel = new ImagePanel();

    public PracticalExercise2() {
        setTitle("문제 2: 이미지 로딩");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(imagePanel);
        
        createMenu();
        
        setSize(500, 400);
        setVisible(true);
    }

    private void createMenu() {
        JMenuBar mb = new JMenuBar();
        JMenu fileMenu = new JMenu("파일");
        
        JMenuItem openItem = new JMenuItem("열기");

        openItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();

                FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG Images", "jpg", "jpeg");
                chooser.setFileFilter(filter);

                int ret = chooser.showOpenDialog(null);
                if (ret == JFileChooser.APPROVE_OPTION) {
                    String path = chooser.getSelectedFile().getPath();
                    imagePanel.setImage(path);
                }
            }
        });

        fileMenu.add(openItem);
        mb.add(fileMenu);
        setJMenuBar(mb);
    }

    class ImagePanel extends JPanel {
        private Image img = null;

        public void setImage(String path) {
            this.img = new ImageIcon(path).getImage();
            repaint();
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (img != null) {
                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }

    public static void main(String[] args) {
        new PracticalExercise2();
    }
}