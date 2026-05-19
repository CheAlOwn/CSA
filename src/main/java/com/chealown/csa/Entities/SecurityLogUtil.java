package com.chealown.csa.Entities;

import com.chealown.csa.DataBase.DBConnector;

import javax.crypto.SecretKey;

public class SecurityLogUtil {

    private static final SecretKey ENCRYPTION_KEY = SecurityUtil.loadKeyFromEnv("APP_ENCRYPTION_KEY");

    public enum EventType { LOGIN, LOGOUT, SYSTEM_STARTUP, SYSTEM_SHUTDOWN }

    public enum EventResult { SUCCESS, FAILURE, UNAUTHORIZED }

    public static void log(EventType type, EventResult result, String subjectId, String details) {
        String sql = "INSERT INTO security_access_log (event_type, event_result, subject_identifier, details) VALUES (?, ?, ?, ?)";

        DBConnector.update(sql, type.name(), result.name(), subjectId != null ? subjectId : "UNKNOWN", details);
    }

    public static String getCurrentSubject() {
        if (StaticObjects.getCurrentUser() != null) {
            var u = StaticObjects.getCurrentUser();
            return u.getLogin() + " (" + SecurityUtil.decryptSafe(u.getSecondName(), ENCRYPTION_KEY) + " " + SecurityUtil.decryptSafe(u.getFirstName(), ENCRYPTION_KEY) + ")";
        }
        return "SYSTEM";
    }
}