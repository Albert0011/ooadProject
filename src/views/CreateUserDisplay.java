package views;


import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controllers.UserController;

public class CreateUserDisplay extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CreateUserDisplay() {
		this.getContentPane().setBackground(new Color(70, 130, 180));
		this.setBounds(100, 100, 352, 449);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		
		Label titleCreateUser = new Label("CREATE USER FORM");
		titleCreateUser.setBackground(new Color(70, 130, 180));
		titleCreateUser.setFont(new Font("Snap ITC", Font.BOLD, 22));
		titleCreateUser.setForeground(new Color(255, 255, 255));
		titleCreateUser.setBounds(53, 45, 243, 22);
		this.getContentPane().add(titleCreateUser);
		
		Label uname = new Label("Username");
		uname.setForeground(new Color(255, 255, 255));
		uname.setFont(new Font("Dialog", Font.BOLD, 12));
		uname.setBounds(27, 109, 62, 22);
		this.getContentPane().add(uname);
		
		Label role = new Label("Role");
		role.setForeground(new Color(255, 255, 255));
		role.setFont(new Font("Dialog", Font.BOLD, 12));
		role.setBounds(27, 139, 62, 22);
		this.getContentPane().add(role);
		
		Label dob = new Label("DOB");
		dob.setForeground(new Color(255, 255, 255));
		dob.setFont(new Font("Dialog", Font.BOLD, 12));
		dob.setBounds(27, 169, 62, 22);
		this.getContentPane().add(dob);
		
		Label address = new Label("Address");
		address.setForeground(new Color(255, 255, 255));
		address.setFont(new Font("Dialog", Font.BOLD, 12));
		address.setBounds(27, 220, 62, 22);
		this.getContentPane().add(address);
		
		Label telp = new Label("Telp");
		telp.setForeground(new Color(255, 255, 255));
		telp.setFont(new Font("Dialog", Font.BOLD, 12));
		telp.setBounds(27, 279, 62, 22);
		this.getContentPane().add(telp);
		
		JTextField telpField = new JTextField();
		telpField.setBounds(102, 280, 175, 20);
		this.getContentPane().add(telpField);
		telpField.setColumns(10);
		
		
		
		JComboBox<Object> roleChoice = new JComboBox<Object>();
		roleChoice.setModel(new DefaultComboBoxModel<Object>(new String[] {"Worker", "Supervisor"}));
		roleChoice.setBounds(102, 140, 175, 20);
		this.getContentPane().add(roleChoice);
		
		JComboBox<Object> yearChoose = new JComboBox<Object>();
		yearChoose.setModel(new DefaultComboBoxModel<Object>(new String[] {"2000", "1999", "1998", "1997", "1996", "1995", "1994", "1993", "1992", "1991", "1990", "1989", "1988", "1987", "1986", "1985", "1984", "1983", "1982", "1981", "1980", "1979", "1978", "1977", "1976", "1975", "1974", "1973", "1972", "1971", "1970"}));
		yearChoose.setBounds(102, 170, 69, 20);
		this.getContentPane().add(yearChoose);
		
		JComboBox<Object> monthChoose = new JComboBox<Object>();
		monthChoose.setModel(new DefaultComboBoxModel<Object>(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"}));
		monthChoose.setBounds(175, 170, 53, 20);
		this.getContentPane().add(monthChoose);
		
		JComboBox<Object> dayChoose = new JComboBox<Object>();
		dayChoose.setModel(new DefaultComboBoxModel<Object>(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		dayChoose.setBounds(232, 170, 45, 20);
		this.getContentPane().add(dayChoose);
		
		JTextField unamefield = new JTextField();
		unamefield.setBounds(102, 110, 175, 18);
		this.getContentPane().add(unamefield);
		unamefield.setColumns(10);
		
		
		JTextArea addressField = new JTextArea();
		addressField.setForeground(Color.BLACK);
		addressField.setWrapStyleWord(true);
		addressField.setLineWrap(true);
		addressField.setBounds(102, 219, 174, 50);
		this.getContentPane().add(addressField);
		
		JButton backButton = new JButton("Back");
		backButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		backButton.setBackground(new Color(255, 255, 255));
		backButton.setBounds(65, 325, 85, 35);
		this.getContentPane().add(backButton);
		
		JLabel lblNewLabel = new JLabel("yyyy");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(104, 194, 46, 14);
		this.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("mm");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(175, 194, 46, 14);
		this.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("dd");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setBounds(232, 194, 46, 14);
		this.getContentPane().add(lblNewLabel_2);
		
		JButton createUserButton = new JButton("Create User");
		createUserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(unamefield.getText().isEmpty() || ((String) roleChoice.getSelectedItem()).isEmpty() || 
						addressField.getText().isEmpty() || ((String) dayChoose.getSelectedItem()).isEmpty() || 
						((String) monthChoose.getSelectedItem()).isEmpty() || ((String) yearChoose.getSelectedItem()).isEmpty()) {
					
					JOptionPane.showMessageDialog(null, "Please Complete All Data");
				
				}
				else {
					int year = Integer.parseInt(yearChoose.getSelectedItem().toString());
					int month = Integer.parseInt(monthChoose.getSelectedItem().toString()) - 1;
					int day = Integer.parseInt(dayChoose.getSelectedItem().toString());
					
					Date date1 = new GregorianCalendar(year, month, day).getTime();
					UserController.createUser(unamefield.getText(), roleChoice.getSelectedItem().toString(), date1 , addressField.getText(), telpField.getText());
					
					unamefield.setText("");
					addressField.setText("");
					telpField.setText("");
				}
			}
		});
		createUserButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		createUserButton.setBackground(new Color(255, 255, 255));
		createUserButton.setBounds(163, 325, 120, 35);
		this.getContentPane().add(createUserButton);
		
		
		
		
	}

}
