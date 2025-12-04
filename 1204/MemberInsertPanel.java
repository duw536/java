package Book;
import javax.swing.*;
import java.awt.*;

public class MemberInsertPanel extends JPanel {
    LibraryMainGUI mainFrame;
    JTextField tfName=new JTextField(), tfId=new JTextField(), tfPhone=new JTextField();
    JPasswordField pfPw=new JPasswordField();

    public MemberInsertPanel(LibraryMainGUI mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());

        JPanel cen = new JPanel(new GridLayout(4,2,10,10));
        cen.setBorder(BorderFactory.createEmptyBorder(50,50,50,50));
        cen.add(new JLabel("이름:")); cen.add(tfName);
        cen.add(new JLabel("ID:")); cen.add(tfId);
        cen.add(new JLabel("PW:")); cen.add(pfPw);
        cen.add(new JLabel("전화:")); cen.add(tfPhone);
        add(cen, BorderLayout.CENTER);

        JPanel bot = new JPanel();
        JButton btnAdd = new JButton("등록"), btnBack = new JButton("뒤로가기");
        bot.add(btnAdd); bot.add(btnBack); add(bot, BorderLayout.SOUTH);

        btnBack.addActionListener(e -> mainFrame.showCard("HOME"));
        btnAdd.addActionListener(e -> {
            if(new MemberDAO().insertMember(new MemberDTO(0, tfName.getText(), tfId.getText(), new String(pfPw.getPassword()), tfPhone.getText(), "ACTIVE", "USER")) > 0)
                JOptionPane.showMessageDialog(this, "가입완료");
            else JOptionPane.showMessageDialog(this, "실패(ID중복 등)");
        });
    }
}