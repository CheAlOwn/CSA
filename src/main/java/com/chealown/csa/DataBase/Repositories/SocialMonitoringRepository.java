package com.chealown.csa.DataBase.Repositories;

import com.chealown.csa.DataBase.DBConnector;
import com.chealown.csa.DataBase.Models.Children;
import com.chealown.csa.DataBase.Models.SocialMonitoring;
import com.chealown.csa.Entities.SecurityUtil;
import com.chealown.csa.Entities.StaticObjects;

import javax.crypto.SecretKey;

public class SocialMonitoringRepository {
    private static final SecretKey ENCRYPTION_KEY = SecurityUtil.loadKeyFromEnv("APP_ENCRYPTION_KEY");
    private static final String[] DISPLAY_COLUMNS = {
            "ID", "Фамилия ребенка", "Имя ребенка",
            "Отчество ребенка", "ID ребенка", "Дата фиксации", "Тип мониторинга", "Старое значение", "Новое значение",
            "Причина изменения"
    };
    private static final String QUERY = """
                        SELECT s.id, c.second_name AS child_surname, c.first_name AS child_first, c.patronymic AS child_patronymic, c.id,
                               s.date_of_fixation, mt.monitoring_name, s.old_value,
                               s.new_value, s.change_reason,
                               e.second_name AS emp_surname, e.first_name AS emp_first, e.patronymic AS emp_patronymic, u.id
                        FROM social_monitoring s
                        INNER JOIN children c ON s.id_children = c.id
                        INNER JOIN monitoring_type mt ON s.id_monitoring_type = mt.id
                        INNER JOIN "user" u ON s.id_user = u.id
                        INNER JOIN employee e ON u.id_employee = e.id
                        WHERE s.archive = false
                        """;


    public static boolean save(SocialMonitoring socialMonitoring) {
        if (StaticObjects.getSocialMonitoring() == null) {
            System.out.println("insert");
            return insert(socialMonitoring);
        } else {
            System.out.println("update");
            return update(socialMonitoring);
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

    public static void archive(SocialMonitoring socialMonitoring) {
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

    public static String[] getDisplayColumns() {
        return DISPLAY_COLUMNS;
    }

    public static String getQUERY() {
        return QUERY;
    }
}
