package controllers;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Date;

import models.User;
import views.AllUserDisplay;
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
	
	public static void openAllUserDisplay() {
		
		ArrayList<User> user = getAllUser();
		AllUserDisplay frame = new AllUserDisplay(user);
		frame.setVisible(true);
	
	
	}

	
	public static User getUser(String userID) {
		return User.get(userID);
	}
	
	public static User createUser(String username, String role, Date DOB, String address, String telp) {
		User user = User.create(username, role, DOB, address, telp);
		return user.save(); 
		
	}
	
	public static ArrayList<User> getAllUser(){
		return User.getAll();
	}
	
	public static void deleteUser(String id) {
		User user = getUser(id);
		user.delete();
	}
	
	public static User resetPassword(String id) {
		User user = getUser(id);
		
		java.sql.Date date= new java.sql.Date(user.getDOB().getTime());
		String defaultPassword = date.toString();
		user.setPassword(defaultPassword);
		user.update();
		return user;
	}
	
	

}
