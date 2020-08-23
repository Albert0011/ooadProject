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
import javax.swing.JPanel;

public class CreateUserDisplay extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTextField unameField;
	private JTextField telpField;
	
	public static final int[] JUMLAHHARI = {
			31,28,31,30,31,30,31,31,30,31,30,31
	};
	
	
	public static boolean isLeapYear(int year) {
		if(((year%4 == 0) && !(year%100 == 0)) || year%400 == 0) {
			return true;
		}
		return false;
	}
	
	public static boolean isValidDate(int day, int month, int year) {
		if(month == 2 && isLeapYear(year)) {
			if(day > 29 || day < 1) return false;
		}
		else if(month == 2 && !isLeapYear(year)) {
			if(day > 28 || day < 1) return false;
		}
		else {
			if(day < 1 || day > JUMLAHHARI[month-1]) return false;
		}
		return true;
	}

	public CreateUserDisplay() {
		setBackground(new Color(70, 130, 180));
		setBounds(100, 100, 618, 406);
		setLayout(null);

		JLabel titleCreateUser = new JLabel("CREATE USER FORM");
		titleCreateUser.setForeground(new Color(255, 255, 255));
		titleCreateUser.setFont(new Font("Tahoma", Font.BOLD, 20));
		titleCreateUser.setBounds(186, 24, 212, 35);
		add(titleCreateUser);
		
		Label telp = new Label("Telp");
		telp.setForeground(Color.WHITE);
		telp.setFont(new Font("Dialog", Font.BOLD, 12));
		telp.setBounds(127, 280, 62, 22);
		add(telp);
		
		Label address = new Label("Address");
		address.setForeground(Color.WHITE);
		address.setFont(new Font("Dialog", Font.BOLD, 12));
		address.setBounds(127, 222, 62, 22);
		add(address);
		
		Label dob = new Label("DOB");
		dob.setForeground(Color.WHITE);
		dob.setFont(new Font("Dialog", Font.BOLD, 12));
		dob.setBounds(127, 170, 62, 22);
		add(dob);
		
		Label role = new Label("Role");
		role.setForeground(Color.WHITE);
		role.setFont(new Font("Dialog", Font.BOLD, 12));
		role.setBounds(127, 137, 62, 22);
		add(role);
		
		Label uname = new Label("Username");
		uname.setForeground(Color.WHITE);
		uname.setFont(new Font("Dialog", Font.BOLD, 12));
		uname.setBounds(127, 110, 62, 22);
		add(uname);
		
		JComboBox<Object> dayChoose = new JComboBox<Object>();
		dayChoose.setModel(new DefaultComboBoxModel<Object>(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		dayChoose.setBounds(331, 170, 45, 20);
		add(dayChoose);
		
		JComboBox<Object> monthChoose = new JComboBox<Object>();
		monthChoose.setModel(new DefaultComboBoxModel<Object>(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"}));
		monthChoose.setBounds(275, 170, 53, 20);
		add(monthChoose);
		
		JComboBox<Object> yearChoose = new JComboBox<Object>();
		yearChoose.setModel(new DefaultComboBoxModel<Object>(new String[] {"2000", "1999", "1998", "1997", "1996", "1995", "1994", "1993", "1992", "1991", "1990", "1989", "1988", "1987", "1986", "1985", "1984", "1983", "1982", "1981", "1980", "1979", "1978", "1977", "1976", "1975", "1974", "1973", "1972", "1971", "1970"}));
		yearChoose.setBounds(201, 170, 69, 20);
		add(yearChoose);
		
		JComboBox<Object> roleChoice = new JComboBox<Object>();
		roleChoice.setModel(new DefaultComboBoxModel<Object>(new String[] {"Worker", "Supervisor"}));
		roleChoice.setBounds(201, 139, 175, 20);
		add(roleChoice);
		
		unameField = new JTextField();
		unameField.setColumns(10);
		unameField.setBounds(201, 110, 175, 18);
		add(unameField);
		
		JTextArea addressField = new JTextArea();
		addressField.setWrapStyleWord(true);
		addressField.setLineWrap(true);
		addressField.setForeground(Color.BLACK);
		addressField.setBounds(201, 217, 174, 50);
		add(addressField);
		
		
		telpField = new JTextField();
		telpField.setColumns(10);
		telpField.setBounds(201, 280, 175, 20);
		add(telpField);
		
		JLabel limitUname = new JLabel("5 - 15 characters");
		limitUname.setForeground(Color.WHITE);
		limitUname.setFont(new Font("Tahoma", Font.ITALIC, 11));
		limitUname.setBounds(386, 112, 107, 14);
		add(limitUname);
		
		JLabel limitNo = new JLabel("10 - 13 characters");
		limitNo.setForeground(Color.WHITE);
		limitNo.setFont(new Font("Tahoma", Font.ITALIC, 11));
		limitNo.setBounds(386, 283, 107, 14);
		add(limitNo);
		
		JLabel limitAddr = new JLabel("10 - 100 characters");
		limitAddr.setForeground(Color.WHITE);
		limitAddr.setFont(new Font("Tahoma", Font.ITALIC, 11));
		limitAddr.setBounds(386, 222, 107, 14);
		add(limitAddr);
		
		JLabel lblNewLabel = new JLabel("yyyy");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(201, 196, 46, 14);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("mm");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(275, 196, 46, 14);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("dd");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setBounds(331, 196, 46, 14);
		add(lblNewLabel_2);
		
		
		
		
		
		JButton createUserButton = new JButton("Create User");
		createUserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(unameField.getText().isEmpty() || ((String) roleChoice.getSelectedItem()).isEmpty() || 
						addressField.getText().isEmpty() || ((String) dayChoose.getSelectedItem()).isEmpty() || 
						((String) monthChoose.getSelectedItem()).isEmpty() || ((String) yearChoose.getSelectedItem()).isEmpty()) {
					
					JOptionPane.showMessageDialog(null, "Please Complete All Data");
				
				}
				else {
					int year = Integer.parseInt(yearChoose.getSelectedItem().toString());
					int month = Integer.parseInt(monthChoose.getSelectedItem().toString());
					int day = Integer.parseInt(dayChoose.getSelectedItem().toString());
					

					
					if(isValidDate(day, month, year) == true) {
						Date date1 = new GregorianCalendar(year, month-1, day).getTime();
						UserController.createUser(unameField.getText(), roleChoice.getSelectedItem().toString(), date1 , addressField.getText(), telpField.getText());		
						
						unameField.setText("");
						addressField.setText("");
						telpField.setText("");
						
					}
					else {
						JOptionPane.showMessageDialog(null, "Date is not valid!");
					}
					
				}
			}
		});
		createUserButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		createUserButton.setBackground(Color.WHITE);
		createUserButton.setBounds(230, 323, 117, 35);
		add(createUserButton);
	
		
		this.setVisible(true);
		
		
	}
}
