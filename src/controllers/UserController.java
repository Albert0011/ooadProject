package controllers;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFrame;

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
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AllUserDisplay frame = new AllUserDisplay(getAllUser());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public static User getUser(String userID) {
		return User.get(userID);
	}
	
	public static void createUser(String username, String role, Date DOB, String address, String telp) {
		User user = new User(null, username, null, role, address, null, telp);
		user.create(username, role, DOB, address, telp);
		
	}
	
	public static ArrayList<User> getAllUser(){
		return User.getAll();
	}
	
	public static void deleteUser(String id) {
		User user = getUser(id);
		user.delete(id);
		AllUserDisplay frame = new AllUserDisplay(getAllUser());
		frame.setVisible(false);
	}

}
