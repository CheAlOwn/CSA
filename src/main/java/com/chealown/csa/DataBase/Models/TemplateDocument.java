package com.chealown.csa.DataBase.Models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TemplateDocument {
    private int id;
    private String name; // Название документа
    private String filePath;
    private List<TemplateField> fields; // Поля для ввода в документ
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public TemplateDocument() {
        this.fields = new ArrayList<>();
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
