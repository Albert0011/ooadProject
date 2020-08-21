package controllers;

import javax.swing.JFrame;
import javax.swing.JPanel;

import views.MainDisplay;

public class MainController {

	private static MainController mainController;
	private static MainDisplay mainDisplay;
	
	public MainController() {
		mainDisplay = new MainDisplay();
	}
	
	public static MainController getInstance() {
		if(mainController == null) {
			mainController = new MainController();
		}
		return mainController;
	}
	
	
	
	public void addPanel(JPanel panel) {
		mainDisplay.add(panel);
		mainDisplay.revalidate();
		mainDisplay.repaint();
	}
	
	
	public static final MainDisplay getMainDisplay() {
		return mainDisplay;
	}
	
}
