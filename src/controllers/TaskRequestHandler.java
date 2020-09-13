package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NoSuchObjectException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

import javax.swing.JOptionPane;

import helpers.Log;
import models.Task;
import models.TaskRequest;
import models.User;
import views.AllTaskRequestDisplay;



public class TaskRequestHandler {
	
	private static TaskRequestHandler taskRequestHandler;
	
	//singleton
	public static TaskRequestHandler getInstance() {
		if(taskRequestHandler == null) {
			taskRequestHandler = new TaskRequestHandler();
		}
		return taskRequestHandler;
	}
	
	//membuat task request baru
	public TaskRequest createTaskRequest(String title, UUID supervisorID, UUID workerID, String note) throws NoSuchObjectException {
		try {
			TaskRequest taskReq = TaskRequest.create(workerID, supervisorID, title, note);
			taskReq.save();
			
			return taskReq;
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Create Task Request Failed!! "+e.getMessage());
			return null;
		}
		
	}
	
	//GET ALL TASK REQUEST
	public ArrayList<TaskRequest> getAllTaskRequest() throws NoSuchObjectException, SQLException{
		ArrayList<TaskRequest> taskReq = null;
		User currentUser = Log.getInstance().getCurrentUser();
		taskReq = TaskRequest.getAll(currentUser.getId());
		
		return taskReq;
	}
	
	// GET TASK REQUEST
	public TaskRequest getTaskRequest(UUID taskRequestID) {
		TaskRequest taskRequest;
		
		try {
			taskRequest = TaskRequest.get(taskRequestID);
			return taskRequest;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			return null;
		}
		
	}
	
	//REJECT TASK REQUEST
	public TaskRequest rejectTaskRequest(UUID taskRequestID) throws SQLException {
		//mengambil id task request yang akan di reject
		TaskRequest taskRequest = getTaskRequest(taskRequestID);
		
		UUID workerID = taskRequest.getWorkerID();
		UUID supervisorID = taskRequest.getSupervisorID();
		User supervisor = User.get(supervisorID.toString());
		
		try {
			//delete task request
			taskRequest.delete();	
			
			//mengirimkan notifikasi kepada supervisor dan worker tentang task request yang di reject 
			NotificationController.getInstance().createNotification(workerID, supervisor.getUsername()+" has rejected your task request "+taskRequest.getTitle());
			
			return taskRequest;
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			return null;
		}
		
	}
	
	//ACCEPT TASK REQUEST
	public TaskRequest acceptTaskRequest(UUID taskRequestID) throws SQLException {
		//mengambil id task request yang akan di accept
		TaskRequest taskRequest = getTaskRequest(taskRequestID);
		
		UUID workerID = taskRequest.getWorkerID();
		UUID supervisorID = taskRequest.getSupervisorID();
		User supervisor = User.get(supervisorID.toString());
		
		try {
			//menghilangkan task yang sudah di accept dari tabel
			taskRequest.delete();	
			
			//membuat task baru untuk worker
			TaskHandler.getInstance().createTask(taskRequest.getTitle(), taskRequest.getSupervisorID(), taskRequest.getWorkerID(), taskRequest.getNote());
			NotificationController.getInstance().createNotification(workerID, supervisor.getUsername()+" has accepted your task request "+taskRequest.getTitle());
			
			return taskRequest;
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			return null;
		}
		
	}
	
	//OPEN ALL TASK REQUEST DISPLAY
	public AllTaskRequestDisplay openAllTaskRequestDisplay() throws NoSuchObjectException, SQLException {
		
		ArrayList<TaskRequest> taskReq = getAllTaskRequest();
		AllTaskRequestDisplay allTaskRequestDisplay = new AllTaskRequestDisplay(taskReq);
		
		//REJECT TASK REQUEST
		allTaskRequestDisplay.getBtnRejectTaskRequest().addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				//jika belum select task dari tabel
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
							TaskRequestHandler.getInstance().rejectTaskRequest(UUID.fromString(taskRequestID));
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
		
		//ACCEPT TASK REQUEST
		allTaskRequestDisplay.getBtnAcceptTaskRequest().addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				//jika belum select task dari tabel
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
							TaskRequestHandler.getInstance().acceptTaskRequest(UUID.fromString(taskRequestID));
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