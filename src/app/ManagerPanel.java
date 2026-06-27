package app;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import db.TableManager;
import form.AccountFormPanel;
import form.CardFormPanel;
import form.ClientFormPanel;
import form.TransfersFormPanel;
import model.Client;

public class ManagerPanel {
	private CardFormPanel cardFormPanel;
	private CardPanel cardPanel;
	private ClientPanel clientPanel;
	private JFrame frame;
	private ClientFormPanel formPanel;
	private LoginPanel loginPanel;
	private ProfilePanel profilePanel;
	private JOptionPane dialog;
	private AccPanel accPanel;
	private AccountFormPanel accFormPanel;
	private TransfersFormPanel transfersFormPanel;
	private TransferPanel transferPanel;
	private TableManager tb = new TableManager();
	private Boolean admin = false;

	public ManagerPanel() {
		this.makeDB();
	}

	public void makeDB() {
		try {
			tb.createTableAccount();
			tb.createTableCard();
			tb.createTableClient();
			tb.createTableTransfer();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this.frame, e.toString(), "Error DB", JOptionPane.CLOSED_OPTION);
			System.exit(0);
		}
	}

	public Boolean getAdmin() {
		return this.admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

	public void makeManager() {
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 700);
		loginPanel = new LoginPanel(this);
		loginPanel.makePanel();
		formPanel = new ClientFormPanel(this);
		formPanel.makePanel();
		clientPanel = new ClientPanel(this);
		clientPanel.makePanel();
		accPanel = new AccPanel(this);
		accPanel.makePanel();
		accFormPanel = new AccountFormPanel(this);
		accFormPanel.makePanel();
		transfersFormPanel = new TransfersFormPanel(this);
		transfersFormPanel.makePanel();
		transferPanel = new TransferPanel(this);
		transferPanel.makePanel();
		cardFormPanel = new CardFormPanel(this);
		cardFormPanel.makePanel();
		cardPanel = new CardPanel(this);
		cardPanel.makePanel();
	}

	public void makeCardPanel() {
		cardPanel.isAdmin();
		frame.getContentPane().removeAll();
		frame.getContentPane().add(cardPanel);
		frame.getContentPane().validate();
		frame.getContentPane().repaint();
	}

	public void makeCardFormPanel() {
		frame.getContentPane().removeAll();
		frame.getContentPane().add(cardFormPanel);
		frame.getContentPane().validate();
		frame.getContentPane().repaint();
	}

	public void makeClientPanel() {
		clientPanel.updateList();
		frame.getContentPane().removeAll();
		frame.getContentPane().add(clientPanel);
		frame.getContentPane().validate();
		frame.getContentPane().repaint();
	}

	public void makeFormPanel() {
		frame.getContentPane().removeAll();
		frame.getContentPane().add(formPanel);
		frame.getContentPane().validate();
		frame.getContentPane().repaint();
	}

	public void makeLoginPanel() {
		frame.getContentPane().removeAll();
		frame.getContentPane().add(loginPanel);
		frame.getContentPane().validate();
		frame.getContentPane().repaint();
	}

	public void makeProfilePanel(Client client) {
		profilePanel = new ProfilePanel(this, client);
		profilePanel.makePanel();
		frame.getContentPane().removeAll();
		frame.getContentPane().add(profilePanel);
		frame.getContentPane().validate();
		frame.getContentPane().repaint();
	}

	public void makeDialogPanel(String msg, String subject, int type) {
		dialog = new JOptionPane();
		dialog.showMessageDialog(this.frame, msg, subject, type);
		frame.getContentPane().add(dialog);
		frame.getContentPane().validate();
		frame.getContentPane().repaint();
	}

	public void makeAccountPanel() {
		accPanel.updateList();
		frame.getContentPane().removeAll();
		frame.getContentPane().add(accPanel);
		frame.getContentPane().validate();
		frame.getContentPane().repaint();
	}

	public void makeAccountFormPanel() {
		frame.getContentPane().removeAll();
		frame.getContentPane().add(accFormPanel);
		frame.getContentPane().validate();
		frame.getContentPane().repaint();
	}

	public void makeTransfersFormPanel() {
		frame.getContentPane().removeAll();
		frame.getContentPane().add(transfersFormPanel);
		frame.getContentPane().validate();
		frame.getContentPane().repaint();
	}

	public void makeTransferPanel() {
		transferPanel.updateList();
		transferPanel.isAdmin();
		frame.getContentPane().removeAll();
		frame.getContentPane().add(transferPanel);
		frame.getContentPane().validate();
		frame.getContentPane().repaint();
	}

	public void showFrame() {
		frame.setVisible(true);
	}

	public void mostrarSalir() {
		int response = JOptionPane.showConfirmDialog(frame, "Esta seguro?");
		if (response == JOptionPane.OK_OPTION) {
			System.exit(0);
		}
	}
}
