package lk.ijse.rdfcarrentals.bo.custom;

import lk.ijse.rdfcarrentals.dto.ReservationDetailDTO;
import lk.ijse.rdfcarrentals.entity.ReservationDetail;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ReservationDetailBO {
    ArrayList<ReservationDetailDTO> getReservationDetails() throws SQLException, ClassNotFoundException;
    void saveReservationDetail(ReservationDetailDTO reservationDetailDTO) throws SQLException, ClassNotFoundException;
    void saveReservationDetailList(ArrayList<ReservationDetail> reservationDetails) throws SQLException, ClassNotFoundException;
}
