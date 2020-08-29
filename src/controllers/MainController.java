package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import views.AdminHomepage;
import views.LoginDisplay;
import views.MainDisplay;

public class MainController {

	private static MainController mainController;
	private static MainDisplay mainDisplay;
	private static AdminHomepage adminHomepage;
	private static LoginDisplay loginDisplay;
	
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
				UserController.getInstance().getUserBy(loginDisplay.getUnameField().getText(), loginDisplay.getPassField().getText(), loginDisplay.getRoleChoose().getSelectedItem().toString());		
			}
		});
		
		return loginDisplay;
		
	}
	
	
	public AdminHomepage displayAdminHomepage() {
		adminHomepage = new AdminHomepage();
		
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
	
	public void disposeLoginFrame() {
		loginDisplay.dispose();
	}

	
	
	public void addPanel(JPanel panel) {
		mainDisplay = new MainDisplay();
		mainDisplay.add(panel);
		mainDisplay.revalidate();
		mainDisplay.repaint();
	}
	
	public void refreshContent(JPanel panel) {
		adminHomepage.refreshContent(panel);
	}
}
