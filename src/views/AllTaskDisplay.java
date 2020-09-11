package views;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import models.Task;
import models.User;

import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;

public class AllTaskDisplay extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTable viewAllTable;
	private JButton btnSortTask;
	private JTextField idField;
	private JTextField workerIDField;
	private JTextField supervisorIDField;
	private JTextField titleField;
	private JScrollPane scrollPane;
	private JTextField noteField;
	private JTextField searchField;
	

	private JButton btnRequestRevision;
	private JButton btnUpdate;
	private JButton btnApprove;
	private JButton btnSearch;
	private JButton btnSubmit;
	private JButton btnDeleteTask;
	private JComboBox<String> sortByBox;
	private JComboBox<String> sortDirBox;
	
	private final String[] SORT_BY = {"Sort By","title","approved_at","is_submitted","Supervisor","Worker"};
	private final String[] SORT_DIR = {"Sort Dir","ASC","DESC"};
	
	private JPanel panelSupervisor;
	private JPanel panelWorker;
	private JPanel mainPanel;
	
	private DefaultTableModel model;
	
	
	public AllTaskDisplay(ArrayList<Task> list) throws SQLException {
		this.setBackground(new Color(175, 238, 238));
		this.setSize(628, 376);
		this.setLayout(null);
		
		mainPanel = new JPanel();
		mainPanel.setBackground(new Color(175, 238, 238));
		mainPanel.setBounds(0, 0, 628, 343);
		add(mainPanel);
		mainPanel.setLayout(null);
		
		
		
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
		
		JLabel supervisorID_1 = new JLabel("supervisor_id");
		supervisorID_1.setBounds(429, 156, 83, 14);
		mainPanel.add(supervisorID_1);
		
		
		workerIDField = new JTextField();
		workerIDField.setBounds(429, 130, 179, 20);
		mainPanel.add(workerIDField);
		workerIDField.setEditable(false);
		workerIDField.setColumns(10);
		
		JLabel workerID_1 = new JLabel("worker_id");
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
		
	
		//TITLE
		Label allTask = new Label("All Task Display");
		allTask.setBounds(249, 10, 145, 22);
		mainPanel.add(allTask);
		allTask.setFont(new Font("Dialog", Font.BOLD, 18));
		
		searchField = new JTextField();
		searchField.setBounds(422, 42, 118, 22);
		mainPanel.add(searchField);
		searchField.setColumns(10);
		
		btnSearch = new JButton("Search");
		btnSearch.setBounds(545, 42, 77, 23);
		mainPanel.add(btnSearch);
		
		//button
		btnSortTask = new JButton("Sort");
		btnSortTask.setBounds(186, 42, 69, 22);
		mainPanel.add(btnSortTask);
		btnSortTask.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		//sortBox
		sortByBox = new JComboBox<String>();
		sortByBox.setBounds(10, 44, 78, 20);
		mainPanel.add(sortByBox);
		sortByBox.setModel(new DefaultComboBoxModel<String>(SORT_BY));
		
		sortDirBox = new JComboBox<String>();
		sortDirBox.setBounds(98, 44, 78, 20);
		mainPanel.add(sortDirBox);
		sortDirBox.setModel(new DefaultComboBoxModel<String>(SORT_DIR));
		
		
		panelWorker = new JPanel();
		panelWorker.setBackground(new Color(175, 238, 238));
		panelWorker.setBounds(0, 343, 628, 34);
		//add(panelWorker);
		panelWorker.setLayout(null);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.setBackground(new Color(224, 255, 255));
		btnSubmit.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSubmit.setBounds(251, 0, 125, 34);
		panelWorker.add(btnSubmit);
		
		panelSupervisor = new JPanel();
		panelSupervisor.setBackground(new Color(175, 238, 238));
		panelSupervisor.setBounds(0, 343, 628, 34);
		//add(panelSupervisor);
		panelSupervisor.setLayout(new GridLayout(0, 4, 0, 0));
		
		btnApprove = new JButton("Approve");
		btnApprove.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnApprove.setBackground(new Color(224, 255, 255));
		panelSupervisor.add(btnApprove);
		
		btnUpdate = new JButton("Update");
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnUpdate.setBackground(new Color(224, 255, 255));
		panelSupervisor.add(btnUpdate);
		
		btnDeleteTask = new JButton("Delete");
		btnDeleteTask.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnDeleteTask.setBackground(new Color(224, 255, 255));
		panelSupervisor.add(btnDeleteTask);
		
		btnRequestRevision = new JButton("Request Revision");
		btnRequestRevision.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnRequestRevision.setBackground(new Color(224, 255, 255));
		panelSupervisor.add(btnRequestRevision);
		
		
		this.setVisible(true);
	}
	
	
	
	public JButton getBtnDeleteTask() {
		return btnDeleteTask;
	}



	public void setBtnDeleteTask(JButton btnDeleteTask) {
		this.btnDeleteTask = btnDeleteTask;
	}



	public JTable getViewAllTable() {
		return viewAllTable;
	}


	public void setViewAllTable(JTable viewAllTable) {
		this.viewAllTable = viewAllTable;
	}


	public JButton getBtnSortTask() {
		return btnSortTask;
	}


	public void setBtnSortTask(JButton btnSortTask) {
		this.btnSortTask = btnSortTask;
	}


	public JButton getBtnSubmit() {
		return btnSubmit;
	}


	public void setBtnSubmit(JButton btnSubmit) {
		this.btnSubmit = btnSubmit;
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


	public JTextField getSearchField() {
		return searchField;
	}


	public void setSearchField(JTextField searchField) {
		this.searchField = searchField;
	}


	public JButton getBtnRequestRevision() {
		return btnRequestRevision;
	}


	public void setBtnRequestRevision(JButton btnRequestRevision) {
		this.btnRequestRevision = btnRequestRevision;
	}


	public JButton getBtnUpdate() {
		return btnUpdate;
	}

	public void setBtnUpdate(JButton btnUpdate) {
		this.btnUpdate = btnUpdate;
	}

	public JButton getBtnApprove() {
		return btnApprove;
	}


	public void setBtnApprove(JButton btnApprove) {
		this.btnApprove = btnApprove;
	}


	public JButton getBtnSearch() {
		return btnSearch;
	}


	public void setBtnSearch(JButton btnSearch) {
		this.btnSearch = btnSearch;
	}

	public String getSortByItem() {
		return sortByBox.getSelectedItem().toString();
	}
	
	public String getSortDirItem() {
		return sortDirBox.getSelectedItem().toString();
	}

	public JComboBox<String> getSortByBox() {
		return sortByBox;
	}


	public void setSortByBox(JComboBox<String> sortByBox) {
		this.sortByBox = sortByBox;
	}


	public JComboBox<String> getSortDirBox() {
		return sortDirBox;
	}


	public void setSortDirBox(JComboBox<String> sortDirBox) {
		this.sortDirBox = sortDirBox;
	}


	public String[] getSORT_BY() {
		return SORT_BY;
	}


	public String[] getSORT_DIR() {
		return SORT_DIR;
	}



	public JPanel getPanelSupervisor() {
		return panelSupervisor;
	}



	public void setPanelSupervisor(JPanel panelSupervisor) {
		this.panelSupervisor = panelSupervisor;
	}



	public JPanel getPanelWorker() {
		return panelWorker;
	}



	public void setPanelWorker(JPanel panelWorker) {
		this.panelWorker = panelWorker;
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
	
	
	
	
}