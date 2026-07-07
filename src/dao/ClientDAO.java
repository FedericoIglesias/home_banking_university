package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DBManager;
import exception.DAOException;
import model.Client;

public class ClientDAO implements CRUD<Client> {

	@Override
	public void Insert(Client client){
		String sql = "INSERT INTO users(name,email,pass,dni,admin) VALUES('" + client.getName() + "','"
				+ client.getEmail() + "','" + client.getPass() + "','" + client.getDni() + "','" + client.getAdmin()
				+ "');";
		Connection c = null;
		try {
			c = DBManager.connect();
			Statement s = c.createStatement();
			s.execute(sql);
		} catch (SQLException e) {
			try {
				c.rollback();
				if(e.getErrorCode() == 23505){
					throw new DAOException("DNI repetido", e);
				}else{
					throw new DAOException(e.getMessage(), e);
				}
			} catch (SQLException er) {
				throw new DAOException(er.getMessage(), er);
			}
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), e);
		} finally {
			try {
				c.close();
			} catch (SQLException e) {
				throw new DAOException(e.getMessage(), e);
			}
		}

	}

	@Override
	public void Update(Client client) {
		String sql = "UPDATE users SET name=" + client.getName() + ",email=" + client.getEmail() + ",pass="
				+ client.getPass() + " WHERE " + "dni=" + client.getDni();
		Connection c = null;
		try {
			c = DBManager.connect();
			Statement s = c.createStatement();
			s.execute(sql);
		} catch (SQLException e) {
			try {
				c.rollback();
				throw new DAOException(e.getMessage(), e);
			} catch (SQLException er) {
				throw new DAOException(er.getMessage(), er);
			}
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), e);
		} finally {
			try {
				c.close();
			} catch (SQLException e) {
				throw new DAOException(e.getMessage(), e);
			}
		}

	}

	@Override
	public Client Read(int dni) throws Exception {
		String sql = "SELECT * FROM users WHERE dni=" + dni;
		Client client = new Client();
		Connection c = null;
		try {
			c = DBManager.connect();
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
				throw new DAOException(e.getMessage(), e);
			} catch (SQLException er) {
				throw new DAOException(er.getMessage(), er);
			}
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), e);
		} finally {
			try {
				c.close();
			} catch (SQLException e) {
				throw new DAOException(e.getMessage(), e);
			}
		}

		return client;
	}

	public Client ReadId(int id) {
		String sql = "SELECT * FROM users WHERE id=" + id;
		Client client = new Client();
		Connection c = null;
		try {
			c = DBManager.connect();
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
				throw new DAOException(e.getMessage(), e);
			} catch (SQLException er) {
				throw new DAOException(er.getMessage(), er);
			}
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), e);
		} finally {
			try {
				c.close();
			} catch (SQLException e) {
				throw new DAOException(e.getMessage(), e);
			}
		}

		return client;
	}

	public Client Read(String email) {
		String sql = "SELECT * FROM users WHERE email='" + email + "'";
		Client client = new Client();
		Connection c = null;
		try {
			c = DBManager.connect();
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
				throw new DAOException(e.getMessage(), e);
			} catch (SQLException er) {
				throw new DAOException(er.getMessage(), er);
			}
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), e);
		} finally {
			try {
				c.close();
			} catch (SQLException e) {
				throw new DAOException(e.getMessage(), e);
			}
		}

		return client;
	}

	@Override
	public List<Client> ReadPool() {
		List<Client> list = new ArrayList<Client>();
		String sql = "SELECT * FROM users";
		Connection c = null;
		try {
			c = DBManager.connect();
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
				throw new DAOException(e.getMessage(), e);
			} catch (SQLException er) {
				throw new DAOException(er.getMessage(), er);
			}
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), e);
		} finally {
			try {
				c.close();
			} catch (SQLException e) {
				throw new DAOException(e.getMessage(), e);
			}
		}

		return list;
	}

	@Override
	public void Delete(int dni) {
		String sql = "DELETE FROM users WHERE dni=" + dni;
		Connection c = null;
		try {
			c = DBManager.connect();
			Statement s = c.createStatement();
			s.execute(sql);
		} catch (SQLException e) {
			try {
				c.rollback();
				throw new DAOException(e.getMessage(), e);
			} catch (SQLException er) {
				throw new DAOException(er.getMessage(), er);
			}
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), e);
		} finally {
			try {
				c.close();
			} catch (SQLException e) {
				throw new DAOException(e.getMessage(), e);
			}
		}
	}
}
