package ooad;


import controllers.TaskController;

import controllers.UserController;

public class Main {
	

	public Main() {

//		UserController.openProfileDisplay();
//		UserController.openCreateUserDisplay();
//		UserController.openAllUserDisplay();
		TaskController.openCreateTaskForm();

		//UserController.openProfileDisplay();
		//UserController.openCreateUserDisplay();
		UserController.openAllUserDisplay();
	}
	
	public static void main(String[] args) {
		new Main();
	}
	
}
