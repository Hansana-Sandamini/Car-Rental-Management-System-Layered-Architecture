package lk.ijse.rdfcarrentals.dao.custom;

import lk.ijse.rdfcarrentals.dao.CrudDAO;
import lk.ijse.rdfcarrentals.entity.Admin;

import java.sql.SQLException;

public interface AdminDAO extends CrudDAO<Admin> {
    Admin getAdminByUsername(String username) throws SQLException, ClassNotFoundException;

}
