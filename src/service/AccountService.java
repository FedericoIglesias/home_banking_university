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

  public void createAccount(Account acc) throws Exception {
    try {
      acDAO.Insert(acc);
    } catch (Exception e) {
      throw new ServiceException(e.getMessage(), e);
    }
  }

  public List<Account> getAccount(Integer id) throws Exception {
    List<Account> acc = null;
    try {
      acc = acDAO.ReadClientId(id);
    } catch (Exception e) {
      throw new ServiceException(e.getMessage(), e);
    }
    return acc;
  }

  public Account getByAlias(String alias) throws Exception {
    Account acc = null;
    try {
      acc = acDAO.ReadAlias(alias);
    } catch (Exception e) {
      throw new ServiceException(e.getMessage(), e);
    }
    return acc;
  }

  public Account getByCBU(String CBU) throws Exception {
    Account acc = null;
    try {
      acc = acDAO.ReadCBU(CBU);
    } catch (Exception e) {
      throw new ServiceException(e.getMessage(), e);
    }
    return acc;
  }

  public void updateAllAcc(Integer oriId, Integer dstId, Integer blc) throws Exception {
    Account acc = null;
    try {
      acc = acDAO.Read(oriId);
      acc.setBalance(acc.getBalance() - blc);
      acDAO.Update(acc);
      acc = acDAO.Read(dstId);
      acc.setBalance(acc.getBalance() + blc);
      acDAO.Update(acc);
    } catch (Exception e) {
      throw new ServiceException(e.getMessage(), e);
    }
  }
}
