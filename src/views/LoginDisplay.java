package views;

import java.awt.Button;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class LoginDisplay extends JFrame{
	

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginDisplay frame = new LoginDisplay();
					frame.setVisible(true);
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public LoginDisplay() {
		this.setBackground(new Color(30, 144, 255));
		this.getContentPane().setBackground(new Color(255, 255, 255));
		this.getContentPane().setLayout(null);
		
		JLabel titleLogin = new JLabel("Login");
		titleLogin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		titleLogin.setBounds(140, 38, 48, 25);
		this.getContentPane().add(titleLogin);
		
		JLabel uname = new JLabel("Username");
		uname.setFont(new Font("Tahoma", Font.PLAIN, 16));
		uname.setBounds(20, 98, 90, 25);
		this.getContentPane().add(uname);
		
		JLabel pass = new JLabel("Password");
		pass.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pass.setBounds(20, 131, 90, 25);
		this.getContentPane().add(pass);
		
		TextField unameField = new TextField();
		unameField.setBounds(115, 99, 199, 22);
		this.getContentPane().add(unameField);
		
		TextField passField = new TextField();
		passField.setBounds(115, 132, 199, 22);
		this.getContentPane().add(passField);
		
		Button loginButton = new Button("Login");
		loginButton.setForeground(new Color(255, 255, 255));
		loginButton.setBackground(new Color(0, 0, 255));
		loginButton.setFont(new Font("Dubai Light", Font.PLAIN, 19));
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		loginButton.setBounds(83, 223, 169, 38);
		this.getContentPane().add(loginButton);
		
		JLabel lblRole = new JLabel("Role");
		lblRole.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblRole.setBounds(20, 164, 90, 25);
		this.getContentPane().add(lblRole);
		
		JComboBox<Object> comboBox = new JComboBox<Object>();
		comboBox.setModel(new DefaultComboBoxModel<Object>(new String[] {"Admin", "Worker", "Supervisor"}));
		comboBox.setBounds(115, 165, 199, 20);
		this.getContentPane().add(comboBox);
		this.setBounds(100, 100, 351, 336);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
