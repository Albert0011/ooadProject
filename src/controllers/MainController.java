package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NoSuchObjectException;
import java.sql.SQLException;

import javax.swing.JPanel;
import views.AdminHomepage;
import views.LoginDisplay;
import views.MainDisplay;
import views.ProfileDisplay;
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
				UserController.getInstance().getUserBy(loginDisplay.getUnameField().getText(), loginDisplay.getPassField().getText());		
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
		return adminHomepage;
	}
	
	
	public SupervisorHomepage displaySupervisorHomepage() {
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
<<<<<<< HEAD
				try {
					supervisorHomepage.refreshContent(TaskHandler.getInstance().openAllTaskDisplay());
				} catch (NoSuchObjectException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
=======
				
					try {
						supervisorHomepage.refreshContent(TaskHandler.getInstance().openAllTaskDisplay());
					} catch (NoSuchObjectException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
				
>>>>>>> refs/remotes/origin/master
			}
		});
		
		supervisorHomepage.getTaskReqBtn().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
<<<<<<< HEAD
				try {
					supervisorHomepage.refreshContent(TaskRequestHandler.getInstance().openAllTaskRequestDisplay());
				} catch (NoSuchObjectException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
=======
				// TODO Auto-generated method stub
				try {
					supervisorHomepage.refreshContent(TaskRequestHandler.getInstance().openAllTaskRequestDisplay());
				} catch (NoSuchObjectException | SQLException e1) {
>>>>>>> refs/remotes/origin/master
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
		
		return supervisorHomepage;
	}
	
	public WorkerHomepage displayWorkerHomepage() {
		workerHomepage = new WorkerHomepage();
		
		workerHomepage.refreshContent(UserController.getInstance().openUserProfileDisplay());
		
		workerHomepage.getProfileBtn().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
<<<<<<< HEAD
				workerHomepage.refreshContent(UserController.getInstance().openUserProfileDisplay());
=======
				try {
					workerHomepage.refreshContent(UserController.getInstance().openProfileDisplay());
				} catch (NoSuchObjectException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//supervisorHomepage.refreshContent(UserController.getInstance().openChangePasswordForm());
				//supervisorHomepage.refreshContent(UserController.getInstance().openUpdateProfileForm());
>>>>>>> refs/remotes/origin/master
			}
		});
		
		workerHomepage.getTaskBtn().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
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

	
	public void supervisorRefreshContent(JPanel panel) {
		supervisorHomepage.refreshContent(panel);
	}
	
	public void workerRefreshContent(JPanel panel) {
		workerHomepage.refreshContent(panel);
	}
	
	
	public void refreshContent(JPanel panel) {
		adminHomepage.refreshContent(panel);
	}
}