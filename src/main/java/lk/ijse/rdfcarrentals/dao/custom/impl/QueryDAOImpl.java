package lk.ijse.rdfcarrentals.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.rdfcarrentals.dao.SQLUtil;
import lk.ijse.rdfcarrentals.dao.custom.QueryDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class QueryDAOImpl implements QueryDAO {

    @Override
    public ObservableList<Double> getIncomeMonthly() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute(
                "SELECT MONTH(r.pick_up_date) AS MONTH, " +
                        "COALESCE(SUM(p.amount), 0) + COALESCE(SUM(CASE WHEN c.amount_to_pay = 0 THEN c.total_amount ELSE 0 END), 0) AS monthly_income " +
                        "FROM reservation r " +
                        "LEFT JOIN payment p ON r.reservation_id = p.reservation_id " +
                        "LEFT JOIN credit c ON r.reservation_id = c.reservation_id " +
                        "GROUP BY MONTH(r.pick_up_date) " +
                        "ORDER BY MONTH(r.pick_up_date)"
        );

        Double[] incomeByMonth = new Double[12];
        Arrays.fill(incomeByMonth, 0.0);

        while (rst.next()) {
            int month = rst.getInt("MONTH");
            double income = rst.getDouble("monthly_income");
            incomeByMonth[month - 1] = income;
        }
        return FXCollections.observableArrayList(Arrays.asList(incomeByMonth));
    }

    @Override
    public ArrayList<String> getTopProducts() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute(
                "SELECT c.model, COUNT(model) FROM car c " +
                        "LEFT JOIN reservation_detail rd ON c.license_plate_no = rd.license_plate_no " +
                        "GROUP BY model ORDER BY COUNT(model) DESC LIMIT 3;"
        );
        ArrayList<String> arrayList = new ArrayList();
        while (rst.next()) {
            arrayList.add(rst.getString(1));
        }
        return arrayList;
    }

    @Override
    public int getYearTotalSaleAmount() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute(
                "SELECT " +
                        "MONTH(r.pick_up_date) AS month, " +
                        "COALESCE(SUM(p.amount), 0) + COALESCE(SUM(CASE WHEN c.amount_to_pay = 0 THEN c.total_amount ELSE 0 END), 0) AS monthly_income " +
                        "FROM reservation r " +
                        "LEFT JOIN payment p ON r.reservation_id = p.reservation_id " +
                        "LEFT JOIN credit c ON r.reservation_id = c.reservation_id " +
                        "GROUP BY MONTH(r.pick_up_date) " +
                        "ORDER BY MONTH(r.pick_up_date)"
        );

        int totalIncome = 0;
        while (rst.next()) {
            totalIncome += rst.getInt("monthly_income");
        }
        return totalIncome;
    }

    @Override
    public int getMonthlySales() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute(
                "SELECT COUNT(reservation_id) AS sales_count FROM reservation " +
                        "WHERE MONTH(pick_up_date) = MONTH(CURRENT_DATE()) AND YEAR(pick_up_date) = YEAR(CURRENT_DATE())"
        );
        if (rst.next()) {
            return rst.getInt(1);
        }
        return 0;
    }

    @Override
    public int getCreditNotPaidCount() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute(
                "SELECT COUNT(credit_id) FROM credit WHERE amount_to_pay > 0"
        );
        if (rst.next()) {
            return rst.getInt(1);
        }
        return 0;
    }

}
