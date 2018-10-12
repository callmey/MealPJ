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
		
		ii1 = new ImageIcon("src/images/menu_1.png");
		ii2 = new ImageIcon("src/images/menu_2.png");
		ii3 = new ImageIcon("src/images/menu_3.png");
		ii4 = new ImageIcon("src/images/menu_4.png");
		
		Image i1 = ii1.getImage();
		Image i2 = ii2.getImage();
		Image i3 = ii3.getImage();
		Image i4 = ii4.getImage(); // �̹��� ����

		Image ci1 = i1.getScaledInstance(120, 150, Image.SCALE_SMOOTH);
		Image ci2 = i2.getScaledInstance(120, 150, Image.SCALE_SMOOTH);
		Image ci3 = i3.getScaledInstance(120, 150, Image.SCALE_SMOOTH);
		Image ci4 = i4.getScaledInstance(120, 150, Image.SCALE_SMOOTH); // �̹��� ũ�� ����
	
		
		ImageIcon icon1 = new ImageIcon(ci1);
		ImageIcon icon2 = new ImageIcon(ci2);
		ImageIcon icon3 = new ImageIcon(ci3);
		ImageIcon icon4 = new ImageIcon(ci4); // ����� �̹����� ImageIcon ��ü�� �ٽ� ����
	
		jbtn1 = new JButton(icon1);
		jbtn2 = new JButton(icon2);
		jbtn3 = new JButton(icon3);
		jbtn4 = new JButton(icon4); // ��ư�� ����� �̹��� ����
	
		
		tpane = new JTabbedPane();
		jp2.add(jbtn1); jp2.add(jbtn2); jp2.add(jbtn3); jp2.add(jbtn4);
		jp2.setBounds(0, 50, 280, 360);
		tpane.setBounds(0, 50, 280, 360);
		tpane.addTab("�޴�", jp2);
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
		add(tpane); // �� �޴��� �� ������Ʈ�� �߰�
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
			jlb2.setText("����ð� : " + (date.getYear()+1900) + "��" + (date.getMonth()+1) + "��" + date.getDate() + "��"
							+ date.getHours() + "��" + date.getMinutes() +"��" + date.getSeconds() + "��");
			
		}
	}
}
