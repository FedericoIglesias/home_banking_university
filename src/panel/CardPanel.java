package panel;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import app.Buttons;
import app.DebtForm;
import app.ManagerPanel;
import model.Card;
import service.CardService;
import table.CardTable;

public class CardPanel extends JPanel implements ActionListener {
  private JTable table;
  private CardTable model;
  private JScrollPane scrollPane;
  private JButton addBtn;
  private JButton deleteBtn;
  // private JButton debBtn;
  private ManagerPanel manager;
  private List<Card> list;
  private Buttons btns;
  private CardService cardSer = new CardService(manager);
  private DebtForm dbForm;

  public CardPanel(ManagerPanel manager) {
    super();
    this.manager = manager;
  }

  public void makePanel() {
    this.setLayout(new FlowLayout());
    btns = new Buttons(manager);
    btns.makePanel();
    dbForm = new DebtForm();
    dbForm.makePanel();
    dbForm.getDebtBtn().addActionListener(this);
    model = new CardTable();
    table = new JTable(model);
    scrollPane = new JScrollPane(table);
    deleteBtn = new JButton("Borrar");
    deleteBtn.addActionListener(this);
    addBtn = new JButton("Agregar");
    addBtn.addActionListener(this);
    this.updateList();
    this.add(scrollPane);
    if (!manager.getClient().getAdmin()) {
      this.add(deleteBtn);
      this.add(addBtn);
    } else {
      this.add(dbForm);
    }
    this.add(btns);
  }

  public void updateList() {
    try {
      if (manager.getClient().getAdmin()) {
        list = cardSer.getPoolCd();
      } else {
        list = cardSer.getClCd(manager.getClient().getId());
      }
      model.setCardList(list);
      model.fireTableDataChanged();
    } catch (Exception e) {
      JOptionPane.showMessageDialog(manager.getFrame(), e.getMessage(), "Error Lectura", JOptionPane.ERROR_MESSAGE);
    }
  }

  public void deleteRow(int id) {
    try {
      cardSer.deleteCard(list.get(id).getNumber());
      list.remove(id);
      model.setCardList(list);
      model.fireTableDataChanged();
    } catch (Exception e) {
      JOptionPane.showMessageDialog(manager.getFrame(), e.getMessage(), "Error Borrar", JOptionPane.ERROR_MESSAGE);
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    Object btn = e.getSource();
    if (btn == addBtn) {
      manager.makeCardFormPanel();
    }
    if (btn == deleteBtn && table.getSelectedRow() != -1) {
      deleteRow(table.getSelectedRow());
    }
    if (btn == dbForm.getDebtBtn() && table.getSelectedRow() != -1) {
      System.out.println(list.get(table.getSelectedRow()).getNumber());
    }
  }

}
