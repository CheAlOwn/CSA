package com.chealown.csa.DataBase.Repositories;

import com.chealown.csa.DataBase.DBConnector;
import com.chealown.csa.DataBase.Models.Children;
import com.chealown.csa.DataBase.Models.EducationGroup;
import com.chealown.csa.DataBase.Models.Employee;
import com.chealown.csa.Entities.SecurityUtil;
import com.chealown.csa.Entities.StaticObjects;

import javax.crypto.SecretKey;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository {
    private static final SecretKey ENCRYPTION_KEY = SecurityUtil.loadKeyFromEnv("APP_ENCRYPTION_KEY");

    private static final String QUERY = """
            SELECT e.id, second_name, first_name, patronymic, email, phone, post_name
            FROM employee e
            INNER JOIN post p on e.post = p.id 
            """;

    public static boolean save(Employee employee) {
        if (StaticObjects.getSelectedObject() == null) {
            System.out.println("insert");
            return insert(employee);
        } else {
            System.out.println("update");
            return update(employee);
        }
    }

    private static boolean insert(Employee employee) {
        String sql = """
                INSERT INTO employee
                (second_name, first_name, patronymic, email, phone, post)
                VALUES(?, ?, ?, ?, ?, ?);
                """;

        Object[] params = {
                SecurityUtil.encryptSafe(employee.getFirstName(), ENCRYPTION_KEY),
                SecurityUtil.encryptSafe(employee.getSecondName(), ENCRYPTION_KEY),
                SecurityUtil.encryptSafe(employee.getPatronymic(), ENCRYPTION_KEY),
                SecurityUtil.encryptSafe(employee.getEmail(), ENCRYPTION_KEY),
                SecurityUtil.encryptSafe(employee.getPhone(), ENCRYPTION_KEY),
                Integer.parseInt(employee.getPost()),

        };
        int result = DBConnector.update(sql, params);
        System.out.println("result: " + result);
        return result > 0;
    }


    private static boolean update(Employee employee) {
        String sql = """
                UPDATE employee
                SET second_name=?, first_name=?, patronymic=?, email=?, phone=?, post=?
                WHERE id=?;
                """;

        Object[] params = {
                SecurityUtil.encryptSafe(employee.getFirstName(), ENCRYPTION_KEY),
                SecurityUtil.encryptSafe(employee.getSecondName(), ENCRYPTION_KEY),
                SecurityUtil.encryptSafe(employee.getPatronymic(), ENCRYPTION_KEY),
                SecurityUtil.encryptSafe(employee.getEmail(), ENCRYPTION_KEY),
                SecurityUtil.encryptSafe(employee.getPhone(), ENCRYPTION_KEY),
                Integer.parseInt(employee.getPost()),
                employee.getId()
        };

        return DBConnector.update(sql, params) > 0;
    }

    public static void delete(Employee employee) {
        String sql = """
                DELETE FROM employee
                WHERE id=?;
                """;

        Object[] params = {
                employee.getId()
        };

        DBConnector.update(sql, params);
    }


    public static List<Employee> getAllData() throws SQLException {
        ArrayList<Employee> data = new ArrayList<>();
        ResultSet rs = DBConnector.query(QUERY);
        while (rs.next()) {
            data.add(new Employee(
                    rs.getInt("id"),
                    SecurityUtil.decryptSafe(rs.getString("second_name"), ENCRYPTION_KEY),
                    SecurityUtil.decryptSafe(rs.getString("first_name"), ENCRYPTION_KEY),
                    SecurityUtil.decryptSafe(rs.getString("patronymic"), ENCRYPTION_KEY),
                    SecurityUtil.decryptSafe(rs.getString("email"), ENCRYPTION_KEY),
                    SecurityUtil.decryptSafe(rs.getString("phone"), ENCRYPTION_KEY),
                    rs.getString("post_name")
            ));
        }

        return data;
    }
}
