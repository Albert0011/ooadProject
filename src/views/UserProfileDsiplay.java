package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class UserProfileDsiplay extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel topMenuPanel;
	private JButton viewProfileBtn;
	private JButton changePassBtn;
	private JButton updateProfileBtn;
	private JPanel mainPanel;
	/**
	 * Create the panel.
	 */
	public UserProfileDsiplay() {
		this.setBackground(Color.white);
		this.setSize(628, 416);
		this.setLayout(null);
		
		mainPanel = new JPanel();
		mainPanel.setBackground(new Color(255, 255, 224));
		mainPanel.setBounds(0, 40, 628, 376);
		this.add(mainPanel);
		mainPanel.setLayout(null);
		
		
		topMenuPanel = new JPanel();
		topMenuPanel.setBackground(new Color(255, 255, 224));
		topMenuPanel.setBounds(0, 0, 628, 40);
		this.add(topMenuPanel);
		topMenuPanel.setLayout(new GridLayout(0, 3, 0, 0));
		
		viewProfileBtn = new JButton("View Profile");
		viewProfileBtn.setForeground(new Color(255, 255, 255));
		viewProfileBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
		viewProfileBtn.setBackground(new Color(75, 0, 130));
		topMenuPanel.add(viewProfileBtn);
		
		changePassBtn = new JButton("Change Password");
		changePassBtn.setForeground(new Color(255, 255, 255));
		changePassBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
		changePassBtn.setBackground(new Color(75, 0, 130));
		topMenuPanel.add(changePassBtn);
		
		updateProfileBtn = new JButton("Update Profile");
		updateProfileBtn.setBackground(new Color(75, 0, 130));
		updateProfileBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
		updateProfileBtn.setForeground(new Color(255, 255, 255));
		topMenuPanel.add(updateProfileBtn);
		
		
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
	public JButton getViewProfileBtn() {
		return viewProfileBtn;
	}
	public void setViewProfileBtn(JButton viewProfileBtn) {
		this.viewProfileBtn = viewProfileBtn;
	}
	public JButton getChangePassBtn() {
		return changePassBtn;
	}
	public void setChangePassBtn(JButton changePassBtn) {
		this.changePassBtn = changePassBtn;
	}
	public JButton getUpdateProfileBtn() {
		return updateProfileBtn;
	}
	public void setUpdateProfileBtn(JButton updateProfileBtn) {
		this.updateProfileBtn = updateProfileBtn;
	}
	public JPanel getMainPanel() {
		return mainPanel;
	}
	public void setMainPanel(JPanel mainPanel) {
		this.mainPanel.add(mainPanel);
	}
	
	
}
