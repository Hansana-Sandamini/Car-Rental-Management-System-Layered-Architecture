package lk.ijse.rdfcarrentals.dao;

import lk.ijse.rdfcarrentals.dao.custom.impl.*;

public class DAOFactory {

    private static DAOFactory daoFactory;

    private DAOFactory() {}

    public static DAOFactory getInstance() {
        return daoFactory==null?daoFactory=new DAOFactory():daoFactory;
    }

    public enum DAOType {
        CASHIER, CUSTOMER, FUEL_TYPE, CAR, DRIVER_ASSIGNMENT
    }

    public SuperDAO getDAO(DAOType type) {
        switch (type) {
            case CASHIER: return new CashierDAOImpl();
            case CUSTOMER: return new CustomerDAOImpl();
            case FUEL_TYPE: return new FuelTypeDAOImpl();
            case CAR: return new CarDAOImpl();
            case DRIVER_ASSIGNMENT: return new DriverAssignmentDAOImpl();
            default: return null;
        }
    }
}
