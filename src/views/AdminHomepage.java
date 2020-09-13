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

public class AdminHomepage extends JFrame {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JPanel contentPane;
	public JButton btnCreateUser;
	public JButton btnViewAllUser;
	public JPanel menuPanel;
	public JPanel mainPanel;
	private JButton logoutBtn;

	public AdminHomepage() {
		
		getContentPane().setLayout(new BorderLayout());
		this.setBounds(100, 100, 813, 567);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		
		contentPane = new JPanel();
		contentPane.setLayout(null);
		contentPane.setBackground(new Color(51, 153, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		//menu panel ada di sebelah kiri yang terdapat icon, nama perusahaan, button view all user, dan button create user
		menuPanel = new JPanel();
		menuPanel.setBackground(new Color(0, 0, 51));
		menuPanel.setBounds(0, 0, 179, 538);
		contentPane.add(menuPanel);
		menuPanel.setLayout(null);
		
		//main panel adalah panel utama yang dapat diubah-ubah misalnya ketika klik view all user, mainPanel akan berubah menjadi AllUserDisplay
		mainPanel = new JPanel();
		mainPanel.setBackground(new Color(153, 153, 153));
		mainPanel.setBounds(179, 122, 628, 416);
		contentPane.add(mainPanel);
		mainPanel.setLayout(null);
		
		//button ini akan menampilkan form create user jika di klik
		btnCreateUser = new JButton("Create User");
		btnCreateUser.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnCreateUser.setBounds(0, 248, 180, 65);
		menuPanel.add(btnCreateUser);
		btnCreateUser.setForeground(new Color(255, 255, 255));
		btnCreateUser.setBackground(new Color(51, 153, 204));
		
		//button ini akan menampilkan all user display jika di klik
		btnViewAllUser = new JButton("View All User");
		btnViewAllUser.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnViewAllUser.setBounds(0, 183, 180, 65);
		menuPanel.add(btnViewAllUser);
		btnViewAllUser.setForeground(Color.WHITE);
		btnViewAllUser.setBackground(new Color(51, 153, 204));

		//untuk menampilkan icon di sebelah kiri atas
		JLabel icon = new JLabel("New label");
		icon.setForeground(new Color(255, 255, 255));
		icon.setIcon(new ImageIcon(AdminHomepage.class.getResource("/ICON.jpg")));
		icon.setBounds(43, 25, 95, 87);
		menuPanel.add(icon);
		
		//label untuk nama perusahaan
		JLabel labelCyberWhale = new JLabel("Cyber Whale");
		labelCyberWhale.setHorizontalAlignment(SwingConstants.CENTER);
		labelCyberWhale.setForeground(new Color(255, 255, 255));
		labelCyberWhale.setFont(new Font("Sylfaen", Font.BOLD, 17));
		labelCyberWhale.setBounds(24, 134, 129, 25);
		menuPanel.add(labelCyberWhale);

		//panel paling atas yang berwarna putih dan berisi button logout
		JPanel topPanel = new JPanel();
		topPanel.setBackground(new Color(255, 255, 255));
		topPanel.setBounds(179, 0, 628, 42);
		contentPane.add(topPanel);
		topPanel.setLayout(null);
		
		//tombol untuk logout
		logoutBtn = new JButton("Logout");
		logoutBtn.setForeground(new Color(255, 255, 255));
		logoutBtn.setBackground(new Color(0, 0, 128));
		logoutBtn.setBounds(531, 11, 80, 23);
		topPanel.add(logoutBtn);
		
		//label yang bertuliskan "Admin Homepage" berada di bawah top Panel
		JLabel titleLable = new JLabel("Admin Homepage");
		titleLable.setForeground(new Color(255, 255, 255));
		titleLable.setFont(new Font("Dubai Medium", Font.BOLD, 30));
		titleLable.setBounds(185, 70, 323, 57);
		contentPane.add(titleLable);

		getContentPane().add(contentPane);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public void refreshContent(JPanel panel) {
		//untuk ganti panel pada main panel
		mainPanel.removeAll();
		mainPanel.repaint();
		mainPanel.revalidate();
		
		mainPanel.add(panel);
		mainPanel.repaint();
		mainPanel.revalidate();
	}

	//setter getter
	public JButton getLogoutBtn() {
		return logoutBtn;
	}

	public void setLogoutBtn(JButton logoutBtn) {
		this.logoutBtn = logoutBtn;
	}
	
	
}
