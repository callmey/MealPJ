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
	
	DefaultTableModel dtModel;
	ArrayList<String> arr = null;
		
	int r = 0; 
	
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
		
		MenuTab(); // 테이블
		
		jlb4 = new JLabel();
		jlb4.setText("선택품명 : ");
		jlb4.setBounds(10, 250, 100, 30);
		jtf1 = new JTextField();
		jtf1.setBounds(70, 255, 300, 25);
		//jtf1.setEnabled(false);
		jtf1.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
		jtf1.setBackground(Color.LIGHT_GRAY);
		jlb5 = new JLabel();
		jlb5.setText("수량 : ");
		jlb5.setBounds(380, 250, 200, 30);
		jtf2 = new JTextField();
		jtf2.setBounds(420, 255, 100, 25);
		
		jpl3.add(jlb2); jpl3.add(jlb3); jpl3.add(jlb4); jpl3.add(jlb5);
		jpl3.add(jtf1); jpl3.add(jtf2);
		
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
		
		insert.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int getNum = 0;
				int i = 0;
				String[] str = new String[4];
				
				if (jtf2.getText().equals("0") || !(jtf2.getText().matches("^[0-9]*$")) || jtf2.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "수량을 입력해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
				} 
				else {
					// 버튼 클릭 하고 수량 입력 시 table에 추가됨.
					// 음식이름(가격) 과 일치하면 
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
					
					
							/*rowData[r][0] = arr.get(i);*/
							System.out.println("rne");
			 /*else if(clk_menuName[n].equals(arr.get(i+1))){ // 음식이름
							rowData[r][1] = arr.get(i+1);*/
						 /*else if(String.valueOf(clk_menuCount[j]).equals(arr.get(i+3))) { // 수량
							dtModel.setValueAt(rowData, r, 2);*/
						/*} else if(clk_menuPrice[n].equals(arr.get(1+2))){ //가격
							rowData[r][3] = arr.get(i+2);
							System.out.println(rowData[r][3]);
						}
						}*/
					}
					dtModel.addRow(str);
			/*			
			clk_menuNo[j] = arr.get(i);
			clk_menuName[j] = arr.get(i+1);
			clk_menuPrice[j]= arr.get(i+2);
			clk_menuCount[j] = Integer.valueOf(arr.get(i+3)); */
					
				
	
				/*//여러 컬럼을 선택하는 경우와 선택하지 않은 경우를 고려
				switch(jTbl.getRowCount()) {
				case 0: 
					JOptionPane.showMessageDialog(null, "선택된 컬럼이 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
					break;
				case 1:
					//DefaultTableModel에서 선택한 컬럼의 값들을 가져오기
					String id = String.valueOf(defaultTableModel.getValueAt(jTable.getSelectedRow(), 0));
					String name = String.valueOf(defaultTableModel.getValueAt(jTable.getSelectedRow(), 1));
					int age = Integer.valueOf((String) defaultTableModel.getValueAt(jTable.getSelectedRow(), 2));
					String addr = String.valueOf(defaultTableModel.getValueAt(jTable.getSelectedRow(), 3));
				}*/
			}
		});
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

		int i = 0;
		int j = 0;
		
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
	} // select end
	
	public void MenuTab() {
		//DefaultTableModel을 선언하고 데이터 담기
		dtModel = new DefaultTableModel(rowData,tabTitle)
		{
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		
		//JTable에 DefaultTableModel을 담기
		jTbl = new JTable(dtModel);
		
		//JScrollPane에 JTable을 담기
		// Swing에서 스크롤바를 넣기 위해 아래와 같이 사용한다.
		jsp = new JScrollPane(jTbl); // 스크롤바 만듦.
		jsp.setBounds(0,40,550,200);
		jTbl.getTableHeader().setReorderingAllowed(false); // 테이블 컬럼 순서 변경 금지.
		jpl3.add(jsp); 
		
	}
	
	
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
		}
			
		for(int num = 0; num<arrsize; num++) {
			if(selected.equals(clk_menuName[num]+"("+ clk_menuPrice[num]+")")) {
				jtf1.setText(clk_menuName[num]);
			}
		}
	
	}
}

