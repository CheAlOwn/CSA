package com.chealown.csa.DataBase.Repositories;

import com.chealown.csa.DataBase.DBConnector;
import com.chealown.csa.DataBase.Models.TemplateDocument;
import com.chealown.csa.DataBase.Models.TemplateField;
import com.chealown.csa.Entities.StaticObjects;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TemplateFieldRepository {
    private static final String QUERY = """
            SELECT id, template_id, placeholder, "label", is_required, default_value
            FROM template_field;
            """;

    public static boolean save(List<TemplateField> fields) {
        if (StaticObjects.getSelectedObject() == null) {
            System.out.println("insert");
            return insert(fields);
        } else {
            System.out.println("update");
            return update(fields);
        }
    }

    private static boolean insert(List<TemplateField> fields) {
        String sql = """
                INSERT INTO template_field
                (template_id, placeholder, "label", is_required, default_value)
                VALUES(?, ?, ?, ?, ?);
                """;

        List<Object[]> params = new ArrayList<>();
        for (TemplateField field : fields) {
            params.add(new Object[]{
                    field.getTemplateId(),
                    field.getPlaceholder(),
                    field.getLabel(),
                    field.isRequired(), // Добавлено поле is_required
                    field.getDefaultValue()
            });
        }

        int[] result = DBConnector.batchInsert(sql, params);

        // Если батч выполнился без исключений - считаем успехом
        return result != null && result.length > 0;
    }


    private static boolean update(List<TemplateField> fields) {
        String sql = """
                UPDATE template_field
                SET template_id=?, placeholder=?, "label"=?, is_required=?, default_value=?
                WHERE id=?;
                """;

        List<Object[]> params = new ArrayList<>();
        for (TemplateField field : fields) {
            params.add(new Object[]{
                    field.getTemplateId(),
                    field.getPlaceholder(),
                    field.getLabel(),
                    field.isRequired(), // Добавлено поле is_required
                    field.getDefaultValue(),
                    field.getId()
            });
        }

        int[] result = DBConnector.batchInsert(sql, params);

        // Если батч выполнился без исключений - считаем успехом
        return result != null && result.length > 0;
    }

    public static ArrayList<TemplateField> getAllData(int templateId) throws SQLException {
        ResultSet rs = DBConnector.query("""
                    SELECT id, template_id, placeholder, "label", is_required, default_value
                    FROM template_field
                    WHERE template_id = ?
                """, templateId);
        ArrayList<TemplateField> templates = new ArrayList<>();
        while (rs.next()) {
            templates.add(new TemplateField(
                    rs.getInt("id"),
                    rs.getInt("template_id"),
                    rs.getString("placeholder"),
                    rs.getString("label"),
                    rs.getBoolean("is_required"),
                    rs.getString("default_value")
            ));
        }
        return templates;
    }

    public static String getQUERY() {
        return QUERY;
    }
}