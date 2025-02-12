package lk.ijse.rdfcarrentals.bo.custom.impl;

import lk.ijse.rdfcarrentals.bo.custom.AdminBO;
import lk.ijse.rdfcarrentals.bo.custom.SuperBO;
import lk.ijse.rdfcarrentals.dao.DAOFactory;
import lk.ijse.rdfcarrentals.dao.custom.impl.AdminDAOImpl;
import lk.ijse.rdfcarrentals.entity.Admin;

import java.sql.SQLException;

public class AdminBOImpl implements AdminBO, SuperBO {

    AdminDAOImpl adminDAO = (AdminDAOImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ADMIN);

    @Override
    public Admin login(String username, String password) throws SQLException, ClassNotFoundException {
        Admin admin = adminDAO.getAdminByUsername(username);
        if (admin != null && admin.getPassword().equals(password)) {
            return admin;
        }
        return null;
    }
}
