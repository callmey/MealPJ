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
		super("������");
		setBounds(200, 300, 450, 370);
		setDefaultCloseOperation(EXIT_ON_CLOSE); // ����
		setLayout(null);
		
		jpl = new JPanel(new GridLayout(5, 2, 5, 3));
		jpl.setBounds(0, 10, 430, 300);
		
		jlb1 = new JLabel("�����ȣ : ");
		jlb2 = new JLabel("����� : ");
		jlb3 = new JLabel("�н����� : ");
		jlb4 = new JLabel("�н��������Է� : ");
		jtf1 = new JTextField();
		jtf2 = new JTextField();
		jpf3 = new JPasswordField();
		jpf4 = new JPasswordField();
		jbtn1 = new JButton("���");
		jbtn2 = new JButton("�ݱ�");
		
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
		
	} // ������ end
	
	public static void main(String[] args) {
		new MemResister();
		//JOptionPane.showMessageDialog(mr, "�׸� ����", "Message", JOptionPane.ERROR_MESSAGE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();
		if(obj == jbtn1) {
			if(jtf1.getText().trim().isEmpty() || jtf2.getText().trim().isEmpty() || jpf3.getPassword().equals("") ||  jpf4.getPassword().equals("")) {
				JOptionPane.showMessageDialog(null, "�׸� ����", "Message", JOptionPane.ERROR_MESSAGE);
			} else if (jpf3.getPassword().equals(jpf4.getPassword())) {
				JOptionPane.showMessageDialog(null, "�н����� Ȯ�� ���", "Message", JOptionPane.ERROR_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "����� ��ϵǾ����ϴ�.", "Message", JOptionPane.INFORMATION_MESSAGE);
			}
		} else if(obj == jbtn2) {
			System.exit(0);
		}
	}

}
