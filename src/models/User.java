package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;


import com.mysql.jdbc.PreparedStatement;

import connection.Connector;

public class User {
	
	private UUID id;
	private String username;
	private String password;
	private String address;

	
	public User(UUID id, String username, String password, String address) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.address = address;
	}
	
	
	public static User getUser(String id) {
		String query = "SELECT * from users where id  = ?";
		
		try {
			PreparedStatement ps = (PreparedStatement) Connector.getConnection().prepareStatement(query);
			ps.setString(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			rs.next();
			String userID = rs.getString("id");
			String username = rs.getString("username");
			String password = rs.getString("password");
			String address = rs.getString("address");
			
			System.out.println(userID);
			System.out.println(username);
			
			return new User(UUID.fromString(userID), username, password, address);
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return null;
		
	}
	

	public UUID getId() {
		return id;
	}



	public void setId(UUID id) {
		this.id = id;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	
}
