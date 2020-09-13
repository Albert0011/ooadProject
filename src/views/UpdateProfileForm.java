package views;


import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;

public class UpdateProfileForm extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField usernameField;
	private JTextField telpField;
	private JButton updateButton;
	private JTextArea addressField;
	private JComboBox<Object> yearChoose;
	private JComboBox<Object> monthChoose;
	private JComboBox<Object> dayChoose;

	public UpdateProfileForm() {
		this.setSize(628, 376);
		this.setBackground(new Color(240, 248, 255));
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(null);
		
		//TITLE
		JLabel lblUpdateProfileForm = new JLabel("Update Profile Form");
		lblUpdateProfileForm.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblUpdateProfileForm.setBounds(209, 11, 186, 29);
		this.add(lblUpdateProfileForm);
		
		//LABEL
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblUsername.setBounds(160, 75, 89, 14);
		this.add(lblUsername);
		
		JLabel lblDOB = new JLabel("DOB");
		lblDOB.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDOB.setBounds(160, 118, 78, 14);
		this.add(lblDOB);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAddress.setBounds(160, 161, 78, 14);
		this.add(lblAddress);
		
		JLabel lblTelp = new JLabel("Telp");
		lblTelp.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTelp.setBounds(160, 251, 78, 14);
		this.add(lblTelp);
		
		
		usernameField = new JTextField();
		usernameField.setBounds(249, 73, 206, 20);
		this.add(usernameField);
		usernameField.setColumns(10);
		
		addressField = new JTextArea();
		addressField.setBounds(249, 158, 206, 69);
		this.add(addressField);
		
		yearChoose = new JComboBox<Object>();
		yearChoose.setModel(new DefaultComboBoxModel<Object>(new String[] {"2000", "1999", "1998", "1997", "1996", "1995", "1994", "1993", "1992", "1991", "1990", "1989", "1988", "1987", "1986", "1985", "1984", "1983", "1982", "1981", "1980", "1979", "1978", "1977", "1976", "1975", "1974", "1973", "1972", "1971", "1970"}));
		yearChoose.setBounds(249, 116, 80, 20);
		this.add(yearChoose);
		
		monthChoose = new JComboBox<Object>();
		monthChoose.setModel(new DefaultComboBoxModel<Object>(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"}));
		monthChoose.setBounds(332, 116, 60, 20);
		this.add(monthChoose);
		
		dayChoose = new JComboBox<Object>();
		dayChoose.setModel(new DefaultComboBoxModel<Object>(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		dayChoose.setBounds(395, 116, 60, 20);
		this.add(dayChoose);
		
		telpField = new JTextField();
		telpField.setBounds(249, 249, 206, 20);
		this.add(telpField);
		telpField.setColumns(10);
		
		JLabel lblCharacter1 = new JLabel("5 - 15 characters");
		lblCharacter1.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblCharacter1.setBounds(367, 93, 88, 14);
		this.add(lblCharacter1);
		
		JLabel lblyyyy = new JLabel("yyyy");
		lblyyyy.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblyyyy.setBounds(249, 135, 32, 14);
		this.add(lblyyyy);
		
		JLabel lblmm = new JLabel("mm");
		lblmm.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblmm.setBounds(332, 135, 32, 14);
		this.add(lblmm);
		
		JLabel lbldd = new JLabel("dd");
		lbldd.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lbldd.setBounds(395, 135, 32, 14);
		this.add(lbldd);
		
		JLabel lblCharacter2 = new JLabel("10 - 100 characters");
		lblCharacter2.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblCharacter2.setBounds(358, 229, 97, 14);
		this.add(lblCharacter2);
		
		JLabel lblCharacter3 = new JLabel("10 - 13 characters");
		lblCharacter3.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblCharacter3.setBounds(367, 270, 90, 14);
		this.add(lblCharacter3);
		
		
		updateButton = new JButton("Update Profile");
		updateButton.setForeground(new Color(255, 255, 255));
		updateButton.setBackground(new Color(0, 0, 128));
		updateButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		updateButton.setBounds(231, 303, 129, 41);
		this.add(updateButton);
		
		this.setVisible(true);
	}

	//setter getter
	public JTextField getUnameField() {
		return usernameField;
	}

	public void setUnameField(JTextField unameField) {
		this.usernameField = unameField;
	}

	public JTextField getTelpField() {
		return telpField;
	}

	public void setTelpField(JTextField telpField) {
		this.telpField = telpField;
	}

	public JButton getUpdateButton() {
		return updateButton;
	}

	public void setUpdateButton(JButton updateButton) {
		this.updateButton = updateButton;
	}

	public JTextArea getAddrField() {
		return addressField;
	}

	public void setAddrField(JTextArea addrField) {
		this.addressField = addrField;
	}

	public JComboBox<Object> getYearChoose() {
		return yearChoose;
	}

	public void setYearChoose(JComboBox<Object> yearChoose) {
		this.yearChoose = yearChoose;
	}

	public JComboBox<Object> getMonthChoose() {
		return monthChoose;
	}

	public void setMonthChoose(JComboBox<Object> monthChoose) {
		this.monthChoose = monthChoose;
	}

	public JComboBox<Object> getDayChoose() {
		return dayChoose;
	}

	public void setDayChoose(JComboBox<Object> dayChoose) {
		this.dayChoose = dayChoose;
	}
	
	public void emptyUpdateField() {
		this.usernameField.setText("");
		this.addressField.setText("");
		this.telpField.setText("");
	}
	
	
}
