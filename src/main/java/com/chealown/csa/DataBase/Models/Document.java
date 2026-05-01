package com.chealown.csa.DataBase.Models;

import java.util.ArrayList;

public class Document {
    int id;
    String template;
    String label;
    String filePath;
    String createdAt;
    int userId;
//
//    public Document(int id, String template, String label, String filePath, int userId) {
//        this.id = id;
//        this.template = template;
//        this.label = label;
//        this.filePath = filePath;
//        this.createdAt = createdAt;
//        this.userId = userId;
//    }
    public Document(String template, String label, String createdAt, int userId) {
        this.template = template;
        this.label = label;
        this.createdAt = createdAt;
        this.userId = userId;
    }

    public Document(Object id, Object label, Object createdAt, Object template) {
        this.id = id != null ? (Integer) id : -1;
        this.label = label != null ? label.toString() : "";
        this.createdAt = createdAt != null ? createdAt.toString() : "";
        this.template = template != null ? template.toString() : "";
    }

    public Document(String label, String createdAt, int userId, String template) {
        this.label = label;
        this.createdAt = createdAt;
        this.userId = userId;
        this.template = template;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
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
