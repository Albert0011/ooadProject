package views;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class WorkerHomepage extends JFrame {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton taskBtn;
	private JButton profileBtn;
	private JPanel menuPanel;
	private JPanel mainPanel;
	private JPanel titlePanel;
	private JButton notifBtn;
	private JButton logoutBtn;

	public WorkerHomepage() {
		
		getContentPane().setLayout(new BorderLayout());
		this.setBounds(100, 100, 813, 567);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		
		contentPane = new JPanel();
		contentPane.setLayout(null);
		contentPane.setBackground(new Color(51, 153, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		
		menuPanel = new JPanel();
		menuPanel.setBackground(new Color(0, 0, 51));
		menuPanel.setBounds(0, 0, 179, 538);
		contentPane.add(menuPanel);
		menuPanel.setLayout(null);
		
		//mainPanel adalah panel utama yang dapat diubah-ubah misalnya ketika klik notification, mainPanel akan berubah menjadi NotificationHistoryDisplay
		mainPanel = new JPanel();
		mainPanel.setBackground(new Color(153, 153, 153));
		mainPanel.setBounds(179, 122, 628, 416);
		contentPane.add(mainPanel);
		mainPanel.setLayout(null);
		
		taskBtn = new JButton("Task");
		taskBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
		taskBtn.setBounds(0, 226, 180, 44);
		menuPanel.add(taskBtn);
		taskBtn.setForeground(new Color(255, 255, 255));
		taskBtn.setBackground(new Color(51, 153, 204));
		
		profileBtn = new JButton("Profile");
		profileBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
		profileBtn.setBounds(0, 183, 180, 44);
		menuPanel.add(profileBtn);
		profileBtn.setForeground(Color.WHITE);
		profileBtn.setBackground(new Color(51, 153, 204));

		
		JLabel icon = new JLabel("New label");
		icon.setForeground(new Color(255, 255, 255));
		icon.setIcon(new ImageIcon(WorkerHomepage.class.getResource("/ICON.jpg")));
		icon.setBounds(43, 25, 95, 87);
		menuPanel.add(icon);
		
		JLabel labelCyberWhale = new JLabel("Cyber Whale");
		labelCyberWhale.setHorizontalAlignment(SwingConstants.CENTER);
		labelCyberWhale.setForeground(new Color(255, 255, 255));
		labelCyberWhale.setFont(new Font("Sylfaen", Font.BOLD, 17));
		labelCyberWhale.setBounds(24, 134, 129, 25);
		menuPanel.add(labelCyberWhale);
		
		notifBtn = new JButton("Notification");
		notifBtn.setForeground(Color.WHITE);
		notifBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
		notifBtn.setBackground(new Color(51, 153, 204));
		notifBtn.setBounds(0, 269, 180, 44);
		menuPanel.add(notifBtn);
		
		//panel paling atas warna putih, berisi button logout
		JPanel topPanel = new JPanel();
		topPanel.setBackground(new Color(255, 255, 255));
		topPanel.setBounds(179, 0, 628, 42);
		contentPane.add(topPanel);
		topPanel.setLayout(null);
		
		logoutBtn = new JButton("Logout");
		logoutBtn.setForeground(new Color(255, 255, 255));
		logoutBtn.setBackground(new Color(0, 0, 128));
		logoutBtn.setBounds(531, 11, 80, 23);
		topPanel.add(logoutBtn);

		getContentPane().add(contentPane);
		
		titlePanel = new JPanel();
		titlePanel.setBackground(new Color(51, 153, 204));
		titlePanel.setBounds(179, 42, 628, 80);
		contentPane.add(titlePanel);
		titlePanel.setLayout(null);
		
		
		JLabel titleLable = new JLabel("Worker Homepage");
		titleLable.setBounds(10, 18, 346, 51);
		titlePanel.add(titleLable);
		titleLable.setForeground(new Color(255, 255, 255));
		titleLable.setFont(new Font("Dubai Medium", Font.BOLD, 30));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public void refreshContent(JPanel panel) {
		mainPanel.removeAll();
		mainPanel.repaint();
		mainPanel.revalidate();
		
		mainPanel.add(panel);
		mainPanel.repaint();
		mainPanel.revalidate();
	}

	//setter getter
	public JButton getTaskBtn() {
		return taskBtn;
	}

	public void setTaskBtn(JButton taskBtn) {
		this.taskBtn = taskBtn;
	}

	public JButton getProfileBtn() {
		return profileBtn;
	}

	public void setProfileBtn(JButton profileBtn) {
		this.profileBtn = profileBtn;
	}

	public JButton getNotifBtn() {
		return notifBtn;
	}

	public void setNotifBtn(JButton notifBtn) {
		this.notifBtn = notifBtn;
	}

	public JButton getLogoutBtn() {
		return logoutBtn;
	}

	public void setLogoutBtn(JButton logoutBtn) {
		this.logoutBtn = logoutBtn;
	}
	
	
}
