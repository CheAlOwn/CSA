package com.chealown.csa.DataBase.Repositories;

import com.chealown.csa.DataBase.DBConnector;
import com.chealown.csa.DataBase.Models.Children;
import com.chealown.csa.Entities.SecurityUtil;
import com.chealown.csa.Entities.StaticObjects;

import javax.crypto.SecretKey;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ChildrenRepository {
    private static final SecretKey ENCRYPTION_KEY = SecurityUtil.loadKeyFromEnv("APP_ENCRYPTION_KEY");
    private static final String[] ENCRYPTED_FIELDS = {
            "Фамилия", "Имя", "Отчество", "Дата рождения",
            "Серия паспорта", "Номер паспорта", "СНИЛС",
            "Дата регистрации"
    };

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

    public static Children loadById(int id) throws SQLException {
        String sql = """
                SELECT c.id, c.second_name, c.first_name, c.patronymic, c.birthdate,
                               g.gender_name, c.snils, c.passport_series, c.passport_number,
                               c.registration_date, eg.group_name, s.status_name
                        FROM children c
                        INNER JOIN gender g ON c.gender = g.id
                        INNER JOIN education_group eg ON c.id_education_group = eg.id
                        INNER JOIN status s ON c.status = s.id
                WHERE id = ?
                """;

        ResultSet rs = DBConnector.query(sql, id);

        if (rs.next()) {
            Children child = new Children();

            child.setId(id);
            child.setSecondName(SecurityUtil.decryptSafe(rs.getString("second_name"), ENCRYPTION_KEY));
            child.setFirstName(SecurityUtil.decryptSafe(rs.getString("first_name"), ENCRYPTION_KEY));
            child.setPatronymic(SecurityUtil.decryptSafe(rs.getString("patronymic"), ENCRYPTION_KEY));
            child.setBirthdate(SecurityUtil.decryptSafe(rs.getString("birthdate"), ENCRYPTION_KEY));
            child.setGender(rs.getString("gender_name"));
            child.setSnils(SecurityUtil.decryptSafe(rs.getString("snils"), ENCRYPTION_KEY));
            child.setPassportSer(SecurityUtil.decryptSafe(rs.getString("passport_series"), ENCRYPTION_KEY));
            child.setPassportNum(SecurityUtil.decryptSafe(rs.getString("passport_number"), ENCRYPTION_KEY));
            child.setRegistrationDate(SecurityUtil.decryptSafe(rs.getString("registration_date"), ENCRYPTION_KEY));
            child.setEducationGroup(rs.getString("group_name"));
            child.setStatus(rs.getString("status_name"));

            return child;
        }
        return null;
    }

    public static ArrayList<Children> loadAll() throws SQLException {
        String sql = """
                SELECT c.id, c.second_name, c.first_name, c.patronymic, c.birthdate,
                               g.gender_name, c.snils, c.passport_series, c.passport_number,
                               c.registration_date, eg.group_name, s.status_name
                        FROM children c
                        INNER JOIN gender g ON c.gender = g.id
                        INNER JOIN education_group eg ON c.id_education_group = eg.id
                        INNER JOIN status s ON c.status = s.id
                """;

        ResultSet rs = DBConnector.query(sql);

        ArrayList<Children> childrenList = new ArrayList<>();

        while (rs.next()) {
            Children child = new Children();

            child.setId(rs.getInt("id"));
            child.setSecondName(SecurityUtil.decryptSafe(rs.getString("second_name"), ENCRYPTION_KEY));
            child.setFirstName(SecurityUtil.decryptSafe(rs.getString("first_name"), ENCRYPTION_KEY));
            child.setPatronymic(SecurityUtil.decryptSafe(rs.getString("patronymic"), ENCRYPTION_KEY));
            child.setBirthdate(SecurityUtil.decryptSafe(rs.getString("birthdate"), ENCRYPTION_KEY));
            child.setGender(rs.getString("gender_name"));
            child.setSnils(SecurityUtil.decryptSafe(rs.getString("snils"), ENCRYPTION_KEY));
            child.setPassportSer(SecurityUtil.decryptSafe(rs.getString("passport_series"), ENCRYPTION_KEY));
            child.setPassportNum(SecurityUtil.decryptSafe(rs.getString("passport_number"), ENCRYPTION_KEY));
            child.setRegistrationDate(SecurityUtil.decryptSafe(rs.getString("registration_date"), ENCRYPTION_KEY));
            child.setEducationGroup(rs.getString("group_name"));
            child.setStatus(rs.getString("status_name"));
            childrenList.add(child);
        }

        return childrenList;
    }

    public static String[] getEncryptedFields() {
        return ENCRYPTED_FIELDS;
    }
}
