package com.chealown.csa.DataBase.Models;

public class Document {
    int id;
    int templateId;
    String label;
    String filePath;
    String createdAt;
    int userId;

    public Document(int id, int templateId, String label, String filePath, String createdAt, int userId) {
        this.id = id;
        this.templateId = templateId;
        this.label = label;
        this.filePath = filePath;
        this.createdAt = createdAt;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTemplateId() {
        return templateId;
    }

    public void setTemplateId(int templateId) {
        this.templateId = templateId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
