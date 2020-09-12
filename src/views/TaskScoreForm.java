package views;

import java.awt.Color;


import javax.swing.JLabel;
import java.awt.Font;


import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingConstants;

public class TaskScoreForm extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField taskIDField;
	private JTextField workerIDField;
	private JTextField supervisorIDField;
	private JTextField titleField;
	private JTextField scoreField;
	private JButton btnSubmit;
	private JButton btnCancel;
	
	public TaskScoreForm() {
		this.setBackground(new Color(240, 248, 255));
		this.setSize(615, 364);
		this.setLocation(0, 0);
		getContentPane().setLayout(null);
		this.setBounds(100, 100, 650, 400);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		//title
		JLabel lblTaskID = new JLabel("taskID");
		lblTaskID.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTaskID.setToolTipText("");
		lblTaskID.setBounds(83, 78, 61, 36);
		getContentPane().add(lblTaskID);
		
		JLabel lblWorkerID = new JLabel("workerID");
		lblWorkerID.setToolTipText("");
		lblWorkerID.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblWorkerID.setBounds(82, 113, 84, 30);
		getContentPane().add(lblWorkerID);
		
		JLabel lblSupervisorID = new JLabel("supervisorID");
		lblSupervisorID.setToolTipText("");
		lblSupervisorID.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSupervisorID.setBounds(83, 148, 105, 30);
		getContentPane().add(lblSupervisorID);
		
		JLabel lblScore = new JLabel("score");
		lblScore.setToolTipText("");
		lblScore.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblScore.setBounds(83, 218, 61, 23);
		getContentPane().add(lblScore);
		
		JLabel lblScoreForm = new JLabel("Submit Score");
		lblScoreForm.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblScoreForm.setBounds(48, 23, 168, 41);
		getContentPane().add(lblScoreForm);
		
		JLabel lblTitle = new JLabel("title");
		lblTitle.setToolTipText("");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTitle.setBounds(83, 183, 84, 23);
		getContentPane().add(lblTitle);
		
		
		//button
		btnSubmit = new JButton("Submit");
		btnSubmit.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSubmit.setBounds(470, 284, 89, 23);
		getContentPane().add(btnSubmit);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCancel.setBounds(334, 284, 89, 23);
		getContentPane().add(btnCancel);
		getContentPane().setLayout(null);
		setBackground(Color.CYAN);
		getContentPane().setLayout(null);
		

		
		taskIDField = new JTextField();
		taskIDField.setEditable(false);
		taskIDField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		taskIDField.setColumns(10);
		taskIDField.setBounds(224, 88, 335, 21);
		getContentPane().add(taskIDField);
		
		workerIDField = new JTextField();
		workerIDField.setEditable(false);
		workerIDField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		workerIDField.setColumns(10);
		workerIDField.setBounds(223, 121, 335, 21);
		getContentPane().add(workerIDField);
		
		supervisorIDField = new JTextField();
		supervisorIDField.setEditable(false);
		supervisorIDField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		supervisorIDField.setColumns(10);
		supervisorIDField.setBounds(224, 154, 335, 21);
		getContentPane().add(supervisorIDField);
		
		titleField = new JTextField();
		titleField.setEditable(false);
		titleField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		titleField.setColumns(10);
		titleField.setBounds(224, 183, 335, 21);
		getContentPane().add(titleField);
		
		scoreField = new JTextField();
		scoreField.setHorizontalAlignment(SwingConstants.CENTER);
		scoreField.setToolTipText("");
		scoreField.setFont(new Font("Tahoma", Font.PLAIN, 30));
		scoreField.setColumns(10);
		scoreField.setBounds(224, 218, 84, 54);
		getContentPane().add(scoreField);
		
	
		this.setVisible(true);
	}
	
//	public void refreshContent(JPanel panel) {
//		mainPanel.removeAll();
//		mainPanel.repaint();
//		mainPanel.revalidate();
//		
//		mainPanel.add(panel);
//		mainPanel.repaint();
//		mainPanel.revalidate();
//	}
	

	public JTextField getTaskIDField() {
		return taskIDField;
	}


	public void setTaskIDField(String uuid) {
		this.taskIDField.setText(uuid);;
	}


	public JTextField getWorkerIDField() {
		return workerIDField;
	}


	public void setWorkerIDField(String workerID) {
		this.workerIDField.setText(workerID);;
	}


	public JTextField getSupervisorIDField() {
		return supervisorIDField;
	}


	public void setSupervisorIDField(String supervisorID) {
		this.supervisorIDField.setText(supervisorID);;
	}


	public JTextField getTitleField() {
		return titleField;
	}


	public void setTitleField(String title) {
		this.titleField.setText(title);
	}


	public JTextField getScoreField() {
		return scoreField;
	}


	public void setScoreField(String score) {
		this.scoreField.setText(score);;
	}

	public JButton getBtnSubmit() {
		return btnSubmit;
	}

	public void setBtnSubmit(JButton btnSubmit) {
		this.btnSubmit = btnSubmit;
	}

	public JButton getBtnCancel() {
		return btnCancel;
	}

	public void setBtnCancel(JButton btnCancel) {
		this.btnCancel = btnCancel;
	}
}