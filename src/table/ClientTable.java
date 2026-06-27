package table;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Account;
import model.Client;

public class ClientTable extends AbstractTableModel implements ActionListener{
	
	private static final int COLUMN_ID =0 ;
	private static final int COLUMN_NAME =1 ;
	private static final int COLUMN_EMAIL =2 ;
	private static final int COLUMN_DNI =3 ;
	private static final int COLUMN_PASS =4 ;
	
	private String[] columnsName = {"ID","nombre","email","DNI","contraseñas"};
	
	private Class[] columnsType = {Integer.class,String.class,String.class,Integer.class,String.class};

	
	private List<Client> clientList = null;
	
	public ClientTable() {
	}
	
	public ClientTable(List<Client> clientList) {
		this.clientList = clientList;
	}
	
	@Override 
	public String getColumnName(int col) {
		return columnsName[col];
	}
	
	@Override
	public int getRowCount() {
		if(clientList == null) {
			return 0;
		}
		return clientList.size();
	}

	@Override
	public int getColumnCount() {
		return columnsName.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
			Client c = clientList.get(rowIndex);
			Object r = null;
			switch(columnIndex) {
				case COLUMN_ID:
					r = c.getId();
					break;
				case COLUMN_NAME:
					r = c.getName();
					break;
				case COLUMN_EMAIL:
					r = c.getEmail();
					break;
				case COLUMN_DNI:
					r = c.getDni();
					break;
				case COLUMN_PASS:
					r = c.getPass();
					break;	
				default:
					r = new String("");
			}
		return r;
	}

	public List<Client> getClientList() {
		return clientList;
	}

	public void setClientList(List<Client> clientList) {
		this.clientList = clientList;
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
