package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.UUID;

import javax.swing.JOptionPane;

import com.mysql.jdbc.PreparedStatement;

import connection.Connector;

public class Task {

	private String workerID;
	private String supervisorID;
	private String title;
	private int revisionCount;
	private int score;
	private boolean isSubmitted;
	private Timestamp approveAt;
	private String note;
	
	public Task(String workerID, String supervisorID, String title, String note) {
		super();
		this.workerID = workerID;
		this.supervisorID = supervisorID;
		this.title = title;
//		this.revisionCount = revisionCount;
//		this.score = score;
//		this.isSubmitted = isSubmitted;
//		this.approveAt = approveAt;
		this.note = note;
	}

	public static ArrayList<Task> getAll(String userID){
		ArrayList<Task> listTask = new ArrayList<Task>();
		String query = "select * from task where userID = ?";
		try {
			PreparedStatement ps = (PreparedStatement) Connector.getConnection().prepareStatement(query);
			ps.setString(1, userID);
			ResultSet rs = ps.executeQuery(query);
			Task task;
			while(rs.next()) {
//				task = new Task(UUID.fromString(rs.getString(1)), UUID.fromString(rs.getString(2)), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getBoolean(6), rs.getTimestamp(7), rs.getString(8));
				task = new Task(rs.getString(1),rs.getString(2), rs.getString(3), rs.getString(4));
				listTask.add(task);
			}
			ps.close();
			rs.close();
			return listTask;	
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Get All Task Failed "+e.getMessage());
		}
		return null;
	}
	
	public static Task  create(String title,String supervisorID, String workerID, String note){
		Task task = new Task(title, supervisorID, workerID, note);
		return task;
	}
	
	public void save(){
		String query = "insert into task values (?,?,?,?)";
		try {
			PreparedStatement ps = (PreparedStatement) Connector.getConnection().prepareStatement(query);
			
			ps.setString(1, title);
			ps.setString(2, supervisorID);
			ps.setString(3, workerID);
			ps.setString(4, note);
			
			ps.execute();
			JOptionPane.showMessageDialog(null, "Add task Success!!");			
		} 
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Add task Failed!! "+e.getMessage());
		}
	}
	
	public Task update(){
		
		return null;
	}
	 
	public UUID delete(){
		
		return null;
	}
	
	
}
