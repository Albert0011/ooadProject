package models;

import java.sql.Timestamp;

public class TaskRequest {

	private String workerID;
	private String supervisorID;
	private String title;
	private String note;
	
	public TaskRequest(String workerID, String supervisorID, String title, String note) {
		super();
		this.workerID = workerID;
		this.supervisorID = supervisorID;
		this.title = title;
		this.note = note;
	}
	
	
	
	
	
	public String getWorkerID() {
		return workerID;
	}
	public void setWorkerID(String workerID) {
		this.workerID = workerID;
	}
	public String getSupervisorID() {
		return supervisorID;
	}
	public void setSupervisorID(String supervisorID) {
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
