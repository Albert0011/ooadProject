package ooad;



import controllers.MainController;
import controllers.UserController;

public class Main {
	

	public Main() {
//		UserController.openProfileDisplay();
		MainController.getInstance().displayAdminHomepage();
//		MainController.getInstance().displayLogin();
//		UserController.openCreateUserDisplay();
//		UserController.openAllUserDisplay();
//		UserController.openChangePasswordForm();
//		UserController.openUpdateProfileForm();
//		TaskController.openCreateTaskForm();

		
	}
	
	public static void main(String[] args) {
		new Main();
	}
	
}
