package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection {
	public static void main(String[] args) {
		// 변수 설정
		String driver = "oracle.jdbc.driver.OrableDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		
		String user = "root";
		String password = "1234";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		// 드라이버 로딩
		try {
			Class.forName(driver);
			
		// Connection 객체 생성
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("conn : " + conn );
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("드라이버 로딩 실패");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("DBMS 연결 실패");
		}
		
		
		// SQL 문장 작성
		
		// 문장 객체
		
		// 실행
		
		// 레코드별로 로직 처리
		
		// 자원 반납
	}
}
