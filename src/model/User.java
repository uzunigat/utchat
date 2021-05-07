package model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import config.MyConnection;

/**
 * User model
 * 
 * @author Uriel
 *
 */
public class User extends ActiveRecordBase{
	
	/**
	 * User email
	 */
	private String email;
	
	/**
	 * User nickname
	 */
	private String nickname;
	
	/**
	 * User password
	 */
	private String password;
	
	/**
	 * User role (Admin, Other)
	 */
	private String role;
	
	/**
	 * Constructor by default
	 */
	public User() {
		this._builtFromDB = false;
	}
	
	/**
	 * Constructor
	 * 
	 * @param _email
	 * @param _nickname
	 * @param _password
	 * @param _role
	 */
	public User(String _email, String _nickname, String _password, String _role) {
		
		this._builtFromDB = false;
		this.email = _email;
		this.nickname = _nickname;
		this.password = _password;
		this.role = _role;
		
	}
	
	/** 
	 * Constructor
	 * 
	 * @param _email
	 * @param _nickname
	 * @param _password
	 * @param _role
	 * @param state
	 */
	public User (String _email, String _nickname, String _password, String _role, boolean state) {
		
		this._builtFromDB = state;
		this.email = _email;
		this.nickname = _nickname;
		this.password = _password;
		this.role = _role;
		
	}
	
	// Getters
	public String getEmail() {
		return email;
	}
	
	public String getNickname() {
		return nickname;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getRole() {
		return role;
	}
	
	// Setters
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	/**
	 * Search an user by email
	 */
	public static User searchUser(String email) {
		
		try {
			Connection conn = MyConnection.getInstance();
			
			Statement stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery("SELECT * FROM User WHERE email = '" + email + "' ");
			
			if(res.next()) {
				
				User user = new User(res.getString("email"), res.getString("nickname"), res.getString("password"), res.getString("role"), true);
				return user;
				
			}
			
			else {
				
				return null;
				
			}
			
		} catch (ClassNotFoundException | IOException | SQLException e) {

			e.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 * Search an user by nickname (unique)
	 * 
	 * @param nickname
	 * @return
	 */
	public static User searchUserByNickname(String nickname) {
		
		try {
			Connection conn = MyConnection.getInstance();
			
			Statement stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery("SELECT * FROM User WHERE nickname = '" + nickname + "' ");
			
			if(res.next()) {
				
				User user = new User(res.getString("email"), res.getString("nickname"), res.getString("password"), res.getString("role"), true);
				return user;
				
			}
			
			else {
				
				return null;
				
			}
			
		} catch (ClassNotFoundException | IOException | SQLException e) {

			e.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 * Search all members method
	 * 
	 * @return a vector of all the users in the database
	 */
	public static Vector<String> SearchAllMembers() {
		
		Vector<String> members = new Vector<String>();
		
		Connection conn;
		try {
			conn = MyConnection.getInstance();
			
			String query = "SELECT * FROM User";
			
			Statement stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery(query);
			
			while(res.next()) {
				
				members.add(res.getString("nickname"));
				
			}
		} catch (ClassNotFoundException | IOException | SQLException e) {

			e.printStackTrace();
		}
	
		return members;
		
	}
	
	/**
	 * Surcharge of method herits from ARB
	 */
	@Override
	protected String _update() {
		
		int roleValue = (this.role.equals("Admin")) ? 1 : 2 ;
		
		return "UPDATE User "
				+ "SET password = '" + this.password + "' ,"
				+ " nickname = '"+ this.nickname +"' ,"
				+ " role = "+ roleValue +""
				+ " WHERE email = '" + this.email + " '";
	}
	
	/**
	 * Surcharge of method herits from ARB
	 */
	@Override
	protected String _insert() {
		
		int role = ("Admin".equals(this.role)) ? 1  : 2 ;
		
		return "INSERT INTO User (email, nickname, password,  role)"
				+ " VALUES ('" + this.email + "' , '" + this.nickname + "' ,  '" +  this.password  +"', "+ role +")";
	}

	/**
	 * Surcharge of method herits from ARB
	 */
	@Override
	protected String _delete() {
		
		return "DELETE FROM User "
				+ "WHERE email = '" +  this.email  + "'";
	}

}
