package service;

import java.util.List;

import dao.AccountDAO;
import exception.DAOException;
import exception.ServiceException;
import model.Account;

public class AccountService {
  private AccountDAO acDAO = new AccountDAO();

  public AccountService() {
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
      acDAO.Delete(id);
    } catch (DAOException e) {
      throw new ServiceException(e.getMessage(), e);
    }
  }

}
