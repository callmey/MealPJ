package project;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class MemResister extends JFrame implements ActionListener {
	JButton jbtn1, jbtn2;
	GridLayout gl;
	JLabel jlb1, jlb2, jlb3, jlb4;
	JTextField jtf1, jtf2;
	JPasswordField 	jpf3, jpf4;
	JPanel jpl;

	
	public MemResister() {
		super("사원등록");
		setBounds(200, 300, 450, 370);
		setDefaultCloseOperation(EXIT_ON_CLOSE); // 종료
		setLayout(null);
		
		jpl = new JPanel(new GridLayout(5, 2, 5, 3));
		jpl.setBounds(0, 10, 430, 300);
		
		jlb1 = new JLabel("사원번호 : ");
		jlb2 = new JLabel("사원명 : ");
		jlb3 = new JLabel("패스워드 : ");
		jlb4 = new JLabel("패스워드재입력 : ");
		jtf1 = new JTextField();
		jtf2 = new JTextField();
		jpf3 = new JPasswordField();
		jpf4 = new JPasswordField();
		jbtn1 = new JButton("등록");
		jbtn2 = new JButton("닫기");
		
		jpl.add(jlb1);
		jpl.add(jtf1);
		jpl.add(jlb2);
		jpl.add(jtf2);
		jpl.add(jlb3);
		jpl.add(jpf3);
		jpl.add(jlb4);
		jpl.add(jpf4);
		jpl.add(jbtn1);
		jpl.add(jbtn2);
		
		this.add(jpl);

		jbtn1.addActionListener(this);
		jbtn2.addActionListener(this);
		setVisible(true);
		
	} // 생성자 end
	
	public static void main(String[] args) {
		new MemResister();
		//JOptionPane.showMessageDialog(mr, "항목 누락", "Message", JOptionPane.ERROR_MESSAGE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();
		if(obj == jbtn1) {
			if(jtf1.getText().trim().isEmpty() || jtf2.getText().trim().isEmpty() || jpf3.getPassword().equals("") ||  jpf4.getPassword().equals("")) {
				JOptionPane.showMessageDialog(null, "항목 누락", "Message", JOptionPane.ERROR_MESSAGE);
			} else if (jpf3.getPassword().equals(jpf4.getPassword())) {
				JOptionPane.showMessageDialog(null, "패스워드 확인 요망", "Message", JOptionPane.ERROR_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "사원이 등록되었습니다.", "Message", JOptionPane.INFORMATION_MESSAGE);
			}
		} else if(obj == jbtn2) {
			System.exit(0);
		}
	}

}
