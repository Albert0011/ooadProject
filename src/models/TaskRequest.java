package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

import javax.swing.JOptionPane;

import com.mysql.jdbc.PreparedStatement;

import connection.Connector;

public class TaskRequest {

	private UUID workerID;
	private UUID supervisorID;
	private String title;
	private String note;
	
	public TaskRequest(UUID workerID, UUID supervisorID, String title, String note) {
		super();
		this.workerID = workerID;
		this.supervisorID = supervisorID;
		this.title = title;
		this.note = note;
	}
	
	public static ArrayList<TaskRequest> getAll(String userID){
		ArrayList<TaskRequest> listTaskRequest = new ArrayList<TaskRequest>();
		String query = "select * from taskRequest where userID = " + userID;
		try {
			PreparedStatement ps = (PreparedStatement) Connector.getConnection().prepareStatement(query);
			ResultSet rs = ps.executeQuery(query);
			TaskRequest taskRequest;
			while(rs.next()) {
				taskRequest = new TaskRequest(UUID.fromString(rs.getString(1)), UUID.fromString(rs.getString(2)), rs.getString(3), rs.getString(4));
				listTaskRequest.add(taskRequest);
			}
			ps.close();
			rs.close();
			return listTaskRequest;
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Get All Task Request Failed "+e.getMessage());
		}
		return null;
	}
	
	public TaskRequest get(String id) {
		String query = "SELECT * from users where id  = ?";
		
		try {
			PreparedStatement ps = (PreparedStatement) Connector.getConnection().prepareStatement(query);
			ps.setString(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			rs.next();
			String workerID = rs.getString("workerID");
			String supervisorID = rs.getString("supervisorID");
			String title = rs.getString("title");
			String note = rs.getString("note");
			
			return new TaskRequest(UUID.fromString(workerID), UUID.fromString(supervisorID), title, note);
			
		} 
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return null;
		
	}
	
	public TaskRequest save() {
		String query = "insert into taskRequest values (?,?,?,?)";

		try {
			PreparedStatement ps = (PreparedStatement) Connector.getConnection().prepareStatement(query);
			
			ps.setString(1, workerID.toString());
			ps.setString(2, supervisorID.toString());
			ps.setString(3, title);
			ps.setString(4, note);
			
			ps.execute();
			JOptionPane.showMessageDialog(null, "Create Task Request Success!");
			return new TaskRequest(workerID, supervisorID, title, note);
		}
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Add Task Request Failed! "+e.getMessage());
		}
		return null;
	}

	public TaskRequest update() {
		String query = "update taskRequest set workerID = ?, supervisorID = ?, title = ?, note = ?";
		try {

			PreparedStatement ps = (PreparedStatement) Connector.getConnection().prepareStatement(query);
			ps.setString(1, workerID.toString());
			ps.setString(2, supervisorID.toString());
			ps.setString(3, title);
			ps.setString(4, note);
			
			ps.execute();
			JOptionPane.showMessageDialog(null, "Update Task Request Success!");
			return new TaskRequest(workerID, supervisorID, title, note);
			
		} 
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Update Task Request Failed!!" +e.getMessage());
		}
		return null;
	}
	
	public void delete() {
		String query = "delete from taskRequest where workerID = ?, supervisorID = ?";
		try {
			PreparedStatement ps = (PreparedStatement) Connector.getConnection().prepareStatement(query);
			ps.setString(1, this.workerID.toString());
			ps.setString(2, this.supervisorID.toString());
			
			ps.execute();
			JOptionPane.showMessageDialog(null, "Delete Task Request Success!!");			
		} 
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Delete Task Request Failed!! "+e.getMessage());
		}
	}
	
	
	
	public UUID getWorkerID() {
		return workerID;
	}

	public void setWorkerID(UUID workerID) {
		this.workerID = workerID;
	}

	public UUID getSupervisorID() {
		return supervisorID;
	}

	public void setSupervisorID(UUID supervisorID) {
		this.supervisorID = supervisorID;
	}

	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
	
	
	
}
