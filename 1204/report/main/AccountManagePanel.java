package main;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import dao.AccountDAO;
import dto.Account;

public class AccountManagePanel extends JPanel {

    private MainGUI mainFrame;
    private AccountDAO accountDAO = new AccountDAO();
    private JTable table;
    private DefaultTableModel tableModel;
    private List<Account> currentList;

    public AccountManagePanel(MainGUI mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(null); // 절대 좌표 사용

        // 화면 전체 너비: 900, 높이: 600
        // 콘텐츠 너비: 700으로 설정 (좌우 여백 100씩)
        int contentX = 100; 
        int contentWidth = 700;

        // 1. 타이틀
        JLabel titleLabel = new JLabel("업로더(연구원) 계정 관리");
        titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 22));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER); // 텍스트 자체 중앙 정렬
        titleLabel.setBounds(0, 30, 900, 30); // 화면 전체 너비 사용
        add(titleLabel);

        // 2. 표 (Table)
        String[] colNames = {"No.", "아이디", "이름", "권한"};
        tableModel = new DefaultTableModel(colNames, 0) {
            @Override public boolean isCellEditable(int row, int col) { return false; }
        };

        table = new JTable(tableModel);
        
        // 컬럼 너비 조정 (표가 넓어졌으니 비율 조정)
        table.getColumnModel().getColumn(0).setPreferredWidth(50);  // No.
        table.getColumnModel().getColumn(1).setPreferredWidth(200); // 아이디
        table.getColumnModel().getColumn(2).setPreferredWidth(200); // 이름
        table.getColumnModel().getColumn(3).setPreferredWidth(150); // 권한

        JScrollPane scrollPane = new JScrollPane(table);
        // ★ 화면 중앙에 큼지막하게 배치 (X:100, Width:700)
        scrollPane.setBounds(contentX, 80, contentWidth, 350);
        add(scrollPane);


        // --- 버튼 배치 (표 바로 아래) ---
        int btnY = 450;

        // 3. 기능 버튼들 (왼쪽 정렬)
        JButton addBtn = new JButton("+ 계정 추가");
        addBtn.setBounds(contentX, btnY, 140, 45);
        addBtn.setBackground(new Color(200, 230, 255)); // 연한 파랑
        addBtn.setFont(new Font("맑은 고딕", Font.BOLD, 14));
        add(addBtn);

        JButton deleteBtn = new JButton("선택 삭제");
        deleteBtn.setBounds(contentX + 150, btnY, 120, 45);
        deleteBtn.setForeground(Color.RED);
        add(deleteBtn);

        JButton refreshBtn = new JButton("새로고침");
        refreshBtn.setBounds(contentX + 280, btnY, 100, 45);
        add(refreshBtn);

        // 4. 나가기 버튼 (오른쪽 정렬)
        // 표의 오른쪽 끝(800) - 버튼 크기(120) = 680
        JButton backBtn = new JButton("나가기");
        backBtn.setBounds(contentX + contentWidth - 120, btnY, 120, 45);
        add(backBtn);


        // --- 이벤트 연결 ---

        // A. 추가
        addBtn.addActionListener(e -> {
            JPanel inputPanel = new JPanel(new GridLayout(3, 2, 5, 5));
            JTextField idField = new JTextField();
            JTextField pwField = new JTextField();
            JTextField nameField = new JTextField();

            inputPanel.add(new JLabel("아이디:")); inputPanel.add(idField);
            inputPanel.add(new JLabel("비밀번호:")); inputPanel.add(pwField);
            inputPanel.add(new JLabel("이름:")); inputPanel.add(nameField);

            int result = JOptionPane.showConfirmDialog(mainFrame, inputPanel, 
                    "새 연구원 추가", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                Account newAcc = new Account();
                newAcc.setUsername(idField.getText());
                newAcc.setPassword(pwField.getText());
                newAcc.setName(nameField.getText());
                newAcc.setRole("UPLOADER"); 

                if (accountDAO.join(newAcc)) {
                    JOptionPane.showMessageDialog(mainFrame, "추가되었습니다.");
                    loadData();
                } else {
                    JOptionPane.showMessageDialog(mainFrame, "추가 실패 (아이디 중복)");
                }
            }
        });

        // B. 삭제
        deleteBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(mainFrame, "삭제할 계정을 선택하세요.");
                return;
            }

            Account target = currentList.get(row);
            int confirm = JOptionPane.showConfirmDialog(mainFrame, 
                "[" + target.getName() + "] 님을 삭제합니까?", "경고", JOptionPane.YES_NO_OPTION);
            
            if (confirm == JOptionPane.YES_OPTION) {
                accountDAO.deleteAccount(target.getId());
                loadData();
            }
        });

        // C. 새로고침
        refreshBtn.addActionListener(e -> loadData());

        // D. 뒤로가기
        backBtn.addActionListener(e -> mainFrame.showCard("DASHBOARD"));
    }

    // 데이터 로드
    public void loadData() {
        tableModel.setRowCount(0);
        currentList = accountDAO.getManageableAccounts(); 
        int no = 1;
        for (Account a : currentList) {
            tableModel.addRow(new Object[]{no++, a.getUsername(), a.getName(), a.getRole()});
        }
    }
}