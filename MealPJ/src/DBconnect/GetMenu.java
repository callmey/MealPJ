package DBconnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DBconnect.MakeConnection;

public class GetMenu {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	
	private String sql = null;
	private ResultSet rs = null;
	
	private ArrayList<String> menu = null;

	public GetMenu() {
		
		//����, ���ĸ�, ����, �������ɼ��� ��������
		//���� 1 �ѽ�
		//   2 �߽�
		//   3 �Ͻ�
		//   4 ���
		//������ �޴� 1�� �� (����)
		
		conn = MakeConnection.getConnection();
	}
	
	public ArrayList<String> menuButton(int cuisineno) {
		menu = new ArrayList<String>();
		sql = "SELECT * FROM MEAL WHERE cuisineno = " + cuisineno;
			try {
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery(); // while ������ �������;���
				while(rs.next()) {
				int mealno = rs.getInt("mealno");
				String mealname = rs.getString("mealname");
				int price = rs.getInt("price");
				int maxcount = rs.getInt("maxcount");
				int todaymeal = rs.getInt("todaymeal");
				System.out.println(price);
				if(maxcount > 1 && todaymeal == 1) {		
					menu.add(String.valueOf(mealno));
					menu.add(mealname);
					menu.add(String.valueOf(price));
					menu.add(String.valueOf(maxcount));
				}
				}
					//jGridBtn.add(new JButton(mealname + "(" + price + ")"));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return menu;
			
			/*while(rs.next()) {
				pstmt.setInt(1, cuisineno);
				rs = pstmt.executeQuery();
				int mealno = rs.getInt("mealno");
				String mealname = rs.getString("mealname");
				int price = rs.getInt("price");
				int maxcount = rs.getInt("maxcount");
				int todaymeal = rs.getInt("todaymeal");
				System.out.println(price);
				if(maxcount > 1 && todaymeal == 1) {
					System.out.println("���ǰ��ɼ���" + maxcount);
					System.out.println("���ǿ���" + todaymeal);
					System.out.println("���������̸�" + mealname);	
					menu.add(String.valueOf(mealno));
					menu.add(mealname);
					menu.add(String.valueOf(price));
					menu.add(String.valueOf(maxcount));
					//jGridBtn.add(new JButton(mealname + "(" + price + ")"));
				}
					*/
			//} // while end
			/*for(int i =0; i <jGridBtn.size(); i++) {
				System.out.println(jGridBtn.get(i));
				jGridBtn.get(i).setBounds(0,0,50,50);
				jpl2.add(jGridBtn.get(i));	
			}*/
			/*} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
	} 
	
}
