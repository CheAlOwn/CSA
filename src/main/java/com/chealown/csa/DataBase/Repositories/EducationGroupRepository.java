package com.chealown.csa.DataBase.Repositories;

import com.chealown.csa.DataBase.DBConnector;
import com.chealown.csa.DataBase.Models.Children;
import com.chealown.csa.DataBase.Models.Document;
import com.chealown.csa.DataBase.Models.EducationGroup;
import com.chealown.csa.DataBase.Models.SocialMonitoring;
import com.chealown.csa.Entities.SecurityUtil;
import com.chealown.csa.Entities.StaticObjects;

import javax.crypto.SecretKey;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EducationGroupRepository {
    private static final SecretKey ENCRYPTION_KEY = SecurityUtil.loadKeyFromEnv("APP_ENCRYPTION_KEY");

    private static final String QUERY = """
            SELECT eg.id, group_name, id_tutor, second_name, first_name, patronymic
            FROM education_group eg
            INNER JOIN employee e on eg.id_tutor=e.id
            """;


    public static boolean save(EducationGroup educationGroup) {
        if (StaticObjects.getSelectedObject() == null) {
            System.out.println("insert");
            return insert(educationGroup);
        } else {
            System.out.println("update");
            return update(educationGroup);
        }
    }

    private static boolean insert(EducationGroup educationGroup) {
        String sql = """
                INSERT INTO education_group
                (group_name, id_tutor)
                VALUES(?, ?);
                """;

        Object[] params = {
                SecurityUtil.encryptSafe(educationGroup.getGroupName(), ENCRYPTION_KEY),
                educationGroup.getTutorId()
        };
        int result = DBConnector.update(sql, params);
        return result > 0;
    }


    private static boolean update(EducationGroup educationGroup) {
        String sql = """
                   UPDATE education_group
                   SET group_name=?, id_tutor=?
                   WHERE id=?;
                """;

        Object[] params = {
                SecurityUtil.encryptSafe(educationGroup.getGroupName(), ENCRYPTION_KEY),
                educationGroup.getTutorId(),
                educationGroup.getId()
        };

        return DBConnector.update(sql, params) > 0;
    }

    public static void delete(EducationGroup educationGroup) {
        String sql = """
                DELETE FROM education_group
                WHERE id=?;
                """;

        Object[] params = {
                educationGroup.getId()
        };

        DBConnector.update(sql, params);
    }


    public static ArrayList<EducationGroup> getAllData() throws SQLException {
        ArrayList<EducationGroup> data = new ArrayList<>();
        ResultSet rs = DBConnector.query(QUERY);
        while (rs.next()) {
            data.add(new EducationGroup(
                    rs.getInt("id"),
                    SecurityUtil.decryptSafe(rs.getString("group_name"), ENCRYPTION_KEY),
                    rs.getInt("id_tutor"),
                    SecurityUtil.decryptSafe(rs.getString("second_name"), ENCRYPTION_KEY),
                    SecurityUtil.decryptSafe(rs.getString("first_name"), ENCRYPTION_KEY),
                    SecurityUtil.decryptSafe(rs.getString("patronymic"), ENCRYPTION_KEY)
            ));
        }

        return data;
    }
}
