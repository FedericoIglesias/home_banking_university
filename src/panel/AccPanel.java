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
import app.ManagerPanel;
import model.Account;
import service.AccountService;
import table.AccTable;

public class AccPanel extends JPanel implements ActionListener {

	private JTable table;
	private AccTable model;
	private JScrollPane scrollPane;
	private JButton addBtn;
	private JButton deleteBtn;
	private ManagerPanel manager;
	private List<Account> list;
	private AccountService accSer = new AccountService();
	private Buttons btns;

	public AccPanel(ManagerPanel m) {
		super();
		this.manager = m;
	}

	public void makePanel() {
		this.setLayout(new FlowLayout());
		btns = new Buttons(manager);
		btns.makePanel();
		model = new AccTable();
		table = new JTable(model);
		scrollPane = new JScrollPane(table);
		deleteBtn = new JButton("Borrar");
		deleteBtn.addActionListener(this);
		addBtn = new JButton("Agregar");
		addBtn.addActionListener(this);
		this.updateList();
		this.add(scrollPane);
		this.add(deleteBtn);
		this.add(addBtn);
		this.add(btns);
	}

	public void updateList() {
		try {
			if (manager.getClient().getAdmin()) {
				list = accSer.getPoolAcc();
			} else {
				list = accSer.getAccCl(manager.getClient().getId());
			}
			model.setAccountList(list);
			model.fireTableDataChanged();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(manager.getFrame(), e.getMessage(), "Error Lectura", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void deleteRow(int id) {
		try {
			accSer.deleteAcc(list.get(id).getId());
			list.remove(id);
			model.setAccountList(list);
			model.fireTableDataChanged();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(manager.getFrame(), e.getMessage(), "Error Borrar", JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object btn = e.getSource();
		if (btn == addBtn) {
			manager.makeAccountFormPanel();
		}
		if (btn == deleteBtn && table.getSelectedRow() != -1) {
			deleteRow(table.getSelectedRow());
		}
	}

}
