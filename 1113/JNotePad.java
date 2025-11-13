package Chapter10;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.GridLayout;
import javax.swing.text.Document;
import javax.swing.text.Highlighter;
import javax.swing.text.DefaultHighlighter;
import java.awt.Color;

public class JNotePad extends JFrame {
 private JTextPane _textPane;
 private ActionMap _actionMap;
 private boolean _isSaved = true;
 private JFileChooser _fc = new JFileChooser(".");
 private File _file = null;
 
 public JNotePad()
 {
  super("                              ");
  _textPane=new JTextPane();
  _textPane.getDocument().addDocumentListener (new DocumentListener()
  {
   public void insertUpdate (DocumentEvent e)
   {
    _isSaved = false;
   }
   
   public void removeUpdate (DocumentEvent e)
   {
    _isSaved = false;
   }
   public void changedUpdate(DocumentEvent e) 
   {
    _isSaved = false;    
   }
  });
  JScrollPane p = new JScrollPane(_textPane);
  add(p);
  _actionMap = createActionMap();
  setJMenuBar(createMenuBar());
  add(createToolBar(), BorderLayout.NORTH);
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  
 }
 
 private JMenuBar createMenuBar() {
  JMenuBar menubar = new JMenuBar();
  //File
  JMenu m = new JMenu("파일");
  m.add(new JMenuItem(_actionMap.get("새파일")));
  m.add(new JMenuItem(_actionMap.get("열기")));
  m.add(new JMenuItem(_actionMap.get("저장")));
  m.add(new JMenuItem(_actionMap.get("다른이름으로 저장")));
  m.addSeparator();
  m.add(new JMenuItem(_actionMap.get("종료")));
  menubar.add(m);
   
  //Edita
  m = new JMenu("수정");
  m.add(new JMenuItem(_actionMap.get("잘라내기")));
  m.add(new JMenuItem(_actionMap.get("복사")));
  m.add(new JMenuItem(_actionMap.get("붙여넣기")));
  m.add(new JMenuItem(_actionMap.get("찾기")));
  m.add(new JMenuItem(_actionMap.get("바꾸기")));
  menubar.add(m);
  
  //Help
  m = new JMenu("도움말");
  m.add(new JMenuItem(_actionMap.get("도움말")));
  m.add(new JMenuItem(_actionMap.get("정보")));
  menubar.add(m);
  
  return menubar;
 }
 
 private JToolBar createToolBar() {
  JToolBar toolbar = new JToolBar();
  toolbar.add(new JButton(_actionMap.get("새파일")));
  toolbar.add(new JButton(_actionMap.get("열기")));
  toolbar.add(new JButton(_actionMap.get("저장")));
  toolbar.add(new JButton(_actionMap.get("다른이름으로 저장")));
  toolbar.addSeparator();
  
  toolbar.add(new JButton(_actionMap.get("복사")));
  toolbar.add(new JButton(_actionMap.get("잘라내기")));
  toolbar.add(new JButton(_actionMap.get("붙여넣기")));
  toolbar.addSeparator();
  
  toolbar.add(new JButton(_actionMap.get("도움말")));
  toolbar.add(new JButton(_actionMap.get("정보")));
  
  Component[] comps = toolbar.getComponents();
  for (Component c : comps) 
  {
   if(c instanceof JButton)
    c.setFocusable(false);
  }
  
  return toolbar;
 } 
  
 private class NewAction extends AbstractAction
 {
  public NewAction()
  {
   super("새파일");
   putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("Ctrl+N"));
   putValue(Action.MNEMONIC_KEY, KeyEvent.VK_N);
  }
  public void actionPerformed(ActionEvent e)
  {
   System.out.println(getValue(Action.NAME));
   _textPane.setText("");
   _isSaved = true;
  }
 }
 
 private class OpenAction extends AbstractAction
 {
  public OpenAction(){
   super("열기");
   putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("Ctrl+O"));
   putValue(Action.MNEMONIC_KEY, KeyEvent.VK_O);
  }
  
  public void actionPerformed(ActionEvent e)
  {
   System.out.println(getValue(Action.NAME));
   if(!confirmSave())
    return;
   //open();
   _isSaved = open();
  }
 }
 
 private boolean open()
 {
  if(_fc.showOpenDialog(this)==JFileChooser.APPROVE_OPTION);
  {
   File file = _fc.getSelectedFile();
   try {
    open(file);
    _file = file;
    setTitle(file.getName() + " - JNotePad");
    return true;
   } catch (IOException e){
    JOptionPane.showMessageDialog(
      this,
      "파일을 열 수 없습니다" + file,
      "텍스트 에디터",
      JOptionPane.ERROR_MESSAGE);
    return false;
   }
  }
  
 }
 
 
 private boolean confirmSave() {
  if(_isSaved)
   return true;
  int ret = JOptionPane.showConfirmDialog
  (
    this, 
    "내용이 수정되었습니다. 저장하겠습니까?",
    "텍스트 에디터",
    JOptionPane.YES_NO_CANCEL_OPTION);
  switch(ret){
  case JOptionPane.YES_OPTION:
  save();
  return true;
  case JOptionPane.NO_OPTION:
  return true;
  default:
  return false;
  }
 }
 
 private boolean save()
 {
  if(_file==null)
   return saveAs();
  else
   try {
    save(_file);
    return true;
   } catch (IOException e) {
    showSaveErrorMessage(e);
    
   }
   return false;
 }
 
 
 private void showSaveErrorMessage(IOException e) {
  e.printStackTrace();
  String[] mesg = {
    "저장 할 수 없습니다." + _file,
    "접근불가"
  };
  JOptionPane.showMessageDialog(
    this,
    mesg,
    "텍스트 에디터",
    JOptionPane.ERROR_MESSAGE);
 }
 
 private void save(File file) throws IOException
 {
  BufferedWriter w = new BufferedWriter(new FileWriter(file));
  w.write(_textPane.getText());
  w.close();
 }
 
 public boolean saveAs() 
 {
  
  if (_fc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION)
  {
   File file = _fc.getSelectedFile();
   try 
   {
    save(file);//open(file);
    _file = file;
    setTitle(file.getName() + " - 텍스트 에디터");
    return true;
   }catch (IOException e){
    showSaveErrorMessage(e);
    return false;
   }
  }
  return false;
 }
 
 private class SaveAction extends AbstractAction
 {
  public SaveAction() {
   super("저장");
   putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("Ctrl+S"));
   putValue(Action.MNEMONIC_KEY, KeyEvent.VK_S);
  }
  public void actionPerformed(ActionEvent e)
  {
   System.out.println(getValue(Action.NAME));
   //save();
   _isSaved = save();
  }
 }
 
 private class SaveAsAction extends AbstractAction
 {
  public SaveAsAction()
  {
   super("다른이름으로 저장");
   putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("Ctrl+A"));
   putValue(Action.MNEMONIC_KEY, KeyEvent.VK_A);
  }
  public void actionPerformed(ActionEvent e)
  {
   System.out.println(getValue(Action.NAME));
   saveAs();
   _isSaved = saveAs();
  }
 }
 
 private void open(File file) throws IOException
 {
  BufferedReader r = new BufferedReader (new FileReader(file));
  StringBuffer sbuf = new StringBuffer();
  char[] buf = new char[1024];
  int nread;
  
  while ((nread = r.read(buf)) != -1){
   sbuf.append(buf, 0, nread);
   
  }
  r.close();
  _textPane.setText(sbuf.toString());
 }
 
 private class ExitAction extends AbstractAction
 {
  public ExitAction()
  {
   super("종료");
   putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("Ctrl+E"));
   putValue(Action.MNEMONIC_KEY, KeyEvent.VK_E);
  }
  public void actionPerformed(ActionEvent e)
  {
   System.out.println(getValue(Action.NAME));
   if(!confirmSave())
    return;
   System.exit(0);
  }
 }
 
 private class CutAction extends AbstractAction
 {
  public CutAction()
  {
   super("잘라내기");
   putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("Ctrl+X"));
   putValue(Action.MNEMONIC_KEY, KeyEvent.VK_X);
  }
  public void actionPerformed(ActionEvent e)
  {
   System.out.println(getValue(Action.NAME));
   _textPane.cut();
  }
 }
 
 private class CopyAction extends AbstractAction
 {
  public CopyAction()
  {
   super("복사하기");
   putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("Ctrl+C"));
   putValue(Action.MNEMONIC_KEY, KeyEvent.VK_C);
  }
  public void actionPerformed(ActionEvent e)
  {
   System.out.println(getValue(Action.NAME));
   _textPane.copy();
  }
 }
 
 private class PasteAction extends AbstractAction
 {
  public PasteAction()
  {
   super("붙여넣기");
   putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("Ctrl+O"));
   putValue(Action.MNEMONIC_KEY, KeyEvent.VK_O);
  }
  public void actionPerformed(ActionEvent e)
  {
   System.out.println(getValue(Action.NAME));
   _textPane.paste();
  }
 }
 
 private class FindAction extends AbstractAction {
	    public FindAction() {
	        super("찾기");
	        putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("Ctrl+F"));
	        putValue(Action.MNEMONIC_KEY, KeyEvent.VK_F);
	    }

	    @Override
	    public void actionPerformed(ActionEvent e) {
	        String keyword = JOptionPane.showInputDialog(JNotePad.this, "찾을 단어를 입력하세요:", "찾기", JOptionPane.PLAIN_MESSAGE);
	        if (keyword == null || keyword.isEmpty()) return;

	        // 기존 하이라이트 제거 후 새로 하이라이트
	        removeHighlights(_textPane);
	        highlightAllOccurrences(_textPane, keyword);
	    }
	}

//기존 하이라이트 제거
private void removeHighlights(JTextPane textPane) {
  Highlighter highlighter = textPane.getHighlighter();
  Highlighter.Highlight[] highlights = highlighter.getHighlights();

  for (Highlighter.Highlight h : highlights) {
      if (h.getPainter() instanceof DefaultHighlighter.DefaultHighlightPainter) {
          highlighter.removeHighlight(h);
      }
  }
}

//모든 단어를 하이라이트
private void highlightAllOccurrences(JTextPane textPane, String keyword) {
  try {
      Highlighter highlighter = textPane.getHighlighter();
      Document doc = textPane.getDocument();
      String text = doc.getText(0, doc.getLength());
      int pos = 0;

      DefaultHighlighter.DefaultHighlightPainter painter =
              new DefaultHighlighter.DefaultHighlightPainter(new Color(255, 255, 100)); // 연노란색

      int count = 0;
      while ((pos = text.indexOf(keyword, pos)) >= 0) {
          highlighter.addHighlight(pos, pos + keyword.length(), painter);
          pos += keyword.length();
          count++;
      }

      if (count == 0) {
          JOptionPane.showMessageDialog(this, "찾을 단어가 없습니다.", "찾기", JOptionPane.INFORMATION_MESSAGE);
      }

  } catch (Exception ex) {
      ex.printStackTrace();
  }
}
 
 private class ReplaceAction extends AbstractAction {
	    public ReplaceAction() {
	        super("바꾸기");
	        putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("Ctrl+R"));
	        putValue(Action.MNEMONIC_KEY, KeyEvent.VK_R);
	    }

	    public void actionPerformed(ActionEvent e) {
	        JPanel panel = new JPanel(new BorderLayout(5,5));
	        JPanel labels = new JPanel(new GridLayout(0,1,2,2));
	        labels.add(new JLabel("찾을 단어: ", SwingConstants.RIGHT));
	        labels.add(new JLabel("바꿀 단어: ", SwingConstants.RIGHT));
	        panel.add(labels, BorderLayout.WEST);

	        JPanel fields = new JPanel(new GridLayout(0,1,2,2));
	        JTextField findField = new JTextField();
	        JTextField replaceField = new JTextField();
	        fields.add(findField);
	        fields.add(replaceField);
	        panel.add(fields, BorderLayout.CENTER);

	        int result = JOptionPane.showConfirmDialog(JNotePad.this, panel, "바꾸기", JOptionPane.OK_CANCEL_OPTION);
	        if (result != JOptionPane.OK_OPTION) return;

	        String findText = findField.getText();
	        String replaceText = replaceField.getText();
	        if (findText.isEmpty()) return;

	        String text = _textPane.getText();
	        if (!text.contains(findText)) {
	            JOptionPane.showMessageDialog(JNotePad.this, "찾을 단어가 없습니다.", "바꾸기", JOptionPane.INFORMATION_MESSAGE);
	            return;
	        }

	        // 모두 바꾸기 확인
	        int confirm = JOptionPane.showConfirmDialog(JNotePad.this, "모두 바꾸시겠습니까?", "바꾸기", JOptionPane.YES_NO_OPTION);
	        if (confirm == JOptionPane.YES_OPTION) {
	            _textPane.setText(text.replace(findText, replaceText));
	            _isSaved = false;
	        } else {
	            // 한 번만 바꾸기
	            int index = text.indexOf(findText, _textPane.getCaretPosition());
	            if (index != -1) {
	                _textPane.select(index, index + findText.length());
	                _textPane.replaceSelection(replaceText);
	                _isSaved = false;
	            }
	        }
	    }
	} 
 
 private class AboutAction extends AbstractAction
 {
  public AboutAction()
  {
   super("정보");
  }
  public void actionPerformed(ActionEvent e)
  {
   String[] mesg={
     "텍스트 에디터 v1",
     "제작자: 최경섭"
   };
   JOptionPane.showMessageDialog(JNotePad.this, mesg, "텍스트 에디터 정보", JOptionPane.INFORMATION_MESSAGE);
   }
  }
 
 private class HelpAction extends AbstractAction
 {
  public HelpAction()
  {
  super("도움말");
  putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("Ctrl+H"));
  putValue(Action.MNEMONIC_KEY, KeyEvent.VK_H);
  }
  public void actionPerformed(ActionEvent e)
  {
   String[] mesg = {
    "아직 지원하지 않습니다."
     };
   JOptionPane.showMessageDialog(JNotePad.this, mesg, "텍스트 에디터", JOptionPane.INFORMATION_MESSAGE);
   }
 }
 
 private ActionMap createActionMap()
 {
  ActionMap am = new ActionMap();
  am.put("정보", new AboutAction());
  am.put("도움말", new HelpAction());
  am.put("잘라내기", new CutAction());
  am.put("복사", new CopyAction());
  am.put("붙여넣기", new PasteAction());
  am.put("종료", new ExitAction());
  am.put("새파일", new NewAction());
  am.put("열기", new OpenAction());
  am.put("저장", new SaveAction());
  am.put("다른이름으로 저장", new SaveAsAction());
  am.put("찾기", new FindAction());
  am.put("바꾸기", new ReplaceAction());
  return am;
 }
 
 private void start() {
  setSize(600,480);
  setLocation(100, 100);
  setVisible(true);
 }
 
 public static void main(String[] args) 
 {
  new JNotePad().start();
 }
 }
