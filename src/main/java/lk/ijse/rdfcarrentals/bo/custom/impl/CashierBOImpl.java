package lk.ijse.rdfcarrentals.bo.custom.impl;

import lk.ijse.rdfcarrentals.bo.custom.CashierBO;
import lk.ijse.rdfcarrentals.bo.custom.SuperBO;
import lk.ijse.rdfcarrentals.dao.DAOFactory;
import lk.ijse.rdfcarrentals.dao.custom.impl.CashierDAOImpl;
import lk.ijse.rdfcarrentals.dto.CashierDTO;
import lk.ijse.rdfcarrentals.entity.Cashier;

import java.sql.SQLException;
import java.util.ArrayList;

public class CashierBOImpl implements CashierBO, SuperBO {

    CashierDAOImpl cashierDAO = (CashierDAOImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.CASHIER);

    @Override
    public ArrayList<CashierDTO> getAllCashiers() throws SQLException, ClassNotFoundException {
        ArrayList<CashierDTO> cashierDTOS = new ArrayList<>();
        ArrayList<Cashier> cashiers = cashierDAO.getAll();
        for (Cashier cashier : cashiers){
            cashierDTOS.add(new CashierDTO(
                    cashier.getUserName(),
                    cashier.getPassword(),
                    cashier.getName(),
                    cashier.getContactNumber(),
                    cashier.getEmail()
            ));
        }
        return cashierDTOS;
    }

    @Override
    public void saveCashier(CashierDTO cashierDTO) throws SQLException, ClassNotFoundException {
        cashierDAO.save(new Cashier(
                cashierDTO.getUserName(),
                cashierDTO.getPassword(),
                cashierDTO.getName(),
                cashierDTO.getContactNumber(),
                cashierDTO.getEmail()
        ));
    }

    @Override
    public void updateCashier(CashierDTO cashierDTO) throws SQLException, ClassNotFoundException {
        cashierDAO.update(new Cashier(
                cashierDTO.getUserName(),
                cashierDTO.getPassword(),
                cashierDTO.getName(),
                cashierDTO.getContactNumber(),
                cashierDTO.getEmail()
        ));
    }

    @Override
    public Cashier searchCashier(String selectedCashierUsername) throws SQLException, ClassNotFoundException {
        return cashierDAO.search(selectedCashierUsername);
    }

    @Override
    public void deleteCashier(String selectedCashier) throws SQLException, ClassNotFoundException {
        cashierDAO.delete(selectedCashier);
    }

}
