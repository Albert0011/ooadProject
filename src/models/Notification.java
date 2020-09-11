package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import java.util.UUID;

import javax.swing.JOptionPane;

import com.mysql.jdbc.PreparedStatement;

import connection.Connector;

public class Notification {

	private UUID id;
	private UUID userID;
	private String message;
	private Timestamp readAt;

	
	public Notification(UUID id, UUID userID, String message, Timestamp readAt) {
		super();
		this.id = id;
		this.userID = userID;
		this.message = message;
		this.readAt = readAt;
	}

	public Notification save() {
		String query = "insert into notifications values (?,?,?,?)";

		try {
			PreparedStatement ps = (PreparedStatement) Connector.getConnection().prepareStatement(query);
			
			ps.setString(1, id.toString());
			ps.setString(2, userID.toString());
			ps.setString(3, message);
			ps.setTimestamp(4, readAt);
			
			ps.execute();
			
			return new Notification(id, userID, message, readAt);
		}
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Add Notification Failed! "+e.getMessage());
		}
		return null;
	}

	public Notification update() throws SQLException {
		String query = "update notifications set user_id =?, message = ?, read_at = ? where id = ?";
		
		PreparedStatement ps = (PreparedStatement) Connector.getConnection().prepareStatement(query);
		ps.setString(1, userID.toString());
		ps.setString(2, message);
		ps.setTimestamp(3, readAt);
		ps.setString(4, id.toString());
		ps.execute();
	
		return new Notification(id, userID, message, readAt);
	
	}
	
	public static Notification create(UUID userID, String message, Timestamp readAt) {
		UUID notifID = UUID.randomUUID();
		Notification notif = new Notification(notifID, userID, message, readAt);
		return notif;
	}
	
	public static ArrayList<Notification> getAll(UUID userID){
		ArrayList<Notification> listNotification = new ArrayList<Notification>();
		
		String query = "select * from notifications where user_id = '"+userID.toString()+"' order by read_at asc";
		
		try {
			PreparedStatement ps = (PreparedStatement) Connector.getConnection().prepareStatement(query);
			
			ResultSet rs = ps.executeQuery(query);
			Notification notification;
			while(rs.next()) {
				notification = new Notification(UUID.fromString(rs.getString(1)), UUID.fromString(rs.getString(2)), rs.getString(3), rs.getTimestamp(4));
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
	
	public static ArrayList<Notification> getAllUnread(UUID userID){
		ArrayList<Notification> listNotification = new ArrayList<Notification>();
		String query = "select * from notifications where user_id = '" + userID.toString() + "'AND read_at IS NULL";
		try {
			
			PreparedStatement ps = (PreparedStatement) Connector.getConnection().prepareStatement(query);
			
			
			ResultSet rs = ps.executeQuery(query);
			Notification notification;
			while(rs.next()) {
				notification = new Notification(UUID.fromString(rs.getString(1)), UUID.fromString(rs.getString(2)), rs.getString(3), rs.getTimestamp(4));
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

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public UUID getUserID() {
		return userID;
	}

	public void setUserID(UUID userID) {
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