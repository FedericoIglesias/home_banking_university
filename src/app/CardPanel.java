package app;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import model.Card;

import table.CardTable;

public class CardPanel extends JPanel implements ActionListener {
  private JTable table;
  private CardTable model;
  private JScrollPane scrollPane;
  private JButton addBtn;
  private JButton deleteBtn;
  private ManagerPanel manager;
  private List<Card> list;
  private Buttons btns;
  private ServiceApp sa = new ServiceApp();

  public CardPanel(ManagerPanel manager) {
    super();
    this.manager = manager;
  }

  public void makePanel() {
    this.setLayout(new FlowLayout());
    btns = new Buttons(manager, "ACCOUNTS");
    btns.makePanel();
    model = new CardTable();
    table = new JTable(model);
    scrollPane = new JScrollPane(table);
    this.add(scrollPane);
    deleteBtn = new JButton("Borrar");
    deleteBtn.addActionListener(this);
    addBtn = new JButton("Agregar");
    addBtn.addActionListener(this);
    this.add(btns);
  }

  public void updateList() {
    try {
      list = sa.getPoolCd();
      model.setCardList(list);
      model.fireTableDataChanged();
    } catch (Exception e) {
      manager.makeDialogPanel(e.getMessage(), "Error Lectura", JOptionPane.ERROR_MESSAGE);
    }
  }

  public void deleteRow(int id) {
    try {
      sa.deleteCard(list.get(id).getNumber());
      list.remove(id);
      model.setCardList(list);
      model.fireTableDataChanged();
    } catch (Exception e) {
      manager.makeDialogPanel(e.getMessage(), "Error Borrar", JOptionPane.ERROR_MESSAGE);
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
  }

  public void isAdmin() {
    if (!manager.getClient().getAdmin()) {
      this.add(deleteBtn);
      this.add(addBtn);
    }
  }

}
