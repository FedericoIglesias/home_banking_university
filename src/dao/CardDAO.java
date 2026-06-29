package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DBManager;
import model.Card;

public class CardDAO implements CRUD<Card> {

	@Override
	public void Insert(Card c) throws Exception {
		String sql = "INSERT INTO cards (number,limite,clientId,debt) VALUES('" + c.getNumber() + "','"
				+ c.getLimit() + "','" + c.getClientId() + "','" + c.getDebt() + "');";
		Connection co = DBManager.connect();
		try {
			Statement s = co.createStatement();
			s.execute(sql);
		} catch (SQLException e) {
			try {
				co.rollback();
				throw new Exception(e);
			} catch (SQLException er) {
				throw new Exception(er);
			}
		} finally {
			try {
				co.close();
			} catch (SQLException e) {
				throw new Exception(e);
			}
		}

	}

	@Override
	public void Update(Card ca) throws Exception {
		String sql = "UPDATE cards SET debt=" + ca.getDebt() + ",limite=" + ca.getLimit() + " WHERE " + "number="
				+ ca.getNumber();
		Connection c = DBManager.connect();
		try {
			Statement s = c.createStatement();
			s.execute(sql);
		} catch (SQLException e) {
			try {
				c.rollback();
				throw new Exception(e);
			} catch (SQLException er) {
				throw new Exception(e);
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
	public Card Read(int id) throws Exception {
		String sql = "SELECT * FROM cards WHERE number=" + id;
		Connection c = DBManager.connect();
		Card card = new Card();
		try {
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

		return card;
	}

	@Override
	public List<Card> ReadPool() throws Exception {
		List<Card> list = new ArrayList<Card>();
		String sql = "SELECT * FROM cards";
		Connection c = DBManager.connect();
		try {
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
	public void Delete(int id){
	}
	
	public void Delete(String number) throws Exception {
		String sql = "DELETE FROM cards WHERE number=" + number;
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
