package views;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import models.Notification;

public class NotificationHistoryDisplay extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable notificationTable;
	private JButton readAllButton;
	/**
	 * Create the panel.
	 */
	public NotificationHistoryDisplay(ArrayList<Notification> listNotification) {
		this.setBackground(new Color(240, 248, 255));
		this.setSize(628, 416);
		setLayout(null);
		
		JPanel headerPanel = new JPanel();
		headerPanel.setBackground(new Color(0, 0, 102));
		headerPanel.setBounds(10, 11, 608, 45);
		add(headerPanel);
		headerPanel.setLayout(null);
		
		JLabel titleLabel = new JLabel("Notification History Display");
		titleLabel.setBounds(193, 11, 222, 20);
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		titleLabel.setForeground(new Color(255, 255, 255));
		headerPanel.add(titleLabel);
		
		readAllButton = new JButton("Read All");
		readAllButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		readAllButton.setBounds(502, 8, 96, 27);
		headerPanel.add(readAllButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 62, 609, 344);
		add(scrollPane);
		
		notificationTable = new JTable(){
			private static final long serialVersionUID = 1L;
			
			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};
		
		
		DefaultTableModel model = (DefaultTableModel) notificationTable.getModel();
		model.addColumn("Message");
		model.addColumn("Read At");
		
		Object[] row = new Object[3];
		for(int i=0; i<listNotification.size(); i++) {
			row[0] = listNotification.get(i).getMessage();
			row[1] = listNotification.get(i).getReadAt();
			model.addRow(row);
		}
		
		notificationTable.setFillsViewportHeight(true);
		notificationTable.getColumnModel().getColumn(0).setPreferredWidth(420);
		scrollPane.setViewportView(notificationTable);
		
		this.setVisible(true);
		
	}
	public JTable getNotificationTable() {
		return notificationTable;
	}
	public void setNotificationTable(JTable notificationTable) {
		this.notificationTable = notificationTable;
	}
	public JButton getReadAllButton() {
		return readAllButton;
	}
	public void setReadAllButton(JButton readAllButton) {
		this.readAllButton = readAllButton;
	}
	
}
