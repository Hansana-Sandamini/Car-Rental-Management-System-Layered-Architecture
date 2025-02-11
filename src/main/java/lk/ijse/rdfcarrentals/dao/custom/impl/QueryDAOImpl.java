package lk.ijse.rdfcarrentals.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.rdfcarrentals.dao.SQLUtil;
import lk.ijse.rdfcarrentals.dao.custom.QueryDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
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

}
