package views;

import java.awt.Color;
import java.awt.Font;


import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;


public class TaskForm extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea titleField;
	private JTextArea supervisorIDField;
	private JTextArea workerIDField;
	private JTextArea noteField;
	private JButton backButton;
	private JButton createTaskForm;

	public TaskForm() {
		this.setBackground(Color.CYAN);
		this.setSize(628, 416);
		this.setLayout(null);

		JLabel titleTaskForm = new JLabel("Create New Task Form");
		titleTaskForm.setHorizontalAlignment(SwingConstants.CENTER);
		titleTaskForm.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
		titleTaskForm.setBounds(41, 11, 561, 70);
		add(titleTaskForm);
		
		JLabel title = new JLabel("Title");
		title.setBackground(Color.BLACK);
		title.setForeground(Color.BLACK);
		title.setFont(new Font("Dialog", Font.BOLD, 20));
		title.setBounds(82, 92, 59, 35);
		add(title);

		JLabel workerID = new JLabel("Worker ID");
		workerID.setBackground(Color.WHITE);
		workerID.setForeground(Color.BLACK);
		workerID.setFont(new Font("Dialog", Font.BOLD, 20));
		workerID.setBounds(82, 179, 140, 32);
		add(workerID);

		JLabel supervisorID = new JLabel("Supervisor ID");
		supervisorID.setForeground(Color.BLACK);
		supervisorID.setFont(new Font("Dialog", Font.BOLD, 20));
		supervisorID.setBounds(82, 133, 141, 35);
		add(supervisorID);

		JLabel note = new JLabel("Note");
		note.setForeground(Color.BLACK);
		note.setFont(new Font("Dialog", Font.BOLD, 20));
		note.setBounds(82, 222, 59, 26);
		add(note);

		// user field
		titleField = new JTextArea();
		titleField.setForeground(Color.BLACK);
		titleField.setWrapStyleWord(true);
		titleField.setLineWrap(true);
		titleField.setBounds(266, 92, 277, 35);
		add(titleField);

		supervisorIDField = new JTextArea();
		supervisorIDField.setForeground(Color.BLACK);
		supervisorIDField.setWrapStyleWord(true);
		supervisorIDField.setLineWrap(true);
		supervisorIDField.setBounds(266, 133, 277, 35);
		add(supervisorIDField);

		workerIDField = new JTextArea();
		workerIDField.setForeground(Color.BLACK);
		workerIDField.setWrapStyleWord(true);
		workerIDField.setLineWrap(true);
		workerIDField.setBounds(266, 180, 277, 31);
		add(workerIDField);

		noteField = new JTextArea();
		noteField.setForeground(Color.BLACK);
		noteField.setWrapStyleWord(true);
		noteField.setLineWrap(true);
		noteField.setBounds(266, 220, 277, 100);
		add(noteField);

		// button

		// back
		backButton = new JButton("Back");
		backButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		backButton.setBackground(new Color(255, 255, 255));
		backButton.setBounds(266, 343, 85, 35);
		add(backButton);

		// create
		createTaskForm = new JButton("Create Task");
		createTaskForm.setFont(new Font("Tahoma", Font.BOLD, 14));
		createTaskForm.setBackground(new Color(255, 255, 255));
		createTaskForm.setBounds(423, 343, 120, 35);
		add(createTaskForm);

		this.setVisible(true);
	}

	public JTextArea getTitleField() {
		return titleField;
	}

	public void setTitleField(JTextArea titleField) {
		this.titleField = titleField;
	}

	public JTextArea getSupervisorIDField() {
		return supervisorIDField;
	}

	public void setSupervisorIDField(JTextArea supervisorIDField) {
		this.supervisorIDField = supervisorIDField;
	}

	public JTextArea getWorkerIDField() {
		return workerIDField;
	}

	public void setWorkerIDField(JTextArea workerIDField) {
		this.workerIDField = workerIDField;
	}

	public JTextArea getNoteField() {
		return noteField;
	}

	public void setNoteField(JTextArea noteField) {
		this.noteField = noteField;
	}

	public JButton getBackButton() {
		return backButton;
	}

	public void setBackButton(JButton backButton) {
		this.backButton = backButton;
	}

	public JButton getCreateTaskForm() {
		return createTaskForm;
	}

	public void setCreateTaskForm(JButton createTaskForm) {
		this.createTaskForm = createTaskForm;
	}

}