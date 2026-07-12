package db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import exception.ServiceException;

public class TableManager {

	public void createTableAdmin() {
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
				throw new ServiceException(e.getMessage(), e);
			} catch (SQLException er) {
				throw new ServiceException(er.getMessage(), er);
			}
		} finally {
			try {
				c.close();
			} catch (SQLException e) {
				throw new ServiceException(e.getMessage(), e);
			}
		}
	}

	public void createTableClient() {
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
				throw new ServiceException(e.getMessage(), e);
			} catch (SQLException er) {
				throw new ServiceException(e.getMessage(), e);
			}
		} finally {
			try {
				c.close();
			} catch (SQLException e) {
				throw new ServiceException(e.getMessage(), e);
			}
		}
	}

	public void createTableAccount() {
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
			} catch (SQLException er) {
				throw new ServiceException(e.getMessage(), e);
			}
		} finally {
			try {
				c.close();
			} catch (SQLException e) {
				throw new ServiceException(e.getMessage(), e);
			}
		}
	}

	public void createTableTransfer() {
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
				throw new ServiceException(e.getMessage(), e);
			} catch (SQLException er) {
				throw new ServiceException(e.getMessage(), e);
			}
		} finally {
			try {
				c.close();
			} catch (Exception e) {
				throw new ServiceException(e.getMessage(), e);
			}
		}
	}

	public void createTableCard() {
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
				throw new ServiceException(e.getMessage(), e);
			} catch (SQLException er) {
				throw new ServiceException(e.getMessage(), e);
			}
		} finally {
			try {
				c.close();
			} catch (SQLException e) {
				throw new ServiceException(e.getMessage(), e);
			}
		}
	}

	public void dropTable(String name) {

		Connection c = null;
		String sql = "drop table " + name;

		try {
			c = DBManager.connect();
			Statement s = c.createStatement();
			s.execute(sql);
		} catch (SQLException e) {
			try {
				c.rollback();
				throw new ServiceException(e.getMessage(), e);
			} catch (SQLException er) {
				throw new ServiceException(e.getMessage(), e);
			}
		} finally {
			try {
				c.close();
			} catch (SQLException e) {
				throw new ServiceException(e.getMessage(), e);
			}
		}
	}
}
