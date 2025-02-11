package lk.ijse.rdfcarrentals.bo.custom;

import lk.ijse.rdfcarrentals.bo.custom.impl.*;

public class BOFactory {

    private static BOFactory boFactory;

    private BOFactory() {}

    public static BOFactory getInstance() {
        if (boFactory == null) {
            boFactory = new BOFactory();
        }
        return boFactory;
    }

    public enum BOType {
        CASHIER, CUSTOMER, FUEL_TYPE, CAR, DRIVER_ASSIGNMENT, REPAIR, DRIVER, RESERVATION_DETAIL, RESERVATION, CREDIT,
        PAYMENT, BILL, REPORT
    }

    public SuperBO getBO(BOType type) {
        switch (type) {
            case CASHIER: return new CashierBOImpl();
            case CUSTOMER: return new CustomerBOImpl();
            case FUEL_TYPE: return new FuelTypeBOImpl();
            case CAR: return new CarBOImpl();
            case DRIVER_ASSIGNMENT: return new DriverAssignmentBOImpl();
            case REPAIR: return new RepairBOImpl();
            case DRIVER: return new DriverBOImpl();
            case RESERVATION_DETAIL: return new ReservationDetailBOImpl();
            case RESERVATION: return new ReservationBOImpl();
            case CREDIT: return new CreditBOImpl();
            case PAYMENT: return new PaymentBOImpl();
            case BILL: return new BillBOImpl();
            case REPORT: return new ReportBOImpl();
            default: return null;
        }
    }
}
