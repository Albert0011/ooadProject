package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NoSuchObjectException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.UUID;

import javax.swing.JOptionPane;

import helpers.Log;
import models.Notification;
import models.User;
import views.NotificationHistoryDisplay;

public class NotificationController {
	
	private static NotificationController notificationController;
	
	public static NotificationController getInstance() {
		if(notificationController == null) {
			notificationController = new NotificationController();
			
		}
		return notificationController;
	}
	
	//NOTIFICATION DISPLAY
	public NotificationHistoryDisplay openNotificationDisplay() throws NoSuchObjectException {
		
		NotificationHistoryDisplay nh = new NotificationHistoryDisplay(getAllNotification());
		
		//ketika klik read all button, notifikasi akan terbaca semua
		nh.getReadAllButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					UUID userID = Log.getInstance().getCurrentUser().getId();
					readAllNotification(userID);
					
					MainController.getInstance().refreshContent(openNotificationDisplay());
				
					
				} catch (NoSuchObjectException e1) {
					e1.printStackTrace();
				}
			}
		});
		return nh;
	}
	
	//create notification
	public Notification createNotification(UUID userID, String message) {
		try {
			Notification notification = Notification.create(userID, message, null);
			notification.save();
			return notification;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			return null;
		}
	}
	
	//get all notification
	public ArrayList<Notification> getAllNotification() throws NoSuchObjectException{
		ArrayList<Notification> notif = null;
		User currentUser = Log.getInstance().getCurrentUser();
		notif = Notification.getAll(currentUser.getId());
		
		return notif;
	}
	
	//read all notification
	public void readAllNotification(UUID userID) {
		//menampung semua notification dari user tersebut yang belum terbaca
		ArrayList<Notification> listNotif = Notification.getAllUnread(userID);
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		int success = 0;
		
		//setiap notification tersebut di set readAt nya dan diupdate kedalam database
		for (Notification notification : listNotif) {
			
			notification.setReadAt(timestamp);
			try {
				notification.update();
				success = 1;
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Update Notification Failed!!" +e.getMessage());
			}
		}
		
		if(success == 1) {
			JOptionPane.showMessageDialog(null, "Update Notification Success!");
		}
		
	}

}