package com.chealown.csa.DataBase.Repositories;

import com.chealown.csa.DataBase.DBConnector;
import com.chealown.csa.DataBase.Models.Children;
import com.chealown.csa.DataBase.Models.Post;
import com.chealown.csa.DataBase.Models.Status;
import com.chealown.csa.Entities.SecurityUtil;
import com.chealown.csa.Entities.StaticObjects;

import javax.crypto.SecretKey;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StatusRepository {
    private static final SecretKey ENCRYPTION_KEY = SecurityUtil.loadKeyFromEnv("APP_ENCRYPTION_KEY");

    private static final String QUERY = """
            SELECT id, status_name
            FROM status;
            """;

    public static boolean save(Status status) {
        if (StaticObjects.getSelectedObject() == null) {
            System.out.println("insert");
            return insert(status);
        } else {
            System.out.println("update");
            return update(status);
        }
    }

    private static boolean insert(Status status) {
        String sql = """
                INSERT INTO status
                (status_name)
                VALUES(?);
                """;

        Object[] params = {
                SecurityUtil.encryptSafe(status.getStatusName(), ENCRYPTION_KEY)
        };
        int result = DBConnector.update(sql, params);
        return result > 0;
    }


    private static boolean update(Status status) {
        String sql = """
                   UPDATE post
                   SET status_name=?
                   WHERE id=?;
                """;

        Object[] params = {
                SecurityUtil.encryptSafe(status.getStatusName(), ENCRYPTION_KEY),
                status.getId()
        };

        return DBConnector.update(sql, params) > 0;
    }

    public static void delete(Status status) {
        String sql = """
                DELETE FROM status
                WHERE id=?;
                """;

        Object[] params = {
                status.getId()
        };

        DBConnector.update(sql, params);
    }


    public static List<Status> getAllData() throws SQLException {
        ArrayList<Status> data = new ArrayList<>();
        ResultSet rs = DBConnector.query(QUERY);
        while (rs.next()) {
            data.add(new Status(
                    rs.getInt("id"),
                    SecurityUtil.decryptSafe(rs.getString("status_name"), ENCRYPTION_KEY)
            ));
        }

        return data;
    }
}
