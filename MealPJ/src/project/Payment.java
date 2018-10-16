package project;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import DBconnect.GetMenu;
import DBconnect.MakeConnection;

public class Payment extends JFrame implements ActionListener{
	ArrayList<JButton> jGridBtn;
	JButton[] btn;
	JButton jbtn1, jbtn2, jbtn3, jbtn4, jbtn5, jbtn6, jbtn7;
	JButton insert, pay, exit;
	JPanel jpl1, jpl2, jpl3;
	JLabel jlb, jlb2, jlb3, jlb4, jlb5;
	JTextField jtf1, jtf2;
	JTable jTbl;
	JScrollPane jsp;
	
	private int i = 0;
	private int j = 0;
	
	ArrayList<String> arr = null;
	
	String query;
	private int arrsize;
	static Connection conn;
	
	static PreparedStatement pstmt = null;
	static ResultSet rs = null;
	static StringBuffer sql;
	
	public Payment(int cuisineno) {
		super("결제");
		setBounds(200, 300, 1200, 500);
		setLayout(null);
		
		conn = MakeConnection.getConnection();
		
		jpl1 = new JPanel();
		jpl1.setBounds(250, 20, 600, 50);
		jlb = new JLabel();
		jlb.setText("중식");
		jlb.setFont(jlb.getFont().deriveFont(20.0f)); // 폰트 크기 변경
		jpl1.add(jlb);
		this.add(jpl1);
		
		JButton[] jbtn = {jbtn1, jbtn2, jbtn3, jbtn4, jbtn5, jbtn6, jbtn7};
		
		jpl2 = new JPanel();
		jpl2.setBounds(10, 100, 550, 300);
	
		select(cuisineno);
		
		/*for(int i =0; i< jbtn.length; i++) {
			jbtn[i] = new JButton();
			//jbtn[i].setBounds(0,0,50,50);
		}
		
		jbtn[0].setText("해물짜장1 (5000)");
		jbtn[1].setText("쟁반짜장2 (5500)");
		jbtn[2].setText("쟁반짜장3 (5500)");
		jbtn[3].setText("쟁반짜장4 (5500)");
		jbtn[4].setText("쟁반짜장5 (5500)");
		jbtn[5].setText("쟁반짜장6 (5500)");
		jbtn[6].setText("쟁반짜장6 (5500)");
		
		for(int i =0; i<jbtn.length; i++) {
			jpl2.add(jbtn[i]);
		}*/
		
		
		
		jpl3 = new JPanel();
		jpl3.setLayout(null);
		jpl3.setBounds(600, 100, 600, 500);
		jlb2 = new JLabel();
		jlb3 = new JLabel();
		
		jlb2.setText("총결제금액");
		jlb2.setFont(jlb2.getFont().deriveFont(16.0f));
		jlb2.setBounds(10, 0, 100, 30);
		jlb3.setText(" 원");
		jlb3.setBounds(500, 0, 100, 30);
		jlb3.setFont(jlb3.getFont().deriveFont(16.0f));
		jlb4 = new JLabel();
		jlb4.setText("선택품명 : ");
		jlb4.setBounds(10, 250, 100, 30);
		jtf1 = new JTextField();
		jtf1.setBounds(70, 255, 300, 25);
		jtf1.setEnabled(false);
		jlb5 = new JLabel();
		jlb5.setText("수량 : ");
		jlb5.setBounds(380, 250, 200, 30);
		jtf2 = new JTextField();
		jtf2.setBounds(420, 255, 100, 25);
		
		jpl3.add(jlb2); jpl3.add(jlb3); jpl3.add(jlb4); jpl3.add(jlb5);
		jpl3.add(jtf1); jpl3.add(jtf2);
		String columnNames[] = 
			{"상품번호", "품명", "수량", "금액"};
		
		// DB 데이터 들어갈 부분
		Object rowData[][] =
			{{1, "짜장면", 2, 5500},
			{2, "쟁반짜장", 1, 5000}};
		
		jTbl = new JTable(rowData, columnNames);
		jsp = new JScrollPane(jTbl);
		jsp.setBounds(0,40,550,200);
		jpl3.add(jsp); 
		
		insert = new JButton("입력");
		insert.setBounds(120, 290, 100, 30);
		pay = new JButton("결제");
		pay.setBounds(225, 290, 100, 30);
		exit = new JButton("닫기");
		exit.setBounds(330, 290, 100, 30);
		insert.addActionListener(this);
		exit.addActionListener(this);
		
		jpl3.add(insert); jpl3.add(pay); jpl3.add(exit); 
		add(jpl2); add(jpl3);
		setVisible(true);
	}
	
	public void select(int cuisineno) {
		arr = new GetMenu().menuButton(cuisineno);
		arrsize = arr.size()/4; // 한 줄씩 분류하기.
		int rownum = 0;

		if((arrsize%5)==0) {
			rownum = arrsize/5;
		}else if((arrsize%5)==1) {
			rownum = ((arrsize)/5)+1;
		}
		
		jpl2.setLayout(new GridLayout(0,5));
		
		btn = new JButton[arrsize];
		
		while(i<arr.size()) {
			String text = arr.get(i+1)+"("+arr.get(i+2)+ ")"; // 줄에서 2번째 음식이름, 줄에서 3번째 가격 정보 
			btn[j] = new JButton(text);
			btn[j].setSize(50, 50);
			btn[j].setHorizontalTextPosition(JButton.CENTER); // 텍스트 수평 가운데 정렬
			btn[j].setVerticalTextPosition(JButton.CENTER); // 텍스트 수직 가운데 정렬
			
			jpl2.add(btn[j]);
			j++;
			i+=4;
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
		new Payment(2);	
		System.out.println("conn : " + conn);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		System.out.println(obj);
		if(obj == exit) {
			System.exit(0);
			System.out.println("종료");
		}else if(obj == insert) {
			if(jtf2.getText().equals("0") || !(jtf2.getText().matches("^[0-9]*$")) || jtf2.getText().trim().isEmpty()){
				JOptionPane.showMessageDialog(null, "수량을 입력해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
		}else if (obj == pay) {
			/*if(jtbl. == null) {
				JOptionPane.showMessageDialog(null, "메뉴를 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
			}*/
		}
	}
	}
}

