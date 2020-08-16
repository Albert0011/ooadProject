package controllers;

import java.awt.EventQueue;
import java.util.Date;
import models.User;
import views.CreateUserDisplay;
import views.ProfileDisplay;

public class UserController {

	public UserController() {
		
	}
	
	public static void openProfileDisplay() {
		User user = getUser("09c64781-a6c8-41d3-991b-3ba2cfbab67a");
		ProfileDisplay profileDisplay = new ProfileDisplay(user);
		MainController.getInstance().addPanel(profileDisplay);
	}
	
	public static void openCreateUserDisplay() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateUserDisplay frame = new CreateUserDisplay();
					frame.setVisible(true);
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static User getUser(String userID) {
		return User.getUser(userID);
	}
	
	public static void createUser(String username, String role, Date DOB, String address, String telp) {
		User user = new User(null, username, null, role, address, null, telp);
		user.createUser(username, role, DOB, address, telp);
		
	}

}
