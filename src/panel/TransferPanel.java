package panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import app.Buttons;
import app.ManagerPanel;
import exception.ServiceException;
import model.Transfer;
import service.TransferService;
import table.TransfersTable;

public class TransferPanel extends JPanel implements ActionListener {

	private JTable table;
	private TransfersTable model;
	private JScrollPane scrollPane;
	private JButton addBtn;
	private JButton resumeBtn;
	private ManagerPanel manager;
	private List<Transfer> list;
	private Buttons btns;
	private TransferService trSer = new TransferService();

	public TransferPanel(ManagerPanel m) {
		super();
		this.manager = m;
	}

	public void makePanel() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		btns = new Buttons(manager);
		btns.makePanel();
		model = new TransfersTable();
		table = new JTable(model);
		scrollPane = new JScrollPane(table);
		addBtn = new JButton("Agregar");
		resumeBtn = new JButton("Crear Resumen");
		resumeBtn.addActionListener(this);
		addBtn.addActionListener(this);
		this.updateList();
		this.add(scrollPane);
		if (!manager.getClient().getAdmin()) {
			this.add(addBtn);
		}
		this.add(resumeBtn);
		this.add(btns);
	}

	private void updateList() {
		try {
			if (manager.getClient().getAdmin()) {
				list = trSer.getPoolTr();
			} else {
				list = trSer.getTrCl(manager.getClient().getId());
			}
			model.setTransferList(list);
			model.fireTableDataChanged();
		} catch (ServiceException e) {
			JOptionPane.showMessageDialog(manager.getFrame(), e.getMessage(), "Error Lectura", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void deleteRow(int id) {
		try {
			trSer.deleteTr(list.get(id).getId());
			list.remove(id);
			model.setTransferList(list);
			model.fireTableDataChanged();
		} catch (ServiceException e) {
			JOptionPane.showMessageDialog(manager.getFrame(), e.getMessage(), "Error Borrar", JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object btn = e.getSource();
		if (btn == addBtn) {
			manager.makeTransfersFormPanel();
		}
		if (btn == resumeBtn) {
			this.generateResume();
		}
	}

	public void generateResume() {
		try {
			trSer.generateResume(list);
		} catch (ServiceException e) {
			JOptionPane.showMessageDialog(manager.getFrame(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		JOptionPane.showMessageDialog(manager.getFrame(), "Archivo creado", "Exito!!", JOptionPane.INFORMATION_MESSAGE);

	}

}
