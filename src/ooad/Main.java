package ooad;

import controllers.MainController;
import controllers.UserController;
import views.CreateUserDisplay;
import views.ProfileDisplay;

public class Main {
	

	public Main() {
		UserController.openProfileDisplay();
		UserController.openCreateUserDisplay();
	}
	
	public static void main(String[] args) {
		new Main();
	}
	
}
