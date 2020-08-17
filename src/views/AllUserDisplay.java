package views;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.Font;
import javax.swing.table.DefaultTableModel;

import controllers.UserController;
import models.User;
import java.awt.Color;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.border.LineBorder;

public class AllUserDisplay extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable viewAllTable;

	/**
	 * Launch the application.
	 */

	public AllUserDisplay(ArrayList<User> list) {

		this.getContentPane().setBackground(new Color(250, 250, 210));
		this.setBounds(100, 100, 545, 480);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		
	
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("No");
		model.addColumn("id");
		model.addColumn("username");
		model.addColumn("role");
		model.addColumn("address");
		model.addColumn("DOB");
		model.addColumn("telp");
		
		viewAllTable = new JTable() {
			private static final long serialVersionUID = 1L;
			
			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};
		Object[] row = new Object[7];
		for(int i=0; i<list.size(); i++) {
			row[0] = i+1;
			row[1] = list.get(i).getId();
			row[2] = list.get(i).getUsername();
			row[3] = list.get(i).getRole();
			row[4] = list.get(i).getAddress();
			row[5] = list.get(i).getDOB();
			row[6] = list.get(i).getTelp();
			model.addRow(row);
		}

		viewAllTable.setEnabled(true);
		viewAllTable.setRowSelectionAllowed(true);
		viewAllTable.setModel(model);
		viewAllTable.getColumnModel().getColumn(0).setPreferredWidth(30);
		viewAllTable.getColumnModel().getColumn(1).setPreferredWidth(188);
		viewAllTable.getColumnModel().getColumn(2).setPreferredWidth(89);
		viewAllTable.setAutoResizeMode(0);
		viewAllTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		viewAllTable.setBounds(10, 79, 507, 289);
		this.getContentPane().add(viewAllTable);
		
		
		Label label = new Label("All User Display");
		label.setFont(new Font("Dialog", Font.PLAIN, 18));
		label.setBounds(10, 10, 129, 22);
		this.getContentPane().add(label);
		
		
		JTextField txtNo = new JTextField();
		txtNo.setHorizontalAlignment(SwingConstants.CENTER);
		txtNo.setText("No");
		txtNo.setEditable(false);
		txtNo.setBounds(10, 58, 27, 22);
		this.getContentPane().add(txtNo);
		txtNo.setColumns(10);
		
		JTextField txtId = new JTextField();
		txtId.setHorizontalAlignment(SwingConstants.CENTER);
		txtId.setText("id");
		txtId.setEditable(false);
		txtId.setColumns(10);
		txtId.setBounds(35, 58, 156, 22);
		this.getContentPane().add(txtId);
		
		JTextField txtUsername = new JTextField();
		txtUsername.setHorizontalAlignment(SwingConstants.CENTER);
		txtUsername.setText("username");
		txtUsername.setEditable(false);
		txtUsername.setColumns(10);
		txtUsername.setBounds(190, 58, 75, 22);
		this.getContentPane().add(txtUsername);
		
		JTextField txtTelp = new JTextField();
		txtTelp.setHorizontalAlignment(SwingConstants.CENTER);
		txtTelp.setText("telp");
		txtTelp.setEditable(false);
		txtTelp.setColumns(10);
		txtTelp.setBounds(453, 58, 64, 22);
		this.getContentPane().add(txtTelp);
		
		JTextField txtDob = new JTextField();
		txtDob.setHorizontalAlignment(SwingConstants.CENTER);
		txtDob.setText("DOB");
		txtDob.setEditable(false);
		txtDob.setColumns(10);
		txtDob.setBounds(390, 58, 64, 22);
		this.getContentPane().add(txtDob);
		
		JTextField txtAddress = new JTextField();
		txtAddress.setHorizontalAlignment(SwingConstants.CENTER);
		txtAddress.setText("address");
		txtAddress.setEditable(false);
		txtAddress.setColumns(10);
		txtAddress.setBounds(327, 58, 64, 22);
		this.getContentPane().add(txtAddress);
		
		JTextField txtRole = new JTextField();
		txtRole.setHorizontalAlignment(SwingConstants.CENTER);
		txtRole.setText("role");
		txtRole.setEditable(false);
		txtRole.setColumns(10);
		txtRole.setBounds(264, 58, 64, 22);
		this.getContentPane().add(txtRole);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.setBounds(38, 391, 89, 34);
		this.getContentPane().add(btnNewButton);
		
		JButton btnDeleteUser = new JButton("Delete User");
		btnDeleteUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(viewAllTable.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "Please Select User");
				}
				else {
					int jawab = JOptionPane.showConfirmDialog(null, "Are you sure to delete this user?");
					switch (jawab) {
					case JOptionPane.YES_OPTION:
						int column = 1;
						int row = viewAllTable.getSelectedRow();
						String userId = (viewAllTable.getValueAt(row, column)).toString();
						UserController.deleteUser(userId);
						//AllUserDisplay frame = new AllUserDisplay(list);
						//frame.dispose();
						
						UserController.openAllUserDisplay();
						//setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
						break;
					case JOptionPane.NO_OPTION:
					
						break;
					case JOptionPane.CANCEL_OPTION:
					
						break;

					default:
						break;
					}

				}
			}
		});
		btnDeleteUser.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnDeleteUser.setBounds(162, 391, 129, 34);
		getContentPane().add(btnDeleteUser);
		
		JButton btnResetPassword = new JButton("Reset Password");
		btnResetPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnResetPassword.setBounds(327, 391, 162, 34);
		getContentPane().add(btnResetPassword);
	}

}
