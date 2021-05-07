package model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import config.MyConnection;

/**
 * Abstract Class active Record Base
 * 
 * @author Uriel
 *
 */
public abstract class ActiveRecordBase {
	
	/**
	 * Id of the class
	 */
	protected int id;
	
	/**
	 * True or False if is on the databse or not
	 */
	protected boolean _builtFromDB;
	
	/**
	 * SQL String to update a register
	 * @return
	 */
	protected abstract String _update();
	
	/**
	 * SQL String to insert on the Database
	 * @return
	 */
	protected abstract String _insert();
	
	/**
	 * SQL String to delete from the database
	 * @return
	 */
	protected abstract String _delete();
	
	/**
	 * Method to modify a eguster on the Database
	 */
	public void save() throws SQLException, IOException, ClassNotFoundException {
		Connection conn = MyConnection.getInstance();
		Statement s = conn.createStatement();
		if (_builtFromDB) {
			System.out.println("Executing: " + _update());
			s.executeUpdate(_update());
		} else {
			
			System.out.println("Executing: " + _insert());
			s.executeLargeUpdate(_insert(), Statement.RETURN_GENERATED_KEYS);
			_builtFromDB = true;
			ResultSet r = s.getGeneratedKeys();
			while (r.next()) {
				id = r.getInt(1);
			}
		}

	}
	
	/**
	 * Method to delete from the Database
	 * 
	 */
	public void delete() throws IOException, ClassNotFoundException, SQLException {
		Connection conn = MyConnection.getInstance();
		Statement s = conn.createStatement();
		if (_builtFromDB) {
			System.out.println("Executing this command: " + _delete() + "\n");
			s.executeUpdate(_delete());

		} else {
			System.out.println("Objet non persistant!");
		}
	}
	
	/**
	 * Getter Id
	 * @return id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Setter Id
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

}
