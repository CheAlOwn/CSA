package com.chealown.csa.DataBase.Repositories;

import com.chealown.csa.DataBase.DBConnector;
import com.chealown.csa.DataBase.Models.Document;
import com.chealown.csa.DataBase.Models.DocumentField;
import com.chealown.csa.DataBase.Models.TemplateField;
import com.chealown.csa.Entities.StaticObjects;

import javax.print.Doc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DocumentFieldRepository {

    private static final String QUERY = """
            SELECT id, document_id, field_id, value
            FROM document_field_values;
            """;

    public static boolean save(ArrayList<DocumentField> fields) {
        if (StaticObjects.getSelectedObject() == null) {
            System.out.println("insert");
            return insert(fields);
        } else {
            System.out.println("update");
            return update(fields);
        }
    }

    private static boolean insert(List<DocumentField> fields) {
        String sql = """
                INSERT INTO document_field_values
                (document_id, field_id, value)
                VALUES(?, ?, ?);
                """;

        List<Object[]> params = new ArrayList<>();
        for (DocumentField field : fields) {
            params.add(new Object[]{
                    field.getDocumentId(),
                    field.getFieldId(),
                    field.getValue()
            });
        }

        int[] result = DBConnector.batchInsert(sql, params);

        // Если батч выполнился без исключений - считаем успехом
        return result != null && result.length > 0;
    }


    private static boolean update(List<DocumentField> fields) {
        String sql = """
                UPDATE document_field_values
                SET document_id=?, field_id=?, value=?
                WHERE id=?;
                """;

        List<Object[]> params = new ArrayList<>();
        for (DocumentField field : fields) {
            params.add(new Object[]{
                    field.getDocumentId(),
                    field.getFieldId(),
                    field.getValue(),
                    field.getId()
            });
        }

        int[] result = DBConnector.batchInsert(sql, params);

        // Если батч выполнился без исключений - считаем успехом
        return result != null && result.length > 0;
    }

    public static ArrayList<DocumentField> getAllData(int documentId) throws SQLException {
        ResultSet rs = DBConnector.query("""
                    SELECT id, document_id, field_id, value
                    FROM document_field_values
                    WHERE document_id = ?
                """, documentId);
        ArrayList<DocumentField> docFields = new ArrayList<>();
        while (rs.next()) {
            docFields.add(new DocumentField(
                    rs.getInt("id"),
                    rs.getInt("document_id"),
                    rs.getInt("field_id"),
                    rs.getString("value")
            ));
        }
        return docFields;
    }

    public static String getQUERY() {
        return QUERY;
    }
}
