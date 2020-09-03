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
import helpers.Log;
import models.Task;
import models.User;
import views.AllTaskDisplay;
import views.TaskForm;
import views.UserTaskDisplay;

public class TaskHandler {
	
	private static TaskHandler th;
	
	public static TaskHandler getInstance() {
		if(th == null) {
			th = new TaskHandler();
		}
		return th;
	}
	
	public AllTaskDisplay openAllTaskDisplay() throws NoSuchObjectException, SQLException {
		
		ArrayList<Task> task = getAllTask();
		AllTaskDisplay allTaskDisplay = new AllTaskDisplay(task);
		
		allTaskDisplay.getBtnApprove().addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				if(allTaskDisplay.getViewAllTable().getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "Please Select Task");
				}
				else {
					int jawab = JOptionPane.showConfirmDialog(null, "Are you sure to approve this task?");
					switch (jawab) {
					case JOptionPane.YES_OPTION:
							int row = allTaskDisplay.getViewAllTable().getSelectedRow();
							String taskID = (allTaskDisplay.getViewAllTable().getValueAt(row, 0)).toString();
							Integer score = 5;//apa ini isinya,kubikin 5 biar ga eror aja wkwk
							TaskHandler.approveTask(UUID.fromString(taskID), score);
							
						try {
							MainController.getInstance().refreshContent(openAllTaskDisplay());
						} catch (NoSuchObjectException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
							JOptionPane.showMessageDialog(null, "Approve Task Success!! ");
							
						break;
					case JOptionPane.NO_OPTION:
					
						break;

					default:
						break;
					}

				}
		
			}
		});
		
		allTaskDisplay.getBtnSubmit().addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				if(allTaskDisplay.getViewAllTable().getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "Please Select Task");
				}
				else {
					int jawab = JOptionPane.showConfirmDialog(null, "Are you sure to submit this task?");
					switch (jawab) {
					case JOptionPane.YES_OPTION:
							int row = allTaskDisplay.getViewAllTable().getSelectedRow();
							String taskID = (allTaskDisplay.getViewAllTable().getValueAt(row, 0)).toString();
							TaskHandler.submitTask(UUID.fromString(taskID));
							
						try {
							MainController.getInstance().refreshContent(openAllTaskDisplay());
						} catch (NoSuchObjectException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
							JOptionPane.showMessageDialog(null, "Submit Task Success!! ");
							
						break;
					case JOptionPane.NO_OPTION:
						break;

					default:
						break;
					}

				}
		
			}
		});
		
		
		allTaskDisplay.getBtnRequestRevision().addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				if(allTaskDisplay.getViewAllTable().getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "Please Select Task");
				}
				else {
					int jawab = JOptionPane.showConfirmDialog(null, "Request revision for this task?");
					switch (jawab) {
					case JOptionPane.YES_OPTION:
							int row = allTaskDisplay.getViewAllTable().getSelectedRow();
							String taskID = (allTaskDisplay.getViewAllTable().getValueAt(row, 0)).toString();
							TaskHandler.requestTaskRevision(UUID.fromString(taskID));
							
						try {
							MainController.getInstance().refreshContent(openAllTaskDisplay());
						} catch (NoSuchObjectException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
							JOptionPane.showMessageDialog(null, "Request Revision Success!! ");
							
						break;
					case JOptionPane.NO_OPTION:
						break;

					default:
						break;
					}

				}
		
			}
		});
		
		allTaskDisplay.getBtnDeleteTask().addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				if(allTaskDisplay.getViewAllTable().getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "Please Select Task");
				}
				else {
					int jawab = JOptionPane.showConfirmDialog(null, "Delete this task?");
					switch (jawab) {
					case JOptionPane.YES_OPTION:
							int row = allTaskDisplay.getViewAllTable().getSelectedRow();
							String taskID = (allTaskDisplay.getViewAllTable().getValueAt(row, 0)).toString();
							TaskHandler.deleteTask(UUID.fromString(taskID));
							
						try {
							MainController.getInstance().refreshContent(openAllTaskDisplay());
						} catch (NoSuchObjectException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
							JOptionPane.showMessageDialog(null, "Delete Task Success!! ");
							
						break;
					case JOptionPane.NO_OPTION:
						break;

					default:
						break;
					}

				}
		
			}
		});
		
		
		allTaskDisplay.getBtnUpdate().addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
			
			}
		
		});
		
		allTaskDisplay.getBtnSearch().addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
			
			}
		
		});
		
		allTaskDisplay.getBtnSortTask().addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
			
			}
		
		});
		
		return allTaskDisplay;
	
	}

	
	
	public UserTaskDisplay openUserTaskDisplay() throws NoSuchObjectException {
		UserTaskDisplay up = new UserTaskDisplay();
		
		try {
			up.refreshContent(openAllTaskDisplay());
		} catch (NoSuchObjectException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		MainController.getInstance().refreshContent(up);
		
				up.getViewAllTaskBtn().addActionListener(new ActionListener() {
				
					@Override
					public void actionPerformed(ActionEvent e) {
						try {
							up.refreshContent(openAllTaskDisplay());
							MainController.getInstance().refreshContent(up);
						} catch (NoSuchObjectException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
			
				up.getCreateTaskBtn().addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						up.refreshContent(openCreateTaskForm());
						try {
							MainController.getInstance().refreshContent(up);
						} catch (NoSuchObjectException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
				
		
		return up;
	}
	
	

	public TaskForm openCreateTaskForm() {
		TaskForm tf = new TaskForm();
		tf.getCreateTaskForm().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (tf.getTitleField().getText().isEmpty() || tf.getSupervisorIDField().getText().isEmpty()
						|| tf.getWorkerIDField().getText().isEmpty() || tf.getNoteField().getText().isEmpty()) {

					JOptionPane.showMessageDialog(null, "Please Complete All Data");
					
				} else {
					try {
						TaskHandler.createTask(tf.getTitleField().getText(), UUID.fromString(tf.getWorkerIDField().getText()), UUID.fromString(tf.getSupervisorIDField().getText()), 
										 tf.getNoteField().getText());
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

	public static Task createTask(String title, UUID workerID, UUID supervisorID, String note) throws RequestFailedException, SQLException{
		if(title.length() < 10){
			throw new RequestFailedException("Title cannot be less than 15 characters");
		}
//		if(note.length() > 10 && note.length() < 100){
//			throw new RequestFailedException("note must be between 10 - 100 characters");
//		}
		Task task = Task.create(supervisorID, workerID, title, note);
		task.save();
		
		try {
			String role = Log.getInstance().getCurrentUser().getRole();
			String uname = Log.getInstance().getCurrentUser().getUsername();
			String message;
			if(role.equalsIgnoreCase("Supervisor")) {
				message = "Supervisor " + uname + " has assigned you a new task \"" + title + "\"";
				NotificationController.createNotification(workerID, message);
			}
			else {
				message = uname +" has requested you to supervise a new task \"" + title + "\"";
				NotificationController.createNotification(supervisorID, message);
			}
			
		} catch (NoSuchObjectException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return task;
	}
	
	
	public static ArrayList<Task> getAllTask() throws NoSuchObjectException, SQLException{
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
	
	public static Task submitTask(UUID taskID){
		Task task;
		task = Task.get(taskID);
		try {
			task = new Task(taskID, task.getWorkerID(), task.getSupervisorID(), task.getTitle(), task.getRevisionCount(), task.getScore(), 1, task.getApproveAt(), task.getNote());
			task.update();
			String message = task.getWorkerID().toString() + " has submitted \"" + task.getTitle() + "\"";
			NotificationController.createNotification(taskID, message);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return task;
	}
	
	public static Task updateTask(UUID taskID, UUID workerID, UUID supervisorID, String title,Integer score, String note){
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		Task task = new Task(taskID, workerID, supervisorID, title, 0, score, 0, timestamp, note);
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
	
	public static Task approveTask(UUID taskID, Integer score){
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		Task task;
		task = Task.get(taskID);	
		try {
			task = new Task(taskID, task.getWorkerID(), task.getSupervisorID(), task.getTitle(), task.getRevisionCount(), score, task.getIsSubmitted(), timestamp, task.getNote());
			task.update();
			
			String message = task.getSupervisorID().toString() + " has approved your task \""+ task.getTitle() + "\"" ;
			NotificationController.createNotification(taskID, message);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return task;	
	}
	
	public static Task requestTaskRevision(UUID taskID){
		Task task;
		task = Task.get(taskID);
		try {
			task = new Task(taskID, task.getWorkerID(), task.getSupervisorID(), task.getTitle(), task.getRevisionCount()+1, task.getScore(), 0, task.getApproveAt(), task.getNote());
			task.update();
			
			String message = task.getSupervisorID().toString() + " has requested you a revision on task \"" + task.getTitle() + "\"";
			NotificationController.createNotification(taskID, message);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
		return task;
	}

	


}