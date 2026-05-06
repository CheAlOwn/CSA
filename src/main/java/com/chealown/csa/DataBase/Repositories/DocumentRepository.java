package com.chealown.csa.DataBase.Repositories;

import com.chealown.csa.DataBase.DBConnector;
import com.chealown.csa.DataBase.Models.Children;
import com.chealown.csa.DataBase.Models.Document;
import com.chealown.csa.Entities.SecurityUtil;
import com.chealown.csa.Entities.StaticObjects;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DocumentRepository {
    private static final String QUERY = """
            SELECT d.id, "label", template_name, d.created_at
            FROM "document" d
            INNER JOIN template t ON template_id = t.id
            WHERE archive = false or archive = ?;
            """;

    public static int save(Document document) {
        if (StaticObjects.getSelectedObject() == null) {
            System.out.println("insert");
            return insert(document);
        } else {
            System.out.println("update");
            return update(document);
        }
    }

    public static void deleteRecord(Document document, boolean isAdmin) {
        if (isAdmin) {
            delete(document);
        } else {
            archive(document);
        }
    }

    private static int insert(Document document) {
        String sql = """
                INSERT INTO "document"
                (template_id, user_id, created_at, "label", archive)
                VALUES(?, ?, ?, ?, false)
                RETURNING id
                """;

        Object[] params = {
                Integer.parseInt(document.getTemplate()),
                document.getUserId(),
                document.getCreatedAt(),
                document.getLabel()
        };

        return DBConnector.updateWithReturningId(sql, params);
    }


    private static int update(Document document) {
        String sql = """
                UPDATE "document"
                SET template_id=?, user_id=?, created_at=?, "label"=?
                WHERE id=?;
                """;

        Object[] params = {
                document.getTemplate(),
                document.getUserId(),
                document.getCreatedAt(),
                document.getLabel(),
                document.getId()
        };

        return DBConnector.update(sql, params);
    }

    public static ArrayList<Document> getAllData(boolean withArchive) throws SQLException {
        ArrayList<Document> data = new ArrayList<>();
        ResultSet rs = DBConnector.query(QUERY, withArchive);
        while (rs.next()) {
            data.add(new Document(
                    rs.getInt("id"),
                    rs.getString("label"),
                    rs.getString("created_at"),
                    rs.getString("template_name")
                    ));
        }

        return data;
    }

    public static Object getData(int id, String findCol) throws SQLException {
        ResultSet rs = DBConnector.query("SELECT * FROM document WHERE id = ?", id);
        if (rs.next())
            return rs.getObject(findCol);
        return null;
    }

    private static void archive(Document document) {
        String sql = """
                UPDATE "document"
                SET archive=true
                WHERE id=?;
                """;

        Object[] params = {
                document.getId()
        };

        DBConnector.update(sql, params);
    }

    private static void delete(Document document) {
        String sql = """
                DELETE FROM document
                WHERE id=?;
                """;

        Object[] params = {
                document.getId()
        };

        DBConnector.update(sql, params);
    }

    public static String getQUERY() {
        return QUERY;
    }
}
