
package views;


import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import models.TaskRequest;
import models.User;

import java.awt.Color;
import java.awt.Label;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.border.LineBorder;

public class AllTaskRequestDisplay extends JPanel {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable viewAllTable;
	private JButton btnRejectTaskRequest;
	private JButton btnAcceptTaskRequest;
	private JTextField taskIDField;
	private JTextField noteField;
	private JScrollPane scrollPane;
	private JTextField workerIDField;
	private JTextField supervisorIDField;
	private JTextField titleField;
	

	public AllTaskRequestDisplay(ArrayList<TaskRequest> list) throws SQLException {
		
		this.setBackground(new Color(240, 248, 255));
		this.setSize(628, 416);
		this.setLayout(null);
		
		
		
		//TABEL
		viewAllTable = new JTable() {
			private static final long serialVersionUID = 1L;
			
			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};
		
		DefaultTableModel model = (DefaultTableModel) viewAllTable.getModel();
		model.addColumn("id");
		model.addColumn("supervisor");
		model.addColumn("worker");
		model.addColumn("title");
		model.addColumn("note");
	
		
		Object[] row = new Object[5];
		for(int i=0; i<list.size(); i++) {
			row[0] = list.get(i).getId();
			row[1] = User.get(list.get(i).getSupervisorID().toString()).getUsername();
			row[2] = User.get(list.get(i).getWorkerID().toString()).getUsername();
			row[3] = list.get(i).getTitle();
			row[4] = list.get(i).getNote();
			model.addRow(row);
		}
		
		viewAllTable.setEnabled(true);
		viewAllTable.setRowSelectionAllowed(true);
		viewAllTable.setModel(model);
		viewAllTable.getColumnModel().getColumn(0).setPreferredWidth(165);
		viewAllTable.getColumnModel().getColumn(1).setPreferredWidth(89);
		viewAllTable.getColumnModel().getColumn(2).setPreferredWidth(79);
		viewAllTable.getColumnModel().getColumn(3).setPreferredWidth(65);
		viewAllTable.getColumnModel().getColumn(4).setPreferredWidth(65);
		viewAllTable.setAutoResizeMode(0);
		viewAllTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		viewAllTable.setBounds(10, 79, 507, 289);
		
	
		//TITLE
		Label label = new Label("All Task Request Display");
		label.setFont(new Font("Dialog", Font.BOLD, 18));
		label.setBounds(213, 10, 243, 22);
		add(label);
		
		//FORM YG KANAN
		JLabel lblTaskID = new JLabel("Task ID");
		lblTaskID.setBounds(428, 58, 46, 14);
		add(lblTaskID);
		
		JLabel lblTitle = new JLabel("Title");
		lblTitle.setBounds(428, 193, 63, 14);
		add(lblTitle);
		
		JLabel lblWorkerID = new JLabel("Worker");
		lblWorkerID.setBounds(428, 148, 63, 14);
		add(lblWorkerID);
		
		JLabel lblSupervisorID = new JLabel("Supervisor");
		lblSupervisorID.setBounds(428, 103, 80, 14);
		add(lblSupervisorID);
		
		JLabel lblNote = new JLabel("Note");
		lblNote.setBounds(428, 238, 46, 14);
		add(lblNote);
		
		taskIDField = new JTextField();
		taskIDField.setEditable(false);
		taskIDField.setBounds(428, 72, 179, 20);
		add(taskIDField);
		taskIDField.setColumns(10);
		
		
		workerIDField = new JTextField();
		workerIDField.setEditable(false);
		workerIDField.setColumns(10);
		workerIDField.setBounds(428, 207, 179, 20);
		add(workerIDField);
		
		supervisorIDField = new JTextField();
		supervisorIDField.setEditable(false);
		supervisorIDField.setColumns(10);
		supervisorIDField.setBounds(428, 117, 179, 20);
		add(supervisorIDField);
		
		titleField = new JTextField();
		titleField.setEditable(false);
		titleField.setColumns(10);
		titleField.setBounds(428, 206, 179, 20);
		add(titleField);
		
		noteField = new JTextField();
		noteField.setEditable(false);
		noteField.setColumns(10);
		noteField.setBounds(428, 252, 179, 20);
		add(noteField);
		
		workerIDField = new JTextField();
		workerIDField.setEditable(false);
		workerIDField.setColumns(10);
		workerIDField.setBounds(428, 162, 179, 20);
		add(workerIDField);
	
		//BUAT SCROLLPANE TABEL
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 58, 397, 246);
		add(scrollPane);
		

		//MUNCULIN DATA PAS DI KLIK
		viewAllTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int row = viewAllTable.getSelectedRow();
				TableModel model = viewAllTable.getModel();
				String taskID = (model.getValueAt(row, 0)).toString();
				String supervisorID = (model.getValueAt(row, 1)).toString();
				String workerID = (model.getValueAt(row, 2)).toString();
				String title = (model.getValueAt(row, 3)).toString();
				String note = (model.getValueAt(row, 4)).toString();
				
				taskIDField.setText(taskID);
				supervisorIDField.setText(supervisorID);
				workerIDField.setText(workerID);
				titleField.setText(title);
				noteField.setText(note);
				
			}
		});
		
		
		btnRejectTaskRequest = new JButton("Reject Task");
		btnRejectTaskRequest.setForeground(new Color(255, 255, 255));
		btnRejectTaskRequest.setBackground(new Color(0, 0, 128));
		btnRejectTaskRequest.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnRejectTaskRequest.setBounds(57, 338, 129, 34);
		add(btnRejectTaskRequest);

		scrollPane.setViewportView(viewAllTable);
		
		btnAcceptTaskRequest = new JButton("Accept Task");
		btnAcceptTaskRequest.setForeground(new Color(255, 255, 255));
		btnAcceptTaskRequest.setBackground(new Color(0, 0, 128));
		btnAcceptTaskRequest.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnAcceptTaskRequest.setBounds(240, 338, 129, 34);
		add(btnAcceptTaskRequest);
	
		
		
		this.setVisible(true);
	}


	public JTable getViewAllTable() {
		return viewAllTable;
	}


	public void setViewAllTable(JTable viewAllTable) {
		this.viewAllTable = viewAllTable;
	}


	public JButton getBtnRejectTaskRequest() {
		return btnRejectTaskRequest;
	}


	public void setBtnRejectTaskRequest(JButton btnRejectTaskRequest) {
		this.btnRejectTaskRequest = btnRejectTaskRequest;
	}


	public JButton getBtnAcceptTaskRequest() {
		return btnAcceptTaskRequest;
	}


	public void setBtnAcceptTaskRequest(JButton btnAcceptTaskRequest) {
		this.btnAcceptTaskRequest = btnAcceptTaskRequest;
	}


	public JTextField getTaskIDField() {
		return taskIDField;
	}


	public void setTaskIDField(JTextField taskIDField) {
		this.taskIDField = taskIDField;
	}


	public JTextField getNoteField() {
		return noteField;
	}


	public void setNoteField(JTextField noteField) {
		this.noteField = noteField;
	}


	public JScrollPane getScrollPane() {
		return scrollPane;
	}


	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
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


	public JTextField getTitleField() {
		return titleField;
	}


	public void setTitleField(JTextField titleField) {
		this.titleField = titleField;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}