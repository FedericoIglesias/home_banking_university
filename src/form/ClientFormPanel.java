package form;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import app.FormLabel;
import app.ManagerPanel;
import exception.ServiceException;
import model.Client;
import service.ClientService;

public class ClientFormPanel extends JPanel implements ActionListener {
	private JButton addBtn;
	private JButton cleanBtn;
	private JButton backBtn;
	private ManagerPanel manager;
	private JCheckBox admin;
	private FormLabel name;
	private FormLabel email;
	private FormLabel dni;
	private FormLabel pass;
	private ClientService clSer = new ClientService();

	public ClientFormPanel(ManagerPanel manager) {
		this.manager = manager;
	}

	public void makePanel() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		name = new FormLabel("Nombre y Apellido");
		email = new FormLabel("Email");
		dni = new FormLabel("DNI");
		pass = new FormLabel("Contraseña");
		admin = new JCheckBox("Es admin");
		addBtn = new JButton("Agregar");
		addBtn.setSize(100, 20);
		addBtn.addActionListener(this);
		cleanBtn = new JButton("Limpiar");
		cleanBtn.setSize(100, 20);
		cleanBtn.addActionListener(this);
		backBtn = new JButton("Volver");
		backBtn.addActionListener(this);
		this.add(name.getLbl());
		this.add(name.getTxt());
		this.add(email.getLbl());
		this.add(email.getTxt());
		this.add(dni.getLbl());
		this.add(dni.getTxt());
		this.add(pass.getLbl());
		this.add(pass.getTxt());
		this.add(admin);
		this.add(addBtn);
		this.add(cleanBtn);
		this.add(backBtn);
	}

	public Boolean checkInputs() {
		if (name.checkText() && email.checkText() && dni.checkNumber() && pass.checkText()) {
			return true;
		}
		JOptionPane.showMessageDialog(manager.getFrame(),
				"e.getMessage()", "Campo invalido",
				JOptionPane.ERROR_MESSAGE);
		return false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object btn = e.getSource();
		if (btn == addBtn) {
			addUser();
		}
		if (btn == cleanBtn) {
			clearInputs();
		}
		if (btn == backBtn) {
			manager.makeClientPanel();
		}
	}

	public void clearInputs() {
		name.clearInput();
		email.clearInput();
		dni.clearInput();
		pass.clearInput();
	}

	public void addUser() {
		if (checkInputs()) {
			Integer ndi = Integer.parseInt(dni.getTxt().getText());
			Client c = new Client(name.getTxt().getText(), email.getTxt().getText(), ndi,
					pass.getTxt().getText(), admin.isSelected());
			try {
				clSer.createClient(c);
			} catch (ServiceException e) {
				JOptionPane.showMessageDialog(manager.getFrame(), e.getMessage(), "Error Guardado",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			JOptionPane.showMessageDialog(manager.getFrame(), "Exito al guardar usuario", "Exito!!",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

}
