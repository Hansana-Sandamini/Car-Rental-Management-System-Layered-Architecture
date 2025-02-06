package lk.ijse.rdfcarrentals.dao.custom.impl;

import lk.ijse.rdfcarrentals.dao.SQLUtil;
import lk.ijse.rdfcarrentals.dao.custom.ReservationDAO;
import lk.ijse.rdfcarrentals.entity.Reservation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReservationDAOImpl implements ReservationDAO {

    @Override
    public ArrayList<Reservation> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM reservation");

        ArrayList<Reservation> reservations = new ArrayList<>();

        while (rst.next()) {
            Reservation reservation = new Reservation();
            reservation.setReservationId(rst.getString("reservation_id"));
            reservation.setCustomerNic(rst.getString("customer_nic"));
            reservation.setCashierUsername(rst.getString("cashier_username"));
            reservation.setPickUpDate(rst.getDate("pick_up_date"));
            reservation.setPickUpTime(rst.getString("pick_up_time"));
            reservation.setReturnDate(rst.getDate("return_date"));
            reservation.setReturnTime(rst.getString("return_time"));
            reservation.setIsDriverWant(rst.getString("is_driver_want"));
            reservations.add(reservation);
        }
        return reservations;
    }

    @Override
    public void save(Reservation reservation) throws SQLException, ClassNotFoundException {
        SQLUtil.execute(
                "INSERT INTO reservation VALUES (?,?,?,?,?,?,?,?)",
                reservation.getReservationId(),
                reservation.getCustomerNic(),
                reservation.getCashierUsername(),
                reservation.getPickUpDate(),
                reservation.getPickUpTime(),
                reservation.getReturnDate(),
                reservation.getReturnTime(),
                reservation.getIsDriverWant()
        );
    }

    @Override
    public void update(Reservation reservation) throws SQLException, ClassNotFoundException {
    }

    @Override
    public void delete(String selectedReservation) throws SQLException, ClassNotFoundException {
        SQLUtil.execute("DELETE FROM reservation WHERE reservation_id = ?", selectedReservation);
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT reservation_id FROM reservation ORDER BY reservation_id DESC LIMIT 1");

        if (rst.next()) {
            String lastId = rst.getString(1);
            String substring = lastId.substring(1);
            int i = Integer.parseInt(substring);
            int newIdIndex = i + 1;
            return String.format("R%03d", newIdIndex);
        }
        return "R001";
    }

    @Override
    public Reservation search(String selectedReservationID) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM reservation WHERE reservation_id = ?", selectedReservationID);
        rst.next();
            Reservation reservation = new Reservation(
                rst.getString("reservation_id"),
                rst.getString("customer_nic"),
                rst.getString("cashier_username"),
                rst.getDate("pick_up_date"),
                rst.getString("pick_up_time"),
                rst.getDate("return_date"),
                rst.getString("return_time"),
                rst.getString("is_driver_want")
            );
        return reservation;
    }

    @Override
    public ArrayList<String> loadAllIDs() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT reservation_id FROM reservation");

        ArrayList<String> reservationIDS = new ArrayList<>();

        while (rst.next()) {
            reservationIDS.add(rst.getString(1));
        }
        return reservationIDS;
    }

    @Override
    public String getLastID() throws SQLException, ClassNotFoundException {
        return "";
    }
}
