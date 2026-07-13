package form;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import app.FormLabel;
import app.ManagerPanel;
import exception.ServiceException;
import model.Account;
import model.Client;
import service.AccountService;
import service.ClientService;

public class AccountFormPanel extends JPanel implements ActionListener {

	private ManagerPanel manager;
	private FormLabel balance;
	private FormLabel alias;
	private FormLabel id;
	private JButton addBtn;
	private JButton clearBtn;
	private JButton backBtn;
	private FormLabel list;
	private Client client;
	private String[] t = { "Cuenta Corriente", "Caja Ahorro", "Cuenta Dolares", "Cuenta Sueldo" };
	private AccountService accSer = new AccountService();
	private ClientService clSer = new ClientService();

	public AccountFormPanel(ManagerPanel manager) {
		this.manager = manager;
	}

	public void makePanel() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		list = new FormLabel("Tipo de cuenta:", t);
		id = new FormLabel("ID:");
		balance = new FormLabel("Saldo:");
		alias = new FormLabel("Alias:");
		addBtn = new JButton("Agregar");
		addBtn.addActionListener(this);
		clearBtn = new JButton("Limpiar");
		clearBtn.addActionListener(this);
		backBtn = new JButton("Volver");
		backBtn.addActionListener(this);
		if (!manager.getClient().getAdmin()) {
			id.getLbl().setText("ID: " + manager.getClient().getId().toString());
			id.getTxt().setText(manager.getClient().getId().toString());
			this.add(id.getLbl());
		} else {
			this.add(id.getLbl());
			this.add(id.getTxt());
		}
		this.add(list.getLbl());
		this.add(list.getList());
		this.add(balance.getLbl());
		this.add(balance.getTxt());
		this.add(alias.getLbl());
		this.add(alias.getTxt());
		this.add(addBtn);
		this.add(clearBtn);
		this.add(backBtn);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object btn = e.getSource();
		if (btn == addBtn) {
			addAccount();
		}
		if (btn == clearBtn) {
			clearInputs();
		}
		if (btn == backBtn) {
			manager.makeAccountPanel();
		}
	}

	private void addAccount() {
		String CBU = Long.toString(new Date().getTime());
		try {
			accSer.checkInputs(alias, id, balance, list);
			client = clSer.getClient(Integer.parseInt(id.getTxt().getText()));
			Integer blc = Integer.parseInt(balance.getTxt().getText());
			Account acc = new Account(blc, CBU, alias.getTxt().getText(), list.getList().getSelectedValue(),
					client.getId());
			accSer.createAccount(acc);
			JOptionPane.showMessageDialog(manager.getFrame(), "Cuenta creada con exito!!!", "Exito",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(manager.getFrame(), "Saldo invalido", "Error de numero",
					JOptionPane.ERROR_MESSAGE);
		} catch (ServiceException e) {
			JOptionPane.showMessageDialog(manager.getFrame(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void clearInputs() {
		alias.getTxt().setText("");
		id.getTxt().setText("");
		list.getList().clearSelection();
		balance.getTxt().setText("");
	}
}
