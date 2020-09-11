package models;

import java.rmi.NoSuchObjectException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.UUID;

import javax.swing.JOptionPane;

import com.mysql.jdbc.PreparedStatement;

import connection.Connector;
import helpers.Log;


public class Task {
	private UUID id;
	private UUID workerID;
	private UUID supervisorID;
	private String title;
	private Integer revisionCount;
	private Integer score;
	private Integer isSubmitted;
	private Timestamp approveAt;
	private String note;
	
	
	public Task(UUID id, UUID supervisorID, UUID workerID, String title, Integer revisionCount, Integer score,
			Integer isSubmitted, Timestamp approveAt, String note) {
		super();
		this.id = id;
		this.supervisorID = supervisorID;
		this.workerID = workerID;
		this.title = title;
		this.revisionCount = revisionCount;
		this.score = score;
		this.isSubmitted = isSubmitted;
		this.approveAt = approveAt;
		this.note = note;
	}

	public static ArrayList<Task> Search(String query) throws SQLException, NoSuchObjectException{
		ArrayList<Task> listTask = new ArrayList<Task>();
		User user = Log.getInstance().getCurrentUser();
		String queryFinal = "";
		String type = null;
		if(user.getRole().equalsIgnoreCase("Worker")) {
			type = "worker_id";
			queryFinal = "select distinct ts.id, ts.supervisor_id, ts.worker_id, ts.title, ts.revision_count, ts.score, "
					+ "ts.is_submitted, ts.approved_at, ts.note from tasks ts JOIN users us on ts.worker_id = us.id OR ts.supervisor_id = us.id where ts.worker_id = '"+user.getId()+"' AND us.username"
					+ " LIKE '%"+query+"%' OR title LIKE '%"+query+"%'";
		} else if(user.getRole().equalsIgnoreCase("Supervisor")){
			type = "supervisor_id";
			queryFinal = "select distinct ts.id, ts.supervisor_id, ts.worker_id, ts.title, ts.revision_count, ts.score, "
					+ "ts.is_submitted, ts.approved_at, ts.note from tasks ts JOIN users us on ts.worker_id = us.id OR ts.supervisor_id = us.id where ts.supervisor_id = '"+user.getId()+"' AND us.username"
					+ " LIKE '%"+query+"%' OR title LIKE '%"+query+"%'";
		}
		
		
		System.out.println(queryFinal);
		PreparedStatement ps = (PreparedStatement) Connector.getConnection().prepareStatement(query);
		ResultSet rs = ps.executeQuery(queryFinal);
		Task task;
		while(rs.next()) {
			task = new Task(UUID.fromString(rs.getString(1)),UUID.fromString(rs.getString(2)),UUID.fromString(rs.getString(3)), rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getTimestamp(8), rs.getString(9));
			listTask.add(task);
		}
		ps.close();
		rs.close();
		return listTask;
	}
	
	public static ArrayList<Task> getAll(UUID userID) throws SQLException {
		ArrayList<Task> listTask = new ArrayList<Task>();
		User user = User.get(userID.toString());
		String type = null;
		if(user.getRole().equalsIgnoreCase("Worker")) {
			type = "worker_id";
		} else if(user.getRole().equalsIgnoreCase("Supervisor")){
			type = "supervisor_id";
		}
		
		String query = "select * from tasks where "+type+" = '"+userID.toString()+"'";
		PreparedStatement ps = (PreparedStatement) Connector.getConnection().prepareStatement(query);
//		ps.setString(1, userID.toString());
		ResultSet rs = ps.executeQuery(query);
		Task task;
		while(rs.next()) {
			task = new Task(UUID.fromString(rs.getString(1)),UUID.fromString(rs.getString(2)),UUID.fromString(rs.getString(3)), rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getTimestamp(8), rs.getString(9));
			listTask.add(task);
		}
		ps.close();
		rs.close();
		return listTask;	
	}
	


	 public static Task get(UUID id) {
	        String query = "SELECT * from tasks where id  = ?";

	        try {
	            PreparedStatement ps = (PreparedStatement) Connector.getConnection().prepareStatement(query);
	            ps.setString(1, id.toString());

	            ResultSet rs = ps.executeQuery();

	            rs.next();
	            String workerID = rs.getString("worker_id");
	            String supervisorID = rs.getString("supervisor_id");
	            String title = rs.getString("title");
	            String note = rs.getString("note");
	            Integer revCount = rs.getInt("revision_count");
	            Integer score = rs.getInt("score");
	            Integer isSubmit = rs.getInt("is_submitted");
	            Timestamp approveAt = rs.getTimestamp("approved_at");

	            return new Task(id, UUID.fromString(supervisorID), UUID.fromString(workerID), title, revCount, score, isSubmit, approveAt, note);

	        } 
	        catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }

	        return null;

	    }
	
	
	public static Task create(UUID supervisorID, UUID workerID, String title, String note){
		UUID taskId = UUID.randomUUID();
		Task task = new Task(taskId, supervisorID, workerID, title, 0, 0, 0, null, note);
		
		return task;
	}
	
	public Task save(){
		String query = "insert into tasks values (?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = (PreparedStatement) Connector.getConnection().prepareStatement(query);
			
			ps.setString(1, id.toString());
			ps.setString(2, supervisorID.toString());
			ps.setString(3, workerID.toString());
			ps.setString(4, title);
			ps.setString(5, revisionCount.toString());
			ps.setString(6, score.toString());
			ps.setString(7, isSubmitted.toString());
			if(approveAt == null) {
				ps.setNull(8, java.sql.Types.DATE);
			}else {
				ps.setString(8, approveAt.toString());				
			}
			ps.setString(9, note);
			
			ps.execute();
			JOptionPane.showMessageDialog(null, "Add task Success!!");			
		} 
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Add task Failed!! "+e.getMessage());
		}
		return this;
	}
	
	public void delete() {
        String query = "delete from tasks where id = ?";
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
        String query = "update tasks set supervisor_id = ?, worker_id = ?, title = ?, revision_count = ?, score = ?, is_submitted = ?, approved_at = ?,note = ? where id = ?";
        try {

            PreparedStatement ps = (PreparedStatement) Connector.getConnection().prepareStatement(query);
            ps.setString(1, supervisorID.toString());
            ps.setString(2, workerID.toString());
            ps.setString(3, title);
            ps.setString(4, revisionCount.toString());
            ps.setString(5, score.toString());
            ps.setString(6, isSubmitted.toString());
            if(approveAt == null) {
				ps.setNull(7, java.sql.Types.DATE);
			}else {
				ps.setString(7, approveAt.toString());				
			}
            ps.setString(8, note);
            ps.setString(9, id.toString());
            

            ps.execute();
            JOptionPane.showMessageDialog(null, "Update Task Success!");
            return new Task(id, supervisorID, workerID, title, revisionCount, score, isSubmitted, approveAt, note);
        } 
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Update Task Failed!!" +e.getMessage());
        }
        return null;
    }
	
	public static ArrayList<Task> sort(String sortBy, String sortDir) throws NoSuchObjectException, SQLException {
		
		ArrayList<Task> listTask = new ArrayList<Task>();
	
		User user = Log.getInstance().getCurrentUser();
		String query= "";
		String type = null;
		if(user.getRole().equalsIgnoreCase("Worker")) {
			type = "worker_id";
		} else if(user.getRole().equalsIgnoreCase("Supervisor")){
			type = "supervisor_id";
		}
		
		if(sortBy.equalsIgnoreCase("supervisor") || sortBy.equalsIgnoreCase("worker")) {
			sortBy = "username";
			query = "select distinct ts.id, ts.supervisor_id, ts.worker_id, ts.title, ts.revision_count, ts.score, "
					+ "ts.is_submitted, ts.approved_at, ts.note from tasks ts JOIN users us on ts.worker_id = us.id OR ts.supervisor_id = us.id "
					+ "where ts."+type+" = '"+user.getId().toString()+"'"+" order by us."+sortBy+" "+sortDir;
		} else {
			query = "select * from tasks where worker_id = '"+user.getId().toString()+"' OR supervisor_id = '"+user.getId().toString()+"' order by "+sortBy+" "+sortDir;
		}
		
//		String query = "select * from tasks where "+type+" = '"+user.getId().toString()+"'"+" order by "+sortBy+" "+sortDir;
//		System.out.println(query);
		PreparedStatement ps = (PreparedStatement) Connector.getConnection().prepareStatement(query);
		ResultSet rs = ps.executeQuery(query);
		Task task;
		while(rs.next()) {
			task = new Task(UUID.fromString(rs.getString(1)),UUID.fromString(rs.getString(2)),UUID.fromString(rs.getString(3)), rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getTimestamp(8), rs.getString(9));
			listTask.add(task);
		}
		ps.close();
		rs.close();
		return listTask;
		
	}

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

	public Integer getRevisionCount() {
		return revisionCount;
	}

	public void setRevisionCount(Integer revisionCount) {
		this.revisionCount = revisionCount;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getIsSubmitted() {
		return isSubmitted;
	}

	public void setIsSubmitted(Integer isSubmitted) {
		this.isSubmitted = isSubmitted;
	}

	public Timestamp getApproveAt() {
		return approveAt;
	}

	public void setApproveAt(Timestamp approveAt) {
		this.approveAt = approveAt;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	
	
	
	
}