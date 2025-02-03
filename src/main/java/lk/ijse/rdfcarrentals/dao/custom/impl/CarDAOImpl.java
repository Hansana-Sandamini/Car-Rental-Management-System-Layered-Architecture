package lk.ijse.rdfcarrentals.dao.custom.impl;

import lk.ijse.rdfcarrentals.dao.SQLUtil;
import lk.ijse.rdfcarrentals.dao.custom.CarDAO;
import lk.ijse.rdfcarrentals.entity.Car;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CarDAOImpl implements CarDAO {

    @Override
    public ArrayList<Car> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM car");

        ArrayList<Car> cars = new ArrayList<>();

        while (rst.next()) {
            Car car = new Car();
            car.setLicensePlateNo(rst.getString("license_plate_no"));
            car.setModel(rst.getString("model"));
            car.setColour(rst.getString("colour"));
            car.setDailyRate(rst.getDouble("daily_rate"));
            car.setMonthlyRate(rst.getDouble("monthly_rate"));
            car.setAvailabilityStatus(rst.getString("availability_status"));
            car.setTypeId(rst.getString("type_id"));
            cars.add(car);
        }
        return cars;
    }

    @Override
    public void save(Car car) throws SQLException, ClassNotFoundException {
        SQLUtil.execute(
                "INSERT INTO car VALUES (?,?,?,?,?,?,?)",
                car.getLicensePlateNo(),
                car.getModel(),
                car.getColour(),
                car.getDailyRate(),
                car.getMonthlyRate(),
                car.getAvailabilityStatus(),
                car.getTypeId()
        );
    }

    @Override
    public void update(Car car) throws SQLException, ClassNotFoundException {
        SQLUtil.execute(
                "UPDATE car SET type_id = ?, model = ?, colour = ?, daily_rate = ?, monthly_rate = ?, availability_status = ? WHERE license_plate_no = ?",
                car.getTypeId(),
                car.getModel(),
                car.getColour(),
                car.getDailyRate(),
                car.getMonthlyRate(),
                car.getAvailabilityStatus(),
                car.getLicensePlateNo()
        );
    }

    @Override
    public void delete(String selectedCar) throws SQLException, ClassNotFoundException {
        SQLUtil.execute("DELETE FROM car WHERE license_plate_no = ?", selectedCar);
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
    public Car search(String selectedLicensePlateNo) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM car WHERE license_plate_no = ?", selectedLicensePlateNo);
        rst.next();
        Car car = new Car(
                rst.getString("license_plate_no"),
                rst.getString("model"),
                rst.getString("colour"),
                rst.getDouble("daily_rate"),
                rst.getDouble("monthly_rate"),
                rst.getString("availability_status"),
                rst.getString("type_id")
        );
        return car;
    }

    @Override
    public ArrayList<String> loadAllIDs() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT license_plate_no FROM car");

        ArrayList<String> licensePlateNos = new ArrayList<>();

        while (rst.next()) {
            licensePlateNos.add(rst.getString(1));
        }
        return licensePlateNos;
    }

    @Override
    public String getLastID() throws SQLException, ClassNotFoundException {
        return "";
    }
}
