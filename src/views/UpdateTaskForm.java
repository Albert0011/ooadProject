
package views;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

public class UpdateTaskForm extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField taskIDField;
	private JTextField workerIDField;
	private JTextField supervisorIDField;
	private JTextField titleIDField;
	private JTextField scoreField;
	private JTextField noteField;

	
	public UpdateTaskForm() {
		this.setBackground(new Color(255, 255, 224));
		this.setSize(628, 376);
		this.setLocation(0, 0);
		this.setLayout(null);
		
		
		//title
		JLabel lblTaskID = new JLabel("taskID");
		lblTaskID.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTaskID.setToolTipText("");
		lblTaskID.setBounds(83, 78, 61, 36);
		add(lblTaskID);
		
		JLabel lblWorkerID = new JLabel("workerID");
		lblWorkerID.setToolTipText("");
		lblWorkerID.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblWorkerID.setBounds(82, 113, 84, 30);
		add(lblWorkerID);
		
		JLabel lblSupervisorID = new JLabel("supervisorID");
		lblSupervisorID.setToolTipText("");
		lblSupervisorID.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSupervisorID.setBounds(83, 148, 105, 30);
		add(lblSupervisorID);
		
		JLabel lblScore = new JLabel("score");
		lblScore.setToolTipText("");
		lblScore.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblScore.setBounds(83, 218, 61, 23);
		add(lblScore);
		
		JLabel lblNote = new JLabel("note");
		lblNote.setToolTipText("");
		lblNote.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNote.setBounds(83, 253, 53, 23);
		add(lblNote);
		
		JLabel lblUpdateTask = new JLabel("Update Task");
		lblUpdateTask.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblUpdateTask.setBounds(48, 23, 168, 41);
		add(lblUpdateTask);
		
		JLabel lblTitle = new JLabel("title");
		lblTitle.setToolTipText("");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTitle.setBounds(83, 183, 84, 23);
		add(lblTitle);
		
		
		//button
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnUpdate.setBounds(470, 330, 89, 23);
		add(btnUpdate);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCancel.setBounds(334, 330, 89, 23);
		add(btnCancel);
		setLayout(null);
		setBackground(Color.CYAN);
		setLayout(null);
		

		
		taskIDField = new JTextField();
		taskIDField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		taskIDField.setColumns(10);
		taskIDField.setBounds(224, 88, 335, 21);
		add(taskIDField);
		
		workerIDField = new JTextField();
		workerIDField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		workerIDField.setColumns(10);
		workerIDField.setBounds(223, 121, 335, 21);
		add(workerIDField);
		
		supervisorIDField = new JTextField();
		supervisorIDField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		supervisorIDField.setColumns(10);
		supervisorIDField.setBounds(224, 154, 335, 21);
		add(supervisorIDField);
		
		titleIDField = new JTextField();
		titleIDField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		titleIDField.setColumns(10);
		titleIDField.setBounds(224, 183, 335, 21);
		add(titleIDField);
		
		scoreField = new JTextField();
		scoreField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		scoreField.setColumns(10);
		scoreField.setBounds(224, 218, 335, 21);
		add(scoreField);
		
		noteField = new JTextField();
		noteField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		noteField.setColumns(10);
		noteField.setBounds(224, 252, 335, 55);
		add(noteField);
		
	
		this.setVisible(true);
	}
	
	

	public JTextField getTaskIDField() {
		return taskIDField;
	}


	public void setTaskIDField(JTextField taskIDField) {
		this.taskIDField = taskIDField;
	}


	public JTextField getWorkerIDField() {
		return workerIDField;
	}


	public void setWorkerIDField(JTextField workerIDField) {
		this.workerIDField = workerIDField;
	}


	public JTextField getSupervisorIDField() {
		return supervisorIDField;
	}


	public void setSupervisorIDField(JTextField supervisorIDField) {
		this.supervisorIDField = supervisorIDField;
	}


	public JTextField getTitleIDField() {
		return titleIDField;
	}


	public void setTitleIDField(JTextField titleIDField) {
		this.titleIDField = titleIDField;
	}


	public JTextField getScoreField() {
		return scoreField;
	}


	public void setScoreField(JTextField scoreField) {
		this.scoreField = scoreField;
	}


	public JTextField getNoteField() {
		return noteField;
	}


	public void setNoteField(JTextField noteField) {
		this.noteField = noteField;
	}
}