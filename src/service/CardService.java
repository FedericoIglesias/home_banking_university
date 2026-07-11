package service;

import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import app.DebtForm;
import app.ManagerPanel;
import dao.CardDAO;
import exception.DAOException;
import exception.ServiceException;
import model.Card;

public class CardService {
  private CardDAO cdDAO = new CardDAO();
  private ManagerPanel manager;

  public CardService(ManagerPanel manager) {
    this.manager = manager;
  }

  public List<Card> getPoolCd() {
    List<Card> listCards = null;
    try {
      listCards = cdDAO.ReadPool();
    } catch (DAOException e) {
      throw new ServiceException(e.getMessage(), e);
    }
    return listCards;
  }

  public List<Card> getClCd(Integer id) {
    List<Card> listCards = null;
    try {
      listCards = cdDAO.ReadPoolbyCl(id);
    } catch (DAOException e) {
      throw new ServiceException(e.getMessage(), e);
    }
    return listCards;
  }

  public void deleteCard(String Number) {
    try {
      cdDAO.Delete(Number);
    } catch (DAOException e) {
      throw new ServiceException(e.getMessage(), e);
    }
  }

  public void createCard(Integer limit) {
    Card card = new Card();
    card.setClientId(manager.getClient().getId());
    card.setDebt(0);
    card.setLimit(limit);
    card.setNumber(Long.toString(new Date().getTime()));
    try {
      cdDAO.Insert(card);
    } catch (DAOException e) {
      throw new ServiceException(e.getMessage(), e);
    }
  }

  public void generationCredit(String idCard, int debt) {
    Card cd = null;
    try {
      cd = cdDAO.Read(idCard);
    } catch (DAOException e) {
      throw new ServiceException(e.getMessage(), e);
    }
    Integer total = debt + cd.getDebt();
    if (cd.getLimit() < total) {
      throw new ServiceException("Nuevo debito supera el limite", null);
    }
    try {
      cd.setDebt(total);
      cdDAO.Update(cd);
    } catch (DAOException e) {
      throw new ServiceException(e.getMessage(), e);
    }

  }

  public void makeDeb(JTable table, List<Card> list,String deb) {
    try {
      if (table.getSelectedRow() == -1) {
        throw new ServiceException("Seleccione un usuario de la tabla", null);
      }
      
      String id = list.get(table.getSelectedRow()).getNumber();
      int debt = Integer.parseInt(deb);
      this.generationCredit(id, debt);
    } catch (DAOException e) {
      throw new ServiceException(e.getMessage(), e);
    }
  }
}
