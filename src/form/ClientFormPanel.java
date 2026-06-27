package form;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import javax.swing.JOptionPane;
import javax.swing.JPanel;


import app.ManagerPanel;

import model.Client;

public class ClientFormPanel extends JPanel {
	private JButton addBtn;
	private JButton cleanBtn;
	private JButton backBtn;
	private ManagerPanel manager;
	private JCheckBox admin;
	private FormLabel name;
	private FormLabel email;
	private FormLabel dni;
	private FormLabel pass;
	private ServiceForm  sf = new ServiceForm();  

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
		cleanBtn = new JButton("Limpiar");
		cleanBtn.setSize(100, 20);
		backBtn = new JButton("Volver");
		backBtn.setSize(100, 20);
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
		addBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (checkInputs()) {

					Integer ndi = Integer.parseInt(dni.getTxt().getText());
					Client c = new Client(name.getTxt().getText(), email.getTxt().getText(), ndi,
							pass.getTxt().getText(), admin.isSelected());
					try {
						sf.createClient(c);
					}catch(Exception er) {
						manager.makeDialogPanel(er.getMessage(),"Error Guardado", JOptionPane.ERROR_MESSAGE);
						return;
					}
					manager.makeDialogPanel("Exito al guardar usuario","Exito!!", JOptionPane.INFORMATION_MESSAGE);
				}
			}

		});
		cleanBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				name.getTxt().setText("");
				email.getTxt().setText("");
				dni.getTxt().setText("");
				pass.getTxt().setText("");
				admin.setSelected(false);
			}
		});
		backBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				manager.makeClientPanel();
			}
		});
	}

	public Boolean checkInputs() {
		Boolean flag = false;
		if (name.getTxt().getText().length() > 30 || name.getTxt().getText().length() == 0) {
			name.getTxt().setBackground(Color.RED);
			flag = true;
		} else {
			name.getTxt().setBackground(Color.WHITE);
		}
		if (email.getTxt().getText().length() > 30 || email.getTxt().getText().length() == 0) {
			email.getTxt().setBackground(Color.RED);
			flag = true;
		} else {
			email.getTxt().setBackground(Color.WHITE);
		}

		try {
			Integer.parseInt(dni.getTxt().getText());
			dni.getTxt().setBackground(Color.WHITE);
			if (dni.getTxt().getText().length() > 10) {
				dni.getTxt().setBackground(Color.RED);
			}
		} catch (NumberFormatException e) {
			dni.getTxt().setBackground(Color.RED);
			flag = true;
		}

		if (pass.getTxt().getText().length() > 30 || pass.getTxt().getText().length() == 0) {
			pass.getTxt().setBackground(Color.RED);
			flag = true;
		} else {
			pass.getTxt().setBackground(Color.WHITE);
		}

		if (flag) {
			manager.makeDialogPanel("Recordá: campos no mayor a 30 caracteres y DNI no mayor de 10 caracteres",
					"Campo invalido", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

}
