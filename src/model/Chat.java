package model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import config.MyConnection;

/**
 * Chat model that extends from Active Record base
 * @author Uriel
 *
 */
public class Chat extends ActiveRecordBase{
	
	/**
	 * Title Chat
	 */
	private String title;
	
	/**
	 * Description Chat
	 */
	private String description;
	
	/**
	 * StartDate
	 */
	private Date startDate;
	
	/**
	 * EndDate
	 */
	private Date endDate;
	
	/**
	 * Constructor by default
	 */
	public Chat() {
		this.title = "";
		this.description = "";
		this.startDate = new Date();
		this.endDate = new Date();
	}
	
	/**
	 * Constructor
	 * 
	 * @param _title
	 * @param _description
	 * @param _startDate
	 * @param _endDate
	 */
	public Chat(String _title, String _description, Date _startDate, Date _endDate) {
		
		this.title = _title;
		this.description = _description;
		this.startDate = _startDate;
		this.endDate = _endDate;
		this._builtFromDB = false;
		
	}
	
	/**
	 * Constructor
	 * 
	 * @param _id
	 * @param _title
	 * @param _description
	 * @param _startDate
	 * @param _endDate
	 * @param state
	 */
	public Chat(int _id, String _title, String _description, Date _startDate, Date _endDate, boolean state) {
		
		this._builtFromDB = state;
		this.id = _id;
		this.title = _title;
		this.description = _description;
		this.startDate = _startDate;
		this.endDate = _endDate;
		this._builtFromDB = false;
		
	}
	
	// Getters
	public String getTitle() {
		return title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public Date getStartDate() {
		return startDate;
	}
	
	public Date getEndDate() {
		return endDate;
	}
	
	// Setters
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Surcharge of method herits from ARB
	 */
	@Override
	protected String _update() {
		
		DateFormat format = new SimpleDateFormat("YYYY-MM-dd HH:mm");
		
		return "UPDATE Chat SET "
				+ " description = '" + this.description + "' ,"
						+ "debut = '" + format.format(startDate) + "', "
							+ " duree = '"+ format.format(endDate) +"' "
								+ "WHERE titre = '"+ this.title +"'";
							 
	}

	/**
	 * Surcharge of method herits from ARB
	 */
	@Override
	protected String _insert() {
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");

		return "INSERT INTO Chat(titre, description, debut, duree)"
				+ " VALUES('"+ this.title +"', '"+ this.description +"', '" + simpleDateFormat.format(startDate) +"', '"+ simpleDateFormat.format(endDate) +"')";
	}

	/**
	 * Surcharge of method herits from ARB
	 */
	@Override
	protected String _delete() {

		return null;
	}
	
	/**
	 * Method to search all the chat by the email
	 * 
	 * @param email: Email to search their chats
	 * @param owner: 1 if it's the owner
	 * @return a Vector of Chats
	 */
	public static Vector<Chat> searchAllMyChats(String email, int owner) {
		
		String title = "";
		String desc = "";
		String debut = "";
		String fin = "";
		int id;
		
		Date dateDebut;
		Date dateFin;
		
		Chat currentChat = new Chat();
		
		try {
			
			Vector<Chat> chats = new Vector<Chat>();
			
			Connection conn = MyConnection.getInstance();
			Statement stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery("SELECT c.id, c.titre, c.description, c.debut, c.duree "
					+ "FROM chat AS c INNER JOIN owner AS o ON o.id = c.id "
					+ "WHERE o.email = '" + email + "' AND o.owner = "+owner+"");
			
			while(res.next()) {
				
				title = res.getString("titre");
				desc = res.getString("description");
				debut = res.getString("debut");
				fin = res.getString("duree");
				id = Integer.parseInt(res.getString("id"));
				
				dateDebut = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(debut);
				
				dateFin = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(fin);
				
				currentChat = new Chat(id, title, desc, dateDebut ,dateFin, true);
				
				chats.add(currentChat);
				
			}
			
			return chats;
			
		} catch (ClassNotFoundException | IOException | SQLException | ParseException e) {

			e.printStackTrace();
			return null;
		}
		
		
	}
	
	/**
	 * Search a Chat by Title (unique)
	 * 
	 * @param title
	 * @return chat found
	 */
	public static Chat searchChat(String title) {
		
		DateFormat format = new SimpleDateFormat("YYYY-MM-dd HH:mm");
		
		try {
			Connection conn = MyConnection.getInstance();
			Statement stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery("SELECT * FROM Chat WHERE titre = '" + title + "'");
			
			if(res.next()) {
				
				int id = Integer.parseInt(res.getString("id"));
				String titre = res.getString("titre");
				String description = res.getString("description");
				String debut = res.getString("debut");
				String duree = res.getString("duree");
				
				Chat chat = new Chat(id, title, description, format.parse(debut), format.parse(duree), true);
				
				chat._builtFromDB = true;

				return chat;
			}
			
			return null;
			
		} catch (ClassNotFoundException | IOException | SQLException | ParseException e) {

			e.printStackTrace();
			return null;
		}
		
		
	}
	
	/**
	 * Converts the chat to String
	 */
	@Override
	public String toString() {
		
		return "Title: " + this.title 
				+ " \nDescription: " + this.description 
				+ " \nStart Date: " + this.startDate.toLocaleString()
				+ " \nEnd Date: " + this.endDate.toLocaleString();
		
	}

}
