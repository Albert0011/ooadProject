package controllers;

import models.User;
import views.ProfileDisplay;

public class UserController {

	public UserController() {
		
	}
	
	public static void openProfileDisplay() {
		User user = getUser("73ea31e3-1301-49d3-bae9-31b90762879a");
		ProfileDisplay profileDisplay = new ProfileDisplay(user);
		MainController.getInstance().addPanel(profileDisplay);
	}
	
	public static User getUser(String userID) {
		return User.getUser(userID);
	}
	
}
