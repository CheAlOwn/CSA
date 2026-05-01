package com.chealown.csa.DataBase.Repositories;

import com.chealown.csa.DataBase.DBConnector;
import com.chealown.csa.DataBase.Models.Children;
import com.chealown.csa.DataBase.Models.Document;
import com.chealown.csa.Entities.SecurityUtil;
import com.chealown.csa.Entities.StaticObjects;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DocumentRepository {
    private static final String[] DISPLAY_COLUMNS = {
            "ID", "Название документа", "Шаблон", "Дата создания"
    };

    private static final String QUERY = """
            SELECT d.id, "label", template_name, d.created_at
            FROM "document" d
            INNER JOIN template t ON template_id = t.id
            WHERE archive = false;
            """;

    public static int save(Document document) {
        if (StaticObjects.getDocument() == null) {
            System.out.println("insert");
            return insert(document);
        } else {
            System.out.println("update");
            return update(document);
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
                document.getFilePath(),
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

    public static Object getData(int id, String findCol) throws SQLException {
        ResultSet rs = DBConnector.query("SELECT * FROM document WHERE id = ?", id);
        if (rs.next())
            return rs.getObject(findCol);
        return null;
    }

    public static void archive(Document document) {
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

    public static String[] getDisplayColumns() {
        return DISPLAY_COLUMNS;
    }

    public static String getQUERY() {
        return QUERY;
    }
}
