package com.chealown.csa.DataBase.Models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Status {
    IntegerProperty id = new SimpleIntegerProperty();
    StringProperty statusName = new SimpleStringProperty();

    public Status(int id, String statusName) {
        this.id = new SimpleIntegerProperty(id);
        this.statusName = new SimpleStringProperty(statusName != null ? statusName : "");
    }

    public Status() {
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

    public String getStatusName() {
        return statusName.get();
    }

    public StringProperty statusNameProperty() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName.set(statusName);
    }
}
