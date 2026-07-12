package service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import dao.TransferDAO;
import exception.DAOException;
import exception.ServiceException;
import model.Transfer;

public class TransferService {

  private TransferDAO trDAO = new TransferDAO();

  public TransferService() {
  }

  public List<Transfer> getTrCl(int id) {
    List<Transfer> lsTr = null;
    try {
      lsTr = trDAO.ReadTrCl(id);
    } catch (DAOException e) {
      throw new ServiceException(e.getMessage(), e);
    }
    return lsTr;
  }

  public List<Transfer> getPoolTr() {
    List<Transfer> lsTr = null;
    try {
      lsTr = trDAO.ReadPool();
    } catch (DAOException e) {
      throw new ServiceException(e.getMessage(), e);
    }
    return lsTr;
  }

  public void deleteTr(Integer id) {
    try {
      trDAO.Delete(id);
    } catch (DAOException e) {
      throw new ServiceException(e.getMessage(), e);
    }
  }

  public void generateResume(List<Transfer> list) {
    FileWriter fw = null;
    String name = LocalDate.now().toString();
    try {
      File file = new File("./" + name + ".csv");
      fw = new FileWriter(file);
      fw.append("id,origenId,dstId,Saldo,fecha\n");
      for (Transfer tr : list) {
        String txt = tr.getId().toString() + "," + tr.getOriginId().toString() + "," + tr.getDstId().toString() + ","
            + tr.getBalance().toString() + "," + new Date(tr.getDate()).toString();
        fw.append(txt + "\n");
      }
    } catch (FileNotFoundException e) {
      throw new ServiceException(e.getMessage(), e);
    } catch (IOException e) {
      throw new ServiceException(e.getMessage(), e);
    } finally {
      try {
        fw.close();
      } catch (IOException e) {
        throw new ServiceException(e.getMessage(), e);
      }
    }
  }

  public void createTransfers(Transfer trans) {
    try {
      trDAO.Insert(trans);
    } catch (DAOException e) {
      throw new ServiceException(e.getMessage(), e);
    }
  }

}
