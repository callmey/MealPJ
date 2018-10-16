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

import DBconnect.MakeConnection;

public class MealData {
		
		static PreparedStatement pstmt = null;
		static ResultSet rs = null;
		static StringBuffer sql;
		
		public static void main(String[] args) throws IOException, SQLException {
			Connection conn = MakeConnection.getConnection();
			System.out.println("conn : " + conn);
			
			File f = new File("C:\\Users\\soldesk\\Downloads\\정보기술\\정보기술(지급자료)\\정보기술(지급자료 제2과제)\\DataFiles\\meal.txt");
			//File f = new File("C:\\Users\\재원\\Documents\\정보기술(지급자료)-20181015T115308Z-001\\정보기술(지급자료)\\정보기술(지급자료 제2과제)\\DataFiles\\meal.txt");
			FileInputStream fis = new FileInputStream(f);
			
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(new InputStreamReader(fis,"UTF-8"));
			
			String s = null;
			String mealno = null;
			int cuisineno = 0;
			String mealname = null;
			int price = 0;
			int maxcount = 0;
			int todaymeal = 0;
			
			sql = new StringBuffer();
			sql.append("INSERT INTO MEAL(mealno, cuisineno, mealname, price, maxcount, todaymeal) ");
			sql.append("VALUES (mealno.nextval, ?, ?, ?, ?, ?) ");

			 
			try {
				pstmt = conn.prepareStatement(sql.toString());
				while((s=br.readLine()) != null) {
					String[] s1 = s.split("\t");
					//System.out.print(s1[0]);
					mealno = s1[0];
					if(mealno.equals("순번"))continue;
					cuisineno = Integer.parseInt(s1[1]);	
					mealname = s1[2];	
					System.out.println("이름" + mealname);
					price = Integer.parseInt(s1[3]);
					System.out.println("가격 : "+price);
					maxcount = Integer.parseInt(s1[4]);
					todaymeal = Integer.parseInt(s1[5]);
					System.out.println("오늘: "+ todaymeal);
				
				pstmt.setInt(1, cuisineno);
				pstmt.setString(2, mealname);
				pstmt.setInt(3, price);
				pstmt.setInt(4, maxcount);
				pstmt.setInt(5, todaymeal);
					
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
