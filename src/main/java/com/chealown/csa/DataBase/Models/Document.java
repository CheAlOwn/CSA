package com.chealown.csa.DataBase.Models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;

public class Document {
    IntegerProperty id = new SimpleIntegerProperty();
    StringProperty template = new SimpleStringProperty();
    StringProperty label = new SimpleStringProperty();
    StringProperty createdAt = new SimpleStringProperty();
    IntegerProperty userId = new SimpleIntegerProperty();

    public Document(String template, String label, String createdAt, int userId) {
        this.template = new SimpleStringProperty(template != null ? template : "");
        this.label = new SimpleStringProperty(label != null ? label : "");
        this.createdAt = new SimpleStringProperty(createdAt != null ? createdAt : "");
        this.userId = new SimpleIntegerProperty(userId);
    }

    public Document(int id, String label, String createdAt, String template) {
        this.id = new SimpleIntegerProperty(id);
        this.label = new SimpleStringProperty(label != null ? label : "");
        this.createdAt = new SimpleStringProperty(createdAt != null ? createdAt : "");
        this.template = new SimpleStringProperty(template != null ? template : "");
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

    public String getTemplate() {
        return template.get();
    }

    public StringProperty templateProperty() {
        return template;
    }

    public void setTemplate(String template) {
        this.template.set(template);
    }

    public String getLabel() {
        return label.get();
    }

    public StringProperty labelProperty() {
        return label;
    }

    public void setLabel(String label) {
        this.label.set(label);
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

    public int getUserId() {
        return userId.get();
    }

    public IntegerProperty userIdProperty() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId.set(userId);
    }
}
