package table;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import app.ManagerPanel;
import model.Account;

public class AccTable extends AbstractTableModel {

	private static final int COLUMN_ID = 0;
	private static final int COLUMN_TYPE = 2;
	private static final int COLUMN_ALIAS = 1;
	private static final int COLUMN_CBU = 3;
	private static final int COLUMN_BALANCE = 4;
	private static final int COLUMN_CLIENTID = 5;
	private ManagerPanel manager;
	private ServiceTable st = new ServiceTable();

	private String[] columnsName = { "ID", "Alias", "Tipo", "CBU", "Saldo", "Cliente" };

	private Class[] columnsType = { Integer.class, String.class, String.class, Integer.class, Integer.class,
			String.class };

	private List<Account> accList = null;

	public AccTable() {
	}

	public AccTable(List<Account> accList, ManagerPanel manager) {
		this.accList = accList;
		this.manager = manager;
	}

	@Override
	public String getColumnName(int col) {
		return columnsName[col];
	}

	@Override
	public int getRowCount() {
		if (accList == null) {
			return 0;
		}
		return accList.size();
	}

	@Override
	public int getColumnCount() {
		return columnsName.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Account a = accList.get(rowIndex);
		Object r = null;
		switch (columnIndex) {
			case COLUMN_ID:
				r = a.getId();
				break;
			case COLUMN_TYPE:
				r = a.getType();
				break;
			case COLUMN_ALIAS:
				r = a.getAlias();
				break;
			case COLUMN_CBU:
				r = a.getCBU();
				break;
			case COLUMN_BALANCE:
				r = a.getBalance();
				break;
			case COLUMN_CLIENTID:
				r = getClient(a.getClient());
				break;
			default:
				r = new String("");
		}
		return r;
	}

	public List<Account> getAccountList() {
		return accList;
	}

	public void setAccountList(List<Account> accList) {
		this.accList = accList;
	}

	public String[] getColumnsName() {
		return columnsName;
	}

	public void setColumnsName(String[] columnsName) {
		this.columnsName = columnsName;
	}

	public Class[] getColumnsType() {
		return columnsType;
	}

	public void setColumnsType(Class[] columnsType) {
		this.columnsType = columnsType;
	}

	private String getClient(int id) {
		String name = "";
		try {
			name = st.getClient(id).getName();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(manager.getFrame(),e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		return name;
	}
}
