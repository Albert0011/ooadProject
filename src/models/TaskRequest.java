package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

import javax.swing.JOptionPane;

import com.mysql.jdbc.PreparedStatement;

import connection.Connector;

public class TaskRequest {

	private UUID id;
	private UUID workerID;
	private UUID supervisorID;
	private String title;
	private String note;
	
	//constructor
	public TaskRequest(UUID id, UUID workerID, UUID supervisorID, String title, String note) {
		super();
		this.id = id;
		this.workerID = workerID;
		this.supervisorID = supervisorID;
		this.title = title;
		this.note = note;
	}
	
	//get all task request
	public static ArrayList<TaskRequest> getAll(UUID userID){
		ArrayList<TaskRequest> listTaskRequest = new ArrayList<TaskRequest>();

		String query = "select * from task_requests where supervisor_id ='"+userID.toString()+"'";
		try {
			PreparedStatement ps = (PreparedStatement) Connector.getConnection().prepareStatement(query);

			ResultSet rs = ps.executeQuery(query);
			TaskRequest taskRequest;
			while(rs.next()) {
				taskRequest = new TaskRequest(UUID.fromString(rs.getString(1)), UUID.fromString(rs.getString(2)), UUID.fromString(rs.getString(3)), rs.getString(4), rs.getString(5));
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
	
	//select task request berdasarkan id
	public static TaskRequest get(UUID id) {
		String query = "SELECT * from task_requests where id  ='"+ id.toString()+"'";
		
		try {
			PreparedStatement ps = (PreparedStatement) Connector.getConnection().prepareStatement(query);

			
			ResultSet rs = ps.executeQuery();
			
			rs.next();
			String workerID = rs.getString("worker_id");
			String supervisorID = rs.getString("supervisor_id");
			String title = rs.getString("title");
			String note = rs.getString("note");
			
			return new TaskRequest(id, UUID.fromString(workerID), UUID.fromString(supervisorID), title, note);
			
		} 
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return null;
		
	}
	
	//save task request kedalam database
	public TaskRequest save() throws SQLException {
		String query = "insert into task_requests values (?,?,?,?,?)";

		
			PreparedStatement ps = (PreparedStatement) Connector.getConnection().prepareStatement(query);
			
			ps.setString(1, id.toString());
			ps.setString(2, supervisorID.toString());
			ps.setString(3, workerID.toString());
			ps.setString(4, title);
			ps.setString(5, note);
			
			ps.execute();
			
			return new TaskRequest(id, workerID, supervisorID, title, note);
		
		
	}
	
	//update task request
	public TaskRequest update() {
		String query = "update task_requests set worker_id =?, supervisor_id =?, title =?, note =? where id =?";
		try {

			PreparedStatement ps = (PreparedStatement) Connector.getConnection().prepareStatement(query);
			ps.setString(1, workerID.toString());
			ps.setString(2, supervisorID.toString());
			ps.setString(3, title);
			ps.setString(4, note);
			ps.setString(4, id.toString());
			
			ps.execute();
			JOptionPane.showMessageDialog(null, "Update Task Request Success!");
			return new TaskRequest(id, workerID, supervisorID, title, note);
		} 
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Update Task Request Failed!!" +e.getMessage());
		}
		return null;
	}
	
	//delete task request berdasarkan id
	public void delete() {
		String query = "delete from task_requests where id =?";
		try {
			PreparedStatement ps = (PreparedStatement) Connector.getConnection().prepareStatement(query);
			ps.setString(1, this.id.toString());
			
			ps.execute();
						
		} 
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Delete Task Request Failed!! "+e.getMessage());
		}
	}
	
	//create task request
	public static TaskRequest create(UUID workerID, UUID supervisorID, String title, String note) {
		UUID taskReqID = UUID.randomUUID();
		TaskRequest taskReq = new TaskRequest(taskReqID, workerID, supervisorID, title, note);
		return taskReq;
	}
	
	//setter getter
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
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
