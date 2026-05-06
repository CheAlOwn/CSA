package com.chealown.csa.DataBase.Repositories;

import com.chealown.csa.DataBase.DBConnector;
import com.chealown.csa.DataBase.Models.Children;
import com.chealown.csa.DataBase.Models.OrganizationType;
import com.chealown.csa.Entities.StaticObjects;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrganizationTypeRepository {
    private static final String QUERY = """
            SELECT id, type_name
            FROM organization_type;
            """;


    public static boolean save(OrganizationType type) {
        if (StaticObjects.getSelectedObject() == null) {
            System.out.println("insert");
            return insert(type);
        } else {
            System.out.println("update");
            return update(type);
        }
    }

    private static boolean insert(OrganizationType type) {
        String sql = """
                INSERT INTO organization_type
                (type_name)
                VALUES(?);
                """;

        Object[] params = {
                type.getTypeName()
        };
        int result = DBConnector.update(sql, params);
        return result > 0;
    }


    private static boolean update(OrganizationType type) {
        String sql = """
                   UPDATE organization_type
                   SET type_name=?
                   WHERE id=?;
                """;

        Object[] params = {
                type.getTypeName(),
                type.getId()
        };

        return DBConnector.update(sql, params) > 0;
    }

    public static void delete(OrganizationType type) {
        String sql = """
                DELETE FROM organization_type
                WHERE id=?;
                """;

        Object[] params = {
                type.getId()
        };

        DBConnector.update(sql, params);
    }


    public static List<OrganizationType> getAllData() throws SQLException {
        ArrayList<OrganizationType> data = new ArrayList<>();
        ResultSet rs = DBConnector.query(QUERY);
        while (rs.next()) {
            data.add(new OrganizationType(
                    rs.getInt("id"),
                    rs.getString("type_name")
            ));
        }

        return data;
    }
}
