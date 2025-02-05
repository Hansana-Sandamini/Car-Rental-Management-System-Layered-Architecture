package lk.ijse.rdfcarrentals.dao.custom.impl;

import lk.ijse.rdfcarrentals.dao.SQLUtil;
import lk.ijse.rdfcarrentals.dao.custom.ReservationDetailDAO;
import lk.ijse.rdfcarrentals.entity.ReservationDetail;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReservationDetailDAOImpl implements ReservationDetailDAO {

    @Override
    public ArrayList<ReservationDetail> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM reservation_detail");

        ArrayList<ReservationDetail> reservationDetails = new ArrayList<>();

        while (rst.next()) {
            ReservationDetail reservationDetail = new ReservationDetail();
            reservationDetail.setReservationId(rst.getString("reservation_id"));
            reservationDetail.setLicensePlateNo(rst.getString("license_plate_no"));
            reservationDetail.setDriverCost(rst.getDouble("driver_cost"));
            reservationDetail.setTotalAmount(rst.getDouble("total_amount"));
            reservationDetails.add(reservationDetail);
        }
        return reservationDetails;
    }

    @Override
    public void save(ReservationDetail reservationDetail) throws SQLException, ClassNotFoundException {
        SQLUtil.execute(
                "INSERT INTO reservation_detail VALUES (?,?,?,?)",
                reservationDetail.getReservationId(),
                reservationDetail.getLicensePlateNo(),
                reservationDetail.getDriverCost(),
                reservationDetail.getTotalAmount()
        );
    }

    @Override
    public void update(ReservationDetail reservationDetail) throws SQLException, ClassNotFoundException {
    }

    @Override
    public void delete(String id) throws SQLException, ClassNotFoundException {
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateID() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public ReservationDetail search(String id) throws SQLException, ClassNotFoundException {
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
