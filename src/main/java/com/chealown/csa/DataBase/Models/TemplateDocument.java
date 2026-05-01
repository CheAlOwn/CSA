package com.chealown.csa.DataBase.Models;

import java.util.ArrayList;
import java.util.List;

public class TemplateDocument {
    private int id;
    private String name; // Название документа
    private String filePath;
    private List<TemplateField> fields; // Поля для ввода в документ
    private String createdAt;
    private String updatedAt;

    public TemplateDocument() {
        this.fields = new ArrayList<>();
    }

    public TemplateDocument(Object id, Object name, Object createdAt, Object updatedAt) {
        this.id = id != null ? (Integer) id : -1;
        this.name = name != null ? name.toString() : "";
        this.createdAt = createdAt != null ? createdAt.toString() : "";
        this.updatedAt = updatedAt != null ? updatedAt.toString() : "";
        this.fields = new ArrayList<>();
    }

    public TemplateDocument(int id, String name, String filePath, String createdAt, String updatedAt) {
        this.id = id;
        this.name = name;
        this.filePath = filePath;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public List<TemplateField> getFields() {
        return fields;
    }

    public void setFields(List<TemplateField> fields) {
        this.fields = fields;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
