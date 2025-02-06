package lk.ijse.rdfcarrentals.bo.custom;

import lk.ijse.rdfcarrentals.dto.ReservationDTO;
import lk.ijse.rdfcarrentals.entity.Reservation;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ReservationBO {
    ArrayList<ReservationDTO> getAllReservations() throws SQLException, ClassNotFoundException;
    void saveReservation(ReservationDTO reservationDTO) throws SQLException, ClassNotFoundException;
    String getNextReservationId() throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllReservationIDS() throws SQLException, ClassNotFoundException;
    Reservation searchReservation(String selectedReservationID) throws SQLException, ClassNotFoundException;
    void deleteReservation(String selectedReservation) throws SQLException, ClassNotFoundException;
}
