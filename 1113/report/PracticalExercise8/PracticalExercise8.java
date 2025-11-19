package PracticalExercise8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.sound.sampled.*;

public class PracticalExercise8 extends JFrame {
    private String[] names = {"bee.wav", "bee2.wav", "bee3.wav", "bee4.wav"};
    private JCheckBox[] checkBoxes = new JCheckBox[names.length];
    
    private Clip clip;
    private int currentSongIndex = -1;

    public PracticalExercise8() {
        setTitle("오디오 연주");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.setLayout(new FlowLayout());

        JLabel msgLabel = new JLabel("체크된 곡만 순서대로 한 번 연주합니다.");
        c.add(msgLabel);

        for (int i = 0; i < names.length; i++) {
            checkBoxes[i] = new JCheckBox(names[i]);
            c.add(checkBoxes[i]);
        }

        JButton startBtn = new JButton("연주 시작");
        JButton stopBtn = new JButton("연주 끝");

        startBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (clip != null && clip.isActive()) return;
                playNextSong(-1);
            }
        });

        stopBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopAudio();
            }
        });

        c.add(startBtn);
        c.add(stopBtn);

        setSize(350, 300);
        setVisible(true);
    }
    
    private void playNextSong(int finishedIndex) {
        for (int i = finishedIndex + 1; i < names.length; i++) {
            if (checkBoxes[i].isSelected()) {
                currentSongIndex = i;
                loadAndPlay(i);
                return;
            }
        }
        
        stopAudio();
    }

    private void loadAndPlay(int index) {
        try {
            if (clip != null) {
                clip.close();
            }

            File audioFile = new File(names[index]);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.addLineListener(new LineListener() {
                @Override
                public void update(LineEvent event) {
                    if (event.getType() == LineEvent.Type.STOP) {
                        if (clip.getMicrosecondPosition() >= clip.getMicrosecondLength()) {
                            playNextSong(index); 
                        }
                    }
                }
            });
            
            clip.start();

            resetColors();
            checkBoxes[index].setForeground(Color.RED);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "오디오 파일을 찾을 수 없습니다: " + names[index]);
        }
    }

    private void stopAudio() {
        if (clip != null) {
            clip.stop();
            clip.close();
        }
        resetColors();
        currentSongIndex = -1;
    }

    private void resetColors() {
        for (int i = 0; i < checkBoxes.length; i++) {
            checkBoxes[i].setForeground(Color.BLACK);
        }
    }

    public static void main(String[] args) {
        new PracticalExercise8();
    }
}
