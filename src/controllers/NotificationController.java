package controllers;

import java.rmi.NoSuchObjectException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.UUID;

import javax.swing.JOptionPane;

import helpers.Log;
import models.Notification;
import models.User;

public class NotificationController {

	
	public static Notification createNotification(UUID userID, String message) {
		try {
			Notification notification = Notification.create(userID, message, null);
			notification.save();
			return notification;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Create Notification Failed!! "+e.getMessage());
			return null;
		}
	}
	
	public static ArrayList<Notification> getAllNotification() throws NoSuchObjectException{
		ArrayList<Notification> notif = null;
		User currentUser = Log.getInstance().getCurrentUser();
		notif = Notification.getAll(currentUser.getId());
		
		return notif;
	}
	
	public static void readAllNotification(UUID userID) {
		ArrayList<Notification> listNotif = Notification.getAllUnread(userID);
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		for (Notification notification : listNotif) {
			notification.setReadAt(timestamp);
			notification.update();
		}
		
	}

}