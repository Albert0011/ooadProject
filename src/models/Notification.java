package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.mysql.jdbc.PreparedStatement;

import connection.Connector;

public class Notification {

	private String userID;
	private String message;
	private Timestamp readAt;

	public Notification(String userID, String message, Timestamp readAt) {
		super();
		this.userID = userID;
		this.message = message;
		this.readAt = readAt;
	}
	
	public Notification save() {
		String query = "insert into notification values (?,?,?)";

		try {
			PreparedStatement ps = (PreparedStatement) Connector.getConnection().prepareStatement(query);
			
			ps.setString(1, userID.toString());
			ps.setString(2, message);
			ps.setTimestamp(3, readAt);
			
			ps.execute();
			JOptionPane.showMessageDialog(null, "Create Notification Success!");
			return new Notification(userID, message, readAt);
		}
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Add Notification Failed! "+e.getMessage());
		}
		return null;
	}

	public Notification update() {
		String query = "update notification set userID =?, message = ?, readAt = ?";
		try {

			PreparedStatement ps = (PreparedStatement) Connector.getConnection().prepareStatement(query);
			ps.setString(1, userID.toString());
			ps.setString(2, message);
			ps.setTimestamp(3, readAt);
			ps.execute();
			
			
		} 
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Update Notification Failed!!" +e.getMessage());
		}
		return null;
	}
	
	
	public static ArrayList<Notification> getAll(String userID){
		ArrayList<Notification> listNotification = new ArrayList<Notification>();
		String query = "select * from task where userID = " + userID;
		try {
			PreparedStatement ps = (PreparedStatement) Connector.getConnection().prepareStatement(query);
			ResultSet rs = ps.executeQuery(query);
			Notification notification;
			while(rs.next()) {
				notification = new Notification(rs.getString(1), rs.getString(2), rs.getTimestamp(3));
				listNotification.add(notification);
			}
			ps.close();
			rs.close();
			return listNotification;	
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Get All Notification Failed "+e.getMessage());
		}
		return null;
	}
	
	public static ArrayList<Notification> getAllUnread(String userID){
		ArrayList<Notification> listNotification = new ArrayList<Notification>();
		String query = "select * from task where userID = " + userID + "AND readAt = NULL";
		try {
			PreparedStatement ps = (PreparedStatement) Connector.getConnection().prepareStatement(query);
			ResultSet rs = ps.executeQuery(query);
			Notification notification;
			while(rs.next()) {
				notification = new Notification(rs.getString(1), rs.getString(2), rs.getTimestamp(3));
				listNotification.add(notification);
			}
			ps.close();
			rs.close();
			return listNotification;	
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Get All Unread Notification Failed "+e.getMessage());
		}
		return null;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Timestamp getReadAt() {
		return readAt;
	}

	public void setReadAt(Timestamp readAt) {
		this.readAt = readAt;
	}

	
		
	
	
	
}