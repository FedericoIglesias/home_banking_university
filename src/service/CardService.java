package service;

import java.util.List;

import dao.CardDAO;
import exception.DAOException;
import exception.ServiceException;
import model.Card;

public class CardService {
  private CardDAO cdDAO = new CardDAO();

  public CardService() {
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
}
