package lk.ijse.rdfcarrentals.bo.custom;

import lk.ijse.rdfcarrentals.entity.Admin;

import java.sql.SQLException;

public interface AdminBO {
    Admin login(String username, String password) throws SQLException, ClassNotFoundException;
}
