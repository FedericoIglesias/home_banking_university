package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import exception.ServiceException;

public class DBManager {
	private static final String DB_DRIVER = "org.h2.Driver";
	private static final String DB_BASE_URL = new URL().getDB_BASE_URL();
	private static final String DB_USERNAME = "sa";
	private static final String DB_PASSWORD = "";

	public static Connection connect() {
		Connection c = null;
		try {
			Class.forName(DB_DRIVER);
		} catch (ClassNotFoundException e) {
			throw new ServiceException(e.getMessage(), e);
		}
		try {
			c = DriverManager.getConnection(DB_BASE_URL, DB_USERNAME, DB_PASSWORD);
		} catch (SQLException e) {
			throw new ServiceException(e.getMessage(), e);
		}

		return c;
	}

}
