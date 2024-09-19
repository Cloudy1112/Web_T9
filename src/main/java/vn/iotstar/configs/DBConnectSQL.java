package vn.iotstar.configs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.eclipse.tags.shaded.org.apache.regexp.recompile;

import com.mysql.cj.jdbc.Driver;

public class DBConnectSQL {
	private final static String serverName = "VAN\\VAN";
	private final static String dbName = "ltweb";
	private final static String portNumber = "1433";
	private final static String instance = "";
	private final static String userID = "sa";
	private final static String password = "van123";

	public static Connection getDatabaseConnection() throws Exception {
		String url = "jdbc:sqlserver://" + serverName + "\\" 
	+instance+":"+ portNumber + ";databaseName=" + dbName;;


			if (instance == null || instance.trim().isEmpty()) {
				url = "jdbc:sqlserver://" + serverName + ":" + portNumber + ";databaseName=" + dbName;
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				return DriverManager.getConnection(url, userID, password);
			}
			return null;
	}

	public static void main(String[] args) {
		try {
			//new DBConnectSQL();
			System.out.println(DBConnectSQL.getDatabaseConnection());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
