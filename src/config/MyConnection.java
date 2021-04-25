package config;

import java.io.BufferedInputStream;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MyConnection { // implémenté sous forme de singleton //
	private static Connection singleton;

	private MyConnection() throws IOException, ClassNotFoundException, SQLException {
		// protection
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

	public static Connection getInstance() throws IOException, ClassNotFoundException, SQLException {
		if (singleton == null) {
			new MyConnection();
		}
		return singleton;
	}

}