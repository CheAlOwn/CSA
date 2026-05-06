package com.chealown.csa.DataBase.Models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.List;

public class TemplateDocument {
    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty name = new SimpleStringProperty(); // Название документа
    private StringProperty filePath = new SimpleStringProperty();
    private List<TemplateField> fields; // Поля для ввода в документ
    private StringProperty createdAt = new SimpleStringProperty();
    private StringProperty updatedAt = new SimpleStringProperty();

    public TemplateDocument() {
        this.fields = new ArrayList<>();
    }

    public TemplateDocument(int id, String name, String createdAt, String updatedAt) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name != null ? name : "");
        this.createdAt = new SimpleStringProperty(createdAt != null ? createdAt : "");
        this.updatedAt = new SimpleStringProperty(updatedAt != null ? updatedAt : "");
        this.fields = new ArrayList<>();
    }

    public TemplateDocument(int id, String name, String filePath, String createdAt, String updatedAt) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name != null ? name : "");
        this.filePath = new SimpleStringProperty(filePath != null ? filePath : "");
        this.createdAt = new SimpleStringProperty(createdAt != null ? createdAt : "");
        this.updatedAt = new SimpleStringProperty(updatedAt != null ? updatedAt : "");
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getFilePath() {
        return filePath.get();
    }

    public StringProperty filePathProperty() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath.set(filePath);
    }

    public List<TemplateField> getFields() {
        return fields;
    }

    public void setFields(List<TemplateField> fields) {
        this.fields = fields;
    }

    public String getCreatedAt() {
        return createdAt.get();
    }

    public StringProperty createdAtProperty() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt.set(createdAt);
    }

    public String getUpdatedAt() {
        return updatedAt.get();
    }

    public StringProperty updatedAtProperty() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt.set(updatedAt);
    }
}
