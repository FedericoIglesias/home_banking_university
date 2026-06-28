package table;

import java.sql.SQLException;

import dao.ClientDAO;
import model.Client;

public class ServiceTable {
	private ClientDAO clDAO = new ClientDAO();
	
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
