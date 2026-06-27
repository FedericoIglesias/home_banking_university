package app;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;

import model.Client;
import table.ClientTable;

public class Buttons extends JPanel implements ActionListener {
	private JButton accsBtn;
	private JButton cardsBtn;
	private JButton transfersBtn;
	private JButton clientsBtn;
	private ManagerPanel manager;
	private String panel;

	public Buttons(ManagerPanel manager, String panel) {
		this.manager = manager;
		this.panel = panel;
	}

	public Buttons(ManagerPanel manager, JTable table, ClientTable model, List<Client> list) {
		this.manager = manager;
	}

	public void makePanel() {
		this.setLayout(new FlowLayout());
		accsBtn = new JButton("Cuentas");
		cardsBtn= new JButton("Tarjetas");;
		transfersBtn = new JButton("Transferencias");;
		clientsBtn = new JButton("Clientes");
		accsBtn.addActionListener(this);
		cardsBtn.addActionListener(this);
		transfersBtn.addActionListener(this);
		clientsBtn .addActionListener(this);
		this.add(cardsBtn);
		this.add(accsBtn);
		this.add(transfersBtn);
		this.add(clientsBtn);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object btn = e.getSource();
		if (btn == accsBtn) {
			manager.makeAccountPanel();
		}
		if (btn == cardsBtn) {
			System.out.println("cards");
		}
		if (btn == transfersBtn) {
			manager.makeTransferPanel();
		}
		if (btn == clientsBtn) {
			manager.makeClientPanel();
		}
	}

	public void whatPanel() {
		switch (this.panel) {
		case "":
			manager.makeClientPanel();
			break;
		case "ACCOUNTS":
			manager.makeAccountPanel();
			;
			break;
		case "CARDS":
			System.out.println("cards");
			break;
		case "TRANSFERS":
			System.out.println("transfers");
			break;
		}
	}
}
