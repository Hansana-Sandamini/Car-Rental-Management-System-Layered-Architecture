package lk.ijse.rdfcarrentals.dao.custom.impl;

import lk.ijse.rdfcarrentals.dao.SQLUtil;
import lk.ijse.rdfcarrentals.dao.custom.AdminDAO;
import lk.ijse.rdfcarrentals.entity.Admin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminDAOImpl implements AdminDAO {

    @Override
    public Admin getAdminByUsername(String username) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM admin WHERE username=?", username);

        if (rst.next()) {
            return new Admin(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5)
            );
        }
        return null;
    }

    @Override
    public ArrayList<Admin> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Admin dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public void update(Admin dto) throws SQLException, ClassNotFoundException {
    }

    @Override
    public void delete(String id) throws SQLException, ClassNotFoundException {
    }

    @Override
    public String generateID() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public Admin search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<String> loadAllIDs() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String getLastID() throws SQLException, ClassNotFoundException {
        return "";
    }
}
