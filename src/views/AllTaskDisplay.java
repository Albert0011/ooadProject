package views;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import models.Task;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JComboBox;

public class AllTaskDisplay extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTable viewAllTable;
	private JButton btnSortTask;
	private JButton btnSubmit;
	private JTextField idField;
	private JTextField workerIDField;
	private JTextField supervisorIDField;
	private JTextField titleField;
	private JScrollPane scrollPane;
	private JTextField noteField;
	private JTextField searchField;
	

	private JButton btnRequestRevision;
	private JButton btnReject;
	private JButton btnApprove;
	private JButton btnSearch;
	private JComboBox<String> sortByBox;
	private JComboBox<String> sortDirBox;
	
	private final String[] SORT_BY = {"Sort By","Username","DOB"};
	private final String[] SORT_DIR = {"Sort Dir","Ascending","Descending"};
	
	
	public AllTaskDisplay(ArrayList<Task> list) {
		this.setBackground(Color.CYAN);
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
		model.addColumn("workerID");
		model.addColumn("supervisorID");
		model.addColumn("title");
		model.addColumn("revisionCount");
		model.addColumn("score");
		model.addColumn("isSubmitted");
		model.addColumn("approveAt");
		model.addColumn("note");
		
		Object[] row = new Object[9];
		for(int i=0; i<list.size(); i++) {
			row[0] = list.get(i).getId();
			row[1] = list.get(i).getWorkerID();
			row[2] = list.get(i).getSupervisorID();
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
		viewAllTable.getColumnModel().getColumn(4).setPreferredWidth(79);
		viewAllTable.getColumnModel().getColumn(6).setPreferredWidth(65);
		viewAllTable.setAutoResizeMode(0);
		viewAllTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		viewAllTable.setBounds(10, 79, 507, 289);
		
	
		//TITLE
		Label allTask = new Label("All Task Display");
		allTask.setFont(new Font("Dialog", Font.BOLD, 18));
		allTask.setBounds(232, 10, 145, 22);
		add(allTask);
		
		//FORM YG KANAN
		JLabel lblNewLabel = new JLabel("id");
		lblNewLabel.setBounds(433, 109, 46, 14);
		add(lblNewLabel);
		
		JLabel workerID = new JLabel("worker_id");
		workerID.setBounds(433, 148, 63, 14);
		add(workerID);
		
		JLabel supervisorID = new JLabel("supervisor_id");
		supervisorID.setBounds(433, 187, 83, 14);
		add(supervisorID);
		
		JLabel title = new JLabel("title");
		title.setBounds(433, 226, 46, 14);
		add(title);
		
		JLabel note = new JLabel("note");
		note.setBounds(433, 271, 69, 14);
		add(note);
		
		//field
		idField = new JTextField();
		idField.setEditable(false);
		idField.setBounds(433, 123, 179, 20);
		add(idField);
		idField.setColumns(10);
		
		
		workerIDField = new JTextField();
		workerIDField.setEditable(false);
		workerIDField.setColumns(10);
		workerIDField.setBounds(433, 162, 179, 20);
		add(workerIDField);
		
		supervisorIDField = new JTextField();
		supervisorIDField.setEditable(false);
		supervisorIDField.setColumns(10);
		supervisorIDField.setBounds(433, 201, 179, 20);
		add(supervisorIDField);
		
		titleField = new JTextField();
		titleField.setEditable(false);
		titleField.setColumns(10);
		titleField.setBounds(433, 240, 179, 20);
		add(titleField);
		
		noteField = new JTextField();
		noteField.setEditable(false);
		noteField.setColumns(10);
		noteField.setBounds(433, 296, 179, 75);
		add(noteField);
		
		searchField = new JTextField();
		searchField.setBounds(14, 61, 118, 22);
		add(searchField);
		searchField.setColumns(10);
		
		//sortBox
		sortByBox = new JComboBox<String>();
//		SORT_BY
		sortByBox.setBounds(241, 62, 78, 20);
		add(sortByBox);
		
		sortDirBox = new JComboBox<String>();
//		SORT_DIR
		sortDirBox.setBounds(329, 62, 78, 20);
		add(sortDirBox);
		
	
		//BUAT SCROLLPANE TABEL
		scrollPane = new JScrollPane();
		scrollPane.setBounds(14, 125, 397, 246);
		add(scrollPane);
		

		//MUNCULIN DATA PAS DI KLIK
		viewAllTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int row = viewAllTable.getSelectedRow();
				TableModel model = viewAllTable.getModel();
				String id = (model.getValueAt(row, 0)).toString();
				String workerID = (model.getValueAt(row, 1)).toString();
				String supervisorID = (model.getValueAt(row, 2)).toString();
				String title = (model.getValueAt(row, 3)).toString();
				String note = (model.getValueAt(row, 8)).toString();
				idField.setText(id);
				workerIDField.setText(workerID);
				supervisorIDField.setText(supervisorID);
				titleField.setText(title);
				noteField.setText(note);
			}
		});
		
		//button
		btnSortTask = new JButton("Sort");
		btnSortTask.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnSortTask.setBounds(417, 61, 69, 22);
		add(btnSortTask);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnSubmit.setBounds(495, 61, 96, 23);
		add(btnSubmit);
		
		btnSearch = new JButton("Search");
		btnSearch.setBounds(142, 61, 89, 23);
		add(btnSearch);
		
		btnRequestRevision = new JButton("Request Revision");
		btnRequestRevision.setBounds(293, 93, 118, 23);
		add(btnRequestRevision);
		
		btnReject = new JButton("Reject ");
		btnReject.setBounds(152, 93, 118, 23);
		add(btnReject);
		
		btnApprove = new JButton("Approve");
		btnApprove.setBounds(14, 94, 118, 23);
		add(btnApprove);
		
		scrollPane.setViewportView(viewAllTable);
		
		this.setVisible(true);
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


	public JButton getBtnReject() {
		return btnReject;
	}


	public void setBtnReject(JButton btnReject) {
		this.btnReject = btnReject;
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
	
	
	
}
