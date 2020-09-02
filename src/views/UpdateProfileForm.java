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
	private JTextField unameField;
	private JTextField telpField;
	private JButton updateButton;
	private JTextArea addrField;
	private JComboBox<Object> yearChoose;
	private JComboBox<Object> monthChoose;
	private JComboBox<Object> dayChoose;

	public UpdateProfileForm() {
		this.setSize(628, 376);
		this.setBackground(new Color(240, 248, 255));
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Update Profile Form");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(209, 11, 186, 29);
		this.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(160, 75, 89, 14);
		this.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("DOB");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(160, 118, 78, 14);
		this.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Address");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_3.setBounds(160, 161, 78, 14);
		this.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Telp");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_4.setBounds(160, 251, 78, 14);
		this.add(lblNewLabel_4);
		
		
		unameField = new JTextField();
		unameField.setBounds(249, 73, 206, 20);
		this.add(unameField);
		unameField.setColumns(10);
		
		addrField = new JTextArea();
		addrField.setBounds(249, 158, 206, 69);
		this.add(addrField);
		
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
		
		JLabel lblNewLabel_5 = new JLabel("5 - 15 characters");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblNewLabel_5.setBounds(367, 93, 88, 14);
		this.add(lblNewLabel_5);
		
		JLabel lblNewLabel_5_1 = new JLabel("yyyy");
		lblNewLabel_5_1.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblNewLabel_5_1.setBounds(249, 135, 32, 14);
		this.add(lblNewLabel_5_1);
		
		JLabel lblNewLabel_5_1_1 = new JLabel("mm");
		lblNewLabel_5_1_1.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblNewLabel_5_1_1.setBounds(332, 135, 32, 14);
		this.add(lblNewLabel_5_1_1);
		
		JLabel lblNewLabel_5_1_2 = new JLabel("dd");
		lblNewLabel_5_1_2.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblNewLabel_5_1_2.setBounds(395, 135, 32, 14);
		this.add(lblNewLabel_5_1_2);
		
		JLabel lblNewLabel_5_2 = new JLabel("10 - 100 characters");
		lblNewLabel_5_2.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblNewLabel_5_2.setBounds(358, 229, 97, 14);
		this.add(lblNewLabel_5_2);
		
		JLabel lblNewLabel_5_2_1 = new JLabel("10 - 13 characters");
		lblNewLabel_5_2_1.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblNewLabel_5_2_1.setBounds(367, 270, 90, 14);
		this.add(lblNewLabel_5_2_1);
		
		
		updateButton = new JButton("Update Profile");
		updateButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		updateButton.setBounds(231, 303, 129, 41);
		this.add(updateButton);
		
		this.setVisible(true);
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

	public JButton getUpdateButton() {
		return updateButton;
	}

	public void setUpdateButton(JButton updateButton) {
		this.updateButton = updateButton;
	}

	public JTextArea getAddrField() {
		return addrField;
	}

	public void setAddrField(JTextArea addrField) {
		this.addrField = addrField;
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
		this.unameField.setText("");
		this.addrField.setText("");
		this.telpField.setText("");
	}
	
	
}
