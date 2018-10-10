package project;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
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
	JPanel jp1, jp2, jp3; // Ÿ��Ʋ, ��ư, ����ð�
	JLabel jlb1, jlb2;
	Date date;
	JTabbedPane tpane;
	
	public SalesMeal() {
		// TODO Auto-generated constructor stub
		super("�ı� �߸� ���α׷�");
		setBounds(200, 300, 300, 480); // ��ü â
		setLayout(null);
		
		jp1 = new JPanel();
		jp2 = new JPanel(new GridLayout(2, 2, 0, 0));
		jp3 = new JPanel();
		jlb1 = new JLabel();
		jlb2 = new JLabel();
		
		jp1.setBounds(0, 10, 300, 20);
		jlb1.setText("�ı� �߸� ���α׷�");
		jp1.add(jlb1); 
		
		// jp1 end
		
		tpane = new JTabbedPane(JTabbedPane.TOP);
		ii1 = new ImageIcon("src/images/menu_1.png");
		ii2 = new ImageIcon("src/images/menu_2.png");
		ii3 = new ImageIcon("src/images/menu_3.png");
		ii4 = new ImageIcon("src/images/menu_4.png");
		
		jbtn1 = new JButton(ii1);
		jbtn2 = new JButton(ii2);
		jbtn3 = new JButton(ii3);
		jbtn4 = new JButton(ii4);
		
		
		jp2.add(jbtn1); jp2.add(jbtn2); jp2.add(jbtn3); jp2.add(jbtn4);
		jp2.setBounds(0, 50, 280, 350);
		
		tpane.addTab("�޴�", jp2);
		add(tpane);
		// jp2 end
		
		
		jp3.setBounds(0, 410, 300, 60);
		jp3.setBackground(Color.BLACK);
		jlb2.setForeground(Color.WHITE);
		ActionListener listener = new TimerAction();
		Timer t = new Timer(1000, listener);
		t.start();
		jp3.add(jlb2);
		// jp3 end
		add(jp1); add(jp2); add(jp3);
		
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
			jlb2.setText("����ð� : " + (date.getYear()+1900) + "��" + (date.getMonth()+1) + "��" + date.getDate() + "��"
							+ date.getHours() + "��" + date.getMinutes() +"��" + date.getSeconds() + "��");
			
		}
	}
}
