package com.chealown.csa.DataBase.Repositories;

import com.chealown.csa.DataBase.DBConnector;
import com.chealown.csa.DataBase.Models.Children;
import com.chealown.csa.DataBase.Models.FamilySituation;
import com.chealown.csa.DataBase.Models.HealthGroup;
import com.chealown.csa.Entities.SecurityUtil;
import com.chealown.csa.Entities.StaticObjects;

import javax.crypto.SecretKey;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HealthGroupRepository {
    private static final SecretKey ENCRYPTION_KEY = SecurityUtil.loadKeyFromEnv("APP_ENCRYPTION_KEY");

    private static final String QUERY = """
            SELECT id, group_name
            FROM health_group;
            """;


    public static boolean save(HealthGroup group) {
        if (StaticObjects.getSelectedObject() == null) {
            System.out.println("insert");
            return insert(group);
        } else {
            System.out.println("update");
            return update(group);
        }
    }

    private static boolean insert(HealthGroup group) {
        String sql = """
                INSERT INTO health_group
                (group_name)
                VALUES(?);
                """;

        Object[] params = {
                SecurityUtil.encryptSafe(group.getGroupName(), ENCRYPTION_KEY)
        };
        int result = DBConnector.update(sql, params);
        return result > 0;
    }


    private static boolean update(HealthGroup group) {
        String sql = """
                   UPDATE health_group
                   SET group_name=?
                   WHERE id=?;
                """;

        Object[] params = {
                SecurityUtil.encryptSafe(group.getGroupName(), ENCRYPTION_KEY),
                group.getId()
        };

        return DBConnector.update(sql, params) > 0;
    }

    public static void delete(HealthGroup healthGroup) {
        String sql = """
                DELETE FROM health_group
                WHERE id=?;
                """;

        Object[] params = {
                healthGroup.getId()
        };

        DBConnector.update(sql, params);
    }


    public static List<HealthGroup> getAllData() throws SQLException {
        ArrayList<HealthGroup> data = new ArrayList<>();
        ResultSet rs = DBConnector.query(QUERY);
        while (rs.next()) {
            data.add(new HealthGroup(
                    rs.getInt("id"),
                    SecurityUtil.decryptSafe(rs.getString("group_name"), ENCRYPTION_KEY)
            ));
        }

        return data;
    }
}
