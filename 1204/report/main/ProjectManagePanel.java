package main;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import dao.ProjectDAO;
import dto.Category;
import dto.Project;
import dto.Material;

public class ProjectManagePanel extends JPanel {

    private MainGUI mainFrame;
    private ProjectDAO projectDAO = new ProjectDAO();

    private JTable projectTable;
    private DefaultTableModel tableModel;
    private JComboBox<Category> categoryCombo;
    
    private JTextField sampleNameField; 
    
    private JLabel csvLabel, pptxLabel, saveDirLabel;
    private File selectedCsvFile, selectedPptxFile, selectedSaveDir;

    public ProjectManagePanel(MainGUI mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(null); 

        // --- [1. 좌측: 표] ---
        JLabel listLabel = new JLabel("프로젝트 및 자료 현황");
        listLabel.setBounds(30, 20, 300, 20);
        listLabel.setFont(new Font("맑은 고딕", Font.BOLD, 14));
        add(listLabel);

        // 컬럼: No, 업로더, 분류, 프로젝트명, 샘플명, CSV, PPTX, PID(숨김), MID(숨김)
        String[] colNames = {"No.", "업로더", "분류", "프로젝트명", "샘플명", "CSV파일", "PPTX파일", "PID", "MID"};
        
        tableModel = new DefaultTableModel(colNames, 0) {
            @Override public boolean isCellEditable(int row, int col) { return false; }
        };
        projectTable = new JTable(tableModel);
        projectTable.getTableHeader().setReorderingAllowed(false);
        
        // 너비 설정
        projectTable.getColumnModel().getColumn(0).setPreferredWidth(40);  
        projectTable.getColumnModel().getColumn(1).setPreferredWidth(60); 
        projectTable.getColumnModel().getColumn(2).setPreferredWidth(60); 
        projectTable.getColumnModel().getColumn(3).setPreferredWidth(130); 
        projectTable.getColumnModel().getColumn(5).setPreferredWidth(100);
        
        // 숨김 처리 (PID, MID)
        projectTable.getColumnModel().getColumn(7).setMinWidth(0);
        projectTable.getColumnModel().getColumn(7).setMaxWidth(0);
        projectTable.getColumnModel().getColumn(7).setPreferredWidth(0);
        projectTable.getColumnModel().getColumn(8).setMinWidth(0);
        projectTable.getColumnModel().getColumn(8).setMaxWidth(0);
        projectTable.getColumnModel().getColumn(8).setPreferredWidth(0);

        JScrollPane scroll = new JScrollPane(projectTable);
        scroll.setBounds(30, 50, 480, 450); 
        add(scroll);


        // --- [2. 우측: 기능 패널] ---
        int rightX = 540; 
        int inputWidth = 300; 

        // (A) 프로젝트 생성
        JLabel newProjLabel = new JLabel("=== 1. 신규 프로젝트 생성 ===");
        newProjLabel.setFont(new Font("맑은 고딕", Font.BOLD, 14));
        newProjLabel.setForeground(new Color(0, 102, 204)); 
        newProjLabel.setBounds(rightX, 20, 300, 20);
        add(newProjLabel);

        // ★ [버튼 재배치] 되돌리기 삭제 -> 남은 버튼 2개 확장
        // 총 너비 300px 활용
        
        // 1. 분류 추가 (너비 200)
        JButton addCatBtn = new JButton("+ 분류 추가");
        addCatBtn.setBounds(rightX, 50, 200, 25);
        addCatBtn.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
        add(addCatBtn);

        // 2. 분류 삭제 (너비 95 + 간격 5)
        JButton delCatBtn = new JButton("삭제");
        delCatBtn.setBounds(rightX + 205, 50, 95, 25);
        delCatBtn.setFont(new Font("맑은 고딕", Font.BOLD, 12));
        delCatBtn.setForeground(Color.RED); 
        add(delCatBtn);

        // 콤보박스
        add(new JLabel("분류:")).setBounds(rightX, 85, 50, 25);
        categoryCombo = new JComboBox<>();
        categoryCombo.setBounds(rightX + 60, 85, 240, 25);
        add(categoryCombo);

        // 프로젝트 생성 버튼
        JButton createProjBtn = new JButton("프로젝트 만들기");
        createProjBtn.setBounds(rightX, 125, inputWidth, 35);
        add(createProjBtn);


        // (B) 구분선
        JSeparator sep = new JSeparator();
        sep.setBounds(rightX, 180, inputWidth, 2);
        add(sep);


        // (C) 자료 업로드
        int uploadStartY = 200; 

        JLabel uploadLabel = new JLabel("=== 2. 자료 업로드 & 삭제 ===");
        uploadLabel.setFont(new Font("맑은 고딕", Font.BOLD, 14));
        uploadLabel.setForeground(new Color(0, 102, 204));
        uploadLabel.setBounds(rightX, uploadStartY, 300, 20);
        add(uploadLabel);

        add(new JLabel("샘플명:")).setBounds(rightX, uploadStartY + 30, 60, 25);
        sampleNameField = new JTextField();
        sampleNameField.setBounds(rightX + 60, uploadStartY + 30, 240, 25);
        add(sampleNameField);

        JButton csvBtn = new JButton("CSV 찾기");
        csvBtn.setBounds(rightX, uploadStartY + 65, 100, 25);
        add(csvBtn);
        csvLabel = new JLabel("선택 안됨");
        csvLabel.setBounds(rightX + 110, uploadStartY + 65, 190, 25);
        add(csvLabel);

        JButton pptxBtn = new JButton("PPTX 찾기");
        pptxBtn.setBounds(rightX, uploadStartY + 100, 100, 25);
        add(pptxBtn);
        pptxLabel = new JLabel("선택 안됨");
        pptxLabel.setBounds(rightX + 110, uploadStartY + 100, 190, 25);
        add(pptxLabel);

        JButton dirBtn = new JButton("저장 위치");
        dirBtn.setBounds(rightX, uploadStartY + 135, 100, 25);
        dirBtn.setBackground(new Color(255, 255, 200)); 
        add(dirBtn);
        saveDirLabel = new JLabel("폴더 미지정");
        saveDirLabel.setBounds(rightX + 110, uploadStartY + 135, 190, 25);
        saveDirLabel.setForeground(Color.BLUE);
        add(saveDirLabel);

        JButton uploadBtn = new JButton("자료 업로드 및 저장");
        uploadBtn.setBounds(rightX, uploadStartY + 175, inputWidth, 40);
        uploadBtn.setFont(new Font("맑은 고딕", Font.BOLD, 14));
        uploadBtn.setBackground(new Color(200, 230, 255)); 
        add(uploadBtn);

        JButton delProjectBtn = new JButton("선택한 프로젝트 전체 삭제");
        delProjectBtn.setBounds(rightX, uploadStartY + 225, inputWidth, 35);
        delProjectBtn.setFont(new Font("맑은 고딕", Font.BOLD, 12));
        delProjectBtn.setForeground(Color.RED);
        add(delProjectBtn);


        // --- [하단 버튼] ---
        JButton backBtn = new JButton("나가기");
        backBtn.setBounds(640, 520, 220, 40);
        add(backBtn);


        // --- [이벤트 핸들러] ---
        loadData(); 
        backBtn.addActionListener(e -> mainFrame.showCard("DASHBOARD"));

        // 카테고리 추가
        addCatBtn.addActionListener(e -> {
            String name = JOptionPane.showInputDialog(this, "새 카테고리 이름:");
            if(name != null && !name.trim().isEmpty()) {
                projectDAO.addCategory(name); loadData();
            }
        });
        // 카테고리 삭제
        delCatBtn.addActionListener(e -> {
            Category selected = (Category) categoryCombo.getSelectedItem();
            if(selected == null) return;
            int confirm = JOptionPane.showConfirmDialog(this, "[" + selected.getName() + "] 분류 삭제?", "경고", JOptionPane.YES_NO_OPTION);
            if(confirm == JOptionPane.YES_OPTION) {
                projectDAO.deleteCategory(selected.getId()); loadData();
            }
        });
        
        // 프로젝트 생성
        createProjBtn.addActionListener(e -> {
            Category cat = (Category) categoryCombo.getSelectedItem();
            if(cat == null) { JOptionPane.showMessageDialog(this, "분류 선택 필수"); return; }
            String name = JOptionPane.showInputDialog(this, "프로젝트(방) 이름:");
            if(name != null && !name.trim().isEmpty()) {
                if(projectDAO.addProject(new Project(cat.getId(), name, ""))) {
                    JOptionPane.showMessageDialog(this, "생성 완료"); loadData();
                } else {
                    JOptionPane.showMessageDialog(this, "생성 실패 (중복)");
                }
            }
        });

        // 파일 선택
        csvBtn.addActionListener(e -> {
            JFileChooser fc = new JFileChooser();
            if(fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                selectedCsvFile = fc.getSelectedFile(); csvLabel.setText(selectedCsvFile.getName());
            }
        });
        pptxBtn.addActionListener(e -> {
            JFileChooser fc = new JFileChooser();
            if(fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                selectedPptxFile = fc.getSelectedFile(); pptxLabel.setText(selectedPptxFile.getName());
            }
        });
        dirBtn.addActionListener(e -> {
            JFileChooser fc = new JFileChooser();
            fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            if(fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                selectedSaveDir = fc.getSelectedFile(); saveDirLabel.setText(selectedSaveDir.getName());
            }
        });
        
        // 업로드
        uploadBtn.addActionListener(e -> {
            int row = projectTable.getSelectedRow();
            if (row == -1) { JOptionPane.showMessageDialog(this, "프로젝트 선택 필요!"); return; }
            
            if (sampleNameField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "샘플명을 입력해주세요!"); return;
            }
            if (selectedCsvFile == null || selectedSaveDir == null) {
                JOptionPane.showMessageDialog(this, "CSV 및 저장위치 필수!"); return;
            }
            
            String pidStr = tableModel.getValueAt(row, 7).toString();
            int projectId = Integer.parseInt(pidStr);
            
            try {
                File destCsv = new File(selectedSaveDir, selectedCsvFile.getName());
                Files.copy(selectedCsvFile.toPath(), destCsv.toPath(), StandardCopyOption.REPLACE_EXISTING);
                String pptxName=null, pptxPath=null;
                if(selectedPptxFile!=null){
                    File destPptx=new File(selectedSaveDir, selectedPptxFile.getName());
                    Files.copy(selectedPptxFile.toPath(), destPptx.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    pptxName=selectedPptxFile.getName(); pptxPath=destPptx.getAbsolutePath();
                }
                Material m = new Material(projectId, mainFrame.session.getId(), sampleNameField.getText(), 
                        selectedCsvFile.getName(), destCsv.getAbsolutePath(), pptxName, pptxPath);
                
                if(projectDAO.addMaterial(m)) {
                    JOptionPane.showMessageDialog(this, "업로드 완료!");
                    sampleNameField.setText(""); csvLabel.setText("-"); selectedCsvFile=null; 
                    pptxLabel.setText("-"); selectedPptxFile=null; loadData();
                }
            } catch (Exception ex) { ex.printStackTrace(); }
        });

        // 프로젝트 전체 삭제
        delProjectBtn.addActionListener(e -> {
            int row = projectTable.getSelectedRow();
            if (row == -1) { JOptionPane.showMessageDialog(this, "삭제할 행을 선택하세요."); return; }
            
            String pName = tableModel.getValueAt(row, 3).toString(); 
            String pidStr = tableModel.getValueAt(row, 7).toString();
            int pid = Integer.parseInt(pidStr);

            int confirm = JOptionPane.showConfirmDialog(this, 
                "[" + pName + "] 프로젝트 전체 삭제?\n(자료도 모두 삭제됩니다)", 
                "전체 삭제", JOptionPane.YES_NO_OPTION);
            
            if(confirm == JOptionPane.YES_OPTION) {
                if(projectDAO.deleteProject(pid)) {
                    JOptionPane.showMessageDialog(this, "삭제되었습니다.");
                    loadData(); 
                }
            }
        });
    }

    public void loadData() {
        categoryCombo.removeAllItems();
        for(Category c : projectDAO.getCategoryList()) categoryCombo.addItem(c);
        tableModel.setRowCount(0);
        List<String[]> list = projectDAO.getProjectWithMaterials();
        
        int virtualNo = 1;
        for(String[] row : list) {
            tableModel.addRow(new Object[] {
                virtualNo++, // No.
                row[1],      // 업로더
                row[2],      // 분류
                row[3],      // 프로젝트명
                row[4],      // 샘플명
                row[5],      // CSV
                row[6],      // PPTX
                row[0],      // PID(숨김)
                row[7]       // MID(숨김)
            });
        }
    }
}