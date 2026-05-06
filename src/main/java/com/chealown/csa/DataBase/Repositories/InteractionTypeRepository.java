package com.chealown.csa.DataBase.Repositories;

import com.chealown.csa.DataBase.DBConnector;
import com.chealown.csa.DataBase.Models.Children;
import com.chealown.csa.DataBase.Models.InteractionType;
import com.chealown.csa.Entities.StaticObjects;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InteractionTypeRepository {
    private static final String QUERY = """
            SELECT id, interaction_name
            FROM interaction_type;
            """;


    public static boolean save(InteractionType type) {
        if (StaticObjects.getSelectedObject() == null) {
            System.out.println("insert");
            return insert(type);
        } else {
            System.out.println("update");
            return update(type);
        }
    }

    private static boolean insert(InteractionType type) {
        String sql = """
                INSERT INTO interaction_type
                (interaction_name)
                VALUES(?);
                """;

        Object[] params = {
                type.getInteractionName()
        };
        int result = DBConnector.update(sql, params);
        return result > 0;
    }


    private static boolean update(InteractionType type) {
        String sql = """
                   UPDATE interaction_type
                   SET interaction_name=?
                   WHERE id=?;
                """;

        Object[] params = {
                type.getInteractionName(),
                type.getId()
        };

        return DBConnector.update(sql, params) > 0;
    }

    public static void delete(InteractionType type) {
        String sql = """
                DELETE FROM interaction_type
                WHERE id=?;
                """;

        Object[] params = {
                type.getId()
        };

        DBConnector.update(sql, params);
    }


    public static List<InteractionType> getAllData() throws SQLException {
        ArrayList<InteractionType> data = new ArrayList<>();
        ResultSet rs = DBConnector.query(QUERY);
        while (rs.next()) {
            data.add(new InteractionType(
                    rs.getInt("id"),
                    rs.getString("interaction_name")
            ));
        }

        return data;
    }
}
