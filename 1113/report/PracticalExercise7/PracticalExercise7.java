package PracticalExercise7;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.sound.sampled.*;

public class PracticalExercise7 extends JFrame {
    private JLabel statusLabel;
    private Clip clip;

    public PracticalExercise7() {
        setTitle("오디오 파일을 찾아 연주/종료 제어");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.setLayout(new FlowLayout());

        statusLabel = new JLabel("오디오 파일을 선택하세요");
        c.add(statusLabel);

        createMenu();

        setSize(400, 200);
        setVisible(true);
    }

    private void createMenu() {
        JMenuBar mb = new JMenuBar();
        JMenu audioMenu = new JMenu("오디오");
        
        JMenuItem playItem = new JMenuItem("연주");
        JMenuItem stopItem = new JMenuItem("종료");

        playItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Audio Files(wav, au, mid, rmf)", "wav", "au", "mid", "rmf");
                fileChooser.setFileFilter(filter);

                int result = fileChooser.showOpenDialog(PracticalExercise7.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    playAudio(selectedFile);
                    statusLabel.setText(selectedFile.getName() + " 를 연주하고 있습니다.");
                }
            }
        });

        stopItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (clip != null && clip.isRunning()) {
                    clip.stop();
                    statusLabel.setText("연주가 중단되었습니다.");
                }
            }
        });

        audioMenu.add(playItem);
        audioMenu.add(stopItem);
        mb.add(audioMenu);
        setJMenuBar(mb);
    }

    private void playAudio(File file) {
        try {
            if (clip != null) {
                clip.stop();
                clip.close();
            }

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();

        } catch (Exception e) {
            statusLabel.setText("오디오 재생 오류: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new PracticalExercise7();
    }
}
