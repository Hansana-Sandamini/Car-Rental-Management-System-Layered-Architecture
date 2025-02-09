package lk.ijse.rdfcarrentals.bo.custom.impl;

import lk.ijse.rdfcarrentals.bo.custom.BillBO;
import lk.ijse.rdfcarrentals.bo.custom.SuperBO;
import lk.ijse.rdfcarrentals.dao.DAOFactory;
import lk.ijse.rdfcarrentals.dao.custom.BillDAO;
import lk.ijse.rdfcarrentals.dto.BillDTO;
import lk.ijse.rdfcarrentals.entity.Bill;

import java.sql.SQLException;

public class BillBOImpl implements BillBO, SuperBO {

    BillDAO billDAO = (BillDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.BILL);

    @Override
    public String getNextBillId() throws SQLException, ClassNotFoundException {
        return billDAO.generateID();
    }

    @Override
    public void saveBill(BillDTO billDTO) throws SQLException, ClassNotFoundException {
        billDAO.save(new Bill(
                billDTO.getBillId(),
                billDTO.getPaymentId(),
                billDTO.getCreditId(),
                billDTO.getDescription(),
                billDTO.getIssueDate()
        ));
    }
}
