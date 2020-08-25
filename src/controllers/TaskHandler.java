package controllers;

import java.awt.EventQueue;
import java.util.Date;

import models.Task;
import views.TaskForm;

public class TaskHandler {

	public static void openCreateTaskForm() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TaskForm frame = new TaskForm();
					frame.setVisible(true);
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void createTask(String title, String supervisorID, String workerID, String note) {
		Task task = new Task(title, supervisorID, workerID, note);
		task.create(title, supervisorID, workerID, note);
		
	}

}
