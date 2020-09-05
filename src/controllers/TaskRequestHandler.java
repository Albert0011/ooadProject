package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NoSuchObjectException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

import javax.swing.JOptionPane;

import helpers.Log;
import models.TaskRequest;
import models.User;
import views.AllTaskRequestDisplay;



public class TaskRequestHandler {
	
	private static TaskRequestHandler trh;
	
	
	public static TaskRequestHandler getInstance() {
		if(trh == null) {
			trh = new TaskRequestHandler();
		}
		return trh;
	}

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
	
	public static TaskRequest rejectTaskRequest(UUID taskRequestID) throws SQLException {
		
		TaskRequest taskRequest = getTaskRequest(taskRequestID);
		
		UUID workerID = taskRequest.getWorkerID();
		UUID supervisorID = taskRequest.getSupervisorID();
		User supervisor = User.get(supervisorID.toString());
		
		try {

			taskRequest.delete();	
			NotificationController.createNotification(workerID, "Supervisor " + supervisor.getUsername()+" has rejected your task request "+taskRequest.getTitle());
			JOptionPane.showMessageDialog(null,"Task Rejected!");
			return taskRequest;
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			return null;
		}
		
	}
	
	public static TaskRequest acceptTaskRequest(UUID taskRequestID) throws SQLException {
		
		TaskRequest taskRequest = getTaskRequest(taskRequestID);
		
		UUID workerID = taskRequest.getWorkerID();
		UUID supervisorID = taskRequest.getSupervisorID();
		User supervisor = User.get(supervisorID.toString());
		
		try {

			taskRequest.delete();	
			TaskHandler.createTask(taskRequest.getTitle(), taskRequest.getSupervisorID(), taskRequest.getWorkerID(), taskRequest.getNote());
			NotificationController.createNotification(workerID, "Supervisor " + supervisor.getUsername()+" has accepted your task request "+taskRequest.getTitle());
			JOptionPane.showMessageDialog(null,"Task Accepted!");
			
			return taskRequest;
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			return null;
		}
		
	}
	
	public AllTaskRequestDisplay openAllTaskRequestDisplay() throws NoSuchObjectException, SQLException {
		
		ArrayList<TaskRequest> taskReq = getAllTaskRequest();
		AllTaskRequestDisplay allTaskRequestDisplay = new AllTaskRequestDisplay(taskReq);
		
		allTaskRequestDisplay.getBtnRejectTaskRequest().addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				if(allTaskRequestDisplay.getViewAllTable().getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "Please Select Task Request");
				}
				else {
					int jawab = JOptionPane.showConfirmDialog(null, "Are you sure to reject this task request?");
					switch (jawab) {
					case JOptionPane.YES_OPTION:
							int row = allTaskRequestDisplay.getViewAllTable().getSelectedRow();
							String taskRequestID = (allTaskRequestDisplay.getViewAllTable().getValueAt(row, 0)).toString();
						try {
							TaskRequestHandler.rejectTaskRequest(UUID.fromString(taskRequestID));
						} catch (SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
							
						try {
							MainController.getInstance().refreshContent(openAllTaskRequestDisplay());
						} catch (NoSuchObjectException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
							JOptionPane.showMessageDialog(null, "Reject Task Request Success!! ");
							
						break;
					case JOptionPane.NO_OPTION:
					
						break;

					default:
						break;
					}

				}
		
			}
		});
		
		allTaskRequestDisplay.getBtnAcceptTaskRequest().addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				if(allTaskRequestDisplay.getViewAllTable().getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "Please Select Task Request");
				}
				else {
					int jawab = JOptionPane.showConfirmDialog(null, "Are you sure to accept this task request?");
					switch (jawab) {
					case JOptionPane.YES_OPTION:
							int row = allTaskRequestDisplay.getViewAllTable().getSelectedRow();
							String taskRequestID = (allTaskRequestDisplay.getViewAllTable().getValueAt(row, 0)).toString();
						try {
							TaskRequestHandler.acceptTaskRequest(UUID.fromString(taskRequestID));
						} catch (SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
							
						try {
							MainController.getInstance().refreshContent(openAllTaskRequestDisplay());
						} catch (NoSuchObjectException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
							JOptionPane.showMessageDialog(null, "Accept Task Request Success!! ");
							
						break;
					case JOptionPane.NO_OPTION:
						break;

					default:
						break;
					}

				}
		
			}
		});
		
		
		return allTaskRequestDisplay;
	}
}
