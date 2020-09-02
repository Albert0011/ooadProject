
package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class UserTaskDisplay extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel topMenuPanel;
	private JButton viewAllTaskBtn;
	private JButton createTaskBtn;
	private JPanel mainPanel;
	/**
	 * Create the panel.
	 */
	public UserTaskDisplay() {
		this.setBackground(Color.LIGHT_GRAY);
		this.setSize(628, 416);
		this.setLayout(null);
		
		mainPanel = new JPanel();
		mainPanel.setBackground(new Color(255, 255, 224));
		mainPanel.setBounds(0, 40, 628, 376);
		add(mainPanel);
		mainPanel.setLayout(null);
		
		
		topMenuPanel = new JPanel();
		topMenuPanel.setBackground(new Color(255, 255, 224));
		topMenuPanel.setBounds(0, 0, 628, 40);
		add(topMenuPanel);
		topMenuPanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		viewAllTaskBtn = new JButton("View Tasks");
		topMenuPanel.add(viewAllTaskBtn);
		viewAllTaskBtn.setForeground(new Color(255, 255, 255));
		viewAllTaskBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
		viewAllTaskBtn.setBackground(new Color(75, 0, 130));
		
		createTaskBtn = new JButton("Create Task");
		topMenuPanel.add(createTaskBtn);
		createTaskBtn.setForeground(new Color(255, 255, 255));
		createTaskBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
		createTaskBtn.setBackground(new Color(75, 0, 130));
		
		
		this.setVisible(true);
	}
	
	
	public void refreshContent(JPanel panel) {
		mainPanel.removeAll();
		mainPanel.repaint();
		mainPanel.revalidate();
		
		mainPanel.add(panel);
		mainPanel.repaint();
		mainPanel.revalidate();
	}


	public JPanel getTopMenuPanel() {
		return topMenuPanel;
	}


	public void setTopMenuPanel(JPanel topMenuPanel) {
		this.topMenuPanel = topMenuPanel;
	}


	public JButton getViewAllTaskBtn() {
		return viewAllTaskBtn;
	}


	public void setViewAllTaskBtn(JButton viewAllTaskBtn) {
		this.viewAllTaskBtn = viewAllTaskBtn;
	}


	public JButton getCreateTaskBtn() {
		return createTaskBtn;
	}


	public void setCreateTaskBtn(JButton createTaskBtn) {
		this.createTaskBtn = createTaskBtn;
	}


	public JPanel getMainPanel() {
		return mainPanel;
	}


	public void setMainPanel(JPanel mainPanel) {
		this.mainPanel = mainPanel;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}

