package views;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import models.User;

public class ProfileDisplay extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	public ProfileDisplay(User user) {
		
		this.setLayout(new GridLayout(4, 1));
		this.setBorder(new EmptyBorder(50, 80, 150, 30));
		this.setPreferredSize(new Dimension(100, 100));

		
		this.add(new JLabel("My Profile"));
		this.add(new JLabel("ID:	" + user.getId()));
		this.add(new JLabel("Username:	" + user.getUsername()));
		this.add(new JLabel("Role:	" + user.getRole()));
		this.add(new JLabel("Address:	" + user.getAddress()));
		this.add(new JLabel("DOB:	" + user.getDOB()));
		this.add(new JLabel("Telp:	" + user.getTelp()));
		
		
		this.setVisible(true);
		
	}
}
