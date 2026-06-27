package form;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import app.ManagerPanel;
import model.Account;
import model.Client;

public class AccountFormPanel extends JPanel implements ActionListener {

	private ManagerPanel manager;
	private FormLabel balance;
	private FormLabel alias;
	private FormLabel email;
	private JButton addBtn;
	private JButton clearBtn;
	private JButton backBtn;
	private FormLabel list;
	private Client client;
	private String[] t = { "Cuenta Corriente", "Caja Ahorro", "Cuenta Dolares", "Cuenta Sueldo" };
	private ServiceForm  sf = new ServiceForm();  

	public AccountFormPanel(ManagerPanel manager) {
		this.manager = manager;
	}

	public void makePanel() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		list = new FormLabel("Tipo de cuenta:", t);
		email = new FormLabel("Email:");
		balance = new FormLabel("Saldo:");
		alias = new FormLabel("Alias:");
		addBtn = new JButton("Agregar");
		addBtn.addActionListener(this);
		clearBtn = new JButton("Limpiar");
		clearBtn.addActionListener(this);
		backBtn = new JButton("Volver");
		backBtn.addActionListener(this);
		this.add(email.getLbl());
		this.add(email.getTxt());
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
		Boolean ok = true;
		String CBU =Long.toString(new Date().getTime());
		if (checkInputs()) {
			try {
				Integer blc = Integer.parseInt(balance.getTxt().getText());
				Account acc = new Account(blc, CBU, alias.getTxt().getText(), list.getList().getSelectedValue(),client.getId());
				sf.createAccount(acc);
			} catch (NumberFormatException e) {
				manager.makeDialogPanel("Saldo invalido", "Error de numero", JOptionPane.ERROR_MESSAGE);
				ok=false;
			} catch (Exception e) {
				manager.makeDialogPanel(e.getMessage(), "Error Guardado", JOptionPane.ERROR_MESSAGE);
				ok=false;
			}
			if(ok) {
				manager.makeDialogPanel("Cuenta creada con exito!!!", "Exito", JOptionPane.INFORMATION_MESSAGE);				
			}
		}
	}

	private Boolean checkInputs() {
		Boolean flag = false;
		if (alias.getTxt().getText().length() == 0 || alias.getTxt().getText().length() > 30) {
			alias.getTxt().setBackground(Color.RED);
			flag = true;
		} else {
			alias.getTxt().setBackground(Color.WHITE);
		}
		if (email.getTxt().getText().length() == 0 || email.getTxt().getText().length() > 30) {
			email.getTxt().setBackground(Color.RED);
			flag = true;
		} else {
			email.getTxt().setBackground(Color.WHITE);
			getClient();
		}

		if (list.getList().getSelectedValue() == null) {
			list.getList().setBackground(Color.RED);
			flag = true;
		} else {
			list.getList().setBackground(Color.WHITE);
		}

		try {
			Integer.parseInt(balance.getTxt().getText());
			balance.getTxt().setBackground(Color.WHITE);
			if (balance.getTxt().getText().length() > 10) {
				balance.getTxt().setBackground(Color.RED);
				flag = true;
			}
		} catch (NumberFormatException e) {
			balance.getTxt().setBackground(Color.RED);
			flag = true;
		}
		if (flag) {
			manager.makeDialogPanel("Recordá: campos no mayor a 30 caracteres, balance deben ser n°", "Campo invalido",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;

	}

	private void getClient() {
		try {
			client = sf.getClient(email.getTxt().getText());
		} catch (Exception e) {
			manager.makeDialogPanel(e.getMessage(), "Error Extrayendo datos", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void clearInputs() {
		alias.getTxt().setText("");
		email.getTxt().setText("");
		list.getList().clearSelection();
		balance.getTxt().setText("");
	}
}
