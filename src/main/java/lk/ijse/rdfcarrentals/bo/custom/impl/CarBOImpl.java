package lk.ijse.rdfcarrentals.bo.custom.impl;

import lk.ijse.rdfcarrentals.bo.custom.CarBO;
import lk.ijse.rdfcarrentals.bo.custom.SuperBO;
import lk.ijse.rdfcarrentals.dao.DAOFactory;
import lk.ijse.rdfcarrentals.dao.custom.impl.CarDAOImpl;
import lk.ijse.rdfcarrentals.dto.CarDTO;
import lk.ijse.rdfcarrentals.entity.Car;

import java.sql.SQLException;
import java.util.ArrayList;

public class CarBOImpl implements CarBO, SuperBO {

    CarDAOImpl carDAO = (CarDAOImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.CAR);

    @Override
    public ArrayList<CarDTO> getAllCars() throws SQLException, ClassNotFoundException {
        ArrayList<CarDTO> carDTOS = new ArrayList<>();
        ArrayList<Car> cars = carDAO.getAll();
        for (Car car : cars) {
            carDTOS.add(new CarDTO(
                    car.getLicensePlateNo(),
                    car.getModel(),
                    car.getColour(),
                    car.getDailyRate(),
                    car.getMonthlyRate(),
                    car.getAvailabilityStatus(),
                    car.getTypeId()
            ));
        }
        return carDTOS;
    }

    @Override
    public void saveCar(CarDTO carDTO) throws SQLException, ClassNotFoundException {
        carDAO.save(new Car(
                carDTO.getLicensePlateNo(),
                carDTO.getModel(),
                carDTO.getColour(),
                carDTO.getDailyRate(),
                carDTO.getMonthlyRate(),
                carDTO.getAvailabilityStatus(),
                carDTO.getTypeId()
        ));
    }

    @Override
    public void updateCar(CarDTO carDTO) throws SQLException, ClassNotFoundException {
        carDAO.update(new Car(
//                carDTO.getTypeId(),
//                carDTO.getModel(),
//                carDTO.getColour(),
//                carDTO.getDailyRate(),
//                carDTO.getMonthlyRate(),
//                carDTO.getLicensePlateNo(),
//                carDTO.getAvailabilityStatus()
                carDTO.getLicensePlateNo(),
                carDTO.getModel(),
                carDTO.getColour(),
                carDTO.getDailyRate(),
                carDTO.getMonthlyRate(),
                carDTO.getAvailabilityStatus(),
                carDTO.getTypeId()
        ));
    }

    @Override
    public void deleteCar(String selectedCar) throws SQLException, ClassNotFoundException {
        carDAO.delete(selectedCar);
    }

    @Override
    public Car searchCar(String selectedLicensePlateNo) throws SQLException, ClassNotFoundException {
        return carDAO.search(selectedLicensePlateNo);
    }

    @Override
    public ArrayList<String> loadAllLicensePlateNos() throws SQLException, ClassNotFoundException {
        return carDAO.loadAllIDs();
    }
}
