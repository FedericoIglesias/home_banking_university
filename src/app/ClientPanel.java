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

import model.Client;
import table.ClientTable;

public class ClientPanel extends JPanel implements ActionListener {

	private JTable table;
	private ClientTable model;
	private JScrollPane scrollPane;
	private JButton addBtn;
	private JButton deleteBtn;
	private ManagerPanel manager;
	private List<Client> list;
	private Buttons btns;
	private ServiceApp sa=new ServiceApp();

	public ClientPanel(ManagerPanel m) {
		super();
		this.manager = m;
	}

	public void makePanel() {
		this.setLayout(new FlowLayout());
		btns = new Buttons(manager, "ACCOUNTS");
		btns.makePanel();
		model = new ClientTable();
		table = new JTable(model);
		scrollPane = new JScrollPane(table);
		this.add(scrollPane);
		deleteBtn = new JButton("Borrar");
		deleteBtn.addActionListener(this);
		this.add(deleteBtn);
		addBtn = new JButton("Agregar");
		addBtn.addActionListener(this);
		this.add(addBtn);
		this.add(btns);
	}
	
	public void updateList() {
		try {
			list = sa.getPoolCl();
			model.setClientList(list);
			model.fireTableDataChanged();
		} catch (Exception e) {
			manager.makeDialogPanel(e.getMessage(), "Error Lectura", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void deleteRow(int id) {
		try {
			sa.deleteClient(list.get(id).getDni());
			list.remove(id);
			model.setClientList(list);
			model.fireTableDataChanged();
		} catch (Exception e) {
			manager.makeDialogPanel(e.getMessage(), "Error Borrar", JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object btn = e.getSource();
		if (btn == addBtn) {
			manager.makeFormPanel();
		}
		if (btn == deleteBtn && table.getSelectedRow() != -1) {
			deleteRow(table.getSelectedRow());
		}
	}
}
