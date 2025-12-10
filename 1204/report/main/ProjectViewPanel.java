package main;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import dao.ProjectDAO;

public class ProjectViewPanel extends JPanel {

    private MainGUI mainFrame;
    private ProjectDAO projectDAO = new ProjectDAO();
    
    private JTable viewTable;
    private DefaultTableModel tableModel;
    private TableRowSorter<DefaultTableModel> sorter; // 검색 필터용
    private JTextField searchField;

    public ProjectViewPanel(MainGUI mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(null);

        // 1. 상단: 타이틀 및 검색
        JLabel titleLabel = new JLabel("프로젝트 및 자료 조회");
        titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        titleLabel.setBounds(30, 20, 250, 30);
        add(titleLabel);

        JLabel searchLabel = new JLabel("통합 검색:");
        searchLabel.setBounds(300, 25, 70, 25);
        add(searchLabel);

        searchField = new JTextField();
        searchField.setBounds(370, 25, 200, 25);
        add(searchField);

        JButton searchBtn = new JButton("검색");
        searchBtn.setBounds(580, 25, 80, 25);
        add(searchBtn);
        
        // 검색 초기화 버튼
        JButton resetBtn = new JButton("전체보기");
        resetBtn.setBounds(670, 25, 90, 25);
        add(resetBtn);


        // 2. 중앙: 테이블 (읽기 전용)
        // 화면에 보여줄 컬럼들
        String[] colNames = {"No.", "업로더", "분류", "프로젝트명", "샘플명", "CSV파일", "PPTX파일", 
                             "CSV경로", "PPTX경로"}; // 뒤에 2개는 숨길 예정
        
        tableModel = new DefaultTableModel(colNames, 0) {
            @Override public boolean isCellEditable(int row, int col) { return false; }
        };
        
        viewTable = new JTable(tableModel);
        viewTable.getTableHeader().setReorderingAllowed(false);
        
        // 검색 필터 적용을 위한 Sorter 설정
        sorter = new TableRowSorter<>(tableModel);
        viewTable.setRowSorter(sorter);

        // 컬럼 너비 설정
        viewTable.getColumnModel().getColumn(0).setPreferredWidth(40);  // No.
        viewTable.getColumnModel().getColumn(1).setPreferredWidth(70);  // 업로더
        viewTable.getColumnModel().getColumn(2).setPreferredWidth(60);  // 분류
        viewTable.getColumnModel().getColumn(3).setPreferredWidth(150); // 프로젝트명
        viewTable.getColumnModel().getColumn(5).setPreferredWidth(120); // CSV

        // ★ 경로 컬럼(7, 8번) 숨기기
        viewTable.getColumnModel().getColumn(7).setMinWidth(0);
        viewTable.getColumnModel().getColumn(7).setMaxWidth(0);
        viewTable.getColumnModel().getColumn(7).setPreferredWidth(0);
        
        viewTable.getColumnModel().getColumn(8).setMinWidth(0);
        viewTable.getColumnModel().getColumn(8).setMaxWidth(0);
        viewTable.getColumnModel().getColumn(8).setPreferredWidth(0);

        JScrollPane scroll = new JScrollPane(viewTable);
        scroll.setBounds(30, 70, 830, 400); // 꽉 차게 배치
        add(scroll);


        // 3. 하단: 다운로드 버튼들
        JLabel tipLabel = new JLabel("💡 팁: 목록에서 행을 선택하고 다운로드 버튼을 누르세요.");
        tipLabel.setBounds(30, 490, 400, 20);
        tipLabel.setForeground(Color.DARK_GRAY);
        add(tipLabel);

        // CSV 다운로드
        JButton downCsvBtn = new JButton("CSV 파일 다운로드");
        downCsvBtn.setBounds(460, 490, 180, 40);
        downCsvBtn.setBackground(new Color(200, 255, 200)); // 연한 초록
        downCsvBtn.setFont(new Font("맑은 고딕", Font.BOLD, 14));
        add(downCsvBtn);

        // PPTX 다운로드
        JButton downPptxBtn = new JButton("PPTX 파일 다운로드");
        downPptxBtn.setBounds(650, 490, 180, 40);
        downPptxBtn.setBackground(new Color(255, 230, 200)); // 연한 주황
        downPptxBtn.setFont(new Font("맑은 고딕", Font.BOLD, 14));
        add(downPptxBtn);

        // 뒤로가기
        JButton backBtn = new JButton("메인으로");
        backBtn.setBounds(30, 520, 100, 30);
        add(backBtn);


        // --- 이벤트 연결 ---

        // ★ 수정됨: 시작하자마자 loadData()를 호출하던 코드를 삭제했습니다.
        // 처음엔 빈 화면이 뜹니다.

        // 1. 검색 기능 (엔터키 or 버튼)
        // ★ 수정됨: 검색 버튼을 누르면 그제서야 데이터를 가져오고(loadData) 필터를 겁니다.
        searchBtn.addActionListener(e -> {
            loadData(); // 최신 데이터 가져오기
            filter(searchField.getText()); // 그 다음 검색어 적용
        });

        searchField.addActionListener(e -> {
            loadData();
            filter(searchField.getText());
        });
        
        // 전체보기 버튼 (초기화)
        resetBtn.addActionListener(e -> {
            searchField.setText("");
            filter(""); 
            loadData(); // 전체 데이터 로드
        });

        // 2. CSV 다운로드
        downCsvBtn.addActionListener(e -> downloadFile(true)); // true = CSV

        // 3. PPTX 다운로드
        downPptxBtn.addActionListener(e -> downloadFile(false)); // false = PPTX

        // 4. 뒤로가기
        backBtn.addActionListener(e -> mainFrame.showCard("DASHBOARD"));
    }

    // 파일 다운로드 로직 (CSV/PPTX 공용)
    private void downloadFile(boolean isCsv) {
        int row = viewTable.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "먼저 목록에서 항목을 선택해주세요.");
            return;
        }

        // 검색(Sorter) 적용 시 행 번호가 섞이므로, 모델 기준 인덱스로 변환
        int modelRow = viewTable.convertRowIndexToModel(row);

        // 숨겨둔 경로 가져오기 (7번:CSV경로, 8번:PPTX경로)
        int pathCol = isCsv ? 7 : 8;
        int nameCol = isCsv ? 5 : 6;
        
        String srcPath = (String) tableModel.getValueAt(modelRow, pathCol);
        String fileName = (String) tableModel.getValueAt(modelRow, nameCol);

        if (srcPath == null || srcPath.isEmpty() || fileName == null || fileName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "해당 파일은 등록되지 않았습니다.");
            return;
        }

        File srcFile = new File(srcPath);
        if (!srcFile.exists()) {
            JOptionPane.showMessageDialog(this, "서버에 원본 파일이 없습니다.\n(경로: " + srcPath + ")");
            return;
        }

        // 저장할 위치 선택창 띄우기
        JFileChooser fc = new JFileChooser();
        fc.setSelectedFile(new File(fileName)); // 기본 파일명 세팅
        fc.setDialogTitle("저장할 위치를 선택하세요");
        
        if (fc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File destFile = fc.getSelectedFile();
            try {
                // 파일 복사 수행
                Files.copy(srcFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                JOptionPane.showMessageDialog(this, "다운로드 완료!\n" + destFile.getAbsolutePath());
                
                // (선택사항) 다운로드 후 바로 열어볼지 물어보기
                int open = JOptionPane.showConfirmDialog(this, "파일을 바로 여시겠습니까?", "확인", JOptionPane.YES_NO_OPTION);
                if(open == JOptionPane.YES_OPTION) {
                    Desktop.getDesktop().open(destFile);
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "다운로드 실패: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    // 검색 필터링
    private void filter(String text) {
        if (text == null || text.trim().isEmpty()) {
            sorter.setRowFilter(null);
        } else {
            // 대소문자 구분 없이 모든 컬럼 검색
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
        }
    }

    // 데이터 불러오기
    public void loadData() {
        tableModel.setRowCount(0);
        List<String[]> list = projectDAO.getProjectWithMaterials();
        
        int virtualNo = 1;
        for (String[] row : list) {
            // DAO에서 온 순서: [0:ID, 1:Up, 2:Cat, 3:PName, 4:Samp, 5:CSV, 6:PPTX, 7:MID, 8:CsvPath, 9:PptxPath]
            // 화면 모델 순서: [No, Up, Cat, PName, Samp, CSV, PPTX, CsvPath, PptxPath]
            
            tableModel.addRow(new Object[]{
                virtualNo++, 
                row[1], // 업로더
                row[2], // 분류
                row[3], // 프로젝트명
                row[4], // 샘플명
                row[5], // CSV파일명
                row[6], // PPTX파일명
                row[8], // CSV경로 (숨김)
                row[9]  // PPTX경로 (숨김)
            });
        }
    }
}