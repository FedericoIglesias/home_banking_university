package table;

import java.util.List;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import app.ManagerPanel;
import model.Card;
import service.ClientService;

public class CardTable extends AbstractTableModel {

  private static final int COLUMN_NUMBER = 0;
  private static final int COLUMN_LIMIT = 1;
  private static final int COLUMN_NAME = 2;
  private static final int COLUMN_DEBITO = 3;
  private ManagerPanel manager;
  private ClientService sv= new  ClientService();

  private String[] columnsName = { "Numero", "Limite", "Nombre", "Debito" };

  private Class[] columnsType = { String.class, Integer.class, String.class, Integer.class };

  private List<Card> cardsList = null;

  public CardTable() {
  }

  public CardTable(List<Card> cardsList,ManagerPanel manager) {
    this.cardsList = cardsList;
    this.manager = manager;
  }

  @Override
  public String getColumnName(int col) {
    return columnsName[col];
  }

  @Override
  public int getRowCount() {
    if (cardsList == null) {
      return 0;
    }
    return cardsList.size();
  }

  @Override
  public int getColumnCount() {
    return columnsName.length;
  }

  @Override
  public Object getValueAt(int rowIndex, int columnIndex) {
    Card c = cardsList.get(rowIndex);
    Object r = null;
    switch (columnIndex) {
      case COLUMN_NUMBER:
        r = c.getNumber();
        break;
      case COLUMN_NAME:
          r =  getName(c.getClientId());
        break;
      case COLUMN_LIMIT:
        r = c.getLimit();
        break;
      case COLUMN_DEBITO:
        r = c.getDebt();
        break;
      default:
        r = new String("");
    }
    return r;
  }

  public List<Card> getCardList() {
    return cardsList;
  }

  public void setCardList(List<Card> cardsList) {
    this.cardsList = cardsList;
  }

  public String[] getColumnsName() {
    return columnsName;
  }

  public void setColumnsName(String[] columnsName) {
    this.columnsName = columnsName;
  }

  public Class[] getColumnsType() {
    return columnsType;
  }

  public void setColumnsType(Class[] columnsType) {
    this.columnsType = columnsType;
  }

  private String getName(Integer id) {
    String name = "";
    try {
      name = sv.getClient(id).getName();
    } catch (Exception e) {
      JOptionPane.showMessageDialog(manager.getFrame(),e.getMessage(), "Error", JDialog.ERROR);
    }
      return name;
  }

}
