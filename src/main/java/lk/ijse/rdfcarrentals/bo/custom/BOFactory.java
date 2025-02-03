package lk.ijse.rdfcarrentals.bo.custom;

import lk.ijse.rdfcarrentals.bo.custom.impl.CashierBOImpl;
import lk.ijse.rdfcarrentals.bo.custom.impl.CustomerBOImpl;
import lk.ijse.rdfcarrentals.bo.custom.impl.FuelTypeBOImpl;

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
        CASHIER, CUSTOMER, FUELTYPE
    }

    public SuperBO getBO(BOType type) {
        switch (type) {
            case CASHIER: return new CashierBOImpl();
            case CUSTOMER: return new CustomerBOImpl();
            case FUELTYPE: return new FuelTypeBOImpl();
            default: return null;
        }
    }
}
