package OpenChallenge;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.*;

public class OpenChallenge extends JFrame {
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    Statement stmt = null;

    JLabel imageLabel = new JLabel("사진 없음", SwingConstants.CENTER);
    JButton nextBtn = new JButton("다음 사진");

    public OpenChallenge() {
        super("이미지 데이터 베이스");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.setLayout(new BorderLayout());

        JMenuBar mb = new JMenuBar();
        JMenu fileMenu = new JMenu("메뉴");
        JMenuItem saveItem = new JMenuItem("사진 저장");
        JMenuItem viewItem = new JMenuItem("모든 사진 보기");
        JMenuItem exitItem = new JMenuItem("종료");

        fileMenu.add(saveItem);
        fileMenu.add(viewItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);
        mb.add(fileMenu);
        setJMenuBar(mb);

        c.add(imageLabel, BorderLayout.CENTER);
        c.add(nextBtn, BorderLayout.SOUTH);
        nextBtn.setEnabled(false);

        setSize(400, 400);
        setVisible(true);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookdb", "root", "1111");
            System.out.println("DB 연결 성공");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "DB 연결 실패: " + e.getMessage());
        }

        saveItem.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "JPG & PNG Images", "jpg", "png");
            chooser.setFileFilter(filter);

            int ret = chooser.showOpenDialog(null);
            if (ret != JFileChooser.APPROVE_OPTION) {
                return;
            }

            File selectedFile = chooser.getSelectedFile();
            String filePath = selectedFile.getPath();
            String fileName = selectedFile.getName();

            try {
                String sql = "INSERT INTO Images (filename, file) VALUES (?, ?)";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, fileName);

                FileInputStream fis = new FileInputStream(selectedFile);
                pstmt.setBinaryStream(2, fis, (int) selectedFile.length());

                pstmt.executeUpdate();
                fis.close();
                pstmt.close();

                JOptionPane.showMessageDialog(this, fileName + " 저장 완료!");
                
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "저장 실패: " + ex.getMessage());
            }
        });

        viewItem.addActionListener(e -> {
            try {
                if (stmt != null) stmt.close();
                if (rs != null) rs.close();

                stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                rs = stmt.executeQuery("SELECT * FROM Images");

                if (rs.next()) {
                    showImage();
                    nextBtn.setEnabled(true);
                } else {
                    imageLabel.setText("저장된 사진이 없습니다.");
                    imageLabel.setIcon(null);
                    nextBtn.setEnabled(false);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        exitItem.addActionListener(e -> System.exit(0));

        nextBtn.addActionListener(e -> {
            try {
                if (rs != null && rs.next()) {
                    showImage();
                } else {
                    JOptionPane.showMessageDialog(this, "마지막 사진입니다.");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
    }

    private void showImage() throws SQLException {
        String fileName = rs.getString("filename");
        Blob blob = rs.getBlob("file");
        
        byte[] imageBytes = blob.getBytes(1, (int) blob.length());
        ImageIcon icon = new ImageIcon(imageBytes);

        Image img = icon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(img));
        imageLabel.setText("");
        
        setTitle("이미지 데이터 베이스 - " + fileName);
    }

    public static void main(String[] args) {
        new OpenChallenge();
    }
}
