package views;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import java.awt.Font;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import controllers.UserController;
import models.User;

import java.awt.Color;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.border.LineBorder;

public class AllUserDisplay extends JFrame {


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
	
	public void load_table(ArrayList<User> list) {
		DefaultTableModel model = (DefaultTableModel) viewAllTable.getModel();
		model.addColumn("id");
		model.addColumn("username");
		model.addColumn("password");
		model.addColumn("role");
		model.addColumn("address");
		model.addColumn("DOB");
		model.addColumn("telp");
		
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
		
	}

	public AllUserDisplay(ArrayList<User> list) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				load_table(list);
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 500);
		
		getContentPane().setBackground(new Color(230, 230, 250));
		setBackground(new Color(250, 250, 210));
		getContentPane().setLayout(null);
		
	
		
		
		viewAllTable = new JTable() {
			private static final long serialVersionUID = 1L;
			
			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};
		
		
		Label label = new Label("All User Display");
		label.setFont(new Font("Dialog", Font.PLAIN, 18));
		label.setBounds(10, 10, 129, 22);
		add(label);
		
		JLabel lblNewLabel = new JLabel("id");
		lblNewLabel.setBounds(544, 91, 46, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblUsername = new JLabel("username");
		lblUsername.setBounds(544, 125, 63, 14);
		getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("password");
		lblPassword.setBounds(544, 161, 63, 14);
		getContentPane().add(lblPassword);
		
		JLabel lblRole = new JLabel("role");
		lblRole.setBounds(544, 198, 46, 14);
		getContentPane().add(lblRole);
		
		JLabel lblAddress = new JLabel("address");
		lblAddress.setBounds(544, 234, 63, 14);
		getContentPane().add(lblAddress);
		
		JLabel lblDob = new JLabel("DOB");
		lblDob.setBounds(544, 271, 46, 14);
		getContentPane().add(lblDob);
		
		JLabel lblTelp = new JLabel("telp");
		lblTelp.setBounds(544, 309, 46, 14);
		getContentPane().add(lblTelp);
		
		idField = new JTextField();
		idField.setEditable(false);
		idField.setBounds(612, 88, 179, 20);
		getContentPane().add(idField);
		idField.setColumns(10);
		
		
		unameField = new JTextField();
		unameField.setEditable(false);
		unameField.setColumns(10);
		unameField.setBounds(612, 122, 179, 20);
		getContentPane().add(unameField);
		
		passField = new JTextField();
		passField.setEditable(false);
		passField.setColumns(10);
		passField.setBounds(612, 158, 179, 20);
		getContentPane().add(passField);
		
		roleField = new JTextField();
		roleField.setEditable(false);
		roleField.setColumns(10);
		roleField.setBounds(612, 195, 179, 20);
		getContentPane().add(roleField);
		
		addrField = new JTextField();
		addrField.setEditable(false);
		addrField.setColumns(10);
		addrField.setBounds(612, 231, 179, 20);
		getContentPane().add(addrField);
		
		dobField = new JTextField();
		dobField.setEditable(false);
		dobField.setColumns(10);
		dobField.setBounds(612, 268, 179, 20);
		getContentPane().add(dobField);
		
		telpField = new JTextField();
		telpField.setEditable(false);
		telpField.setColumns(10);
		telpField.setBounds(612, 306, 179, 20);
		getContentPane().add(telpField);
		
		
		JButton backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false); //you can't see me!
				dispose();
			}
		});
		backButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		backButton.setBounds(125, 391, 89, 34);
		add(backButton);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 58, 507, 289);
		getContentPane().add(scrollPane);
		

		
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
		btnDeleteUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(viewAllTable.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "Please Select User");
				}
				else {
					int jawab = JOptionPane.showConfirmDialog(null, "Are you sure to delete this user?");
					switch (jawab) {
					case JOptionPane.YES_OPTION:
						int row = viewAllTable.getSelectedRow();
						String userId = (viewAllTable.getValueAt(row, 0)).toString();
							UserController.deleteUser(userId);
							
							DefaultTableModel model = (DefaultTableModel) viewAllTable.getModel();
							model.removeRow(row);
							idField.setText("");
							unameField.setText("");
							passField.setText("");
							roleField.setText("");
							addrField.setText("");
							dobField.setText("");
							telpField.setText("");
							
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
		btnDeleteUser.setBounds(312, 391, 129, 34);
		add(btnDeleteUser);
		
		btnResetPassword = new JButton("Reset Password");
		btnResetPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(viewAllTable.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "Please Select User");
				}
				else if((passField.getText()).equals(dobField.getText())){
						JOptionPane.showMessageDialog(null, "Password still default!");
				}
				else {

					int jawab = JOptionPane.showConfirmDialog(null, "Are you sure to reset this user password?");
					switch (jawab) {
					case JOptionPane.YES_OPTION:
						int row = viewAllTable.getSelectedRow();
						String userId = (viewAllTable.getValueAt(row, 0)).toString();
							UserController.resetPassword(userId);
							DefaultTableModel model = (DefaultTableModel) viewAllTable.getModel();
							model.setValueAt(dobField.getText(), row, 2);
							passField.setText(dobField.getText());	
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
		btnResetPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnResetPassword.setBounds(544, 391, 162, 34);
		add(btnResetPassword);

		scrollPane.setViewportView(viewAllTable);
		
		
		this.setVisible(true);
	}

}
