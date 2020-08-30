package helpers;

import java.rmi.NoSuchObjectException;
import models.User;

public class Log {
	
	private static Log loggedIn;
	private User currentUser;
	
	public Log(User currentUser) {
		this.currentUser = currentUser;
	}
	
	public static Log createLog(User currentUser) {
		loggedIn = new Log(currentUser);
		return loggedIn;
	}
	
	public static Log getInstance() throws NoSuchObjectException {
		if(loggedIn == null) {
			throw new NoSuchObjectException("No one is logged in");
		}
		return loggedIn;
	}
	
	public User getCurrentUser() {
		return currentUser;
	}
	
}
