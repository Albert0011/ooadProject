package controllers;

import java.sql.Timestamp;
import java.util.ArrayList;

import models.Notification;

public class NotificationController {

	
	public static Notification createNotification(String userID, String message) {
		Notification notification = new Notification(userID, message, null);
		return notification.save();
	}
	
	public static ArrayList<Notification> getAllNotification(){
		return Notification.getAll("seharusnya ini user");
	}
	
	public static void readAllNotification(String userID) {
		ArrayList<Notification> listNotif = Notification.getAllUnread(userID);
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		for (Notification notification : listNotif) {
			notification.setReadAt(timestamp);
			notification.update();
		}
		
	}
	
}
