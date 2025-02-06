package lk.ijse.rdfcarrentals.bo.custom.impl;

import lk.ijse.rdfcarrentals.bo.custom.ReservationBO;
import lk.ijse.rdfcarrentals.bo.custom.SuperBO;
import lk.ijse.rdfcarrentals.dao.DAOFactory;
import lk.ijse.rdfcarrentals.dao.custom.impl.ReservationDAOImpl;
import lk.ijse.rdfcarrentals.dto.ReservationDTO;
import lk.ijse.rdfcarrentals.entity.Reservation;

import java.sql.SQLException;
import java.util.ArrayList;

public class ReservationBOImpl implements ReservationBO, SuperBO {

    ReservationDAOImpl reservationDAO = (ReservationDAOImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.RESERVATION);

    @Override
    public ArrayList<ReservationDTO> getAllReservations() throws SQLException, ClassNotFoundException {
        ArrayList<ReservationDTO> reservationDTOS = new ArrayList<>();
        ArrayList<Reservation> reservations = reservationDAO.getAll();

        for (Reservation reservation : reservations) {
            reservationDTOS.add(new ReservationDTO(
                    reservation.getReservationId(),
                    reservation.getCustomerNic(),
                    reservation.getCashierUsername(),
                    reservation.getPickUpDate(),
                    reservation.getPickUpTime(),
                    reservation.getReturnDate(),
                    reservation.getReturnTime(),
                    reservation.getIsDriverWant()
            ));
        }
        return reservationDTOS;
    }

    @Override
    public void saveReservation(ReservationDTO reservationDTO) throws SQLException, ClassNotFoundException {
        reservationDAO.save(new Reservation(
                reservationDTO.getReservationId(),
                reservationDTO.getCustomerNic(),
                reservationDTO.getCashierUsername(),
                reservationDTO.getPickUpDate(),
                reservationDTO.getPickUpTime(),
                reservationDTO.getReturnDate(),
                reservationDTO.getReturnTime(),
                reservationDTO.getIsDriverWant()
        ));
    }

    @Override
    public String getNextReservationId() throws SQLException, ClassNotFoundException {
        return reservationDAO.generateID();
    }

    @Override
    public ArrayList<String> getAllReservationIDS() throws SQLException, ClassNotFoundException {
        return reservationDAO.loadAllIDs();
    }

    @Override
    public Reservation searchReservation(String selectedReservationID) throws SQLException, ClassNotFoundException {
        return reservationDAO.search(selectedReservationID);
    }

    @Override
    public void deleteReservation(String selectedReservation) throws SQLException, ClassNotFoundException {
        reservationDAO.delete(selectedReservation);
    }
}
