package com.chealown.csa.DataBase.Repositories;

import com.chealown.csa.DataBase.DBConnector;
import com.chealown.csa.DataBase.Models.Children;
import com.chealown.csa.DataBase.Models.SocialPassport;
import com.chealown.csa.Entities.SecurityUtil;
import com.chealown.csa.Entities.StaticObjects;

import javax.crypto.SecretKey;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SocialPassportRepository {
    private static final SecretKey ENCRYPTION_KEY = SecurityUtil.loadKeyFromEnv("APP_ENCRYPTION_KEY");
    private static final String[] ENCRYPTED_FIELDS = {
            "Инвалидность"
    };

    public static boolean save(SocialPassport socialPassport) {
        if (StaticObjects.getSocialPassport() == null) {
            System.out.println("insert");
            return insert(socialPassport);
        } else {
            System.out.println("update");
            return update(socialPassport);
        }
    }

    private static boolean insert(SocialPassport socialPassport) {
        String sql = """
                INSERT INTO social_passport (
                id_children, id_education, id_health_group, 
                id_family_situation, having_a_disability, date_create
                )
                VALUES(?, ?, ?, ?, ?, ?);
                """;

        Object[] params = {
                socialPassport.getIdChildren(),
                socialPassport.getEducation(),
                socialPassport.getHealthGroup(),
                socialPassport.getFamilySituation(),
                SecurityUtil.encryptSafe(socialPassport.getHavingADisability(), ENCRYPTION_KEY),
                socialPassport.getDateCreate()
        };
        int result = DBConnector.update(sql, params) ;
        return result > 0;
    }



    private static boolean update(SocialPassport socialPassport) {
        String sql = """
                UPDATE social_passport
                SET id_children = ?, id_education = ?, id_health_group = ?, 
                id_family_situation = ?, having_a_disability = ?, date_create = ?
                WHERE id_passport = ?;
            """;

        Object[] params = {
                socialPassport.getIdChildren(),
                socialPassport.getEducation(),
                socialPassport.getHealthGroup(),
                socialPassport.getFamilySituation(),
                SecurityUtil.encryptSafe(socialPassport.getHavingADisability(), ENCRYPTION_KEY),
                socialPassport.getDateCreate(),
                socialPassport.getId()
        };

        return DBConnector.update(sql, params) > 0;
    }

//    public static Children loadById(int id) throws SQLException {
//        String sql = """
//                SELECT c.id, c.second_name, c.first_name, c.patronymic, c.birthdate,
//                               g.gender_name, c.snils, c.passport_series, c.passport_number,
//                               c.registration_date, eg.group_name, s.status_name
//                        FROM children c
//                        INNER JOIN gender g ON c.gender = g.id
//                        INNER JOIN education_group eg ON c.id_education_group = eg.id
//                        INNER JOIN status s ON c.status = s.id
//                WHERE id = ?
//                """;
//
//        ResultSet rs = DBConnector.query(sql, id);
//
//        if (rs.next()) {
//            Children child = new Children();
//
//            child.setFirstName(SecurityUtil.decryptSafe(rs.getString("first_name"), ENCRYPTION_KEY));
//            child.setSecondName(SecurityUtil.decryptSafe(rs.getString("second_name"), ENCRYPTION_KEY));
//            child.setPatronymic(SecurityUtil.decryptSafe(rs.getString("patronymic"), ENCRYPTION_KEY));
//            child.setGender(rs.getString("gender_name"));
//            child.setBirthdate(SecurityUtil.decryptSafe(rs.getString("birthdate"), ENCRYPTION_KEY));
//            child.setPassportSer(SecurityUtil.decryptSafe(rs.getString("passport_series"), ENCRYPTION_KEY));
//            child.setPassportNum(SecurityUtil.decryptSafe(rs.getString("passport_number"), ENCRYPTION_KEY));
//            child.setRegistrationDate(SecurityUtil.decryptSafe(rs.getString("registration_date"), ENCRYPTION_KEY));
//            child.setSnils(SecurityUtil.decryptSafe(rs.getString("snils"), ENCRYPTION_KEY));
//            child.setEducationGroup(rs.getString("group_name"));
//            child.setStatus(rs.getString("status_name"));
//            child.setId(id);
//
//            return  child;
//        }
//        return null;
//    }
//
//    public static ArrayList<Children> loadAll() throws SQLException {
//        String sql = """
//                SELECT c.id, c.second_name, c.first_name, c.patronymic, c.birthdate,
//                               g.gender_name, c.snils, c.passport_series, c.passport_number,
//                               c.registration_date, eg.group_name, s.status_name
//                        FROM children c
//                        INNER JOIN gender g ON c.gender = g.id
//                        INNER JOIN education_group eg ON c.id_education_group = eg.id
//                        INNER JOIN status s ON c.status = s.id
//                """;
//
//        ResultSet rs = DBConnector.query(sql);
//
//        ArrayList<Children> childrenList = new ArrayList<>();
//
//        while (rs.next()) {
//            Children child = new Children();
//
//            child.setFirstName(SecurityUtil.decryptSafe(rs.getString("first_name"), ENCRYPTION_KEY));
//            child.setSecondName(SecurityUtil.decryptSafe(rs.getString("second_name"), ENCRYPTION_KEY));
//            child.setPatronymic(SecurityUtil.decryptSafe(rs.getString("patronymic"), ENCRYPTION_KEY));
//            child.setGender(rs.getString("gender_name"));
//            child.setBirthdate(SecurityUtil.decryptSafe(rs.getString("birthdate"), ENCRYPTION_KEY));
//            child.setPassportSer(SecurityUtil.decryptSafe(rs.getString("passport_series"), ENCRYPTION_KEY));
//            child.setPassportNum(SecurityUtil.decryptSafe(rs.getString("passport_number"), ENCRYPTION_KEY));
//            child.setRegistrationDate(SecurityUtil.decryptSafe(rs.getString("registration_date"), ENCRYPTION_KEY));
//            child.setSnils(SecurityUtil.decryptSafe(rs.getString("snils"), ENCRYPTION_KEY));
//            child.setEducationGroup(rs.getString("group_name"));
//            child.setStatus(rs.getString("status_name"));
//            child.setId(rs.getInt("id"));
//            childrenList.add(child);
//        }
//
//        return childrenList;
//    }


    public static String[] getEncryptedFields() {
        return ENCRYPTED_FIELDS;
    }
}
