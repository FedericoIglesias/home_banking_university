package panel;


import javax.swing.JLabel;
import javax.swing.JPanel;

import app.Buttons;
import app.ManagerPanel;
import model.Client;

public class ProfilePanel extends JPanel{
	private JLabel name;
	private JLabel email;
	private JLabel dni;
	private Buttons btns;
	private ManagerPanel manager;
	private Client client;
	
	public ProfilePanel(ManagerPanel manager,Client client) {
		super();
		this.manager = manager;
		this.client = client;
	}
	
	public void makePanel() {
		name = new JLabel("Nombre Completo: " + client.getName());
		email = new JLabel("Email: " + client.getEmail());
		dni = new JLabel("DNI: " + client.getDni().toString());
		btns = new Buttons(manager);
		btns.makePanel();
		this.add(name);
		this.add(email);
		this.add(dni);
		this.add(btns);
	}
	
	
	
}
