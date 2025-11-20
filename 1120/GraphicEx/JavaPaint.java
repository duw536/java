package GraphicEx;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class JavaPaint extends JFrame {

    // [수정] DROPPER(스포이드) 도구 추가
    private enum Tool { PENCIL, ERASER, BUCKET, SELECT, TEXT, DROPPER, LINE, RECT, OVAL, TRIANGLE }
    private Tool currentTool = Tool.PENCIL;
    
    // 속성
    private Color mainColor = Color.BLACK;   // 색 1
    private Color subColor = Color.YELLOW;   // 색 2
    private int strokeWidth = 3;
    private boolean isFilled = true;
    
    private int selectedColorMode = 1; 

    // 컴포넌트
    private DrawPanel drawPanel;
    private JPanel previewPanel1;
    private JPanel previewPanel2;
    private JPanel colorBox1;
    private JPanel colorBox2;

    public JavaPaint() {
        setTitle("자바 그림판 (스포이드 기능 추가됨)");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        setJMenuBar(createMenuBar());

        setLayout(new BorderLayout());
        add(createRibbonPanel(), BorderLayout.NORTH);

        drawPanel = new DrawPanel();
        JPanel canvasBackground = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        canvasBackground.setBackground(new Color(200, 200, 200));
        canvasBackground.add(drawPanel);
        
        JScrollPane scrollPane = new JScrollPane(canvasBackground);
        scrollPane.setBorder(null);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    // =================================================
    // 메뉴바
    // =================================================
    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("파일(F)");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        JMenuItem newItem = new JMenuItem("새로 만들기(N)");
        newItem.addActionListener(e -> drawPanel.clear());
        JMenuItem openItem = new JMenuItem("열기(O)");
        openItem.addActionListener(e -> drawPanel.openFile());
        JMenuItem saveItem = new JMenuItem("저장(S)");
        saveItem.addActionListener(e -> drawPanel.saveFile());
        JMenuItem exitItem = new JMenuItem("끝내기(X)");
        exitItem.addActionListener(e -> System.exit(0));
        fileMenu.add(newItem); fileMenu.add(openItem); fileMenu.add(saveItem); fileMenu.addSeparator(); fileMenu.add(exitItem);

        JMenu editMenu = new JMenu("편집(E)");
        editMenu.setMnemonic(KeyEvent.VK_E);
        JMenuItem cutItem = new JMenuItem("잘라내기(T)");
        cutItem.addActionListener(e -> { drawPanel.copyToClipboard(); drawPanel.clear(); });
        JMenuItem copyItem = new JMenuItem("복사(C)");
        copyItem.addActionListener(e -> drawPanel.copyToClipboard());
        JMenuItem pasteItem = new JMenuItem("붙여넣기(P)");
        pasteItem.addActionListener(e -> drawPanel.pasteFromClipboard());
        editMenu.add(cutItem); editMenu.add(copyItem); editMenu.add(pasteItem);

        JMenu viewMenu = new JMenu("보기(V)");
        viewMenu.setMnemonic(KeyEvent.VK_V);
        JMenuItem zoomInItem = new JMenuItem("확대(I)");
        zoomInItem.addActionListener(e -> drawPanel.zoom(0.1));
        JMenuItem zoomOutItem = new JMenuItem("축소(O)");
        zoomOutItem.addActionListener(e -> drawPanel.zoom(-0.1));
        viewMenu.add(zoomInItem); viewMenu.add(zoomOutItem);

        menuBar.add(fileMenu); menuBar.add(editMenu); menuBar.add(viewMenu);
        return menuBar;
    }

    // =================================================
    // 리본 패널
    // =================================================
    private JPanel createRibbonPanel() {
        JPanel ribbon = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        ribbon.setBackground(new Color(245, 246, 247));
        ribbon.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));

        // 1. 도구 그룹
        JPanel toolGroup = createGroupPanel("도구");
        toolGroup.setLayout(new GridLayout(2, 3, 2, 2));
        toolGroup.add(createIconButton("펜", Tool.PENCIL));
        toolGroup.add(createIconButton("양동이", Tool.BUCKET));
        toolGroup.add(createIconButton("A", Tool.TEXT)); 
        toolGroup.add(createIconButton("지우개", Tool.ERASER));
        
        // [수정] 스포이드 버튼에 Tool.DROPPER 연결
        toolGroup.add(createIconButton("스포이드", Tool.DROPPER));
        
        toolGroup.add(createIconButton("돋보기", null));
        ribbon.add(toolGroup);

        ribbon.add(createSeparator());

        // 2. 이미지 (선택) 그룹
        JPanel imgGroup = createGroupPanel("이미지");
        imgGroup.setLayout(new FlowLayout(FlowLayout.CENTER));
        JButton selectBtn = createIconButton("<html><center>□<br>선택</center></html>", Tool.SELECT);
        selectBtn.setPreferredSize(new Dimension(50, 50));
        imgGroup.add(selectBtn);
        ribbon.add(imgGroup);

        ribbon.add(createSeparator());

        // 3. 도형 그룹
        JPanel shapeGroup = createGroupPanel("도형");
        shapeGroup.setLayout(new GridLayout(3, 4, 2, 2));
        shapeGroup.add(createShapeButton("／", Tool.LINE));
        shapeGroup.add(createShapeButton("○", Tool.OVAL));
        shapeGroup.add(createShapeButton("□", Tool.RECT));
        shapeGroup.add(createShapeButton("△", Tool.TRIANGLE));
        for(int i=0; i<8; i++) shapeGroup.add(new JButton(" "));
        ribbon.add(shapeGroup);

        // 4. 옵션
        JPanel optPanel = new JPanel(new GridLayout(2, 1));
        optPanel.setOpaque(false);
        JCheckBox fillCheck = new JCheckBox("채우기");
        fillCheck.setOpaque(false);
        fillCheck.setSelected(true);
        fillCheck.addActionListener(e -> isFilled = fillCheck.isSelected());
        JButton sizeBtn = new JButton("굵기 변경");
        sizeBtn.setBackground(Color.WHITE);
        sizeBtn.addActionListener(e -> {
            String input = JOptionPane.showInputDialog("선 굵기 (1~20):", strokeWidth);
            try { strokeWidth = Integer.parseInt(input); } catch(Exception ex){}
        });
        optPanel.add(fillCheck); optPanel.add(sizeBtn);
        ribbon.add(optPanel);
        ribbon.add(createSeparator());

        // 5. 색상 그룹
        JPanel colorGroup = createGroupPanel("색");
        colorGroup.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));

        colorBox1 = new JPanel(new BorderLayout());
        colorBox1.setOpaque(false);
        colorBox1.setBorder(createSelectedBorder(true));
        previewPanel1 = new JPanel();
        previewPanel1.setBackground(mainColor);
        previewPanel1.setPreferredSize(new Dimension(30, 30));
        previewPanel1.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        colorBox1.add(previewPanel1, BorderLayout.CENTER);
        colorBox1.add(new JLabel("색 1", SwingConstants.CENTER), BorderLayout.SOUTH);
        colorBox1.addMouseListener(new MouseAdapter() {
            @Override public void mousePressed(MouseEvent e) { selectedColorMode = 1; updateColorBoxSelection(); }
        });
        colorGroup.add(colorBox1);

        colorBox2 = new JPanel(new BorderLayout());
        colorBox2.setOpaque(false);
        colorBox2.setBorder(createSelectedBorder(false));
        previewPanel2 = new JPanel();
        previewPanel2.setBackground(subColor);
        previewPanel2.setPreferredSize(new Dimension(30, 30));
        previewPanel2.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        colorBox2.add(previewPanel2, BorderLayout.CENTER);
        colorBox2.add(new JLabel("색 2", SwingConstants.CENTER), BorderLayout.SOUTH);
        colorBox2.addMouseListener(new MouseAdapter() {
            @Override public void mousePressed(MouseEvent e) { selectedColorMode = 2; updateColorBoxSelection(); }
        });
        colorGroup.add(colorBox2);

        JPanel palettePanel = new JPanel(new GridLayout(3, 7, 2, 2));
        palettePanel.setOpaque(false);
        Color[] colors = {
            Color.BLACK, Color.GRAY, new Color(139,0,0), Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN,
            Color.WHITE, Color.LIGHT_GRAY, new Color(165,42,42), Color.PINK, new Color(255,215,0), new Color(237,234,190), Color.BLUE,
            Color.BLUE, Color.CYAN, Color.MAGENTA, new Color(128,0,128), new Color(0,128,128), new Color(0,255,127), new Color(75,0,130)
        };
        for (Color c : colors) {
            JButton cBtn = new JButton();
            cBtn.setBackground(c);
            cBtn.setPreferredSize(new Dimension(15, 15));
            cBtn.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
            cBtn.addActionListener(e -> {
                if (selectedColorMode == 1) { mainColor = c; previewPanel1.setBackground(c); } 
                else { subColor = c; previewPanel2.setBackground(c); }
            });
            palettePanel.add(cBtn);
        }
        colorGroup.add(palettePanel);
        ribbon.add(colorGroup);

        return ribbon;
    }

    private void updateColorBoxSelection() {
        colorBox1.setBorder(createSelectedBorder(selectedColorMode == 1));
        colorBox2.setBorder(createSelectedBorder(selectedColorMode == 2));
        colorBox1.revalidate(); colorBox1.repaint(); colorBox2.revalidate(); colorBox2.repaint();
    }
    private Border createSelectedBorder(boolean isSelected) {
        if (isSelected) return BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(100, 150, 255), 2), BorderFactory.createEmptyBorder(2, 2, 2, 2));
        return BorderFactory.createEmptyBorder(4, 4, 4, 4);
    }
    private JPanel createGroupPanel(String title) {
        JPanel p = new JPanel(); p.setBackground(new Color(245, 246, 247));
        if(!title.isEmpty()) { TitledBorder tb = BorderFactory.createTitledBorder(title); tb.setTitleFont(new Font("맑은 고딕", Font.PLAIN, 11)); tb.setTitleJustification(TitledBorder.CENTER); tb.setTitlePosition(TitledBorder.BOTTOM); p.setBorder(tb); }
        return p;
    }
    private JSeparator createSeparator() { JSeparator s = new JSeparator(SwingConstants.VERTICAL); s.setPreferredSize(new Dimension(3, 60)); return s; }
    
    private JButton createIconButton(String text, Tool tool) {
        JButton b = new JButton(text); 
        b.setMargin(new Insets(0,0,0,0)); 
        b.setFont(new Font("맑은 고딕", Font.PLAIN, 11)); 
        b.setFocusPainted(false);
        if(tool != null) b.addActionListener(e -> {
            drawPanel.pasteSelection();
            currentTool = tool;
        }); 
        return b;
    }
    private JButton createShapeButton(String text, Tool tool) {
        JButton b = new JButton(text); b.setMargin(new Insets(0,0,0,0)); b.setFont(new Font("맑은 고딕", Font.BOLD, 12)); 
        b.addActionListener(e -> {
            drawPanel.pasteSelection();
            currentTool = tool;
        }); 
        return b;
    }

    // =================================================
    // 그리기 패널
    // =================================================
    class DrawPanel extends JPanel {
        private BufferedImage canvas;
        private Graphics2D g2;
        
        private int startX, startY, curX, curY;
        private boolean isDrawing = false;
        private double zoomFactor = 1.0;

        private BufferedImage selectedImage;
        private Rectangle selectionRect;
        private boolean hasSelection = false;
        private boolean isDraggingSelection = false;
        private int dragOffsetX, dragOffsetY;

        public DrawPanel() {
            setPreferredSize(new Dimension(800, 600));
            setBackground(Color.WHITE);
            setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
            MyMouseHandler handler = new MyMouseHandler();
            addMouseListener(handler);
            addMouseMotionListener(handler);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.scale(zoomFactor, zoomFactor);

            if (canvas == null) {
                canvas = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
                g2 = canvas.createGraphics();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.WHITE);
                g2.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
            }
            g2d.drawImage(canvas, 0, 0, null);

            // 미리보기
            if (isDrawing && currentTool != Tool.PENCIL && currentTool != Tool.ERASER && currentTool != Tool.SELECT && currentTool != Tool.TEXT && currentTool != Tool.DROPPER) {
                g2d.setStroke(new BasicStroke(strokeWidth));
                drawShape(g2d, startX, startY, curX, curY, currentTool);
            }

            // 선택 영역 그리기
            if (currentTool == Tool.SELECT) {
                if (hasSelection && selectedImage != null) {
                    g2d.drawImage(selectedImage, selectionRect.x, selectionRect.y, null);
                    drawSelectionBorder(g2d, selectionRect.x, selectionRect.y, selectionRect.width, selectionRect.height);
                } else if (isDrawing && !hasSelection) {
                    int x = Math.min(startX, curX);
                    int y = Math.min(startY, curY);
                    int w = Math.abs(startX - curX);
                    int h = Math.abs(startY - curY);
                    drawSelectionBorder(g2d, x, y, w, h);
                }
            }
        }

        private void drawSelectionBorder(Graphics2D g, int x, int y, int w, int h) {
            Stroke dashed = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{4}, 0);
            g.setStroke(dashed);
            g.setColor(Color.BLUE);
            g.drawRect(x, y, w, h);
        }

        public void pasteSelection() {
            if (hasSelection && selectedImage != null) {
                g2.drawImage(selectedImage, selectionRect.x, selectionRect.y, null);
                hasSelection = false;
                selectedImage = null;
                selectionRect = null;
                repaint();
            }
        }

        public void clear() {
            if(g2 != null) {
                g2.setPaint(Color.WHITE);
                g2.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
                hasSelection = false;
                repaint();
            }
        }

        public void openFile() {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Image files", "png", "jpg", "jpeg", "gif");
            chooser.setFileFilter(filter);
            if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                try {
                    File file = chooser.getSelectedFile();
                    BufferedImage img = ImageIO.read(file);
                    if (img != null) {
                        pasteSelection();
                        g2.drawImage(img, 0, 0, null);
                        repaint();
                    }
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(this, "이미지를 열 수 없습니다.");
                }
            }
        }

        public void saveFile() {
            pasteSelection();
            JFileChooser chooser = new JFileChooser();
            chooser.setFileFilter(new FileNameExtensionFilter("PNG Image", "png"));
            if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                try {
                    File file = chooser.getSelectedFile();
                    if (!file.getName().toLowerCase().endsWith(".png")) file = new File(file.getParentFile(), file.getName() + ".png");
                    ImageIO.write(canvas, "png", file);
                    JOptionPane.showMessageDialog(this, "저장되었습니다.");
                } catch (IOException e) {}
            }
        }

        public void copyToClipboard() {
            pasteSelection();
            TransferableImage trans = new TransferableImage(canvas);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(trans, null);
            JOptionPane.showMessageDialog(this, "전체 그림이 복사되었습니다.");
        }

        public void pasteFromClipboard() {
            pasteSelection();
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            Transferable contents = clipboard.getContents(null);
            if (contents != null && contents.isDataFlavorSupported(DataFlavor.imageFlavor)) {
                try {
                    Image img = (Image) contents.getTransferData(DataFlavor.imageFlavor);
                    ImageIcon icon = new ImageIcon(img);
                    if (g2 != null) {
                        g2.drawImage(icon.getImage(), 0, 0, this);
                        repaint();
                    }
                } catch (Exception e) {}
            }
        }

        public void zoom(double factor) {
            zoomFactor += factor;
            if (zoomFactor < 0.1) zoomFactor = 0.1;
            setPreferredSize(new Dimension((int)(800 * zoomFactor), (int)(600 * zoomFactor)));
            revalidate(); repaint();
        }

        private void drawShape(Graphics2D g, int x1, int y1, int x2, int y2, Tool tool) {
            int x = Math.min(x1, x2); int y = Math.min(y1, y2);
            int w = Math.abs(x1 - x2); int h = Math.abs(y1 - y2);
            g.setColor(mainColor);
            switch (tool) {
                case LINE: g.drawLine(x1, y1, x2, y2); break;
                case RECT:
                    if(isFilled) { g.setColor(subColor); g.fillRect(x, y, w, h); g.setColor(mainColor); g.drawRect(x, y, w, h); } 
                    else { g.drawRect(x, y, w, h); } break;
                case OVAL:
                    if(isFilled) { g.setColor(subColor); g.fillOval(x, y, w, h); g.setColor(mainColor); g.drawOval(x, y, w, h); } 
                    else { g.drawOval(x, y, w, h); } break;
                case TRIANGLE: 
                    int[] xPoints = {x + w/2, x, x + w}; int[] yPoints = {y, y + h, y + h};
                    if(isFilled) { g.setColor(subColor); g.fillPolygon(xPoints, yPoints, 3); g.setColor(mainColor); g.drawPolygon(xPoints, yPoints, 3); } 
                    else { g.drawPolygon(xPoints, yPoints, 3); } break;
            }
        }

        class MyMouseHandler extends MouseAdapter {
            @Override public void mousePressed(MouseEvent e) {
                int mx = (int)(e.getX() / zoomFactor);
                int my = (int)(e.getY() / zoomFactor);

                // [추가] 스포이드 도구 로직
                if (currentTool == Tool.DROPPER) {
                    pasteSelection();
                    // 캔버스 범위 내인지 확인
                    if (mx >= 0 && mx < canvas.getWidth() && my >= 0 && my < canvas.getHeight()) {
                        int rgb = canvas.getRGB(mx, my);
                        Color pickedColor = new Color(rgb);
                        
                        if (selectedColorMode == 1) {
                            mainColor = pickedColor;
                            previewPanel1.setBackground(mainColor);
                        } else {
                            subColor = pickedColor;
                            previewPanel2.setBackground(subColor);
                        }
                    }
                    return;
                }

                // 선택 도구 로직
                if (currentTool == Tool.SELECT) {
                    if (hasSelection && selectionRect.contains(mx, my)) {
                        isDraggingSelection = true;
                        dragOffsetX = mx - selectionRect.x;
                        dragOffsetY = my - selectionRect.y;
                    } else {
                        pasteSelection();
                        startX = mx; startY = my; curX = mx; curY = my;
                        isDrawing = true;
                    }
                    return;
                }
                
                // 텍스트 입력 도구 로직
                if (currentTool == Tool.TEXT) {
                    pasteSelection(); 
                    String text = JOptionPane.showInputDialog(JavaPaint.this, "입력할 텍스트를 쓰세요:");
                    if (text != null && !text.isEmpty()) {
                        g2.setColor(mainColor);
                        int fontSize = 12 + (strokeWidth * 3);
                        g2.setFont(new Font("맑은 고딕", Font.BOLD, fontSize));
                        g2.drawString(text, mx, my);
                        repaint();
                    }
                    return; 
                }

                // 일반 그리기 도구
                pasteSelection();
                startX = mx; startY = my; curX = mx; curY = my;
                isDrawing = true;
                if (currentTool == Tool.BUCKET) { 
                    g2.setPaint(mainColor); g2.fillRect(0, 0, canvas.getWidth(), canvas.getHeight()); 
                    repaint(); isDrawing = false; 
                } else if (currentTool == Tool.PENCIL || currentTool == Tool.ERASER) { drawPath(); }
            }

            @Override public void mouseDragged(MouseEvent e) {
                int mx = (int)(e.getX() / zoomFactor);
                int my = (int)(e.getY() / zoomFactor);

                if (currentTool == Tool.SELECT) {
                    if (isDraggingSelection) {
                        selectionRect.x = mx - dragOffsetX;
                        selectionRect.y = my - dragOffsetY;
                    } else if (isDrawing) {
                        curX = mx; curY = my;
                    }
                    repaint();
                    return;
                }

                curX = mx; curY = my;
                if (currentTool == Tool.PENCIL || currentTool == Tool.ERASER) { drawPath(); startX = curX; startY = curY; }
                repaint();
            }

            @Override public void mouseReleased(MouseEvent e) {
                int mx = (int)(e.getX() / zoomFactor);
                int my = (int)(e.getY() / zoomFactor);

                if (currentTool == Tool.SELECT) {
                    if (isDrawing) {
                        isDrawing = false;
                        int x = Math.min(startX, mx);
                        int y = Math.min(startY, my);
                        int w = Math.abs(startX - mx);
                        int h = Math.abs(startY - my);

                        if (w > 0 && h > 0) {
                            BufferedImage sub = canvas.getSubimage(x, y, w, h);
                            selectedImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
                            Graphics2D gSub = selectedImage.createGraphics();
                            gSub.drawImage(sub, 0, 0, null);
                            gSub.dispose();
                            g2.setColor(Color.WHITE);
                            g2.fillRect(x, y, w, h);
                            selectionRect = new Rectangle(x, y, w, h);
                            hasSelection = true;
                        }
                    }
                    isDraggingSelection = false;
                    repaint();
                    return;
                }

                isDrawing = false;
                if (currentTool != Tool.PENCIL && currentTool != Tool.ERASER && currentTool != Tool.BUCKET && currentTool != Tool.TEXT && currentTool != Tool.DROPPER) {
                    g2.setStroke(new BasicStroke(strokeWidth));
                    drawShape(g2, startX, startY, mx, my, currentTool); repaint();
                }
            }

            private void drawPath() {
                if(currentTool == Tool.ERASER) { g2.setColor(Color.WHITE); g2.setStroke(new BasicStroke(strokeWidth*2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND)); }
                else { g2.setColor(mainColor); g2.setStroke(new BasicStroke(strokeWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND)); }
                g2.drawLine(startX, startY, curX, curY);
            }
        }
    }

    private static class TransferableImage implements Transferable {
        private Image image;
        public TransferableImage(Image image) { this.image = image; }
        @Override public DataFlavor[] getTransferDataFlavors() { return new DataFlavor[]{DataFlavor.imageFlavor}; }
        @Override public boolean isDataFlavorSupported(DataFlavor flavor) { return DataFlavor.imageFlavor.equals(flavor); }
        @Override public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException {
            if (isDataFlavorSupported(flavor)) return image;
            throw new UnsupportedFlavorException(flavor);
        }
    }

    public static void main(String[] args) {
        try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } catch (Exception e) {}
        new JavaPaint();
    }
}