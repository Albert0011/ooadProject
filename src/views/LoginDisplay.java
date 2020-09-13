package views;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class LoginDisplay extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton loginButton;
	private JPasswordField passField;
	private JTextField unameField;

	

	public LoginDisplay() {
		this.setBackground(new Color(30, 144, 255));
		this.setBackground(new Color(255, 255, 255));
		getContentPane().setLayout(null);
		this.setResizable(false);
		
		JPanel panelTitle = new JPanel();
		panelTitle.setBackground(new Color(51, 153, 204));
		panelTitle.setBounds(0, 0, 324, 199);
		getContentPane().add(panelTitle);
		panelTitle.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("WELCOME TO");
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Segoe UI Black", Font.BOLD, 27));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(28, 34, 227, 48);
		panelTitle.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Cyber Whale");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Segoe UI Black", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(28, 93, 251, 41);
		panelTitle.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Task Management System");
		lblNewLabel_2.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setBounds(28, 123, 252, 27);
		panelTitle.add(lblNewLabel_2);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(28, 85, 258, 2);
		panelTitle.add(separator);
		
		JPanel panelLogin = new JPanel();
		panelLogin.setBackground(new Color(0, 0, 51));
		panelLogin.setBounds(323, 0, 317, 417);
		this.getContentPane().add(panelLogin);
		panelLogin.setLayout(null);
		
		JLabel uname = new JLabel("Username");
		uname.setForeground(new Color(255, 255, 255));
		uname.setBounds(37, 128, 71, 20);
		panelLogin.add(uname);
		uname.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel titleLogin = new JLabel("Login");
		titleLogin.setForeground(new Color(255, 255, 255));
		titleLogin.setHorizontalAlignment(SwingConstants.CENTER);
		titleLogin.setBounds(108, 41, 93, 25);
		panelLogin.add(titleLogin);
		titleLogin.setFont(new Font("Tahoma", Font.PLAIN, 22));
		
		passField = new JPasswordField();
		passField.setForeground(new Color(255, 255, 255));
		passField.setBackground(new Color(0, 0, 51));
		passField.setBounds(37, 234, 235, 30);
		panelLogin.add(passField);
		
		JLabel pass = new JLabel("Password");
		pass.setForeground(new Color(255, 255, 255));
		pass.setBounds(37, 207, 90, 25);
		panelLogin.add(pass);
		pass.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		loginButton = new JButton("Login");
		loginButton.setBounds(91, 324, 133, 38);
		panelLogin.add(loginButton);
		loginButton.setForeground(new Color(255, 255, 255));
		loginButton.setBackground(new Color(51, 153, 204));
		loginButton.setFont(new Font("Dubai Light", Font.BOLD, 19));
		
		unameField = new JTextField();
		unameField.setForeground(new Color(255, 255, 255));
		unameField.setBackground(new Color(0, 0, 51));
		unameField.setBounds(37, 150, 235, 30);
		panelLogin.add(unameField);
		
		JPanel panelGambar = new JPanel();
		panelGambar.setBounds(0, 199, 324, 218);
		this.getContentPane().add(panelGambar);
		panelGambar.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setIcon(new ImageIcon(LoginDisplay.class.getResource("/company.jpg")));
		lblNewLabel_3.setBounds(0, 0, 324, 218);
		panelGambar.add(lblNewLabel_3);
		this.setBounds(100, 100, 646, 446);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setVisible(true);
		
		
	}

	//setter getter
	public JButton getLoginButton() {
		return loginButton;
	}

	public void setLoginButton(JButton loginButton) {
		this.loginButton = loginButton;
	}

	public JPasswordField getPassField() {
		return passField;
	}

	public void setPassField(JPasswordField passField) {
		this.passField = passField;
	}

	public JTextField getUnameField() {
		return unameField;
	}

	public void setUnameField(JTextField unameField) {
		this.unameField = unameField;
	}
	
	
	


}
