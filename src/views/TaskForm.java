package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import controllers.TaskController;
import controllers.UserController;

public class TaskForm extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public TaskForm(){
		this.getContentPane().setBackground(new Color(70,130,180));
		this.setBounds(100, 100, 352, 449);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		
		Label titleTaskForm = new Label("CREATE NEW TASK FORM");
		titleTaskForm.setBackground(new Color(70, 130, 180));
		titleTaskForm.setFont(new Font("Snap ITC", Font.BOLD, 22));
		titleTaskForm.setForeground(new Color(255, 255, 255));
		titleTaskForm.setBounds(20, 45, 300, 22);
		this.getContentPane().add(titleTaskForm);
		
		Label title = new Label("Title");
		title.setForeground(new Color(255, 255, 255));
		title.setFont(new Font("Dialog", Font.BOLD, 12));
		title.setBounds(27, 109, 80, 22);
		this.getContentPane().add(title);
		
		Label supervisorID = new Label("Supervisor ID");
		supervisorID.setForeground(new Color(255, 255, 255));
		supervisorID.setFont(new Font("Dialog", Font.BOLD, 12));
		supervisorID.setBounds(27, 139, 80, 22);
		this.getContentPane().add(supervisorID);
		
		Label workerID = new Label("Worker ID");
		workerID.setForeground(new Color(255, 255, 255));
		workerID.setFont(new Font("Dialog", Font.BOLD, 12));
		workerID.setBounds(27, 169, 80, 22);
		this.getContentPane().add(workerID);
		
		Label note = new Label("Note");
		note.setForeground(new Color(255, 255, 255));
		note.setFont(new Font("Dialog", Font.BOLD, 12));
		note.setBounds(27, 199, 80, 22);
		this.getContentPane().add(note);
		
		
		//user field
		JTextArea titleField = new JTextArea();
		titleField.setForeground(Color.BLACK);
		titleField.setWrapStyleWord(true);
		titleField.setLineWrap(true);
		titleField.setBounds(120, 109, 174, 20);
		this.getContentPane().add(titleField);
		
		JTextArea supervisorIDField = new JTextArea();
		supervisorIDField.setForeground(Color.BLACK);
		supervisorIDField.setWrapStyleWord(true);
		supervisorIDField.setLineWrap(true);
		supervisorIDField.setBounds(120, 139, 174, 20);
		this.getContentPane().add(supervisorIDField);
		
		JTextArea workerIDField = new JTextArea();
		workerIDField.setForeground(Color.BLACK);
		workerIDField.setWrapStyleWord(true);
		workerIDField.setLineWrap(true);
		workerIDField.setBounds(120, 169, 174, 20);
		this.getContentPane().add(workerIDField);
		
		JTextArea noteField = new JTextArea();
		noteField.setForeground(Color.BLACK);
		noteField.setWrapStyleWord(true);
		noteField.setLineWrap(true);
		noteField.setBounds(120, 199, 174, 100);
		this.getContentPane().add(noteField);
		
		
		//button
		
		//back
		JButton backButton = new JButton("Back");
		backButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		backButton.setBackground(new Color(255, 255, 255));
		backButton.setBounds(65, 325, 85, 35);
		this.getContentPane().add(backButton);
		
		//create
		JButton createTaskForm = new JButton("Create Task");
		createTaskForm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(titleField.getText().isEmpty() || supervisorIDField.getText().isEmpty() || 
						workerIDField.getText().isEmpty() || noteField.getText().isEmpty()) {
					
					JOptionPane.showMessageDialog(null, "Please Complete All Data");
				
				}
				else {
					TaskController.createTask(titleField.getText(), supervisorIDField.getText(), workerIDField.getText(), noteField.getText());
					
					titleField.setText("");
					supervisorIDField.setText("");
					workerIDField.setText("");
					noteField.setText("");
				}
			}
		});
		createTaskForm.setFont(new Font("Tahoma", Font.BOLD, 14));
		createTaskForm.setBackground(new Color(255, 255, 255));
		createTaskForm.setBounds(163, 325, 120, 35);
		this.getContentPane().add(createTaskForm);
		
		
	}

	

}
