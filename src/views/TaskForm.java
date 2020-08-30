package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import controllers.TaskHandler;

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
		this.setBackground(new Color(70, 130, 180));
		this.setSize(618, 406);
		this.setLayout(null);

		JLabel titleTaskForm = new JLabel("CREATE NEW TASK FORM");
		titleTaskForm.setForeground(new Color(70, 130, 180));
		titleTaskForm.setFont(new Font("Snap ITC", Font.BOLD, 22));
		titleTaskForm.setForeground(new Color(255, 255, 255));
		titleTaskForm.setBounds(20, 45, 300, 22);
		add(titleTaskForm);

		JLabel title = new JLabel("Title");
		title.setForeground(new Color(255, 255, 255));
		title.setFont(new Font("Dialog", Font.BOLD, 12));
		title.setBounds(27, 109, 80, 22);
		add(title);

		JLabel supervisorID = new JLabel("Supervisor ID");
		supervisorID.setForeground(new Color(255, 255, 255));
		supervisorID.setFont(new Font("Dialog", Font.BOLD, 12));
		supervisorID.setBounds(27, 139, 80, 22);
		add(supervisorID);

		JLabel workerID = new JLabel("Worker ID");
		workerID.setForeground(new Color(255, 255, 255));
		workerID.setFont(new Font("Dialog", Font.BOLD, 12));
		workerID.setBounds(27, 169, 80, 22);
		add(workerID);

		JLabel note = new JLabel("Note");
		note.setForeground(new Color(255, 255, 255));
		note.setFont(new Font("Dialog", Font.BOLD, 12));
		note.setBounds(27, 199, 80, 22);
		add(note);

		// user field
		titleField = new JTextArea();
		titleField.setForeground(Color.BLACK);
		titleField.setWrapStyleWord(true);
		titleField.setLineWrap(true);
		titleField.setBounds(120, 109, 174, 20);
		add(titleField);

		supervisorIDField = new JTextArea();
		supervisorIDField.setForeground(Color.BLACK);
		supervisorIDField.setWrapStyleWord(true);
		supervisorIDField.setLineWrap(true);
		supervisorIDField.setBounds(120, 139, 174, 20);
		add(supervisorIDField);

		workerIDField = new JTextArea();
		workerIDField.setForeground(Color.BLACK);
		workerIDField.setWrapStyleWord(true);
		workerIDField.setLineWrap(true);
		workerIDField.setBounds(120, 169, 174, 20);
		add(workerIDField);

		noteField = new JTextArea();
		noteField.setForeground(Color.BLACK);
		noteField.setWrapStyleWord(true);
		noteField.setLineWrap(true);
		noteField.setBounds(120, 199, 174, 100);
		add(noteField);

		// button

		// back
		backButton = new JButton("Back");
		backButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		backButton.setBackground(new Color(255, 255, 255));
		backButton.setBounds(65, 325, 85, 35);
		add(backButton);

		// create
		createTaskForm = new JButton("Create Task");
		createTaskForm.setFont(new Font("Tahoma", Font.BOLD, 14));
		createTaskForm.setBackground(new Color(255, 255, 255));
		createTaskForm.setBounds(163, 325, 120, 35);
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