package lk.ijse.rdfcarrentals.bo.custom;

import lk.ijse.rdfcarrentals.dto.CarDTO;
import lk.ijse.rdfcarrentals.entity.Car;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CarBO {
    ArrayList<CarDTO> getAllCars() throws SQLException, ClassNotFoundException;
    void saveCar(CarDTO carDTO) throws SQLException, ClassNotFoundException;
    void updateCar(CarDTO carDTO) throws SQLException, ClassNotFoundException;
    void deleteCar(String selectedCar) throws SQLException, ClassNotFoundException;
    Car searchCar(String selectedLicensePlateNo) throws SQLException, ClassNotFoundException;
    ArrayList<String> loadAllLicensePlateNos() throws SQLException, ClassNotFoundException;
}
