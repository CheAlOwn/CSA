package com.chealown.csa.DataBase.Repositories;

import com.chealown.csa.DataBase.DBConnector;
import com.chealown.csa.DataBase.Models.Children;
import com.chealown.csa.DataBase.Models.SocialMonitoring;
import com.chealown.csa.Entities.SecurityUtil;
import com.chealown.csa.Entities.StaticObjects;

import javax.crypto.SecretKey;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SocialMonitoringRepository {
    private static final SecretKey ENCRYPTION_KEY = SecurityUtil.loadKeyFromEnv("APP_ENCRYPTION_KEY");

    private static final String QUERY = """
            SELECT s.id, c.second_name, c.first_name, c.patronymic, c.id as id_child,
                   s.date_of_fixation, mt.monitoring_name, s.old_value,
                   s.new_value, s.change_reason, u.id as id_user
            FROM social_monitoring s
            INNER JOIN children c ON s.id_children = c.id
            INNER JOIN monitoring_type mt ON s.id_monitoring_type = mt.id
            inner join "user" u on s.id_user = u.id
            WHERE s.archive = ? or s.archive = false
            """;


    public static boolean save(SocialMonitoring socialMonitoring) {
        if (StaticObjects.getSelectedObject() == null) {
            System.out.println("insert");
            return insert(socialMonitoring);
        } else {
            System.out.println("update");
            return update(socialMonitoring);
        }
    }

    public static void deleteRecord(SocialMonitoring socialMonitoring, boolean isAdmin) {
        if (isAdmin) {
            delete(socialMonitoring);
        } else {
            archive(socialMonitoring);
        }
    }

    private static boolean insert(SocialMonitoring socialMonitoring) {
        String sql = """
                INSERT INTO social_monitoring
                (
                id_children, date_of_fixation, id_monitoring_type,
                 old_value, new_value, change_reason, id_user
                 )
                VALUES(?, ?, ?, ?, ?, ?, ?);
                """;

        Object[] params = {
                socialMonitoring.getIdChildren(),
                socialMonitoring.getDateOfFixation(),
                Integer.parseInt(socialMonitoring.getMonitoringType()),
                SecurityUtil.encryptSafe(socialMonitoring.getOldValue(), ENCRYPTION_KEY),
                SecurityUtil.encryptSafe(socialMonitoring.getNewValue(), ENCRYPTION_KEY),
                SecurityUtil.encryptSafe(socialMonitoring.getChangeReason(), ENCRYPTION_KEY),
                socialMonitoring.getIdUser()
        };
        int result = DBConnector.update(sql, params);
        return result > 0;
    }


    private static boolean update(SocialMonitoring socialMonitoring) {
        String sql = """
                   UPDATE social_monitoring
                       SET id_children = ?, date_of_fixation = ?, 
                       id_monitoring_type = ?, old_value = ?, new_value = ?, 
                       change_reason = ?, id_user = ?
                       WHERE id = ?;
                """;

        Object[] params = {
                socialMonitoring.getIdChildren(),
                socialMonitoring.getDateOfFixation(),
                Integer.parseInt(socialMonitoring.getMonitoringType()),
                SecurityUtil.encryptSafe(socialMonitoring.getOldValue(), ENCRYPTION_KEY),
                SecurityUtil.encryptSafe(socialMonitoring.getNewValue(), ENCRYPTION_KEY),
                SecurityUtil.encryptSafe(socialMonitoring.getChangeReason(), ENCRYPTION_KEY),
                socialMonitoring.getIdUser(),
                socialMonitoring.getId()
        };

        return DBConnector.update(sql, params) > 0;
    }

    private static void archive(SocialMonitoring socialMonitoring) {
        String sql = """
                UPDATE social_monitoring
                SET archive=true
                WHERE id=?;
                """;

        Object[] params = {
                socialMonitoring.getId()
        };

        DBConnector.update(sql, params);
    }

    private static void delete(SocialMonitoring monitoring) {
        String sql = """
                DELETE FROM social_monitoring
                WHERE id=?;
                """;

        Object[] params = {
                monitoring.getId()
        };

        DBConnector.update(sql, params);
    }

    public static String getQUERY() {
        return QUERY;
    }

    public static List<SocialMonitoring> getAllData(boolean withArchive) throws SQLException {
        ArrayList<SocialMonitoring> data = new ArrayList<>();
        ResultSet rs = DBConnector.query(QUERY, withArchive);
        while (rs.next()) {
            data.add(new SocialMonitoring(
                    rs.getInt("id"),
                    SecurityUtil.decryptSafe(rs.getString("second_name"), ENCRYPTION_KEY),
                    SecurityUtil.decryptSafe(rs.getString("first_name"), ENCRYPTION_KEY),
                    SecurityUtil.decryptSafe(rs.getString("patronymic"), ENCRYPTION_KEY),
                    rs.getString("date_of_fixation"),
                    rs.getString("monitoring_name"),
                    SecurityUtil.decryptSafe(rs.getString("old_value"), ENCRYPTION_KEY),
                    SecurityUtil.decryptSafe(rs.getString("new_value"), ENCRYPTION_KEY),
                    SecurityUtil.decryptSafe(rs.getString("change_reason"), ENCRYPTION_KEY),
                    rs.getInt("id_user")
            ));
        }

        return data;
    }
}
