package form;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import app.ManagerPanel;
import model.Account;
import model.Client;
import model.Transfer;

//  TODO - hacer transferencias por CBU y por alias


public class TransfersFormPanel extends JPanel implements ActionListener, ListSelectionListener {
	private ManagerPanel manager;
	private JButton addBtn;
	private JButton cleanBtn;
	private JButton backBtn;
	private FormLabel emails;
	private FormLabel dstId;
	private FormLabel originId;
	private FormLabel balance;
	private JCheckBox cbu;
	private JCheckBox alias;
	private JCheckBox id;

	private ServiceForm sf = new ServiceForm();

	private DefaultListModel<String> list = new DefaultListModel<>();
	private DefaultListModel<String> listEmails = new DefaultListModel<>();

	public TransfersFormPanel(ManagerPanel manager) {
		this.manager = manager;
	}

	public void makePanel() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		dstId = new FormLabel("Cuenta destino, ingrese CBU, Id o alias de cuenta:");
		getEmail();
		emails = new FormLabel("Cliente:", listEmails);
		emails.getList().addListSelectionListener(this);
		originId = new FormLabel("Seleccione una cuenta de origen:", list);
		balance = new FormLabel("Ingrese el valor a transferir: ");
		cbu = new JCheckBox("CBU");
		alias = new JCheckBox("Alias");
		id = new JCheckBox("Id");
		addBtn = new JButton("Agregar");
		addBtn.addActionListener(this);
		cleanBtn = new JButton("Limpiar");
		cleanBtn.addActionListener(this);
		backBtn = new JButton("Volver");
		backBtn.addActionListener(this);
		this.add(emails.getLbl());
		if (manager.getClient().getAdmin()) {
			this.add(emails.getList());
		} else {
			this.add(new JLabel(manager.getClient().getEmail()));
			this.getAccounts(manager.getClient().getId());
			originId.getList().setSelectedIndex(0);
		}
		this.add(originId.getLbl());
		this.add(originId.getList());
		this.add(dstId.getLbl());
		this.add(dstId.getTxt());
		this.add(cbu);
		this.add(id);
		this.add(alias);
		this.add(balance.getLbl());
		this.add(balance.getTxt());
		this.add(addBtn);
		this.add(cleanBtn);
		this.add(backBtn);
	}

	private void getEmail() {
		List<Client> lsCl = null;
		try {
			lsCl = sf.getClientPool();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(manager.getFrame(),e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		int i;
		for (i = 0; i < lsCl.size(); i++) {
			listEmails.add(i, lsCl.get(i).getEmail());
		}
	}

	private void getAccounts(int id) {
		List<Account> lsAcc = null;
		list.removeAllElements();
		try {
			lsAcc = sf.getAccount(id);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(manager.getFrame(),e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		int i;
		for (i = 0; i < lsAcc.size(); i++) {
			String name = lsAcc.get(i).getType() + " -- " + lsAcc.get(i).getBalance() + " -- " + lsAcc.get(i).getId();
			list.add(i, name);
		}
		
	}

	public void addTransfers() {
		Boolean ok = true;
		String[] parts = originId.getList().getSelectedValue().split(" -- ");
		Transfer trans = new Transfer();
		if (checkInputs() && getAccountDst() != -1 && checkBalance()) {
			try {
				trans.setBalance(Integer.parseInt(balance.getTxt().getText()));
				trans.setDate(new Date().getTime());
				trans.setDstId(getAccountDst());
				trans.setOriginId(Integer.parseInt(parts[2]));
				sf.createTransfers(trans);
				updateAcc(Integer.parseInt(parts[2]), getAccountDst());
			} catch (Exception e) {
				JOptionPane.showMessageDialog(manager.getFrame(),e.getMessage(), "Error Archivos", JOptionPane.ERROR_MESSAGE);
				ok = false;
			}
			if (ok) {
				JOptionPane.showMessageDialog(manager.getFrame(),"Transferencia realizada con exito", "Hecho", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

	public Boolean checkInputs() {
		Boolean flag = false;
		if (dstId.getTxt().getText().length() > 30 || dstId.getTxt().getText().length() == 0) {
			dstId.getTxt().setBackground(Color.RED);
			flag = true;
		} else {
			dstId.getTxt().setBackground(Color.WHITE);
		}

		try {
			Integer.parseInt(balance.getTxt().getText());
			balance.getTxt().setBackground(Color.WHITE);
			if (balance.getTxt().getText().length() > 10) {
				balance.getTxt().setBackground(Color.RED);
			}
		} catch (NumberFormatException e) {
			balance.getTxt().setBackground(Color.RED);
			flag = true;
		}
		if (originId.getList().getSelectedValue() == null) {
			originId.getList().setBackground(Color.RED);
			flag = true;
		} else {
			originId.getList().setBackground(Color.WHITE);
		}

		if ((cbu.isSelected() && alias.isSelected()) || (id.isSelected() && alias.isSelected())
				|| (cbu.isSelected() && id.isSelected())
				|| (!id.isSelected() && !alias.isSelected() && !cbu.isSelected())) {
			cbu.setBackground(Color.RED);
			id.setBackground(Color.RED);
			alias.setBackground(Color.RED);
		} else {
			cbu.setBackground(Color.WHITE);
			id.setBackground(Color.WHITE);
			alias.setBackground(Color.WHITE);
		}

		if (flag) {
			JOptionPane.showMessageDialog(manager.getFrame(),"Recordá: campos no mayor a 30 caracteres y DNI no mayor de 10 caracteres",
					"Campo invalido", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	private void clearInputs() {
		dstId.getTxt().setText("");
		originId.getList().clearSelection();
		balance.getTxt().setText("");
		cbu.setSelected(false);
		alias.setSelected(false);
		id.setSelected(false);
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
				id = sf.getClient(selected).getId();

			} catch (Exception e1) {
				JOptionPane.showMessageDialog(manager.getFrame(),e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
			getAccounts(id);
		}
	}

	public int getAccountDst() {
		Object ids = null;
		try {

			if (cbu.isSelected()) {
				ids = sf.getByCBU(dstId.getTxt().getText()).getId();
			}
			if (id.isSelected()) {
				ids = Integer.parseInt(dstId.getTxt().getText());
			}
			if (alias.isSelected()) {
				ids = sf.getByAlias(dstId.getTxt().getText()).getId();
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(manager.getFrame(),e.getMessage(), "Error archivos", JOptionPane.ERROR_MESSAGE);
		}
		if (ids == null) {
			JOptionPane.showMessageDialog(manager.getFrame(),"Cuenta destino no encontrada", "No encontrado", JOptionPane.INFORMATION_MESSAGE);
			return -1;
		} else {
			return (int) ids;
		}
	}

	public Boolean checkBalance() {
		String[] parts = originId.getList().getSelectedValue().split(" -- ");
		if (Integer.parseInt(balance.getTxt().getText()) > Integer.parseInt(parts[1])) {
			JOptionPane.showMessageDialog(manager.getFrame(),"Insuficiente saldo", "Sin fondos", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	public void updateAcc(Integer oriId, Integer dstId) {
		Integer blc = Integer.parseInt(balance.getTxt().getText());
		try {
			sf.updateAllAcc(oriId, dstId, blc);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(manager.getFrame(),e.getMessage(), "Problema de archivos", JOptionPane.ERROR_MESSAGE);
		}
	}
}
