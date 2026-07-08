package form;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FormLabel extends JPanel {
	private JLabel lbl;
	private JTextField txt = new JTextField("");
	private JList<String> list;
	private JCheckBox box = new JCheckBox();

	public FormLabel(String lbl) {
		this.lbl = new JLabel(lbl);
		this.lbl.setSize(100, 20);
		this.txt.setMaximumSize(new Dimension(400, 30));
	}

	public FormLabel(String lbl, String[] list) {
		this.lbl = new JLabel(lbl);
		this.lbl.setSize(100, 20);
		this.setList(new JList<String>(list));
	}

	public FormLabel(String lbl, DefaultListModel<String> list) {
		this.lbl = new JLabel(lbl);
		this.lbl.setSize(100, 20);
		this.setList(new JList<String>(list));
	}

	public JLabel getLbl() {
		return lbl;
	}

	public void setLbl(JLabel lbl) {
		this.lbl = lbl;
	}

	public JTextField getTxt() {
		return txt;
	}

	public void setTxt(JTextField txt) {
		this.txt = txt;
	}

	public JList<String> getList() {
		return list;
	}

	public void setList(JList<String> list) {
		this.list = list;
	}

	public JCheckBox getBox() {
		return box;
	}

	public void setBox(JCheckBox box) {
		this.box = box;
	}

	public Boolean checkText() {
		Boolean flag = true;
		if (txt.getText().length() > 30 || txt.getText().length() == 0) {
			txt.setBackground(Color.red);
			return false;
		} else {
			txt.setBackground(Color.white);
		}
		return flag;
	}

	public Boolean checkNumber() {
		Boolean flag = true;
		if (txt.getText().length() > 30 || txt.getText().length() == 0) {
			flag = false;
			txt.setBackground(Color.red);
		} else {
			txt.setBackground(Color.white);
		}
		try {
			Integer.parseInt(txt.getText());
		} catch (NumberFormatException e) {
			flag = false;
			txt.setBackground(Color.red);
		} finally {
			return flag;
		}
	}

	public void clearInput() {
		txt.setText("");
	}

	public void clearCheck() {
		box.setSelected(false);
	}

	public Boolean checkBox() {
		return box.isSelected();
	}

	public Boolean checkList() {
		if (list.getSelectedValue() == null) {
			list.setBackground(Color.red);
			return false;
		}
		list.setBackground(Color.white);
		return true;
	}
}
