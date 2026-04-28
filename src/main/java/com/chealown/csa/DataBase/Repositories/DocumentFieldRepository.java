package com.chealown.csa.DataBase.Repositories;

import com.chealown.csa.DataBase.DBConnector;
import com.chealown.csa.DataBase.Models.Document;
import com.chealown.csa.DataBase.Models.DocumentField;
import com.chealown.csa.Entities.StaticObjects;

public class DocumentFieldRepository {
    private static final String[] DISPLAY_COLUMNS = {};

    private static final String QUERY = """
            SELECT id, document_id, field_id, value
            FROM document_field_values;
            """;

    public static boolean save(DocumentField documentField) {
        if (StaticObjects.getDocument() == null) {
            System.out.println("insert");
            return insert(documentField);
        } else {
            System.out.println("update");
            return update(documentField);
        }
    }

    private static boolean insert(DocumentField documentField) {
        String sql = """
                INSERT INTO document_field_values
                (document_id, field_id, value)
                VALUES(?, ?, ?);
                """;

        Object[] params = {
                documentField.getDocumentId(),
                documentField.getFieldId(),
                documentField.getValue()
        };

        int result = DBConnector.update(sql, params);
        System.out.println("result: " + result);
        return result > 0;
    }


    private static boolean update(DocumentField documentField) {
        String sql = """
                UPDATE document_field_values
                SET document_id=?, field_id=?, value=?
                WHERE id=?;
                """;

        Object[] params = {
                documentField.getDocumentId(),
                documentField.getFieldId(),
                documentField.getValue(),
                documentField.getId()
        };

        return DBConnector.update(sql, params) > 0;
    }

    public static String[] getDisplayColumns() {
        return DISPLAY_COLUMNS;
    }

    public static String getQUERY() {
        return QUERY;
    }
}
