package com.chealown.csa.DataBase.Repositories;

import com.chealown.csa.DataBase.DBConnector;
import com.chealown.csa.DataBase.Models.Children;
import com.chealown.csa.DataBase.Models.SocialMonitoring;
import com.chealown.csa.DataBase.Models.SocialPassport;
import com.chealown.csa.Entities.SecurityUtil;
import com.chealown.csa.Entities.StaticObjects;

import javax.crypto.SecretKey;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SocialPassportRepository {
    private static final SecretKey ENCRYPTION_KEY = SecurityUtil.loadKeyFromEnv("APP_ENCRYPTION_KEY");

    private static final String QUERY = """
            SELECT s.id_passport, c.second_name, c.first_name, c.patronymic, c.id as id_child,
                   el.education_name, hg.group_name, t.situation_name,
                   s.having_a_disability, s.date_create
            FROM social_passport s
            INNER JOIN children c ON s.id_children = c.id
            INNER JOIN education_level el ON s.id_education = el.id
            INNER JOIN health_group hg ON s.id_health_group = hg.id
            INNER JOIN family_situation t ON s.id_family_situation = t.id
            WHERE s.archive = ? or s.archive = false
            """;


    public static boolean save(SocialPassport socialPassport) {
        if (StaticObjects.getSelectedObject() == null) {
            System.out.println("insert");
            return insert(socialPassport);
        } else {
            System.out.println("update");
            return update(socialPassport);
        }
    }

    public static void deleteRecord(SocialPassport socialPassport, boolean isAdmin) {
        if (isAdmin) {
            delete(socialPassport);
        } else {
            archive(socialPassport);
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
        int result = DBConnector.update(sql, params);
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

    private static void archive(SocialPassport socialPassport) {
        String sql = """
                UPDATE social_passport
                SET archive=true
                WHERE id_passport=?;
                """;

        Object[] params = {
                socialPassport.getId()
        };

        DBConnector.update(sql, params);
    }

    private static void delete(SocialPassport passport) {
        String sql = """
                DELETE FROM social_passport
                WHERE id_passport=?;
                """;

        Object[] params = {
                passport.getId()
        };

        DBConnector.update(sql, params);
    }


    public static String getQUERY() {
        return QUERY;
    }

    public static List<SocialPassport> getAllData(boolean withArchive) throws SQLException {
        ArrayList<SocialPassport> data = new ArrayList<>();
        ResultSet rs = DBConnector.query(QUERY, withArchive);
        while (rs.next()) {
            data.add(new SocialPassport(
                    rs.getInt("id_passport"),
                    SecurityUtil.decryptSafe(rs.getString("second_name"), ENCRYPTION_KEY),
                    SecurityUtil.decryptSafe(rs.getString("first_name"), ENCRYPTION_KEY),
                    SecurityUtil.decryptSafe(rs.getString("patronymic"), ENCRYPTION_KEY),
                    SecurityUtil.decryptSafe(rs.getString("education_name"), ENCRYPTION_KEY),
                    SecurityUtil.decryptSafe(rs.getString("group_name"), ENCRYPTION_KEY),
                    SecurityUtil.decryptSafe(rs.getString("situation_name"), ENCRYPTION_KEY),
                    SecurityUtil.decryptSafe(rs.getString("having_a_disability"), ENCRYPTION_KEY),
                    rs.getString("date_create"),
                    rs.getInt("id_child")
            ));
        }

        return data;
    }
}
