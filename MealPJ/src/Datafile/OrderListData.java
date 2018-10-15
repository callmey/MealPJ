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
		
		File f = new File("C:\\Users\\soldesk\\Downloads\\정보기술\\정보기술(지급자료)\\정보기술(지급자료 제2과제)\\DataFiles\\orderlist.txt");
		
		FileInputStream fis = new FileInputStream(f);
		
		FileReader fr = new FileReader(f);
		BufferedReader br = new BufferedReader(new InputStreamReader(fis,"UTF-8"));
		
		String s = null;
		int orderno = 0;
		int cuisineno = 0;
		int mealno = 0;
		int memberno = 0;
		int ordercount = 0;
		int amount = 0;
		String orderdate = null;
		
		sql = new StringBuffer();
		sql.append("INSERT INTO ORDERLIST(ORDERNO, CUISINENO, MEALNO, MEMBERNO. ORDERCOUNT, AMOUNT, ORDERDATE) ");
		sql.append("VALUES (orderno.nextval, ?, ?, ?, ?, ?, ?) ");
			 
		/*try {
			pstmt = conn.prepareStatement(sql.toString());
			while((s=br.readLine()) != null) {
				String[] s1 = s.split("\t");
				//System.out.print(s1[0]);
					memno = s1[0];
					if(memno.equals("사번"))continue;
					memname = s1[1];		
					pw = s1[2];
			//pstmt = conn.prepareStatement(sql1.toString());
			//rs = pstmt.executeQuery();
			//pstmt.setString(1, memno);
			pstmt.setString(1, memname);
			pstmt.setString(2, pw);
			
			System.out.print("사번" + memno);
			System.out.println();
			System.out.print("이름" + memname);
			System.out.println("이름길이" + memname.length());
			System.out.println();
			System.out.print("비밀번호" + pw);
	
			//System.out.println("번호길이" + pw.length());
			pstmt.executeUpdate();
			}*/
		/*	
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
	
}*/
