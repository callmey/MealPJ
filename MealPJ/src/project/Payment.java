package project;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import DBconnect.GetMenu;
import DBconnect.MakeConnection;

public class Payment extends JFrame implements ActionListener{
	
	JButton[] btn;
	JButton jbtn1, jbtn2, jbtn3, jbtn4, jbtn5, jbtn6, jbtn7;
	JButton insert, pay, exit;
	JPanel jpl1, jpl2, jpl3;
	JLabel jlb, jlb2, jlb3, jlb4, jlb5, won;
	JTextField jtf1, jtf2;
	JTable jTbl;
	JScrollPane jsp;
	ActionListener listener = null;
	private String[] tabTitle = {"��ǰ��ȣ", "ǰ��", "����", "�ݾ�"};
	private Object[][] rowData = {};
	private int sum;

	DefaultTableModel dtModel;
	ArrayList<String> arr = null;
		
	int r = 0; 
	
	String query;
	private int arrsize = 0;
	private int i = 0;
	private int j = 0;
	private int getNum=0;
	
	private String[] clk_menuNo;
	private int[] clk_menuCount;
	private String[] clk_menuName;
	private String[] clk_menuPrice;

	static Connection conn;
	
	static PreparedStatement pstmt = null;
	static ResultSet rs = null;
	static StringBuffer sql;
	
	public Payment(int cuisineno) {
		super("����");
		setBounds(200, 300, 1200, 500);
		setLayout(null);
		
		conn = MakeConnection.getConnection();
		
		jpl1 = new JPanel();
		jpl1.setBounds(250, 20, 600, 50);
		jlb = new JLabel();
		jlb.setFont(jlb.getFont().deriveFont(20.0f)); // ��Ʈ ũ�� ����
		
		switch(cuisineno) {
		case 1 :
			jlb.setText("�ѽ�");
			break;
		case 2 :
			jlb.setText("�߽�");
			break;
		case 3 : 
			jlb.setText("�Ͻ�");
			break;
		case 4 :
			jlb.setText("���");
			break;
		}
		
		jpl1.add(jlb);
		this.add(jpl1);
			
		jpl2 = new JPanel();
		jpl2.setBounds(10, 100, 550, 300);
	
		select(cuisineno);
			
		
		
		/* --------jp3--------------*/
		
		jpl3 = new JPanel();
		jpl3.setLayout(null);
		jpl3.setBounds(600, 100, 600, 500);
		jlb2 = new JLabel();
		won = new JLabel();
		jlb3 = new JLabel();
		
		jlb2.setText("�Ѱ����ݾ�");
		jlb2.setFont(jlb2.getFont().deriveFont(16.0f));
		jlb2.setBounds(10, 0, 100, 30);
		won.setBounds(400, 0, 100, 30);
		won.setText(null);
		won.setFont(jlb2.getFont().deriveFont(16.0f));
		jlb3.setText(" ��");
		jlb3.setBounds(500, 0, 100, 30);
		jlb3.setFont(jlb3.getFont().deriveFont(16.0f));
		
		MenuTab(); // ���̺�
		
		jlb4 = new JLabel();
		jlb4.setText("����ǰ�� : ");
		jlb4.setBounds(10, 250, 100, 30);
		jtf1 = new JTextField();
		jtf1.setBounds(70, 255, 300, 25);
		//jtf1.setEnabled(false);
		jtf1.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
		jtf1.setBackground(Color.LIGHT_GRAY);
		jlb5 = new JLabel();
		jlb5.setText("���� : ");
		jlb5.setBounds(380, 250, 200, 30);
		jtf2 = new JTextField();
		jtf2.setBounds(420, 255, 100, 25);
		
		jpl3.add(jlb2); jpl3.add(won); jpl3.add(jlb3); jpl3.add(jlb4); jpl3.add(jlb5);
		jpl3.add(jtf1); jpl3.add(jtf2);
		
		insert = new JButton("�Է�");
		insert.setBounds(120, 290, 100, 30);
		pay = new JButton("����");
		pay.setBounds(225, 290, 100, 30);
		exit = new JButton("�ݱ�");
		exit.setBounds(330, 290, 100, 30);
		insert.addActionListener(this);
		pay.addActionListener(this);
		exit.addActionListener(this);
		
		jpl3.add(insert); jpl3.add(pay); jpl3.add(exit); 
		add(jpl2); add(jpl3);
		setVisible(true);
		
		insert.addActionListener(new ActionListener() {		
		
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int i = 0;
				String[] str = new String[4];
				
				if (jtf2.getText().equals("0") || !(jtf2.getText().matches("^[0-9]*$")) || jtf2.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "������ �Է����ּ���.", "Message", JOptionPane.ERROR_MESSAGE);
				} 
				else {
					// textfield�� ���� ��ġ�ϴ� ��ư�� ã�Ƽ� �ε����� �����ϰ�
					// ������ �ε����� �������� String �迭�� ���� �� �� �� �� ���̺� �߰�
					while(i<arrsize) {
						if(jtf1.getText().equals(clk_menuName[i])) {
							getNum = i;
							break;
						}
						i++;
					}			
					
					str[0] = clk_menuNo[getNum];
					str[1] = clk_menuName[getNum];
					str[2] = jtf2.getText();
					str[3] = clk_menuPrice[getNum];
					
					
					dtModel.addRow(str);
					btn[getNum].setEnabled(false); // ���̺� �߰� �� �޴� ��Ȱ��ȭ
					sum += Integer.parseInt(str[3]) * Integer.parseInt(jtf2.getText());
					won.setText(String.valueOf(sum));
				}
			}
		});
	}
	
	public void getRowAt(int row) {
		String[] result = new String[4];
		
		for (int i =0; i<4; i++) {
			result[i] = String.valueOf(dtModel.getValueAt(row, 4));
		}
	 //return result;
	}
	
	public void select(int cuisineno) {
		arr = new GetMenu().menuButton(cuisineno); // String ArrayList�� ���������� ��� �������� �ִ´�.
		arrsize = arr.size()/4; // �� �پ� �з��ϱ�.
		int rownum = 0; // �� �� �� ��ġ�ϱ� ���� ����

		if((arrsize%5)==0) { // 5ĭ��
			rownum = arrsize/5;
		}else if((arrsize%5)==1) { // 5ĭ�� �ϰ� �� �� ������ ���� �� �ؼ� �����ֱ�.
			rownum = ((arrsize)/5)+1;
		}
		
		jpl2.setLayout(new GridLayout(0,5)); // jp2 �׸��� �г� ���̾ƿ�.
		
		btn = new JButton[arrsize]; // �� ���� ��� ��ư �迭 ũ�� ����.
		clk_menuCount = new int[arrsize];
		clk_menuName = new String[arrsize];
		clk_menuNo = new String[arrsize];
		clk_menuPrice = new String[arrsize];
		
		while(i<arr.size()) {
			String text = arr.get(i+1)+"("+arr.get(i+2)+ ")"; // �ٿ��� 2��° �����̸�, �ٿ��� 3��° ���� ���� 
			btn[j] = new JButton(text); // �� �� �� ��ư �ϳ� ����.
			btn[j].setSize(50, 50); // ��ư ũ�� ����
			btn[j].setHorizontalTextPosition(JButton.CENTER); // �ؽ�Ʈ ���� ��� ����
			btn[j].setVerticalTextPosition(JButton.CENTER); // �ؽ�Ʈ ���� ��� ����
			
			clk_menuNo[j] = arr.get(i);
			clk_menuName[j] = arr.get(i+1);
			clk_menuPrice[j]= arr.get(i+2);
			clk_menuCount[j] = Integer.valueOf(arr.get(i+3));
			
			jpl2.add(btn[j]); // �гο� ��ư �ϳ��� �߰�
			btn[j].addActionListener(this);
			j++; 
			i+=4; // �� �پ� �ݺ�
		}
	} // select end
	
	public void MenuTab() {
		//DefaultTableModel�� �����ϰ� ������ ���
		dtModel = new DefaultTableModel(rowData,tabTitle)
		{
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		
		//JTable�� DefaultTableModel�� ���
		jTbl = new JTable(dtModel);
		
		//JScrollPane�� JTable�� ���
		// Swing���� ��ũ�ѹٸ� �ֱ� ���� �Ʒ��� ���� ����Ѵ�.
		jsp = new JScrollPane(jTbl); // ��ũ�ѹ� ����.
		jsp.setBounds(0,40,550,200);
		jTbl.getTableHeader().setReorderingAllowed(false); // ���̺� �÷� ���� ���� ����.
		jpl3.add(jsp); 
		
		jTbl.addMouseListener(new MouseAdapter() {
			
			private int num = 0;

			@Override
			public void mouseClicked(MouseEvent e) {
				int point=jTbl.rowAtPoint(e.getPoint()); // ���� ���� 
				System.out.println(point);
				
				if(e.getClickCount() == 2) { // Ŭ�� �ι� ���� ��
					String str = (String)jTbl.getValueAt(point, 1); // �� ���� ���� ��
					String q =  (String)jTbl.getValueAt(point, 2);
					String price = (String)jTbl.getValueAt(point, 3);
					
					while(i<arrsize) {
						if(clk_menuName[i].equals(str)) {
							getNum = num;
							break;
						}
						i++;
					}
					
					btn[getNum].setEnabled(true); // ��ư �ٽ� Ȱ��ȭ
					dtModel.removeRow(point);
					sum -= Integer.parseInt(q)*Integer.parseInt(price); // �� �հ� ������ �� ����
					won.setText(String.valueOf(sum));
				}
			}
		});
	}
	public static void main(String[] args) {
		new Payment(2);	
		System.out.println("conn : " + conn);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		Object source = e.getActionCommand(); // �� ��ü�� ���Ե� String�� ������.
		String selected = source.toString();
		
		if(obj == exit) {
			System.exit(0);
			System.out.println("����");
		} else if (obj == pay) {
			System.out.println(pay);
			//���� �÷��� �����ϴ� ���� �������� ���� ��츦 ���
			switch(jTbl.getRowCount()) {	
				case 0:
					JOptionPane.showMessageDialog(null, "�޴��� �������ּ���.", "Message", JOptionPane.ERROR_MESSAGE);
					//System.out.println(jTbl.getSelectedRowCount());
					break;
				default : 
					System.out.println("good");
			}
		}
		for(int num = 0; num<arrsize; num++) {
			if(selected.equals(clk_menuName[num]+"("+ clk_menuPrice[num]+")")) {
				jtf1.setText(clk_menuName[num]);
			}
		}
	
	}
	
	
}

