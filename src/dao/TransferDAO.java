package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DBManager;
import model.Transfer;

public class TransferDAO implements CRUD<Transfer> {

	@Override
	public void Insert(Transfer t) throws Exception {
		String sql = "INSERT INTO transfers(dstId,originId,balance,date) VALUES('" + t.getDstId() + "','"
				+ t.getOriginId() + "','" + t.getBalance() + "','" + t.getDate() + "');";
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
	public void Update(Transfer t) throws Exception {
		String sql = "UPDATE transfers SET dstId=" + t.getDstId() + ",originId=" + t.getOriginId() + ",balance="
				+ t.getBalance() + ",date=" + t.getDate() + " WHERE " + "id=" + t.getId();
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
	public Transfer Read(int id) throws Exception {
		String sql = "SELECT * FROM transfers WHERE id=" + id;
		Connection c = DBManager.connect();
		Transfer transfer = new Transfer();
		try {
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

		return transfer;
	}

	public Transfer ReadCBU(Long CBU) throws Exception {
		String sql = "SELECT * FROM transfers WHERE cbu=" + CBU;
		Connection c = DBManager.connect();
		Transfer transfer = new Transfer();
		try {
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

		return transfer;
	}

	public Transfer ReadAlias(String alias) throws Exception {
		String sql = "SELECT * FROM transfers WHERE alias='" + alias + "'";
		Connection c = DBManager.connect();
		Transfer transfer = new Transfer();
		try {
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

		return transfer;
	}

	@Override
	public List<Transfer> ReadPool() throws Exception {
		List<Transfer> list = new ArrayList<Transfer>();
		String sql = "SELECT * FROM transfers";
		Connection c = DBManager.connect();
		try {
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
	public void Delete(int id) throws Exception {
		String sql = "DELETE FROM transfers WHERE id=" + id;
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
