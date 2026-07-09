package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DBManager;
import exception.DAOException;
import model.Card;

public class CardDAO implements CRUD<Card> {

	@Override
	public void Insert(Card ca) {
		String sql = "INSERT INTO cards (number,limite,clientId,debt) VALUES('" + ca.getNumber() + "','"
				+ ca.getLimit() + "','" + ca.getClientId() + "','" + ca.getDebt() + "');";
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
	public void Update(Card ca) {
		String sql = "UPDATE cards SET debt=" + ca.getDebt() + ",limite=" + ca.getLimit() + " WHERE " + "number="
				+ ca.getNumber();
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
	public Card Read(int id) {
		String sql = "SELECT * FROM cards WHERE number=" + id;
		Connection c = null;
		Card card = new Card();
		try {
			c = DBManager.connect();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			if (rs.next()) {
				card.setNumber(rs.getString("number"));
				card.setLimit(rs.getInt("limite"));
				card.setDebt(rs.getInt("debt"));
				card.setClientId(rs.getInt("clientId"));
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

		return card;
	}

	public Card Read(String id) {
		String sql = "SELECT * FROM cards WHERE number=" + id;
		Connection c = null;
		Card card = new Card();
		try {
			c = DBManager.connect();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			if (rs.next()) {
				card.setNumber(rs.getString("number"));
				card.setLimit(rs.getInt("limite"));
				card.setDebt(rs.getInt("debt"));
				card.setClientId(rs.getInt("clientId"));
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

		return card;
	}

	@Override
	public List<Card> ReadPool() {
		List<Card> list = new ArrayList<Card>();
		String sql = "SELECT * FROM cards";
		Connection c = null;
		try {
			c = DBManager.connect();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			while (rs.next()) {
				Card card = new Card();
				card.setNumber(rs.getString("number"));
				card.setLimit(rs.getInt("limite"));
				card.setDebt(rs.getInt("debt"));
				card.setClientId(rs.getInt("clientId"));
				list.add(card);
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

	public List<Card> ReadPoolbyCl(Integer id) {
		List<Card> list = new ArrayList<Card>();
		String sql = "SELECT * FROM cards where clientId = " + id;
		Connection c = null;
		try {
			c = DBManager.connect();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			while (rs.next()) {
				Card card = new Card();
				card.setNumber(rs.getString("number"));
				card.setLimit(rs.getInt("limite"));
				card.setDebt(rs.getInt("debt"));
				card.setClientId(rs.getInt("clientId"));
				list.add(card);
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
	}

	public void Delete(String number) {
		String sql = "DELETE FROM cards WHERE number=" + number;
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
