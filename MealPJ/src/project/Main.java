/*package project;

import javax.swing.JFrame;

public class Main extends JFrame{
	public MainForm mf = null;
	public MemResister mr = null;
	
	public void change(String panelName) {
		if(panelName.equals("mainForm")) {
			getContentPane().removeAll();
			getContentPane().add(mf);
			revalidate();
			repaint();
		}else {
			getContentPane().removeAll();
			getContentPane().add(mr);
			revalidate();
			repaint();
		}
	}
	
	public static void main(String[] args) {
		Main m = new Main();
		
		m.setTitle("MainTest");
		m.mf = new MainForm(m);
		m.mr = new MemResister(m);
		
		m.add(m.mf);
		m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		m.setBounds(200, 300, 300, 350);
		m.setVisible(true);
	
	}

	

}
*/