package me.Jhim.TackArena.MySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import me.Jhim.TacklArena.Main;

public class MySQL {

	Main plugin;
	private static Connection connection;
	private static String host, database, user, pass, JDBC_Driver = "com.mysql.jdbc.Driver";
	private static int port;
	
	public MySQL(Main plugin) {
		this.plugin = plugin;
	}

	
	public void openConnection() throws SQLException, ClassNotFoundException {
		host = plugin.getConfig().getString("MySQL.host");
		database = plugin.getConfig().getString("MySQL.database");
		user = plugin.getConfig().getString("MySQL.user");
		pass = plugin.getConfig().getString("MySQL.pass");
		port = plugin.getConfig().getInt("MySQL.port");
		Class.forName(JDBC_Driver);
		
		connection = DriverManager.getConnection("jdbc:mysql://" + MySQL.host + ":" + MySQL.port + "/" + MySQL.database, MySQL.user, MySQL.pass);
	}
	
	public void closeConnection() throws SQLException {
		connection.close();
	}
	
	public static Connection getConnection() {
		return connection;
	}

}
