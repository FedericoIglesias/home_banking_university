package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DBManager;
import exception.DAOException;
import model.Transfer;

public class TransferDAO implements CRUD<Transfer> {

	@Override
	public void Insert(Transfer t) {
		String sql = "INSERT INTO transfers(dstId,originId,balance,date) VALUES('" + t.getDstId() + "','"
				+ t.getOriginId() + "','" + t.getBalance() + "','" + t.getDate() + "');";
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
	public void Update(Transfer t){
		String sql = "UPDATE transfers SET dstId=" + t.getDstId() + ",originId=" + t.getOriginId() + ",balance="
				+ t.getBalance() + ",date=" + t.getDate() + " WHERE " + "id=" + t.getId();
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
	public Transfer Read(int id) {
		String sql = "SELECT * FROM transfers WHERE id=" + id;
		Connection c = null;
		Transfer transfer = new Transfer();
		try {c = DBManager.connect();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			if (rs.next()) {
				transfer.setId(rs.getInt("id"));
				transfer.setBalance(rs.getInt("balance"));
				transfer.setDstId(rs.getInt("dstId"));
				transfer.setOriginId(rs.getInt("originId"));
				transfer.setDate(rs.getLong("date"));
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

		return transfer;
	}

	public Transfer ReadCBU(Long CBU) {
		String sql = "SELECT * FROM transfers WHERE cbu=" + CBU;
		Connection c = null;
		Transfer transfer = new Transfer();
		try {
			c = DBManager.connect();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			if (rs.next()) {
				transfer.setId(rs.getInt("id"));
				transfer.setBalance(rs.getInt("balance"));
				transfer.setDstId(rs.getInt("dstId"));
				transfer.setOriginId(rs.getInt("originId"));
				transfer.setDate(rs.getLong("date"));
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

		return transfer;
	}

	public Transfer ReadAlias(String alias) {
		String sql = "SELECT * FROM transfers WHERE alias='" + alias + "'";
		Connection c = null;
		Transfer transfer = new Transfer();
		try {
			c = DBManager.connect();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			if (rs.next()) {
				transfer.setId(rs.getInt("id"));
				transfer.setBalance(rs.getInt("balance"));
				transfer.setDstId(rs.getInt("dstId"));
				transfer.setOriginId(rs.getInt("originId"));
				transfer.setDate(rs.getLong("date"));
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

		return transfer;
	}

	@Override
	public List<Transfer> ReadPool(){
		List<Transfer> list = new ArrayList<Transfer>();
		String sql = "SELECT * FROM transfers";
		Connection c = null;
		try {
			c = DBManager.connect();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			while (rs.next()) {
				Transfer transfer = new Transfer();
				transfer.setId(rs.getInt("id"));
				transfer.setBalance(rs.getInt("balance"));
				transfer.setDstId(rs.getInt("dstId"));
				transfer.setOriginId(rs.getInt("originId"));
				transfer.setDate(rs.getLong("date"));
				list.add(transfer);
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
		String sql = "DELETE FROM transfers WHERE id=" + id;
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

	public List<Transfer> ReadTrCl(int id) {
		List<Transfer> list = new ArrayList<Transfer>();
		String sql = "select  * from transfers where dstid in (SELECT id FROM ACCOUNTS where clientId =" + id
				+ ") or originid in (SELECT id FROM ACCOUNTS where clientId = " + id + ")";
		Connection c = null;
		try {
			c = DBManager.connect();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			while (rs.next()) {
				Transfer transfer = new Transfer();
				transfer.setId(rs.getInt("id"));
				transfer.setBalance(rs.getInt("balance"));
				transfer.setDstId(rs.getInt("dstId"));
				transfer.setOriginId(rs.getInt("originId"));
				transfer.setDate(rs.getLong("date"));
				list.add(transfer);
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

}
