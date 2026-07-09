package app;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class DebtForm extends JPanel implements ActionListener {

  private FormLabel deb;
  private JButton debtBtn;
  private JButton cleanBtn;

  public DebtForm() {

  }

  public void makePanel() {
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    deb = new FormLabel("Debito a agregar");
    debtBtn = new JButton("Agregar debito");
    debtBtn.addActionListener(this);
    cleanBtn = new JButton("Limpiar");
    cleanBtn.addActionListener(this);
    this.add(deb.getLbl());
    this.add(deb.getTxt());
    this.add(debtBtn);
    this.add(cleanBtn);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    Object btn = e.getSource();
    if (btn == cleanBtn) {
      clearInputs();
    }
  }

  public void clearInputs() {
    deb.clearInput();
  }

  public JButton getDebtBtn() {
    return debtBtn;
  }

  public void setDebtBtn(JButton debtBtn) {
    this.debtBtn = debtBtn;
  }

}
