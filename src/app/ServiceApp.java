package app;

import java.sql.SQLException;
import java.util.List;

import dao.AccountDAO;
import dao.ClientDAO;
import dao.TransferDAO;
import model.Account;
import model.Client;
import model.Transfer;

public class ServiceApp {

	private ClientDAO clDAO = new ClientDAO();
	private AccountDAO acDAO = new AccountDAO();
	private TransferDAO trDAO = new TransferDAO();
	
	public ServiceApp() {
		super();
	}
	
	public List<Account> getPoolAcc()throws Exception{
		List<Account> lsAcc = null;
		try {
			lsAcc = acDAO.ReadPool();
		}catch(SQLException e) {
			throw new Exception(e);
		}
		return lsAcc;
	}

	public void deleteAcc(Integer id)throws Exception{
		try {
			new AccountDAO().Delete(id);
		}catch(SQLException e) {
			throw new Exception(e);
		}
	}
	
	public void deleteClient(Integer id) throws Exception{
		try {
			clDAO.Delete(id);
		}catch(SQLException e) {
			throw new Exception(e);
		}
	}
	
	public List<Client> getPoolCl() throws Exception{
		List<Client> lsCl=null;
		try {
			lsCl = clDAO.ReadPool();
		}catch(SQLException e) {
			throw new Exception(e);
		}
		return lsCl;
	}
	
	public Client loginUser(Integer dni)throws Exception{
		Client cl = null;
		try {
			cl = clDAO.Read(dni);
		}catch(SQLException e) {
			throw new Exception(e);
		}
		return cl;
	}
	
	public List<Transfer> getPoolTr()throws Exception{
		List<Transfer> lsTr = null;
		try {
			lsTr = trDAO.ReadPool();
		}catch(SQLException e) {
			throw new Exception(e);
		}
		return lsTr;
	}
	
	public void deleteTr(Integer id) throws Exception {
		try {
			trDAO.Delete(id);
		}catch(SQLException e) {
			throw new Exception(e);
		}
	}
	
}
