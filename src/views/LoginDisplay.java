package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.TextField;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controllers.MainController;

import javax.swing.ImageIcon;
import javax.swing.JSeparator;
import javax.swing.JPasswordField;

public class LoginDisplay {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginDisplay window = new LoginDisplay();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginDisplay() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBackground(new Color(30, 144, 255));
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.getContentPane().setLayout(null);
		
		JPanel panelTitle = new JPanel();
		panelTitle.setBackground(new Color(51, 153, 204));
		panelTitle.setBounds(0, 0, 324, 190);
		frame.getContentPane().add(panelTitle);
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
		panelLogin.setBounds(323, 0, 307, 407);
		frame.getContentPane().add(panelLogin);
		panelLogin.setLayout(null);
		
		JLabel uname = new JLabel("Username");
		uname.setForeground(new Color(255, 255, 255));
		uname.setBounds(37, 105, 71, 20);
		panelLogin.add(uname);
		uname.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel titleLogin = new JLabel("Login");
		titleLogin.setForeground(new Color(255, 255, 255));
		titleLogin.setHorizontalAlignment(SwingConstants.CENTER);
		titleLogin.setBounds(108, 41, 93, 25);
		panelLogin.add(titleLogin);
		titleLogin.setFont(new Font("Tahoma", Font.PLAIN, 22));
		
		JLabel lblRole = new JLabel("Role");
		lblRole.setForeground(new Color(255, 255, 255));
		lblRole.setBounds(37, 235, 90, 25);
		panelLogin.add(lblRole);
		lblRole.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JPasswordField passField = new JPasswordField();
		passField.setForeground(new Color(255, 255, 255));
		passField.setBackground(new Color(0, 0, 51));
		passField.setBounds(37, 190, 235, 30);
		panelLogin.add(passField);
		
		JLabel pass = new JLabel("Password");
		pass.setForeground(new Color(255, 255, 255));
		pass.setBounds(37, 170, 90, 25);
		panelLogin.add(pass);
		pass.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		Button loginButton = new Button("Login");
		loginButton.setBounds(91, 324, 133, 38);
		panelLogin.add(loginButton);
		loginButton.setForeground(new Color(255, 255, 255));
		loginButton.setBackground(new Color(51, 153, 204));
		loginButton.setFont(new Font("Dubai Light", Font.PLAIN, 19));
		
		JComboBox roleChoose = new JComboBox();
		roleChoose.setForeground(new Color(255, 255, 255));
		roleChoose.setBackground(new Color(0, 0, 51));
		roleChoose.setFont(new Font("Tahoma", Font.PLAIN, 12));
		roleChoose.setBounds(37, 255, 235, 30);
		panelLogin.add(roleChoose);
		roleChoose.setModel(new DefaultComboBoxModel(new String[] {"Admin", "Worker", "Supervisor"}));
		
		TextField unameField = new TextField();
		unameField.setForeground(new Color(255, 255, 255));
		unameField.setBackground(new Color(0, 0, 51));
		unameField.setBounds(37, 125, 235, 30);
		panelLogin.add(unameField);
		
		JPanel panelGambar = new JPanel();
		panelGambar.setBounds(0, 189, 324, 218);
		frame.getContentPane().add(panelGambar);
		panelGambar.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setIcon(new ImageIcon(LoginDisplay.class.getResource("/company.jpg")));
		lblNewLabel_3.setBounds(0, 0, 324, 218);
		panelGambar.add(lblNewLabel_3);
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(roleChoose.getSelectedItem().equals("Admin")) {
					
				}
			}
		});
		frame.setBounds(100, 100, 646, 446);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
