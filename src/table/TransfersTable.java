package table;

import java.util.Date;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Transfer;

public class TransfersTable extends AbstractTableModel {
	private static final int COLUMN_ID = 0;
	private static final int COLUMN_ORIGINID = 1;
	private static final int COLUMN_DSTID = 2;
	private static final int COLUMN_BALANCE = 3;
	private static final int COLUMN_DATE = 4;

	private String[] columnsName = { "ID", "Origen", "Destino", "Saldo", "Fecha" };

	private Class[] columnsType = { Integer.class, Integer.class, Integer.class, Integer.class, Date.class };

	private List<Transfer> transfersList = null;

	public TransfersTable() {
	}

	public TransfersTable(List<Transfer> transfersList) {
		this.transfersList = transfersList;
	}

	@Override
	public String getColumnName(int col) {
		return columnsName[col];
	}

	@Override
	public int getRowCount() {
		if (transfersList == null) {
			return 0;
		}
		return transfersList.size();
	}

	@Override
	public int getColumnCount() {
		return columnsName.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Transfer a = transfersList.get(rowIndex);
		Object r = null;
		switch (columnIndex) {
			case COLUMN_ID:
				r = a.getId();
				break;
			case COLUMN_ORIGINID:
				r = a.getOriginId();
				break;
			case COLUMN_DSTID:
				r = a.getDstId();
				break;
			case COLUMN_BALANCE:
				r = a.getBalance();
				break;
			case COLUMN_DATE:
				r = new Date(a.getDate());
				break;
			default:
				r = new String("");
		}
		return r;
	}

	public List<Transfer> getAccountList() {
		return transfersList;
	}

	public void setTransferList(List<Transfer> transfersList) {
		this.transfersList = transfersList;
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
}
