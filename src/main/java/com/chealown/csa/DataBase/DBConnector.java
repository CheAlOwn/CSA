package com.chealown.csa.DataBase;

import com.chealown.csa.Entities.Service;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnector {
    private static Connection conn;
    static Service service = new Service();

    public static void connect() throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");

        try {
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/CSA", "postgres", "0");
            System.out.println("есть");
        } catch (SQLException e) {
            service.showAlert(Alert.AlertType.ERROR, "Подключение к базе данных", "Не удалось подключиться к базе данных");
        }
    }

    public static Connection getConn() {
        return conn;
    }

    public static ResultSet executeQuery(String sql) throws SQLException {
        if (conn.isClosed() || conn == null) {
            service.showAlert(Alert.AlertType.ERROR, "Подключение к базе данных", "Нет соединения с базой данных");
            return null;
        }
        return conn.createStatement().executeQuery(sql);

    }

}
