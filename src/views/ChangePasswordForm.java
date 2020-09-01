package views;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;

public class ChangePasswordForm extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPasswordField oldPassField;
	private JPasswordField newPassField;
	private static JButton changePassBtn;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public ChangePasswordForm() {
		this.setSize(628, 416);
		//this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(new BorderLayout(0, 0));
		
		JPanel changePassPanel = new JPanel();
		changePassPanel.setBackground(new Color(250, 235, 215));
		changePassPanel.setLayout(null);
		
		JLabel changePassTitle = new JLabel("Change Password Form");
		changePassTitle.setBounds(165, 24, 272, 29);
		changePassTitle.setForeground(new Color(0, 0, 0));
		changePassTitle.setFont(new Font("Verdana", Font.BOLD, 20));
		changePassPanel.add(changePassTitle);
		
		JLabel oldPassLabel = new JLabel("Old Password");
		oldPassLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		oldPassLabel.setBounds(129, 123, 94, 14);;
		changePassPanel.add(oldPassLabel);
		
		JLabel newPassLabel = new JLabel("New Password");
		newPassLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		newPassLabel.setBounds(129, 197, 105, 14);
		changePassPanel.add(newPassLabel);
		
		oldPassField = new JPasswordField();
		oldPassField.setBounds(260, 121, 216, 20);
		changePassPanel.add(oldPassField);
		oldPassField.setColumns(10);
		
		newPassField = new JPasswordField();
		newPassField.setBounds(260, 195, 217, 20);
		changePassPanel.add(newPassField);
		newPassField.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("8-12 characters");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblNewLabel_3.setBounds(393, 142, 83, 14);
		changePassPanel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("8-12 characters");
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblNewLabel_3_1.setBounds(393, 216, 83, 14);
		changePassPanel.add(lblNewLabel_3_1);
		
	
		
		changePassBtn = new JButton("Change Password");
		changePassBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		changePassBtn.setBounds(226, 286, 147, 35);
		changePassPanel.add(changePassBtn);
		
		this.add(changePassPanel);
		this.setVisible(true);
	}

	public JPasswordField getOldPassField() {
		return oldPassField;
	}

	public void setOldPassField(JPasswordField oldPassField) {
		this.oldPassField = oldPassField;
	}

	public JPasswordField getNewPassField() {
		return newPassField;
	}

	public void setNewPassField(JPasswordField newPassField) {
		this.newPassField = newPassField;
	}

	public JButton getChangePassBtn() {
		return changePassBtn;
	}

	public void setChangePassBtn(JButton changePassBtn) {
		this.changePassBtn = changePassBtn;
	}
	
	
}
