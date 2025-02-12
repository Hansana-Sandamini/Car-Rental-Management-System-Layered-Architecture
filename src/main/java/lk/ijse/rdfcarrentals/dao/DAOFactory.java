package lk.ijse.rdfcarrentals.dao;

import lk.ijse.rdfcarrentals.dao.custom.impl.*;

public class DAOFactory {

    private static DAOFactory daoFactory;

    private DAOFactory() {}

    public static DAOFactory getInstance() {
        return daoFactory==null?daoFactory=new DAOFactory():daoFactory;
    }

    public enum DAOType {
        CASHIER, CUSTOMER, FUEL_TYPE, CAR, DRIVER_ASSIGNMENT, REPAIR, DRIVER, RESERVATION_DETAIL, RESERVATION, CREDIT,
        PAYMENT, BILL, QUERY, ADMIN
    }

    public SuperDAO getDAO(DAOType type) {
        switch (type) {
            case CASHIER: return new CashierDAOImpl();
            case CUSTOMER: return new CustomerDAOImpl();
            case FUEL_TYPE: return new FuelTypeDAOImpl();
            case CAR: return new CarDAOImpl();
            case DRIVER_ASSIGNMENT: return new DriverAssignmentDAOImpl();
            case REPAIR: return new RepairDAOImpl();
            case DRIVER: return new DriverDAOImpl();
            case RESERVATION_DETAIL: return new ReservationDetailDAOImpl();
            case RESERVATION: return new ReservationDAOImpl();
            case CREDIT: return new CreditDAOImpl();
            case PAYMENT: return new PaymentDAOImpl();
            case BILL: return new BillDAOImpl();
            case QUERY: return new QueryDAOImpl();
            case ADMIN: return new AdminDAOImpl();
            default: return null;
        }
    }
}
