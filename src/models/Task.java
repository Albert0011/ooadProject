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
	private UUID id;
	private UUID workerID;
	private UUID supervisorID;
	private String title;
	private Integer revisionCount;
	private Integer score;
	private boolean isSubmitted;
	private Timestamp approveAt;
	private String note;
	
	
	public Task(UUID id, UUID workerID, UUID supervisorID, String title, Integer revisionCount, Integer score,
			boolean isSubmitted, Timestamp approveAt, String note) {
		super();
		this.id = id;
		this.workerID = workerID;
		this.supervisorID = supervisorID;
		this.title = title;
		this.revisionCount = revisionCount;
		this.score = score;
		this.isSubmitted = isSubmitted;
		this.approveAt = approveAt;
		this.note = note;
	}

	public static ArrayList<Task> Search(String query) throws SQLException{
		ArrayList<Task> listTask = new ArrayList<Task>();
		
		PreparedStatement ps = (PreparedStatement) Connector.getConnection().prepareStatement(query);
		ResultSet rs = ps.executeQuery(query);
		Task task;
		while(rs.next()){
			task = new Task(UUID.fromString(rs.getString(1)),UUID.fromString(rs.getString(2)),UUID.fromString(rs.getString(3)), rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getBoolean(7), rs.getTimestamp(8), rs.getString(9));
			listTask.add(task);
		}
		ps.close();
		rs.close();
		return listTask;		
	}
	
	public static ArrayList<Task> getAll(UUID userID) throws SQLException {
		ArrayList<Task> listTask = new ArrayList<Task>();
		String query = "select * from task where userID = ?";
		PreparedStatement ps = (PreparedStatement) Connector.getConnection().prepareStatement(query);
		ps.setString(1, userID.toString());
		ResultSet rs = ps.executeQuery(query);
		Task task;
		while(rs.next()) {
			task = new Task(UUID.fromString(rs.getString(1)),UUID.fromString(rs.getString(2)),UUID.fromString(rs.getString(3)), rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getBoolean(7), rs.getTimestamp(8), rs.getString(9));
			listTask.add(task);
		}
		ps.close();
		rs.close();
		return listTask;	
	}
	


    public static Task get(UUID id) {
    	Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String query = "SELECT * from task where id  = ?";

        try {
            PreparedStatement ps = (PreparedStatement) Connector.getConnection().prepareStatement(query);
            ps.setString(1, id.toString());

            ResultSet rs = ps.executeQuery();

            rs.next();
            String workerID = rs.getString("worker_id");
            String supervisorID = rs.getString("supervisor_id");
            String title = rs.getString("title");
            String note = rs.getString("note");

            return new Task(id, UUID.fromString(workerID), UUID.fromString(supervisorID), title, 0, 0, false, timestamp, note);

        } 
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;

    }
	
	
	public static Task create(UUID supervisorID, UUID workerID, String title, String note){
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		UUID taskId = UUID.randomUUID();
		Task task = new Task(taskId, workerID, supervisorID, title, 0, 0, false, timestamp, note);
		
		return task;
	}
	
	public Task save(){
		String query = "insert into task values (?,?,?,?)";
		try {
			PreparedStatement ps = (PreparedStatement) Connector.getConnection().prepareStatement(query);
			
			ps.setString(1, supervisorID.toString());
			ps.setString(2, workerID.toString());
			ps.setString(3, title);
			ps.setString(4, note);
			
			ps.execute();
			JOptionPane.showMessageDialog(null, "Add task Success!!");			
		} 
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Add task Failed!! "+e.getMessage());
		}
		return this;
	}
	
	public void delete() {
        String query = "delete from task where id = ?";
        try {
            PreparedStatement ps = (PreparedStatement) Connector.getConnection().prepareStatement(query);
            ps.setString(1, this.id.toString());

            ps.execute();
            JOptionPane.showMessageDialog(null, "Delete Task Success!!");
        } 
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Delete Task Failed!! "+e.getMessage());
        }
    }
	
	public Task update() {
        String query = "update task set worker_id = ?, supervisor_id = ?, title = ?, score = ?, note = ? where id = ?";
        try {

            PreparedStatement ps = (PreparedStatement) Connector.getConnection().prepareStatement(query);
            ps.setString(1, workerID.toString());
            ps.setString(2, supervisorID.toString());
            ps.setString(3, score.toString());
            ps.setString(4, title);
            ps.setString(5, note);
            ps.setString(6, id.toString());

            ps.execute();
            JOptionPane.showMessageDialog(null, "Update Task Success!");
            return new Task(id, workerID, supervisorID, title, 0, score, false, approveAt, note);
        } 
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Update Task Failed!!" +e.getMessage());
        }
        return null;
    }
	
	
	
}
