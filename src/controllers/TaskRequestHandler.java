package controllers;

import java.rmi.NoSuchObjectException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

import javax.swing.JOptionPane;

import helpers.Log;
import models.TaskRequest;
import models.User;



public class TaskRequestHandler {

	public static TaskRequest createTaskRequest(String title, UUID supervisorID, UUID workerID, String note) {
		
		try {
			TaskRequest taskReq = TaskRequest.create(workerID, supervisorID, title, note);
			taskReq.save();
			return taskReq;
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Create Failed!! "+e.getMessage());
			return null;
		}
		
	}
	
	
	public static ArrayList<TaskRequest> getAllTaskRequest() throws NoSuchObjectException, SQLException{
		ArrayList<TaskRequest> taskReq = null;
		User currentUser = Log.getInstance().getCurrentUser();
		taskReq = TaskRequest.getAll(currentUser.getId());
		
		return taskReq;
	}
	
	public static TaskRequest getTaskRequest(UUID taskRequestID) {
		TaskRequest taskRequest;
		
		try {
			taskRequest = TaskRequest.get(taskRequestID);
			return taskRequest;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			return null;
		}
		
	}
	
	public static TaskRequest rejectTaskRequest(UUID taskRequestID) {
		
		TaskRequest taskRequest = getTaskRequest(taskRequestID);
		
		UUID workerID = taskRequest.getWorkerID();
		String taskReqID = taskRequest.getId().toString();
		
		
		try {

			taskRequest.delete();	
			NotificationController.createNotification(workerID, "Your Task Request: "+taskReqID+" has been rejected.");
			JOptionPane.showMessageDialog(null,"Task Rejected!");
			return taskRequest;
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			return null;
		}
		
	}
	
	public static TaskRequest acceptTaskRequest(UUID taskRequestID) {
		
		TaskRequest taskRequest = getTaskRequest(taskRequestID);
		
		try {

			taskRequest.delete();	
			TaskHandler.createTask(taskRequest.getTitle(), taskRequest.getWorkerID(), taskRequest.getSupervisorID(), taskRequest.getNote());
			NotificationController.createNotification(taskRequest.getWorkerID(), "Your Task Request: "+taskRequest.getId()+" has been accepted!.");
			JOptionPane.showMessageDialog(null,"Task Accepted!");
			
			return taskRequest;
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			return null;
		}
		
	}
	
}
