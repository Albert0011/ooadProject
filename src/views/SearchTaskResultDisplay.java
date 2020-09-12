
package views;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import models.Task;
import models.User;

import javax.swing.JFrame;

public class SearchTaskResultDisplay extends JFrame {
	/**
	 * 
	 */
private static final long serialVersionUID = 1L;
	
	private JTable viewAllTable;
	private JTextField idField;
	private JTextField workerIDField;
	private JTextField supervisorIDField;
	private JTextField titleField;
	private JScrollPane scrollPane;
	private JTextField noteField;
	
	private JPanel mainPanel;
	
	private DefaultTableModel model;
	
	
	public SearchTaskResultDisplay(ArrayList<Task> list) throws SQLException {
		this.setBackground(new Color(240, 248, 255));
		this.setSize(628, 376);
		getContentPane().setLayout(null);
		
		mainPanel = new JPanel();
		mainPanel.setBackground(new Color(175, 238, 238));
		mainPanel.setBounds(0, 0, 628, 343);
		getContentPane().add(mainPanel);
		mainPanel.setLayout(null);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		
		
		//TABEL
		viewAllTable = new JTable() {
			private static final long serialVersionUID = 1L;
			
			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};
		
		model = (DefaultTableModel) viewAllTable.getModel();
		model.addColumn("id");
		model.addColumn("supervisor");
		model.addColumn("worker");
		model.addColumn("title");
		model.addColumn("revisionCount");
		model.addColumn("score");
		model.addColumn("isSubmitted");
		model.addColumn("approveAt");
		model.addColumn("note");
		

		Object[] row = new Object[9];
		for(int i=0; i<list.size(); i++) {
			row[0] = list.get(i).getId();
			row[1] = User.get(list.get(i).getSupervisorID().toString()).getUsername();
			row[2] = User.get(list.get(i).getWorkerID().toString()).getUsername();
			row[3] = list.get(i).getTitle();
			row[4] = list.get(i).getRevisionCount();
			row[5] = list.get(i).getScore();
			row[6] = list.get(i).getIsSubmitted();
			row[7] = list.get(i).getApproveAt();
			row[8] = list.get(i).getNote();
			model.addRow(row);
		}
	
		viewAllTable.setEnabled(true);
		viewAllTable.setRowSelectionAllowed(true);
		viewAllTable.setModel(model);
		viewAllTable.getColumnModel().getColumn(0).setPreferredWidth(165);
		viewAllTable.getColumnModel().getColumn(1).setPreferredWidth(89);
		viewAllTable.getColumnModel().getColumn(2).setPreferredWidth(79);
		viewAllTable.getColumnModel().getColumn(3).setPreferredWidth(65);
		viewAllTable.setAutoResizeMode(0);
		viewAllTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		viewAllTable.setBounds(10, 79, 507, 289);
		
	
		//BUAT SCROLLPANE TABEL
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 75, 397, 257);
		mainPanel.add(scrollPane);
		

		//MUNCULIN DATA PAS DI KLIK
		viewAllTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int row = viewAllTable.getSelectedRow();
				TableModel model = viewAllTable.getModel();
				String id = (model.getValueAt(row, 0)).toString();
				String workerID = (model.getValueAt(row, 2)).toString();
				String supervisorID = (model.getValueAt(row, 1)).toString();
				String title = (model.getValueAt(row, 3)).toString();
				String note = (model.getValueAt(row, 8)).toString();
				idField.setText(id);
				workerIDField.setText(workerID);
				supervisorIDField.setText(supervisorID);
				titleField.setText(title);
				noteField.setText(note);
			}
		});
		
		scrollPane.setViewportView(viewAllTable);
		
		JLabel searchTaskResultLabel = new JLabel("Search Task Result");
		searchTaskResultLabel.setFont(new Font("Malgun Gothic", Font.BOLD, 21));
		searchTaskResultLabel.setBounds(10, 21, 207, 37);
		mainPanel.add(searchTaskResultLabel);
		
		noteField = new JTextField();
		noteField.setBounds(429, 250, 179, 75);
		mainPanel.add(noteField);
		noteField.setEditable(false);
		noteField.setColumns(10);
		
		JLabel note_1 = new JLabel("note");
		note_1.setBounds(429, 236, 69, 14);
		mainPanel.add(note_1);
		
		titleField = new JTextField();
		titleField.setBounds(429, 210, 179, 20);
		mainPanel.add(titleField);
		titleField.setEditable(false);
		titleField.setColumns(10);
		
		JLabel title_1 = new JLabel("title");
		title_1.setBounds(429, 196, 46, 14);
		mainPanel.add(title_1);
		
		supervisorIDField = new JTextField();
		supervisorIDField.setBounds(429, 170, 179, 20);
		mainPanel.add(supervisorIDField);
		supervisorIDField.setEditable(false);
		supervisorIDField.setColumns(10);
		
		JLabel supervisorID_1 = new JLabel("supervisor");
		supervisorID_1.setBounds(429, 156, 83, 14);
		mainPanel.add(supervisorID_1);
		
		
		workerIDField = new JTextField();
		workerIDField.setBounds(429, 130, 179, 20);
		mainPanel.add(workerIDField);
		workerIDField.setEditable(false);
		workerIDField.setColumns(10);
		
		JLabel workerID_1 = new JLabel("worker");
		workerID_1.setBounds(429, 116, 63, 14);
		mainPanel.add(workerID_1);
		
		//field
		idField = new JTextField();
		idField.setBounds(429, 90, 179, 20);
		mainPanel.add(idField);
		idField.setEditable(false);
		idField.setColumns(10);
		
		//FORM YG KANAN
		JLabel lblNewLabel = new JLabel("id");
		lblNewLabel.setBounds(429, 76, 46, 14);
		mainPanel.add(lblNewLabel);
		
		
		this.setVisible(true);
	}


	public JTable getViewAllTable() {
		return viewAllTable;
	}


	public void setViewAllTable(JTable viewAllTable) {
		this.viewAllTable = viewAllTable;
	}


	public JTextField getIdField() {
		return idField;
	}


	public void setIdField(JTextField idField) {
		this.idField = idField;
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


	public JScrollPane getScrollPane() {
		return scrollPane;
	}


	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}


	public JTextField getNoteField() {
		return noteField;
	}


	public void setNoteField(JTextField noteField) {
		this.noteField = noteField;
	}


	public JPanel getMainPanel() {
		return mainPanel;
	}


	public void setMainPanel(JPanel mainPanel) {
		this.mainPanel = mainPanel;
	}


	public DefaultTableModel getModel() {
		return model;
	}


	public void setModel(DefaultTableModel model) {
		this.model = model;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	


}