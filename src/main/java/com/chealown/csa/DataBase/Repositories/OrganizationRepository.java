package com.chealown.csa.DataBase.Repositories;

import com.chealown.csa.DataBase.DBConnector;
import com.chealown.csa.DataBase.Models.Children;
import com.chealown.csa.DataBase.Models.HousingRights;
import com.chealown.csa.DataBase.Models.Organization;
import com.chealown.csa.Entities.SecurityUtil;
import com.chealown.csa.Entities.StaticObjects;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrganizationRepository {
    private static final String QUERY = """
            SELECT o.id, organization_name, city, street, build, phone, email, type_name
            FROM organization o
            INNER JOIN organization_type t on o."type" = t.id
            """;


    public static boolean save(Organization organization) {
        if (StaticObjects.getSelectedObject() == null) {
            return insert(organization);
        } else {
            return update(organization);
        }
    }

    private static boolean insert(Organization organization) {
        String sql = """
                INSERT INTO organization
                (organization_name, city, street, build, phone, email, "type")
                VALUES(?, ?,?, ?, ?, ?, ?);
                """;

        Object[] params = {
                organization.getOrganizationName(),
                organization.getCity(),
                organization.getStreet(),
                organization.getBuild(),
                organization.getPhone(),
                organization.getEmail(),
                organization.getType()
        };
        int result = DBConnector.update(sql, params);
        return result > 0;
    }


    private static boolean update(Organization organization) {
        String sql = """
                UPDATE organization
                SET organization_name=?, city=?, street=?, build=?, phone=?, email=?, "type"=?
                WHERE id=?;
                """;

        Object[] params = {
                organization.getOrganizationName(),
                organization.getCity(),
                organization.getStreet(),
                organization.getBuild(),
                organization.getPhone(),
                organization.getEmail(),
                organization.getType(),
                organization.getId()
        };

        return DBConnector.update(sql, params) > 0;
    }

    public static void delete(Organization organization) {
        String sql = """
                DELETE FROM organization
                WHERE id=?;
                """;

        Object[] params = {
                organization.getId()
        };

        DBConnector.update(sql, params);
    }


    public static List<Organization> getAllData() throws SQLException {
        ArrayList<Organization> data = new ArrayList<>();
        ResultSet rs = DBConnector.query(QUERY);
        while (rs.next()) {
            data.add(new Organization(
                    rs.getInt("id"),
                    rs.getString("organization_name"),
                    rs.getString("city"),
                    rs.getString("street"),
                    rs.getString("build"),
                    rs.getString("phone"),
                    rs.getString("email"),
                    rs.getString("type_name")
            ));
        }

        return data;
    }
}
