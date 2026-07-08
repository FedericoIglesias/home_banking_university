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
import exception.ServiceException;
import model.Transfer;
import table.TransfersTable;

public class TransferPanel extends JPanel implements ActionListener {

	private JTable table;
	private TransfersTable model;
	private JScrollPane scrollPane;
	private JButton addBtn;
	private JButton deleteBtn;
	private JButton resumeBtn;
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
		btns = new Buttons(manager);
		btns.makePanel();
		model = new TransfersTable();
		table = new JTable(model);
		scrollPane = new JScrollPane(table);
		deleteBtn = new JButton("Borrar");
		addBtn = new JButton("Agregar");
		resumeBtn = new JButton("Crear Resumen");
		resumeBtn.addActionListener(this);
		deleteBtn.addActionListener(this);
		addBtn.addActionListener(this);
		this.updateList();
		this.add(scrollPane);
		if (!manager.getClient().getAdmin()) {
			this.add(deleteBtn);
			this.add(addBtn);
		}
		this.add(resumeBtn);
		this.add(btns);
	}

	private void updateList() {
		try {
			if (manager.getClient().getAdmin()) {
				list = sa.getPoolTr();
			} else {
				list = sa.getTrCl(manager.getClient().getId());
			}
			model.setTransferList(list);
			model.fireTableDataChanged();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(manager.getFrame(), e.getMessage(), "Error Lectura", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void deleteRow(int id) {
		try {
			sa.deleteTr(list.get(id).getId());
			list.remove(id);
			model.setTransferList(list);
			model.fireTableDataChanged();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(manager.getFrame(), e.getMessage(), "Error Borrar", JOptionPane.ERROR_MESSAGE);
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
		if (btn == resumeBtn) {
			this.generateResume();
		}
	}

	public void generateResume() {
		try {
			sa.generateResume(list);
		} catch (ServiceException e) {
			JOptionPane.showMessageDialog(manager.getFrame(), e.getMessage(), "Exito!!", JOptionPane.ERROR_MESSAGE);
		}
		JOptionPane.showMessageDialog(manager.getFrame(), "Archivo creado", "Exito!!", JOptionPane.INFORMATION_MESSAGE);

	}

}
