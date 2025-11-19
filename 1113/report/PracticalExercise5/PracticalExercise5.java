package PracticalExercise5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.sound.sampled.*;

public class PracticalExercise5 extends JFrame {
    private Clip clip; 
    private JLabel label;

    public PracticalExercise5() {
        setTitle("오디오 시작 중단 연습");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.setLayout(new FlowLayout());

        label = new JLabel("bee.wav 연주 계속");
        c.add(label);

        loadAudio("bee.wav"); 

        c.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                if (clip != null) {
                    clip.start();
                    label.setText("bee.wav 연주 계속");

                    c.setBackground(Color.LIGHT_GRAY); 
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (clip != null) {
                    clip.stop();
                    label.setText("bee.wav 연주 일시 중단");

                    c.setBackground(Color.LIGHT_GRAY);
                }
            }
        });

        setSize(400, 200);
        setVisible(true);
    }

    private void loadAudio(String path) {
        try {
            File audioFile = new File(path); 
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            
            clip.start();
            
        } catch (Exception e) {
            label.setText("오디오 파일을 찾을 수 없습니다.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new PracticalExercise5();
    }
}
