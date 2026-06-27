package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DBManager;
import model.Client;

public class ClientDAO implements CRUD<Client> {

	@Override
	public void Insert(Client client) throws Exception {
		String sql = "INSERT INTO users(name,email,pass,dni,admin) VALUES('" + client.getName() + "','"
				+ client.getEmail() + "','" + client.getPass() + "','" + client.getDni() + "','" + client.getAdmin()
				+ "');";
		Connection c = DBManager.connect();
		try {
			Statement s = c.createStatement();
			s.execute(sql);
		} catch (SQLException e) {
			try {
				c.rollback();
				throw new Exception();
			} catch (SQLException er) {
				throw new Exception(er);
			}
		} finally {
			try {
				c.close();
			} catch (SQLException e) {
				throw new Exception(e);
			}
		}

	}

	@Override
	public void Update(Client client) throws Exception {
		String sql = "UPDATE users SET name=" + client.getName() + ",email=" + client.getEmail() + ",pass="
				+ client.getPass() + " WHERE " + "dni=" + client.getDni();
		Connection c = DBManager.connect();
		try {
			Statement s = c.createStatement();
			s.execute(sql);
		} catch (SQLException e) {
			try {
				c.rollback();
				throw new Exception(e);
			} catch (SQLException er) {
				throw new Exception(er);
			}
		} finally {
			try {
				c.close();
			} catch (SQLException e) {
				throw new Exception(e);
			}
		}

	}

	@Override
	public Client Read(int dni) throws Exception {
		String sql = "SELECT * FROM users WHERE dni=" + dni;
		Connection c = DBManager.connect();
		Client client = new Client();
		try {
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			if (rs.next()) {
				client.setId(rs.getInt("id"));
				client.setDni(rs.getInt("dni"));
				client.setEmail(rs.getString("email"));
				client.setName(rs.getString("name"));
				client.setPass(rs.getString("pass"));
				client.setAdmin(rs.getBoolean("admin"));
			}
		} catch (SQLException e) {
			try {
				c.rollback();
				throw new Exception(e);
			} catch (SQLException er) {
				throw new Exception(er);
			}
		} finally {
			try {
				c.close();
			} catch (SQLException e) {
				throw new Exception(e);
			}
		}

		return client;
	}

	public Client ReadId(int id) throws Exception {
		String sql = "SELECT * FROM users WHERE id=" + id;
		Connection c = DBManager.connect();
		Client client = new Client();
		try {
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			if (rs.next()) {
				client.setId(rs.getInt("id"));
				client.setDni(rs.getInt("dni"));
				client.setEmail(rs.getString("email"));
				client.setName(rs.getString("name"));
				client.setPass(rs.getString("pass"));
				client.setAdmin(rs.getBoolean("admin"));
			}
		} catch (SQLException e) {
			try {
				c.rollback();
				throw new Exception(e);
			} catch (SQLException er) {
				throw new Exception(er);
			}
		} finally {
			try {
				c.close();
			} catch (SQLException e) {
				throw new Exception(e);
			}
		}

		return client;
	}
	
	public Client Read(String email) throws Exception {
		String sql = "SELECT * FROM users WHERE email='" + email+"'";
		Connection c = DBManager.connect();
		Client client = new Client();
		try {
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			if (rs.next()) {
				client.setId(rs.getInt("id"));
				client.setDni(rs.getInt("dni"));
				client.setEmail(rs.getString("email"));
				client.setName(rs.getString("name"));
				client.setPass(rs.getString("pass"));
				client.setAdmin(rs.getBoolean("admin"));
			}
		} catch (SQLException e) {
			try {
				c.rollback();
				throw new Exception(e);
			} catch (SQLException er) {
				throw new Exception(er);
			}
		} finally {
			try {
				c.close();
			} catch (SQLException e) {
				throw new Exception(e);
			}
		}

		return client;
	}
	
	
	@Override
	public List<Client> ReadPool() throws Exception {
		List<Client> list = new ArrayList<Client>();
		String sql = "SELECT * FROM users";
		Connection c = DBManager.connect();
		try {
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			while (rs.next()) {
				Client user = new Client();
				user.setId(rs.getInt("id"));
				user.setDni(rs.getInt("dni"));
				user.setEmail(rs.getString("email"));
				user.setName(rs.getString("name"));
				user.setPass(rs.getString("pass"));
				list.add(user);
			}
		} catch (SQLException e) {
			try {
				c.rollback();
				throw new Exception(e);
			} catch (SQLException er) {
				throw new Exception(er);
			}
		} finally {
			try {
				c.close();
			} catch (SQLException e) {
				throw new Exception(e);
			}
		}

		return list;
	}

	@Override
	public void Delete(int dni) throws Exception {
		String sql = "DELETE FROM users WHERE dni=" + dni;
		Connection c = DBManager.connect();
		try {
			Statement s = c.createStatement();
			s.execute(sql);
		} catch (SQLException e) {
			try {
				c.rollback();
				throw new Exception(e);
			} catch (SQLException er) {
				throw new Exception(er);
			}
		} finally {
			try {
				c.close();
			} catch (SQLException e) {
				throw new Exception(e);
			}
		}
	}

}
