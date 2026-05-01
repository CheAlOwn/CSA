package com.chealown.csa.DataBase.Models;

public class DocumentField {
    int id;
    int documentId;
    int fieldId;
    String value;

    public DocumentField(int id, int documentId, int fieldId, String value) {
        this.id = id;
        this.documentId = documentId;
        this.fieldId = fieldId;
        this.value = value;
    }

    public DocumentField(int documentId, int fieldId, String value) {
        this.documentId = documentId;
        this.fieldId = fieldId;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDocumentId() {
        return documentId;
    }

    public void setDocumentId(int documentId) {
        this.documentId = documentId;
    }

    public int getFieldId() {
        return fieldId;
    }

    public void setFieldId(int fieldId) {
        this.fieldId = fieldId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
