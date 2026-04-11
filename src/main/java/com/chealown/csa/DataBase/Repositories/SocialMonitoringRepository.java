package com.chealown.csa.DataBase.Repositories;

import com.chealown.csa.DataBase.DBConnector;
import com.chealown.csa.DataBase.Models.SocialMonitoring;
import com.chealown.csa.DataBase.Models.SocialPassport;
import com.chealown.csa.Entities.SecurityUtil;
import com.chealown.csa.Entities.StaticObjects;

import javax.crypto.SecretKey;

public class SocialMonitoringRepository {
    private static final SecretKey ENCRYPTION_KEY = SecurityUtil.loadKeyFromEnv("APP_ENCRYPTION_KEY");
    private static final String[] ENCRYPTED_FIELDS = {
            "Старое значение", "Новое значение", "Причина изменения"
    };

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

    public static String[] getEncryptedFields() {
        return ENCRYPTED_FIELDS;
    }
}
