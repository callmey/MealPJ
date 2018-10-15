package Datafile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import project.MakeConnection;

public class OrderListData {
	
	static PreparedStatement pstmt = null;
	static ResultSet rs = null;
	static StringBuffer sql;
	
	public static void main(String[] args) throws IOException, SQLException {
		Connection conn = MakeConnection.getConnection();
		System.out.println("conn : " + conn);
		
		//File f = new File("C:\\Users\\soldesk\\Downloads\\정보기술\\정보기술(지급자료)\\정보기술(지급자료 제2과제)\\DataFiles\\orderlist.txt");
		
		File f = new File("C:\\Users\\재원\\Documents\\정보기술(지급자료)-20181015T115308Z-001\\정보기술(지급자료)\\정보기술(지급자료 제2과제)\\DataFiles\\orderlist.txt");
		
		FileInputStream fis = new FileInputStream(f);
		
		FileReader fr = new FileReader(f);
		BufferedReader br = new BufferedReader(new InputStreamReader(fis,"UTF-8"));
		
		String s = null;
		String orderno = null;
		int cuisineno = 0;
		int mealno = 0;
		int memberno = 0;
		int ordercount = 0;
		int amount = 0;
		String orderdate = null;
		
		sql = new StringBuffer();
		sql.append("INSERT INTO ORDERLIST(ORDERNO, CUISINENO, MEALNO, MEMBERNO, ORDERCOUNT, AMOUNT, ORDERDATE) ");
		sql.append("VALUES (orderno.nextval, ?, ?, ?, ?, ?, ?) ");
			 
		try {
			pstmt = conn.prepareStatement(sql.toString());
			while((s=br.readLine()) != null) {
				String[] s1 = s.split("\t");
				orderno = s1[0];
					if(orderno.equals("순번"))continue;
				cuisineno = Integer.parseInt(s1[1]);		
				mealno = Integer.parseInt(s1[2]);
				memberno = Integer.parseInt(s1[3]);
				ordercount = Integer.parseInt(s1[4]);
				amount = Integer.parseInt(s1[5]);
				orderdate = s1[6];
	
				pstmt.setInt(1, cuisineno);
				pstmt.setInt(2, mealno);
				pstmt.setInt(3, memberno);
				pstmt.setInt(4, ordercount);
				pstmt.setInt(5, amount);
				pstmt.setString(6, orderdate);
			
				
				System.out.print("음식종류" + cuisineno);
				System.out.println();
				System.out.print("음식번호" + mealno);
				System.out.println("사번" + memberno);
				System.out.println();
				System.out.print("주문횟수" + ordercount);
				System.out.println("결제금액" + amount);
				System.out.println("주문 일자 : " + orderdate);
	
			pstmt.executeUpdate();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();		
			
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
