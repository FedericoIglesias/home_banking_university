package service;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import app.FormLabel;
import dao.TransferDAO;
import exception.DAOException;
import exception.ServiceException;
import model.Transfer;

public class TransferService {

  private TransferDAO trDAO = new TransferDAO();
  private AccountService accSer = new AccountService();

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

  public void checkInputTrs(FormLabel balance, FormLabel originId, FormLabel alias, FormLabel id, FormLabel cbu,
      FormLabel dstId) {
    int count = 0;
    try {
      balance.checkNumber();
      originId.checkList();
      if (alias.checkBox()) {
        count++;
      }
      if (id.checkBox()) {
        count++;
      }
      if (cbu.checkBox()) {
        count++;
      }
      if (count == 0 || count > 1) {
        cbu.getBox().setBackground(Color.RED);
        id.getBox().setBackground(Color.RED);
        alias.getBox().setBackground(Color.RED);
        throw new ServiceException("Opciones no elegidas", null);
      } else {
        cbu.getBox().setBackground(Color.WHITE);
        id.getBox().setBackground(Color.WHITE);
        alias.getBox().setBackground(Color.WHITE);
      }
      if (id.checkBox()) {
        dstId.checkNumber();
      } else {
        dstId.checkText();
      }
    } catch (ServiceException e) {
      throw new ServiceException(e.getMessage(), e);
    }
  }

  public void checkBalance(FormLabel originId, FormLabel balance) {
    String[] parts = originId.getList().getSelectedValue().split(" -- ");
    if (Integer.parseInt(balance.getTxt().getText()) > Integer.parseInt(parts[1])) {
      throw new ServiceException("Saldo insuficiente", null);
    }
  }

  public int getAccountDst(FormLabel cbu, FormLabel id, FormLabel alias, FormLabel dstId) {
    Object ids = null;
    try {
      if (cbu.checkBox()) {
        ids = accSer.getByCBU(dstId.getTxt().getText()).getId();
      }
      if (id.checkBox()) {
        ids = Integer.parseInt(dstId.getTxt().getText());
      }
      if (alias.checkBox()) {
        ids = accSer.getByAlias(dstId.getTxt().getText()).getId();
      }
    } catch (ServiceException e) {
      throw new ServiceException(e.getMessage(), e);
    }
    if (ids == null) {
      throw new ServiceException("Cuenta destino no encontrada", null);
    }
    return (int) ids;
  }

}
