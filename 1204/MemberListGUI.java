package Lib;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class MemberListGUI extends JFrame {

    // 테이블 관련
    String[] colNames = {"회원번호", "이름", "아이디", "연락처", "상태"};
    
    DefaultTableModel model = new DefaultTableModel(colNames, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    
    JTable table = new JTable(model);
    JScrollPane scrollPane = new JScrollPane(table);

    JButton btnBack = new JButton("뒤로가기");

    public MemberListGUI() {
        setTitle("전체 회원 목록");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // 1. 중앙: 테이블 배치
        add(scrollPane, BorderLayout.CENTER);

        // 2. 하단: 뒤로가기 버튼
        JPanel pnlSouth = new JPanel();
        pnlSouth.add(btnBack);
        add(pnlSouth, BorderLayout.SOUTH);

        // 3. 데이터 불러오기 (창이 열리자마자 실행)
        loadData();

        // 4. 이벤트: 뒤로가기
        btnBack.addActionListener(e -> dispose());

        setVisible(true);
    }

    // DB에서 목록을 가져와 테이블에 채우는 메서드
    private void loadData() {
        MemberDAO dao = new MemberDAO();
        ArrayList<MemberDTO> list = dao.getAllMembers();

        // 기존 데이터 초기화
        model.setRowCount(0);

        for (MemberDTO m : list) {
            Object[] row = {
                m.getMemberId(),
                m.getName(),
                m.getLoginId(),
                // 비밀번호는 보안상 제외
                m.getPhone(),
                m.getStatus()
            };
            model.addRow(row);
        }
        
        // 회원이 한 명도 없을 경우
        if (list.size() == 0) {
            JOptionPane.showMessageDialog(null, "등록된 회원이 없습니다.");
        }
    }
}