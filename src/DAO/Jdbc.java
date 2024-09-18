package  src.DAO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBC {

    private static Connection conn = null;
   
    public static Connection getConnection() throws SQLException {
        if (conn == null) {
			try {
				Properties props = loadProperties();
				String url = props.getProperty("dburl");
				conn = DriverManager.getConnection(url,props);
			}
			catch (SQLException e ) {
				throw new DbException(e.getMessage());
			}
		}
		return conn;
    }
    // Método para carregar as propriedades do db.properties
    private static Properties loadProperties() {
        try(FileInputStream fs = new FileInputStream("db.properties")){
			Properties props = new Properties();
			props.load(fs); //ler e guarda os dados do arv properties no obj props
			return props;
		}
		catch(IOException e ) {
			throw new DbException(e.getMessage());
		}
    }
    
}
