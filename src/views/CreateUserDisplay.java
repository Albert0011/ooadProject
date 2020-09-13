package views;


import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JPanel;

public class CreateUserDisplay extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTextField unameField;
	private JTextField telpField;
	private JButton createUserButton;
	private JComboBox<Object> roleChoice;
	private JTextArea addressField;
	private JComboBox<Object> dayChoose;
	private JComboBox<Object> monthChoose;
	private JComboBox<Object> yearChoose;
	
	


	public CreateUserDisplay() {
		this.setBackground(new Color(240, 248, 255));
		this.setSize(628, 416);
		this.setLayout(null);
		
		JLabel titleCreateUser = new JLabel("CREATE USER FORM");
		titleCreateUser.setForeground(Color.BLACK);
		titleCreateUser.setFont(new Font("Tahoma", Font.BOLD, 20));
		titleCreateUser.setBounds(186, 24, 212, 35);
		add(titleCreateUser);
		
		Label telp = new Label("Telp");
		telp.setForeground(Color.BLACK);
		telp.setFont(new Font("Dialog", Font.BOLD, 12));
		telp.setBounds(127, 280, 62, 22);
		add(telp);
		
		Label address = new Label("Address");
		address.setForeground(Color.BLACK);
		address.setFont(new Font("Dialog", Font.BOLD, 12));
		address.setBounds(127, 222, 62, 22);
		add(address);
		
		Label dob = new Label("DOB");
		dob.setForeground(Color.BLACK);
		dob.setFont(new Font("Dialog", Font.BOLD, 12));
		dob.setBounds(127, 170, 62, 22);
		add(dob);
		
		Label role = new Label("Role");
		role.setForeground(Color.BLACK);
		role.setFont(new Font("Dialog", Font.BOLD, 12));
		role.setBounds(127, 137, 62, 22);
		add(role);
		
		Label uname = new Label("Username");
		uname.setForeground(Color.BLACK);
		uname.setFont(new Font("Dialog", Font.BOLD, 12));
		uname.setBounds(127, 110, 62, 22);
		add(uname);
		
		dayChoose = new JComboBox<Object>();
		dayChoose.setModel(new DefaultComboBoxModel<Object>(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		dayChoose.setBounds(331, 170, 45, 20);
		add(dayChoose);
		
		monthChoose = new JComboBox<Object>();
		monthChoose.setModel(new DefaultComboBoxModel<Object>(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"}));
		monthChoose.setBounds(275, 170, 53, 20);
		add(monthChoose);
		
		yearChoose = new JComboBox<Object>();
		yearChoose.setModel(new DefaultComboBoxModel<Object>(new String[] {"2000", "1999", "1998", "1997", "1996", "1995", "1994", "1993", "1992", "1991", "1990", "1989", "1988", "1987", "1986", "1985", "1984", "1983", "1982", "1981", "1980", "1979", "1978", "1977", "1976", "1975", "1974", "1973", "1972", "1971", "1970"}));
		yearChoose.setBounds(201, 170, 69, 20);
		add(yearChoose);
		
		roleChoice = new JComboBox<Object>();
		roleChoice.setModel(new DefaultComboBoxModel<Object>(new String[] {"Worker", "Supervisor"}));
		roleChoice.setBounds(201, 139, 175, 20);
		add(roleChoice);
		
		unameField = new JTextField();
		unameField.setColumns(10);
		unameField.setBounds(201, 110, 175, 18);
		add(unameField);
		
		addressField = new JTextArea();
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
		limitUname.setForeground(Color.BLACK);
		limitUname.setFont(new Font("Tahoma", Font.ITALIC, 11));
		limitUname.setBounds(386, 112, 107, 14);
		add(limitUname);
		
		JLabel limitNo = new JLabel("10 - 13 characters");
		limitNo.setForeground(Color.BLACK);
		limitNo.setFont(new Font("Tahoma", Font.ITALIC, 11));
		limitNo.setBounds(386, 283, 107, 14);
		add(limitNo);
		
		JLabel limitAddr = new JLabel("10 - 100 characters");
		limitAddr.setForeground(Color.BLACK);
		limitAddr.setFont(new Font("Tahoma", Font.ITALIC, 11));
		limitAddr.setBounds(386, 222, 107, 14);
		add(limitAddr);
		
		JLabel lblNewLabel = new JLabel("yyyy");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(201, 196, 46, 14);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("mm");
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(275, 196, 46, 14);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("dd");
		lblNewLabel_2.setForeground(Color.BLACK);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setBounds(331, 196, 46, 14);
		add(lblNewLabel_2);
		
		
		createUserButton = new JButton("Create User");
		createUserButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		createUserButton.setForeground(new Color(255, 255, 255));
		createUserButton.setBackground(new Color(0, 0, 128));
		createUserButton.setBounds(230, 323, 117, 35);
		add(createUserButton);
	
		
		this.setVisible(true);
		
		
	}
	
	//setter getter
	public JButton getCreateUserButton() {
		return createUserButton;
	}

	public void setCreateUserButton(JButton createUserButton) {
		this.createUserButton = createUserButton;
	}

	public JTextField getUnameField() {
		return unameField;
	}

	public void setUnameField(JTextField unameField) {
		this.unameField = unameField;
	}

	public JTextField getTelpField() {
		return telpField;
	}

	public void setTelpField(JTextField telpField) {
		this.telpField = telpField;
	}

	public JComboBox<Object> getRoleChoice() {
		return roleChoice;
	}

	public void setRoleChoice(JComboBox<Object> roleChoice) {
		this.roleChoice = roleChoice;
	}

	public JTextArea getAddressField() {
		return addressField;
	}

	public void setAddressField(JTextArea addressField) {
		this.addressField = addressField;
	}

	public JComboBox<Object> getDayChoose() {
		return dayChoose;
	}

	public void setDayChoose(JComboBox<Object> dayChoose) {
		this.dayChoose = dayChoose;
	}

	public JComboBox<Object> getMonthChoose() {
		return monthChoose;
	}

	public void setMonthChoose(JComboBox<Object> monthChoose) {
		this.monthChoose = monthChoose;
	}

	public JComboBox<Object> getYearChoose() {
		return yearChoose;
	}

	public void setYearChoose(JComboBox<Object> yearChoose) {
		this.yearChoose = yearChoose;
	}
	
	
	
	
}
