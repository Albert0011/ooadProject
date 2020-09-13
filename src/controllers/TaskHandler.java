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
	// variable untuk menyimpan id task yang akan ditampilkan di halaman lain
	private static String idTask;
	
	
	//singleton
	public static TaskHandler getInstance() {
		if(taskHandler == null) {
			taskHandler = new TaskHandler();
		}
		return taskHandler;
	}
	
	//untuk menampilkan semua task
	public AllTaskDisplay openAllTaskDisplay(ArrayList<Task> task) throws NoSuchObjectException, SQLException {
		
	
		AllTaskDisplay allTaskDisplay = new AllTaskDisplay(task);
		
		String role = Log.getInstance().getCurrentUser().getRole();
		
		//Jika rolenya supervisor maka akan di add panel(panelSupervisor) yang berisi 4 button yaitu Button Approve, Update, Delete, Request revision
		if(role.equalsIgnoreCase("Supervisor")) {
			allTaskDisplay.add(allTaskDisplay.getPanelSupervisor());
		}
		//Jika rolenya worker maka akan di add panel(panelWorker) yang berisi button submit saja
		else {
			allTaskDisplay.add(allTaskDisplay.getPanelWorker());
		}
		
		//APPROVE TASK
		allTaskDisplay.getBtnApprove().addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				//jika belum select task dari tabel
				if(allTaskDisplay.getViewAllTable().getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "Please Select Task");
				}
				else {
					int row = allTaskDisplay.getViewAllTable().getSelectedRow();
					String isSubmitted = allTaskDisplay.getViewAllTable().getValueAt(row, 6).toString();
					Object approved_at = allTaskDisplay.getViewAllTable().getValueAt(row, 7);
					
					//jika task sudah disubmit dan belum diapprove
					if(isSubmitted.equals("1") && approved_at == null) {
						
						int jawab = JOptionPane.showConfirmDialog(null, "Are you sure to approve this task?");
						switch (jawab) {
						case JOptionPane.YES_OPTION:
							//menyimpan id task yang akan di approve
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
						
					} else if(approved_at != null) { //jika sudah di approve
						JOptionPane.showMessageDialog(null, "Task is already approved!");
					} else { //jika task belum disubmit
						JOptionPane.showMessageDialog(null, "Task is not submitted!");
					}
					

				}
		
			}
		});
		
		//SUBMIT TASK
		allTaskDisplay.getBtnSubmit().addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				//jika belum select task dari tabel
				if(allTaskDisplay.getViewAllTable().getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "Please Select Task");
				}
				else {
					
					int row = allTaskDisplay.getViewAllTable().getSelectedRow();
					String isSubmitted = allTaskDisplay.getViewAllTable().getValueAt(row, 6).toString();
					//jika task belum disubmit
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
		
		//REQUEST REVISION
		allTaskDisplay.getBtnRequestRevision().addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				//jika belum select task dari tabel
				if(allTaskDisplay.getViewAllTable().getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "Please Select Task");
				}
				else {
					int row = allTaskDisplay.getViewAllTable().getSelectedRow();
					String isSubmitted = allTaskDisplay.getViewAllTable().getValueAt(row, 6).toString();
					Object approved_at = allTaskDisplay.getViewAllTable().getValueAt(row, 7);
					
					//jika sudah disubmit dan belum di approve
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
						
					} else if(approved_at != null) { //jika task sudah di approve
						JOptionPane.showMessageDialog(null, "Task is already approved!");
					} else { //jika task belum disubmit
						JOptionPane.showMessageDialog(null, "Task is not submitted!");
					}

				}
		
			}
		});
		
		//DELETE TASK
		allTaskDisplay.getBtnDeleteTask().addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				//jika belum select task dari tabel
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
						} catch (NoSuchObjectException | SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						};

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
		
		//UPDATE TASK
		allTaskDisplay.getBtnUpdate().addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				//jika belum select task dari tabel
				if(allTaskDisplay.getViewAllTable().getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "Please Select Task");
				}
				else {
					int jawab = JOptionPane.showConfirmDialog(null, "Update this task?");
					switch (jawab) {
					case JOptionPane.YES_OPTION:
						int row = allTaskDisplay.getViewAllTable().getSelectedRow();
						
						//menyimpan id task yang akan diupdate
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
		
		//SEARCH TASK
		allTaskDisplay.getBtnSearch().addActionListener(new ActionListener() {	
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//jika field search task masih kosong
				if(allTaskDisplay.getSearchField().equals(null)) {
					JOptionPane.showMessageDialog(null, "Put something first in the search field");
				}
				else {
					String query = allTaskDisplay.getSearchField().getText().toString();
					ArrayList<Task> listTask = searchTask(query);
					
					try {
						//membuka jframe SearchTaskResultDisplay
						openSearchTaskResultDisplay(listTask);
					} catch (NoSuchObjectException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		
		});
		
		//SORT TASK
		allTaskDisplay.getBtnSortTask().addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String sortBy = allTaskDisplay.getSortByItem();
				String sortDir = allTaskDisplay.getSortDirItem();
				
				//jika belum memilih sortBy dan sortDir
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
	
	// fungsi untuk memanggil sort di model Task
	private ArrayList<Task> sortTask(String sortBy, String sortDir) throws NoSuchObjectException, SQLException {
        
		ArrayList<Task> listTask = Task.sort(sortBy, sortDir);
        return listTask;
        
	}
	
	//menampilkan Form Task Score
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
	
	//menampilkan form update task (jframe)
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
						
						//jika sudah diupdate jframe update task akan ditutup dan halaman user task display akan di refresh
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
			//AllTaskDisplay adalah mainPanel default dari UserTaskDisplay 
			up.refreshContent(openAllTaskDisplay(task));
		} catch (NoSuchObjectException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		MainController.getInstance().refreshContent(up);
		
				//VIEW ALL TASK
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
			
				//CREATE TASK
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
	
	//menampilkan form create task
	public TaskForm openCreateTaskForm() {
		TaskForm tf = new TaskForm();
		
		//CREATE TASK
		tf.getCreateTaskForm().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//jika masih ada field yang kosong
				if (tf.getTitleField().getText().isEmpty() || tf.getSupervisorIDField().getText().isEmpty()
						|| tf.getWorkerIDField().getText().isEmpty() || tf.getNoteField().getText().isEmpty()) {

					JOptionPane.showMessageDialog(null, "Please Complete All Data");
					
				} else {
					try {
						TaskHandler.getInstance().createTask(tf.getTitleField().getText(), UUID.fromString(tf.getSupervisorIDField().getText()), UUID.fromString(tf.getWorkerIDField().getText()), 
										 tf.getNoteField().getText());
						
						String role = Log.getInstance().getCurrentUser().getRole().toString();
						
						if(role.equals("Supervisor")) {
							JOptionPane.showMessageDialog(null, "Create Task Success!");
						}
						else {
							JOptionPane.showMessageDialog(null, "Create Task Request Success!");
						}
						
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					}
					
					//semua field dikosongkan kembali
					tf.getTitleField().setText("");
					tf.getSupervisorIDField().setText("");
					tf.getWorkerIDField().setText("");
					tf.getNoteField().setText("");
				}
			}
		});
		return tf;
	}

	public Task createTask(String title, UUID supervisorID, UUID workerID, String note) throws RequestFailedException, SQLException, NoSuchObjectException{
		//validasi ketika create task
		if(validateTitle(title) == false) {
			throw new IllegalArgumentException("Title length must be between 5-20 characters length!");
		} else if(validateNote(note) == false) {
			throw new IllegalArgumentException("Note length must be between 0-50 characters length!");
		} 
		else if(validateExistID(supervisorID) == false){
			throw new IllegalArgumentException("The supervisorID doesn't exist in database");
		}
		else if(validateExistID(workerID) == false){
			throw new IllegalArgumentException("The workerID doesn't exist in database");
		} 
		else if(validateID(workerID) == false){
			throw new IllegalArgumentException("You cannot create task for different workerID");
		}
		else if(validateID(supervisorID) == false){
			throw new IllegalArgumentException("You cannot create task for different supervisorID");
		}
		
		Task task = null;

		try {
			
			//menampung role dan username dari user yang sedang login tersebut
			String role = Log.getInstance().getCurrentUser().getRole();
			String uname = Log.getInstance().getCurrentUser().getUsername();
			String message;
			
			//melakukan pengecekan role
			if(role.equalsIgnoreCase("Supervisor")) {
				//membuat task untuk worker
				task = Task.create(supervisorID, workerID, title, note);
				task.save();
				message = uname + " has assigned you a new task \"" + title + "\"";
				//mengirimkan notifikasi kepada worker tentang task baru
				NotificationController.getInstance().createNotification(workerID, message);
			}
			else {
				//membuat taskrequest untuk supervisor agar supervisor melakukan supervise kepada worker tersebut 
				TaskRequestHandler.getInstance().createTaskRequest(title, supervisorID, workerID, note);
				//mengirimkan notifikasi kepada supervisor tentang task request baru
				message = uname +" has requested you to supervise a new task \"" + title + "\"";
				NotificationController.getInstance().createNotification(supervisorID, message);
			}
		} catch (NoSuchObjectException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	
		return task;
	}
	
	
	//Validasi panjang note di form create task
	private static boolean validateNote(String note) {
		if(note.length()>=0 && note.length()<=50) {
			return true;
		}
		return false;
	}

	//Validasi panjang title di form create task
	private static boolean validateTitle(String title) {
		if(title.length()>=5 && title.length()<=20) {
			return true;
		}
		return false;
	}
	
	//Validasi apakah id yang diinput ada di dalam database atau tidak
	private static boolean validateExistID(UUID id) throws NoSuchObjectException{
		ArrayList<User> userList = getAllUser();
		for(User user: userList) {
			if(user.getId().equals(id) == true) {
				return true;
			}
		}
		
		return false;
	}
	
	//menampung seluruh list user
	public static ArrayList<User> getAllUser(){
		ArrayList<User> user = null;
		try {
			user = User.getAll();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Get All User Failed "+e.getMessage());
			return null;
		}
		return user;
	}

	//Validasi apakah id yang diinput sama dengan id user yang sedang login atau tidak
	private static boolean validateID(UUID id){
		
		UUID roleid = null;
		try {
			roleid = Log.getInstance().getCurrentUser().getId();
		} catch (NoSuchObjectException e) {
			e.printStackTrace();
		}
		
		if(roleid.equals(id)){
			return true;
		}
		
		
		return false;
	}
	
	//GET ALL TASK
	public ArrayList<Task> getAllTask() throws NoSuchObjectException, SQLException{
        ArrayList<Task> task = null;
        
        User currentUser = Log.getInstance().getCurrentUser();
        task = Task.getAll(currentUser.getId());

        return task;
    }
	
	//GET TASK
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
	
    //SEARCH TASK
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
	
	//SUBMIT TASK
	public Task submitTask(UUID taskID) throws NoSuchObjectException{
		Task task;
		task = Task.get(taskID);
		
		//menampung username dari user yang sedang login tersebut
		String uname = Log.getInstance().getCurrentUser().getUsername();
	
		try {
			//memperbaharui status task yang akan disubmit, status menjadi sudah tersubmit
			task = new Task(taskID, task.getSupervisorID(), task.getWorkerID(), task.getTitle(), task.getRevisionCount(), task.getScore(), 1, task.getApproveAt(), task.getNote());
			task.update();
			
			//mengirimkan notifikasi kepada supervisor tersebut bahwa task tersebut telah disubmit
			String message = uname + " has submitted \"" + task.getTitle() + "\"";
			NotificationController.getInstance().createNotification(task.getSupervisorID(), message);
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	
		return task;
	}
	
	public Task updateTask(UUID taskID, UUID supervisorID, UUID workerID, String title,Integer score, String note) throws NoSuchObjectException, IllegalArgumentException{
		//validasi form update task
		if(validateTitle(title) == false) {//validasi panjang title
			throw new IllegalArgumentException("Title length must be between 5-20 characters length!");
		} else if(validateNote(note) == false) {//validasi panjang note
			throw new IllegalArgumentException("Note length must be between 0-50 characters length!");
		} else if(validateScore(score) == false) {//validasi score
			throw new IllegalArgumentException("Score must be between 1-100!");
		} 
		//menampung username dari user yang sedang login tersebut
		String uname = Log.getInstance().getCurrentUser().getUsername();
		 
		//memperbaharui title, note, score yang di input
		Task task = new Task(taskID, supervisorID, workerID, title, Task.get(taskID).getRevisionCount(), score, Task.get(taskID).getIsSubmitted(), Task.get(taskID).getApproveAt(), note);
		
		String message = uname + " has updated information on task \"" + task.getTitle() + "\"";
		
		task.update();
		
		//mengirimkan notifikasi kepada supervisor dan worker tentang task yang di update
		NotificationController.getInstance().createNotification(supervisorID, message);
		NotificationController.getInstance().createNotification(workerID, message);
		
		return task;
	}
	
	//validasi range score task
	private static boolean validateScore(Integer score) {

		if(score<1 || score>100) {
			System.out.println("masuk");
			return false;
		}
		
		return true;
		
	}

	public void deleteTask(UUID taskID) throws NoSuchObjectException{
		//menampung username dari user yang sedang login tersebut
		String uname = Log.getInstance().getCurrentUser().getUsername();
		Task task;
		try {
			//mendelete task yang di delete berdasarkan taskID
			task = Task.get(taskID);
			task.delete();
				
			String message = uname + " has deleted task \"" + task.getTitle() + "\"";
			//mengirimkan notifikasi kepada supervisor dan worker tentang task yang di delete
			NotificationController.getInstance().createNotification(task.getSupervisorID(), message);
			NotificationController.getInstance().createNotification(task.getWorkerID(), message);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
	}
	
	public Task approveTask(UUID taskID, Integer score) throws NoSuchObjectException{
		//mengecek score yang diinput user
		if(validateScore(score) == false) {
			throw new IllegalArgumentException("Score must be between 1-100!");
		} 
		
		//mengambil waktu saat klik button approve task
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		Task task;
		task = Task.get(taskID);
		
		//menampung role dan username dari user yang sedang login tersebut
		String role = Log.getInstance().getCurrentUser().getRole();
		String uname = Log.getInstance().getCurrentUser().getUsername();
		
		//melakukan pengecekan role bahwa hanya supervisor saja yang dapat approve task
		if(role.equalsIgnoreCase("Supervisor")){
			try {
				//mengupdate waktu approve dan score
				task = new Task(taskID, task.getSupervisorID(),  task.getWorkerID(), task.getTitle(), task.getRevisionCount(), score, task.getIsSubmitted(), timestamp, task.getNote());
				task.update();
				
				//mengirimkan notifikasi kepada worker tersebut tentang task yang di approve oleh supervisor
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
		
		//menampung username dari user yang sedang login tersebut
		String uname = Log.getInstance().getCurrentUser().getUsername();
		
		
		try {
			//mengupdate task revision count + 1 dan mengubah tanda submit menjadi belum disubmit
			task = new Task(taskID, task.getSupervisorID(), task.getWorkerID(), task.getTitle(), task.getRevisionCount()+1, task.getScore(), 0, task.getApproveAt(), task.getNote());
			task.update();
			
			//mengirimkan notifikasi kepada worker tersebut tentang task yang harus direvisi
			String message = uname + " has requested you a revision on task \"" + task.getTitle() + "\"";
			NotificationController.getInstance().createNotification(task.getWorkerID(), message);
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
		
		return task;
	}
	
	
	//getter untuk mengambil id task yang disimpan
	public static String getIdTask() {
		return idTask;
	}
	
	//setter untuk id task
	public static void setIdTask(String idTask) {
		TaskHandler.idTask = idTask;
	}

	


}









