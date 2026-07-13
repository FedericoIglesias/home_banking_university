package service;

import java.util.List;

import app.FormLabel;
import dao.ClientDAO;
import exception.DAOException;
import exception.ServiceException;
import model.Client;

public class ClientService {
	private ClientDAO clDAO = new ClientDAO();

	public ClientService() {
		super();
	}

	public Client getClient(Integer id) {
		Client cl = null;
		try {
			cl = clDAO.ReadId(id);
			if (cl.getId() == null) {
				throw new ServiceException("cliente no encontrado", null);
			}
			return cl;
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void deleteClient(Integer id) {
		try {
			clDAO.Delete(id);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public List<Client> getPoolCl() {
		List<Client> lsCl = null;
		try {
			lsCl = clDAO.ReadPool();
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
		return lsCl;
	}

	public Client loginUser(Integer dni) {
		Client cl = null;
		try {
			cl = clDAO.Read(dni);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
		return cl;
	}

	public void createClient(Client c) {
		try {
			clDAO.Insert(c);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public Client getClient(String email) {
		Client cl = null;
		try {
			cl = clDAO.Read(email);
			if (cl.getId() == null) {
				throw new ServiceException("cliente no encontrado", null);
			}
			return cl;
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public List<Client> getClientPool() {
		List<Client> lsC = null;
		try {
			lsC = clDAO.ReadPool();
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
		return lsC;
	}

	public void checkInputs(FormLabel email, FormLabel name, FormLabel dni, FormLabel pass) {
		try {
			email.checkText();
			name.checkText();
			dni.checkNumber();
			pass.checkText();
		} catch (ServiceException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

}
