package Chapter10;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class RadioButtonExTest extends JFrame{
	private JLabel imgLabel;
	
	public RadioButtonExTest() {
		setTitle("라디오 버튼");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		ButtonGroup g = new ButtonGroup(); 
		
		JRadioButton car = new JRadioButton("자동차");
		JRadioButton ship = new JRadioButton("배");
		JRadioButton airplane = new JRadioButton("항공기");
		
		g.add(car);
		g.add(ship);
		g.add(airplane);
		
		imgLabel = new JLabel();
        c.add(car); c.add(ship); c.add(airplane);
        c.add(imgLabel);
		
        car.addActionListener(e -> imgLabel.setIcon(new ImageIcon("images/car.png")));
        ship.addActionListener(e -> imgLabel.setIcon(new ImageIcon("images/ship.png")));
        airplane.addActionListener(e -> imgLabel.setIcon(new ImageIcon("images/airplane.png")));

		setSize(500,500);
		setVisible(true);

	}
	
	public static void main(String[] args) {
		new RadioButtonExTest();
	}
}
