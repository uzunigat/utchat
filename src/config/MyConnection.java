package config;

import java.io.BufferedInputStream;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Connection to the Database 
 * 
 * @author Uriel
 *
 */
public class MyConnection { // implémenté sous forme de singleton //
	
	/**
	 * Unique Instance
	 */
	private static Connection singleton;
	
	/**
	 * Constructor by Default
	 * 
	 */
	private MyConnection() throws IOException, ClassNotFoundException, SQLException {
		
		// Read file properties
		Properties param = new Properties();
		URL urlFichierProp = MyConnection.class.getResource("./DBconfig.properties");
		if (urlFichierProp == null) {
			throw new IOException("Fichier " + "DBconfig" + " pas trouvé !");
		}
		BufferedInputStream bis = null;
		try {
			bis = new BufferedInputStream(urlFichierProp.openStream());
			param.load(bis);
			String driver = param.getProperty("driver");
			String host = param.getProperty("host");
			String user = param.getProperty("user");
			String password = param.getProperty("password");
			
			Class.forName(driver);
			singleton = DriverManager.getConnection(host, user, password);
		} finally {
			if (bis != null) {
				bis.close();
			}
		}
	}
	
	/**
	 * Return unique instance
	 */
	public static Connection getInstance() throws IOException, ClassNotFoundException, SQLException {
		if (singleton == null) {
			new MyConnection();
		}
		return singleton;
	}

}