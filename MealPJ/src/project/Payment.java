package project;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class Payment extends JFrame implements ActionListener{
	JButton[] jGridBtn;
	JButton jbtn1, jbtn2, jbtn3, jbtn4, jbtn5, jbtn6, jbtn7;
	JButton insert, pay, exit;
	JPanel jpl1, jpl2, jpl3;
	JLabel jlb, jlb2, jlb3, jlb4, jlb5;
	JTextField jtf1, jtf2;
	JTable jTbl;
	JScrollPane jsp;
	
	String query;
	static Connection conn;
	
	static PreparedStatement pstmt = null;
	static ResultSet rs = null;
	static StringBuffer sql;
	
	Payment() {
		super("����");
		setBounds(200, 300, 1200, 500);
		setLayout(null);
		
		conn = MakeConnection.getConnection();
		
		jpl1 = new JPanel();
		jpl1.setBounds(250, 20, 600, 50);
		jlb = new JLabel();
		jlb.setText("�߽�");
		jlb.setFont(jlb.getFont().deriveFont(20.0f)); // ��Ʈ ũ�� ����
		jpl1.add(jlb);
		this.add(jpl1);
		
		JButton[] jbtn = {jbtn1, jbtn2, jbtn3, jbtn4, jbtn5, jbtn6, jbtn7};
		
		jpl2 = new JPanel();
		jpl2.setBounds(10, 100, 550, 300);
		jpl2.setLayout(new GridLayout(0,5));
		
		//select();
		
		/*for(int i =0; i< jbtn.length; i++) {
			jbtn[i] = new JButton();
			//jbtn[i].setBounds(0,0,50,50);
		}
		
		jbtn[0].setText("�ع�¥��1 (5000)");
		jbtn[1].setText("���¥��2 (5500)");
		jbtn[2].setText("���¥��3 (5500)");
		jbtn[3].setText("���¥��4 (5500)");
		jbtn[4].setText("���¥��5 (5500)");
		jbtn[5].setText("���¥��6 (5500)");
		jbtn[6].setText("���¥��6 (5500)");
		
		for(int i =0; i<jbtn.length; i++) {
			jpl2.add(jbtn[i]);
		}*/
		
		
		
		jpl3 = new JPanel();
		jpl3.setLayout(null);
		jpl3.setBounds(600, 100, 600, 500);
		jlb2 = new JLabel();
		jlb3 = new JLabel();
		
		jlb2.setText("�Ѱ����ݾ�");
		jlb2.setFont(jlb2.getFont().deriveFont(16.0f));
		jlb2.setBounds(10, 0, 100, 30);
		jlb3.setText(" ��");
		jlb3.setBounds(500, 0, 100, 30);
		jlb3.setFont(jlb3.getFont().deriveFont(16.0f));
		jlb4 = new JLabel();
		jlb4.setText("����ǰ�� : ");
		jlb4.setBounds(10, 250, 100, 30);
		jtf1 = new JTextField();
		jtf1.setBounds(70, 255, 300, 25);
		jtf1.setEnabled(false);
		jlb5 = new JLabel();
		jlb5.setText("���� : ");
		jlb5.setBounds(380, 250, 200, 30);
		jtf2 = new JTextField();
		jtf2.setBounds(420, 255, 100, 25);
		
		jpl3.add(jlb2); jpl3.add(jlb3); jpl3.add(jlb4); jpl3.add(jlb5);
		jpl3.add(jtf1); jpl3.add(jtf2);
		String columnNames[] = 
			{"��ǰ��ȣ", "ǰ��", "����", "�ݾ�"};
		
		// DB ������ �� �κ�
		Object rowData[][] =
			{{1, "¥���", 2, 5500},
			{2, "���¥��", 1, 5000}};
		
		jTbl = new JTable(rowData, columnNames);
		jsp = new JScrollPane(jTbl);
		jsp.setBounds(0,40,550,200);
		jpl3.add(jsp); 
		
		insert = new JButton("�Է�");
		insert.setBounds(120, 290, 100, 30);
		pay = new JButton("����");
		pay.setBounds(225, 290, 100, 30);
		exit = new JButton("�ݱ�");
		exit.setBounds(330, 290, 100, 30);
		insert.addActionListener(this);
		exit.addActionListener(this);
		
		jpl3.add(insert); jpl3.add(pay); jpl3.add(exit); 
		add(jpl2); add(jpl3);
		setVisible(true);
	}
	
	private void select() {
		query = "SELECT * FROM MEAL";
		int count = 0;
		
		try {
			pstmt = conn.prepareStatement(query);
			while(rs.next()) {
				rs = pstmt.executeQuery();
				int maxcount = rs.getInt("maxcount");
				int todaymeal = rs.getInt("todaymeal");
				String mealname = rs.getString("mealname");
				int price = rs.getInt("price");
				System.out.println(price);
				if(maxcount > 1 && todaymeal == 1) {
					System.out.println("���ǰ��ɼ���" + maxcount);
					System.out.println("���ǿ���" + todaymeal);
					System.out.println("���������̸�" + mealname);
					count++;	
				}
				jGridBtn = new JButton[count];
				for(int i =0; i < count; i++) {
				jGridBtn[i].setText(mealname + "(" + price + ")");
				jGridBtn[i].setBounds(0,0,50,50);
				jpl2.add(jGridBtn[i]);	
				}
					
			} // while end
			 
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
	} // select end 
	
	/*private void insert() {	
		query = "SELECT * FROM MEAL";
		try {
			pstmt = conn.prepareStatement(query);
			while(rs.next()) {
				rs = pstmt.executeQuery();
				int maxcount = rs.getInt("maxcount");
				int todaymeal = rs.getInt("todaymeal");
				String mealname = rs.getString("mealname");
				int price = rs.getInt("price");
				System.out.println(price);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}*/
	public static void main(String[] args) {
		new Payment();	
		System.out.println("conn : " + conn);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		System.out.println(obj);
		if(obj == exit) {
			System.exit(0);
			System.out.println("����");
		}else if(obj == insert) {
			if(jtf2.getText().equals("0") || !(jtf2.getText().matches("^[0-9]*$")) || jtf2.getText().trim().isEmpty()){
				JOptionPane.showMessageDialog(null, "������ �Է����ּ���.", "Message", JOptionPane.ERROR_MESSAGE);
		}else if (obj == pay) {
			/*if(jtbl. == null) {
				JOptionPane.showMessageDialog(null, "�޴��� �������ּ���.", "Message", JOptionPane.ERROR_MESSAGE);
			}*/
		}
	}
	}
}

