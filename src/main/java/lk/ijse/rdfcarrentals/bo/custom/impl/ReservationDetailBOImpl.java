package lk.ijse.rdfcarrentals.bo.custom.impl;

import lk.ijse.rdfcarrentals.bo.custom.ReservationDetailBO;
import lk.ijse.rdfcarrentals.bo.custom.SuperBO;
import lk.ijse.rdfcarrentals.dao.DAOFactory;
import lk.ijse.rdfcarrentals.dao.custom.impl.ReservationDetailDAOImpl;
import lk.ijse.rdfcarrentals.dto.ReservationDetailDTO;
import lk.ijse.rdfcarrentals.entity.ReservationDetail;

import java.sql.SQLException;
import java.util.ArrayList;

public class ReservationDetailBOImpl implements ReservationDetailBO, SuperBO {

    ReservationDetailDAOImpl reservationDetailDAO = (ReservationDetailDAOImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.RESERVATION_DETAIL);

    @Override
    public ArrayList<ReservationDetailDTO> getReservationDetails() throws SQLException, ClassNotFoundException {
        ArrayList<ReservationDetailDTO> reservationDetailDTOS = new ArrayList<>();
        ArrayList<ReservationDetail> reservationDetails = reservationDetailDAO.getAll();

        for (ReservationDetail reservationDetail : reservationDetails) {
            reservationDetailDTOS.add(new ReservationDetailDTO(
                    reservationDetail.getReservationId(),
                    reservationDetail.getLicensePlateNo(),
                    reservationDetail.getDriverCost(),
                    reservationDetail.getTotalAmount()
            ));
        }
        return reservationDetailDTOS;
    }

    @Override
    public void saveReservationDetail(ReservationDetailDTO reservationDetailDTO) throws SQLException, ClassNotFoundException {
        reservationDetailDAO.save(new ReservationDetail(
                reservationDetailDTO.getReservationId(),
                reservationDetailDTO.getLicensePlateNo(),
                reservationDetailDTO.getDriverCost(),
                reservationDetailDTO.getTotalAmount()
        ));
    }

    @Override
    public boolean saveReservationDetailList(ArrayList<ReservationDetail> reservationDetails) throws SQLException, ClassNotFoundException {
        for (ReservationDetail reservationDetail : reservationDetails) {
            reservationDetailDAO.save(reservationDetail);
        }
        return true;
    }

}
