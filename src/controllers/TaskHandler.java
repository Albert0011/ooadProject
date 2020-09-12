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
import views.SearchTaskResultDisplay;
import views.TaskForm;
import views.TaskScoreForm;
import views.UpdateTaskForm;
import views.UserTaskDisplay;

public class TaskHandler {
	
	private static TaskHandler taskHandler;
	private static String idTask;
	
	public static TaskHandler getInstance() {
		if(taskHandler == null) {
			taskHandler = new TaskHandler();
		}
		return taskHandler;
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
					int row = allTaskDisplay.getViewAllTable().getSelectedRow();
					String isSubmitted = allTaskDisplay.getViewAllTable().getValueAt(row, 6).toString();
					Object approved_at = allTaskDisplay.getViewAllTable().getValueAt(row, 7);
					
					if(isSubmitted.equals("1") && approved_at == null) {
						
						int jawab = JOptionPane.showConfirmDialog(null, "Are you sure to approve this task?");
						switch (jawab) {
						case JOptionPane.YES_OPTION:
							idTask = (allTaskDisplay.getViewAllTable().getValueAt(row, 0)).toString();
							try {
								TaskHandler.getInstance().openScoreDisplay();
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
							break;
						case JOptionPane.NO_OPTION:
							
							break;
							
						default:
							break;
						}
						
					} else if(approved_at != null) { 
						JOptionPane.showMessageDialog(null, "Task is already approved!");
					} else {
						JOptionPane.showMessageDialog(null, "Task is not submitted!");
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
					
					int row = allTaskDisplay.getViewAllTable().getSelectedRow();
					String isSubmitted = allTaskDisplay.getViewAllTable().getValueAt(row, 6).toString();
					
					if(isSubmitted.equals("0")) {
						
						int jawab = JOptionPane.showConfirmDialog(null, "Are you sure to submit this task?");
						switch (jawab) {
						case JOptionPane.YES_OPTION:
							String taskID = (allTaskDisplay.getViewAllTable().getValueAt(row, 0)).toString();
							try {
								TaskHandler.getInstance().submitTask(UUID.fromString(taskID));
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
						
					} else {
						JOptionPane.showMessageDialog(null, "Task is already submitted! ");
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
					int row = allTaskDisplay.getViewAllTable().getSelectedRow();
					String isSubmitted = allTaskDisplay.getViewAllTable().getValueAt(row, 6).toString();
					Object approved_at = allTaskDisplay.getViewAllTable().getValueAt(row, 7);
					
					
					if(isSubmitted.equals("1") && approved_at == null) {
						
						int jawab = JOptionPane.showConfirmDialog(null, "Request revision for this task?");
						switch (jawab) {
						case JOptionPane.YES_OPTION:
							String taskID = (allTaskDisplay.getViewAllTable().getValueAt(row, 0)).toString();
							try {
								TaskHandler.getInstance().requestTaskRevision(UUID.fromString(taskID));
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
						
					} else if(approved_at != null) { 
						JOptionPane.showMessageDialog(null, "Task is already approved!");
					} else {
						JOptionPane.showMessageDialog(null, "Task is not submitted!");
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
							TaskHandler.getInstance().deleteTask(UUID.fromString(taskID));
							
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
				if(allTaskDisplay.getSearchField().equals(null)) {
					JOptionPane.showMessageDialog(null, "Put something first in the search field");
				}
				else {
					String query = allTaskDisplay.getSearchField().getText().toString();
					ArrayList<Task> listTask = searchTask(query);
					
					try {
						openSearchTaskResultDisplay(listTask);
					} catch (NoSuchObjectException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
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
	
	public TaskScoreForm openScoreDisplay(){
		TaskScoreForm ts= new TaskScoreForm();
		Task task;
		task = Task.get(UUID.fromString(TaskHandler.getIdTask()));
		ts.setTaskIDField(task.getId().toString());
		ts.setSupervisorIDField(task.getSupervisorID().toString());
		ts.setWorkerIDField(task.getWorkerID().toString());
		ts.setTitleField(task.getTitle());
		
		
			ts.getBtnSubmit().addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						TaskHandler.getInstance().approveTask(UUID.fromString(TaskHandler.getIdTask()), Integer.parseInt(ts.getScoreField().getText()));
						ts.dispose();
						MainController.getInstance().refreshContent(openUserTaskDisplay());
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					}
					
				}
			});
			
			ts.getBtnCancel().addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					ts.dispose();
				}
			});
		return ts;
	}
	
	public UpdateTaskForm openUpdateTaskDisplay() throws NoSuchObjectException{
		UpdateTaskForm ut = new UpdateTaskForm();
		Task task;
		task = Task.get(UUID.fromString(TaskHandler.getIdTask()));
		ut.setTaskIDField(task.getId().toString());
		ut.setSupervisorIDField(task.getSupervisorID().toString());
		ut.setWorkerIDField(task.getWorkerID().toString());
		ut.setTitleField(task.getTitle());
		ut.setScoreField(task.getScore().toString());
		ut.setNoteField(task.getNote());
		
			ut.getBtnUpdate().addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					try {
						updateTask(UUID.fromString(ut.getTaskIDField().getText()), UUID.fromString(ut.getSupervisorIDField().getText()), 
												UUID.fromString(ut.getWorkerIDField().getText()), ut.getTitleField().getText(), Integer.parseInt(ut.getScoreField().getText()), 
												ut.getNoteField().getText());
						
						ut.dispose();
						MainController.getInstance().refreshContent(openUserTaskDisplay());
						
						
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					}
					
				}
			});
			
			ut.getBtnCancel().addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					ut.dispose();
				}
			});
		return ut;
	}

	public SearchTaskResultDisplay openSearchTaskResultDisplay(ArrayList<Task> list) throws NoSuchObjectException, SQLException{
		
		SearchTaskResultDisplay sc = new SearchTaskResultDisplay(list);
		return sc;
		
		
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
							up.refreshContent(openAllTaskDisplay(task));
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
						TaskHandler.getInstance().createTask(tf.getTitleField().getText(), UUID.fromString(tf.getSupervisorIDField().getText()), UUID.fromString(tf.getWorkerIDField().getText()), 
										 tf.getNoteField().getText());
						JOptionPane.showMessageDialog(null, "Create Task Success!");
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

	public Task createTask(String title, UUID supervisorID, UUID workerID, String note) throws RequestFailedException, SQLException{
		
		if(validateTitle(title) == false) {
			throw new IllegalArgumentException("Title length must be between 5-20 characters length!");
		} else if(validateNote(note) == false) {
			throw new IllegalArgumentException("Note length must be between 0-50 characters length!");
		} else if(validateExistID(workerID) == false){
			throw new IllegalArgumentException("The workerID doesn't exist in database");
		} else if(validateExistID(supervisorID) == false){
			throw new IllegalArgumentException("The supervisorID doesn't exist in database");
		} else if(validateWorkerID(workerID) == false){
			throw new IllegalArgumentException("You cannot create task for different workerID");
		} else if(validateSupervisorID(supervisorID) == false){
			throw new IllegalArgumentException("You cannot create task for different supervisorID");
		}
		
		Task task = null;

		try {
			String role = Log.getInstance().getCurrentUser().getRole();
			String uname = Log.getInstance().getCurrentUser().getUsername();
			String message;
			if(role.equalsIgnoreCase("Supervisor")) {
				task = Task.create(supervisorID, workerID, title, note);
				task.save();
				message = uname + " has assigned you a new task \"" + title + "\"";
				NotificationController.getInstance().createNotification(workerID, message);
			}
			else {
				TaskRequestHandler.getInstance().createTaskRequest(title, supervisorID, workerID, note);
				message = uname +" has requested you to supervise a new task \"" + title + "\"";
				NotificationController.getInstance().createNotification(supervisorID, message);
			}
		} catch (NoSuchObjectException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	
		return task;
	}
	
	
	private static boolean validateNote(String note) {
		if(note.length()>=0 && note.length()<=50) {
			return true;
		}
		return false;
	}

	private static boolean validateTitle(String title) {
		if(title.length()>=5 && title.length()<=20) {
			return true;
		}
		return false;
	}
	
	private static boolean validateExistID(UUID id){
		
		if(Task.get(id) == null){
			return false;
		}
		
		return true;
		
	}

	
	private static boolean validateWorkerID(UUID idWorker){
		String role = "";
		String roleid = "";
		try {
			role = Log.getInstance().getCurrentUser().getRole();
			roleid = Log.getInstance().getCurrentUser().getId().toString();
		} catch (NoSuchObjectException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(role.equals("Worker")){
			if(UUID.fromString(roleid).equals(idWorker)){
				return true;
			}
		}
		
		return false;
		
	}
	
	private static boolean validateSupervisorID(UUID idSupervisor){
		String role = "";
		String roleid = "";
		try {
			role = Log.getInstance().getCurrentUser().getRole();
			roleid = Log.getInstance().getCurrentUser().getId().toString();
		} catch (NoSuchObjectException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(role.equals("Supervisor")){
			if(UUID.fromString(roleid).equals(idSupervisor)){
				return true;
			}
		}
		
		return false;
		
	}

	public ArrayList<Task> getAllTask() throws NoSuchObjectException, SQLException{
        ArrayList<Task> task = null;
        
        User currentUser = Log.getInstance().getCurrentUser();
        task = Task.getAll(currentUser.getId());

        return task;
    }

    public Task getTask(UUID taskID) {
        Task task;

        try {
            task = Task.get(taskID);
            return task;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        }

    }
	
	public ArrayList<Task> searchTask(String query){
		ArrayList<Task> task = null;
		try {
			task = Task.Search(query);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Search Failed "+e.getMessage());
			return null;
		}
		return task;
	}
	
	public Task submitTask(UUID taskID) throws NoSuchObjectException{
		Task task;
		task = Task.get(taskID);
		String uname = Log.getInstance().getCurrentUser().getUsername();
	
		try {
			task = new Task(taskID, task.getSupervisorID(), task.getWorkerID(), task.getTitle(), task.getRevisionCount(), task.getScore(), 1, task.getApproveAt(), task.getNote());
			task.update();
			
			String message = uname + " has submitted \"" + task.getTitle() + "\"";
			NotificationController.getInstance().createNotification(task.getSupervisorID(), message);
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	
		return task;
	}
	
	public Task updateTask(UUID taskID, UUID supervisorID, UUID workerID, String title,Integer score, String note) throws NoSuchObjectException, IllegalArgumentException{

		if(validateTitle(title) == false) {
			throw new IllegalArgumentException("Title length must be between 5-20 characters length!");
		} else if(validateNote(note) == false) {
			throw new IllegalArgumentException("Note length must be between 0-50 characters length!");
		} else if(validateScore(score) == false) {
			throw new IllegalArgumentException("Score must be between 1-100!");
		} 
		
		String uname = Log.getInstance().getCurrentUser().getUsername();
		
		Task task = new Task(taskID, supervisorID, workerID, title, Task.get(taskID).getRevisionCount(), score, Task.get(taskID).getIsSubmitted(), Task.get(taskID).getApproveAt(), note);
		
		String message = uname + " has updated information on task \"" + task.getTitle() + "\"";
		
		task.update();
		
		NotificationController.getInstance().createNotification(supervisorID, message);
		NotificationController.getInstance().createNotification(workerID, message);
		
		return task;
	}
	
	private static boolean validateScore(Integer score) {

		if(score<1 || score>100) {
			System.out.println("masuk");
			return false;
		}
		
		return true;
		
	}

	public void deleteTask(UUID taskID) throws NoSuchObjectException{
		String uname = Log.getInstance().getCurrentUser().getUsername();
		Task task;
		try {
			task = Task.get(taskID);
			task.delete();
				
			String message = uname + " has deleted task \"" + task.getTitle() + "\"";
			
			NotificationController.getInstance().createNotification(task.getSupervisorID(), message);
			NotificationController.getInstance().createNotification(task.getWorkerID(), message);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
	}
	
	public Task approveTask(UUID taskID, Integer score) throws NoSuchObjectException{
		
		if(validateScore(score) == false) {
			throw new IllegalArgumentException("Score must be between 1-100!");
		} 
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		Task task;
		task = Task.get(taskID);
		String role = Log.getInstance().getCurrentUser().getRole();
		String uname = Log.getInstance().getCurrentUser().getUsername();
		
		if(role.equalsIgnoreCase("Supervisor")){
			try {
				task = new Task(taskID, task.getSupervisorID(),  task.getWorkerID(), task.getTitle(), task.getRevisionCount(), score, task.getIsSubmitted(), timestamp, task.getNote());
				task.update();
				
				String message = uname + " has approved your task \"" + task.getTitle() + "\"";
				NotificationController.getInstance().createNotification(task.getWorkerID(), message);
				
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}
		return task;	
	}
	
	public Task requestTaskRevision(UUID taskID) throws NoSuchObjectException{
		Task task;
		task = Task.get(taskID);
		String uname = Log.getInstance().getCurrentUser().getUsername();
		
		
		try {
			task = new Task(taskID, task.getSupervisorID(), task.getWorkerID(), task.getTitle(), task.getRevisionCount()+1, task.getScore(), 0, task.getApproveAt(), task.getNote());
			task.update();
			
			String message = uname + " has requested you a revision on task \"" + task.getTitle() + "\"";
			NotificationController.getInstance().createNotification(task.getWorkerID(), message);
			
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