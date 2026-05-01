package com.chealown.csa.DataBase;

import com.chealown.csa.Entities.ManageUtil;
import javafx.scene.control.Alert;

import java.sql.*;
import java.util.List;

public class DBConnector {
    private static Connection conn;

    public static void connect() throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");

        try {
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/CSA_DB", "app_user", System.getenv("APP_USER"));
            System.out.println("есть");
        } catch (SQLException e) {
            ManageUtil.showAlert(Alert.AlertType.ERROR, "Подключение к базе данных", "Не удалось подключиться к базе данных");
        }
    }

    public static int update(String sql, Object... params) {
        System.out.println("-");
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            bindParams(ps, params);
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ResultSet query(String sql, Object... params) {
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            bindParams(ps, params);
            return ps.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int updateWithReturningId(String sql, Object... params) {
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            bindParams(ps, params);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1); // возвращаем ID
                } else {
                    throw new RuntimeException("INSERT не вернул ID");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int[] batchInsert(String sql, List<Object[]> batchParams) {
        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            for (Object[] params : batchParams) {
                bindParams(ps, params);
                ps.addBatch();
            }

            return ps.executeBatch(); // Выполняет все запросы одной пачкой
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void bindParams(PreparedStatement ps, Object... params) throws SQLException {
        for (int i = 0; i < params.length; i++) {
            ps.setObject(i + 1, params[i]);
        }
    }
}
