package lk.ijse.rdfcarrentals.dao;

import lk.ijse.rdfcarrentals.dao.custom.impl.CashierDAOImpl;
import lk.ijse.rdfcarrentals.dao.custom.impl.CustomerDAOImpl;

public class DAOFactory {

    private static DAOFactory daoFactory;

    private DAOFactory() {}

    public static DAOFactory getInstance() {
        return daoFactory==null?daoFactory=new DAOFactory():daoFactory;
    }

    public enum DAOType {
        CASHIER, CUSTOMER
    }

    public SuperDAO getDAO(DAOType type) {
        switch (type) {
            case CASHIER: return new CashierDAOImpl();
            case CUSTOMER: return new CustomerDAOImpl();
            default: return null;
        }
    }
}
