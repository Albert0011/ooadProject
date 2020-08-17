package views;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


import models.User;

public class ProfileDisplay extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	public ProfileDisplay(User user) {
		
		setBackground(new Color(230, 230, 250));
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("My Profile");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(231, 36, 107, 27);
		add(lblNewLabel);
		
		JLabel id = new JLabel("ID             : " + user.getId());
		id.setFont(new Font("Tahoma", Font.PLAIN, 16));
		id.setBounds(30, 119, 517, 27);
		add(id);
		
		JLabel uname = new JLabel("Username  : " + user.getUsername());
		uname.setFont(new Font("Tahoma", Font.PLAIN, 16));
		uname.setBounds(30, 158, 517, 27);
		add(uname);
		
		JLabel role = new JLabel("Role          : " + user.getRole());
		role.setFont(new Font("Tahoma", Font.PLAIN, 16));
		role.setBounds(30, 200, 517, 27);
		add(role);
		
		JLabel addr = new JLabel("Address     : " + user.getAddress());
		addr.setFont(new Font("Tahoma", Font.PLAIN, 16));
		addr.setBounds(30, 238, 517, 27);
		add(addr);
		
		JLabel dob = new JLabel("DOB          : " + user.getDOB());
		dob.setFont(new Font("Tahoma", Font.PLAIN, 16));
		dob.setBounds(30, 276, 517, 27);
		add(dob);
		
		JLabel telp = new JLabel("Telp          : " + user.getTelp());
		telp.setFont(new Font("Tahoma", Font.PLAIN, 16));
		telp.setBounds(30, 314, 517, 27);
		add(telp);
		
		JButton backButton = new JButton("Back");
		backButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		backButton.setBounds(231, 375, 89, 41);
		add(backButton);

		this.setVisible(true);
		
	}
}
