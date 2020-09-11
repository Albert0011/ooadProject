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
	
	
	public MainController() {
		
		
	}
	
	public static MainController getInstance() {
		if(mainController == null) {
			mainController = new MainController();
			
		}
		return mainController;
	}
	
	public LoginDisplay displayLogin() {
		loginDisplay = new LoginDisplay();
		
		loginDisplay.getLoginButton().addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					UserController.getInstance().getUserBy(loginDisplay.getUnameField().getText(), loginDisplay.getPassField().getText());
				} catch (NoSuchObjectException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}		
			}
		});
		
		return loginDisplay;
		
	}
	
	
	public AdminHomepage displayAdminHomepage() {
		adminHomepage = new AdminHomepage();
		adminHomepage.refreshContent(UserController.getInstance().openAllUserDisplay());
		adminHomepage.btnViewAllUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adminHomepage.refreshContent(UserController.getInstance().openAllUserDisplay());
			}
		});
		
		adminHomepage.btnCreateUser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				adminHomepage.refreshContent(UserController.getInstance().openCreateUserDisplay());
			}
		});
		
		adminHomepage.getLogoutBtn().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				adminHomepage.dispose();
				displayLogin();
				
			}
		});
		
		return adminHomepage;
	}
	
	
	public SupervisorHomepage displaySupervisorHomepage() throws NoSuchObjectException {
		supervisorHomepage = new SupervisorHomepage();
		
		supervisorHomepage.refreshContent(UserController.getInstance().openUserProfileDisplay());
		
		supervisorHomepage.getProfileBtn().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					supervisorHomepage.refreshContent(UserController.getInstance().openUserProfileDisplay());
				} catch (NoSuchObjectException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		supervisorHomepage.getTaskBtn().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					supervisorHomepage.refreshContent(TaskHandler.getInstance().openUserTaskDisplay());
				} catch (NoSuchObjectException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
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
		
		supervisorHomepage.getNotifBtn().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					supervisorHomepage.refreshContent(NotificationController.openNotificationDisplay());
				} catch (NoSuchObjectException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		supervisorHomepage.getLogoutBtn().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				supervisorHomepage.dispose();
				displayLogin();
				
			}
		});
		
		return supervisorHomepage;
	}
	
	public WorkerHomepage displayWorkerHomepage() throws NoSuchObjectException {
		workerHomepage = new WorkerHomepage();
		
		workerHomepage.refreshContent(UserController.getInstance().openUserProfileDisplay());
		
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
		
		workerHomepage.getNotifBtn().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					workerHomepage.refreshContent(NotificationController.openNotificationDisplay());
				} catch (NoSuchObjectException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		
		workerHomepage.getLogoutBtn().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				workerHomepage.dispose();
				displayLogin();
				
			}
		});
		
		return workerHomepage;
	}
	
	public void disposeLoginFrame() {
		loginDisplay.dispose();
	}

	
	
	public void addPanel(JPanel panel) {
		mainDisplay = new MainDisplay();
		mainDisplay.add(panel);
		mainDisplay.revalidate();
		mainDisplay.repaint();
	}
	
	public void refreshContent(JPanel panel) throws NoSuchObjectException {
		User user = Log.getInstance().getCurrentUser();
//		System.out.println("masuk");
		if(user.getRole().equalsIgnoreCase("Admin")) {
			adminHomepage.refreshContent(panel);			
//			System.out.println("admin homepage");
		} else if(user.getRole().equalsIgnoreCase("Worker")) {
			workerHomepage.refreshContent(panel);
//			System.out.println("worker homepage");
		} else if(user.getRole().equalsIgnoreCase("Supervisor")) {
			supervisorHomepage.refreshContent(panel);
//			System.out.println("superv homepage");
		} else {
//			System.out.println("gagal cok");
		}
		
	}
}