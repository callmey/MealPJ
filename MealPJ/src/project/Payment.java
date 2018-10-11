package project;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Payment extends JFrame {
	JButton[] jbtn;
	JPanel jpl1, jpl2, jpl3;
	JLabel jlb;
	
	
	Payment() {
		super("결제");
		setBounds(200, 300, 600, 450);
		setLayout(null);
		
		jpl1 = new JPanel();
		jpl1.setBounds(0, 20, 600, 200);
		jlb = new JLabel();
		jlb.setBounds(0, 0, 100, 100);
		jlb.setText("중식");
		jpl1.add(jlb);
		this.add(jpl1);
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Payment();
	}
}
