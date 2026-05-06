package com.chealown.csa.DataBase.Repositories;

import com.chealown.csa.DataBase.DBConnector;
import com.chealown.csa.DataBase.Models.Children;
import com.chealown.csa.DataBase.Models.EducationGroup;
import com.chealown.csa.DataBase.Models.Post;
import com.chealown.csa.Entities.SecurityUtil;
import com.chealown.csa.Entities.StaticObjects;
import javafx.geometry.Pos;

import javax.crypto.SecretKey;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostRepository {
    private static final String QUERY = """
            SELECT id, post_name
            FROM post;
            """;


    public static boolean save(Post post) {
        if (StaticObjects.getSelectedObject() == null) {
            System.out.println("insert");
            return insert(post);
        } else {
            System.out.println("update");
            return update(post);
        }
    }

    private static boolean insert(Post post) {
        String sql = """
                INSERT INTO post
                (post_name)
                VALUES(?);
                """;

        Object[] params = {
                post.getPostName()
        };
        int result = DBConnector.update(sql, params);
        return result > 0;
    }


    private static boolean update(Post post) {
        String sql = """
                   UPDATE post
                   SET post_name=?
                   WHERE id=?;
                """;

        Object[] params = {
                post.getPostName(),
                post.getId()
        };

        return DBConnector.update(sql, params) > 0;
    }

    public static void delete(Post post) {
        String sql = """
                DELETE FROM post
                WHERE id=?;
                """;

        Object[] params = {
                post.getId()
        };

        DBConnector.update(sql, params);
    }


    public static List<Post> getAllData() throws SQLException {
        ArrayList<Post> data = new ArrayList<>();
        ResultSet rs = DBConnector.query(QUERY);
        while (rs.next()) {
            data.add(new Post(
                    rs.getInt("id"),
                    rs.getString("post_name")
            ));
        }

        return data;
    }
}
