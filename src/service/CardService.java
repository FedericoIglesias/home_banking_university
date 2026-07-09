package service;

import java.util.Date;
import java.util.List;

import app.ManagerPanel;
import dao.CardDAO;
import exception.DAOException;
import exception.ServiceException;
import model.Card;

public class CardService {
  private CardDAO cdDAO = new CardDAO();
  private ManagerPanel manager = new ManagerPanel();

  public CardService(ManagerPanel manager) {
    this.manager = manager;
  }

  public List<Card> getPoolCd() throws Exception {
    List<Card> listCards = null;
    try {
      listCards = cdDAO.ReadPool();
    } catch (DAOException e) {
      throw new ServiceException(e.getMessage(), e);
    }
    return listCards;
  }

  public List<Card> getClCd(Integer id) throws Exception {
    List<Card> listCards = null;
    try {
      listCards = cdDAO.ReadPoolbyCl(id);
    } catch (DAOException e) {
      throw new ServiceException(e.getMessage(), e);
    }
    return listCards;
  }

  public void deleteCard(String Number) throws Exception {
    try {
      cdDAO.Delete(Number);
    } catch (DAOException e) {
      throw new ServiceException(e.getMessage(), e);
    }
  }

  public void createCard(Integer limit) throws Exception {
    Card card = new Card();
    card.setClientId(manager.getClient().getId());
    card.setDebt(0);
    card.setLimit(limit);
    card.setNumber(Long.toString(new Date().getTime()));
    try {
      cdDAO.Insert(card);
    } catch (Exception e) {
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
}
