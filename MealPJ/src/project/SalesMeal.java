package project;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.Timer;
		
public class SalesMeal extends JFrame {
	JButton jbtn1, jbtn2, jbtn3, jbtn4;
	ImageIcon ii1, ii2, ii3, ii4;
	JMenuBar jmb;
	JMenuItem jmi;
	JPanel jp1, jp2, jp3; // 타이틀, 버튼, 현재시간
	JLabel jlb1, jlb2;
	Date date;
	JTabbedPane tpane;
	
	public SalesMeal() {
		// TODO Auto-generated constructor stub
		super("식권 발매 프로그램");
		setBounds(200, 300, 300, 480); // 전체 창
		setLayout(null);
		
		jp1 = new JPanel();
		jp2 = new JPanel(new GridLayout(2, 2, 0, 0));
		jp3 = new JPanel();
		jlb1 = new JLabel();
		jlb2 = new JLabel();
		
		jp1.setBounds(0, 10, 300, 20);
		jlb1.setText("식권 발매 프로그램");
		jp1.add(jlb1); 
		
		// jp1 end
		
		ii1 = new ImageIcon("src/images/menu_1.png");
		ii2 = new ImageIcon("src/images/menu_2.png");
		ii3 = new ImageIcon("src/images/menu_3.png");
		ii4 = new ImageIcon("src/images/menu_4.png");
		
		Image i1 = ii1.getImage();
		Image i2 = ii2.getImage();
		Image i3 = ii3.getImage();
		Image i4 = ii4.getImage(); // 이미지 추출

		Image ci1 = i1.getScaledInstance(120, 150, Image.SCALE_SMOOTH);
		Image ci2 = i2.getScaledInstance(120, 150, Image.SCALE_SMOOTH);
		Image ci3 = i3.getScaledInstance(120, 150, Image.SCALE_SMOOTH);
		Image ci4 = i4.getScaledInstance(120, 150, Image.SCALE_SMOOTH); // 이미지 크기 변경
	
		
		ImageIcon icon1 = new ImageIcon(ci1);
		ImageIcon icon2 = new ImageIcon(ci2);
		ImageIcon icon3 = new ImageIcon(ci3);
		ImageIcon icon4 = new ImageIcon(ci4); // 변경된 이미지를 ImageIcon 객체에 다시 넣음
	
		jbtn1 = new JButton(icon1);
		jbtn2 = new JButton(icon2);
		jbtn3 = new JButton(icon3);
		jbtn4 = new JButton(icon4); // 버튼에 변경된 이미지 넣음
	
		
		tpane = new JTabbedPane();
		jp2.add(jbtn1); jp2.add(jbtn2); jp2.add(jbtn3); jp2.add(jbtn4);
		jp2.setBounds(0, 50, 280, 360);
		tpane.setBounds(0, 50, 280, 360);
		tpane.addTab("메뉴", jp2);
		// jp2 end
		
		
		jp3.setBounds(0, 410, 300, 60);
		jp3.setBackground(Color.BLACK);
		jlb2.setForeground(Color.WHITE);
		ActionListener listener = new TimerAction();
		Timer t = new Timer(1000, listener);
		t.start();
		jp3.add(jlb2);
		// jp3 end
		
		add(jp1); 
		add(tpane); // 탭 메뉴에 들어간 컴포넌트들 추가
		add(jp3);
		
		setVisible(true);
		
	}
	
	public static void main(String[] args) {
		new SalesMeal();
	}
	
	class TimerAction implements ActionListener {
		int n=0;

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			date = new Date();
			n++;
			jlb2.setText("현재시간 : " + (date.getYear()+1900) + "년" + (date.getMonth()+1) + "월" + date.getDate() + "일"
							+ date.getHours() + "시" + date.getMinutes() +"분" + date.getSeconds() + "초");
			
		}
	}
}
