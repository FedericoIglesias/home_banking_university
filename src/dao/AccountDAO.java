package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DBManager;
import exception.DAOException;
import model.Account;

public class AccountDAO implements CRUD<Account> {

	@Override
	public void Insert(Account t) {
		String sql = "INSERT INTO accounts(alias,cbu,balance,type,clientId) VALUES('" + t.getAlias() + "','"
				+ t.getCBU() + "','" + t.getBalance() + "','" + t.getType() + "','" + t.getClient() + "');";
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
	public void Update(Account t) {
		String sql = "UPDATE accounts SET balance=" + t.getBalance() + " WHERE " + "id=" + t.getId();
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
	public Account Read(int id) {
		String sql = "SELECT * FROM accounts WHERE id=" + id;
		Connection c = null;
		Account a = new Account();
		try {
			c = DBManager.connect();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			if (rs.next()) {
				a.setId(rs.getInt("id"));
				a.setBalance(rs.getInt("balance"));
				a.setType(rs.getString("type"));
				a.setCBU(rs.getString("CBU"));
				a.setAlias(rs.getString("alias"));
				a.setClient(rs.getInt("clientId"));
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

		return a;
	}

	public Account ReadAlias(String alias) {
		String sql = "SELECT * FROM accounts WHERE alias='" + alias + "'";
		Connection c = null;
		Account a = new Account();
		try {
			c = DBManager.connect();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			if (rs.next()) {
				a.setId(rs.getInt("id"));
				a.setBalance(rs.getInt("balance"));
				a.setType(rs.getString("type"));
				a.setCBU(rs.getString("CBU"));
				a.setAlias(rs.getString("alias"));
				a.setClient(rs.getInt("clientId"));
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

		return a;
	}

	public Account ReadCBU(String cbu) {
		String sql = "SELECT * FROM accounts WHERE cbu=" + cbu;
		Connection c = null;
		Account a = new Account();
		try {
			c = DBManager.connect();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			if (rs.next()) {
				a.setId(rs.getInt("id"));
				a.setBalance(rs.getInt("balance"));
				a.setType(rs.getString("type"));
				a.setCBU(rs.getString("CBU"));
				a.setAlias(rs.getString("alias"));
				a.setClient(rs.getInt("clientId"));
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

		return a;
	}

	public List<Account> ReadClientId(int id) {
		List<Account> list = new ArrayList<Account>();
		String sql = "SELECT * FROM accounts where clientId=" + id;
		Connection c = null;
		try {
			c = DBManager.connect();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			while (rs.next()) {
				Account a = new Account();
				a.setId(rs.getInt("id"));
				a.setBalance(rs.getInt("balance"));
				a.setType(rs.getString("type"));
				a.setCBU(rs.getString("CBU"));
				a.setAlias(rs.getString("alias"));
				a.setClient(rs.getInt("clientId"));
				list.add(a);
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
	public List<Account> ReadPool() {
		List<Account> list = new ArrayList<Account>();
		String sql = "SELECT * FROM accounts";
		Connection c = null;
		try {
			c = DBManager.connect();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			while (rs.next()) {
				Account a = new Account();
				a.setId(rs.getInt("id"));
				a.setBalance(rs.getInt("balance"));
				a.setType(rs.getString("type"));
				a.setCBU(rs.getString("CBU"));
				a.setAlias(rs.getString("alias"));
				a.setClient(rs.getInt("clientId"));
				list.add(a);
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
	public void Delete(int id) {
		String sql = "DELETE FROM accounts WHERE id=" + id;
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
