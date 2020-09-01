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

	public AdminHomepage() {
		
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
		
		mainPanel = new JPanel();
		mainPanel.setBackground(new Color(153, 153, 153));
		mainPanel.setBounds(179, 122, 628, 416);
		contentPane.add(mainPanel);
		mainPanel.setLayout(null);
		
		btnCreateUser = new JButton("Create User");
		btnCreateUser.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnCreateUser.setBounds(0, 248, 180, 65);
		menuPanel.add(btnCreateUser);
		btnCreateUser.setForeground(new Color(255, 255, 255));
		btnCreateUser.setBackground(new Color(51, 153, 204));
		
		btnViewAllUser = new JButton("View All User");
		btnViewAllUser.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnViewAllUser.setBounds(0, 183, 180, 65);
		menuPanel.add(btnViewAllUser);
		btnViewAllUser.setForeground(Color.WHITE);
		btnViewAllUser.setBackground(new Color(51, 153, 204));

		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setIcon(new ImageIcon(AdminHomepage.class.getResource("/ICON.jpg")));
		lblNewLabel_1.setBounds(43, 25, 95, 87);
		menuPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Cyber Whale");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Sylfaen", Font.BOLD, 17));
		lblNewLabel_2.setBounds(24, 134, 129, 25);
		menuPanel.add(lblNewLabel_2);
		
		JPanel headerPanel = new JPanel();
		headerPanel.setPreferredSize(new Dimension(300, 42));
		headerPanel.setLayout(new BorderLayout());
		headerPanel.setBackground(Color.blue);
		headerPanel.setBounds(179, 0, 618, 123);
		
		JPanel topPanel = new JPanel();
		topPanel.setBackground(new Color(255, 255, 255));
		topPanel.setBounds(179, 0, 628, 42);
		contentPane.add(topPanel);
		
		
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
		mainPanel.removeAll();
		mainPanel.repaint();
		mainPanel.revalidate();
		
		mainPanel.add(panel);
		mainPanel.repaint();
		mainPanel.revalidate();
	}
}
