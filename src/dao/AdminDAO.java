package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DBManager;
import model.Admin;

public class AdminDAO implements CRUD<Admin> {

	static DBManager db = new DBManager();

	@Override
	public void Insert(Admin admin) throws Exception {
		String sql = "INSERT INTO admins(name,email,pass,dni) VALUES('" + admin.getName() + "','" + admin.getEmail()
				+ "','" + admin.getPass() + "','" + admin.getDni() + "');";
		Connection c = db.connect();
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
	public void Update(Admin admin) throws Exception {
		String sql = "UPDATE admins SET name=" + admin.getName() + ",email=" + admin.getEmail() + ",pass="
				+ admin.getPass() + " WHERE " + "dni=" + admin.getDni();
		Connection c = db.connect();
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
	public Admin Read(int dni) throws Exception {
		String sql = "SELECT * FROM admins WHERE dni=" + dni;
		Connection c = db.connect();
		Admin admin = new Admin();
		try {
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			if (rs.next()) {
				admin.setId(rs.getInt("id"));
				admin.setDni(rs.getInt("dni"));
				admin.setEmail(rs.getString("email"));
				admin.setName(rs.getString("name"));
				admin.setPass(rs.getString("pass"));
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

		return admin;
	}

	@Override
	public List<Admin> ReadPool() throws Exception {
		List<Admin> list = new ArrayList<Admin>();
		String sql = "SELECT * FROM users";
		Connection c = db.connect();
		Admin user = new Admin();
		try {
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			while (rs.next()) {
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
		Connection c = db.connect();
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
