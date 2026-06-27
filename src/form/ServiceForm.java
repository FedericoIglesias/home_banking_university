package form;

import java.util.List;

import dao.AccountDAO;
import dao.ClientDAO;
import dao.TransferDAO;
import model.Account;
import model.Client;
import model.Transfer;

public class ServiceForm {

	private ClientDAO clDAO = new ClientDAO();
	private AccountDAO acDAO = new AccountDAO();
	private TransferDAO trDAO = new TransferDAO();

	public ServiceForm() {
		super();
	}

	public void createClient(Client c) throws Exception {
		try {
			clDAO.Insert(c);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	public void createAccount(Account acc) throws Exception {
		try {
			acDAO.Insert(acc);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	public Client getClient(String email) throws Exception {
		Client client = null;
		try {
			client = new ClientDAO().Read(email);
		} catch (Exception e) {
			throw new Exception(e);
		}
		return client;
	}

	public List<Client> getClientPool() throws Exception {
		List<Client> lsC = null;
		try {
			lsC = new ClientDAO().ReadPool();
		} catch (Exception e) {
			throw new Exception(e);
		}
		return lsC;
	}

	public List<Account> getAccount(Integer id) throws Exception {
		List<Account> acc = null;
		try {
			acc = acDAO.ReadClientId(id);
		} catch (Exception e) {
			throw new Exception(e);
		}
		return acc;
	}

	public void createTransfers(Transfer trans) throws Exception {
		try {
			trDAO.Insert(trans);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	public void updateAllAcc(Integer oriId, Integer dstId, Integer blc) throws Exception {
		Account acc = null;
		try {
			acc = new AccountDAO().Read(oriId);
			acc.setBalance(acc.getBalance() - blc);
			new AccountDAO().Update(acc);
			acc = new AccountDAO().Read(dstId);
			acc.setBalance(acc.getBalance() + blc);
			new AccountDAO().Update(acc);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	public Account getByAlias(String alias) throws Exception {
		Account acc = null;
		try {
			acc = acDAO.ReadAlias(alias);
		} catch (Exception e) {
			throw new Exception(e);
		}
		return acc;
	}

	public Account getByCBU(String CBU) throws Exception {
		Account acc = null;
		try {
			acc = acDAO.ReadAlias(CBU);
		} catch (Exception e) {
			throw new Exception(e);
		}
		return acc;
	}

}
