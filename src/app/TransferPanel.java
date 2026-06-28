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

import model.Transfer;
import table.TransfersTable;

public class TransferPanel extends JPanel implements ActionListener{
	
	private JTable table;
	private TransfersTable model;
	private JScrollPane scrollPane;
	private JButton addBtn;
	private JButton deleteBtn;
	private ManagerPanel manager;
	private List<Transfer> list;
	private Buttons btns;
	private ServiceApp sa = new ServiceApp();

	public TransferPanel(ManagerPanel m) {
		super();
		this.manager = m;
	}

	public void makePanel() {
		this.setLayout(new FlowLayout());
		btns = new Buttons(manager, "TRANSFERS");
		btns.makePanel();
		model = new TransfersTable();
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
			list = sa.getPoolTr();
			model.setTransferList(list);
			model.fireTableDataChanged();
		} catch (Exception e) {
			manager.makeDialogPanel(e.getMessage(), "Error Lectura", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void deleteRow(int id) {
		try {
			sa.deleteTr(list.get(id).getId());
			list.remove(id);
			model.setTransferList(list);
			model.fireTableDataChanged();
		} catch (Exception e) {
			manager.makeDialogPanel(e.getMessage(), "Error Borrar", JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object btn = e.getSource();
		if (btn == addBtn) {
			manager.makeTransfersFormPanel();
		}
		if (btn == deleteBtn && table.getSelectedRow() != -1) {
			deleteRow(table.getSelectedRow());
		}
	}

	public void isAdmin(){
		if(!manager.getClient().getAdmin()){
			this.add(deleteBtn);
			this.add(addBtn);
		}
	}
}
