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

	public NotificationHistoryDisplay openNotificationDisplay() throws NoSuchObjectException {
		
		NotificationHistoryDisplay nh = new NotificationHistoryDisplay(getAllNotification());
		
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
	
	public ArrayList<Notification> getAllNotification() throws NoSuchObjectException{
		ArrayList<Notification> notif = null;
		User currentUser = Log.getInstance().getCurrentUser();
		notif = Notification.getAll(currentUser.getId());
		
		return notif;
	}
	
	public void readAllNotification(UUID userID) {
		
		ArrayList<Notification> listNotif = Notification.getAllUnread(userID);
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		int success = 0;
		
		
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