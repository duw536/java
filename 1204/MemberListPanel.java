package Book;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MemberListPanel extends JPanel {
    LibraryMainGUI mainFrame;
    DefaultTableModel model = new DefaultTableModel(new String[]{"번호","이름","ID","전화","상태"}, 0);

    public MemberListPanel(LibraryMainGUI mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());
        add(new JScrollPane(new JTable(model)), BorderLayout.CENTER);
        
        JPanel bot = new JPanel();
        JButton btnLoad = new JButton("새로고침"), btnBack = new JButton("뒤로가기");
        bot.add(btnLoad); bot.add(btnBack); add(bot, BorderLayout.SOUTH);

        btnBack.addActionListener(e -> mainFrame.showCard("HOME"));
        btnLoad.addActionListener(e -> load());
    }
    // 화면이 보여질 때마다 호출해주면 좋은데, 여기선 새로고침 버튼으로 처리
    void load() {
        model.setRowCount(0);
        for(MemberDTO m : new MemberDAO().getAllMembers()) model.addRow(new Object[]{m.getMemberId(), m.getName(), m.getLoginId(), m.getPhone(), m.getStatus()});
    }
}