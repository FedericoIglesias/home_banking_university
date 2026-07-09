package form;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import app.FormLabel;
import app.ManagerPanel;
import exception.ServiceException;
import service.CardService;

public class CardFormPanel extends JPanel implements ActionListener {

	private FormLabel limit;
	private ManagerPanel manager;
	private JButton addBtn;
	private JButton clearBtn;
	private JButton backBtn;
	private CardService cardSer;

	public CardFormPanel(ManagerPanel manager) {
		this.manager = manager;
	}

	public void makePanel() {
		cardSer = new CardService(manager);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		limit = new FormLabel("Limite diario:");
		addBtn = new JButton("Agregar");
		addBtn.addActionListener(this);
		clearBtn = new JButton("Limpiar");
		clearBtn.addActionListener(this);
		backBtn = new JButton("Volver");
		backBtn.addActionListener(this);
		this.add(limit.getLbl());
		this.add(limit.getTxt());
		this.add(addBtn);
		this.add(clearBtn);
		this.add(backBtn);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object btn = e.getSource();
		if (btn == addBtn) {
			addCard();
		}
		if (btn == clearBtn) {
			clearInputs();
		}
		if (btn == backBtn) {
			manager.makeCardPanel();
		}
	}

	public void addCard() {
		try {
			cardSer.createCard(Integer.parseInt(limit.getTxt().getText()));
			JOptionPane.showMessageDialog(manager.getFrame(), "Exito al crear tarjeta", "Exito",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (ServiceException e) {
			JOptionPane.showMessageDialog(manager.getFrame(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void clearInputs() {
		this.limit.clearInput();
	}

}
