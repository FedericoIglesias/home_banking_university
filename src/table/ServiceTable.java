package table;

import dao.ClientDAO;
import exception.DAOException;
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
		}catch(DAOException e) {
			throw new Exception(e);
		}
		return cl;
	}
	
}
