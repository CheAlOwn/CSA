package com.chealown.csa.DataBase.Repositories;

import com.chealown.csa.DataBase.DBConnector;
import com.chealown.csa.DataBase.Models.TemplateDocument;
import com.chealown.csa.Entities.StaticObjects;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TemplateRepository {
    private static final String[] DISPLAY_COLUMNS = {
            "ID", "Название шаблона", "Дата создания", "Дата изменения"
    };

    private static final String QUERY = """
            SELECT id, template_name, created_at, updated_at, file_path
            FROM "template";
            """;

    public static int save(TemplateDocument template) {
        if (StaticObjects.getTemplate() == null) {
            System.out.println("insert");
            return insert(template);
        } else {
            System.out.println("update");
            return update(template);
        }
    }

    private static int insert(TemplateDocument template) {
        String sql = """
                INSERT INTO "template"
                (template_name, file_path, created_at)
                VALUES(?, ?, ?)
                RETURNING id;
                """;

        Object[] params = {
                template.getName(),
                template.getFilePath(),
                template.getCreatedAt(),
        };

        int returnedId = DBConnector.updateWithReturningId(sql, params);
        return returnedId;
    }


    private static int update(TemplateDocument template) {
        String sql = """
                UPDATE "template"
                SET template_name=?, updated_at=?
                WHERE id=?;
                """;

        Object[] params = {
                template.getName(),
                template.getUpdatedAt(),
                template.getId()
        };

        return DBConnector.update(sql, params);
    }

    public static ArrayList<TemplateDocument> getAllData() throws SQLException {
        ResultSet rs = DBConnector.query(QUERY);
        ArrayList<TemplateDocument> templates = new ArrayList<>();
        while (rs.next()) {
            templates.add(new TemplateDocument(
                    rs.getInt("id"),
                    rs.getString("template_name"),
                    rs.getString("file_path"),
                    rs.getString("created_at"),
                    rs.getString("updated_at")
            ));
        }
        return templates;
    }

    public static String getQUERY() {
        return QUERY;
    }

    public static String[] getDisplayColumns() {
        return DISPLAY_COLUMNS;
    }
}
