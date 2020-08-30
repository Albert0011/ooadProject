package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NoSuchObjectException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.UUID;

import javax.swing.JOptionPane;

import exceptions.RequestFailedException;
import models.Task;
import models.User;
import views.TaskForm;

public class TaskHandler {

	public static TaskForm openCreateTaskForm() {
		TaskForm tf = new TaskForm();
		tf.getCreateTaskForm().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (tf.getTitleField().getText().isEmpty() || tf.getSupervisorIDField().getText().isEmpty()
						|| tf.getWorkerIDField().getText().isEmpty() || tf.getNoteField().getText().isEmpty()) {

					JOptionPane.showMessageDialog(null, "Please Complete All Data");
					
				} else {
					try {
						TaskHandler.createTask(UUID.fromString(tf.getSupervisorIDField().getText()), UUID.fromString(tf.getWorkerIDField().getText()), 
										tf.getTitleField().getText(), tf.getNoteField().getText());
					} catch (RequestFailedException | SQLException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
				
					tf.getTitleField().setText("");
					tf.getSupervisorIDField().setText("");
					tf.getWorkerIDField().setText("");
					tf.getNoteField().setText("");
				}
			}
		});
		return tf;
	}

	public static Task createTask(UUID supervisorID, UUID workerID, String title, String note) throws RequestFailedException, SQLException{
		if(title.length() < 10){
			throw new RequestFailedException("Title cannot be less than 15 characters");
		}
		if(note.length() > 10 && note.length() < 100){
			throw new RequestFailedException("note must be between 10 - 100 characters");
		}
		Task task = Task.create(supervisorID, workerID, title, note);
		task.save();
		return task;
	}
	
	
	public static ArrayList<Task> getAllTask() throws NoSuchObjectException{
        ArrayList<Task> task = null;
        User currentUser = Log.getInstance().getCurrentUser();
        task = Task.getAll(currentUser.getId());

        return task;
    }

    public static Task getTask(UUID taskID) {
        Task task;

        try {
            task = Task.get(taskID);
            return task;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        }

    }
	
	public static ArrayList<Task> searchTask(String query){
		ArrayList<Task> task = null;
		try {
			task = Task.Search(query);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Search Failed "+e.getMessage());
			return null;
		}
		return task;
	}
	
	public static Task updateTask(UUID taskID, UUID workerID, UUID supervisorID, String title,Integer score, String note){
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		Task task = new Task(taskID, workerID, supervisorID, title, 0, score, false, timestamp, note);
		task.update();
		return task;
	}
	
	public static void deleteTask(UUID taskID){
		Task task;
		try {
			task = Task.get(taskID);
			task.delete();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
	}
	
	


}