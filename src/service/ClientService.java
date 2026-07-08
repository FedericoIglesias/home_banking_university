package service;

import java.util.List;

import dao.ClientDAO;
import exception.DAOException;
import exception.ServiceException;
import model.Client;

public class ClientService {
	private ClientDAO clDAO = new ClientDAO();

	public ClientService() {
		super();
	}

	public Client getClient(Integer id) throws Exception {
		Client cl = null;
		try {
			cl = clDAO.ReadId(id);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
		return cl;
	}

	public void deleteClient(Integer id) throws Exception {
		try {
			clDAO.Delete(id);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public List<Client> getPoolCl() throws Exception {
		List<Client> lsCl = null;
		try {
			lsCl = clDAO.ReadPool();
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
		return lsCl;
	}
	public Client loginUser(Integer dni) throws Exception {
		Client cl = null;
		try {
			cl = clDAO.Read(dni);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
		return cl;
	}

}
