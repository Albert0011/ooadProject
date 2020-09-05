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
import views.UpdateTaskForm;
import views.UserTaskDisplay;

public class TaskHandler {
	
	private static TaskHandler th;
	private static String idTask;
	
	public static TaskHandler getInstance() {
		if(th == null) {
			th = new TaskHandler();
		}
		return th;
	}
	
	public AllTaskDisplay openAllTaskDisplay(ArrayList<Task> task) throws NoSuchObjectException, SQLException {
		
	
		AllTaskDisplay allTaskDisplay = new AllTaskDisplay(task);
		
		String role = Log.getInstance().getCurrentUser().getRole();
		
		if(role.equalsIgnoreCase("Supervisor")) {
			allTaskDisplay.add(allTaskDisplay.getPanelSupervisor());
		}
		else {
			allTaskDisplay.add(allTaskDisplay.getPanelWorker());
		}
		
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
							try {
								TaskHandler.approveTask(UUID.fromString(taskID), score);
								MainController.getInstance().refreshContent(openUserTaskDisplay());
							} catch (NoSuchObjectException | SQLException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
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
						try {
							TaskHandler.submitTask(UUID.fromString(taskID));
							MainController.getInstance().refreshContent(openUserTaskDisplay());
						} catch (NoSuchObjectException | SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
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
						try {
							TaskHandler.requestTaskRevision(UUID.fromString(taskID));
							MainController.getInstance().refreshContent(openUserTaskDisplay());
						} catch (NoSuchObjectException | SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
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
						try {
							TaskHandler.deleteTask(UUID.fromString(taskID));
							
							MainController.getInstance().refreshContent(openUserTaskDisplay());
						} catch (NoSuchObjectException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						} catch (SQLException e1) {
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
				if(allTaskDisplay.getViewAllTable().getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "Please Select Task");
				}
				else {
					int jawab = JOptionPane.showConfirmDialog(null, "Update this task?");
					switch (jawab) {
					case JOptionPane.YES_OPTION:
						int row = allTaskDisplay.getViewAllTable().getSelectedRow();
						idTask = (allTaskDisplay.getViewAllTable().getValueAt(row, 0)).toString();
						
						try {
							TaskHandler.getInstance().openUpdateTaskDisplay();
						} catch (NoSuchObjectException e1) {
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
		
		allTaskDisplay.getBtnSearch().addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				String query = allTaskDisplay.getSearchField().getText().toString();
				
				ArrayList<Task> listTask = searchTask(query);
				AllTaskDisplay a = new AllTaskDisplay(listTask);
				
				try {
					openUserTaskDisplay().refreshContent(openAllTaskDisplay(listTask));
				} catch (NoSuchObjectException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
//				try {
//					MainController.getInstance().refreshContent(openUserTaskDisplay());
//				} catch (NoSuchObjectException | SQLException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
				//terus cara update tabel gmn how
			}
		
		});
		
		allTaskDisplay.getBtnSortTask().addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String sortBy = allTaskDisplay.getSortByItem();
				String sortDir = allTaskDisplay.getSortDirItem();
				
				if(sortBy.equalsIgnoreCase("Sort By") || sortDir.equalsIgnoreCase("Sort Dir")) {
					JOptionPane.showMessageDialog(null, "Please Select Both Sorting Entities");
				}else {
					
					try {
						ArrayList<Task> listTask = sortTask(sortBy, sortDir);
						//terus gimana updatenya ke layar gtw how
						openUserTaskDisplay().refreshContent(openAllTaskDisplay(listTask));
						
					} catch (NoSuchObjectException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				
				
				
			}

			
		
		});

		
		return allTaskDisplay;
	
	}
	
	private ArrayList<Task> sortTask(String sortBy, String sortDir) throws NoSuchObjectException, SQLException {
        
		ArrayList<Task> listTask = Task.sort(sortBy, sortDir);
        return listTask;
        
	}
	
	public UpdateTaskForm openUpdateTaskDisplay() throws NoSuchObjectException{
		UpdateTaskForm ut = new UpdateTaskForm();
		Task task;
		task = Task.get(UUID.fromString(TaskHandler.getIdTask()));
		ut.setTaskIDField(task.getId().toString());
		ut.setWorkerIDField(task.getWorkerID().toString());
		ut.setSupervisorIDField(task.getSupervisorID().toString());
		ut.setTitleField(task.getTitle());
		ut.setScoreField(task.getScore().toString());
		ut.setNoteField(task.getNote());
		
			ut.getBtnUpdate().addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					try {
						updateTask(UUID.fromString(ut.getTaskIDField().getText()), UUID.fromString(ut.getWorkerIDField().getText()), 
												UUID.fromString(ut.getSupervisorIDField().getText()), ut.getTitleField().getText(), Integer.parseInt(ut.getScoreField().getText()), 
												ut.getNoteField().getText());
						
						ut.dispose();
						MainController.getInstance().refreshContent(openUserTaskDisplay());
						
						
					} catch (NoSuchObjectException | NumberFormatException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			});
			
			ut.getBtnCancel().addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					
				}
			});
		return ut;
	}

	
	
	public UserTaskDisplay openUserTaskDisplay() throws NoSuchObjectException, SQLException {
		UserTaskDisplay up = new UserTaskDisplay();
		
		ArrayList<Task> task = getAllTask();
		try {
			
			up.refreshContent(openAllTaskDisplay(task));
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
							//up.refreshContent(openAllTaskDisplay(task));
							MainController.getInstance().refreshContent(openUserTaskDisplay());
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
		Task task = null;

		try {
			String role = Log.getInstance().getCurrentUser().getRole();
			String uname = Log.getInstance().getCurrentUser().getUsername();
			String message;
			if(role.equalsIgnoreCase("Supervisor")) {
				task = Task.create(supervisorID, workerID, title, note);
				task.save();
				message = "Supervisor " + uname + " has assigned you a new task \"" + title + "\"";
				NotificationController.createNotification(workerID, message);
			}
			else {
				TaskRequestHandler.createTaskRequest(title, supervisorID, workerID, note);
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
	
	public static Task submitTask(UUID taskID) throws NoSuchObjectException{
		Task task;
		task = Task.get(taskID);
		String uname = Log.getInstance().getCurrentUser().getUsername();
	
		try {
			task = new Task(taskID, task.getWorkerID(), task.getSupervisorID(), task.getTitle(), task.getRevisionCount(), task.getScore(), 1, task.getApproveAt(), task.getNote());
			task.update();
			
			String message = uname + " has submitted \"" + task.getTitle() + "\"";
			NotificationController.createNotification(task.getSupervisorID(), message);
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	
		return task;
	}
	
	public static Task updateTask(UUID taskID, UUID workerID, UUID supervisorID, String title,Integer score, String note) throws NoSuchObjectException{

		String uname = Log.getInstance().getCurrentUser().getUsername();
		
		Task task = new Task(taskID, supervisorID, workerID, title, Task.get(taskID).getRevisionCount(), score, Task.get(taskID).getIsSubmitted(), Task.get(taskID).getApproveAt(), note);
		
		String message = "Supervisor " +  uname + " has updated information on task \"" + task.getTitle() + "\"";
		
		task.update();
		
		
		NotificationController.createNotification(supervisorID, message);
		NotificationController.createNotification(workerID, message);
		
		return task;
	}
	
	public static void deleteTask(UUID taskID) throws NoSuchObjectException{
		String uname = Log.getInstance().getCurrentUser().getUsername();
		Task task;
		try {
			task = Task.get(taskID);
			task.delete();
				
			String message = "Supervisor " +  uname + " has deleted task \"" + task.getTitle() + "\"";
			NotificationController.createNotification(task.getSupervisorID(), message);
			NotificationController.createNotification(task.getWorkerID(), message);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
	}
	
	public static Task approveTask(UUID taskID, Integer score) throws NoSuchObjectException{
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		Task task;
		task = Task.get(taskID);
		String role = Log.getInstance().getCurrentUser().getRole();
		String uname = Log.getInstance().getCurrentUser().getUsername();
		
		if(role.equalsIgnoreCase("Supervisor")){
			try {
				task = new Task(taskID, task.getSupervisorID(),  task.getWorkerID(), task.getTitle(), task.getRevisionCount(), score, task.getIsSubmitted(), timestamp, task.getNote());
				task.update();
				
				String message = "Supervisor " +  uname + " has approved your task \"" + task.getTitle() + "\"";
				NotificationController.createNotification(task.getWorkerID(), message);
				
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}
		return task;	
	}
	
	public static Task requestTaskRevision(UUID taskID) throws NoSuchObjectException{
		Task task;
		task = Task.get(taskID);
		String uname = Log.getInstance().getCurrentUser().getUsername();
		
		
		try {
			task = new Task(taskID, task.getWorkerID(), task.getSupervisorID(), task.getTitle(), task.getRevisionCount()+1, task.getScore(), 0, task.getApproveAt(), task.getNote());
			task.update();
			
			String message = "Supervisor " +  uname + " has requested you a revision on task \"" + task.getTitle() + "\"";
			NotificationController.createNotification(task.getWorkerID(), message);
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
		
		return task;
	}

	public static String getIdTask() {
		return idTask;
	}

	public static void setIdTask(String idTask) {
		TaskHandler.idTask = idTask;
	}

	


}