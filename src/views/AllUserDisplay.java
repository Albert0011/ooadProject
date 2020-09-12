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
import models.User;
import java.awt.Color;
import java.awt.Label;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.border.LineBorder;

public class AllUserDisplay extends JPanel {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable viewAllTable;
	private JButton btnDeleteUser;
	private JButton btnResetPassword;
	private JTextField idField;
	private JTextField unameField;
	private JTextField passField;
	private JTextField roleField;
	private JTextField addrField;
	private JTextField dobField;
	private JTextField telpField;
	private JScrollPane scrollPane;

	public AllUserDisplay(ArrayList<User> list) {
		
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
		//NAMA KOLOM
		model.addColumn("id");
		model.addColumn("username");
		model.addColumn("password");
		model.addColumn("role");
		model.addColumn("address");
		model.addColumn("DOB");
		model.addColumn("telp");
		
		//MENGISI TABEL DARI ARRAYLIST
		Object[] row = new Object[7];
		for(int i=0; i<list.size(); i++) {
			row[0] = list.get(i).getId();
			row[1] = list.get(i).getUsername();
			row[2] = list.get(i).getPassword();
			row[3] = list.get(i).getRole();
			row[4] = list.get(i).getAddress();
			row[5] = list.get(i).getDOB();
			row[6] = list.get(i).getTelp();
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
		Label label = new Label("All User Display");
		label.setFont(new Font("Dialog", Font.BOLD, 18));
		label.setBounds(232, 10, 145, 22);
		add(label);
		
		//FORM YG KANAN
		JLabel lblNewLabel = new JLabel("id");
		lblNewLabel.setBounds(429, 41, 46, 14);
		add(lblNewLabel);
		
		JLabel lblUsername = new JLabel("username");
		lblUsername.setBounds(429, 80, 63, 14);
		add(lblUsername);
		
		JLabel lblPassword = new JLabel("password");
		lblPassword.setBounds(429, 119, 63, 14);
		add(lblPassword);
		
		JLabel lblRole = new JLabel("role");
		lblRole.setBounds(429, 158, 46, 14);
		add(lblRole);
		
		JLabel lblAddress = new JLabel("address");
		lblAddress.setBounds(429, 197, 63, 14);
		add(lblAddress);
		
		JLabel lblDob = new JLabel("DOB");
		lblDob.setBounds(429, 236, 46, 14);
		add(lblDob);
		
		JLabel lblTelp = new JLabel("telp");
		lblTelp.setBounds(429, 275, 46, 14);
		add(lblTelp);
		
		idField = new JTextField();
		idField.setEditable(false);
		idField.setBounds(429, 55, 179, 20);
		add(idField);
		idField.setColumns(10);
		
		
		unameField = new JTextField();
		unameField.setEditable(false);
		unameField.setColumns(10);
		unameField.setBounds(429, 94, 179, 20);
		add(unameField);
		
		passField = new JTextField();
		passField.setEditable(false);
		passField.setColumns(10);
		passField.setBounds(429, 133, 179, 20);
		add(passField);
		
		roleField = new JTextField();
		roleField.setEditable(false);
		roleField.setColumns(10);
		roleField.setBounds(429, 172, 179, 20);
		add(roleField);
		
		addrField = new JTextField();
		addrField.setEditable(false);
		addrField.setColumns(10);
		addrField.setBounds(429, 211, 179, 20);
		add(addrField);
		
		dobField = new JTextField();
		dobField.setEditable(false);
		dobField.setColumns(10);
		dobField.setBounds(429, 250, 179, 20);
		add(dobField);
		
		telpField = new JTextField();
		telpField.setEditable(false);
		telpField.setColumns(10);
		telpField.setBounds(429, 289, 179, 20);
		add(telpField);
		
	
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
				String userId = (model.getValueAt(row, 0)).toString();
				String username = (model.getValueAt(row, 1)).toString();
				String password = (model.getValueAt(row, 2)).toString();
				String role = (model.getValueAt(row, 3)).toString();
				String addr = (model.getValueAt(row, 4)).toString();
				String dob = (model.getValueAt(row, 5)).toString();
				String telp = (model.getValueAt(row, 6)).toString();
				idField.setText(userId);
				unameField.setText(username);
				passField.setText(password);
				roleField.setText(role);
				addrField.setText(addr);
				dobField.setText(dob);
				telpField.setText(telp);
			}
		});
		
		
		btnDeleteUser = new JButton("Delete User");
		btnDeleteUser.setForeground(new Color(255, 255, 255));
		btnDeleteUser.setBackground(new Color(0, 0, 128));
		btnDeleteUser.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnDeleteUser.setBounds(101, 338, 129, 34);
		add(btnDeleteUser);
		
		btnResetPassword = new JButton("Reset Password");
		btnResetPassword.setForeground(new Color(255, 255, 255));
		btnResetPassword.setBackground(new Color(0, 0, 128));
		btnResetPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnResetPassword.setBounds(351, 338, 162, 34);
		add(btnResetPassword);

		scrollPane.setViewportView(viewAllTable);
		
		
		this.setVisible(true);
	}
	
	public JTable getViewAllTable() {
		return viewAllTable;
	}



	public void setViewAllTable(JTable viewAllTable) {
		this.viewAllTable = viewAllTable;
	}



	public JButton getBtnDeleteUser() {
		return btnDeleteUser;
	}



	public void setBtnDeleteUser(JButton btnDeleteUser) {
		this.btnDeleteUser = btnDeleteUser;
	}



	public JButton getBtnResetPassword() {
		return btnResetPassword;
	}



	public void setBtnResetPassword(JButton btnResetPassword) {
		this.btnResetPassword = btnResetPassword;
	}

	public JTextField getIdField() {
		return idField;
	}


	public JTextField getUnameField() {
		return unameField;
	}

	public JTextField getPassField() {
		return passField;
	}


	public JTextField getRoleField() {
		return roleField;
	}


	public JTextField getAddrField() {
		return addrField;
	}

	
	public JTextField getDobField() {
		return dobField;
	}


	public JTextField getTelpField() {
		return telpField;
	}

	public void setPassField(JTextField passField) {
		this.passField = passField;
	}

	
	
}
