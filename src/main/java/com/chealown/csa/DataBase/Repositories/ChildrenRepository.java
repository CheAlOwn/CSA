package com.chealown.csa.DataBase.Repositories;

import com.chealown.csa.DataBase.DBConnector;
import com.chealown.csa.DataBase.Models.Children;
import com.chealown.csa.Entities.SecurityUtil;
import com.chealown.csa.Entities.StaticObjects;

import javax.crypto.SecretKey;

public class ChildrenRepository {
    private static final SecretKey ENCRYPTION_KEY = SecurityUtil.loadKeyFromEnv("APP_ENCRYPTION_KEY");
    private static final String[] DISPLAY_COLUMNS = {
            "ID", "Фамилия", "Имя", "Отчество",
            "Дата рождения", "Пол", "СНИЛС", "Серия паспорта",
            "Номер паспорта", "Дата регистрации", "Учебная группа", "Статус"
    };
    private static final String QUERY = """
                        SELECT c.id, c.second_name, c.first_name, c.patronymic, c.birthdate,
                               g.gender_name, c.snils, c.passport_series, c.passport_number,
                               c.registration_date, eg.group_name, s.status_name
                        FROM children c
                        INNER JOIN gender g ON c.gender = g.id
                        INNER JOIN education_group eg ON c.id_education_group = eg.id
                        INNER JOIN status s ON c.status = s.id
                        WHERE c.archive = false
                        """;

    public static boolean save(Children child) {
        if (StaticObjects.getChildren() == null) {
            System.out.println("insert");
            return insert(child);
        } else {
            System.out.println("update");
            return update(child);
        }
    }

    private static boolean insert(Children child) {
        String sql = """
                INSERT INTO children (
                first_name, second_name, patronymic, gender, birthdate,
                passport_series, passport_number, registration_date, snils,
                id_education_group, status
                ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;

        Object[] params = {
                SecurityUtil.encryptSafe(child.getFirstName(), ENCRYPTION_KEY),
                SecurityUtil.encryptSafe(child.getSecondName(), ENCRYPTION_KEY),
                SecurityUtil.encryptSafe(child.getPatronymic(), ENCRYPTION_KEY),
                Integer.parseInt(child.getGender()),
                SecurityUtil.encryptSafe(child.getBirthdate(), ENCRYPTION_KEY),
                SecurityUtil.encryptSafe(child.getPassportSer(), ENCRYPTION_KEY),
                SecurityUtil.encryptSafe(child.getPassportNum(), ENCRYPTION_KEY),
                SecurityUtil.encryptSafe(child.getRegistrationDate(), ENCRYPTION_KEY),
                SecurityUtil.encryptSafe(child.getSnils(), ENCRYPTION_KEY),
                Integer.parseInt(child.getEducationGroup()),
                Integer.parseInt(child.getStatus()),

        };
        int result = DBConnector.update(sql, params);
        System.out.println("result: " + result);
        return result > 0;
    }


    private static boolean update(Children child) {
        String sql = """
                UPDATE children SET
                    first_name = ?, second_name = ?, patronymic = ?, gender = ?, birthdate = ?,
                    passport_series = ?, passport_number = ?, registration_date = ?, snils = ?,
                    id_education_group = ?, status = ?
                WHERE id = ?
                """;

        Object[] params = {
                SecurityUtil.encryptSafe(child.getFirstName(), ENCRYPTION_KEY),
                SecurityUtil.encryptSafe(child.getSecondName(), ENCRYPTION_KEY),
                SecurityUtil.encryptSafe(child.getPatronymic(), ENCRYPTION_KEY),
                Integer.parseInt(child.getGender()),
                SecurityUtil.encryptSafe(child.getBirthdate(), ENCRYPTION_KEY),
                SecurityUtil.encryptSafe(child.getPassportSer(), ENCRYPTION_KEY),
                SecurityUtil.encryptSafe(child.getPassportNum(), ENCRYPTION_KEY),
                SecurityUtil.encryptSafe(child.getRegistrationDate(), ENCRYPTION_KEY),
                SecurityUtil.encryptSafe(child.getSnils(), ENCRYPTION_KEY),
                Integer.parseInt(child.getEducationGroup()),
                Integer.parseInt(child.getStatus()),
                child.getId()
        };

        return DBConnector.update(sql, params) > 0;
    }

    public static void archive(Children child) {
        String sql = """
                UPDATE children
                SET archive=true
                WHERE id=?;
                """;

        Object[] params = {
                child.getId()
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
