package com.chealown.csa.DataBase.Repositories;

import com.chealown.csa.DataBase.DBConnector;
import com.chealown.csa.DataBase.Models.Children;
import com.chealown.csa.DataBase.Models.Document;
import com.chealown.csa.Entities.SecurityUtil;
import com.chealown.csa.Entities.StaticObjects;

public class DocumentRepository {
    private static final String[] DISPLAY_COLUMNS = {
        "ID", "Название", "Тип", "Дата создания"
    };

    private static final String QUERY = """
            SELECT id, template_id, user_id, file_path, created_at, archive, "label"
            FROM "document"
            WHERE archive = false;
            """;

    public static boolean save(Document document) {
        if (StaticObjects.getDocument() == null) {
            System.out.println("insert");
            return insert(document);
        } else {
            System.out.println("update");
            return update(document);
        }
    }

    private static boolean insert(Document document) {
        String sql = """
                INSERT INTO "document"
                (template_id, user_id, file_path, created_at, "label")
                VALUES(?, ?, ?, ?, ?);
                """;

        Object[] params = {
                document.getTemplateId(),
                document.getUserId(),
                document.getFilePath(),
                document.getCreatedAt(),
                document.getLabel()
        };
        int result = DBConnector.update(sql, params);
        System.out.println("result: " + result);
        return result > 0;
    }


    private static boolean update(Document document) {
        String sql = """
                UPDATE "document"
                SET template_id=?, user_id=?, file_path=?, created_at=?, "label"=?
                WHERE id=?;
                """;

        Object[] params = {
                document.getTemplateId(),
                document.getUserId(),
                document.getFilePath(),
                document.getCreatedAt(),
                document.getLabel(),
                document.getId()
        };

        return DBConnector.update(sql, params) > 0;
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
