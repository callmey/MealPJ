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

public class MemberData {
	
	static PreparedStatement pstmt = null;
	static ResultSet rs = null;
	static StringBuffer sql;
	
	public static void main(String[] args) throws IOException, SQLException {
		Connection conn = MakeConnection.getConnection();
		System.out.println("conn : " + conn);
		
		File f = new File("C:\\Users\\soldesk\\Downloads\\�������\\�������(�����ڷ�)\\�������(�����ڷ� ��2����)\\DataFiles\\member.txt");
		
		FileInputStream fis = new FileInputStream(f);
		
		FileReader fr = new FileReader(f);
		BufferedReader br = new BufferedReader(new InputStreamReader(fis,"UTF-8"));
		
		String s = null;
		String memno = null;
		String memname = null;
		String pw = null;
		/*while((s=br.readLine()) != null) {
			String[] s1 = s.split("\t");
			//System.out.print(s1[0]);
			memno = s1[0];
			memname = s1[1];
			pw = s1[2];
			System.out.print("���" + memno);
			System.out.println();
			System.out.print("�̸� " +memname);
			System.out.println();
			System.out.print("��й�ȣ" + pw);
			System.out.println();
		} // while end	
*/		
	

		/*sql = new StringBuffer();
		sql.append("SHOW DATABASE LIKE 'meal'");
		if(rs.next() == true) {
			sql = new StringBuffer();
			sql.append("create sequence memberno start with 10000 increment by 0");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.executeUpdate();
		}*/
		
		
		sql = new StringBuffer();
		sql.append("INSERT INTO MEMBER(MEMBERNO, MEMBERNAME, PASSWD) ");
		sql.append("VALUES (memberno.nextval, ?, ?) ");
		
		pstmt = conn.prepareStatement(sql.toString());

		 
		try {
			pstmt = conn.prepareStatement(sql.toString());
			while((s=br.readLine()) != null) {
				String[] s1 = s.split("\t");
				//System.out.print(s1[0]);
					memno = s1[0];
					if(memno.equals("���"))continue;
					memname = s1[1];		
					pw = s1[2];
			//pstmt = conn.prepareStatement(sql1.toString());
			//rs = pstmt.executeQuery();
			//pstmt.setString(1, memno);
			pstmt.setString(1, memname);
			pstmt.setString(2, pw);
			
			System.out.print("���" + memno);
			System.out.println();
			System.out.print("�̸�" + memname);
			System.out.println("�̸�����" + memname.length());
			System.out.println();
			System.out.print("��й�ȣ" + pw);
	
			//System.out.println("��ȣ����" + pw.length());
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
		
}// class end
