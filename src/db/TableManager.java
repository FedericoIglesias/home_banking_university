package db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TableManager {

	public void createTableAdmin() throws Exception {
		Connection c = null;

		String sql = "CREATE TABLE IF NOT EXISTS admins (\r\n" + "    id INTEGER AUTO_INCREMENT PRIMARY KEY,\r\n"
				+ "    name VARCHAR(256),\r\n" + "    dni INTEGER,\r\n" + "    email VARCHAR(256),\r\n"
				+ "    pass VARCHAR(10)\r\n" + ");";

		try {
			c = DBManager.connect();
			Statement s = c.createStatement();
			s.execute(sql);
		} catch (SQLException e) {
			try {
				c.rollback();
				throw new Exception(e);
			} catch (SQLException er) {
				throw new Exception(er);
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			try {
				c.close();
			} catch (Exception e) {
				throw new Exception(e);
			}
		}
	}

	public void createTableClient() throws Exception {
		Connection c = null;
		String sql = "CREATE TABLE IF NOT EXISTS users (\r\n" + "    id INTEGER AUTO_INCREMENT PRIMARY KEY,\r\n"
				+ "    name VARCHAR(256),\r\n" + "    dni INTEGER UNIQUE,\r\n" + "    email VARCHAR(256),\r\n"
				+ "    pass VARCHAR(10),\r\n" + "    admin BOOLEAN \r\n" + ");";

		try {
			c = DBManager.connect();
			Statement s = c.createStatement();
			s.execute(sql);
		} catch (SQLException e) {
			try {
				c.rollback();
				throw new Exception(e);
			} catch (SQLException er) {
				throw new Exception(er);
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			try {
				c.close();
			} catch (Exception e) {
				throw new Exception(e);
			}
		}
	}

	public void createTableAccount() throws Exception {
		Connection c = null;
		String sql = "CREATE TABLE IF NOT EXISTS accounts (\r\n" + "    id INTEGER AUTO_INCREMENT PRIMARY KEY,\r\n"
				+ "    alias VARCHAR(256) UNIQUE,\r\n" + "    cbu VARCHAR(256) UNIQUE,\r\n" + "    balance INTEGER,\r\n"
				+ "    type VARCHAR(100),\r\n" + "    clientId INTEGER\r\n" + ");";

		try {
			c = DBManager.connect();
			Statement s = c.createStatement();
			s.execute(sql);
		} catch (SQLException e) {
			try {
				c.rollback();
				throw new Exception(e);
			} catch (SQLException er) {
				throw new Exception(e);
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			try {
				c.close();
			} catch (Exception e) {
				throw new Exception(e);
			}
		}
	}

	public void createTableTransfer() throws Exception {
		Connection c = null;
		String sql = "CREATE TABLE IF NOT EXISTS transfers (\r\n" + "    id INTEGER AUTO_INCREMENT PRIMARY KEY,\r\n"
				+ "    dstId INTEGER,\r\n" + "    originId INTEGER,\r\n" + "    balance INTEGER,\r\n"
				+ "	   date BIGINT\r\n" + ");";

		try {
			c = DBManager.connect();
			Statement s = c.createStatement();
			s.execute(sql);
		} catch (SQLException e) {
			try {
				c.rollback();
				throw new Exception(e);
			} catch (SQLException er) {
				throw new Exception(e);
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			try {
				c.close();
			} catch (Exception e) {
				throw new Exception(e);
			}
		}
	}

	public void createTableCard() throws Exception {
		Connection c = null;
		String sql = "CREATE TABLE IF NOT EXISTS cards (\r\n" + "    number VARCHAR(256) UNIQUE PRIMARY KEY,\r\n"
				+ "    debt INTEGER,\r\n" + "    limite INTEGER,\r\n" + "    clientId INTEGER\r\n"
				+ ");";

		try {
			c = DBManager.connect();
			Statement s = c.createStatement();
			s.execute(sql);
		} catch (SQLException e) {
			try {
				c.rollback();
				throw new Exception(e);
			} catch (SQLException er) {
				throw new Exception(er);
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			try {
				c.close();
			} catch (Exception e) {
				throw new Exception(e);
			}
		}
	}

	public void dropTable(String name) throws Exception {

		Connection c = null;
		String sql = "drop table " + name;

		try {
			c = DBManager.connect();
			Statement s = c.createStatement();
			s.execute(sql);
		} catch (SQLException e) {
			try {
				c.rollback();
				throw new Exception(e);
			} catch (SQLException er) {
				throw new Exception(er);
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			try {
				c.close();
			} catch (Exception e) {
				throw new Exception(e);
			}
		}
	}
}
