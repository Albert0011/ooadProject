package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;
import javax.swing.JOptionPane;
import com.mysql.jdbc.PreparedStatement;
import connection.Connector;

public class User {
	
	private UUID id;
	private String username;
	private String password;
	private String role;
	private String address;
	private Date DOB;
	private String telp;

	
	public User(UUID id, String username, String password, String role, String address, Date dOB, String telp) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
		this.address = address;
		this.DOB = dOB;
		this.telp = telp;
	}


	public static User get(String id) {
		String query = "SELECT * from users where id  = ?";
		
		try {
			PreparedStatement ps = (PreparedStatement) Connector.getConnection().prepareStatement(query);
			ps.setString(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			rs.next();
			String userID = rs.getString("id");
			String username = rs.getString("username");
			String password = rs.getString("password");
			String role = rs.getString("role");
			String address = rs.getString("address");
			java.sql.Date DOB = rs.getDate("DOB");
			String telp = rs.getString("telp");
			
			System.out.println(role);
			System.out.println(address);
			
			return new User(UUID.fromString(userID), username, password, role, address, DOB, telp);
			
		} 
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return null;
		
	}
	
	public void create(String username, String role, Date dob, String address, String telp) {
		String query = "insert into users values (?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = (PreparedStatement) Connector.getConnection().prepareStatement(query);
			
			UUID userId = UUID.randomUUID();
			java.sql.Date date= new java.sql.Date(dob.getTime());
			String password = date.toString();
			ps.setString(1, userId.toString());
			ps.setString(2, username);
			ps.setString(3, password);
			ps.setString(4, role);
			ps.setString(5, address);
			ps.setDate(6, date);
			ps.setString(7, telp);
			
			ps.execute();
			JOptionPane.showMessageDialog(null, "Add User Success!!");			
		} 
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Add User Failed!! "+e.getMessage());
		}
	}
	
	public static ArrayList<User> getAll() {
		ArrayList<User> listUser = new ArrayList<User>();
		String query = "select * from users";
		try {
			PreparedStatement ps = (PreparedStatement) Connector.getConnection().prepareStatement(query);
			ResultSet rs = ps.executeQuery(query);
			User user;
			while(rs.next()) {
				user = new User(UUID.fromString(rs.getString(1)), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDate(6), rs.getString(7));
				listUser.add(user);
			}
			ps.close();
			rs.close();
			return listUser;	
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Get All User Failed "+e.getMessage());
		}
		return null;
	}
	
	public void delete(String id) {
		String query = "delete from users where id = ?";
		try {
			PreparedStatement ps = (PreparedStatement) Connector.getConnection().prepareStatement(query);
			ps.setString(1, id.toString());
			
			ps.execute();
			JOptionPane.showMessageDialog(null, "Delete User Success!!");			
		} 
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Delete User Failed!! "+e.getMessage());
		}

	}
	
	public void update(String id) {
		String query = "update users set password = ? where id = ?";
		try {
			PreparedStatement ps = (PreparedStatement) Connector.getConnection().prepareStatement(query);
			ps.setString(1, id.toString());
			
			ps.execute();
			JOptionPane.showMessageDialog(null, "Reset password Success!!");			
		} 
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Reset password Failed!! "+e.getMessage());
		}

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


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public Date getDOB() {
		return DOB;
	}


	public void setDOB(java.sql.Date dOB) {
		DOB = dOB;
	}


	public String getTelp() {
		return telp;
	}


	public void setTelp(String telp) {
		this.telp = telp;
	}


	
	
}
