package views;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class SupervisorHomepage extends JFrame {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel menuPanel;
	private JPanel mainPanel;
	private JPanel titlePanel;
	
	private JButton taskBtn;
	private JButton profileBtn;
	private JButton taskReqBtn;
	private JButton notifBtn;

	public SupervisorHomepage() {
		
		getContentPane().setLayout(new BorderLayout());
		this.setBounds(100, 100, 813, 567);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		
		contentPane = new JPanel();
		contentPane.setLayout(null);
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		
		menuPanel = new JPanel();
		menuPanel.setBackground(new Color(0, 0, 51));
		menuPanel.setBounds(0, 0, 179, 538);
		contentPane.add(menuPanel);
		menuPanel.setLayout(null);
		
		mainPanel = new JPanel();
		mainPanel.setBackground(new Color(245, 245, 245));
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

		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setIcon(new ImageIcon(SupervisorHomepage.class.getResource("/ICON.jpg")));
		lblNewLabel_1.setBounds(43, 25, 95, 87);
		menuPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Cyber Whale");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Sylfaen", Font.BOLD, 17));
		lblNewLabel_2.setBounds(24, 134, 129, 25);
		menuPanel.add(lblNewLabel_2);
		
		taskReqBtn = new JButton("Task Request");
		taskReqBtn.setForeground(Color.WHITE);
		taskReqBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
		taskReqBtn.setBackground(new Color(51, 153, 204));
		taskReqBtn.setBounds(0, 269, 180, 44);
		menuPanel.add(taskReqBtn);
		
		notifBtn = new JButton("Notification");
		notifBtn.setForeground(Color.WHITE);
		notifBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
		notifBtn.setBackground(new Color(51, 153, 204));
		notifBtn.setBounds(0, 312, 180, 44);
		menuPanel.add(notifBtn);
		
		JPanel headerPanel = new JPanel();
		headerPanel.setPreferredSize(new Dimension(300, 42));
		headerPanel.setLayout(new BorderLayout());
		headerPanel.setBackground(Color.blue);
		headerPanel.setBounds(179, 0, 618, 123);
		
		JPanel topPanel = new JPanel();
		topPanel.setBackground(new Color(255, 255, 255));
		topPanel.setBounds(179, 0, 628, 42);
		contentPane.add(topPanel);

		getContentPane().add(contentPane);
		
		titlePanel = new JPanel();
		titlePanel.setBackground(new Color(51, 153, 204));
		titlePanel.setBounds(179, 42, 628, 80);
		contentPane.add(titlePanel);
		titlePanel.setLayout(null);
		
		
		JLabel titleLable = new JLabel("Supervisor Homepage");
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

	public JPanel getTitlePanel() {
		return titlePanel;
	}

	public void setTitlePanel(JPanel titlePanel) {
		this.titlePanel = titlePanel;
	}

	public JButton getTaskReqBtn() {
		return taskReqBtn;
	}

	public void setTaskReqBtn(JButton taskReqBtn) {
		this.taskReqBtn = taskReqBtn;
	}

	public JButton getNotifBtn() {
		return notifBtn;
	}

	public void setNotifBtn(JButton notifBtn) {
		this.notifBtn = notifBtn;
	}

	public JPanel getMainPanel() {
		return mainPanel;
	}

	public void setMainPanel(JPanel mainPanel) {
		this.mainPanel = mainPanel;
	}


	
}
