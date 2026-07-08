package panel;

import java.sql.SQLException;
import java.util.List;

import dao.AccountDAO;
import dao.CardDAO;
import dao.ClientDAO;
import dao.TransferDAO;
import exception.DAOException;
import exception.ServiceException;
import model.Account;
import model.Card;
import model.Client;
import model.Transfer;

public class ServiceApp {

	private ClientDAO clDAO = new ClientDAO();
	private AccountDAO acDAO = new AccountDAO();
	private TransferDAO trDAO = new TransferDAO();
	private CardDAO cdDAO = new CardDAO();

	public ServiceApp() {
		super();
	}

	public List<Account> getAccCl(int id) throws Exception {
		List<Account> lsAcc = null;
		try {
			lsAcc = acDAO.ReadClientId(id);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
		return lsAcc;
	}

	public List<Transfer> getTrCl(int id) throws Exception {
		List<Transfer> lsTr = null;
		try {
			lsTr = trDAO.ReadTrCl(id);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
		return lsTr;
	}

	public List<Account> getPoolAcc() throws Exception {
		List<Account> lsAcc = null;
		try {
			lsAcc = acDAO.ReadPool();
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
		return lsAcc;
	}

	public void deleteAcc(Integer id) throws Exception {
		try {
			new AccountDAO().Delete(id);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
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

	public List<Transfer> getPoolTr() throws Exception {
		List<Transfer> lsTr = null;
		try {
			lsTr = trDAO.ReadPool();
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
		return lsTr;
	}

	public void deleteTr(Integer id) throws Exception {
		try {
			trDAO.Delete(id);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public List<Card> getPoolCd() throws Exception {
		List<Card> listCards = null;
		try {
			listCards = cdDAO.ReadPool();
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
		return listCards;
	}

	public List<Card> getClCd(Integer id) throws Exception {
		List<Card> listCards = null;
		try {
			listCards = cdDAO.ReadPoolbyCl(id);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
		return listCards;
	}

	public void deleteCard(String Number) throws Exception {
		try {
			cdDAO.Delete(Number);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

}
