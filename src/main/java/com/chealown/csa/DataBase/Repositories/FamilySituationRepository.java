package com.chealown.csa.DataBase.Repositories;

import com.chealown.csa.DataBase.DBConnector;
import com.chealown.csa.DataBase.Models.Children;
import com.chealown.csa.DataBase.Models.FamilySituation;
import com.chealown.csa.Entities.SecurityUtil;
import com.chealown.csa.Entities.StaticObjects;

import javax.crypto.SecretKey;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FamilySituationRepository {
    private static final SecretKey ENCRYPTION_KEY = SecurityUtil.loadKeyFromEnv("APP_ENCRYPTION_KEY");

    private static final String QUERY = """
            SELECT id, situation_name
            FROM family_situation;
            """;


    public static boolean save(FamilySituation situation) {
        if (StaticObjects.getSelectedObject() == null) {
            System.out.println("insert");
            return insert(situation);
        } else {
            System.out.println("update");
            return update(situation);
        }
    }

    private static boolean insert(FamilySituation situation) {
        String sql = """
                INSERT INTO family_situation
                (situation_name)
                VALUES(?);
                """;

        Object[] params = {
                SecurityUtil.encryptSafe(situation.getSituationName(), ENCRYPTION_KEY),
        };
        int result = DBConnector.update(sql, params);
        return result > 0;
    }


    private static boolean update(FamilySituation situation) {
        String sql = """
                   UPDATE family_situation
                   SET situation_name=?
                   WHERE id=?;
                """;

        Object[] params = {
                SecurityUtil.encryptSafe(situation.getSituationName(), ENCRYPTION_KEY),
                situation.getId()
        };

        return DBConnector.update(sql, params) > 0;
    }

    public static void delete(FamilySituation familySituation) {
        String sql = """
                DELETE FROM family_situation
                WHERE id=?;
                """;

        Object[] params = {
                familySituation.getId()
        };

        DBConnector.update(sql, params);
    }


    public static List<FamilySituation> getAllData() throws SQLException {
        ArrayList<FamilySituation> data = new ArrayList<>();
        ResultSet rs = DBConnector.query(QUERY);
        while (rs.next()) {
            data.add(new FamilySituation(
                    rs.getInt("id"),
                    SecurityUtil.decryptSafe(rs.getString("situation_name"), ENCRYPTION_KEY)
            ));
        }

        return data;
    }
}
