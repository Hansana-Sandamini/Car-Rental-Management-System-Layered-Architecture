package lk.ijse.rdfcarrentals.bo.custom.impl;

import lk.ijse.rdfcarrentals.bo.custom.ReservationBO;
import lk.ijse.rdfcarrentals.bo.custom.ReservationDetailBO;
import lk.ijse.rdfcarrentals.bo.custom.SuperBO;
import lk.ijse.rdfcarrentals.dao.DAOFactory;
import lk.ijse.rdfcarrentals.dao.custom.impl.*;
import lk.ijse.rdfcarrentals.db.DBConnection;
import lk.ijse.rdfcarrentals.dto.ReservationDTO;
import lk.ijse.rdfcarrentals.entity.Reservation;
import lk.ijse.rdfcarrentals.entity.ReservationDetail;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReservationBOImpl implements ReservationBO, SuperBO {

    ReservationDAOImpl reservationDAO = (ReservationDAOImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.RESERVATION);
    DriverDAOImpl driverDAO = (DriverDAOImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.DRIVER);
    CarDAOImpl carDAO = (CarDAOImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.CAR);
    ReservationDetailBO reservationDetailBO = new ReservationDetailBOImpl();
    DriverAssignmentDAOImpl driverAssignmentDAO = new DriverAssignmentDAOImpl();

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
    public boolean saveReservation(ReservationDTO reservationDTO) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();

        try {
            connection.setAutoCommit(false);

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

            boolean isReservationDetailListSaved = reservationDetailBO.saveReservationDetailList(reservationDTO.getReservationDetails());
            if (!isReservationDetailListSaved) {
                connection.rollback();
                return false;
            }

            for (ReservationDetail reservationDetail : reservationDTO.getReservationDetails()) {
                boolean isCarUpdated = carDAO.updateCarAvailability(reservationDetail.getLicensePlateNo(), "No");

                if (!isCarUpdated) {
                    connection.rollback();
                    return false;
                }

                if (reservationDTO.getIsDriverWant().equalsIgnoreCase("Yes")) {
                    String driverNic = driverAssignmentDAO.getDriverNicByLicensePlate(reservationDetail.getLicensePlateNo());

                    if (driverNic != null) {
                        boolean isDriverUpdated = driverDAO.updateDriverAvailability(driverNic, "No");

                        if (!isDriverUpdated) {
                            connection.rollback();
                            return false;
                        }
                    } else {
                        connection.rollback();
                        throw new SQLException("Driver not linked to the car being reserved.");
                    }
                }
            }
            connection.commit();
            return true;

        } catch (SQLException e) {
            connection.rollback();
            e.printStackTrace();
            return false;

        } finally {
            connection.setAutoCommit(true);
        }
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
