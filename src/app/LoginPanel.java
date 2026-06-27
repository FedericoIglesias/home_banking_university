package app;

import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import form.FormLabel;
import model.Client;

public class LoginPanel extends JPanel implements ActionListener {

	private JButton logBtn;
	private ManagerPanel manager;
	private FormLabel dni;
	private FormLabel pass;
	// private JOptionPane disclaimer = new JOptionPane();
	private ServiceApp sa = new ServiceApp();

	public LoginPanel(ManagerPanel manager) {
		super();
		this.manager = manager;
	}

	public void makePanel() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		dni = new FormLabel("DNI");
		pass = new FormLabel("Contraseña");
		logBtn = new JButton("Login");
		logBtn.setSize(100, 20);
		logBtn.addActionListener(this);
		this.add(dni.getLbl());
		this.add(dni.getTxt());
		this.add(pass.getLbl());
		this.add(pass.getTxt());
		this.add(logBtn);
	}

	private void logIn() {
		try {
			int id = Integer.parseInt(dni.getTxt().getText());
			Client cl = sa.loginUser(id);
			if (pass.getTxt().getText().equals(cl.getPass())) {
				manager.setAdmin(cl.getAdmin());
				if (manager.getAdmin()) {
					manager.makeClientPanel();
				} else {
					manager.makeProfilePanel(new Client(cl.getName(), cl.getEmail(), cl.getDni(), cl.getAdmin()));
				}
			} else {
				manager.makeDialogPanel("DNI o contraseña invalidas", "Login incorrecto", JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (NumberFormatException e) {
			manager.makeDialogPanel("Ingrese los n° del DNI", "DNI incorrecto", JOptionPane.ERROR_MESSAGE);
		}catch(Exception e) {
			manager.makeDialogPanel(e.getMessage(), "Error Ingresando", JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object btn = e.getSource();
		if (btn == logBtn) {
				logIn();
		}

	}

}
