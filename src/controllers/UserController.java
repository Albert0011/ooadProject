package controllers;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.rmi.NoSuchObjectException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;

import helpers.Log;
import helpers.SHA1Encryption;
import models.User;
import views.AllUserDisplay;
import views.ChangePasswordForm;
import views.CreateUserDisplay;
import views.ProfileDisplay;
import views.UpdateProfileForm;
import views.UserProfileDisplay;
public class UserController {

	private static UserController userController;
	
	public static final int[] JUMLAHHARI = {
			31,28,31,30,31,30,31,31,30,31,30,31
	};

	public static UserController getInstance() {
		if(userController == null) {
			userController = new UserController();
		}
		return userController;
	}
	
	
	public static boolean isLeapYear(int year) {
		if(((year%4 == 0) && !(year%100 == 0)) || year%400 == 0) {
			return true;
		}
		return false;
	}
	
	public static boolean isValidDate(int day, int month, int year) {
		if(month == 2 && isLeapYear(year)) {
			if(day > 29 || day < 1) return false;
		}
		else if(month == 2 && !isLeapYear(year)) {
			if(day > 28 || day < 1) return false;
		}
		else {
			if(day < 1 || day > JUMLAHHARI[month-1]) return false;
		}
		return true;
	}
	

	public UserProfileDisplay openUserProfileDisplay() throws NoSuchObjectException {
		UserProfileDisplay up = new UserProfileDisplay();
		
			try {
				up.refreshContent(openProfileDisplay());
			} catch (NoSuchObjectException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
				up.getViewProfileBtn().addActionListener(new ActionListener() {
				
					@Override
					public void actionPerformed(ActionEvent e) {
						try {
							up.refreshContent(openProfileDisplay());

							MainController.getInstance().refreshContent(up);
					
						} catch (NoSuchObjectException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					
				 
						try {
							MainController.getInstance().refreshContent(up);
						} catch (NoSuchObjectException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}
				});
			
				up.getChangePassBtn().addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						up.refreshContent(openChangePasswordForm());
						
						try {
							MainController.getInstance().refreshContent(up);
						} catch (NoSuchObjectException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					}
				});
				
				up.getUpdateProfileBtn().addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						up.refreshContent(openUpdateProfileForm());
						try {
							MainController.getInstance().refreshContent(up);
						} catch (NoSuchObjectException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
		
		return up;
	}
	
	
	public ProfileDisplay openProfileDisplay() throws NoSuchObjectException {
		
		User user = Log.getInstance().getCurrentUser();

		ProfileDisplay pd = new ProfileDisplay(user);

		return pd;
	}
	
	public CreateUserDisplay openCreateUserDisplay() {
		CreateUserDisplay cud = new CreateUserDisplay();
		
		cud.getCreateUserButton().addActionListener(new ActionListener() {
			
			
		
			public void actionPerformed(ActionEvent arg0) {
				
				if(cud.getUnameField().getText().isEmpty() || ((String) cud.getRoleChoice().getSelectedItem()).isEmpty() || 
						cud.getAddressField().getText().isEmpty() || ((String) cud.getDayChoose().getSelectedItem()).isEmpty() || 
						((String) cud.getMonthChoose().getSelectedItem()).isEmpty() || ((String) cud.getYearChoose().getSelectedItem()).isEmpty()) {
					
					JOptionPane.showMessageDialog(null, "Please Complete All Data");
				
				}
				else {
					int year = Integer.parseInt(cud.getYearChoose().getSelectedItem().toString());
					int month = Integer.parseInt(cud.getMonthChoose().getSelectedItem().toString());
					int day = Integer.parseInt(cud.getDayChoose().getSelectedItem().toString());
				
					
					if(isValidDate(day, month, year) == true) {
						Date date1 = new GregorianCalendar(year, month-1, day).getTime();
						try {
							UserController.createUser(cud.getUnameField().getText(), cud.getRoleChoice().getSelectedItem().toString(), date1 , cud.getAddressField().getText(), cud.getTelpField().getText());
							JOptionPane.showMessageDialog(null, "Create User Success!");
						} catch (NoSuchAlgorithmException | UnsupportedEncodingException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (NoSuchObjectException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}		
						
						try {
							MainController.getInstance().refreshContent(openCreateUserDisplay());
						} catch (NoSuchObjectException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
					else {
						JOptionPane.showMessageDialog(null, "Date is not valid!");
					}
					
				}
			}
		});

		return cud;
	}
	
	public AllUserDisplay openAllUserDisplay() {
		
		ArrayList<User> user = getAllUser();
		AllUserDisplay allUserDisplay = new AllUserDisplay(user);
		
		allUserDisplay.getBtnDeleteUser().addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				if(allUserDisplay.getViewAllTable().getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "Please Select User");
				}
				else {
					int jawab = JOptionPane.showConfirmDialog(null, "Are you sure to delete this user?");
					switch (jawab) {
					case JOptionPane.YES_OPTION:
							int row = allUserDisplay.getViewAllTable().getSelectedRow();
							String userId = (allUserDisplay.getViewAllTable().getValueAt(row, 0)).toString();
							UserController.deleteUser(userId);
							
						try {
							MainController.getInstance().refreshContent(openAllUserDisplay());
						} catch (NoSuchObjectException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
							JOptionPane.showMessageDialog(null, "Delete User Success!! ");
							
						break;
					case JOptionPane.NO_OPTION:
					
						break;
					case JOptionPane.CANCEL_OPTION:
					
						break;

					default:
						break;
					}

				}
		
			}
		});
		
		allUserDisplay.getBtnResetPassword().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(allUserDisplay.getViewAllTable().getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "Please Select User");
				}
				else if((allUserDisplay.getPassField().getText()).equals(allUserDisplay.getDobField().getText())){
						JOptionPane.showMessageDialog(null, "Password still default!");
				}
				else {

					int jawab = JOptionPane.showConfirmDialog(null, "Are you sure to reset this user password?");
					switch (jawab) {
					case JOptionPane.YES_OPTION:
						int row = allUserDisplay.getViewAllTable().getSelectedRow();
						String userId = (allUserDisplay.getViewAllTable().getValueAt(row, 0)).toString();
						try {
							UserController.resetPassword(userId);
						} catch (NoSuchAlgorithmException | UnsupportedEncodingException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
							
						try {
							MainController.getInstance().refreshContent(openAllUserDisplay());
						} catch (NoSuchObjectException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}				
							JOptionPane.showMessageDialog(null, "Reset password Success!!");
						break;
					case JOptionPane.NO_OPTION:
						
						break;
					case JOptionPane.CANCEL_OPTION:
					
						break;
	
					default:
						break;
						
					}

				}
		
			}
		});
		
		
		return allUserDisplay;
	
	}
	
	public ChangePasswordForm openChangePasswordForm() {
		
		ChangePasswordForm cp = new ChangePasswordForm();
		
		cp.getChangePassBtn().addActionListener(new ActionListener() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				String oldPass = cp.getOldPassField().getText();
				String newPass = cp.getNewPassField().getText();
				
				if(oldPass.isEmpty() || newPass.isEmpty()) {	
					JOptionPane.showMessageDialog(null, "Please Complete All Data");
				}
				else if(oldPass.length()<8 || oldPass.length()>12 || newPass.length()<8 || newPass.length()>12) {
					JOptionPane.showMessageDialog(null, "Password length not valid!");
				}
				else {
					int jawab = JOptionPane.showConfirmDialog(null, "Are you sure to change your password?");
					switch (jawab) {
					case JOptionPane.YES_OPTION:
						try {
							UserController.changePassword(oldPass, newPass);
							JOptionPane.showMessageDialog(null, "Change password Success!!");
							cp.emptyPassField();

						} catch (NoSuchObjectException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (NoSuchAlgorithmException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (UnsupportedEncodingException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}	
						
						
						//JOptionPane.showMessageDialog(null, "Change Password Success!");
						break;
					case JOptionPane.NO_OPTION:
						
						break;
					case JOptionPane.CANCEL_OPTION:
					
						break;
	
					default:
						break;
						
					}
					
				}
				
			}
		});
			
		return cp;
	}
	
	public UpdateProfileForm openUpdateProfileForm() {
		UpdateProfileForm up = new UpdateProfileForm();
		
		up.getUpdateButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(up.getUnameField().getText().isEmpty() ||up.getAddrField().getText().isEmpty() || up.getTelpField().getText().isEmpty() || ((String) up.getDayChoose().getSelectedItem()).isEmpty() || 
						((String) up.getMonthChoose().getSelectedItem()).isEmpty() || ((String) up.getYearChoose().getSelectedItem()).isEmpty()) {
					
					JOptionPane.showMessageDialog(null, "Please Complete All Data");
				
				}
				else if (up.getUnameField().getText().length() < 5 || up.getUnameField().getText().length() > 15 || up.getAddrField().getText().length() < 10 || up.getAddrField().getText().length()>100 || up.getTelpField().getText().length()<10 || up.getTelpField().getText().length()>13) {
					JOptionPane.showMessageDialog(null, "data not valid!! ");
				}
				else {
					int jawab = JOptionPane.showConfirmDialog(null, "Are you sure to update your profile?");
					switch (jawab) {
					case JOptionPane.YES_OPTION:
						int year = Integer.parseInt(up.getYearChoose().getSelectedItem().toString());
						int month = Integer.parseInt(up.getMonthChoose().getSelectedItem().toString());
						int day = Integer.parseInt(up.getDayChoose().getSelectedItem().toString());
						
						if(isValidDate(day, month, year) == true) {
							Date date = new GregorianCalendar(year, month-1, day).getTime();
							try {
								UserController.updateProfile(up.getUnameField().getText(), date, up.getAddrField().getText(), up.getTelpField().getText());
								JOptionPane.showMessageDialog(null, "Update profile success!");
								up.emptyUpdateField();

							} catch (NoSuchObjectException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						}
						else {
							JOptionPane.showMessageDialog(null, "Date is not valid!");
						}
						
						break;
					case JOptionPane.NO_OPTION:
						
						break;
					case JOptionPane.CANCEL_OPTION:
					
						break;
	
					default:
						break;
						
					}
					
					
				}
				
			}
		});
		return up;
	}
	
	public User getUserBy(String uname, String pass) throws NoSuchObjectException, NoSuchAlgorithmException, UnsupportedEncodingException {
		User user;
		
		try {
			
			user = User.getBy(uname, SHA1Encryption.SHA1(pass));
			//user = User.getBy(uname, pass);
			Log.createLog(user);
			
			JOptionPane.showMessageDialog(null, "Login success!");
			MainController.getInstance().disposeLoginFrame();
			
			if(user.getRole().equalsIgnoreCase("Admin")) {
				MainController.getInstance().displayAdminHomepage();
			}
			else if(user.getRole().equalsIgnoreCase("Supervisor")) {
				MainController.getInstance().displaySupervisorHomepage();
			}
			else {
				MainController.getInstance().displayWorkerHomepage();
			}
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "User not found!");
			return null;
		}
		
		
		return user;
	}

	
	public static User getUser(String userID) {
		User user;
		try {
			user = User.get(userID);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			return null;
		}
		return user;
	}
	
	public static void updateUser(User user) {
		
		try {
			user.update();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Update failed!! "+e.getMessage());
		}
	}
	
	public static User saveUser(User user) {
		try {
			user.save();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Save failed!! "+e.getMessage());
			return null;
		} 
		return user;
	}
	
	public static User createUser(String username, String role, Date DOB, String address, String telp) throws NoSuchAlgorithmException, UnsupportedEncodingException, NoSuchObjectException {
	
		if(validateUsername(username) == false) {
			throw new IllegalArgumentException("Username has been taken!");
		} else if(validateUsernameLength(username) == false) {
			throw new IllegalArgumentException("Username length must be between 5-15 characters length!");
		} else if(validateDOB(DOB) == false) {
			throw new IllegalArgumentException("Date of Birth must be in the past!");
		} else if(validateAddressLength(address) == false) { 
			throw new IllegalArgumentException("Address length must be 10-100 characters!");
		} else if(validateTelp(telp) == false) {
			throw new IllegalArgumentException("Phone number is not valid!");
		}
		
		User user = User.create(username, role, DOB, address, telp);
		return saveUser(user);
	}
	
	private static boolean validateTelp(String telp) {
		if(telp.length()>=10 || telp.length()<=13) {
			if(telp.matches("\\d{10}")) {
				return true;
			}
			return false;
		}
		return false;
	}


	private static boolean validateAddressLength(String address) {
		if(address.length()>=10 && address.length()<=100) {
			 return true;
		}
		return false;
	}


	private static boolean validateDOB(Date DOB) {
		java.sql.Date currentDate = java.sql.Date.valueOf(LocalDate.now());
		if(DOB.before(currentDate) == true) {
			return true;
		}
		return false;
	}


	private static boolean validateUsernameLength(String username) {
		if(username.length()>=5 && username.length()<=15) {
			 return true;
		}
		return false;
	}


	private static boolean validateUsername(String username) throws NoSuchObjectException {
		User currentUser = Log.getInstance().getCurrentUser();
		
		ArrayList<User> userList = getAllUser();
		for(User user: userList) {
			if(user.getUsername().equals(username) && user.getId().equals(currentUser.getId()) == false) {
				return false;
			}
		}
		
		return true;
		
	}


	public static ArrayList<User> getAllUser(){
		ArrayList<User> user = null;
		try {
			user = User.getAll();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Get All User Failed "+e.getMessage());
			return null;
		}
		return user;
	}
	
	public static void deleteUser(String id) {
		User user = getUser(id);
		try {
			user.delete();
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Delete User Failed!! "+e.getMessage());
		}
	}
	
	public static User resetPassword(String id) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		User user = getUser(id);
		
		java.sql.Date date= new java.sql.Date(user.getDOB().getTime());
		String defaultPassword = date.toString();
		user.setPassword(defaultPassword);
		
		updateUser(user);
		
		return user;
	}
	
	public static User changePassword(String oldPassword, String newPassword) throws NoSuchObjectException, NoSuchAlgorithmException, UnsupportedEncodingException {

		User user = Log.getInstance().getCurrentUser();
		
		oldPassword = SHA1Encryption.SHA1(oldPassword);
		
		if(oldPassword.equals(user.getPassword())) {
			user.setPassword(newPassword);
			updateUser(user);
			
			return user;
		}
		else {
			JOptionPane.showMessageDialog(null, "Old password not match!!");
			return null;
		}
	}
	
	public static User updateProfile(String username, Date DOB, String address, String telp) throws NoSuchObjectException {

		
		
		User user = Log.getInstance().getCurrentUser();
		user.setUsername(username);
		user.setDOB(DOB);
		user.setAddress(address);
		user.setTelp(telp);
		
		updateUser(user);
		return user;
	}
	
	

}