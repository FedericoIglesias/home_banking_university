package form;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import app.FormLabel;
import app.ManagerPanel;
import exception.ServiceException;
import model.Account;
import model.Transfer;
import service.AccountService;
import service.ClientService;
import service.TransferService;

public class TransfersFormPanel extends JPanel implements ActionListener, ListSelectionListener {
	private ManagerPanel manager;
	private JButton addBtn;
	private JButton cleanBtn;
	private JButton backBtn;
	private FormLabel emails;
	private FormLabel dstId;
	private FormLabel originId;
	private FormLabel balance;
	private FormLabel cbu;
	private FormLabel alias;
	private FormLabel id;
	private TransferService trSer = new TransferService();
	private ClientService clSer = new ClientService();
	private AccountService accSer = new AccountService();

	private DefaultListModel<String> list = new DefaultListModel<>();

	public TransfersFormPanel(ManagerPanel manager) {
		this.manager = manager;
	}

	public void makePanel() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		dstId = new FormLabel("Cuenta destino, ingrese CBU, Id o alias de cuenta:");
		emails = new FormLabel("Cliente:");
		originId = new FormLabel("Seleccione una cuenta de origen:", list);
		balance = new FormLabel("Ingrese el valor a transferir: ");
		cbu = new FormLabel("CBU");
		alias = new FormLabel("Alias");
		id = new FormLabel("Id");
		addBtn = new JButton("Agregar");
		addBtn.addActionListener(this);
		cleanBtn = new JButton("Limpiar");
		cleanBtn.addActionListener(this);
		backBtn = new JButton("Volver");
		backBtn.addActionListener(this);
		this.add(emails.getLbl());
		this.add(new JLabel(manager.getClient().getEmail()));
		this.getAccounts(manager.getClient().getId());
		originId.getList().setSelectedIndex(0);
		this.add(originId.getLbl());
		this.add(originId.getList());
		this.add(dstId.getLbl());
		this.add(dstId.getTxt());
		this.add(cbu.getLbl());
		this.add(cbu.getBox());
		this.add(id.getLbl());
		this.add(id.getBox());
		this.add(alias.getLbl());
		this.add(alias.getBox());
		this.add(balance.getLbl());
		this.add(balance.getTxt());
		this.add(addBtn);
		this.add(cleanBtn);
		this.add(backBtn);
	}

	private void getAccounts(int id) {
		List<Account> lsAcc = null;
		list.removeAllElements();
		try {
			lsAcc = accSer.getAccount(id);
		} catch (ServiceException e) {
			JOptionPane.showMessageDialog(manager.getFrame(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		int i;
		for (i = 0; i < lsAcc.size(); i++) {
			String name = lsAcc.get(i).getType() + " -- " + lsAcc.get(i).getBalance() + " -- " + lsAcc.get(i).getId();
			list.add(i, name);
		}
	}

	public void addTransfers() {
		String[] parts = originId.getList().getSelectedValue().split(" -- ");
		Transfer trans = new Transfer();
		Integer accID = null;
		try {
			trSer.checkInputTrs(balance, originId, alias, id, cbu, dstId);
			accID = trSer.getAccountDst(cbu, id, alias, dstId);
			trSer.checkBalance(originId, balance);
			trans.setBalance(Integer.parseInt(balance.getTxt().getText()));
			trans.setDate(new Date().getTime());
			trans.setDstId(accID);
			trans.setOriginId(Integer.parseInt(parts[2]));
			trSer.createTransfers(trans);
			updateAcc(Integer.parseInt(parts[2]), accID);
		} catch (ServiceException e) {
			JOptionPane.showMessageDialog(manager.getFrame(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		JOptionPane.showMessageDialog(manager.getFrame(), "Transferencia realizada con exito", "Hecho",
				JOptionPane.INFORMATION_MESSAGE);

	}

	private void clearInputs() {
		this.dstId.clearInput();
		this.originId.clearInput();
		this.balance.clearInput();
		this.cbu.clearCheck();
		this.alias.clearCheck();
		this.id.clearCheck();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object btn = e.getSource();
		if (btn == addBtn) {
			addTransfers();
		}
		if (btn == cleanBtn) {
			clearInputs();
		}
		if (btn == backBtn) {
			manager.makeTransferPanel();
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		Object list = e.getSource();
		if (!e.getValueIsAdjusting() && list == emails.getList()) {
			String selected = emails.getList().getSelectedValue();
			int id = 0;
			try {
				id = clSer.getClient(selected).getId();

			} catch (ServiceException e1) {
				JOptionPane.showMessageDialog(manager.getFrame(), e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
			getAccounts(id);
		}
	}

	public void updateAcc(Integer oriId, Integer dstId) {
		Integer blc = Integer.parseInt(balance.getTxt().getText());
		try {
			accSer.updateAllAcc(oriId, dstId, blc);
		} catch (ServiceException e) {
			JOptionPane.showMessageDialog(manager.getFrame(), e.getMessage(), "Problema de archivos",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
