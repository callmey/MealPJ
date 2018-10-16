package project;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.table.DefaultTableModel;

import DBconnect.GetMenu;
import DBconnect.MakeConnection;

public class Payment extends JFrame implements ActionListener{
	
	JButton[] btn;
	JButton jbtn1, jbtn2, jbtn3, jbtn4, jbtn5, jbtn6, jbtn7;
	JButton insert, pay, exit;
	JPanel jpl1, jpl2, jpl3;
	JLabel jlb, jlb2, jlb3, jlb4, jlb5;
	JTextField jtf1, jtf2;
	JTable jTbl;
	JScrollPane jsp;
	ActionListener listener = null;
	private String[] tabTitle = {"상품번호", "품명", "수량", "금액"};
	private Object[][] rowData = {};
	
	private int i = 0;
	private int j = 0;
	
	DefaultTableModel dtModel;
	ArrayList<String> arr = null;
	
	String query;
	private int arrsize;
	private String[] clk_menuNo;
	private int[] clk_menuCount;
	private String[] clk_menuName;
	private String[] clk_menuPrice;

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
		jlb.setFont(jlb.getFont().deriveFont(20.0f)); // 폰트 크기 변경
		
		switch(cuisineno) {
		case 1 :
			jlb.setText("한식");
			break;
		case 2 :
			jlb.setText("중식");
			break;
		case 3 : 
			jlb.setText("일식");
			break;
		case 4 :
			jlb.setText("양식");
			break;
		}
		
		jpl1.add(jlb);
		this.add(jpl1);
		
		JButton[] jbtn = {jbtn1, jbtn2, jbtn3, jbtn4, jbtn5, jbtn6, jbtn7};
		
		jpl2 = new JPanel();
		jpl2.setBounds(10, 100, 550, 300);
	
		select(cuisineno);
			
		/* --------jp3--------------*/
		
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
		//jtf1.setEnabled(false);
		jtf1.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
		jlb5 = new JLabel();
		jlb5.setText("수량 : ");
		jlb5.setBounds(380, 250, 200, 30);
		jtf2 = new JTextField();
		jtf2.setBounds(420, 255, 100, 25);
		
		jpl3.add(jlb2); jpl3.add(jlb3); jpl3.add(jlb4); jpl3.add(jlb5);
		jpl3.add(jtf1); jpl3.add(jtf2);
		
		//DefaultTableModel을 선언하고 데이터 담기
		dtModel = new DefaultTableModel(rowData,tabTitle);
		{
			
		}
		
		//JTable에 DefaultTableModel을 담기
		jTbl = new JTable(dtModel);
		
		//JScrollPane에 JTable을 담기
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
		arr = new GetMenu().menuButton(cuisineno); // String ArrayList에 음식종류별 모든 정보들을 넣는다.
		arrsize = arr.size()/4; // 한 줄씩 분류하기.
		int rownum = 0; // 한 줄 씩 배치하기 위한 변수

		if((arrsize%5)==0) { // 5칸씩
			rownum = arrsize/5;
		}else if((arrsize%5)==1) { // 5칸씩 하고 몇 개 남으면 한줄 더 해서 보여주기.
			rownum = ((arrsize)/5)+1;
		}
		
		jpl2.setLayout(new GridLayout(0,5)); // jp2 그리드 패널 레이아웃.
		
		btn = new JButton[arrsize]; // 줄 개수 대로 버튼 배열 크기 생성.
		clk_menuCount = new int[arrsize];
		clk_menuName = new String[arrsize];
		clk_menuNo = new String[arrsize];
		clk_menuPrice = new String[arrsize];

		
		while(i<arr.size()) {
			String text = arr.get(i+1)+"("+arr.get(i+2)+ ")"; // 줄에서 2번째 음식이름, 줄에서 3번째 가격 정보 
			btn[j] = new JButton(text); // 한 줄 당 버튼 하나 생성.
			btn[j].setSize(50, 50); // 버튼 크기 설정
			btn[j].setHorizontalTextPosition(JButton.CENTER); // 텍스트 수평 가운데 정렬
			btn[j].setVerticalTextPosition(JButton.CENTER); // 텍스트 수직 가운데 정렬
			
			clk_menuNo[j] = arr.get(i);
			clk_menuName[j] = arr.get(i+1);
			clk_menuPrice[j]= arr.get(i+2);
			clk_menuCount[j] = Integer.valueOf(arr.get(i+3));
			
			jpl2.add(btn[j]); // 패널에 버튼 하나씩 추가
			btn[j].addActionListener(this);
			j++; 
			i+=4; // 한 줄씩 반복
		}
		
		
	/*	listener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Object obj = e.getSource();
				System.out.println(obj);
				
				
					System.out.println(obj);
				//while(i<arr.size()) {
					if(obj == btn[j]) {
						System.out.println(obj.toString());
					if(obj.equals(clk_menuName[i]+"("+clk_menuPrice[i]+")")) {
						jtf1.setText(clk_menuName[i]);
						i++;
					}
				}
				while(j<arrsize) {
					btn[j].addActionListener(this);
					j++;
					System.out.println("???");
				}
			} //action end
			
		};*/
	} // select end
	
	public static void main(String[] args) {
		new Payment(2);	
		System.out.println("conn : " + conn);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		Object source = e.getActionCommand(); // 이 객체에 포함된 String을 가져옴.
		System.out.println(source);
		String selected = source.toString();
		System.out.println(selected);
		if(obj == exit) {
			System.exit(0);
			System.out.println("종료");
		}else if(obj == insert) {
			if(jtf2.getText().equals("0") || !(jtf2.getText().matches("^[0-9]*$")) || jtf2.getText().trim().isEmpty()){
			JOptionPane.showMessageDialog(null, "수량을 입력해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
			}
		}/*else if (obj == pay) {
			if(jtbl. == null) {
				JOptionPane.showMessageDialog(null, "메뉴를 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
			}
		} */
			
		for(int num = 0; num<arrsize; num++) {
			if(selected.equals(clk_menuName[num]+"("+ clk_menuPrice[num]+")")) {
				jtf1.setText(clk_menuName[num]);
			}
		}
	
	}
}

