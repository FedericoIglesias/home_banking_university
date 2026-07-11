package service;

import java.util.List;

import exception.ServiceException;
import model.Card;
import table.CardTable;

public class Service {

  public Service() {
  }

  public void deleteRow(int id, CardService cardSer, List<Card> list, CardTable model) {
    try {
      cardSer.deleteCard(list.get(id).getNumber());
      list.remove(id);
      model.setCardList(list);
      model.fireTableDataChanged();
    } catch (ServiceException e) {
      throw new ServiceException(e.getMessage(), e);
    }
  }

}
