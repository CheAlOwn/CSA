package com.chealown.csa.DataBase.Models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class HealthGroup {
    IntegerProperty id = new SimpleIntegerProperty();
    StringProperty groupName = new SimpleStringProperty();

    public HealthGroup(int id, String groupName) {
        this.id = new SimpleIntegerProperty(id);
        this.groupName = new SimpleStringProperty(groupName != null ? groupName : "");
    }

    public HealthGroup() {

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

    public String getGroupName() {
        return groupName.get();
    }

    public StringProperty groupNameProperty() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName.set(groupName);
    }
}
