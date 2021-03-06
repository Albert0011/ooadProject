package connection;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

import helpers.Env;

public class Connector {

	//connector to database
	private static Connection connection;
	
	public Connector() {
		try {
			connection = DriverManager.getConnection("jdbc:"+ Env.DRIVER +"://"+ Env.HOST + ":" + Env.PORT + "/"
					+ Env.DB, Env.USERNAME, Env.PASSWORD);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static Connection getConnection() {
		if(connection == null) {
			new Connector();
		}
		
		return connection;
	}
	
	
}
