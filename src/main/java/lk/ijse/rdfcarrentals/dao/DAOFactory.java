package lk.ijse.rdfcarrentals.dao;

import lk.ijse.rdfcarrentals.dao.custom.impl.CashierDAOImpl;

public class DAOFactory {

    private static DAOFactory daoFactory;

    private DAOFactory() {}

    public static DAOFactory getInstance() {
        return daoFactory==null?daoFactory=new DAOFactory():daoFactory;
    }

    public enum DAOType {
        CASHIER
    }

    public SuperDAO getDAO(DAOType type) {
        switch (type) {
            case CASHIER: return new CashierDAOImpl();
            default: return null;
        }
    }
}
