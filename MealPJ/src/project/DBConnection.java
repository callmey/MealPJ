package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection {
	public static void main(String[] args) {
		// ���� ����
		String driver = "oracle.jdbc.driver.OrableDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		
		String user = "root";
		String password = "1234";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		// ����̹� �ε�
		try {
			Class.forName(driver);
			
		// Connection ��ü ����
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("conn : " + conn );
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("����̹� �ε� ����");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("DBMS ���� ����");
		}
		
		
		// SQL ���� �ۼ�
		
		// ���� ��ü
		
		// ����
		
		// ���ڵ庰�� ���� ó��
		
		// �ڿ� �ݳ�
	}
}
