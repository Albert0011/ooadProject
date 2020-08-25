package controllers;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JPanel;


import models.User;
import views.AdminHomepage;
import views.AllUserDisplay;
import views.ChangePasswordForm;
import views.CreateUserDisplay;
import views.ProfileDisplay;
import views.UpdateProfileForm;

public class UserController {

	
	public UserController() {
		
	}
	
	public static void openAdminHomepage() {
		ArrayList<User> user = getAllUser();
		AdminHomepage frame = new AdminHomepage(user);
		frame.setVisible(true);
	}
	
	public static void openProfileDisplay() {
		User user = getUser("d49da081-7223-4b4a-9d72-bb4c2a7c427a");
		ProfileDisplay profileDisplay = new ProfileDisplay(user);
		MainController.getInstance().addPanel(profileDisplay);
	}
	
	public static void openCreateUserDisplay() {
		CreateUserDisplay panel = new CreateUserDisplay();
		//MainController.getInstance().addAdminPanel(panel);
		
	}
	
	public static void openAllUserDisplay() {
		
		ArrayList<User> user = getAllUser();
		AllUserDisplay frame = new AllUserDisplay(user);
		frame.setVisible(true);
	
	}
	
	public static void openChangePasswordForm() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChangePasswordForm frame = new ChangePasswordForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void openUpdateProfileForm() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateProfileForm frame = new UpdateProfileForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void openChangePasswordForm() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChangePasswordForm frame = new ChangePasswordForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void openUpdateProfileForm() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateProfileForm frame = new UpdateProfileForm();
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
	
	public static User createUser(String username, String role, Date DOB, String address, String telp) {
	
		if(username.length() < 5 || username.length() > 15 || address.length() < 10 || 
				address.length()>100 || telp.length()<10 || telp.length()>13) {
			JOptionPane.showMessageDialog(null, "data not valid!! ");
			return null;
		}
		
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
	
	public static User changePassword(String oldPassword, String newPassword) {
		User user = getUser("09c64781-a6c8-41d3-991b-3ba2cfbab67a");
		if(oldPassword.equals(user.getPassword())) {
			user.setPassword(newPassword);
			user.update();
			JOptionPane.showMessageDialog(null, "Change password Success!!");
			return user;
		}
		else {
			JOptionPane.showMessageDialog(null, "Old password not match!!");
			return null;
		}
	}
	
	public static User updateProfile(String username, Date DOB, String address, String telp) {
		User user = getUser("09c64781-a6c8-41d3-991b-3ba2cfbab67a");
		java.sql.Date date= new java.sql.Date(DOB.getTime());
		String defaultPassword = date.toString();
		user.setPassword(defaultPassword);
		user.setUsername(username);
		user.setDOB(DOB);
		user.setAddress(address);
		user.setTelp(telp);
		
		user.update();
		return user;
	}
	
	

}