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
import model.Client;
import service.ClientService;
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
	private ClientService clSer = new ClientService();

	public ClientPanel(ManagerPanel m) {
		super();
		this.manager = m;
	}

	public void makePanel() {
		this.setLayout(new FlowLayout());
		btns = new Buttons(manager);
		btns.makePanel();
		model = new ClientTable();
		table = new JTable(model);
		scrollPane = new JScrollPane(table);
		this.updateList();
		this.add(scrollPane);
		deleteBtn = new JButton("Borrar");
		deleteBtn.addActionListener(this);
		this.add(deleteBtn);
		addBtn = new JButton("Agregar");
		addBtn.addActionListener(this);
		this.add(addBtn);
		this.add(btns);
	}

	private void updateList() {
		try {
			list = clSer.getPoolCl();
			model.setClientList(list);
			model.fireTableDataChanged();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(manager.getFrame(), e.getMessage(), "Error Lectura", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void deleteRow(int id) {
		try {
			clSer.deleteClient(list.get(id).getDni());
			list.remove(id);
			model.setClientList(list);
			model.fireTableDataChanged();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(manager.getFrame(), e.getMessage(), "Error Borrar", JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object btn = e.getSource();
		if (btn == addBtn) {
			manager.makeClientFormPanel();
		}
		if (btn == deleteBtn && table.getSelectedRow() != -1) {
			deleteRow(table.getSelectedRow());
		}
	}
}
