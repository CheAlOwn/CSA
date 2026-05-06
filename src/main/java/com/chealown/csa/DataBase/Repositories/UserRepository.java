package com.chealown.csa.DataBase.Repositories;

import com.chealown.csa.DataBase.DBConnector;
import com.chealown.csa.DataBase.Models.Children;
import com.chealown.csa.DataBase.Models.Post;
import com.chealown.csa.DataBase.Models.User;
import com.chealown.csa.Entities.SecurityUtil;
import com.chealown.csa.Entities.StaticObjects;

import javax.crypto.SecretKey;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private static final SecretKey ENCRYPTION_KEY = SecurityUtil.loadKeyFromEnv("APP_ENCRYPTION_KEY");

    private static final String QUERY = """
            SELECT u.id, login, password_hash, id_employee, second_name, first_name, patronymic, post_name
            FROM "user" u
            inner join employee e on u.id_employee = e.id
            inner join post p on e.post = p.id
            """;


    /*
    *
    * Сделать распределение по ролям
    * Сделать контрольную проверку работоспособности
    *
    * */



    public static boolean save(User user) {
        if (StaticObjects.getSelectedObject() == null) {
            System.out.println("insert");
            return insert(user);
        } else {
            System.out.println("update");
            return update(user);
        }
    }

    private static boolean insert(User user) {
        String sql = """
                INSERT INTO "user"
                (login, password_hash, id_employee)
                VALUES(?, ?, ?);
                """;

        Object[] params = {
                user.getLogin(),
                user.getPassword(),
                user.getEmployeeId()
        };
        int result = DBConnector.update(sql, params);
        return result > 0;
    }


    private static boolean update(User user) {
        String sql = """
                   UPDATE "user"
                   SET login=?, password_hash=?, id_employee=?
                   WHERE id=?;
                """;

        Object[] params = {
                user.getLogin(),
                user.getPassword(),
                user.getEmployeeId(),
                user.getId()
        };

        return DBConnector.update(sql, params) > 0;
    }

    public static void delete(User user) {
        String sql = """
                DELETE FROM user
                WHERE id=?;
                """;

        Object[] params = {
                user.getId()
        };

        DBConnector.update(sql, params);
    }


    public static List<User> getAllData() throws SQLException {
        ArrayList<User> data = new ArrayList<>();
        ResultSet rs = DBConnector.query(QUERY);
        while (rs.next()) {
            data.add(new User(
                    rs.getInt("id"),
                    rs.getString("login"),
                    rs.getString("password_hash"),
                    SecurityUtil.decryptSafe(rs.getString("second_name"), ENCRYPTION_KEY),
                    SecurityUtil.decryptSafe(rs.getString("first_name"), ENCRYPTION_KEY),
                    SecurityUtil.decryptSafe(rs.getString("patronymic"), ENCRYPTION_KEY),
                    rs.getString("post_name"),
                    rs.getInt("id_employee")
            ));
        }

        return data;
    }
}
