package Chapter10;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class ComboActionEx extends JFrame {
	private String [] fruits = {"apple", "banana", "mango"};
	private ImageIcon [] images = {new ImageIcon("images/apple.png"),
								new ImageIcon("images/banana.png"),
								new ImageIcon("images/mango.png") };
	private JLabel imgLabel = new JLabel(images[0]);
	
	public ComboActionEx() {
		setTitle("콤보박스 활용 예제");
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		JComboBox<String> combo = new JComboBox<String>(fruits);
		c.add(combo); c.add(imgLabel);
		
		combo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			JComboBox<String> cb = (JComboBox<String>)e.getSource();
			int index = cb.getSelectedIndex();
			imgLabel.setIcon(images[index]);
				}
			});
			setSize(500,500);
			setVisible(true);

	}
	public static void main(String[] args) {
		new ComboActionEx();
	}
}
