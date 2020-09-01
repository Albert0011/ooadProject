package views;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import models.User;

public class ProfileDisplay extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	private JPanel mainPanel;
	
	
	public ProfileDisplay(User user) {
		
		this.setBackground(new Color(255, 255, 224));
		this.setSize(628, 376);
		this.setLocation(0, 0);
		this.setLayout(null);
		
		mainPanel = new JPanel();
		mainPanel.setBackground(new Color(255, 255, 224));
		mainPanel.setBounds(0, 0, 628, 376);
		add(mainPanel);
		mainPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("My Profile");
		lblNewLabel.setBounds(257, 52, 107, 27);
		mainPanel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Segoe UI Historic", Font.BOLD, 20));
		
		JLabel id = new JLabel("ID             : " + user.getId());
		id.setBounds(101, 90, 517, 27);
		mainPanel.add(id);
		id.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel uname = new JLabel("Username  : " + user.getUsername());
		uname.setBounds(101, 128, 517, 27);
		mainPanel.add(uname);
		uname.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel role = new JLabel("Role          : " + user.getRole());
		role.setBounds(101, 166, 517, 27);
		mainPanel.add(role);
		role.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel addr = new JLabel("Address     : " + user.getAddress());
		addr.setBounds(101, 204, 517, 27);
		mainPanel.add(addr);
		addr.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel dob = new JLabel("DOB          : " + user.getDOB());
		dob.setBounds(101, 242, 517, 27);
		mainPanel.add(dob);
		dob.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel telp = new JLabel("Telp          : " + user.getTelp());
		telp.setBounds(101, 280, 517, 27);
		mainPanel.add(telp);
		telp.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		

		this.setVisible(true);
		
	}

	
	
	
}