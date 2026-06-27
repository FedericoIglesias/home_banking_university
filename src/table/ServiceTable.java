package table;

import java.sql.SQLException;

import dao.AccountDAO;
import dao.ClientDAO;
import dao.TransferDAO;
import model.Client;

public class ServiceTable {
	private ClientDAO clDAO = new ClientDAO();
	private AccountDAO acDAO = new AccountDAO();
	private TransferDAO trDAO = new TransferDAO();
	
	public ServiceTable() {
		super();
	}
	
	public Client getClient(Integer id)throws Exception{
		Client cl = null;
		try {
			cl=clDAO.ReadId(id);
		}catch(SQLException e) {
			throw new Exception(e);
		}
		return cl;
	}
	
}
