package models;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import com.mysql.jdbc.PreparedStatement;
import connection.Connector;
import helpers.SHA1Encryption;

public class User {
	
	private UUID id;
	private String username;
	private String password;
	private String role;
	private String address;
	private Date DOB;
	private String telp;

	//constructor
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

	//select data berdasarkan id
	public static User get(String id) throws SQLException {
		String query = "SELECT * from users where id  = ?";
	
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
		
		return new User(UUID.fromString(userID), username, password, role, address, DOB, telp);
	}
	
	//untuk ambil data-data user yang sedang login
	public static User getBy(String uname, String pass) throws SQLException {
		String query = "SELECT * from users where username = ? and password = ?";
		PreparedStatement ps = (PreparedStatement) Connector.getConnection().prepareStatement(query);
		ps.setString(1, uname);
		ps.setString(2, pass);
		
		ResultSet rs = ps.executeQuery();
		
		rs.next();
		String userID = rs.getString("id");
		String address = rs.getString("address");
		String role = rs.getString("role");
		java.sql.Date DOB = rs.getDate("DOB");
		String telp = rs.getString("telp");
		
		User user = new User(UUID.fromString(userID), uname, pass, role, address, DOB, telp);
		return user;
		
	}
	
	//create user
	public static User create(String username, String role, Date dob, String address, String telp) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		UUID userId = UUID.randomUUID();
		java.sql.Date date= new java.sql.Date(dob.getTime());
		
		//password di encrypt
		String password = SHA1Encryption.SHA1(date.toString());
		
		User user = new User(userId, username, password, role, address, date, telp);
		return user;
	}
	
	//save ke database
	public User save() throws SQLException {
		String query = "insert into users values (?,?,?,?,?,?,?)";

		PreparedStatement ps = (PreparedStatement) Connector.getConnection().prepareStatement(query);
		
		
		ps.setString(1, id.toString());
		ps.setString(2, username);
		ps.setString(3, password);
		ps.setString(4, role);
		ps.setString(5, address);
		ps.setDate(6, (java.sql.Date) DOB);
		ps.setString(7, telp);
		
		ps.execute();
		return new User(id, username, password, role, address, DOB, telp);

	}
	
	//get all user
	public static ArrayList<User> getAll() throws SQLException {
		ArrayList<User> listUser = new ArrayList<User>();
		String query = "select * from users";
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
	
	//delete user
	public void delete() throws SQLException {
		
		String query1 = "delete from notifications where user_id = ?";
		PreparedStatement ps = (PreparedStatement) Connector.getConnection().prepareStatement(query1);
		ps.setString(1, this.id.toString());
		ps.execute();
		
		String query2 = "delete from task_requests where worker_id = ? OR supervisor_id = ?";
		PreparedStatement ps2 = (PreparedStatement) Connector.getConnection().prepareStatement(query2);
		ps2.setString(1, this.id.toString());
		ps2.setString(2, this.id.toString());
		ps2.execute();
		
		String query3 = "delete from tasks where worker_id = ? OR supervisor_id = ?";
		PreparedStatement ps3 = (PreparedStatement) Connector.getConnection().prepareStatement(query3);
		ps3.setString(1, this.id.toString());
		ps3.setString(2, this.id.toString());
		ps3.execute();
		
		String query4 = "delete from users where id = ?";
		PreparedStatement ps4 = (PreparedStatement) Connector.getConnection().prepareStatement(query4);
		ps4.setString(1, this.id.toString());
		ps4.execute();

	}
	
	//update user
	public void update() throws SQLException {
		String query = "update users set password = ?, username = ?, address = ?, DOB = ?, telp = ? where id = ?";

		PreparedStatement ps = (PreparedStatement) Connector.getConnection().prepareStatement(query);
		ps.setString(1, this.password);
		ps.setString(2, this.username);
		ps.setString(3, this.address);
		ps.setDate(4, (java.sql.Date) this.DOB);
		ps.setString(5, this.telp);
		ps.setString(6, this.id.toString());
		
		ps.execute();
	
	}
	
	//setter getter
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



	public void setPassword(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		this.password = SHA1Encryption.SHA1(password);
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


	public void setDOB(Date dOB2) {
		java.sql.Date date= new java.sql.Date(dOB2.getTime());
		DOB = date;
	}


	public String getTelp() {
		return telp;
	}


	public void setTelp(String telp) {
		this.telp = telp;
	}


	
	
}
