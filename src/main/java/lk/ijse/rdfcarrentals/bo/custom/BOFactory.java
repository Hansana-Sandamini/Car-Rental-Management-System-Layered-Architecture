package lk.ijse.rdfcarrentals.bo.custom;

import lk.ijse.rdfcarrentals.bo.custom.impl.CashierBOImpl;

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
        CASHIER
    }

    public SuperBO getBO(BOType type) {
        switch (type) {
            case CASHIER: return new CashierBOImpl();
            default: return null;
        }
    }
}
