package project;

import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class MainForm extends JFrame implements ActionListener{
	//private Main M;
	JButton jbtn1, jbtn2, jbtn3, jbtn4;
	JPanel jpl;
	
	public MainForm() {
		setBounds(200, 300, 400, 430);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		
		jpl = new JPanel(new GridLayout(4, 1, 0, 0));
		jpl.setBounds(0, 15, 400, 350);
		
		jbtn1 = new JButton("사용등록");
		jbtn2 = new JButton("사용자");
		jbtn3 = new JButton("관리자");
		jbtn4 = new JButton("종료");
		
		
		jbtn1.addActionListener(this);
		jbtn2.addActionListener(this);
		jbtn3.addActionListener(this);
		jbtn4.addActionListener(this);
		
			
		jpl.add(jbtn1);
		jpl.add(jbtn2);
		jpl.add(jbtn3);
		jpl.add(jbtn4);
		
		this.add(jpl);
		
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		new MainForm();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();
		if(obj == jbtn1) {
			//M.change("MemResister");
			this.setVisible(false);
			new MemResister();
			
		}else if(obj == jbtn2) {
			
		}else if(obj == jbtn3) {
			
		}else if(obj == jbtn4) {
			
		}
	}
}
