package model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import config.MyConnection;

public class User extends ActiveRecordBase{
	
	private String email;
	private String nickname;
	private String password;
	private String role;
	
	public User() {
		this._builtFromDB = false;
	}
	
	public User(String _email, String _nickname, String _password, String _role) {
		
		this._builtFromDB = false;
		this.email = _email;
		this.nickname = _nickname;
		this.password = _password;
		this.role = _role;
		
	}
	
	public User (String _email, String _nickname, String _password, String _role, boolean state) {
		
		this._builtFromDB = state;
		this.email = _email;
		this.nickname = _nickname;
		this.password = _password;
		this.role = _role;
		
	}
	
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

	@Override
	protected String _update() {
		
		int roleValue = (this.role.equals("Admin")) ? 1 : 2 ;
		
		return "UPDATE User "
				+ "SET password = '" + this.password + "' ,"
				+ " nickname = '"+ this.nickname +"' ,"
				+ " role = "+ roleValue +""
				+ " WHERE email = '" + this.email + " '";
	}

	@Override
	protected String _insert() {
		
		int role = ("Admin".equals(this.role)) ? 1  : 2 ;
		
		return "INSERT INTO User (email, nickname, password,  role)"
				+ " VALUES ('" + this.email + "' , '" + this.nickname + "' ,  '" +  this.password  +"', "+ role +")";
	}

	@Override
	protected String _delete() {
		
		return "DELETE FROM User "
				+ "WHERE email = '" +  this.email  + "'";
	}

}
