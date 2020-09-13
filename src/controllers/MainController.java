package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NoSuchObjectException;
import java.sql.SQLException;

import javax.swing.JPanel;

import helpers.Log;
import models.User;
import views.AdminHomepage;
import views.LoginDisplay;
import views.MainDisplay;
import views.SupervisorHomepage;
import views.WorkerHomepage;

public class MainController {

	private static MainController mainController;
	private static MainDisplay mainDisplay;
	private static AdminHomepage adminHomepage;
	private static LoginDisplay loginDisplay;
	private static SupervisorHomepage supervisorHomepage;
	private static WorkerHomepage workerHomepage;
	
	//singleton
	public static MainController getInstance() {
		if(mainController == null) {
			mainController = new MainController();
		}
		return mainController;
	}
	
	//LOGIN DISPLAY
	public LoginDisplay displayLogin() {
		loginDisplay = new LoginDisplay();
		
		//LOGIN
		loginDisplay.getLoginButton().addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					UserController.getInstance().getUserBy(loginDisplay.getUnameField().getText(), loginDisplay.getPassField().getText());
				} catch (Exception e1) {
					e1.printStackTrace();
				}	
			}
		});
		
		return loginDisplay;
		
	}
	
	//ADMIN HOMEPAGE DISPLAY
	public AdminHomepage displayAdminHomepage() {
		adminHomepage = new AdminHomepage();
		adminHomepage.refreshContent(UserController.getInstance().openAllUserDisplay());
		
		//VIEW ALL USER
		adminHomepage.btnViewAllUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adminHomepage.refreshContent(UserController.getInstance().openAllUserDisplay());
			}
		});
		
		//CREATE USER
		adminHomepage.btnCreateUser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				adminHomepage.refreshContent(UserController.getInstance().openCreateUserDisplay());
			}
		});
		
		//LOG OUT
		adminHomepage.getLogoutBtn().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				adminHomepage.dispose();
				displayLogin();
				
			}
		});
		
		return adminHomepage;
	}
	
	//SUPERVISOR HOMEPAGE DISPLAY
	public SupervisorHomepage displaySupervisorHomepage() throws NoSuchObjectException {
		supervisorHomepage = new SupervisorHomepage();
		
		supervisorHomepage.refreshContent(UserController.getInstance().openUserProfileDisplay());
		
		//PROFILE
		supervisorHomepage.getProfileBtn().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					supervisorHomepage.refreshContent(UserController.getInstance().openUserProfileDisplay());
				} catch (NoSuchObjectException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		//TASK
		supervisorHomepage.getTaskBtn().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					supervisorHomepage.refreshContent(TaskHandler.getInstance().openUserTaskDisplay());
				} catch (NoSuchObjectException | SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		
		///TASK REQUEST
		supervisorHomepage.getTaskReqBtn().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					supervisorHomepage.refreshContent(TaskRequestHandler.getInstance().openAllTaskRequestDisplay());
				} catch (NoSuchObjectException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		//NOTIFICATION
		supervisorHomepage.getNotifBtn().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					supervisorHomepage.refreshContent(NotificationController.getInstance().openNotificationDisplay());
				} catch (NoSuchObjectException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		//LOG OUT
		supervisorHomepage.getLogoutBtn().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				supervisorHomepage.dispose();
				displayLogin();
				
			}
		});
		
		return supervisorHomepage;
	}
	
	//WORKER HOMEPAGE DISPLAY
	public WorkerHomepage displayWorkerHomepage() throws NoSuchObjectException {
		workerHomepage = new WorkerHomepage();
		
		workerHomepage.refreshContent(UserController.getInstance().openUserProfileDisplay());
		
		//PROFILE
		workerHomepage.getProfileBtn().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					workerHomepage.refreshContent(UserController.getInstance().openUserProfileDisplay());
				} catch (NoSuchObjectException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		//TASK
		workerHomepage.getTaskBtn().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					workerHomepage.refreshContent(TaskHandler.getInstance().openUserTaskDisplay());
				} catch (NoSuchObjectException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		//NOTIFICATION
		workerHomepage.getNotifBtn().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					workerHomepage.refreshContent(NotificationController.getInstance().openNotificationDisplay());
				} catch (NoSuchObjectException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		//LOG OUT
		workerHomepage.getLogoutBtn().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				workerHomepage.dispose();
				displayLogin();
				
			}
		});
		
		return workerHomepage;
	}
	
	//menutup login display
	public void disposeLoginFrame() {
		loginDisplay.dispose();
	}

	//add panel
	public void addPanel(JPanel panel) {
		mainDisplay = new MainDisplay();
		mainDisplay.add(panel);
		mainDisplay.revalidate();
		mainDisplay.repaint();
	}
	
	//refresh the panel
	public void refreshContent(JPanel panel) throws NoSuchObjectException {
		User user = Log.getInstance().getCurrentUser();
		if(user.getRole().equalsIgnoreCase("Admin")) {
			adminHomepage.refreshContent(panel);			
		} else if(user.getRole().equalsIgnoreCase("Worker")) {
			workerHomepage.refreshContent(panel);
		} else if(user.getRole().equalsIgnoreCase("Supervisor")) {
			supervisorHomepage.refreshContent(panel);
		} else {
		}
		
	}
}